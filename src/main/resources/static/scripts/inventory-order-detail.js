
let inventoryData = [];
let inventoryOrder = {};

// Tải dữ liệu nguyên liệu từ goods.json
fetch("/data/goods.json")
  .then(res => res.json())
  .then(data => {
    inventoryData = data;
    const categories = [...new Set(data.map(item => item.category))];
    renderCategoryButtons(categories);
    loadCategory(categories[0]);
  });

function renderCategoryButtons(categories) {
  const container = document.getElementById("inventory-category");
  container.innerHTML = "";
  categories.forEach(cat => {
    const btn = document.createElement("button");
    btn.className = "btn btn-outline-dark btn-sm";
    btn.innerText = cat;
    btn.onclick = () => loadCategory(cat);
    container.appendChild(btn);
  });
}

function loadCategory(category) {
  const list = document.getElementById("inventory-menu-list");
  list.innerHTML = "";

  inventoryData.filter(item => item.category === category).forEach(item => {
    const div = document.createElement("div");
    div.className = "menu-item d-flex justify-content-between align-items-center p-2 mb-2 bg-dark text-light rounded";
    div.innerHTML = `
      <div>
        <strong>${item.name}</strong><br/>
        <small class="text-muted">Đơn vị: ${item.unit}</small>
      </div>
      <button class="btn btn-sm btn-warning" onclick='addToInventoryOrder("${item.name}", "${item.unit}")'>Thêm</button>
    `;
    list.appendChild(div);
  });
}

function addToInventoryOrder(name, unit) {
  if (!inventoryOrder[name]) {
    inventoryOrder[name] = { quantity: 0, unit };
  }
  inventoryOrder[name].quantity++;
  renderInventoryOrder();
}

function updateInventoryQuantity(name, delta) {
  if (!inventoryOrder[name]) return;
  inventoryOrder[name].quantity += delta;
  if (inventoryOrder[name].quantity <= 0) delete inventoryOrder[name];
  renderInventoryOrder();
}

function renderInventoryOrder() {
  const container = document.getElementById("inventory-order-items");
  container.innerHTML = "";
  for (const name in inventoryOrder) {
    const item = inventoryOrder[name];
    const div = document.createElement("div");
    div.className = "order-item d-flex justify-content-between align-items-center mb-2";
    div.innerHTML = `
      <span>${name} (${item.unit})</span>
      <div class="d-flex gap-2 align-items-center">
        <button class="btn btn-sm btn-outline-secondary" onclick="updateInventoryQuantity('${name}', -1)">-</button>
        <span>${item.quantity}</span>
        <button class="btn btn-sm btn-outline-secondary" onclick="updateInventoryQuantity('${name}', 1)">+</button>
      </div>
    `;
    container.appendChild(div);
  }
}

function submitInventoryOrder() {
  const orders = [];
  for (const name in inventoryOrder) {
    const item = inventoryOrder[name];
    orders.push({
      Nguyên_liệu: name,
      Đơn_vị: item.unit,
      Số_lượng: item.quantity,
      Ngày_đặt: new Date().toLocaleDateString("vi-VN"),
    });
  }

  fetch("https://api.sheetbest.com/sheets/97e8060a-1e22-4cab-bd90-f9fc09ded7cb", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(orders)
  })
    .then(() => {
      alert("✅ Đã gửi đơn hàng tới kho!");
      inventoryOrder = {};
      renderInventoryOrder();
    })
    .catch(err => {
      console.error("❌ Lỗi khi gửi đơn:", err);
      alert("❌ Gửi đơn không thành công.");
    });
}
