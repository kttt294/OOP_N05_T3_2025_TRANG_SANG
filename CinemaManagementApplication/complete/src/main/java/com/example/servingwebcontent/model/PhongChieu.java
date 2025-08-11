package com.example.servingwebcontent.model;
import java.util.ArrayList;

public class PhongChieu {
    private String maPhong;
    private String tenPhong;
    private int soHangGhe;
    private int soCotGhe;

    public PhongChieu(){}

    public PhongChieu(String maPhong, String tenPhong) {
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
    }

    public PhongChieu(String maPhong, String tenPhong, int soHangGhe, int soCotGhe) {
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
        setSoHangGhe(soHangGhe);
        setSoCotGhe(soCotGhe);
    }

    public void setTenPhong(String tenPhong){
        this.tenPhong = tenPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setMaPhong(String maPhong){
        this.maPhong = maPhong;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setSoHangGhe(int soHang){
        if (soHang > 0) this.soHangGhe = soHang;
        else throw new IllegalArgumentException("Số hàng ghế phải lớn hơn 0");
    }

    public int getSoHangGhe() {
        return soHangGhe;
    }

    public void setSoCotGhe(int soCot){
        if (soCot > 0) this.soCotGhe = soCot;
        else throw new IllegalArgumentException("Số cột ghế phải lớn hơn 0");
    }

    public int getSoCotGhe() {
        return soCotGhe;
    }

    private static ArrayList<PhongChieu> danhSachPhong = new ArrayList<>();
    
    // CRUD static
    public static void Create(PhongChieu phong) {
        if (phong == null || phong.getMaPhong() == null || phong.getMaPhong().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin phòng chiếu không được để trống.");
            return;
        }
        if (getPhongByMa(phong.getMaPhong()) != null) {
            System.out.println("Lỗi: Phòng chiếu đã tồn tại.");
            return;
        }
        danhSachPhong.add(phong);
        System.out.println("Đã thêm phòng chiếu thành công.");
    }

    // Read toàn bộ danh sách phòng chiếu
    public static ArrayList<PhongChieu> Read() {
        if (danhSachPhong.isEmpty()) {
            System.out.println("Danh sách phòng chiếu trống.");
            return new ArrayList<>();
        }
        System.out.println("Tổng số phòng chiếu: " + danhSachPhong.size());
        return new ArrayList<>(danhSachPhong);
    }
    
    // Read phòng chiếu theo mã
    public static void Read(String maPhong) {
        if (danhSachPhong.isEmpty()) {
            System.out.println("Danh sách phòng chiếu trống.");
            return;
        }
        PhongChieu phong = getPhongByMa(maPhong);
        if (phong != null) {
            phong.hienThiThongTin();
        } else {
            System.out.println("Không tìm thấy phòng chiếu với mã: " + maPhong);
        }
    }

    public static void Update(String maPhong, PhongChieu phong) {
        if (phong == null || phong.getMaPhong() == null || phong.getMaPhong().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin phòng chiếu không được để trống.");
            return;
        }
        int index = getPhongIndexByMa(maPhong);
        if (index != -1) {
            phong.setMaPhong(maPhong);
            danhSachPhong.set(index, phong);
            System.out.println("Cập nhật thông tin phòng chiếu thành công.");
        } else {
            System.out.println("Không tìm thấy phòng chiếu với mã đã nhập.");
        }
    }

    public static void Delete(String maPhong) {
        int index = getPhongIndexByMa(maPhong);
        if (index != -1) {
            danhSachPhong.remove(index);
            System.out.println("Đã xóa phòng chiếu thành công.");
        } else {
            System.out.println("Không tìm thấy phòng chiếu với mã đã nhập.");
        }
    }

    public static PhongChieu getPhongByMa(String maPhong) {
        for (PhongChieu phong : danhSachPhong) {
            if (phong.getMaPhong().equalsIgnoreCase(maPhong)) return phong;
        }
        return null;
    }

    private static int getPhongIndexByMa(String maPhong) {
        for (int i = 0; i < danhSachPhong.size(); i++) {
            if (danhSachPhong.get(i).getMaPhong().equalsIgnoreCase(maPhong)) return i;
        }
        return -1;
    }
    
    // Hiển thị thông tin chi tiết của một phòng chiếu
    public void hienThiThongTin() {
        System.out.println("=== THÔNG TIN PHÒNG CHIẾU ===");
        System.out.println("Mã phòng: " + maPhong);
        System.out.println("Tên phòng: " + tenPhong);
        System.out.println("Số hàng ghế: " + soHangGhe);
        System.out.println("Số cột ghế: " + soCotGhe);
        System.out.println("Tổng số ghế: " + (soHangGhe * soCotGhe));
        System.out.println("=============================");
    }

    public static ArrayList<PhongChieu> timKiemTheoTen(String tenPhong) {
        ArrayList<PhongChieu> ketQua = new ArrayList<>();
        for (PhongChieu phong : danhSachPhong) {
            if (phong.getTenPhong().toLowerCase().contains(tenPhong.toLowerCase())) {
                ketQua.add(phong);
            }
        }
        return ketQua;
    }

    public static void thongKePhongChieu() {
        System.out.println("=== THỐNG KÊ PHÒNG CHIẾU ===");
        System.out.println("Tổng số phòng chiếu: " + danhSachPhong.size());
        
        int tongGhe = 0;
        for (PhongChieu phong : danhSachPhong) {
            tongGhe += phong.getSoHangGhe() * phong.getSoCotGhe();
        }
        
        System.out.println("Tổng số ghế: " + tongGhe);
        
        // Thống kê phòng theo kích thước
        int nho = 0, vua = 0, lon = 0;
        for (PhongChieu phong : danhSachPhong) {
            int soGhe = phong.getSoHangGhe() * phong.getSoCotGhe();
            if (soGhe < 50) nho++;
            else if (soGhe < 100) vua++;
            else lon++;
        }
        
        System.out.println("Phòng nhỏ (<50 ghế): " + nho);
        System.out.println("Phòng vừa (50-100 ghế): " + vua);
        System.out.println("Phòng lớn (>100 ghế): " + lon);
        System.out.println("=============================");
    }
}
