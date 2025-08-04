package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.model.Voucher;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class VoucherController {

    // Tạo voucher mới
    public static boolean taoVoucher(Voucher voucher) {
        try {
            if (voucher == null || voucher.getMaVoucher() == null || voucher.getMaVoucher().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã voucher không được để trống!");
            }
            if (voucher.getMoTa() == null || voucher.getMoTa().trim().isEmpty()) {
                throw new IllegalArgumentException("Mô tả voucher không được để trống!");
            }
            if (voucher.getPhanTramGiamGia() <= 0) {
                throw new IllegalArgumentException("Phần trăm giảm giá phải lớn hơn 0!");
            }
            if (voucher.getSoLuongConLai() == null || voucher.getSoLuongConLai().trim().isEmpty()) {
                throw new IllegalArgumentException("Số lượng còn lại không được để trống!");
            }

            Voucher.Create(voucher);
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi khi tạo voucher: " + e.getMessage());
            return false;
        }
    }

    // Cập nhật voucher
    public static boolean capNhatVoucher(String maVoucher, Voucher voucherMoi) {
        try {
            if (maVoucher == null || maVoucher.trim().isEmpty() || voucherMoi == null) {
                throw new IllegalArgumentException("Dữ liệu đầu vào không hợp lệ!");
            }

            Voucher cu = Voucher.getVoucherById(maVoucher);
            if (cu == null) {
                System.out.println("Không tìm thấy voucher với mã: " + maVoucher);
                return false;
            }

            Voucher.Update(maVoucher, voucherMoi);
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật voucher: " + e.getMessage());
            return false;
        }
    }

    // Xóa voucher
    public static boolean xoaVoucher(String maVoucher) {
        try {
            if (maVoucher == null || maVoucher.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã voucher không được để trống!");
            }

            Voucher v = Voucher.getVoucherById(maVoucher);
            if (v == null) {
                System.out.println("Không tìm thấy voucher với mã: " + maVoucher);
                return false;
            }

            Voucher.Delete(maVoucher);
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi khi xóa voucher: " + e.getMessage());
            return false;
        }
    }

    // Xem 1 voucher
    public static boolean xemThongTinVoucher(String maVoucher) {
        try {
            if (maVoucher == null || maVoucher.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã voucher không được để trống!");
            }

            Voucher.Read(maVoucher);
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi khi xem thông tin voucher: " + e.getMessage());
            return false;
        }
    }

    // Xem tất cả voucher
    public static boolean xemTatCaVoucher() {
        try {
            Voucher.Read();
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi khi xem danh sách voucher: " + e.getMessage());
            return false;
        }
    }

    // Tìm voucher theo mã
    public static Voucher timVoucherTheoMa(String maVoucher) {
        try {
            if (maVoucher == null || maVoucher.trim().isEmpty()) return null;
            return Voucher.getVoucherById(maVoucher);
        } catch (Exception e) {
            System.out.println("Lỗi khi tìm voucher: " + e.getMessage());
            return null;
        }
    }

    // Tìm voucher theo tên
    public static ArrayList<Voucher> timVoucherTheoTen(String keyword) {
        ArrayList<Voucher> ketQua = new ArrayList<>();
        try {
            if (keyword == null || keyword.trim().isEmpty()) return ketQua;

            for (Voucher v : Voucher.Read()) {
                if (v.getMoTa().toLowerCase().contains(keyword.toLowerCase())) {
                    ketQua.add(v);
                }
            }

            return ketQua;
        } catch (Exception e) {
            System.out.println("Lỗi khi tìm kiếm voucher theo tên: " + e.getMessage());
            return ketQua;
        }
    }

    // Kiểm tra voucher hợp lệ
    public static boolean kiemTraVoucherHopLe(String maVoucher) {
        try {
            Voucher v = Voucher.getVoucherById(maVoucher);
            if (v == null) return false;

            LocalDateTime now = LocalDateTime.now();
            if (now.isBefore(v.getNgayBatDau()) || now.isAfter(v.getNgayKetThuc())) return false;

            int sl = Integer.parseInt(v.getSoLuongConLai());
            if (sl <= 0) return false;

            return "HoatDong".equalsIgnoreCase(v.getTrangThai());
        } catch (Exception e) {
            System.out.println("Lỗi khi kiểm tra voucher hợp lệ: " + e.getMessage());
            return false;
        }
    }

    // Sử dụng voucher
    public static boolean suDungVoucher(String maVoucher) {
        try {
            Voucher v = Voucher.getVoucherById(maVoucher);
            if (v == null || !kiemTraVoucherHopLe(maVoucher)) return false;

            int sl = Integer.parseInt(v.getSoLuongConLai());
            sl--;
            v.setSoLuongConLai(String.valueOf(sl));
            if (sl <= 0) v.setTrangThai("HetHang");

            return true;
        } catch (Exception e) {
            System.out.println("Lỗi khi sử dụng voucher: " + e.getMessage());
            return false;
        }
    }

    // Thống kê
    public static boolean thongKeVoucher() {
        try {
            ArrayList<Voucher> danhSach = Voucher.Read();
            if (danhSach.isEmpty()) {
                System.out.println("Không có voucher để thống kê.");
                return false;
            }

            int hoatDong = 0, hetHang = 0, hetHan = 0;
            int tongGiamGia = 0;
            LocalDateTime now = LocalDateTime.now();

            for (Voucher v : danhSach) {
                if ("HoatDong".equalsIgnoreCase(v.getTrangThai())) hoatDong++;
                else if ("HetHang".equalsIgnoreCase(v.getTrangThai())) hetHang++;
                if (now.isAfter(v.getNgayKetThuc())) hetHan++;
                tongGiamGia += v.getPhanTramGiamGia();
            }

            System.out.println("=== THỐNG KÊ VOUCHER ===");
            System.out.println("Voucher hoạt động: " + hoatDong);
            System.out.println("Voucher hết hàng: " + hetHang);
            System.out.println("Voucher hết hạn: " + hetHan);
            System.out.println("Tổng phần trăm giảm giá: " + tongGiamGia + "%");
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi thống kê voucher: " + e.getMessage());
            return false;
        }
    }
}
