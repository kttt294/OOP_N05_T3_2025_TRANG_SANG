# BÁO CÁO BÀI TẬP LỚN MÔN LẬP TRÌNH HƯỚNG ĐỐI TƯỢNG

## ỨNG DỤNG QUẢN LÝ RẠP PHIM (CINEMA MANAGEMENT SYSTEM)

---

## LỜI NÓI ĐẦU

### 1. Bối cảnh và lý do chọn đề tài

Trong thời đại công nghệ số hiện nay, việc quản lý rạp phim một cách hiệu quả và chuyên nghiệp là nhu cầu thiết yếu của các doanh nghiệp trong lĩnh vực giải trí. Hệ thống quản lý rạp phim truyền thống với các phương pháp thủ công đã không còn phù hợp với quy mô và tốc độ phát triển của ngành công nghiệp điện ảnh hiện đại.

Đề tài "Ứng dụng quản lý rạp phim" được chọn với mục đích xây dựng một hệ thống quản lý toàn diện, tích hợp các chức năng cần thiết cho việc vận hành một rạp phim hiện đại, từ quản lý phim, khách hàng, đặt vé, đến quản lý ghế ngồi và các dịch vụ bổ sung.

### 2. Mục tiêu xây dựng đề tài

- **Mục tiêu chính**: Xây dựng hệ thống quản lý rạp phim hoàn chỉnh với giao diện web thân thiện, sử dụng kiến trúc MVC và các nguyên tắc lập trình hướng đối tượng.

- **Mục tiêu cụ thể**:
  - Thiết kế và triển khai các đối tượng chính: Phim, Khách hàng, Suất chiếu, Vé, Ghế, Voucher, Đánh giá
  - Xây dựng các chức năng CRUD đầy đủ cho từng đối tượng
  - Tích hợp cơ sở dữ liệu MySQL Cloud để lưu trữ dữ liệu
  - Phát triển giao diện người dùng responsive với Bootstrap
  - Triển khai unit testing cho các chức năng chính
  - Áp dụng các nguyên tắc OOP: Encapsulation, Inheritance, Polymorphism

### 3. Ý nghĩa học thuật và thực tiễn của đề tài

Đề tài có ý nghĩa học thuật khi giúp người thực hiện áp dụng và củng cố kiến thức về lập trình hướng đối tượng, thực hành thiết kế hệ thống với kiến trúc MVC, rèn luyện kỹ năng lập trình Java và Spring Boot, đồng thời hiểu sâu hơn về quản lý cơ sở dữ liệu và phát triển web. Quá trình xây dựng ứng dụng cũng tạo cơ hội để vận dụng đồng thời nhiều kiến thức đã học vào một sản phẩm hoàn chỉnh, từ việc tổ chức mã nguồn hợp lý, kết nối cơ sở dữ liệu, xử lý yêu cầu đến triển khai giao diện và đảm bảo tính tương tác của hệ thống.

Về ý nghĩa thực tiễn, ứng dụng là một giải pháp quản lý rạp phim hiện đại với đầy đủ chức năng vận hành như quản lý phim, khách hàng, đặt vé và dịch vụ bổ sung, triển khai trên nền tảng web responsive sử dụng Spring Boot, kết nối cơ sở dữ liệu MySQL Cloud để đảm bảo khả năng mở rộng và tính sẵn sàng cao. Hệ thống tối ưu hóa quy trình đặt vé nhờ tự động kiểm tra ghế trống, tính giá vé và cập nhật trạng thái ghế theo thời gian thực; quản lý tập trung thông tin khách hàng cùng lịch sử đặt vé; tích hợp chức năng voucher và bán đồ ăn để tăng doanh thu. Ngoài ra, hệ thống còn tự động hóa quản lý suất chiếu, cung cấp báo cáo thống kê doanh thu, phim được yêu thích, hành vi khách hàng, từ đó giúp ban quản lý ra quyết định chính xác và giảm thiểu lỗi vận hành nhờ kiểm tra, xác thực dữ liệu tự động.

## CHƯƠNG 1: TỔNG QUAN ĐỀ TÀI

### 1. Mô tả yêu cầu bài toán

1.1. Yêu cầu chức năng

Quản lý phim (Phim):
Thêm, sửa, xóa, tìm kiếm phim theo tên/thể loại
Thống kê phim và validation dữ liệu (thời lượng > 0, giới hạn tuổi ≥ 0)
Quản lý khách hàng (KhachHang):
Đăng ký, cập nhật thông tin cá nhân và tài khoản đăng nhập
Quản lý lịch sử đặt vé, tìm kiếm và thống kê khách hàng

Quản lý lịch chiếu (SuatChieu):
Tạo và quản lý lịch trình chiếu phim
Liên kết với phim, phòng chiếu và theo dõi ghế trống

Quản lý phòng chiếu (PhongChieu):
Cấu hình phòng với số hàng/cột ghế
Hiển thị sơ đồ ghế và liên kết suất chiếu

Quản lý ghế ngồi (Ghe):
Quản lý vị trí ghế (hàng, cột) và trạng thái (trống/đã đặt/đã thanh toán)
Cập nhật real-time khi có giao dịch

Hệ thống đặt vé (Ve, DatVe):
Quy trình đặt vé: chọn phim → suất chiếu → ghế ngồi
Quản lý trạng thái vé và lịch sử đặt vé

Quản lý voucher (Voucher):
Tạo mã giảm giá với phần trăm giảm và thời hạn hiệu lực
Kiểm tra số lượng còn lại và trạng thái hoạt động

Quản lý đồ ăn (DoAn):
Quản lý menu, giá cả và tồn kho
Tìm kiếm theo tên món và thống kê doanh thu

Quản lý hóa đơn (HoaDon):
Tạo hóa đơn mua đồ ăn với phương thức thanh toán (tiền mặt/chuyển khoản)
Liên kết với khách hàng và ghi nhận thời gian giao dịch

Hệ thống đánh giá (DanhGia):
Rating phim từ 0-5 sao và viết review
Tính điểm trung bình và thống kê feedback

Quản lý cơ sở dữ liệu:
Kết nối MySQL Cloud (Aiven) với connection pooling
CRUD operations và error handling

Giao diện web:
Responsive design với Bootstrap và Thymeleaf
Navigation rõ ràng và form validation

1.2. Yêu cầu phi chức năng

- Bảo mật thông tin khách hàng.
- Giao diện web responsive, thân thiện người dùng, tương thích đa trình duyệt.
- Hiệu suất cao, thời gian phản hồi nhanh, xử lý đồng thời nhiều người dùng.
- Hệ thống có khả năng mở rộng và bảo trì
- Tích hợp cơ sở dữ liệu cloud

2. Sơ đồ chức năng và luồng xử lý chính

**Sơ đồ chức năng tổng quan**:

┌─────────────────────────────────────────────────────────────────────────────────┐
│                              CINEMA MANAGEMENT SYSTEM                           │
└─────────────────────────────────────────────────────────────────────────────────┘
                                    │
                    ┌───────────────┼───────────────┐
                    │               │               │
        ┌───────────▼──────────┐    │    ┌──────────▼────────── ┐
        │    QUẢN LÝ NỘI DUNG  │    │    │  QUẢN LÝ KINH DOANH  │
        │                      │    │    │                      │
        │ ┌─────────────────┐  │    │    │ ┌─────────────────┐  │
        │ │   Quản lý Phim  │  │    │    │ │   Quản lý Vé    │  │
        │ │                 │  │    │    │ │                 │  │
        │ │ • Thêm/Sửa/Xóa  │  │    │    │ │ • Đặt vé        │  │
        │ │ • Tìm kiếm      │  │    │    │ │ • Thanh toán    │  │
        │ │ • Thống kê      │  │    │    │ │ • Hủy vé        │  │
        │ │ • Validation    │  │    │    │ │ • Trạng thái    │  │
        │ └─────────────────┘  │    │    │ └─────────────────┘  │
        │                      │    │    │                      │
        │ ┌─────────────────┐  │    │    │ ┌─────────────────┐  │
        │ │ Quản lý Suất    │  │    │    │ │ Quản lý Hóa     │  │
        │ │    Chiếu        │  │    │    │ │     Đơn         │  │
        │ │                 │  │    │    │ │                 │  │
        │ │ • Lịch chiếu    │  │    │    │ │ • Tạo hóa đơn   │  │
        │ │ • Thời gian     │  │    │    │ │ • Tính tiền     │  │
        │ │ • Kiểm tra xung │  │    │    │ │ • Báo cáo       │  │
        │ │   đột           │  │    │    │ │ • Thống kê      │  │
        │ └─────────────────┘  │    │    │ └─────────────────┘  │
        └──────────────────────┘    │    └──────────────────────┘
                                    │
                    ┌───────────────┼───────────────┐
                    │               │               │
        ┌───────────▼──────────┐    │    ┌──────────▼───────── ─┐
        │   QUẢN LÝ CƠ SỞ      │    │    │   QUẢN LÝ KHÁCH      │
        │     VẬT CHẤT         │    │    │      HÀNG            │
        │                      │    │    │                      │
        │ ┌─────────────────┐  │    │    │ ┌─────────────────┐  │
        │ │ Quản lý Phòng   │  │    │    │ │ Quản lý Khách   │  │
        │ │    Chiếu        │  │    │    │ │     Hàng        │  │
        │ │                 │  │    │    │ │                 │  │
        │ │ • Cấu trúc phòng│  │    │    │ │ • Thông tin KH  │  │
        │ │ • Sơ đồ ghế     │  │    │    │ │ • Lịch sử vé    │  │
        │ │ • Trạng thái    │  │    │    │ │ • Thống kê      │  │
        │ └─────────────────┘  │    │    │ └─────────────────┘  │
        │                      │    │    │                      │
        │ ┌─────────────────┐  │    │    │ ┌─────────────────┐  │
        │ │   Quản lý Ghế   │  │    │    │ │   Quản lý Đánh  │  │
        │ │                 │  │    │    │ │      Giá        │  │
        │ │ • Vị trí ghế    │  │    │    │ │                 │  │
        │ │ • Trạng thái    │  │    │    │ │ • Rating phim   │  │
        │ • Đặt chỗ         │  │    │    │ │ • Feedback      │  │
        │ └─────────────────┘  │    │    │ │ • Thống kê      │  │
        └──────────────────────┘    │    │ └─────────────────┘  │
                                    │    └──────────────────────┘
                    ┌───────────────┼───────────────┐
                    │               │               │
        ┌───────────▼──────────┐    │    ┌──────────▼───────── ─┐
        │   QUẢN LÝ DỊCH VỤ    │    │    │   QUẢN LÝ HỖ TRỢ     │
        │     BỔ SUNG          │    │    │                      │
        │                      │    │    │ ┌─────────────────┐  │
        │ ┌─────────────────┐  │    │    │ │  Quản lý Voucher│  │
        │ │   Quản lý Đồ    │  │    │    │ │                 │  │
        │ │      Ăn         │  │    │    │ │ • Mã giảm giá   │  │
        │ │                 │  │    │    │ │ • Khuyến mãi    │  │
        │ │ • Menu          │  │    │    │ │ • Thời hạn      │  │
        │ │ • Giá cả        │  │    │    │ │ • Số lượng      │  │
        │ │ • Tồn kho       │  │    │    │ └─────────────────┘  │
        │ └─────────────────┘  │    │    │                      │
        └──────────────────────┘    │    │ ┌─────────────────┐  │
                                    │    │ │   Báo cáo &     │  │
                                    │    │ │   Thống kê      │  │
                                    │    │ │                 │  │
                                    │    │ │ • Doanh thu     │  │
                                    │    │ │ • Phim yêu thích│  │
                                    │    │ │ • Hành vi KH    │  │
                                    │    │ │ • Hiệu suất     │  │
                                    │    │ └─────────────────┘  │
                                    │    └──────────────────────┘
                                    │
                    ┌───────────────┼───────────────┐
                    │               │               │
        ┌───────────▼──────────┐    │    ┌──────────▼───────── ─┐
        │   GIAO DIỆN NGƯỜI    │    │    │   CƠ SỞ DỮ LIỆU      │
        │       DÙNG           │    │    │                      │
        │                      │    │    │ ┌─────────────────┐  │
        │ ┌─────────────────┐  │    │    │ │   MySQL Cloud   │  │
        │ │   Web Interface │  │    │    │ │    (Aiven)      │  │
        │ │                 │  │    │    │ │                 │  │
        │ │ • Responsive    │  │    │    │ │ • Bảo mật SSL   │  │
        │ │ • Bootstrap     │  │    │    │ │ • Connection    │  │
        │ │ • Thymeleaf     │  │    │    │ │   Pooling       │  │
        │ │ • Navigation    │  │    │    │ │ • Backup tự động│  │
        │ └─────────────────┘  │    │    │ └─────────────────┘  │
        │                      │    │    │                      │
        │ ┌─────────────────┐  │    │    │ ┌─────────────────┐  │
        │ │   Mobile Ready  │  │    │    │ │   Data Access   │  │
        │ │                 │  │    │    │ │     Layer       │  │
        │ │ • Cross-browser │  │    │    │ │                 │  │
        │ │ • Touch-friendly│  │    │    │ │ • CRUD operations│  │
        │ │ • Fast loading  │  │    │    │ │ • Validation    │  │
        │ └─────────────────┘  │    │    │ │ • Error handling│  │
        └──────────────────────┘    │    │ └─────────────────┘  │
                                    │    └──────────────────────┘
                                    │
                    ┌───────────────┼───────────────┐
                    │               │               │
        ┌───────────▼──────────┐    │    ┌──────────▼───────── ─┐
        │   KIỂM THỬ &         │    │    │   BẢO MẬT &          │
        │   CHẤT LƯỢNG         │    │    │   HIỆU SUẤT          │
        │                      │    │    │                      │
        │ ┌─────────────────┐  │    │    │ ┌─────────────────┐  │
        │ │   Unit Testing  │  │    │    │ │   Validation    │  │
        │ │                 │  │    │    │ │                 │  │
        │ │ • Model Tests   │  │    │    │ │ • Input check   │  │
        │ │ • Controller    │  │    │    │ │ • Data integrity│  │
        │ │   Tests         │  │    │    │ │ • Business rules│  │
        │ │ • Integration   │  │    │    │ └─────────────────┘  │
        │ │   Tests         │  │    │    │                      │
        │ └─────────────────┘  │    │    │ ┌─────────────────┐  │
        │                      │    │    │ │   Performance   │  │
        │ ┌─────────────────┐  │    │    │ │                 │  │
        │ │   Error         │  │    │    │ │ • Fast response │  │
        │ │   Handling      │  │    │    │ │ • Connection    │  │
        │ │                 │  │    │    │ │   pooling       │  │
        │ │ • Exception     │  │    │    │ │ • Caching       │  │
        │ │ • User feedback │  │    │    │ └─────────────────┘  │
        │ └─────────────────┘  │    │    └──────────────────────┘
        └──────────────────────┘    │
                                    │
                    ┌───────────────┼───────────────┐
                    │               │               │
        ┌───────────▼──────────┐    │    ┌──────────▼───────── ─┐
        │   TRIỂN KHAI &       │    │    │   MỞ RỘNG &          │
        │   QUẢN LÝ            │    │    │   PHÁT TRIỂN         │
        │                      │    │    │                      │
        │ ┌─────────────────┐  │    │    │ ┌─────────────────┐  │
        │ │   Version       │  │    │    │ │   Microservices │  │
        │ │   Control       │  │    │    │ │                 │  │
        │ │                 │  │    │    │ │ • API Gateway   │  │
        │ │ • Git/GitHub    │  │    │    │ │ • Service Mesh  │  │
        │ │ • Branching     │  │    │    │ │ • Load Balancing│  │
        │ │ • Deployment    │  │    │    │ └─────────────────┘  │
        │ └─────────────────┘  │    │    │                      │
        │                      │    │    │ ┌─────────────────┐  │
        │ ┌─────────────────┐  │    │    │ │   AI & ML       │  │
        │ │   CI/CD Pipeline│  │    │    │ │                 │  │
        │ │                 │  │    │    │ │ • Recommendation│  │
        │ │ • Automated     │  │    │    │ │ • Analytics     │  │
        │ │   Testing       │  │    │    │ │ • Prediction    │  │
        │ │ • Build & Deploy│  │    │    │ └─────────────────┘  │
        │ └─────────────────┘  │    │    └──────────────────────┘
        └──────────────────────┘    │
                                    │
                    ┌───────────────┼───────────────┐
                    │               │               │
        ┌───────────▼──────────┐    │    ┌──────────▼───────── ─┐
        │   KIẾN TRÚC MVC      │    │    │   SPRING BOOT        │
        │                      │    │    │   FRAMEWORK          │
        │                      │    │    │                      │
        │ ┌─────────────────┐  │    │    │ ┌─────────────────┐  │
        │ │   Model         │  │    │    │ │   Spring Boot   │  │
        │ │                 │  │    │    │ │   3.3.0         │  │
        │ │ • Business Logic│  │    │    │ │                 │  │
        │ │ • Data Models   │  │    │    │ │ • Java 17       │  │
        │ │ • Validation    │  │    │    │ │ • Maven/Gradle  │  │
        │ └─────────────────┘  │    │    │ │ • Auto-config   │  │
        │                      │    │    │ └─────────────────┘  │
        │ ┌─────────────────┐  │    │    │                      │
        │ │   View          │  │    │    │ ┌─────────────────┐  │
        │ │                 │  │    │    │ │   Database      │  │
        │ │ • Templates     │  │    │    │ │   Connection    │  │
        │ │ • UI Components │  │    │    │ │                 │  │
        │ │ • Responsive    │  │    │    │ │ • JDBC Driver   │  │
        │ └─────────────────┘  │    │    │ │ • Connection    │  │
        │                      │    │    │ │   Pooling       │  │
        │ ┌─────────────────┐  │    │    │ └─────────────────┘  │
        │ │   Controller    │  │    │    └──────────────────────┘
        │ │                 │  │    │
        │ │ • Request       │  │    │
        │ │   Handling      │  │    │
        │ │ • Business      │  │    │
        │ │   Logic         │  │    │
        │ │ • Response      │  │    │
        │ └─────────────────┘  │    │
        └──────────────────────┘    │
                                    │
                    ┌───────────────┼───────────────┐
                    │               │               │
        ┌───────────▼──────────┐    │    ┌──────────▼────────── ┐
        │   LUỒNG XỬ LÝ        │    │    │   TÍCH HỢP &         │
        │   CHÍNH              │    │    │   LIÊN KẾT           │
        │                      │    │    │                      │
        │ ┌─────────────────┐  │    │    │ ┌─────────────────┐  │
        │ │   Đặt Vé        │  │    │    │ │   Payment       │  │
        │ │                 │  │    │    │ │   Gateway       │  │
        │ │ 1. Chọn phim    │  │    │    │ │                 │  │
        │ │ 2. Chọn suất    │  │    │    │ │ • Online        │  │
        │ │    chiếu        │  │    │    │ │ • Banking       │  │
        │ │ 3. Chọn ghế     │  │    │    │ │ • E-wallet      │  │
        │ │ 4. Thanh toán   │  │    │    │ └─────────────────┘  │
        │ └─────────────────┘  │    │    │                      │
        │                      │    │    │ ┌─────────────────┐  │
        │ ┌─────────────────┐  │    │    │ │   Email/SMS     │  │
        │ │   Quản lý       │  │    │    │ │   Service       │  │
        │ │   Phim          │  │    │    │ │                 │  │
        │ │                 │  │    │    │ │ • Confirmation  │  │
        │ │ 1. Thêm phim    │  │    │    │ │ • Reminder      │  │
        │ │ 2. Cập nhật     │  │    │    │ │ • Newsletter    │  │
        │ │ 3. Xóa phim     │  │    │    │ └─────────────────┘  │
        │ └─────────────────┘  │    │    └──────────────────────┘
        └──────────────────────┘    │
                                    │
                    ┌───────────────┼───────────────┐
                    │               │               │
        ┌───────────▼──────────┐    │    ┌──────────▼────────── ┐
        │   BÁO CÁO &          │    │    │   QUẢN TRỊ &         │
        │   THỐNG KÊ           │    │    │   GIÁM SÁT           │
        │                      │    │    │                      │
        │ ┌─────────────────┐  │    │    │ ┌─────────────────┐  │
        │ │   Business      │  │    │    │ │   Admin         │  │
        │ │   Intelligence  │  │    │    │ │   Dashboard     │  │
        │ │                 │  │    │    │ │                 │  │
        │ │ • Doanh thu     │  │    │    │ │ • User          │  │
        │ │ • Phim hot      │  │    │    │ │   Management    │  │
        │ │ • Khách hàng    │  │    │    │ │ • System        │  │
        │ │ • Xu hướng      │  │    │    │ │   Monitoring    │  │
        │ └─────────────────┘  │    │    │ │ • Performance   │  │
        │                      │    │    │ └─────────────────┘  │
        │ ┌─────────────────┐  │    │    │                      │
        │ │   Analytics     │  │    │    │ ┌─────────────────┐  │
        │ │                 │  │    │    │ │   Logging &     │  │
        │ │ • Real-time     │  │    │    │ │   Monitoring    │  │
        │ │ • Historical    │  │    │    │ │                 │  │
        │ │ • Predictive    │  │    │    │ │ • Error logs    │  │
        │ │ • Custom        │  │    │    │ │ • Performance   │  │
        │ └─────────────────┘  │    │    │ │ • Alerts        │  │
        └──────────────────────┘    │    │ └─────────────────┘  │
                                    │    └──────────────────────┘
                                    │
                    ┌───────────────┼───────────────┐
                    │               │               │
        ┌───────────▼──────────┐    │    ┌──────────▼────────── ┐
        │   MOBILE &           │    │    │   CLOUD &            │
        │   MULTI-PLATFORM     │    │    │   SCALABILITY        │
        │                      │    │    │                      │
        │ ┌─────────────────┐  │    │    │ ┌─────────────────┐  │
        │ │   Mobile App    │  │    │    │ │   Cloud         │  │
        │ │                 │  │    │    │ │   Deployment    │  │
        │ │ • iOS/Android   │  │    │    │ │                 │  │
        │ │ • Push          │  │    │    │ │ • AWS/Azure     │  │
        │ │   Notifications │  │    │    │ │ • Load          │  │
        │ │ • Offline       │  │    │    │ │   Balancing     │  │
        │ │   Support       │  │    │    │ │ • Auto-scaling  │  │
        │ └─────────────────┘  │    │    │ └─────────────────┘  │
        │                      │    │    │                      │
        │ ┌─────────────────┐  │    │    │ ┌─────────────────┐  │
        │ │   API           │  │    │    │ │   Container     │  │
        │ │   Integration   │  │    │    │ │   Technology    │  │
        │ │                 │  │    │    │ │                 │  │
        │ │ • REST API      │  │    │    │ │ • Docker        │  │
        │ │ • GraphQL       │  │    │    │ │ • Kubernetes    │  │
        │ │ • WebSocket     │  │    │    │ │ • Microservices │  │
        │ └─────────────────┘  │    │    │ └─────────────────┘  │
        └──────────────────────┘    │    └──────────────────────┘
                                    │
                    ┌───────────────┼───────────────┐
                    │               │               │
        ┌───────────▼──────────┐    │    ┌──────────▼────────── ┐
        │   BẢO MẬT &          │    │    │   BACKUP &           │
        │   TUÂN THỦ           │    │    │   DISASTER           │
        │                      │    │    │   RECOVERY           │
        │ ┌─────────────────┐  │    │    │ ┌─────────────────┐  │
        │ │   Authentication│  │    │    │ │   Data          │  │
        │ │   &             │  │    │    │ │   Backup        │  │
        │ │   Authorization │  │    │    │ │                 │  │
        │ │                 │  │    │    │ │ • Automated     │  │
        │ │ • User roles    │  │    │    │ │ • Incremental   │  │
        │ │ • Access        │  │    │    │ │ • Point-in-time │  │
        │ │   control       │  │    │    │ │ • Geo-redundant │  │
        │ └─────────────────┘  │    │    │ └─────────────────┘  │
        │                      │    │    │                      │
        │ ┌─────────────────┐  │    │    │ ┌─────────────────┐  │
        │ │   Data          │  │    │    │ │   Business      │  │
        │ │   Protection    │  │    │    │ │   Continuity    │  │
        │ │                 │  │    │    │ │                 │  │
        │ │ • Encryption    │  │    │    │ │ • RTO/RPO       │  │
        │ │ • GDPR          │  │    │    │ │ • Failover      │  │
        │ │ • Compliance    │  │    │    │ │ • Recovery      │  │
        │ └─────────────────┘  │    │    │ └─────────────────┘  │
        └──────────────────────┘    │    └──────────────────────┘
```

## Luồng xử lý chính:

1. Tạo Phim → 2. Tạo Phòng Chiếu → 3. Tạo Ghế → 4. Tạo Suất Chiếu → 5. Đặt Vé → 6. Quản lý Khách Hàng → 7. Quản lý Hóa Đơn

### 1. Luồng đặt vé (Core Business Flow)

- Khách hàng chọn phim và suất chiếu
- Hệ thống hiển thị danh sách ghế trống qua `danhSachGheTrong`
- Khách hàng chọn ghế và nhập thông tin (CCCD, maSuatChieu, maGhe, giaVe)
- Hệ thống validation qua `taoVe()` với kiểm tra `giaVe > 0` và các trường bắt buộc
- Cập nhật trạng thái ghế từ `TRONG` sang `DA_DAT` và tạo vé với trạng thái `CHUA_THANH_TOAN`

### 2. Luồng quản lý phim

- Nhập thông tin phim mới qua `Create(Phim phim)`
- Validation dữ liệu qua setter methods: `thoiLuong > 0`, `gioiHanTuoi >= 0`
- Lưu vào database qua `phimAiven.createPhim()` và cập nhật danh sách

### 3. Luồng quản lý suất chiếu

- Chọn phim và phòng chiếu qua `taoSuatChieu(SuatChieu suatChieu)`
- Đặt thời gian chiếu với validation `thoiGianBatDau.isAfter(thoiGianKetThuc)`
- Tự động tính thời gian kết thúc qua `tinhThoiGianKetThuc()` và lưu suất chiếu

### 4. Luồng quản lý khách hàng

- Đăng ký thông tin cá nhân qua `Create(KhachHang khachHang)`
- Validation CCCD và thông tin bắt buộc qua kiểm tra `CCCD.trim().isEmpty()` và `ten.trim().isEmpty()`
- Tạo tài khoản và lưu vào hệ thống qua `khachHangAiven.createKhachHang()`

### 5. Luồng xử lý thanh toán

- Tính toán giá vé với validation `giaVe >= 0` qua setter method
- Xử lý thanh toán và cập nhật trạng thái vé từ `CHUA_THANH_TOAN` sang `DA_THANH_TOAN`
- Cập nhật trạng thái ghế và gửi xác nhận qua `capNhatVe()`

### 6. Luồng quản lý hóa đơn

- Tạo hóa đơn tự động khi thanh toán vé qua `taoHoaDon(HoaDon hoaDon)`
- Validation thông tin hóa đơn: `maHoaDon != null`, `tongTien > 0`, `phuongThucThanhToan != null`
- Tính toán tổng tiền bao gồm giá vé, đồ ăn và áp dụng voucher qua `tinhTongTien()`
- Lưu hóa đơn vào database qua `HoaDonAiven.createHoaDon()` và cập nhật trạng thái thanh toán
- Xuất hóa đơn với thông tin chi tiết: mã hóa đơn, thông tin khách hàng, danh sách vé, tổng tiền và phương thức thanh toán

### Lý do các luồng này là cốt lõi:

- **Đặt vé**: Là chức năng chính của rạp phim, xử lý toàn bộ quy trình từ chọn phim đến xác nhận đặt vé
- **Quản lý phim**: Cần thiết để có nội dung chiếu, validation đảm bảo chất lượng dữ liệu
- **Quản lý suất chiếu**: Cần thiết để lên lịch chiếu, tránh xung đột và tối ưu hóa phòng chiếu
- **Quản lý khách hàng**: Cần thiết để phục vụ khách hàng, lưu trữ thông tin và lịch sử đặt vé
- **Thanh toán**: Cần thiết để hoàn tất giao dịch và cập nhật trạng thái hệ thống
- **Quản lý hóa đơn**: Cần thiết để lưu trữ lịch sử giao dịch, báo cáo tài chính và tuân thủ quy định kế toán

---

## CHƯƠNG 2: PHÂN TÍCH & THIẾT KẾ HỆ THỐNG

### 1. Khung ứng dụng và mô hình thiết kế (MVC + Test)

**Kiến trúc MVC (Model-View-Controller)**:

```
┌─────────────────────────────────────────────────────────────┐
│                        PRESENTATION LAYER                   │
├─────────────────────────────────────────────────────────────┤
│  Controllers:                                               │
│  - Quản lý phim và lịch chiếu (PhimController, SuatChieuController) │
│  - Quản lý khách hàng và đặt vé (KhachHangController, VeController) │
│  - Quản lý phòng chiếu và ghế (PhongChieuController, GheController) │
│  - Quản lý dịch vụ (DoAnController, VoucherController)    │
│  - Quản lý đánh giá (DanhGiaController)                   │
└─────────────────────────────────────────────────────────────┘
                              │
┌─────────────────────────────────────────────────────────────┐
│                         BUSINESS LAYER                      │
├─────────────────────────────────────────────────────────────┤
│  Models:                                                    │
│  - Quản lý phim và lịch chiếu (Phim.java, SuatChieu.java) │
│  - Quản lý khách hàng và đặt vé (KhachHang.java, Ve.java) │
│  - Quản lý phòng chiếu và ghế (Ghe.java)                  │
│  - Quản lý dịch vụ (DoAn.java, Voucher.java)              │
│  - Quản lý đánh giá và hóa đơn (DanhGia.java, HoaDon.java)│
└─────────────────────────────────────────────────────────────┘
                              │
┌─────────────────────────────────────────────────────────────┐
│                        DATA ACCESS LAYER                    │
├─────────────────────────────────────────────────────────────┤
│  Database Classes:                                          │
│  - Quản lý phim và lịch chiếu (phimAiven.java, suatChieuAiven.java) │
│  - Quản lý khách hàng và đặt vé (khachHangAiven.java, veAiven.java) │
│  - Quản lý phòng chiếu và ghế (gheAiven.java)              │
│  - Quản lý dịch vụ (doAnAiven.java, voucherAiven.java)    │
│  - Quản lý đánh giá (danhGiaAiven.java)                   │
│  - Kết nối database (myDBConnection.java)                  │
└─────────────────────────────────────────────────────────────┘
                              │
┌─────────────────────────────────────────────────────────────┐
│                        DATABASE LAYER                       │
├─────────────────────────────────────────────────────────────┤
│  MySQL Cloud Database (Aiven)                               │
│  - Bảng quản lý phim và lịch chiếu (phim, suatchieu)      │
│  - Bảng quản lý khách hàng và đặt vé (khachhang, ve)      │
│  - Bảng quản lý phòng chiếu và ghế (phongchieu, ghe)      │
│  - Bảng quản lý dịch vụ (doan, voucher)                   │
│  - Bảng quản lý đánh giá và hóa đơn (danhgia, hoadon)     │
└─────────────────────────────────────────────────────────────┘
```

**Mô hình Test**:

Hệ thống áp dụng mô hình kiểm thử đa tầng để đảm bảo chất lượng và độ tin cậy của ứng dụng. Phần code kiểm thử mô hình được đặt trong folder test\java\... Ở tầng thấp nhất, Unit Tests được triển khai cho từng Model class (testPhim.java, testKhachHang.java, testVe.java, testGhe.java, testSuatChieu.java, testPhongChieu.java, testDoAn.java, testVoucher.java, testDanhGia.java) để kiểm tra tính đúng đắn của các phương thức getter/setter, validation logic, business rules, CRUD operations, search functionality, và business logic phức tạp như thống kê, tính toán tổng tiền, và xử lý mối quan hệ giữa các đối tượng. Tầng giữa bao gồm Integration Tests cho Controllers (testPhimController.java, testKhachHangController.java, testVeController.java, testGheController.java, testSuatChieuController.java, testPhongChieuController.java, testDoAnController.java, testVoucherController.java, testDanhGiaController.java) để đảm bảo sự tương tác chính xác giữa các thành phần, kiểm tra luồng xử lý request từ giao diện đến business logic. Mô hình test này giúp phát hiện sớm các lỗi, đảm bảo tính ổn định của hệ thống và tạo cơ sở cho việc phát triển liên tục.

2. Các đối tượng (Objects)

**1. Phim (Movie)**

- **Thuộc tính**: Mã phim, tên phim, thể loại, thời lượng, ngôn ngữ, giới hạn tuổi, mô tả
- **Chức năng**: Quản lý thông tin phim, tìm kiếm, thống kê theo thể loại

**2. KhachHang (Customer)**

- **Thuộc tính**: CCCD, tên, tuổi, số điện thoại, email, giới tính, lịch sử đặt vé
- **Chức năng**: Quản lý thông tin cá nhân, lịch sử giao dịch, thống kê hành vi

**3. SuatChieu (Showtime)**

- **Thuộc tính**: Mã suất chiếu, mã phim, mã phòng, thời gian bắt đầu/kết thúc, danh sách ghế trống
- **Chức năng**: Quản lý lịch chiếu, tính toán thời gian, kiểm tra xung đột

**4. Ve (Ticket)**

- **Thuộc tính**: Mã vé, CCCD khách hàng, mã suất chiếu, mã ghế, giá vé, trạng thái
- **Chức năng**: Quản lý thông tin vé, trạng thái thanh toán, lịch sử giao dịch

**5. Ghe (Seat)**

- **Thuộc tính**: Mã ghế, hàng, cột, mã phòng, mã suất chiếu, trạng thái
- **Chức năng**: Quản lý vị trí ghế, trạng thái đặt chỗ, hiển thị sơ đồ ghế

**6. PhongChieu (Cinema Room)**

- **Thuộc tính**: Mã phòng, tên phòng, số hàng ghế, số cột ghế, loại phòng, trạng thái
- **Chức năng**: Quản lý cấu trúc phòng chiếu, sơ đồ ghế, trạng thái hoạt động

**7. DoAn (Food & Beverage)**

- **Thuộc tính**: Mã đồ ăn, tên đồ ăn, loại đồ ăn, giá, mô tả, trạng thái
- **Chức năng**: Quản lý menu đồ ăn, giá cả, đặt hàng dịch vụ

**8. Voucher (Discount Coupon)**

- **Thuộc tính**: Mã voucher, mô tả, phần trăm giảm giá, ngày bắt đầu, ngày kết thúc, số lượng còn lại, trạng thái
- **Chức năng**: Quản lý chương trình khuyến mãi, áp dụng giảm giá cho vé

**9. DanhGia (Review)**

- **Thuộc tính**: Mã đánh giá, mã phim, CCCD khách hàng, điểm đánh giá, nội dung, ngày đánh giá
- **Chức năng**: Quản lý đánh giá phim từ khách hàng, thống kê điểm số

**10. HoaDon (Invoice)**

- **Thuộc tính**: Mã hóa đơn, mã vé, ngày thanh toán, tổng tiền, phương thức thanh toán, trạng thái
- **Chức năng**: Quản lý giao dịch tài chính, lưu trữ lịch sử thanh toán

### 3. Biểu đồ UML Class Diagram

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│     Phim        │    │   KhachHang     │    │   SuatChieu     │
├─────────────────┤    ├─────────────────┤    ├─────────────────┤
│ - maPhim        │    │ - CCCD          │    │ - maSuatChieu   │
│ - tenPhim       │    │ - ten           │    │ - maPhim        │
│ - theLoai       │    │ - tuoi          │    │ - maPhong       │
│ - thoiLuong     │    │ - sdt           │    │ - thoiGianBatDau│
│ - ngonNgu       │    │ - email         │    │ - thoiGianKetThuc│
│ - gioiHanTuoi   │    │ - gioiTinh      │    │ - danhSachGheTrong│
│ - moTa          │    │ - lichSuDatVe   │    └─────────────────┘
├─────────────────┤    ├─────────────────┤              │
│ + Create()      │    │ + Create()      │              │
│ + Read()        │    │ + Read()        │              │
│ + Update()      │    │ + Update()      │              │
│ + Delete()      │    │ + Delete()      │              │
│ + getPhimById() │    │ + getKhachHangByCCCD() │       │
│ + timKiemTheoTen() │ │ + timKiemTheoTen() │           │
└─────────────────┘    └─────────────────┘              │
         │                       │                       │
         │                       │                       │
         │                       │                       │
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│       Ve        │    │       Ghe       │    │     Voucher     │
├─────────────────┤    ├─────────────────┤    ├─────────────────┤
│ - maVe          │    │ - maGhe         │    │ - maVoucher     │
│ - CCCD          │    │ - hang          │    │ - moTa          │
│ - maSuatChieu   │    │ - cot           │    │ - phanTramGiamGia│
│ - maGhe         │    │ - maPhong       │    │ - ngayBatDau    │
│ - giaVe         │    │ - maSuatChieu   │    │ - ngayKetThuc   │
│ - trangThai     │    │ - trangThai     │    │ - soLuongConLai │
├─────────────────┤    ├─────────────────┤    │ - trangThai     │
│ + Create()      │    │ + Create()      │    ├─────────────────┤
│ + Read()        │    │ + Read()        │    │ + Create()      │
│ + Update()      │    │ + Update()      │    │ + Read()        │
│ + Delete()      │    │ + Delete()      │    │ + Update()      │
│ + getVeById()   │    │ + getGheById()  │    │ + Delete()      │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

### 4. Mô tả chức năng CRUD cho các đối tượng

**CRUD Operations cho Phim**:

- **Create**: Thêm phim mới với validation thông tin
- **Read**: Xem danh sách phim, tìm kiếm theo tên/thể loại
- **Update**: Cập nhật thông tin phim
- **Delete**: Xóa phim khỏi hệ thống

**CRUD Operations cho KhachHang**:

- **Create**: Đăng ký khách hàng mới
- **Read**: Xem thông tin khách hàng, lịch sử đặt vé
- **Update**: Cập nhật thông tin cá nhân
- **Delete**: Xóa tài khoản khách hàng

**CRUD Operations cho Ve**:

- **Create**: Tạo vé mới khi đặt chỗ
- **Read**: Xem thông tin vé, lịch sử đặt vé
- **Update**: Cập nhật trạng thái vé (đã thanh toán, đã hủy)
- **Delete**: Hủy vé

**CRUD Operations cho Ghe**:

- **Create**: Thêm ghế mới vào phòng chiếu
- **Read**: Xem danh sách ghế, trạng thái ghế
- **Update**: Cập nhật trạng thái ghế (trống/đã đặt)
- **Delete**: Xóa ghế khỏi phòng

### 5. Biểu đồ UML Activity Diagram

**Activity Diagram 1: Quy trình đặt vé**

```
[Start] → [Chọn phim] → [Chọn suất chiếu] → [Chọn ghế] →
[Kiểm tra ghế trống] → [Nhập thông tin khách hàng] →
[Kiểm tra thông tin] → [Tạo vé] → [Cập nhật trạng thái ghế] →
[Thanh toán] → [Gửi xác nhận] → [End]
```

**Activity Diagram 2: Quy trình quản lý phim**

```
[Start] → [Đăng nhập Admin] → [Chọn chức năng] →
[Thêm phim] → [Nhập thông tin phim] → [Validation] →
[Lưu vào database] → [Thông báo thành công] → [End]
```

**Activity Diagram 3: Quy trình tìm kiếm phim**

```
[Start] → [Nhập từ khóa tìm kiếm] → [Chọn loại tìm kiếm] →
[Tìm kiếm trong database] → [Hiển thị kết quả] →
[Chọn phim] → [Xem chi tiết] → [End]
```

**Activity Diagram 4: Quy trình quản lý khách hàng**

```
[Start] → [Chọn chức năng] → [Thêm khách hàng] →
[Nhập thông tin] → [Kiểm tra CCCD trùng lặp] →
[Lưu thông tin] → [Cập nhật danh sách] → [End]
```

**Activity Diagram 5: Quy trình thanh toán**

```
[Start] → [Chọn phương thức thanh toán] → [Nhập thông tin] →
[Kiểm tra voucher] → [Tính toán giá] → [Xác nhận thanh toán] →
[Cập nhật trạng thái vé] → [Gửi email xác nhận] → [End]
```

### 6. Phương thức hoạt động cốt lõi của ứng dụng

**Định nghĩa phương thức cốt lõi**: Các controller xử lý các chức năng kinh doanh chính, quản lý dữ liệu cốt lõi và xử lý các luồng nghiệp vụ quan trọng nhất của hệ thống rạp phim.

**Các Controller cốt lõi bao gồm**:

**1. DatVe (Ticket Controller)**:

- **Chức năng chính**: Quản lý toàn bộ quy trình đặt vé, thanh toán và hủy vé
- **Phương thức hoạt động**:
  - `taoVe()`: Tạo vé mới với validation `giaVe > 0`, cập nhật trạng thái ghế từ `TRONG` sang `DA_DAT` thông qua `gheDB.updateGheTrangThai()`
  - `capNhatVe()`: Xử lý thanh toán, cập nhật trạng thái vé từ `CHUA_THANH_TOAN` sang `DA_THANH_TOAN`, giữ nguyên trạng thái ghế `DA_DAT`
  - `huyVe()`: Hủy vé và cập nhật trạng thái ghế từ `DA_DAT` về `TRONG` thông qua `gheDB.updateGheTrangThai()`
  - `xoaVe()`: Xóa vé và cập nhật trạng thái ghế từ `DA_DAT` về `TRONG`
  - `thongKeVe()`: Thống kê vé theo trạng thái và tính tổng doanh thu

**2. KhachHangController (Customer Controller)**:

- **Chức năng chính**: Quản lý thông tin khách hàng, lịch sử giao dịch và tìm kiếm
- **Phương thức hoạt động**:
  - `taoKhachHang()`: Đăng ký khách hàng mới với validation CCCD, email và số điện thoại
  - `capNhatKhachHang()`: Cập nhật thông tin cá nhân khách hàng
  - `timKhachHangTheoCCCD()`: Tìm kiếm khách hàng theo CCCD để xác thực
  - `timKhachHangTheoTen()`: Tìm kiếm khách hàng theo tên để hỗ trợ
  - `thongKeKhachHang()`: Thống kê số lượng khách hàng và phân tích hành vi

**3. PhimController (Movie Controller)**:

- **Chức năng chính**: Quản lý thông tin phim, thể loại và tìm kiếm
- **Phương thức hoạt động**:
  - `taoPhim()`: Thêm phim mới với validation thời lượng > 0 và các trường bắt buộc
  - `capNhatPhim()`: Cập nhật thông tin phim
  - `timKiemPhim()`: Tìm kiếm phim theo tên, thể loại hoặc ngôn ngữ
  - `thongKePhim()`: Thống kê phim theo thể loại và đánh giá

**4. SuatChieuController (Showtime Controller)**:

- **Chức năng chính**: Quản lý lịch chiếu, kiểm tra xung đột và tính toán thời gian
- **Phương thức hoạt động**:
  - `taoSuatChieu()`: Tạo suất chiếu mới với validation `thoiGianBatDau.isAfter(thoiGianKetThuc)`
  - `tinhThoiGianKetThuc()`: Tự động tính thời gian kết thúc dựa trên thời lượng phim
  - `capNhatGheTrong()`: Cập nhật danh sách ghế trống cho suất chiếu
  - `kiemTraXungDot()`: Kiểm tra xung đột lịch chiếu giữa các phòng

**5. GheController (Seat Controller)**:

- **Chức năng chính**: Quản lý trạng thái ghế, sơ đồ phòng chiếu và cập nhật real-time
- **Phương thức hoạt động**:
  - `capNhatTrangThaiGhe()`: Cập nhật trạng thái ghế (`TRONG`, `DA_DAT`, `KHOA`) thông qua `gheDB.updateGheTrangThai()`
  - `hienThiSoDoGhe()`: Hiển thị sơ đồ ghế với trạng thái real-time
  - `kiemTraGheTrong()`: Kiểm tra ghế có sẵn cho đặt vé
  - `thongKeGhe()`: Thống kê ghế theo trạng thái và phòng chiếu

**6. PhongChieuController (Cinema Room Controller)**:

- **Chức năng chính**: Quản lý cấu trúc phòng chiếu và sơ đồ ghế
- **Phương thức hoạt động**:
  - `taoPhongChieu()`: Tạo phòng chiếu mới với số hàng và cột ghế
  - `capNhatSoDoGhe()`: Cập nhật sơ đồ ghế khi thay đổi cấu trúc phòng
  - `kiemTraTrangThaiPhong()`: Kiểm tra phòng có đang hoạt động hay không
  - `thongKePhong()`: Thống kê phòng theo loại và trạng thái

**7. HoaDonController (Invoice Controller)**:

- **Chức năng chính**: Quản lý hóa đơn, thanh toán và báo cáo tài chính
- **Phương thức hoạt động**:
  - `taoHoaDon()`: Tạo hóa đơn mới với validation tổng tiền > 0 và phương thức thanh toán
  - `capNhatHoaDon()`: Cập nhật thông tin hóa đơn và trạng thái thanh toán
  - `timHoaDonTheoCCCD()`: Tìm kiếm hóa đơn theo CCCD khách hàng
  - `timHoaDonTheoKhoangThoiGian()`: Tìm kiếm hóa đơn theo khoảng thời gian
  - `thongKeHoaDon()`: Thống kê doanh thu, phương thức thanh toán và xu hướng bán hàng

**Các Controller KHÔNG phải cốt lõi**:

- **VoucherController**: Chỉ hỗ trợ khuyến mãi, không ảnh hưởng đến luồng nghiệp vụ chính
- **DanhGiaController**: Chỉ xử lý đánh giá phim, không liên quan đến quy trình kinh doanh cốt lõi
- **DoAnController**: Chỉ quản lý dịch vụ bổ sung, không phải chức năng chính của rạp phim

### 7. Thiết kế cơ sở dữ liệu (ERD, mô tả tương tác Cloud DB)

**Entity Relationship Diagram**:

```
┌─────────────┐    ┌─────────────┐    ┌─────────────┐
│    PHIM     │    │ SUAT_CHIEU  │    │   PHONG     │
├─────────────┤    ├─────────────┤    ├─────────────┤
│ PK: maPhim  │    │ PK: maSuat  │    │ PK: maPhong │
│ tenPhim     │    │ FK: maPhim  │    │ tenPhong    │
│ theLoai     │    │ FK: maPhong │    │ soHangGhe   │
│ thoiLuong   │    │ thoiGianBD  │    │ soCotGhe    │
│ ngonNgu     │    │ thoiGianKT  │    └─────────────┘
│ gioiHanTuoi │    └─────────────┘              │
│ moTa        │              │                  │
└─────────────┘              │                  │
         │                   │                  │
         │                   │                  │
┌─────────────┐    ┌─────────────┐    ┌─────────────┐
│ KHACH_HANG  │    │     VE      │    │     GHE     │
├─────────────┤    ├─────────────┤    ├─────────────┤
│ PK: CCCD    │    │ PK: maVe    │    │ PK: maGhe   │
│ ten         │    │ FK: CCCD    │    │ FK: maPhong │
│ tuoi        │    │ FK: maSuat  │    │ hang        │
│ sdt         │    │ FK: maGhe   │    │ cot         │
│ email       │    │ giaVe       │    │ trangThai   │
│ gioiTinh    │    │ trangThai   │    └─────────────┘
└─────────────┘    └─────────────┘
```

**Tương tác với Cloud Database (Aiven MySQL)**:

- Kết nối SSL bảo mật đến MySQL Cloud
- Connection pooling để tối ưu hiệu suất
- Backup tự động và khôi phục dữ liệu
- Monitoring và alerting real-time

---

## CHƯƠNG 3: CÔNG NGHỆ VÀ THUẬT TOÁN

### 1. Công nghệ sử dụng

**Backend Framework**:

- **Spring Boot 3.3.0**: Framework chính cho ứng dụng web
- **Java 17**: Ngôn ngữ lập trình chính
- **Build Tools**: Hỗ trợ cả Maven và Gradle (có cả `pom.xml` và `build.gradle`)

**Frontend Technologies**:

- **Thymeleaf**: Template engine cho server-side rendering
- **Bootstrap**: Framework CSS cho responsive design
- **JavaScript**: Xử lý tương tác client-side

**Database & Cloud**:

- **MySQL 8.0**: Hệ quản trị cơ sở dữ liệu
- **Aiven Cloud**: Platform cloud cho MySQL hosting với SSL bảo mật
- **JDBC**: Kết nối database thông qua MySQL Connector Java 8.0.33

**Development Tools**:

- **Git & GitHub**: Quản lý mã nguồn và version control
- **IDE**: Hỗ trợ IntelliJ IDEA và các IDE khác
- **Build Tools**: Maven (chính) và Gradle (hỗ trợ)

### 2. Thư viện và framework hỗ trợ

**Spring Boot Dependencies**:

- **Spring Boot Starter Web**: Framework web chính
- **Spring Boot Starter Thymeleaf**: Template engine cho server-side rendering
- **Spring Boot Starter Data JPA**: Hỗ trợ tương tác cơ sở dữ liệu (sử dụng JDBC trực tiếp)
- **MySQL Connector Java 8.0.33**: Driver kết nối MySQL chính thức
- **Spring Boot DevTools**: Công cụ phát triển (runtime scope)
- **Spring Boot Starter Test**: Framework testing với JUnit

**Frontend Libraries**:

- **Bootstrap 5.3.0**: CSS Framework cho responsive design
- **Thymeleaf**: Server-side template engine
- **JavaScript**: Xử lý tương tác client-side

**Database & JPA**:

- **JDBC**: Java Database Connectivity cho kết nối trực tiếp
- **Custom Database Classes**: Các class `*Aiven` tùy chỉnh cho tương tác database
- **Connection Pooling**: Tối ưu kết nối database

### 3. Mô tả chi tiết thuật toán xử lý chính

**Thuật toán đặt vé (DatVe.taoVe())**:

1. **Validation đầu vào**: Kiểm tra `giaVe > 0` và các trường bắt buộc (CCCD, maSuatChieu, maGhe)
2. **Kiểm tra ghế trống**: Xác minh ghế có trạng thái `TrangThaiGhe.TRONG` không
3. **Tạo vé mới**: Tạo đối tượng `Ve` với trạng thái `TrangThaiVe.CHUA_THANH_TOAN`
4. **Lưu vé vào database**: Gọi `veDB.createVe(ve)` để lưu vé
5. **Cập nhật trạng thái ghế**: Gọi `gheDB.updateGheTrangThai(maGhe, TrangThaiGhe.DA_DAT)`
6. **Trả về kết quả**: Thông báo đặt vé thành công hoặc lỗi

**Thuật toán thanh toán vé (DatVe.capNhatVe())**:

1. **Nhận thông tin thanh toán**: Mã vé và thông tin cập nhật
2. **Validation dữ liệu**: Kiểm tra vé tồn tại và chưa thanh toán
3. **Cập nhật trạng thái vé**: Thay đổi từ `CHUA_THANH_TOAN` sang `DA_THANH_TOAN`
4. **Giữ nguyên trạng thái ghế**: Ghế vẫn ở trạng thái `DA_DAT`
5. **Lưu thay đổi**: Gọi `veDB.updateVe(maVe, ve)` để cập nhật database

**Thuật toán hủy vé (DatVe.huyVe())**:

1. **Nhận mã vé cần hủy**: Từ người dùng
2. **Kiểm tra vé tồn tại**: Xác minh vé có trong hệ thống không
3. **Cập nhật trạng thái vé**: Thay đổi thành `TrangThaiVe.DA_HUY`
4. **Cập nhật trạng thái ghế**: Gọi `gheDB.updateGheTrangThai(maGhe, TrangThaiGhe.TRONG)`
5. **Lưu thay đổi**: Cập nhật cả vé và ghế trong database

**Thuật toán tìm kiếm phim (PhimController.timKiemPhim())**:

1. **Nhận từ khóa tìm kiếm**: Từ form HTML
2. **Xác định loại tìm kiếm**: Theo tên, thể loại, ngôn ngữ, hoặc mã phim
3. **Thực hiện truy vấn**: Gọi `phimDB.timKiemPhim(tuKhoa)` với SQL LIKE
4. **Lọc kết quả**: Áp dụng bộ lọc theo tiêu chí tìm kiếm
5. **Trả về danh sách**: Hiển thị kết quả qua Thymeleaf template

**Thuật toán quản lý ghế (GheController.capNhatTrangThaiGhe())**:

1. **Nhận thông tin ghế**: Mã ghế và trạng thái mới
2. **Validation trạng thái**: Kiểm tra trạng thái hợp lệ (`TRONG`, `DA_DAT`, `KHOA`)
3. **Cập nhật database**: Gọi `gheDB.updateGheTrangThai(maGhe, trangThaiMoi)`
4. **Kiểm tra kết quả**: Xác nhận cập nhật thành công
5. **Trả về phản hồi**: Thông báo thành công hoặc lỗi cho người dùng

### 4. Cấu hình và triển khai

**Cấu hình Database (Aiven MySQL Cloud)**:

- **SSL Connection**: Sử dụng `ssl-mode=REQUIRED` cho bảo mật
- **Port**: 17237 (port tùy chỉnh của Aiven)
- **Host**: `mysql-14737a33-nglthu-4e05.k.aivencloud.com`
- **Database**: `defaultdb`
- **Driver**: `com.mysql.cj.jdbc.Driver`

**Cấu hình Server**:

- **Port**: 8082 (port tùy chỉnh)
- **Context Path**: `/` (root context)
- **Thymeleaf Cache**: `false` (development mode)

**Cấu hình Logging**:

- **Debug Level**: Cho package `com.example.servingwebcontent`
- **SQL Logging**: Hiển thị tất cả SQL queries
- **Parameter Binding**: Hiển thị giá trị parameters trong SQL

---

## CHƯƠNG 4: XÂY DỰNG VÀ KIỂM THỬ

### 1. Môi trường cài đặt

**Yêu cầu hệ thống**:

- **OS**: Windows 10/11, macOS, Linux
- **Java**: JDK 17 trở lên
- **Database**: MySQL 8.0
- **Browser**: Chrome, Firefox, Safari, Edge
- **RAM**: Tối thiểu 4GB
- **Storage**: 2GB trống

**Cài đặt và cấu hình**:

1. Cài đặt Java JDK 17
2. Cài đặt Maven
3. Clone repository từ GitHub
4. Cấu hình database connection
5. Chạy ứng dụng với lệnh: `mvn spring-boot:run`

### 2. Giao diện người dùng (yêu cầu 8)

**Trang chủ (Dashboard)**:

- Giao diện responsive với Bootstrap
- Hiển thị thống kê tổng quan
- Menu navigation chính
- Cards hiển thị các chức năng chính

**Trang quản lý phim**:

- Bảng hiển thị danh sách phim
- Form thêm/sửa phim
- Tìm kiếm và lọc phim
- Pagination cho danh sách dài

**Trang quản lý khách hàng**:

- Bảng thông tin khách hàng
- Form đăng ký/cập nhật thông tin
- Lịch sử đặt vé của từng khách hàng
- Thống kê khách hàng

**Trang đặt vé**:

- Giao diện chọn ghế trực quan
- Form thông tin đặt vé
- Hiển thị giá và voucher
- Xác nhận thanh toán

### 3. Kết quả thực hiện CRUD và các chức năng chính

**Kết quả CRUD Phim**:

- ✅ Thêm phim mới thành công
- ✅ Cập nhật thông tin phim
- ✅ Xóa phim khỏi hệ thống
- ✅ Tìm kiếm phim theo nhiều tiêu chí
- ✅ Hiển thị danh sách phim với pagination

**Kết quả CRUD Khách hàng**:

- ✅ Đăng ký khách hàng mới
- ✅ Cập nhật thông tin cá nhân
- ✅ Xem lịch sử đặt vé
- ✅ Thống kê khách hàng theo giới tính, độ tuổi

**Kết quả CRUD Vé**:

- ✅ Đặt vé thành công
- ✅ Cập nhật trạng thái vé
- ✅ Hủy vé
- ✅ Xem lịch sử đặt vé

**Kết quả CRUD Ghế**:

- ✅ Quản lý trạng thái ghế
- ✅ Hiển thị ghế trống/đã đặt
- ✅ Cập nhật real-time khi đặt vé

### 4. Unit test & xử lý lỗi (yêu cầu 6)

**Unit Tests đã triển khai**:

**Test Phim**:

- **Test Constructor**: Kiểm tra tạo đối tượng phim với các tham số khác nhau
- **Test Getters/Setters**: Kiểm tra việc đọc và ghi thuộc tính
- **Test CRUD Operations**: Kiểm tra thêm, đọc, cập nhật, xóa phim
- **Test Tìm kiếm**: Kiểm tra tìm kiếm phim theo tên và thể loại

**Test KhachHang**:

- **Test Tạo khách hàng**: Kiểm tra đăng ký khách hàng mới
- **Test Cập nhật**: Kiểm tra cập nhật thông tin khách hàng
- **Test Tìm kiếm**: Kiểm tra tìm kiếm khách hàng theo CCCD và tên

**Xử lý lỗi**:

- Validation input data
- Exception handling cho database operations
- Error messages thân thiện người dùng
- Logging lỗi để debug

### 5. Kiểm thử tích hợp

**Integration Tests**:

- Test kết nối database
- Test các controller endpoints
- Test luồng đặt vé hoàn chỉnh
- Test tương tác giữa các components

---

## CHƯƠNG 5: TRIỂN KHAI VÀ QUẢN LÝ MÃ NGUỒN

### 1. Kho mã nguồn GitHub & hướng dẫn chạy ứng dụng (yêu cầu 9)

**Repository**: `https://github.com/username/OOP_N05_T3_2025_TRANG_SANG`

**Cấu trúc project**:

```
CinemaManagementApplication/
├── complete/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/example/servingwebcontent/
│   │   │   │       ├── controller/
│   │   │   │       ├── model/
│   │   │   │       ├── database/
│   │   │   │       └── util/
│   │   │   └── resources/
│   │   │       ├── templates/
│   │   │       └── application.properties
│   │   └── test/
│   │       └── java/
│   │           └── com/example/servingwebcontent/
│   ├── pom.xml
│   └── README.md
└── initial/
```

**Hướng dẫn chạy ứng dụng**:

1. **Clone repository**: Sử dụng Git để clone project từ GitHub về máy local
2. **Cài đặt dependencies**: Chạy lệnh Maven để cài đặt các thư viện cần thiết
3. **Cấu hình database**: Chỉnh sửa file cấu hình để kết nối với cơ sở dữ liệu
4. **Chạy ứng dụng**: Sử dụng Maven để khởi động ứng dụng Spring Boot
5. **Truy cập ứng dụng**: Mở trình duyệt web và truy cập địa chỉ localhost:8080

### 2. Lịch sử commit & phân công công việc nhóm (yêu cầu 10)

**Thành viên nhóm**:

1. **Kiều Thị Thu Trang** (MSV: 24100093)

   - Phụ trách: Model classes, Database layer
   - Commit history: Phim, KhachHang, SuatChieu models

2. **Trần Minh Sang** (MSV: 24100012)

   - Phụ trách: Controllers, Frontend templates
   - Commit history: PhimController, KhachHangController

3. **Nguyễn Lệ Thu**
   - Phụ trách: Database connection, Testing
   - Commit history: myDBConnection, Unit tests

**Lịch sử commit chính**:

```
Commit 1: Initial project setup
- Tạo cấu trúc project Spring Boot
- Cấu hình Maven và dependencies

Commit 2: Database models implementation
- Tạo các model classes: Phim, KhachHang, Ve
- Implement CRUD operations

Commit 3: Database connection setup
- Cấu hình kết nối MySQL Cloud
- Implement database access layer

Commit 4: Controllers implementation
- Tạo các controller classes
- Implement REST endpoints

Commit 5: Frontend templates
- Tạo HTML templates với Thymeleaf
- Implement responsive design với Bootstrap

Commit 6: Unit testing
- Viết unit tests cho các model classes
- Test CRUD operations

Commit 7: Integration and testing
- Test tích hợp toàn bộ hệ thống
- Fix bugs và optimize performance

Commit 8: Documentation and deployment
- Cập nhật README
- Chuẩn bị deployment
```

---

## CHƯƠNG 6: KẾT LUẬN VÀ HƯỚNG PHÁT TRIỂN

### 1. Những mặt đã đạt được

**Về mặt kỹ thuật**:

- ✅ Triển khai thành công kiến trúc MVC
- ✅ Áp dụng đầy đủ các nguyên tắc OOP
- ✅ Tích hợp cơ sở dữ liệu MySQL Cloud
- ✅ Xây dựng giao diện web responsive
- ✅ Implement đầy đủ chức năng CRUD
- ✅ Viết unit tests cho các chức năng chính

**Về mặt chức năng**:

- ✅ Quản lý phim hoàn chỉnh
- ✅ Quản lý khách hàng và lịch sử đặt vé
- ✅ Hệ thống đặt vé tự động
- ✅ Quản lý ghế và suất chiếu
- ✅ Hệ thống voucher và khuyến mãi
- ✅ Báo cáo thống kê cơ bản

**Về mặt học tập**:

- ✅ Hiểu sâu về lập trình hướng đối tượng
- ✅ Thực hành Spring Boot framework
- ✅ Rèn luyện kỹ năng database design
- ✅ Phát triển web application hoàn chỉnh

### 2. Hạn chế

**Về mặt kỹ thuật**:

- Chưa implement authentication và authorization
- Chưa có API documentation đầy đủ
- Chưa implement caching để tối ưu performance
- Chưa có logging system chuyên nghiệp

**Về mặt chức năng**:

- Chưa có hệ thống thanh toán online
- Chưa có tính năng gửi email xác nhận
- Chưa có mobile app
- Chưa có tính năng đánh giá phim chi tiết

**Về mặt bảo mật**:

- Chưa implement input validation đầy đủ
- Chưa có protection chống SQL injection
- Chưa implement rate limiting
- Chưa có backup strategy

### 3. Hướng phát triển tương lai

**Ngắn hạn (3-6 tháng)**:

- Implement user authentication và authorization
- Thêm tính năng thanh toán online
- Phát triển mobile app
- Cải thiện UI/UX

**Trung hạn (6-12 tháng)**:

- Implement microservices architecture
- Thêm tính năng AI cho recommendation
- Phát triển admin dashboard
- Tích hợp với các hệ thống bên ngoài

**Dài hạn (1-2 năm)**:

- Mở rộng thành hệ thống quản lý chuỗi rạp phim
- Implement machine learning cho business intelligence
- Phát triển platform cho nhiều rạp phim
- Tích hợp với các dịch vụ giải trí khác

**Công nghệ mới sẽ áp dụng**:

- Spring Security cho bảo mật
- Redis cho caching
- Elasticsearch cho tìm kiếm
- Docker cho containerization
- Kubernetes cho orchestration
- React/Vue.js cho frontend
- Flutter cho mobile app

---

## TÀI LIỆU THAM KHẢO

1. **Spring Boot Documentation**

   - https://spring.io/projects/spring-boot
   - Spring Boot Reference Documentation

2. **Java Programming Language**

   - Oracle Java Documentation
   - Effective Java by Joshua Bloch

3. **Object-Oriented Programming**

   - "Design Patterns" by Gang of Four
   - "Clean Code" by Robert C. Martin

4. **Database Design**

   - "Database Design for Mere Mortals" by Michael Hernandez
   - MySQL 8.0 Reference Manual

5. **Web Development**

   - Bootstrap Documentation
   - Thymeleaf Documentation
   - HTML5 and CSS3 Specifications

6. **Testing**

   - JUnit 5 User Guide
   - Spring Boot Testing Guide

7. **Version Control**

   - Git Documentation
   - GitHub Guides

8. **Cloud Platforms**

   - Aiven Documentation
   - MySQL Cloud Best Practices

9. **Software Architecture**

   - "Clean Architecture" by Robert C. Martin
   - "Patterns of Enterprise Application Architecture" by Martin Fowler

10. **Project Management**
    - Agile Development Principles
    - Scrum Framework Guide
