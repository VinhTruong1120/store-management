const urlParams = new URLSearchParams(window.location.search);
const pagerNumber = urlParams.get("pager");

if (pagerNumber) {
  const header = document.getElementById("pager-title");
  if (header) header.innerHTML = `<i class="fa-solid fa-shop"> Order - ${pagerNumber}</i>`;
}

let productsByCategory = {}; // Đổi tên biến để rõ ràng hơn
let order = {};

function loadCategory(categoryId) {
  const list = document.getElementById("menu-list");
  list.innerHTML = "";
  if (!productsByCategory[categoryId]) return;

  productsByCategory[categoryId].forEach(item => {
    const div = document.createElement("div");
    div.className = "menu-item";
    div.innerHTML = `
      <div><i class='fa-solid fa-mug-hot'></i></div>
      <div class='name'>${item.name}</div>
      <div>${item.price.toLocaleString("vi-VN")}đ</div>
      <button class='btn btn-sm btn-warning' onclick='addToOrder("${item.name}", ${item.price}, "${item.ma_sp}" )'>Thêm</button>
    `;
    list.appendChild(div);
  });
}

function renderCategoryButtons(categories) {
  const container = document.getElementById("category-buttons");
  container.innerHTML = "";
  categories.forEach(cat => {
    const btn = document.createElement("button");
    btn.className = "btn btn-outline-dark category-btn me-2";
    btn.innerText = cat.name;
    btn.onclick = () => loadCategory(cat.id);
    container.appendChild(btn);
  });
}

function addToOrder(name, price, ma_sp) { // Thêm tham số ma_sp
  if (!order[ma_sp]) order[ma_sp] = { name, quantity: 0, price, ma_sp }; // Sử dụng ma_sp làm key
  order[ma_sp].quantity++;
  renderOrder();
}

function updateQuantity(ma_sp, delta) { // Sử dụng ma_sp làm key
  if (!order[ma_sp]) return;
  order[ma_sp].quantity += delta;
  if (order[ma_sp].quantity <= 0) delete order[ma_sp];
  renderOrder();
}

function renderOrder() {
  const orderItems = document.getElementById("order-items");
  const orderTotal = document.getElementById("order-total");
  orderItems.innerHTML = "";
  let total = 0;

  for (const ma_sp in order) {
    const item = order[ma_sp];
    const itemTotal = item.quantity * item.price;
    total += itemTotal;
    const div = document.createElement("div");
    div.className = "order-item";
    div.innerHTML = `
      <span>${item.name}</span>
      <div class="controls d-flex align-items-center gap-2">
        <button class="btn btn-sm btn-outline-secondary" onclick="updateQuantity('${ma_sp}', -1)">-</button>
        <span>${item.quantity}</span>
        <button class="btn btn-sm btn-outline-secondary" onclick="updateQuantity('${ma_sp}', 1)">+</button>
        <strong>${itemTotal.toLocaleString("vi-VN")}đ</strong>
      </div>
    `;
    orderItems.appendChild(div);
  }

  orderTotal.textContent = `${total.toLocaleString("vi-VN")}đ`;
}

window.onload = () => {
  fetch("/api/sale/lay_toan_bo_sp", { // Đường dẫn API
    method: 'POST', // Phương thức POST
    headers: {
      'Content-Type': 'application/json' // Loại nội dung gửi đi (nếu có)
    },
    // body: JSON.stringify({}), // Dữ liệu gửi đi (nếu có)
  })
    .then(res => {
      if (!res.ok) {
        throw new Error(`HTTP error! status: ${res.status}`);
      }
      return res.json();
    })
    .then(data => {
      // Chuyển đổi cấu trúc products để dễ truy cập theo categoryId
      const groupedProducts = {};
      for (const categoryProducts of data.products) {
        if (categoryProducts.length > 0) {
          const categoryId = categoryProducts[0].ma_loai;
          groupedProducts[categoryId] = categoryProducts.map(product => ({
            ma_sp: product.ma_sp, // Lưu trữ cả ma_sp (product_id)
            name: product.name,
            price: product.price
          }));
        }
      }
      productsByCategory = groupedProducts;
      renderCategoryButtons(data.categories);
      if (data.categories && data.categories.length > 0) {
        loadCategory(data.categories[0].id); // Tải danh mục đầu tiên
      } else {
        console.warn("Không có danh mục nào được trả về từ API.");
      }
    })
    .catch(err => {
      console.error("Lỗi khi gọi API /lay_toan_bo_sp:", err);
    });
};

function getPagerNumberFromURL() {
  const urlParams = new URLSearchParams(window.location.search);
  return parseInt(urlParams.get("pager")) || 0;
}

document.getElementById("order-submit").addEventListener("click", function () {
  const pager = getPagerNumberFromURL();
  const orderId = generateOrderId();
  const employeeId = ""; // Hãy thay thế bằng cách lấy ID nhân viên thực tế
  const storeId = "";   // Hãy thay thế bằng cách lấy ID cửa hàng thực tế
  const itemsToSend = [];
  console.log(orderId);
  for (const ma_sp in order) {
    const { quantity } = order[ma_sp];
    itemsToSend.push({ product_id: ma_sp, quantity: quantity });
  }
  
  let totals = 0;

  for (const ma_sp in order) {
    const item = order[ma_sp];
    const itemTotal = item.quantity * item.price;
    totals += itemTotal;
  }
  const orderData = {
    ID: orderId,
    ID_nv: employeeId,
    store_id: storeId,
    items: itemsToSend,
    thanh_tien: totals
  };
  console.log(orderData);
  fetch(`http://localhost:8080/api/sale/mua_hang?pager=${pager}`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(orderData)
  })
    .then(res => {
      if (!res.ok) throw new Error(`Gửi đơn thất bại: ${res.status}`);
      return res.text();
    })
    .then(hoaDonHTML => {
      let printWindow = window.open('', '_blank');
      printWindow.document.open();
      printWindow.document.write(hoaDonHTML);
      printWindow.document.close();
      printWindow.print(); // Kích hoạt in
      order = {};
      renderOrder();
    })
    .catch(err => {
      console.error("Lỗi gửi đơn:", err);
      alert("❌ Gửi đơn thất bại.");
    });
});
function generateOrderId() {
  const timestamp = Date.now().toString(36).slice(-4); // Lấy 4 ký tự cuối của timestamp base36
  const randomPart = Math.random().toString(36).substring(2, 3).toUpperCase(); // Lấy 1 ký tự ngẫu nhiên và in hoa
  return `DH-${timestamp}${randomPart}`; // Tổng cộng 3 + 4 + 1 = 8 ký tự (đảm bảo an toàn trong 10)
}