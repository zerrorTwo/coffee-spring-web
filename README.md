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
coffee-spring-web/
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── com/nghia/coffee_spring_web/
│ │ └── resources/
| | └── webapp/
│ └── test/
├── pom.xml
└── README.md

## Giấy phép
[MIT License](LICENSE)

## Tác giả
- Chi Nghia, Quoc Nam
- Email: lechinghia202@gmail.com
