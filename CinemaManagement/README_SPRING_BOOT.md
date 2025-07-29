# 🎬 Cinema Management System - Spring Boot Web Application

## 📋 Tổng quan

Đây là phiên bản web của hệ thống quản lý rạp chiếu phim sử dụng **Spring Boot** và **Thymeleaf**. Ứng dụng cung cấp giao diện web hiện đại để quản lý tất cả các chức năng của rạp chiếu phim.

## 🚀 Tính năng chính

### 🎯 Quản lý cốt lõi

- **Quản lý Khách hàng**: CRUD khách hàng, tìm kiếm, thống kê
- **Quản lý Phim**: Thêm/sửa/xóa phim, quản lý thông tin chi tiết
- **Quản lý Suất chiếu**: Tạo lịch chiếu, quản lý thời gian
- **Quản lý Vé**: Đặt vé, hủy vé, thống kê doanh thu
- **Quản lý Ghế**: Bố trí chỗ ngồi, trạng thái ghế
- **Quản lý Phòng chiếu**: Cấu hình phòng, sức chứa

### 🛠️ Hệ thống hỗ trợ

- **Quản lý Đồ ăn**: Menu, đặt hàng, thống kê
- **Quản lý Voucher**: Mã giảm giá, khuyến mãi
- **Quản lý Đánh giá**: Feedback khách hàng, rating
- **Quản lý Người dùng**: Phân quyền, tài khoản

### 🎨 Giao diện người dùng

- **Responsive Design**: Tương thích mọi thiết bị
- **Modern UI**: Bootstrap 5, Font Awesome icons
- **Interactive**: JavaScript animations, AJAX
- **User-friendly**: Intuitive navigation, search, filter

## 🛠️ Công nghệ sử dụng

### Backend

- **Spring Boot 3.2.0**: Framework chính
- **Spring MVC**: Web framework
- **Thymeleaf**: Template engine
- **Spring Validation**: Form validation
- **Maven**: Build tool

### Frontend

- **Bootstrap 5.3.2**: CSS framework
- **jQuery 3.7.1**: JavaScript library
- **Font Awesome 6.4.2**: Icons
- **Custom CSS/JS**: Styling và interactions

## 📁 Cấu trúc project

```
CinemaManagement/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── cinema/
│   │   │           ├── CinemaManagementApplication.java
│   │   │           └── controller/
│   │   │               ├── HomeController.java
│   │   │               ├── KhachHangWebController.java
│   │   │               └── ... (các controller khác)
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── templates/
│   │       │   ├── layout.html
│   │       │   ├── home.html
│   │       │   ├── dashboard.html
│   │       │   └── ... (các template khác)
│   │       └── static/
│   │           ├── css/
│   │           │   └── style.css
│   │           └── js/
│   │               └── script.js
│   └── test/
├── pom.xml
└── README_SPRING_BOOT.md
```

## 🚀 Hướng dẫn cài đặt và chạy

### Yêu cầu hệ thống

- **Java 17** hoặc cao hơn
- **Maven 3.6** hoặc cao hơn
- **Web browser** hiện đại

### Bước 1: Cài đặt dependencies

```bash
# Di chuyển vào thư mục project
cd CinemaManagement

# Cài đặt dependencies Maven
mvn clean install
```

### Bước 2: Chạy ứng dụng

```bash
# Chạy Spring Boot application
mvn spring-boot:run
```

### Bước 3: Truy cập ứng dụng

- Mở trình duyệt web
- Truy cập: `http://localhost:8080`
- Trang chủ sẽ hiển thị giao diện chính

## 🎯 Các trang chính

### 🏠 Trang chủ (`/`)

- Giới thiệu hệ thống
- Navigation đến các chức năng
- Quick access buttons

### 📊 Dashboard (`/dashboard`)

- Thống kê tổng quan
- Hoạt động gần đây
- Lịch chiếu hôm nay
- Trạng thái hệ thống

### 👥 Quản lý Khách hàng (`/khachhang`)

- Danh sách khách hàng
- Thêm/sửa/xóa khách hàng
- Tìm kiếm theo CCCD, tên
- Thống kê khách hàng

### 🎬 Quản lý Phim (`/phim`)

- Danh sách phim
- Thêm phim mới
- Cập nhật thông tin phim
- Quản lý poster, trailer

### 🎫 Quản lý Suất chiếu (`/suatchieu`)

- Lịch chiếu
- Tạo suất chiếu mới
- Quản lý thời gian
- Liên kết phim - phòng

### 🎟️ Đặt vé (`/datve`)

- Giao diện đặt vé
- Chọn phim, suất chiếu
- Chọn ghế
- Thanh toán

## 🎨 Tính năng giao diện

### ✨ Animations & Effects

- **Fade-in animations**: Cards, tables
- **Hover effects**: Buttons, cards
- **Smooth transitions**: Navigation, forms
- **Loading spinners**: Form submissions

### 🔍 Interactive Features

- **Search & Filter**: Real-time search
- **Sortable tables**: Click to sort columns
- **Pagination**: Large data sets
- **Modal dialogs**: Quick actions

### 📱 Responsive Design

- **Mobile-first**: Tối ưu cho mobile
- **Tablet support**: Responsive breakpoints
- **Desktop optimized**: Full features

### 🎯 User Experience

- **Intuitive navigation**: Clear menu structure
- **Breadcrumbs**: Easy navigation
- **Flash messages**: Success/error feedback
- **Form validation**: Real-time validation

## 🔧 Cấu hình

### Application Properties

```properties
# Server Configuration
server.port=8080
server.servlet.context-path=/

# Thymeleaf Configuration
spring.thymeleaf.cache=false
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html

# Static Resources
spring.web.resources.static-locations=classpath:/static/
```

### Customization

- **Colors**: Chỉnh sửa CSS variables
- **Layout**: Modify templates
- **Functionality**: Extend controllers
- **Styling**: Custom CSS classes

## 🧪 Testing

### Unit Tests

```bash
# Chạy unit tests
mvn test
```

### Integration Tests

```bash
# Chạy integration tests
mvn verify
```

### Manual Testing

- Test tất cả CRUD operations
- Verify form validation
- Check responsive design
- Test browser compatibility

## 🚀 Deployment

### Development

```bash
mvn spring-boot:run
```

### Production

```bash
# Build JAR file
mvn clean package

# Run JAR file
java -jar target/cinema-management-1.0.0.jar
```

### Docker (Optional)

```dockerfile
FROM openjdk:17-jdk-slim
COPY target/cinema-management-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

## 📊 Performance

### Optimization

- **Static resource caching**
- **Minified CSS/JS**
- **Image optimization**
- **Database query optimization**

### Monitoring

- **Application metrics**
- **Response time tracking**
- **Error logging**
- **User activity analytics**

## 🔒 Security

### Features

- **Input validation**
- **XSS protection**
- **CSRF protection**
- **SQL injection prevention**

### Best Practices

- **Secure headers**
- **HTTPS enforcement**
- **Session management**
- **Access control**

## 🤝 Contributing

### Development Workflow

1. Fork repository
2. Create feature branch
3. Make changes
4. Add tests
5. Submit pull request

### Code Standards

- **Java coding conventions**
- **HTML/CSS best practices**
- **JavaScript ES6+**
- **Documentation required**

## 📞 Support

### Issues

- Report bugs via GitHub Issues
- Include detailed descriptions
- Provide reproduction steps

### Documentation

- API documentation
- User guides
- Developer guides
- FAQ section

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

---

**🎬 Cinema Management System** - Modern web application for cinema management with Spring Boot and Thymeleaf.
