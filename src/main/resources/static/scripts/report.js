document.addEventListener("DOMContentLoaded", () => {

  

  document.getElementById('filterDate').addEventListener('click', () => {
    const from = document.getElementById('fromDate').value;
    const to = document.getElementById('toDate').value;
    const fromValue = new Date(from);
    const toValue = new Date(to);
    toValue.setHours(23, 59, 59); // Đảm bảo tính hết cả ngày cuối cùng


    const data = {
        ngay_bat_dau: from,
        ngay_kt: to
    }

    fetch('http://localhost:8080/api/admin/doanh_thu_x_den_y', {
    method: 'POST',
    headers: {
        'Content-Type':'application/json'
    },
    body: JSON.stringify(data)
    })
    .then(res => res.json())
    .then(responseData => {
        console.log()
        const totalPerDay = {};

    responseData.forEach(row => {
      const time = new Date(row.ngayNhan);
      if (time >= fromValue && time <= toValue) {
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
            text: `Tổng doanh thu từ ${fromValue.toLocaleDateString()} đến ${toValue.toLocaleDateString()}: ${totalRevenue.toLocaleString()} VNĐ`
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
    })
.catch(err => {
    console.error("lỗi khi gửi request: ",err);
}) ;   


    
  });
});


