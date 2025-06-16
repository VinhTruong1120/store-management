  ⭐ ⭐ ⭐ ⭐ ⭐ ⭐⭐ ⭐ ⭐ ⭐ ⭐ ⭐⭐ ⭐ ⭐ ⭐ ⭐ ⭐CNPM Group ứng dụng quản lý chuỗi cửa hàng.  ⭐ ⭐ ⭐ ⭐ ⭐ ⭐⭐ ⭐ ⭐ ⭐ ⭐ ⭐⭐ ⭐ ⭐ ⭐ ⭐ ⭐
  ⭐ ⭐Cách dùng: 
Bước 1: Tắt hết các server SQL trên máy 
Bước 2: Tải Docker Desktop  mở Docker Desktop chọn Terminal chạy lần lượt 2 dòng lệnh sau: 
    1: docker pull mysql:8.0.34-debian
    2: docker run --name my_database -p 3306:3306 -e MYSQL_ROOT_PASSWORD=01269258179 -d mysql:8.0.34-debian
Bước 3: Tải Postman và mở xong rồi đăng nhập vào tài khoản cá nhân
Bước 4: Chạy Spring Boot file CnpmApplication.java
Bước 5: Tạo các request sau theo thứ tự:
  1: Tạo role: dùng phương thức HTTP Post với URL: http://localhost:8080/api/admin/Tao_role
  1.1: copy đoạn Json sau dán vào body {
                                            "role_id": "ADMIN",
                                            "ten_role": "ADMIN"
                                        }
  1.2: bấm send 

  2: tạo loại sản phẩm bằng phương thức HTTP Post với URL: http://localhost:8080/api/admin/them_loai_san_pham gồm 4 loại:
    Loại 1: {
                "ma_loai": "Banh_ngot",
                "ten_loai": "Bánh ngọt"
            }
    Loại 2: {
                "ma_loai": "Da_Xay",
                "ten_loai": "Đá xay"
            }
    Loại 3: 
          {
              "ma_loai": "Tra",
              "ten_loai": "Trà"
          }
  Loại 4: {
            "ma_loai": "Cafe",
            "ten_loai": "Cà Phê"
        }
Bước 6: Đăng nhập vào tài khoản admin mk: admin123 trong trình duyệt với đường dẫn: http://localhost:8080/
Bước 7: Enjoy our app
