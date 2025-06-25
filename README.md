# OOP_N05_T3_2025_TRANG_SANG

ỨNG DỤNG QUẢN LÝ RẠP PHIM

# Describe and Analyse the Project

Đối tượng:
- KhachHang (maKH, tenKH, tuoi, sdt, email, lichSuDatVe)
- Phim (maPhim, tenPhim, theLoai, thoiLuong, ngonNgu, gioiHanTuoi, moTa, danhGia)
- SuatChieu (maSuatChieu, phim, phong, thoiGianBatDau, thoiGianKetThuc, danhSachGheTrong)
- PhongChieu (maPhong, tenPhong, soHangGhe, soCotGhe, danhSachGhe)
- Ghe (maGhe, hang, cot, daDat)
- Ve (maVe, khachHang, suatChieu, ghe, giaVe, daThanhToan)
- DatVe (maDatVe, khachHang, danhSachVe, tongTien, thoiGianDat)
- NhanVien (maNV, hoTen, username, password, chucVu)
- DanhGia (maDanhGia, nguoiDanhGia, phim, soSao, binhLuan, thoiGian)
- DoAn (maDoAn, tenDoAn, gia, soLuong)
- HoaDon (maHoaDon, khachHang, veDaDat, doAn, tongTien, thoiGianThanhToan, phuongThucThanhToan)
- TaiKhoan (tenDangNhap, matKhau, vaiTro, trangThai)
- Voucher (maVoucher, moTa, phanTramGiamGia, ngayBatDau, ngayKetThuc, soLuongConLai)
- DoanhThuThongKe(ngayThongKe, soLuongVeBanRa, tongDoanhThu, phimBanChayNhat, tiLeChoNgoiDay)

Thành viên:
1. Kiều Thị Thu Trang, MSV: 24100093 (kttt294)
2. Trần Minh Sang, MSV : 24100012 (sangzesy)
3. Nguyễn Lệ Thu (nglthu1979)

Câu 1: Tiêu đề của bài tập lớn cuối kỳ: Ứng dụng uản lý rạp chiếu phim (CinemaManager)

Câu 2: 03 đối tượng cơ sở cần thiết là:
1. Phim (đối tượng quản lý thông tin Phim): Đại diện cho 1 bộ phim được chiếu tại rạp. Gồm các thuộc tính:
  + maPhim (String): mã phim
  + tenPhim (String): tên phim
  + theLoai (String): thể loại phim
  + thoiLuong (int - đơn vị: Phút): thời lượng phim
  + ngonNgu (String): ngôn ngữ của phim
  + gioiHanTuoi (int - đơn vị: tuổi): giới hạn độ tuổi xem phim
  + moTa (String): mô tả phim
  + danhGia (DanhGia): đánh giá phim, có kiểu là class DanhGia
2. SuatChieu (đối tượng quản lý lịch chiếu): lưu thông tin một suất chiếu cụ thể của phim. Gồm:
  + maSuatChieu (String): mã suất chiếu
  + phim (Phim): bộ phim được chiếu, có kiểu là class Phim
  + phongChieu (PhongChieu): phòng chiếu bộ phim đó, có kiểu là class PhongChieu
  + thoiGianBatDau (LocalDateTime): thời gian bắt đầu chiếu bộ phim
  + thoiGianKetThuc (LocalDateTime): thời gian kết thúc chiếu bộ phim
  + danhSachGheTrong (List<Ghe>): danh sách ghế còn trống cho suất chiếu hiện tại
3. KhachHang (đối tượng quản lý người mua vé): quản lý thông tin khách hàng mua vé. Gồm:
  + maKH (String): mã khách hàng
  + tenKH (String): tên khách hàng
  + tuoi (int): tuổi của khách hàng
  + sdt (String): số điện thoại của khách hàng
  + email (String): email của khách hàng
  + gioiTinh (GioiTinh): giới tính của khách hàng, có kiểu là class GioiTinh
  + lichSuDatVe (List<Ve>): lịch sử đặt vé của khách hàng này
