document.addEventListener("DOMContentLoaded", () => {
    let chartHienTai = null;

    document.getElementById("filterDate").addEventListener("click", () => {
        const from = document.getElementById("fromDate").value;
        const to = document.getElementById("toDate").value;
        const fromValue = new Date(from);
        const toValue = new Date(to);
        toValue.setHours(23, 59, 59); // Đảm bảo tính hết cả ngày cuối cùng

        const data = {
            ngay_bat_dau: from,
            ngay_kt: to,
        };

        fetch("http://localhost:8080/api/admin/doanh_thu_x_den_y", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(data),
        })
            .then((res) => res.json())
            .then((responseData) => {
                console.log();
                const totalPerDay = {};

                responseData.forEach((row) => {
                    const time = new Date(row.ngayNhan);
                    if (time >= fromValue && time <= toValue) {
                        const dateKey = time.toISOString().split("T")[0]; // yyyy-mm-dd
                        if (!totalPerDay[dateKey]) totalPerDay[dateKey] = 0;
                        totalPerDay[dateKey] += row.tongTien;
                    }
                });

                const labels = Object.keys(totalPerDay).sort();
                const scores = labels.map((date) => totalPerDay[date]);
                const totalRevenue = scores.reduce((sum, val) => sum + val, 0);

                console.log("Tổng doanh thu:", totalRevenue);

                // Vẽ chart
                const ctx = document
                    .getElementById("reportRevenueChart")
                    .getContext("2d");
                if (chartHienTai) chartHienTai.destroy();
                chartHienTai = new Chart(ctx, {
                    type: "bar",
                    data: {
                        labels: labels,
                        datasets: [
                            {
                                label: "Tổng doanh thu mỗi ngày (VNĐ)",
                                data: scores,
                                backgroundColor: "rgba(75, 192, 192, 0.5)",
                                borderColor: "rgba(75, 192, 192, 1)",
                                borderWidth: 1,
                            },
                        ],
                    },
                    options: {
                        responsive: true,
                        scales: {
                            y: {
                                beginAtZero: true,
                                title: {
                                    display: true,
                                    text: "Doanh thu (VNĐ)",
                                },
                            },
                            x: {
                                title: {
                                    display: true,
                                    text: "Ngày",
                                },
                            },
                        },
                        plugins: {
                            title: {
                                display: true,
                                text: `Tổng doanh thu từ ${fromValue.toLocaleDateString()} đến ${toValue.toLocaleDateString()}: ${totalRevenue.toLocaleString()} VNĐ`,
                            },
                            tooltip: {
                                callbacks: {
                                    label: function (context) {
                                        const index = context.dataIndex;
                                        const value = context.raw;
                                        let label = `Doanh thu: ${value.toLocaleString()} VND`;

                                        if (index > 0) {
                                            const prev =
                                                context.chart.data.datasets[0]
                                                    .data[index - 1];
                                            const percentChange = (
                                                ((value - prev) / prev) *
                                                100
                                            ).toFixed(2);
                                            const symbol =
                                                percentChange >= 0
                                                    ? "🔺"
                                                    : "🔻";
                                            label += ` (${symbol} ${Math.abs(
                                                percentChange
                                            )}%)`;
                                        }

                                        return label;
                                    },
                                },
                            },
                        },
                    },
                });
            })
            .catch((err) => {
                console.error("lỗi khi gửi request: ", err);
            });
    });

    let currrentBill = null;

    document.getElementById("filterDateBill").addEventListener("click", () => {
        const fromBill = document.getElementById("fromDateBill").value;
        const toBill = document.getElementById("toDateBill").value;
        const storeSelect = document.getElementById("storeSelect").value;
        const storename = document.getElementById("storeSelect").options[document.getElementById("storeSelect").selectedIndex].text;
        if (!fromBill || !toBill) {
            alert("Vui lòng chọn đầy đủ khoảng ngày");
            return;
        }

        const fromDateBill = new Date(fromBill);
        const toDateBill = new Date(toBill);
        toDateBill.setHours(23, 59, 59);

        const data = {
            ngay_bd: fromDateBill,
            ngay_kt: toDateBill,
            store_id: storeSelect || null,
        };

        fetch("http://localhost:8080/api/admin/Lich_su_cac_don", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(data),
        })
            .then((res) => {
                if (!res.ok) throw new Error("Lỗi không tải được json");
                return res.json(); // ✅ chỉ một lần duy nhất ở đây
            })
            .then((responseData) => {
                console.log(responseData); // Kiểm tra dữ liệu trả về
                const body = document.getElementById("bill-body");
                console.log(body);
                body.innerHTML = "";
                responseData.forEach((row) => {
                    const time = new Date(row.ngay);
                    if (
                        time >= fromDateBill &&
                        time <= toDateBill &&
                        (!storename || row.store_name === storename)
                    ) {
                        const tr = document.createElement("tr");
                        tr.innerHTML = `
                    <td>${row.id}</td>
                    <td>${row.nv_id}</td>
                    <td>${formatDateTime(row.ngay)}</td>
                    <td>${row.store_name}</td>
                `;
                        tr.addEventListener("click", () => {
                            showBillDetailToast(row);
                        });
                        body.appendChild(tr);
                    }
                });
            })
            .catch((err) => {
                console.error("Lỗi khi gọi API:", err);
                alert("Đã xảy ra lỗi khi tải dữ liệu");
            });
    });

    window.addEventListener("DOMContentLoaded", async () => {
        try {
            const response = await fetch(
                "http://localhost:8080/api/admin/all_CH"
            );

            if (!response.ok) {
                throw new Error("Lỗi khi gọi API cửa hàng");
            }

            const data = await response.json();
            console.log(data);
            const storeSelect = document.getElementById("storeSelect");

            // Xóa và thêm option mặc định
            storeSelect.innerHTML = `<option value="">Chọn cửa hàng</option>`;

            // Thêm các cửa hàng vào select
            data.forEach((store) => {
                const option = document.createElement("option");
                option.value = store.store_id;
                option.innerText = store.ten_cua_hang;
                storeSelect.appendChild(option);
            });
        } catch (error) {
            console.error("Lỗi khi tải danh sách cửa hàng:", error);
        }
    });
});

function formatDateTime(str) {
    const d = new Date(str);
    return d.toTimeString().slice(0, 8) + " " + d.toLocaleDateString("vi-VN");
}
