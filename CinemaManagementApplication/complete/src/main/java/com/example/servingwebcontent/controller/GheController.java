package com.example.servingwebcontent.controller;

import java.util.ArrayList;
import com.example.servingwebcontent.model.Ghe;
import com.example.servingwebcontent.controller.GenericController;

public class GheController implements GenericController {
    
    // Tạo ghế mới
    public static boolean taoGhe(Ghe ghe) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (ghe == null) {
                throw new IllegalArgumentException("Ghế không được null!");
            }
            if (ghe.getMaGhe() == null || ghe.getMaGhe().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã ghế không được để trống!");
            }
            if (ghe.getHang() < 0) {
                throw new IllegalArgumentException("Hàng ghế không được âm!");
            }
            if (ghe.getCot() < 0) {
                throw new IllegalArgumentException("Cột ghế không được âm!");
            }
            if (ghe.getMaPhong() == null || ghe.getMaPhong().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã phòng không được để trống!");
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
            // Kiểm tra dữ liệu đầu vào
            if (maGhe == null || maGhe.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã ghế không được để trống!");
            }
            if (gheMoi == null) {
                throw new IllegalArgumentException("Thông tin ghế mới không được null!");
            }

            // Kiểm tra ghế có tồn tại không
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
            // Kiểm tra dữ liệu đầu vào
            if (maGhe == null || maGhe.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã ghế không được để trống!");
            }

            // Kiểm tra ghế có tồn tại không
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
            // Kiểm tra dữ liệu đầu vào
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
            Ghe.Read();
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Tìm kiếm ghế theo mã
    public static Ghe timGheTheoMa(String maGhe) {
        try {
            // Kiểm tra dữ liệu đầu vào
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

    // Tìm kiếm ghế theo phòng
    public static ArrayList<Ghe> timGheTheoPhong(String maPhong) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maPhong == null || maPhong.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã phòng không được để trống!");
            }

            return Ghe.getGheByPhong(maPhong);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Tìm kiếm ghế theo suất chiếu
    public static ArrayList<Ghe> timGheTheoSuatChieu(String maSuatChieu) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maSuatChieu == null || maSuatChieu.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã suất chiếu không được để trống!");
            }

            return Ghe.getGheBySuatChieu(maSuatChieu);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Cập nhật trạng thái ghế
    public static boolean capNhatTrangThaiGhe(String maGhe, Ghe.TrangThaiGhe trangThaiMoi) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maGhe == null || maGhe.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã ghế không được để trống!");
            }
            if (trangThaiMoi == null) {
                throw new IllegalArgumentException("Trạng thái ghế không được null!");
            }

            Ghe ghe = Ghe.getGheByMaGhe(maGhe);
            if (ghe == null) {
                System.out.println("Không tìm thấy ghế với mã: " + maGhe);
                return false;
            }

            ghe.setTrangThai(trangThaiMoi);
            Ghe.Update(maGhe, ghe);
            System.out.println("Cập nhật trạng thái ghế thành công!");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Thống kê ghế
    public static boolean thongKeGhe() {
        try {
            Ghe.thongKeGhe();
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