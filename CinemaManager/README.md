## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).


1. Đặt Vé
Chức năng:Đặt vé là quá trình khách hàng chọn ghế, suất chiếu và tiến hành đặt vé cho một bộ phim tại rạp.
Mô tả hoạt động:
•	Khách hàng cung cấp thông tin cá nhân, chọn suất chiếu, ghế và tiến hành đặt vé.
•	Hệ thống kiểm tra trạng thái ghế (chỉ cho phép đặt nếu ghế còn trống - trạng thái "BinhThuong").
•	Nếu hợp lệ, hệ thống tạo một đối tượng Ve mới, lưu vào danh sách vé, đồng thời cập nhật trạng thái ghế thành "Khoa" (đã đặt).
•	Nếu ghế đã được đặt hoặc không khả dụng, hệ thống thông báo lỗi và không tạo vé.
Mã nguồn liên quan:
•	Lớp DatVe (trong controller):
•	Phương thức datVe() thực hiện toàn bộ logic kiểm tra, tạo vé, cập nhật trạng thái ghế.
•	Lớp GheSuatChieu (trong model):
•	Quản lý trạng thái ghế theo từng suất chiếu.
•	Lớp Ve (trong model):
•	Lưu trữ thông tin vé đã đặt.
Ý nghĩa:Đảm bảo tính nhất quán dữ liệu, tránh trùng lặp đặt ghế, đồng thời lưu lại lịch sử đặt vé cho khách hàng.
________________________________________
2. Tính tổng tiền mà khách hàng sử dụng
Chức năng:Tính tổng số tiền mà một khách hàng đã chi trả cho tất cả các vé đã đặt.
Mô tả hoạt động:
•	Hệ thống truy xuất lịch sử đặt vé của khách hàng (thường là thuộc tính lichSuDatVe trong đối tượng KhachHang).
•	Duyệt qua từng vé, cộng dồn giá trị của trường giaVe (hoặc có thể kiểm tra thêm trạng thái đã thanh toán).
•	Trả về tổng số tiền mà khách hàng đã sử dụng cho việc mua vé.
Mã nguồn liên quan:
•	Lớp KhachHang (trong model):
•	Thuộc tính lichSuDatVe (danh sách các vé đã đặt).
•	Lớp Ve (trong model):
•	Trường giaVe lưu giá trị tiền của từng vé.
Ý nghĩa:Giúp quản lý, thống kê chi tiêu của khách hàng, phục vụ cho các chương trình khuyến mãi, chăm sóc khách hàng thân thiết.
________________________________________
3. In ra suất chiếu trong ngày
Chức năng:Liệt kê tất cả các suất chiếu diễn ra trong một ngày cụ thể.
Mô tả hoạt động:
•	Hệ thống duyệt qua danh sách các suất chiếu (SuatChieu).
•	So sánh ngày của từng suất chiếu với ngày cần tra cứu (thường so sánh phần ngày của thuộc tính thoiGianBatDau).
•	In ra thông tin các suất chiếu trùng ngày.
Mã nguồn liên quan:
•	Lớp SuatChieuController (trong controller):
•	Phương thức ví dụ: hienThiSuatChieuTrongNgay(List<SuatChieu> danhSach)
Lọc và in ra các suất chiếu trong ngày.
•	Lớp SuatChieu (trong model):
•	Thuộc tính thoiGianBatDau (kiểu LocalDateTime).
Ý nghĩa:Giúp khách hàng dễ dàng tra cứu các suất chiếu trong ngày, hỗ trợ nhân viên rạp quản lý lịch chiếu.

