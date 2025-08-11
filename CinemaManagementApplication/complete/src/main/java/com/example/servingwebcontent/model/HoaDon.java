package com.example.servingwebcontent.model;

import com.example.servingwebcontent.util.DateTimeUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HoaDon {

    public enum phuongThuc { TIEN_MAT, CHUYEN_KHOAN }

    private String maHoaDon;
    private List<Ve> danhSachVe; // Danh sách vé
    private List<Voucher> danhSachVoucher; // Danh sách voucher
    private List<DoAn> danhSachDoAn; // Thay đổi từ doAn thành danhSachDoAn
    private int tongTien;
    private LocalDateTime thoiGianThanhToan;
    private phuongThuc phuongThuc;
    private String CCCD;

    public HoaDon() {
        this.danhSachVe = new ArrayList<>();
        this.danhSachVoucher = new ArrayList<>();
        this.danhSachDoAn = new ArrayList<>();
    }

    public HoaDon(String maHoaDon, List<Ve> danhSachVe, List<Voucher> danhSachVoucher, List<DoAn> danhSachDoAn, int tongTien, LocalDateTime thoiGianThanhToan, phuongThuc phuongThuc, String CCCD) {
        setMaHoaDon(maHoaDon);
        setDanhSachVe(danhSachVe);
        setDanhSachVoucher(danhSachVoucher);
        setDanhSachDoAn(danhSachDoAn);
        setTongTien(tongTien);
        setThoiGianThanhToan(thoiGianThanhToan);
        setphuongThuc(phuongThuc);
        setCCCD(CCCD);
    }

    // Constructor với vé và voucher
    public HoaDon(String maHoaDon, List<Ve> danhSachVe, List<DoAn> danhSachDoAn, int tongTien, LocalDateTime thoiGianThanhToan, phuongThuc phuongThuc, String CCCD) {
        this(maHoaDon, danhSachVe, new ArrayList<>(), danhSachDoAn, tongTien, thoiGianThanhToan, phuongThuc, CCCD);
    }

    // Constructor cũ để tương thích ngược
    public HoaDon(String maHoaDon, DoAn doAn, int tongTien, LocalDateTime thoiGianThanhToan, phuongThuc phuongThuc, String CCCD) {
        this.maHoaDon = maHoaDon;
        this.danhSachVe = new ArrayList<>();
        this.danhSachVoucher = new ArrayList<>();
        this.danhSachDoAn = new ArrayList<>();
        if (doAn != null) {
            this.danhSachDoAn.add(doAn);
        }
        this.tongTien = tongTien;
        this.thoiGianThanhToan = thoiGianThanhToan;
        this.phuongThuc = phuongThuc;
        this.CCCD = CCCD;
    }

    public String getMaHoaDon() { return maHoaDon; }
    public void setMaHoaDon(String maHoaDon) { this.maHoaDon = maHoaDon; }

    public List<Ve> getDanhSachVe() { return danhSachVe; }
    public void setDanhSachVe(List<Ve> danhSachVe) { 
        this.danhSachVe = danhSachVe != null ? danhSachVe : new ArrayList<>(); 
    }

    public List<Voucher> getDanhSachVoucher() { return danhSachVoucher; }
    public void setDanhSachVoucher(List<Voucher> danhSachVoucher) { 
        this.danhSachVoucher = danhSachVoucher != null ? danhSachVoucher : new ArrayList<>(); 
    }

    public List<DoAn> getDanhSachDoAn() { return danhSachDoAn; }
    public void setDanhSachDoAn(List<DoAn> danhSachDoAn) { 
        this.danhSachDoAn = danhSachDoAn != null ? danhSachDoAn : new ArrayList<>(); 
    }

    // Thêm phương thức để thêm vé vào hóa đơn
    public void themVe(Ve ve) {
        if (ve != null && !this.danhSachVe.contains(ve)) {
            this.danhSachVe.add(ve);
            // Cập nhật tổng tiền
            tinhTongTien();
        }
    }

    // Thêm phương thức để xóa vé khỏi hóa đơn
    public void xoaVe(Ve ve) {
        if (ve != null && this.danhSachVe.remove(ve)) {
            // Cập nhật tổng tiền
            tinhTongTien();
        }
    }

    // Thêm phương thức để xóa vé theo mã vé
    public void xoaVeTheoMa(String maVe) {
        this.danhSachVe.removeIf(ve -> ve.getMaVe().equals(maVe));
        tinhTongTien();
    }

    // Thêm phương thức để thêm voucher vào hóa đơn
    public void themVoucher(Voucher voucher) {
        if (voucher != null && !this.danhSachVoucher.contains(voucher)) {
            this.danhSachVoucher.add(voucher);
            // Cập nhật tổng tiền
            tinhTongTien();
        }
    }

    // Thêm phương thức để xóa voucher khỏi hóa đơn
    public void xoaVoucher(Voucher voucher) {
        if (voucher != null && this.danhSachVoucher.remove(voucher)) {
            // Cập nhật tổng tiền
            tinhTongTien();
        }
    }

    // Thêm phương thức để xóa voucher theo mã voucher
    public void xoaVoucherTheoMa(String maVoucher) {
        this.danhSachVoucher.removeIf(voucher -> voucher.getMaVoucher().equals(maVoucher));
        tinhTongTien();
    }

    // Thêm phương thức để thêm đồ ăn vào hóa đơn
    public void themDoAn(DoAn doAn) {
        if (doAn != null && !this.danhSachDoAn.contains(doAn)) {
            this.danhSachDoAn.add(doAn);
            // Cập nhật tổng tiền
            tinhTongTien();
        }
    }

    // Thêm phương thức để xóa đồ ăn khỏi hóa đơn
    public void xoaDoAn(DoAn doAn) {
        if (doAn != null && this.danhSachDoAn.remove(doAn)) {
            // Cập nhật tổng tiền
            tinhTongTien();
        }
    }

    // Thêm phương thức để xóa đồ ăn theo mã đồ ăn
    public void xoaDoAnTheoMa(String maDoAn) {
        this.danhSachDoAn.removeIf(doAn -> doAn.getMaDoAn().equals(maDoAn));
        tinhTongTien();
    }

    // Phương thức tính tổng tiền tự động (có tính cả voucher)
    public void tinhTongTien() {
        int tienVe = this.danhSachVe.stream().mapToInt(Ve::getGiaVe).sum();
        int tienDoAn = this.danhSachDoAn.stream().mapToInt(DoAn::getGia).sum();
        
        // Tính tiền sau khi áp dụng voucher
        int tongTienTruocGiamGia = tienVe + tienDoAn;
        int tienGiamGia = 0;
        
        for (Voucher voucher : this.danhSachVoucher) {
            if (voucher.getTrangThai().equals("HoatDong")) {
                // Tính giảm giá theo phần trăm
                tienGiamGia += (int)(tongTienTruocGiamGia * voucher.getPhanTramGiamGia() / 100.0);
            }
        }
        
        this.tongTien = Math.max(0, tongTienTruocGiamGia - tienGiamGia);
    }

    // Giữ lại getter cũ để tương thích ngược
    public DoAn getDoAn() { 
        return this.danhSachDoAn.isEmpty() ? null : this.danhSachDoAn.get(0); 
    }
    
    // Giữ lại setter cũ để tương thích ngược
    public void setDoAn(DoAn doAn) { 
        this.danhSachDoAn.clear();
        if (doAn != null) {
            this.danhSachDoAn.add(doAn);
        }
        tinhTongTien(); // Cập nhật tổng tiền khi thay đổi đồ ăn
    }

    public int getTongTien() { return tongTien; }
    public void setTongTien(int tongTien) {
        if (tongTien >= 0) this.tongTien = tongTien;
        else throw new IllegalArgumentException("Tổng tiền không được âm");
    }

    public LocalDateTime getThoiGianThanhToan() { return thoiGianThanhToan; }
    public void setThoiGianThanhToan(LocalDateTime thoiGianThanhToan) {
        this.thoiGianThanhToan = thoiGianThanhToan;
    }

    public phuongThuc getphuongThuc() { return phuongThuc; }
    public void setphuongThuc(phuongThuc pt) {
        this.phuongThuc = pt;
    }

    public String getCCCD() { return CCCD; }
    public void setCCCD(String CCCD) { this.CCCD = CCCD; }

    private static ArrayList<HoaDon> danhSachHoaDon = new ArrayList<>();

    // === CRUD ===
    public static void Create(HoaDon hoaDon) {
        if (hoaDon.getMaHoaDon() == null || hoaDon.getMaHoaDon().trim().isEmpty()) {
            System.out.println("Lỗi: Mã hóa đơn không được để trống.");
            return;
        }
        if (hoaDon.getTongTien() < 0) {
            System.out.println("Lỗi: Tổng tiền không hợp lệ.");
            return;
        }
        danhSachHoaDon.add(hoaDon);
        System.out.println("Đã tạo hóa đơn thành công.");
    }

    public static ArrayList<HoaDon> Read() {
        if (danhSachHoaDon.isEmpty()) {
            System.out.println("Danh sách hóa đơn trống.");
            return new ArrayList<>();
        }
        System.out.println("Tổng số hóa đơn: " + danhSachHoaDon.size());
        return new ArrayList<>(danhSachHoaDon);
    }

    public static void Read(String maHoaDon) {
        HoaDon hd = getHoaDonById(maHoaDon);
        if (hd != null) {
            hd.hienThiThongTin();
        } else {
            System.out.println("Không tìm thấy hóa đơn với mã: " + maHoaDon);
        }
    }

    public static void Update(String maHoaDon, HoaDon hoaDon) {
        int index = getHoaDonIndexById(maHoaDon);
        if (index != -1) {
            hoaDon.setMaHoaDon(maHoaDon);
            danhSachHoaDon.set(index, hoaDon);
            System.out.println("Cập nhật hóa đơn thành công.");
        } else {
            System.out.println("Không tìm thấy hóa đơn với mã đã nhập.");
        }
    }

    public static void Delete(String maHoaDon) {
        int index = getHoaDonIndexById(maHoaDon);
        if (index != -1) {
            danhSachHoaDon.remove(index);
            System.out.println("Xóa hóa đơn thành công.");
        } else {
            System.out.println("Không tìm thấy hóa đơn với mã đã nhập.");
        }
    }

    public static HoaDon getHoaDonById(String maHoaDon) {
        for (HoaDon hd : danhSachHoaDon) {
            if (hd.getMaHoaDon().equalsIgnoreCase(maHoaDon)) {
                return hd;
            }
        }
        return null;
    }

    private static int getHoaDonIndexById(String maHoaDon) {
        for (int i = 0; i < danhSachHoaDon.size(); i++) {
            if (danhSachHoaDon.get(i).getMaHoaDon().equalsIgnoreCase(maHoaDon)) {
                return i;
            }
        }
        return -1;
    }

    public void hienThiThongTin() {
        System.out.println("=== THÔNG TIN HÓA ĐƠN ===");
        System.out.println("Mã hóa đơn: " + maHoaDon);
        System.out.println("CCCD khách hàng: " + CCCD);
        
        // Hiển thị thông tin vé
        if (danhSachVe != null && !danhSachVe.isEmpty()) {
            System.out.println("Danh sách vé:");
            for (Ve ve : danhSachVe) {
                System.out.println("  - Mã vé: " + ve.getMaVe() + ", Giá: " + ve.getGiaVe() + " VNĐ, Ghế: " + ve.getMaGhe());
            }
        } else {
            System.out.println("Vé: Không có");
        }
        
        // Hiển thị thông tin voucher
        if (danhSachVoucher != null && !danhSachVoucher.isEmpty()) {
            System.out.println("Danh sách voucher:");
            for (Voucher voucher : danhSachVoucher) {
                System.out.println("  - Mã voucher: " + voucher.getMaVoucher() + ", Giảm giá: " + voucher.getPhanTramGiamGia() + "%, Trạng thái: " + voucher.getTrangThai());
            }
        } else {
            System.out.println("Voucher: Không có");
        }
        
        // Hiển thị thông tin đồ ăn
        if (danhSachDoAn != null && !danhSachDoAn.isEmpty()) {
            System.out.println("Danh sách đồ ăn:");
            for (DoAn doAn : danhSachDoAn) {
                System.out.println("  - Mã đồ ăn: " + doAn.getMaDoAn() + ", Tên: " + doAn.getTenDoAn() + ", Giá: " + doAn.getGia() + " VNĐ");
            }
        } else {
            System.out.println("Đồ ăn: Không có");
        }
        
        System.out.println("Tổng tiền: " + tongTien + " VNĐ");
        System.out.println("Thời gian thanh toán: " + DateTimeUtils.formatVietDateTime(thoiGianThanhToan));
        System.out.println("Phương thức thanh toán: " + phuongThuc);
        System.out.println("=========================");
    }
}
