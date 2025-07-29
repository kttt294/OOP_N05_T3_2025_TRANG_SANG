package com.example.servingwebcontent.controller;

import java.util.ArrayList;
import com.example.servingwebcontent.controller.GenericController;
import com.example.servingwebcontent.model.DanhGia;

public class DanhGiaController implements GenericController {
    
    // Tạo đánh giá mới
    public static boolean taoDanhGia(DanhGia danhGia) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (danhGia == null) {
                throw new IllegalArgumentException("Đánh giá không được null!");
            }
            if (danhGia.getMaDanhGia() == null || danhGia.getMaDanhGia().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã đánh giá không được để trống!");
            }
            if (danhGia.getCCCD() == null || danhGia.getCCCD().trim().isEmpty()) {
                throw new IllegalArgumentException("CCCD không được để trống!");
            }
            if (danhGia.getNoiDung() == null || danhGia.getNoiDung().trim().isEmpty()) {
                throw new IllegalArgumentException("Nội dung đánh giá không được để trống!");
            }
            if (danhGia.getDiem() < 1 || danhGia.getDiem() > 5) {
                throw new IllegalArgumentException("Điểm đánh giá phải từ 1-5!");
            }

            DanhGia.Create(danhGia);
            System.out.println("Tạo đánh giá thành công!");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Cập nhật đánh giá
    public static boolean capNhatDanhGia(String maDanhGia, DanhGia danhGiaMoi) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maDanhGia == null || maDanhGia.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã đánh giá không được để trống!");
            }
            if (danhGiaMoi == null) {
                throw new IllegalArgumentException("Thông tin đánh giá mới không được null!");
            }

            // Kiểm tra đánh giá có tồn tại không
            DanhGia danhGiaCu = DanhGia.getDanhGiaByMaDanhGia(maDanhGia);
            if (danhGiaCu == null) {
                System.out.println("Không tìm thấy đánh giá với mã: " + maDanhGia);
                return false;
            }

            DanhGia.Update(maDanhGia, danhGiaMoi);
            System.out.println("Cập nhật đánh giá thành công!");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Xóa đánh giá
    public static boolean xoaDanhGia(String maDanhGia) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maDanhGia == null || maDanhGia.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã đánh giá không được để trống!");
            }

            // Kiểm tra đánh giá có tồn tại không
            DanhGia danhGia = DanhGia.getDanhGiaByMaDanhGia(maDanhGia);
            if (danhGia == null) {
                System.out.println("Không tìm thấy đánh giá với mã: " + maDanhGia);
                return false;
            }

            DanhGia.Delete(maDanhGia);
            System.out.println("Xóa đánh giá thành công!");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Xem thông tin đánh giá
    public static boolean xemThongTinDanhGia(String maDanhGia) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maDanhGia == null || maDanhGia.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã đánh giá không được để trống!");
            }

            DanhGia.Read(maDanhGia);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Xem tất cả đánh giá
    public static boolean xemTatCaDanhGia() {
        try {
            DanhGia.Read();
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Tìm kiếm đánh giá theo mã
    public static DanhGia timDanhGiaTheoMa(String maDanhGia) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maDanhGia == null || maDanhGia.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã đánh giá không được để trống!");
            }

            return DanhGia.getDanhGiaByMaDanhGia(maDanhGia);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return null;
        }
    }

    // Tìm kiếm đánh giá theo khách hàng
    public static ArrayList<DanhGia> timDanhGiaTheoKhachHang(String CCCD) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (CCCD == null || CCCD.trim().isEmpty()) {
                throw new IllegalArgumentException("CCCD không được để trống!");
            }

            return DanhGia.getDanhGiaByKhachHang(CCCD);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Tính điểm trung bình
    public static double tinhDiemTrungBinh() {
        try {
            return DanhGia.tinhDiemTrungBinh();
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return 0.0;
        }
    }

    // Thống kê đánh giá
    public static boolean thongKeDanhGia() {
        try {
            DanhGia.thongKeDanhGia();
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Object them(Object obj) { return null; }
    @Override
    public Object sua(Object obj) { return null; }
    @Override
    public boolean xoa(Object obj) { return false; }
    @Override
    public Object hienThi(Object obj) { return null; }
} 