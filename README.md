# Coffee Spring Web Application

## Giới thiệu

Coffee Spring Web là một ứng dụng web được xây dựng bằng Spring Boot, phục vụ cho việc quản lý hoạt động kinh doanh của quán cà phê.

## Công nghệ sử dụng

- Java 17
- Maven
- Spring Boot
- Spring MVC
- Spring Data JPA
- Spring Security
- Java Email
- Dịch vụ Cloudinary

## Cài đặt và Chạy ứng dụng

### Yêu cầu hệ thống

- JDK 17 trở lên
- Maven 3.6.x trở lên

### Các bước cài đặt

1. Clone repository về máy:

```bash
git clone https://github.com/nghiakieran/coffee-spring-web.git
```

2. Di chuyển vào thư mục dự án:

```bash
cd coffee-spring-web
```

3. Configure database:
   spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/coffeeshop1
   spring.datasource.username=username
   spring.datasource.password=password

4. Chạy ứng dụng bằng Maven:

```bash
mvn clean install
mvn spring-boot:run
```

Ứng dụng sẽ chạy tại địa chỉ: `http://localhost:8080`

## Cấu trúc dự án

```
coffee-spring-web/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/nghia/coffee_spring_web/
│   │   ├── resources/
│   │   └── webapp/
│   └── test/
├── pom.xml
└── README.md
```

## Chức năng chính

### Khách (Guest)

- Đăng ký tài khoản mới
  - Điền thông tin cá nhân
  - Xác thực email
- Đăng nhập vào hệ thống
- Xem danh sách và chi tiết sản phẩm
  - Tìm kiếm sản phẩm theo tên
  - Xem giá và thông tin chi tiết

### Người dùng (User)

- Quản lý tài khoản
  - Đăng nhập/Đăng xuất
- Mua sắm trực tuyến
  - Xem sản phẩm
  - Xem lịch sử mua hàng
  - Thêm sản phẩm vào giỏ hàng
  - Chỉnh sửa số lượng sản phẩm
  - Thanh toán đơn hàng

### Quản trị viên (Admin)

- Quản lý sản phẩm
  - Thêm/sửa/xóa sản phẩm
  - Quản lý danh mục
- Quản lý đơn hàng
  - Xem tất cả đơn hàng
  - Thêm/sửa/xóa đơn hàng
- Quản lý người dùng
  -Thêm/sửa/xóa người dùng
  - Xem danh sách khách hàng
  - Phân quyền người dùng

## Tác giả

- Chi Nghia
- Email: lechinghia202@gmail.com

- Quoc Nam
- Email: namle022004@gmail.com
