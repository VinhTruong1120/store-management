fetch("https://api.sheetbest.com/sheets/34999d9f-d745-446c-87d6-2fcbc3edf55a")
  .then(res => res.json())
  .then(data => {
    const container = document.querySelector(".list-group");
    container.innerHTML = "";

    data.forEach(row => {
      const name = row["Họ và tên"] || "Khách ẩn danh";
      const phone = row["Số điện thoại"] || "Ẩn";
      const issue = row["Vấn đề"] || "Không ghi rõ";
      const reason = row["Lý do"] || "Không có";
      const rating = parseInt(row["Đánh giá"]) || 0;
      const time = row["Timestamp"] || "Không rõ thời gian";

      const stars = "⭐".repeat(rating);

      const div = document.createElement("div");
      div.className = "list-group-item list-group-item-dark mb-3";

      div.innerHTML = `
        <strong>${name}</strong> (${phone})<br>
        <span class="text-muted">🕒 ${time}</span><br>
        <strong>Vấn đề:</strong> ${issue}<br>
        <strong>Lý do:</strong> ${reason}<br>
        <strong>Đánh giá:</strong> <span class="text-warning">${stars || "Chưa có"}</span>
      `;

      container.appendChild(div);
    });
  })
  .catch(err => {
    console.error("Lỗi khi tải phản hồi:", err);
  });
s