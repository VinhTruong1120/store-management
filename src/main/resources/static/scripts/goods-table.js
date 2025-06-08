document.addEventListener('DOMContentLoaded', () => {
    fetch('../data/inventory.json') // Đường dẫn từ file js đến file json
      .then(response => response.json())
      .then(data => {
        const tableBody = document.querySelector('#productTable tbody');
  
        data.forEach(item => {
          const row = document.createElement('tr');
  
          row.innerHTML = `
            <td>${item.id}</td>
            <td>${item.product}</td>
            <td>${item["Unit of Measure"]}</td>
            <td>${item.Quantity}</td>
          `;
  
          tableBody.appendChild(row);
        });
      })
      .catch(error => {
        console.error('Lỗi khi tải JSON:', error);
      });
  });
  