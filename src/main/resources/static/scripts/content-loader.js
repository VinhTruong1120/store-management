// content-loader.js

// Hàm showSection này dùng để chuyển đổi giữa các tab nội bộ trong một trang (ví dụ: Người dùng, Phân quyền trong Manager)
function showSection(id) {
    // Đảm bảo chỉ tìm kiếm trong #dynamic-content-area
    document.querySelectorAll('#dynamic-content-area .section').forEach(section => section.classList.remove('active'));
    document.getElementById(id)?.classList.add('active'); // Sử dụng optional chaining để tránh lỗi nếu id không tồn tại

    document.querySelectorAll('#dynamic-content-area .nav-btns button').forEach(btn => btn.classList.remove('active'));
    // Tìm nút điều hướng tương ứng với section được kích hoạt và thêm class 'active'
    const clickedBtn = document.querySelector(`#dynamic-content-area .nav-btns button[data-section="${id}"]`);
    if (clickedBtn) {
        clickedBtn.classList.add('active');
    }
}

// HÀM CHUNG CHO TẤT CẢ CÁC FORM "THÊM MỚI" (Thêm người dùng, Thêm cửa hàng, Thêm nhiệm vụ)
function setupAddItemFormLogic() {
    // Lắng nghe sự kiện click trên tất cả các nút có class 'btn-add-item'
    const addItemBtns = document.querySelectorAll('.btn-add-item');
    addItemBtns.forEach(btn => {
        btn.addEventListener('click', function () {
            const formId = this.getAttribute('data-form-id'); // Lấy ID của form tương ứng
            const form = document.getElementById(formId);
            if (form) {
                form.style.display = 'block'; // Hiển thị form
                this.style.display = 'none'; // Ẩn nút 'Thêm mới'
            }
        });
    });

    // Lắng nghe sự kiện click trên tất cả các nút có class 'btn-close-form'
    const closeFormBtns = document.querySelectorAll('.add-form-container .btn-close-form');
    closeFormBtns.forEach(btn => {
        btn.addEventListener('click', function () {
            const form = this.closest('.add-form-container'); // Tìm form cha gần nhất
            if (form) {
                form.style.display = 'none'; // Ẩn form
                // Tìm lại nút 'Thêm mới' tương ứng và hiển thị nó
                const addItemBtn = document.querySelector(`.btn-add-item[data-form-id="${form.id}"]`);
                if (addItemBtn) {
                    addItemBtn.style.display = 'inline-block';
                }
            }
        });
    });
}

document.addEventListener('DOMContentLoaded', () => {
    const dynamicContentArea = document.getElementById('dynamic-content-area');
    const mainTitle = document.getElementById('main-title'); // Lấy element tiêu đề chính

    async function loadPage(pageName, url, title) {
        try {
            const response = await fetch(url);
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const html = await response.text();

            dynamicContentArea.innerHTML = html; // Chèn nội dung vào dynamic-content-area
            mainTitle.innerHTML = `<i class="fa-solid fa-shop"></i> ${title}`; // Cập nhật tiêu đề

            // Cập nhật trạng thái active của sidebar
            document.querySelectorAll('#sidebar-menu .nav-link').forEach(link => {
                link.classList.remove('active');
            });
            const activeLink = document.querySelector(`.nav-link[data-page="${pageName}"]`);
            if (activeLink) {
                activeLink.classList.add('active');
            } else {
                // Xử lý trường hợp Home submenu link không có data-page (nếu cần)
                // Ví dụ: nếu pageName là 'manager' nhưng link sidebar là link con của Home
                const homeSubmenuLink = document.querySelector(`.nav-home a[data-bs-toggle="collapse"]`);
                if (homeSubmenuLink) {
                    homeSubmenuLink.classList.add('active');
                    // Mở submenu nếu nó đang đóng
                    const homeSubmenu = document.getElementById('homeSubmenu');
                    if (homeSubmenu && !homeSubmenu.classList.contains('show')) {
                        new bootstrap.Collapse(homeSubmenu, { toggle: true });
                    }
                }
            }

            // Gọi hàm thiết lập form chung cho TẤT CẢ các trang có form thêm mới
            setupAddItemFormLogic();

            // Logic để kích hoạt tab con mặc định cho từng trang
            if (pageName === 'manager') {
                showSection('user'); // Kích hoạt tab 'Người dùng' mặc định cho Manager
            } else if (pageName === 'store') {
                showSection('store-overview'); // Kích hoạt tab 'Tổng quan cửa hàng' mặc định cho Store
            } else if (pageName === 'mission') {
                showSection('daily-tasks'); // Kích hoạt tab 'Nhiệm vụ hàng ngày' mặc định cho Mission
            }

            // Chạy các script nhúng trong nội dung được tải (nếu có)
            const scripts = dynamicContentArea.querySelectorAll('script');
            scripts.forEach(script => {
                const newScript = document.createElement('script');
                if (script.src) {
                    newScript.src = script.src;
                } else {
                    newScript.textContent = script.textContent;
                }
                dynamicContentArea.appendChild(newScript);
            });


        } catch (error) {
            console.error(`Lỗi khi tải trang ${pageName}:`, error);
            dynamicContentArea.innerHTML = `<p class="text-danger">Không thể tải nội dung cho ${title}. Vui lòng kiểm tra đường dẫn file hoặc lỗi server.</p>`;
        }
    }

    function loadHomePage() {
        const homeContent = `
            <div class="welcome-box text-center p-5 animated-fadein" style="background:#fffbe6; border-radius:16px; border:2px solid #f7b800; box-shadow:0 2px 12px rgba(0,0,0,0.07); transition:box-shadow 0.3s, transform 0.3s;">
                <h1 class="mb-3 animated-title" style="color:#f7b800; font-weight:bold; letter-spacing:1px;">
                  <span class="welcome-typing">Chào mừng đến với phần mềm quản lý chuỗi cửa hàng! <i class="fa-solid fa-store"></i></span>
                </h1>
                <p style="font-size:1.2rem; color:#444;">Hệ thống giúp bạn quản lý nhân viên, cửa hàng, sản phẩm, đơn hàng và nhiều hơn nữa một cách dễ dàng và hiệu quả.</p>
            </div>
        `;
        dynamicContentArea.innerHTML = homeContent;
        mainTitle.innerHTML = `<i class="fa-solid fa-shop"></i> Home`;

        document.querySelectorAll('#sidebar-menu .nav-link').forEach(link => {
            link.classList.remove('active');
        });
        document.querySelector('.nav-home > .nav-link').classList.add('active');
    }

    document.querySelectorAll('#sidebar-menu .nav-link[data-page]').forEach(link => {
        link.addEventListener('click', function (e) {
            e.preventDefault();
            const pageName = this.getAttribute('data-page');
            const href = this.getAttribute('href');
            const title = this.innerText.trim();
            if (pageName && href) {
                loadPage(pageName, href, title);
            }
        });
    });

    document.querySelector('.nav-home > .nav-link').addEventListener('click', function (e) {
        const homeSubmenu = document.getElementById('homeSubmenu');
        const isExpanded = homeSubmenu.classList.contains('show');

        if (isExpanded) {
            loadHomePage();
        }


        document.querySelectorAll('#sidebar-menu .nav-link').forEach(link => {
            link.classList.remove('active');
        });
        this.classList.add('active');
    });

    loadHomePage();
});

