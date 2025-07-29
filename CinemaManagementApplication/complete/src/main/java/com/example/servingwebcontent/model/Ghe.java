package com.example.servingwebcontent.model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Ghe {
    public enum LoaiGhe { THUONG, VIP, COUPLE }
    public enum TrangThaiGhe { TRONG, DA_DAT, DANG_GIU, KHOA }
    public enum TrangThaiGiu { DANG_GIU, DA_HUY, DA_DAT_THANH_CONG, KHONG_GIU }
    private String maGhe;
    private int hang;
    private int cot;
    private LoaiGhe loaiGheMacDinh;
    private String maPhong;
    private String maSuatChieu;
    private double giaGhe;
    private TrangThaiGhe trangThai;
    private String loaiGheHienTai;
    private String moTa;
    private String maHold;
    private String CCCD;
    private LocalDateTime thoiGianBatDauHold;
    private LocalDateTime thoiGianKetThucHold;
    private TrangThaiGiu trangThaiGiu;

    private static ArrayList<Ghe> danhSachGhe = new ArrayList<>();

    public Ghe() {}

    // Constructor cơ bản cho ghế
    public Ghe(String maGhe, int hang, int cot, LoaiGhe loaiGheMacDinh, String maPhong) {
        setMaGhe(maGhe);
        setHang(hang);
        setCot(cot);
        setLoaiGheMacDinh(loaiGheMacDinh);
        setMaPhong(maPhong);
        this.trangThai = TrangThaiGhe.TRONG;
        this.loaiGheHienTai = loaiGheMacDinh.toString();
    }

    // Constructor đầy đủ cho ghế suất chiếu
    public Ghe(String maGhe, int hang, int cot, LoaiGhe loaiGheMacDinh, String maPhong,
               String maSuatChieu, double giaGhe, TrangThaiGhe trangThai, String loaiGheHienTai, String moTa) {
        this(maGhe, hang, cot, loaiGheMacDinh, maPhong);
        setMaSuatChieu(maSuatChieu);
        setGiaGhe(giaGhe);
        setTrangThai(trangThai);
        setLoaiGheHienTai(loaiGheHienTai);
        setMoTa(moTa);
    }

    // Constructor đầy đủ cho giữ ghế
    public Ghe(String maGhe, int hang, int cot, LoaiGhe loaiGheMacDinh, String maPhong,
               String maSuatChieu, double giaGhe, TrangThaiGhe trangThai, String loaiGheHienTai, String moTa,
               String maHold, String CCCD, LocalDateTime thoiGianBatDauHold, 
               LocalDateTime thoiGianKetThucHold, TrangThaiGiu trangThaiGiu) {
        this(maGhe, hang, cot, loaiGheMacDinh, maPhong, maSuatChieu, giaGhe, trangThai, loaiGheHienTai, moTa);
        setMaHold(maHold);
        setCCCD(CCCD);
        setThoiGianBatDauHold(thoiGianBatDauHold);
        setThoiGianKetThucHold(thoiGianKetThucHold);
        setTrangThaiGiu(trangThaiGiu);
    }

    // Getters và Setters cơ bản
    public String getMaGhe() { return maGhe; }
    public void setMaGhe(String maGhe) {
        if (maGhe == null || maGhe.trim().isEmpty())
            throw new IllegalArgumentException("Mã ghế không được để trống!");
        this.maGhe = maGhe.trim();
    }
    public int getHang() { return hang; }
    public void setHang(int hang) {
        if (hang < 0) throw new IllegalArgumentException("Hàng ghế không hợp lệ!");
        this.hang = hang;
    }
    public int getCot() { return cot; }
    public void setCot(int cot) {
        if (cot < 0) throw new IllegalArgumentException("Cột ghế không hợp lệ!");
        this.cot = cot;
    }
    public LoaiGhe getLoaiGheMacDinh() { return loaiGheMacDinh; }
    public void setLoaiGheMacDinh(LoaiGhe loaiGheMacDinh) {
        this.loaiGheMacDinh = (loaiGheMacDinh != null) ? loaiGheMacDinh : LoaiGhe.THUONG;
    }
    public String getMaPhong() { return maPhong; }
    public void setMaPhong(String maPhong) {
        this.maPhong = (maPhong != null) ? maPhong.trim() : "";
    }

    // Getters và Setters cho GheSuatChieu
    public String getMaSuatChieu() { return maSuatChieu; }
    public void setMaSuatChieu(String maSuatChieu) {
        this.maSuatChieu = (maSuatChieu != null) ? maSuatChieu.trim() : "";
    }
    public double getGiaGhe() { return giaGhe; }
    public void setGiaGhe(double giaGhe) {
        if (giaGhe < 0) throw new IllegalArgumentException("Giá ghế không hợp lệ!");
        this.giaGhe = giaGhe;
    }
    public TrangThaiGhe getTrangThai() { return trangThai; }
    public void setTrangThai(TrangThaiGhe trangThai) {
        this.trangThai = (trangThai != null) ? trangThai : TrangThaiGhe.TRONG;
    }
    public String getLoaiGheHienTai() { return loaiGheHienTai; }
    public void setLoaiGheHienTai(String loaiGheHienTai) {
        this.loaiGheHienTai = (loaiGheHienTai != null) ? loaiGheHienTai.trim() : loaiGheMacDinh.toString();
    }
    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) {
        this.moTa = (moTa != null) ? moTa.trim() : "";
    }

    // Getters và Setters cho GiuGhe
    public String getMaHold() { return maHold; }
    public void setMaHold(String maHold) { this.maHold = maHold; }
    public String getCCCD() { return CCCD; }
    public void setCCCD(String CCCD) { this.CCCD = CCCD; }
    public LocalDateTime getThoiGianBatDauHold() { return thoiGianBatDauHold; }
    public void setThoiGianBatDauHold(LocalDateTime thoiGianBatDauHold) { this.thoiGianBatDauHold = thoiGianBatDauHold; }
    public LocalDateTime getThoiGianKetThucHold() { return thoiGianKetThucHold; }
    public void setThoiGianKetThucHold(LocalDateTime thoiGianKetThucHold) { this.thoiGianKetThucHold = thoiGianKetThucHold; }
    public TrangThaiGiu getTrangThaiGiu() { return trangThaiGiu; }
    public void setTrangThaiGiu(TrangThaiGiu trangThaiGiu) { this.trangThaiGiu = trangThaiGiu; }

    // Phương thức kiểm tra trạng thái
    public boolean isTrong() { return trangThai == TrangThaiGhe.TRONG; }
    public boolean isDaDat() { return trangThai == TrangThaiGhe.DA_DAT; }
    public boolean isDangGiu() { return trangThai == TrangThaiGhe.DANG_GIU; }
    public boolean isKhoa() { return trangThai == TrangThaiGhe.KHOA; }
    public boolean isDangGiuGhe() { return trangThaiGiu == TrangThaiGiu.DANG_GIU; }

    // Phương thức thay đổi trạng thái
    public void datGhe() { 
        this.trangThai = TrangThaiGhe.DA_DAT; 
        if (trangThaiGiu == TrangThaiGiu.DANG_GIU) {
            this.trangThaiGiu = TrangThaiGiu.DA_DAT_THANH_CONG;
        }
    }
    public void giuGhe(String CCCD, LocalDateTime thoiGianKetThuc) {
        if (this.trangThai == TrangThaiGhe.TRONG) {
            this.trangThaiGiu = TrangThaiGiu.DANG_GIU;
            this.CCCD = CCCD;
            this.thoiGianBatDauHold = LocalDateTime.now();
            this.thoiGianKetThucHold = thoiGianKetThuc;
        }
    }
    public void huyGiuGhe() {
        this.trangThaiGiu = TrangThaiGiu.KHONG_GIU;
        this.CCCD = null;
        this.thoiGianBatDauHold = null;
        this.thoiGianKetThucHold = null;
    }

    // CRUD static
    public static void Create(Ghe ghe) {
        if (ghe == null || ghe.getMaGhe() == null || ghe.getMaGhe().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin ghế không được để trống.");
            return;
        }
        if (getGheByMaGhe(ghe.getMaGhe()) != null) {
            System.out.println("Lỗi: Ghế đã tồn tại.");
            return;
        }
        danhSachGhe.add(ghe);
        System.out.println("Đã thêm ghế thành công.");
    }
    
    // Read toàn bộ danh sách ghế
    public static ArrayList<Ghe> Read() {
        if (danhSachGhe.isEmpty()) {
            System.out.println("Danh sách ghế trống.");
            return new ArrayList<>();
        }
        System.out.println("Tổng số ghế: " + danhSachGhe.size());
        return new ArrayList<>(danhSachGhe);
    }
    
    // Read ghế theo mã
    public static void Read(String maGhe) {
        if (danhSachGhe.isEmpty()) {
            System.out.println("Danh sách ghế trống.");
            return;
        }
        Ghe ghe = getGheByMaGhe(maGhe);
        if (ghe != null) {
            ghe.hienThiThongTin();
        } else {
            System.out.println("Không tìm thấy ghế với mã: " + maGhe);
        }
    }
    
    public static void Update(String maGhe, Ghe ghe) {
        if (ghe == null || ghe.getMaGhe() == null || ghe.getMaGhe().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin ghế không được để trống.");
            return;
        }
        int idx = getGheIndexByMaGhe(maGhe);
        if (idx == -1) {
            System.out.println("Không tìm thấy ghế với mã: " + maGhe);
            return;
        }
        ghe.setMaGhe(maGhe);
        danhSachGhe.set(idx, ghe);
        System.out.println("Cập nhật ghế thành công.");
    }
    
    public static void Delete(String maGhe) {
        int idx = getGheIndexByMaGhe(maGhe);
        if (idx == -1) {
            System.out.println("Không tìm thấy ghế với mã: " + maGhe);
            return;
        }
        danhSachGhe.remove(idx);
        System.out.println("Đã xóa ghế thành công.");
    }
    
    public static Ghe getGheByMaGhe(String maGhe) {
        for (Ghe ghe : danhSachGhe) {
            if (ghe.getMaGhe().equalsIgnoreCase(maGhe)) return ghe;
        }
        return null;
    }
    
    public static int getGheIndexByMaGhe(String maGhe) {
        for (int i = 0; i < danhSachGhe.size(); i++) {
            if (danhSachGhe.get(i).getMaGhe().equalsIgnoreCase(maGhe)) return i;
        }
        return -1;
    }

    // Phương thức tìm kiếm theo suất chiếu
    public static ArrayList<Ghe> getGheBySuatChieu(String maSuatChieu) {
        ArrayList<Ghe> result = new ArrayList<>();
        for (Ghe ghe : danhSachGhe) {
            if (ghe.getMaSuatChieu() != null && ghe.getMaSuatChieu().equals(maSuatChieu)) {
                result.add(ghe);
            }
        }
        return result;
    }

    // Phương thức tìm kiếm theo khách hàng đang giữ
    public static ArrayList<Ghe> getGheDangGiuByKhachHang(String CCCD) {
        ArrayList<Ghe> ketQua = new ArrayList<>();
        for (Ghe ghe : danhSachGhe) {
            if (ghe.isDangGiuGhe() && ghe.getCCCD() != null &&
                ghe.getCCCD().equals(CCCD)) {
                ketQua.add(ghe);
            }
        }
        return ketQua;
    }

    public static ArrayList<Ghe> getGheByMaPhong(String maPhong) {
        ArrayList<Ghe> ketQua = new ArrayList<>();
        for (Ghe ghe : danhSachGhe) {
            if (ghe.getMaPhong().equalsIgnoreCase(maPhong)) {
                ketQua.add(ghe);
            }
        }
        return ketQua;
    }

    public static void thongKeGhe() {
        System.out.println("=== THỐNG KÊ GHẾ ===");
        System.out.println("Tổng số ghế: " + danhSachGhe.size());
        
        int trong = 0, daDat = 0, dangGiu = 0, khoa = 0;
        int thuong = 0, vip = 0, couple = 0;
        
        for (Ghe ghe : danhSachGhe) {
            // Thống kê theo trạng thái
            switch (ghe.getTrangThai()) {
                case TRONG: trong++; break;
                case DA_DAT: daDat++; break;
                case DANG_GIU: dangGiu++; break;
                case KHOA: khoa++; break;
            }
            
            // Thống kê theo loại ghế
            switch (ghe.getLoaiGheMacDinh()) {
                case THUONG: thuong++; break;
                case VIP: vip++; break;
                case COUPLE: couple++; break;
            }
        }
        
        System.out.println("Ghế trống: " + trong);
        System.out.println("Ghế đã đặt: " + daDat);
        System.out.println("Ghế đang giữ: " + dangGiu);
        System.out.println("Ghế khóa: " + khoa);
        System.out.println("Ghế thường: " + thuong);
        System.out.println("Ghế VIP: " + vip);
        System.out.println("Ghế couple: " + couple);
        System.out.println("===================");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ghe ghe = (Ghe) o;
        return Objects.equals(maGhe, ghe.maGhe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maGhe);
    }

    public void hienThiThongTin() {
        System.out.println("=== THÔNG TIN GHẾ ===");
        System.out.println("Mã ghế: " + maGhe);
        System.out.println("Vị trí: Hàng " + hang + ", Cột " + cot);
        System.out.println("Loại ghế mặc định: " + loaiGheMacDinh);
        System.out.println("Mã phòng: " + maPhong);
        
        if (maSuatChieu != null && !maSuatChieu.isEmpty()) {
            System.out.println("Mã suất chiếu: " + maSuatChieu);
            System.out.println("Giá ghế: " + giaGhe + " VNĐ");
            System.out.println("Trạng thái: " + trangThai);
            System.out.println("Loại ghế hiện tại: " + loaiGheHienTai);
            if (moTa != null && !moTa.isEmpty()) {
                System.out.println("Mô tả: " + moTa);
            }
        }
        
        if (maHold != null && !maHold.isEmpty()) {
            System.out.println("Mã giữ ghế: " + maHold);
            System.out.println("Mã khách hàng: " + CCCD);
            System.out.println("Thời gian bắt đầu giữ: " + thoiGianBatDauHold);
            System.out.println("Thời gian kết thúc giữ: " + thoiGianKetThucHold);
            System.out.println("Trạng thái giữ: " + trangThaiGiu);
        }
        System.out.println("===================");
    }
}
