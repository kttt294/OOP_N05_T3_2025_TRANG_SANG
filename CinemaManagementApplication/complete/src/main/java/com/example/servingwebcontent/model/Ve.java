package com.example.servingwebcontent.model;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Ve {
    public enum TrangThaiVe { CHUA_THANH_TOAN, DA_THANH_TOAN, DA_HUY }
    public enum PhuongThucThanhToan { TIEN_MAT, CHUYEN_KHOAN }

    private String maVe;
    private String CCCD;
    private String maSuatChieu;
    private String maGhe;
    private int giaVe; // đơn vị đồng
    private TrangThaiVe trangThai;
    
    // Thuộc tính cho hóa đơn
    private String maHoaDon;
    private DoAn doAn;
    private int tongTien;
    private LocalDateTime thoiGianThanhToan;
    private PhuongThucThanhToan phuongThucThanhToan;

    private static ArrayList<Ve> danhSachVe = new ArrayList<>();

    public Ve(){}

    // Constructor cơ bản cho vé
    public Ve(String maVe, String CCCD, String maSuatChieu, String maGhe, int giaVe) {
        this.maVe = maVe;
        this.CCCD = CCCD;
        this.maSuatChieu = maSuatChieu;
        this.maGhe = maGhe;
        setGiaVe(giaVe);
        this.trangThai = TrangThaiVe.CHUA_THANH_TOAN;
    }

    // Constructor đầy đủ cho vé và hóa đơn
    public Ve(String maVe, String CCCD, String maSuatChieu, String maGhe, int giaVe,
              String maHoaDon, DoAn doAn, int tongTien, LocalDateTime thoiGianThanhToan, 
              PhuongThucThanhToan phuongThucThanhToan) {
        this(maVe, CCCD, maSuatChieu, maGhe, giaVe);
        setMaHoaDon(maHoaDon);
        setDoAn(doAn);
        setTongTien(tongTien);
        setThoiGianThanhToan(thoiGianThanhToan);
        setPhuongThucThanhToan(phuongThucThanhToan);
        this.trangThai = TrangThaiVe.DA_THANH_TOAN;
    }

    public String getMaVe() { return maVe; }
    public void setMaVe(String maVe) { this.maVe = maVe; }
    public String getCCCD() { return CCCD; }
    public void setCCCD(String CCCD) { this.CCCD = CCCD; }
    public String getMaSuatChieu() { return maSuatChieu; }
    public void setMaSuatChieu(String maSuatChieu) { this.maSuatChieu = maSuatChieu; }
    public String getMaGhe() { return maGhe; }
    public void setMaGhe(String maGhe) { this.maGhe = maGhe; }
    public double getGiaVe() { return giaVe; }
    public void setGiaVe(int giaVe) { 
        if (giaVe > 0) this.giaVe = giaVe; 
        else throw new IllegalArgumentException("Giá vé phải lớn hơn 0"); 
    }
    public TrangThaiVe getTrangThai() { return trangThai; }
    public void setTrangThai(TrangThaiVe trangThai) { this.trangThai = trangThai; }

    // Getters và Setters cho hóa đơn
    public String getMaHoaDon() { return maHoaDon; }
    public void setMaHoaDon(String maHoaDon) { this.maHoaDon = maHoaDon; }
    public DoAn getDoAn() { return doAn; }
    public void setDoAn(DoAn doAn) { this.doAn = doAn; }
    public int getTongTien() { return tongTien; }
    public void setTongTien(int tongTien) { 
        if (tongTien >= 0) this.tongTien = tongTien; 
        else throw new IllegalArgumentException("Tổng tiền không được âm"); 
    }
    public LocalDateTime getThoiGianThanhToan() { return thoiGianThanhToan; }
    public void setThoiGianThanhToan(LocalDateTime thoiGianThanhToan) { this.thoiGianThanhToan = thoiGianThanhToan; }
    public PhuongThucThanhToan getPhuongThucThanhToan() { return phuongThucThanhToan; }
    public void setPhuongThucThanhToan(PhuongThucThanhToan phuongThucThanhToan) { this.phuongThucThanhToan = phuongThucThanhToan; }

    // Phương thức kiểm tra trạng thái
    public boolean isChuaThanhToan() { return trangThai == TrangThaiVe.CHUA_THANH_TOAN; }
    public boolean isDaThanhToan() { return trangThai == TrangThaiVe.DA_THANH_TOAN; }
    public boolean isDaHuy() { return trangThai == TrangThaiVe.DA_HUY; }

    // Phương thức thanh toán
    public void thanhToan(DoAn doAn, PhuongThucThanhToan phuongThuc) {
        this.doAn = doAn;
        this.phuongThucThanhToan = phuongThuc;
        this.thoiGianThanhToan = LocalDateTime.now();
        
        // Tính tổng tiền
        int giaDoAn = (doAn != null) ? doAn.getGia() : 0;
        this.tongTien = this.giaVe + giaDoAn;
        
        this.trangThai = TrangThaiVe.DA_THANH_TOAN;
        this.maHoaDon = "HD" + System.currentTimeMillis(); // Tạo mã hóa đơn tự động
    }

    public void huyVe() {
        this.trangThai = TrangThaiVe.DA_HUY;
        this.doAn = null;
        this.tongTien = 0;
        this.thoiGianThanhToan = null;
        this.phuongThucThanhToan = null;
        this.maHoaDon = null;
    }

    // CRUD
    public static void Create(Ve ve) {
        if (ve.getMaVe() == null || ve.getMaVe().trim().isEmpty() ||
            ve.getCCCD() == null || ve.getCCCD().trim().isEmpty() ||
            ve.getMaSuatChieu() == null || ve.getMaSuatChieu().trim().isEmpty() ||
            ve.getMaGhe() == null || ve.getMaGhe().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin vé không được để trống.");
            return;
        }
        if (ve.getGiaVe() <= 0) {
            System.out.println("Lỗi: Giá vé phải lớn hơn 0.");
            return;
        }
        danhSachVe.add(ve);
        System.out.println("Đã thêm vé thành công.");
    }

    // Read toàn bộ danh sách vé
    public static ArrayList<Ve> Read() {
        if (danhSachVe.isEmpty()) {
            System.out.println("Danh sách vé trống.");
            return new ArrayList<>();
        }
        System.out.println("Tổng số vé: " + danhSachVe.size());
        return new ArrayList<>(danhSachVe);
    }
    
    // Read vé theo mã
    public static void Read(String maVe) {
        if (danhSachVe.isEmpty()) {
            System.out.println("Danh sách vé trống.");
            return;
        }
        Ve v = getVeById(maVe);
        if (v != null) {
            v.hienThiThongTin();
        } else {
            System.out.println("Không tìm thấy vé với mã: " + maVe);
        }
    }

    public static void Update(String maVe, Ve ve) {
        if (ve.getMaVe() == null || ve.getMaVe().trim().isEmpty() ||
            ve.getCCCD() == null || ve.getCCCD().trim().isEmpty() ||
            ve.getMaSuatChieu() == null || ve.getMaSuatChieu().trim().isEmpty() ||
            ve.getMaGhe() == null || ve.getMaGhe().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin vé không được để trống.");
            return;
        }
        if (ve.getGiaVe() <= 0) {
            System.out.println("Lỗi: Giá vé phải lớn hơn 0.");
            return;
        }
        int index = getVeIndexById(maVe);
        if (index != -1) {
            ve.setMaVe(maVe);
            danhSachVe.set(index, ve);
            System.out.println("Cập nhật thông tin vé thành công.");
        } else {
            System.out.println("Không tìm thấy vé với mã đã nhập.");
        }
    }

    public static void Delete(String maVe) {
        int index = getVeIndexById(maVe);
        if (index != -1) {
            danhSachVe.remove(index);
            System.out.println("Đã xóa vé thành công.");
        } else {
            System.out.println("Không tìm thấy vé với mã đã nhập.");
        }
    }

    public static Ve getVeById(String maVe) {
        for (Ve ve : danhSachVe) {
            if (ve.getMaVe().equalsIgnoreCase(maVe)) {
                return ve;
            }
        }
        return null;
    }

    private static int getVeIndexById(String maVe) {
        for (int i = 0; i < danhSachVe.size(); i++) {
            if (danhSachVe.get(i).getMaVe().equalsIgnoreCase(maVe)) {
                return i;
            }
        }
        return -1;
    }

    // Phương thức tìm kiếm theo khách hàng
    public static ArrayList<Ve> getVeByKhachHang(String CCCD) {
        ArrayList<Ve> result = new ArrayList<>();
        for (Ve ve : danhSachVe) {
            if (ve.getCCCD() != null && ve.getCCCD().equals(CCCD)) {
                result.add(ve);
            }
        }
        return result;
    }

    // Phương thức tìm kiếm theo suất chiếu
    public static ArrayList<Ve> getVeBySuatChieu(String maSuatChieu) {
        ArrayList<Ve> result = new ArrayList<>();
        for (Ve ve : danhSachVe) {
            if (ve.getMaSuatChieu() != null && ve.getMaSuatChieu().equals(maSuatChieu)) {
                result.add(ve);
            }
        }
        return result;
    }

    // Phương thức tìm kiếm theo trạng thái
    public static ArrayList<Ve> getVeByTrangThai(TrangThaiVe trangThai) {
        ArrayList<Ve> result = new ArrayList<>();
        for (Ve ve : danhSachVe) {
            if (ve.getTrangThai() == trangThai) {
                result.add(ve);
            }
        }
        return result;
    }

    // Phương thức tính doanh thu
    public static int tinhDoanhThu(LocalDateTime tuNgay, LocalDateTime denNgay) {
        int tongDoanhThu = 0;
        for (Ve ve : danhSachVe) {
            if (ve.isDaThanhToan() && ve.getThoiGianThanhToan() != null) {
                LocalDateTime thoiGianThanhToan = ve.getThoiGianThanhToan();
                if (thoiGianThanhToan.isAfter(tuNgay) && thoiGianThanhToan.isBefore(denNgay)) {
                    tongDoanhThu += ve.getTongTien();
                }
            }
        }
        return tongDoanhThu;
    }

    public static void thongKeVe() {
        System.out.println("=== THỐNG KÊ VÉ ===");
        System.out.println("Tổng số vé: " + danhSachVe.size());
        
        int chuaThanhToan = 0, daThanhToan = 0, daHuy = 0;
        int tongDoanhThu = 0;
        
        for (Ve ve : danhSachVe) {
            switch (ve.getTrangThai()) {
                case CHUA_THANH_TOAN: chuaThanhToan++; break;
                case DA_THANH_TOAN: 
                    daThanhToan++; 
                    tongDoanhThu += ve.getTongTien();
                    break;
                case DA_HUY: daHuy++; break;
            }
        }
        
        System.out.println("Vé chưa thanh toán: " + chuaThanhToan);
        System.out.println("Vé đã thanh toán: " + daThanhToan);
        System.out.println("Vé đã hủy: " + daHuy);
        System.out.println("Tổng doanh thu: " + tongDoanhThu + " VNĐ");
        
        // Thống kê theo phương thức thanh toán
        int tienMat = 0, chuyenKhoan = 0;
        for (Ve ve : danhSachVe) {
            if (ve.isDaThanhToan() && ve.getPhuongThucThanhToan() != null) {
                switch (ve.getPhuongThucThanhToan()) {
                    case TIEN_MAT: tienMat++; break;
                    case CHUYEN_KHOAN: chuyenKhoan++; break;
                }
            }
        }
        
        System.out.println("Thanh toán tiền mặt: " + tienMat);
        System.out.println("Thanh toán chuyển khoản: " + chuyenKhoan);
        System.out.println("=====================");
    }

    public void hienThiThongTin() {
        System.out.println("=== THÔNG TIN VÉ ===");
        System.out.println("Mã vé: " + maVe);
        System.out.println("CCCD khách hàng: " + CCCD);
        System.out.println("Mã suất chiếu: " + maSuatChieu);
        System.out.println("Mã ghế: " + maGhe);
        System.out.println("Giá vé: " + giaVe + " VNĐ");
        System.out.println("Trạng thái: " + trangThai);
        
        if (isDaThanhToan()) {
            System.out.println("=== THÔNG TIN HÓA ĐƠN ===");
            System.out.println("Mã hóa đơn: " + maHoaDon);
            if (doAn != null) {
                System.out.println("Đồ ăn: " + doAn.getTenDoAn() + " - " + doAn.getGia() + " VNĐ");
            }
            System.out.println("Tổng tiền: " + tongTien + " VNĐ");
            System.out.println("Thời gian thanh toán: " + thoiGianThanhToan);
            System.out.println("Phương thức thanh toán: " + phuongThucThanhToan);
        }
        System.out.println("=====================");
    }
}
