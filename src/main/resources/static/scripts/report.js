let rawData = []; // để lưu toàn bộ dữ liệu JSON

// Tải dữ liệu JSON trước
fetch('./data/test.json')
  .then(res => res.json())
  .then(data => {
    rawData = data; // lưu lại để dùng khi lọc
  })
  .catch(err => {
    console.error("Lỗi khi đọc JSON:", err);
  });

document.addEventListener("DOMContentLoaded", () => {
  let rawData = [];

  // Đọc dữ liệu JSON trước
  fetch('./data/random_revenue_data.json')
    .then(res => res.json())
    .then(data => {
      rawData = data;
    });

  document.getElementById('filterDate').addEventListener('click', () => {
    const from = new Date(document.getElementById('fromDate').value);
    const to = new Date(document.getElementById('toDate').value);
    to.setHours(23, 59, 59); // Đảm bảo tính hết cả ngày cuối cùng

    const totalPerDay = {};

    rawData.forEach(row => {
      const time = new Date(row.ngayNhan);
      if (time >= from && time <= to) {
        const dateKey = time.toISOString().split('T')[0]; // yyyy-mm-dd
        if (!totalPerDay[dateKey]) totalPerDay[dateKey] = 0;
        totalPerDay[dateKey] += row.tongTien;
      }
    });

    const labels = Object.keys(totalPerDay).sort();
    const scores = labels.map(date => totalPerDay[date]);
    const totalRevenue = scores.reduce((sum, val) => sum + val, 0);

    console.log("Tổng doanh thu:", totalRevenue);

    // Vẽ chart
    const ctx = document.getElementById('reportRevenueChart').getContext('2d');
    new Chart(ctx, {
      type: 'bar',
      data: {
        labels: labels,
        datasets: [{
          label: "Tổng doanh thu mỗi ngày (VNĐ)",
          data: scores,
          backgroundColor: "rgba(75, 192, 192, 0.5)",
          borderColor: "rgba(75, 192, 192, 1)",
          borderWidth: 1
        }]
      },
      options: {
        responsive: true,
        scales: {
          y: {
            beginAtZero: true,
            title: {
              display: true,
              text: 'Doanh thu (VNĐ)'
            }
          },
          x: {
            title: {
              display: true,
              text: 'Ngày'
            }
          }
        },
        plugins: {
          title: {
            display: true,
            text: `Tổng doanh thu từ ${from.toLocaleDateString()} đến ${to.toLocaleDateString()}: ${totalRevenue.toLocaleString()} VNĐ`
          },
          tooltip: {
            callbacks: {
                label: function(context) {
                const index = context.dataIndex;
                const value = context.raw;
                let label = `Doanh thu: ${value.toLocaleString()} VND`;

                if (index > 0) {
                    const prev = context.chart.data.datasets[0].data[index - 1];
                    const percentChange = ((value - prev) / prev * 100).toFixed(2);
                    const symbol = percentChange >= 0 ? '🔺' : '🔻';
                    label += ` (${symbol} ${Math.abs(percentChange)}%)`;
                }

                return label;
                }
            }
            }
        }
      }
    });
  });
});


