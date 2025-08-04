package com.example.servingwebcontent.controller;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Scanner;

import com.example.servingwebcontent.model.Ve;
import com.example.servingwebcontent.util.DateTimeUtils;

public class VeController {

    // Tạo vé mới
    public static boolean taoVe(Ve ve) {
        try {
            if (ve == null || ve.getMaVe() == null || ve.getMaVe().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã vé không được để trống!");
            }
            if (ve.getCCCD() == null || ve.getCCCD().trim().isEmpty()) {
                throw new IllegalArgumentException("CCCD không được để trống!");
            }
            if (ve.getMaSuatChieu() == null || ve.getMaSuatChieu().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã suất chiếu không được để trống!");
            }
            if (ve.getMaGhe() == null || ve.getMaGhe().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã ghế không được để trống!");
            }
            if (ve.getGiaVe() <= 0) {
                throw new IllegalArgumentException("Giá vé phải lớn hơn 0!");
            }

            Ve.Create(ve);
            System.out.println("Tạo vé thành công!");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Cập nhật vé
    public static boolean capNhatVe(String maVe, Ve veMoi) {
        try {
            if (maVe == null || maVe.trim().isEmpty() || veMoi == null) {
                throw new IllegalArgumentException("Mã vé và thông tin vé mới không được để trống!");
            }

            Ve veCu = Ve.getVeById(maVe);
            if (veCu == null) {
                System.out.println("Không tìm thấy vé với mã: " + maVe);
                return false;
            }

            Ve.Update(maVe, veMoi);
            System.out.println("Cập nhật vé thành công!");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Xóa vé
    public static boolean xoaVe(String maVe) {
        try {
            if (maVe == null || maVe.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã vé không được để trống!");
            }

            Ve ve = Ve.getVeById(maVe);
            if (ve == null) {
                System.out.println("Không tìm thấy vé với mã: " + maVe);
                return false;
            }

            Ve.Delete(maVe);
            System.out.println("Xóa vé thành công!");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Xem thông tin vé
    public static boolean xemThongTinVe(String maVe) {
        try {
            if (maVe == null || maVe.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã vé không được để trống!");
            }

            Ve.Read(maVe);
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Xem tất cả vé
    public static boolean xemTatCaVe() {
        try {
            Ve.Read();
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Hủy vé
    public static boolean huyVe(String maVe) {
        try {
            if (maVe == null || maVe.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã vé không được để trống!");
            }

            Ve ve = Ve.getVeById(maVe);
            if (ve == null) {
                System.out.println("Không tìm thấy vé với mã: " + maVe);
                return false;
            }

            if (ve.getTrangThai() != Ve.TrangThaiVe.CHUA_THANH_TOAN) {
                System.out.println("Vé không thể hủy (đã hủy hoặc đã sử dụng)!");
                return false;
            }

            ve.setTrangThai(Ve.TrangThaiVe.DA_HUY);
            Ve.Update(maVe, ve);
            System.out.println("Hủy vé thành công!");
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Tính tổng doanh thu – dùng các vé đã thanh toán
    public static double tinhTongDoanhThu() {
        try {
            Scanner scanner = new Scanner(System.in);
            LocalDateTime tuNgay = DateTimeUtils.nhapThoiGian(scanner, "Nhập thời gian bắt đầu");
            LocalDateTime denNgay = DateTimeUtils.nhapThoiGian(scanner, "Nhập thời gian kết thúc");

            double tong = 0;
            for (Ve ve : Ve.getDanhSachVe()) {
                if (ve.getTrangThai() == Ve.TrangThaiVe.DA_THANH_TOAN) {
                    tong += ve.getGiaVe(); // Bỏ điều kiện thời gian vì Ve không có trường thời gian đặt
                }
            }

            System.out.println("Tổng doanh thu từ " + DateTimeUtils.formatVietDateTime(tuNgay) +
                               " đến " + DateTimeUtils.formatVietDateTime(denNgay) +
                               " là: " + tong + " VND");
            return tong;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return 0.0;
        }
    }

    // Thống kê vé theo trạng thái
    public static boolean thongKeVe() {
        try {
            int chuaThanhToan = 0, daThanhToan = 0, daHuy = 0;

            for (Ve ve : Ve.getDanhSachVe()) {
                switch (ve.getTrangThai()) {
                    case CHUA_THANH_TOAN: chuaThanhToan++; break;
                    case DA_THANH_TOAN: daThanhToan++; break;
                    case DA_HUY: daHuy++; break;
                }
            }

            System.out.println("=== THỐNG KÊ VÉ ===");
            System.out.println("Chưa thanh toán: " + chuaThanhToan);
            System.out.println("Đã thanh toán: " + daThanhToan);
            System.out.println("Đã hủy: " + daHuy);
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Tìm kiếm vé theo mã
    public static Ve timVeTheoMa(String maVe) {
        try {
            if (maVe == null || maVe.trim().isEmpty()) return null;
            return Ve.getVeById(maVe);
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return null;
        }
    }

    // Tìm vé theo khách hàng (lọc từ danh sách tĩnh)
    public static ArrayList<Ve> timVeTheoKhachHang(String CCCD) {
        ArrayList<Ve> ketQua = new ArrayList<>();
        try {
            if (CCCD == null || CCCD.trim().isEmpty()) return ketQua;

            for (Ve ve : Ve.getDanhSachVe()) {
                if (ve.getCCCD().equalsIgnoreCase(CCCD)) {
                    ketQua.add(ve);
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
        }
        return ketQua;
    }

    // Tìm vé theo suất chiếu (lọc từ danh sách tĩnh)
    public static ArrayList<Ve> timVeTheoSuatChieu(String maSuatChieu) {
        ArrayList<Ve> ketQua = new ArrayList<>();
        try {
            if (maSuatChieu == null || maSuatChieu.trim().isEmpty()) return ketQua;

            for (Ve ve : Ve.getDanhSachVe()) {
                if (ve.getMaSuatChieu().equalsIgnoreCase(maSuatChieu)) {
                    ketQua.add(ve);
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
        }
        return ketQua;
    }
}
