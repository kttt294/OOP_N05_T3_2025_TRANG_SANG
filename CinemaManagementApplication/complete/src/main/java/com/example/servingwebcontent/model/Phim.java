package com.example.servingwebcontent.model;
import java.util.ArrayList;

public class Phim {
    private String maPhim;
    private String tenPhim;
    private String theLoai;
    private int thoiLuong; // đơn vị: phút
    private String ngonNgu;
    private int gioiHanTuoi;
    private String moTa = "";
    
    public Phim() {}

    public Phim(String maPhim, String tenPhim, String theLoai, int thoiLuong, String ngonNgu, int gioiHanTuoi, String moTa) {
        this.maPhim = maPhim;
        this.tenPhim = tenPhim;
        this.theLoai = theLoai;
        setThoiLuong(thoiLuong);
        this.ngonNgu = ngonNgu;
        setGioiHanTuoi(gioiHanTuoi);
        this.moTa = moTa;
    }

    public Phim(String maPhim, String tenPhim){
        this.maPhim = maPhim;
        this.tenPhim = tenPhim;
    }

    public String getMaPhim() {
        return maPhim;
    }

    public void setMaPhim(String maPhim) {
        this.maPhim = maPhim;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public int getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(int thoiLuong) {
        if (thoiLuong > 0) this.thoiLuong = thoiLuong;
        else throw new IllegalArgumentException("Thời lượng phải lớn hơn 0");
    }

    public String getNgonNgu() {
        return ngonNgu;
    }

    public void setNgonNgu(String ngonNgu) {
        this.ngonNgu = ngonNgu;
    }

    public int getGioiHanTuoi() {
        return gioiHanTuoi;
    }

    public void setGioiHanTuoi(int gioiHanTuoi) {
        if (gioiHanTuoi >= 0) this.gioiHanTuoi = gioiHanTuoi;
        else throw new IllegalArgumentException("Giới hạn tuổi phải lớn hơn hoặc bằng 0");
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    private static ArrayList<Phim> danhSachPhim = new ArrayList<>();

    // CRUD
    public static void Create(Phim phim) {
        if (phim.getMaPhim() == null || phim.getMaPhim().trim().isEmpty() ||
            phim.getTenPhim() == null || phim.getTenPhim().trim().isEmpty() ||
            phim.getTheLoai() == null || phim.getTheLoai().trim().isEmpty() ||
            phim.getNgonNgu() == null || phim.getNgonNgu().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin phim không được để trống.");
            return;
        }
        danhSachPhim.add(phim);
        System.out.println("Đã thêm phim thành công.");
    }

    // Read toàn bộ danh sách phim
    public static ArrayList<Phim> Read() {
        if (danhSachPhim.isEmpty()) {
            System.out.println("Danh sách phim trống.");
            return new ArrayList<>();
        }
        System.out.println("Tổng số phim: " + danhSachPhim.size());
        return new ArrayList<>(danhSachPhim);
    }
    
    // Read phim theo mã
    public static void Read(String maPhim) {
        if (danhSachPhim.isEmpty()) {
            System.out.println("Danh sách phim trống.");
        }
        Phim p = getPhimById(maPhim);
        if (p != null) {
            p.hienThiThongTin();
        } else {
            System.out.println("Không tìm thấy phim với mã: " + maPhim);
        }
    }

    public static void Update(String maPhim, Phim phim) {
        if (phim.getMaPhim() == null || phim.getMaPhim().trim().isEmpty() ||
            phim.getTenPhim() == null || phim.getTenPhim().trim().isEmpty() ||
            phim.getTheLoai() == null || phim.getTheLoai().trim().isEmpty() ||
            phim.getNgonNgu() == null || phim.getNgonNgu().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin phim không được để trống.");
            return;
        }
        int index = getPhimIndexById(maPhim);
        if (index != -1) {
            phim.setMaPhim(maPhim);
            danhSachPhim.set(index, phim);
            System.out.println("Cập nhật thông tin phim thành công.");
        } else {
            System.out.println("Không tìm thấy phim với mã đã nhập.");
        }
    }

    public static void Delete(String maPhim) {
        Phim p = getPhimById(maPhim);
        if (p != null) {
            danhSachPhim.remove(p);
            System.out.println("Xoá phim thành công.");
        } else {
            System.out.println("Không tìm thấy phim với mã đã nhập.");
        }
    }

    public static Phim getPhimById(String maPhim) {
        return danhSachPhim.stream()
                .filter(p -> p.getMaPhim().equalsIgnoreCase(maPhim))
                .findFirst()
                .orElse(null);
    }

    public static int getPhimIndexById(String maPhim) {
        for (int i = 0; i < danhSachPhim.size(); i++) {
            if (danhSachPhim.get(i).getMaPhim().equalsIgnoreCase(maPhim)) {
                return i;
            }
        }
        return -1;
    }

    public static ArrayList<Phim> timKiemTheoTen(String tenPhim) {
        ArrayList<Phim> ketQua = new ArrayList<>();
        for (Phim phim : danhSachPhim) {
            if (phim.getTenPhim().toLowerCase().contains(tenPhim.toLowerCase())) {
                ketQua.add(phim);
            }
        }
        return ketQua;
    }

    public static ArrayList<Phim> timKiemTheoTheLoai(String theLoai) {
        ArrayList<Phim> ketQua = new ArrayList<>();
        for (Phim phim : danhSachPhim) {
            if (phim.getTheLoai().toLowerCase().contains(theLoai.toLowerCase())) {
                ketQua.add(phim);
            }
        }
        return ketQua;
    }

    public static void thongKePhim() {
        System.out.println("=== THỐNG KÊ PHIM ===");
        System.out.println("Tổng số phim: " + danhSachPhim.size());
        
        // Thống kê theo thể loại
        java.util.Map<String, Integer> theLoaiCount = new java.util.HashMap<>();
        for (Phim phim : danhSachPhim) {
            String theLoai = phim.getTheLoai();
            theLoaiCount.put(theLoai, theLoaiCount.getOrDefault(theLoai, 0) + 1);
        }
        
        System.out.println("Thống kê theo thể loại:");
        for (java.util.Map.Entry<String, Integer> entry : theLoaiCount.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue() + " phim");
        }
        
        // Thống kê theo ngôn ngữ
        java.util.Map<String, Integer> ngonNguCount = new java.util.HashMap<>();
        for (Phim phim : danhSachPhim) {
            String ngonNgu = phim.getNgonNgu();
            ngonNguCount.put(ngonNgu, ngonNguCount.getOrDefault(ngonNgu, 0) + 1);
        }
        
        System.out.println("Thống kê theo ngôn ngữ:");
        for (java.util.Map.Entry<String, Integer> entry : ngonNguCount.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue() + " phim");
        }
        
        System.out.println("=====================");
    }

    // Hiển thị thông tin chi tiết của một phim
    public void hienThiThongTin() {
        System.out.println("=== THÔNG TIN PHIM ===");
        System.out.println("Mã phim: " + maPhim);
        System.out.println("Tên phim: " + tenPhim);
        System.out.println("Thể loại: " + theLoai);
        System.out.println("Thời lượng: " + thoiLuong + " phút");
        System.out.println("Ngôn ngữ: " + ngonNgu);
        System.out.println("Giới hạn tuổi: " + gioiHanTuoi + "+");
        System.out.println("Mô tả: " + moTa);
        System.out.println("======================");
    }
}