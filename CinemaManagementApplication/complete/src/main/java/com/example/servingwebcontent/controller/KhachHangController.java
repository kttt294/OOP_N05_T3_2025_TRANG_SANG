package com.example.servingwebcontent.controller;

import java.util.ArrayList;
import com.example.servingwebcontent.model.KhachHang;
import com.example.servingwebcontent.model.Ve;
import com.example.servingwebcontent.model.DanhGia;

public class KhachHangController {
    public static boolean taoKhachHang(KhachHang kh) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (kh == null) {
                throw new IllegalArgumentException("Khách hàng không được null!");
            }
            if (kh.getCCCD() == null || kh.getCCCD().trim().isEmpty()) {
                throw new IllegalArgumentException("CCCD không được để trống!");
            }
            if (kh.getTen() == null || kh.getTen().trim().isEmpty()) {
                throw new IllegalArgumentException("Tên khách hàng không được để trống!");
            }
            if (kh.getTuoi() < 0 || kh.getTuoi() > 150) {
                throw new IllegalArgumentException("Tuổi không hợp lệ!");
            }
            if (kh.getSdt() == null || kh.getSdt().trim().isEmpty()) {
                throw new IllegalArgumentException("Số điện thoại không được để trống!");
            }
            if (kh.getEmail() == null || kh.getEmail().trim().isEmpty()) {
                throw new IllegalArgumentException("Email không được để trống!");
            }

        KhachHang.Create(kh);
            System.out.println("Tạo khách hàng thành công!");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Cập nhật thông tin khách hàng (cho Admin)
    public static boolean capNhatThongTin(String CCCD, KhachHang khMoi) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (CCCD == null || CCCD.trim().isEmpty()) {
                throw new IllegalArgumentException("CCCD không được để trống!");
            }
            if (khMoi == null) {
                throw new IllegalArgumentException("Thông tin khách hàng mới không được null!");
            }

            // Kiểm tra khách hàng có tồn tại không
            KhachHang khCu = KhachHang.getKhachHangByCCCD(CCCD);
            if (khCu == null) {
                System.out.println("Không tìm thấy khách hàng với CCCD: " + CCCD);
                return false;
            }

        KhachHang.Update(CCCD, khMoi);
            System.out.println("Cập nhật thông tin khách hàng thành công!");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Xóa khách hàng (cho Admin)
    public static boolean xoaKhachHang(String CCCD) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (CCCD == null || CCCD.trim().isEmpty()) {
                throw new IllegalArgumentException("CCCD không được để trống!");
            }

            // Kiểm tra khách hàng có tồn tại không
            KhachHang kh = KhachHang.getKhachHangByCCCD(CCCD);
            if (kh == null) {
                System.out.println("Không tìm thấy khách hàng với CCCD: " + CCCD);
                return false;
            }

        KhachHang.Delete(CCCD);
            System.out.println("Xóa khách hàng thành công!");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Xem thông tin khách hàng (cho Admin)
    public static boolean xemThongTin(String CCCD) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (CCCD == null || CCCD.trim().isEmpty()) {
                throw new IllegalArgumentException("CCCD không được để trống!");
            }

        KhachHang.Read(CCCD);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Xem tất cả khách hàng (cho Admin)
    public static boolean xemTatCaKhachHang() {
        try {
        KhachHang.Read();
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // === PHƯƠNG THỨC TÌM KIẾM ===
    
    // Tìm kiếm khách hàng theo CCCD
    public static KhachHang timKhachHangTheoCCCD(String CCCD) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (CCCD == null || CCCD.trim().isEmpty()) {
                throw new IllegalArgumentException("CCCD không được để trống!");
            }

        return KhachHang.getKhachHangByCCCD(CCCD);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return null;
        }
    }

    // Tìm kiếm khách hàng theo tên
    public static ArrayList<KhachHang> timKhachHangTheoTen(String ten) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (ten == null || ten.trim().isEmpty()) {
                throw new IllegalArgumentException("Tên tìm kiếm không được để trống!");
            }

        return KhachHang.timKiemTheoTen(ten);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Tìm kiếm khách hàng theo giới tính
    public static ArrayList<KhachHang> timKhachHangTheoGioiTinh(String gioiTinh) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (gioiTinh == null || gioiTinh.trim().isEmpty()) {
                throw new IllegalArgumentException("Giới tính không được để trống!");
            }

        return KhachHang.timKiemTheoGioiTinh(gioiTinh);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // === PHƯƠNG THỨC THỐNG KÊ ===
    public static boolean xemThongKeKhachHang() {
        try {
        KhachHang.xemThongKe();
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Tính tổng tiền khách hàng đã sử dụng
    public static double tinhTongTienKhachHang(String CCCD) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (CCCD == null || CCCD.trim().isEmpty()) {
                throw new IllegalArgumentException("CCCD không được để trống!");
            }
        KhachHang kh = KhachHang.getKhachHangByCCCD(CCCD);
        return kh.tinhTongTienKhachHang();
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return 0.0;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return 0.0;
        }
    }

    // === PHƯƠNG THỨC QUẢN LÝ VÉ ===
    
    public static boolean xemLichSuDatVe(String CCCD) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (CCCD == null || CCCD.trim().isEmpty()) {
                throw new IllegalArgumentException("CCCD không được để trống!");
            }

        KhachHang kh = KhachHang.getKhachHangByCCCD(CCCD);
        if (kh != null) {
            ArrayList<Ve> lichSu = kh.getLichSuDatVe();
            if (lichSu == null || lichSu.isEmpty()) {
                System.out.println("Khách hàng chưa đặt vé nào.");
            } else {
                System.out.println("Lịch sử đặt vé của khách hàng " + kh.getTen() + ":");
                for (Ve ve : lichSu) {
                    ve.hienThiThongTin();
                    System.out.println("---");
                }
            }
                return true;
        } else {
            System.out.println("Không tìm thấy khách hàng với CCCD: " + CCCD);
                return false;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Thêm vé vào lịch sử khách hàng
    public static boolean themVeChoKhachHang(String CCCD, Ve ve) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (CCCD == null || CCCD.trim().isEmpty()) {
                throw new IllegalArgumentException("CCCD không được để trống!");
            }
            if (ve == null) {
                throw new IllegalArgumentException("Vé không được null!");
            }

        KhachHang kh = KhachHang.getKhachHangByCCCD(CCCD);
        if (kh != null) {
            kh.themVe(ve);
            System.out.println("Đã thêm vé vào lịch sử khách hàng.");
                return true;
        } else {
            System.out.println("Không tìm thấy khách hàng với CCCD: " + CCCD);
                return false;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // === PHƯƠNG THỨC BÁO CÁO ===
    
    public static boolean baoCaoKhachHangVIP() {
        try {
        ArrayList<KhachHang> danhSach = KhachHang.Read();
        System.out.println("=== BÁO CÁO KHÁCH HÀNG VIP ===");
        
        int count = 0;
        for (KhachHang kh : danhSach) {
            if (kh.getLichSuDatVe() != null && kh.getLichSuDatVe().size() >= 5) {
                count++;
                System.out.println("Khách hàng VIP #" + count + ":");
                kh.hienThiThongTin();
                System.out.println("Tổng tiền đã sử dụng: " + tinhTongTienKhachHang(kh.getCCCD()) + " VNĐ");
                System.out.println("---");
            }
        }
        
        if (count == 0) {
            System.out.println("Chưa có khách hàng VIP nào.");
        } else {
            System.out.println("Tổng số khách hàng VIP: " + count);
            }
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Báo cáo khách hàng mới (chưa đặt vé)
    public static boolean baoCaoKhachHangMoi() {
        try {
        ArrayList<KhachHang> danhSach = KhachHang.Read();
        System.out.println("=== BÁO CÁO KHÁCH HÀNG MỚI ===");
        
        int count = 0;
        for (KhachHang kh : danhSach) {
            if (kh.getLichSuDatVe() == null || kh.getLichSuDatVe().isEmpty()) {
                count++;
                System.out.println("Khách hàng mới #" + count + ":");
                kh.hienThiThongTin();
                System.out.println("---");
            }
        }
        
        if (count == 0) {
            System.out.println("Tất cả khách hàng đều đã đặt vé.");
        } else {
            System.out.println("Tổng số khách hàng mới: " + count);
        }
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // === PHƯƠNG THỨC GỬI ĐÁNH GIÁ ===

    public static boolean guiDanhGia(DanhGia danhGia) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (danhGia == null) {
                throw new IllegalArgumentException("Đánh giá không được null!");
            }

            DanhGia.Create(danhGia);
            System.out.println("Gửi đánh giá thành công!");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }
} 