let staffData = [];

document.querySelector('.btn-add-staff').addEventListener('click', function () {
    document.getElementById('addStaffForm').style.display = 'block';
    this.style.display = 'none';
});

document.querySelector('.btn-close-form').addEventListener('click', function () {
    document.getElementById('addStaffForm').style.display = 'none';
    document.querySelector('.btn-add-staff').style.display = 'inline-block';
    document.getElementById('addStaffForm').querySelector('form').reset();
});

document.getElementById('Them').addEventListener('click', function () {
    const name = document.getElementById('fullName').value.trim();
    const position = document.getElementById('roleSelect').value.trim();
    const store = document.getElementById('storeSelect').value.trim();
    const address = document.getElementById('address').value.trim();
    const dob = document.getElementById('NS').value.trim();

    if (!name || /\d/.test(name)) {
        alert('Tên nhân viên không được chứa số và không được để trống!');
        return;
    }
    if (!dob) {
        alert('Vui lòng nhập đầy đủ thông tin!');
        return;
    }
    const dobDate = new Date(dob);
    const now = new Date();
    const age = now.getFullYear() - dobDate.getFullYear() - (now.getMonth() < dobDate.getMonth() || (now.getMonth() === dobDate.getMonth() && now.getDate() < dobDate.getDate()) ? 1 : 0);
    if (age < 18) {
        alert('Nhân viên phải lớn hơn 18 tuổi!');
        return;
    }
    if (!position || !store || !address) {
        alert('Vui lòng nhập đầy đủ thông tin!');
        return;
    }

    const dataNhanVien = {
        id: null,
        hoten: name,
        vitri: position,
        id_ch: store,
        birthday: dob,
        dia_chi: address
    }

    // const id = 'NV' + String(staffData.length + 1).padStart(4, '0');
    // staffData.push({ id, name, position, address, dob, store, active: true });

    fetch('http://localhost:8080/api/manager/tao_staff', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(dataNhanVien)
    })
        .then(res => {
            if (!res.ok) throw new Error("Tạo thất bại!")
            return res.text
        })
        .then(data => {
            alert("Tạo thành công")

            location.reload();
        })
        .catch(err => {
            console.error("Lỗi khi tạo nhân viên", err);
            alert("Có lỗi khi tạo nhân viên")
        })

    document.getElementById('addStaffForm').style.display = 'none';
    document.querySelector('.btn-add-staff').style.display = 'inline-block';
    document.getElementById('addStaffForm').querySelector('form').reset();
});


document.addEventListener('DOMContentLoaded', () => {
    fetch('http://localhost:8080/api/manager/lay_staff', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({})
    })
        .then(res => {
            if (!res.ok) throw new Error("Lỗi không load được danh sách nhân viên")
            return res.json();
        })
        .then(data => {
            console.log(data)
            staffData = data.map(item => ({
                id: item.id,
                name: item.name,
                position: item.position,
                address: item.address,
                dob: convertToDOB(item.dob),
                store: item.store_name
            }));
            renderStaffTable();
        })
})
function convertToDOB(dateString) {
    if (typeof dateString === 'string' && dateString.includes('T')) {
        return dateString.split('T')[0];
    } else {
        return dateString;
    }
}
function xoa_staff(id) {
    if (!confirm("Bạn có muốn xoá nhân viên này?")) return;

    fetch(`http://localhost:8080/api/manager/xoa_staff?id=${encodeURIComponent(id)}`, {
        method: 'DELETE'
    })
        .then(res => {
            if (!res.ok) throw new Error("Xoá thất bại");
            return res.text();
        })
        .then(message => {
            alert(message);
            staffData = staffData.filter(s => s.id !== id);
            renderStaffTable();
        })
        .catch(err => {
            console.error("Lỗi khi xoá nhân viên:", err);
            alert("Đã xảy ra lỗi khi xoá nhân viên.");
        });
}
function createFilterUI() {
    const container = document.querySelector('.container');
    if (!container) return;
    let filterDiv = document.getElementById('staff-filter-bar');
    if (!filterDiv) {
        filterDiv = document.createElement('div');
        filterDiv.id = 'staff-filter-bar';
        filterDiv.style.display = 'flex';
        filterDiv.style.gap = '12px';
        filterDiv.style.marginBottom = '18px';
        filterDiv.style.justifyContent = 'flex-end';
        filterDiv.style.width = '100%';
        filterDiv.innerHTML = `
            <i class="fa-solid fa-filter" style="margin-top: 8px"></i>
            <input id="filter-name" type="text" placeholder="Lọc theo tên nhân viên..." style="padding:4px 8px; border-radius:5px; border:1px solid #bbb;">
            <select id="filter-position" style="padding:4px 8px; border-radius:5px; border:1px solid #bbb;">
                <option value="">Tất cả vị trí</option>
                <option value="Nhân viên quản lý cửa hàng">Nhân viên quản lý cửa hàng</option>
                <option value="Nhân viên thu ngân">Nhân viên thu ngân</option>
                <option value="Nhân viên phục vụ">Nhân viên phục vụ</option>
                <option value="Nhân viên pha chế">Nhân viên pha chế</option>
                <option value="Nhân viên tạp vụ">Nhân viên tạp vụ</option>
                <option value="Nhân viên bảo vệ">Nhân viên bảo vệ</option>
            </select>
            <select id="filter-store" style="padding:4px 8px; border-radius:5px; border:1px solid #bbb;">
                <option value="">Tất cả cửa hàng</option>
                
            </select>
            <select id="filter-dob" style="padding:4px 8px; border-radius:5px; border:1px solid #bbb;">
                <option value="">Sắp xếp ngày sinh</option>
                <option value="asc">Từ thấp đến cao</option>
                <option value="desc">Từ cao đến thấp</option>
            </select>
            <button id="clear-filter-btn" style="padding:4px 14px; border-radius:5px; border:1px solid #bbb; background:#eee; cursor:pointer;">Xóa lọc</button>
        `;
        const yellowBox = container.querySelector('.p-4');
        if (yellowBox) {
            container.insertBefore(filterDiv, yellowBox);
        } else {
            container.insertBefore(filterDiv, container.firstChild);
        }
    }
}

function getFilteredStaff() {
    let filtered = staffData.slice();
    const nameVal = document.getElementById('filter-name')?.value.trim().toLowerCase() || '';
    const posVal = document.getElementById('filter-position')?.value || '';
    const storeVal = document.getElementById('filter-store')?.value || '';
    const dobSort = document.getElementById('filter-dob')?.value || '';
    const nowYear = new Date().getFullYear();

    if (nameVal) filtered = filtered.filter(s => s.name.toLowerCase().includes(nameVal));
    if (posVal) filtered = filtered.filter(s => s.position === posVal);
    if (storeVal) filtered = filtered.filter(s => s.store === storeVal);

    if (dobSort === 'asc') {
        filtered.sort((a, b) => {
            const ageA = nowYear - new Date(a.dob).getFullYear();
            const ageB = nowYear - new Date(b.dob).getFullYear();
            return ageA - ageB;
        });
    } else if (dobSort === 'desc') {
        filtered.sort((a, b) => {
            const ageA = nowYear - new Date(a.dob).getFullYear();
            const ageB = nowYear - new Date(b.dob).getFullYear();
            return ageB - ageA;
        });
    } else if (nameVal || posVal) {
        filtered.sort((a, b) => a.name.localeCompare(b.name, 'vi'));
    }
    return filtered;
}

function renderStaffTable() {
    const tbody = document.getElementById('staff-body');
    tbody.innerHTML = '';
    const filtered = getFilteredStaff();
    filtered.forEach((staff, idx) => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${staff.id}</td>
            <td>${staff.name}</td>
            <td>${staff.position}</td>
            <td>${staff.address}</td>
            <td>${staff.dob}</td>
            <td>${staff.store}</td>
            <td>
                <button class="btn btn-sm btn-primary" onclick="editStaff(${staffData.indexOf(staff)})">Chỉnh sửa</button>
                <button class="btn btn-sm btn-danger" onclick="xoa_staff('${staff.id}')">Xóa</button>
            </td>
        `;
        tbody.appendChild(tr);
    });
}

let editingIndex = null;

function showEditStaffForm(idx) {
    editingIndex = idx;
    const staff = staffData[idx];
    let editDiv = document.getElementById('editStaffFormPopup');
    if (!editDiv) {
        editDiv = document.createElement('div');
        editDiv.id = 'editStaffFormPopup';
        editDiv.className = 'staff-overlay';
        editDiv.innerHTML = `
        <form id="edit-staff-form" class="staff-form-popup">
            <button type="button" class="close-btn" id="edit-staff-close">✖</button>
            <h2>Chỉnh sửa nhân viên</h2>
            <div class="staff-form-grid">
                <div>
                    <label for="edit-staff-name">Tên nhân viên</label>
                    <input id="edit-staff-name" type="text" required>
                </div>
                <div>
                    <label for="edit-staff-position">Vị trí</label>
                    <input id="edit-staff-position" type="text" required>
                </div>
                <div>
                    <label for="edit-staff-address">Địa chỉ</label>
                    <input id="edit-staff-address" type="text" required>
                </div>
                <div>
                    <label for="edit-staff-dob">Ngày sinh</label>
                    <input id="edit-staff-dob" type="date" required>
                </div>
                <div>
                    <label for="edit-staff-store">Cửa hàng</label>
                    <input id="edit-staff-store" type="text" required>
                </div>
            </div>
            <div class="staff-form-actions">
                <button type="submit" class="submit-btn">Lưu</button>
                <button type="button" class="btn-staff" id="edit-staff-cancel">Hủy</button>
            </div>
        </form>
        `;
        document.body.appendChild(editDiv);
    } else {
        editDiv.style.display = 'flex';
    }
    document.getElementById('edit-staff-name').value = staff.name;
    document.getElementById('edit-staff-position').value = staff.position;
    document.getElementById('edit-staff-address').value = staff.address;
    document.getElementById('edit-staff-dob').value = staff.dob;
    document.getElementById('edit-staff-store').value = staff.store;
    editDiv.classList.remove('hidden');

    document.getElementById('edit-staff-close').onclick = hideEditStaffForm;
    document.getElementById('edit-staff-cancel').onclick = hideEditStaffForm;
    document.getElementById('edit-staff-form').onsubmit = handleEditStaffSubmit;
}

function hideEditStaffForm() {
    const editDiv = document.getElementById('editStaffFormPopup');
    if (editDiv) editDiv.classList.add('hidden');
    editingIndex = null;
}

function handleEditStaffSubmit(e) {
    e.preventDefault();
    const name = document.getElementById('edit-staff-name').value.trim();
    const position = document.getElementById('edit-staff-position').value.trim();
    const address = document.getElementById('edit-staff-address').value.trim();
    const dob = document.getElementById('edit-staff-dob').value.trim();
    const store = document.getElementById('edit-staff-store').value.trim();
    if (!name || /\d/.test(name)) {
        alert('Tên nhân viên không được chứa số và không được để trống!');
        return;
    }
    if (!dob) {
        alert('Vui lòng nhập đầy đủ thông tin!');
        return;
    }
    const dobDate = new Date(dob);
    const now = new Date();
    const age = now.getFullYear() - dobDate.getFullYear() - (now.getMonth() < dobDate.getMonth() || (now.getMonth() === dobDate.getMonth() && now.getDate() < dobDate.getDate()) ? 1 : 0);
    if (age < 18) {
        alert('Nhân viên phải lớn hơn 18 tuổi!');
        return;
    }
    if (!position || !store || !address) {
        alert('Vui lòng nhập đầy đủ thông tin!');
        return;
    }
    staffData[editingIndex] = {
        ...staffData[editingIndex],
        name, position, address, dob, store
    };
    renderStaffTable();
    hideEditStaffForm();
}

function editStaff(idx) {
    showEditStaffForm(idx);
}

function toggleActive(idx) {
    if (confirm('Bạn có chắc muốn xóa nhân viên này không?')) {
        staffData.splice(idx, 1);
        renderStaffTable();
    }
}

function setupFilterEvents() {
    ['filter-name', 'filter-position', 'filter-store', 'filter-dob'].forEach(id => {
        const el = document.getElementById(id);
        if (el) el.addEventListener('input', renderStaffTable);
        if (el && el.tagName === 'SELECT') el.addEventListener('change', renderStaffTable);
    });
    const clearBtn = document.getElementById('clear-filter-btn');
    if (clearBtn) {
        clearBtn.addEventListener('click', function () {
            document.getElementById('filter-name').value = '';
            document.getElementById('filter-position').value = '';
            document.getElementById('filter-store').value = '';
            document.getElementById('filter-dob').value = '';
            renderStaffTable();
        });
    }
}

window.addEventListener('DOMContentLoaded', () => {
    createFilterUI();
    setupFilterEvents();
    renderStaffTable();
});

async function fetchStore() {
    try {
        const ls_Store = document.getElementById("store-body");
        const res = await fetch("http://localhost:8080/api/admin/all_CH");
        if (!res.ok) throw new Error("Không load được cửa hàng");

        store = await res.json();
        console.log(store);
        create_fill_select_store_ui(store);
        create_Add_select_store_ui(store);
    } catch (error) {
        console.error("Lỗi load role:", error);
        alert("Lỗi: " + error.message);
    }
}
fetchStore();

function create_fill_select_store_ui(store) {
    const select = document.getElementById("filter-store");
    store.forEach(data => {
        const option = document.createElement("option");
        option.value = data.ten_cua_hang;
        option.text = data.ten_cua_hang;
        select.appendChild(option);
    })

}

function create_Add_select_store_ui(store) {
    const select = document.getElementById("storeSelect");
    store.forEach(data => {
        const option = document.createElement("option");
        option.value = data.store_id;
        option.text = data.ten_cua_hang;
        select.appendChild(option);
    })

}