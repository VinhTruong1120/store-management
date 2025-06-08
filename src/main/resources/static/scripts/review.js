fetch("https://api.sheetbest.com/sheets/34999d9f-d745-446c-87d6-2fcbc3edf55a")
    .then(res => res.json())
    .then(data => {
        const labels = [];
        const scores = [];

        data.forEach(row => {
            const time = row["Timestamp"];
            const rating = parseFloat(row["Đánh giá"]);
            if (!isNaN(rating)) {
                labels.push(time.split(" ")[0]); // chỉ lấy ngày
                scores.push(rating);
            }
        });

        const avg = (scores.reduce((a, b) => a + b, 0) / scores.length).toFixed(2);
        document.getElementById("avg-rating").innerText = avg;

        const ctx = document.getElementById("reviewChart").getContext("2d");
        new Chart(ctx, {
            type: "line",
            data: {
                labels,
                datasets: [{
                    label: "Điểm đánh giá",
                    data: scores,
                    fill: true,
                    borderColor: "#ffc107",
                    backgroundColor: "rgba(255, 193, 7, 0.2)",
                    tension: 0.3
                }]
            },
            options: {
                responsive: true,
                scales: {
                    y: {
                        min: 1,
                        max: 5,
                        title: { display: true, text: "Sao" }
                    }
                }
            }
        });
    })
    .catch(err => {
        console.error("Lỗi khi tải dữ liệu đánh giá:", err);
    });
