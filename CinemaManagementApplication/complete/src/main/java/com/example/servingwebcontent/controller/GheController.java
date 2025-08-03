package com.example.servingwebcontent.controller;

import java.util.ArrayList;
import com.example.servingwebcontent.model.Ghe;

public class GheController {

    // Tạo ghế mới
    public static boolean taoGhe(Ghe ghe) {
        try {
            if (ghe == null) {
                throw new IllegalArgumentException("Ghế không được null!");
            }
            if (ghe.getMaGhe() == null || ghe.getMaGhe().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã ghế không được để trống!");
            }

            Ghe.Create(ghe);
            System.out.println("Tạo ghế thành công!");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Cập nhật ghế
    public static boolean capNhatGhe(String maGhe, Ghe gheMoi) {
        try {
            if (maGhe == null || maGhe.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã ghế không được để trống!");
            }
            if (gheMoi == null) {
                throw new IllegalArgumentException("Thông tin ghế mới không được null!");
            }

            Ghe gheCu = Ghe.getGheByMaGhe(maGhe);
            if (gheCu == null) {
                System.out.println("Không tìm thấy ghế với mã: " + maGhe);
                return false;
            }

            Ghe.Update(maGhe, gheMoi);
            System.out.println("Cập nhật ghế thành công!");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Xóa ghế
    public static boolean xoaGhe(String maGhe) {
        try {
            if (maGhe == null || maGhe.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã ghế không được để trống!");
            }

            Ghe ghe = Ghe.getGheByMaGhe(maGhe);
            if (ghe == null) {
                System.out.println("Không tìm thấy ghế với mã: " + maGhe);
                return false;
            }

            Ghe.Delete(maGhe);
            System.out.println("Xóa ghế thành công!");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Xem thông tin ghế
    public static boolean xemThongTinGhe(String maGhe) {
        try {
            if (maGhe == null || maGhe.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã ghế không được để trống!");
            }

            Ghe.Read(maGhe);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Xem tất cả ghế
    public static boolean xemTatCaGhe() {
        try {
            ArrayList<Ghe> danhSach = Ghe.Read();
            for (Ghe ghe : danhSach) {
                ghe.hienThiThongTin();
            }
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Tìm kiếm ghế theo mã
    public static Ghe timGheTheoMa(String maGhe) {
        try {
            if (maGhe == null || maGhe.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã ghế không được để trống!");
            }

            return Ghe.getGheByMaGhe(maGhe);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return null;
        }
    }
}
