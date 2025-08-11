package com.example.servingwebcontent.model;

import com.example.servingwebcontent.util.DateTimeUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class HoaDon {

    public enum phuongThuc { TIEN_MAT, CHUYEN_KHOAN }

    private String maHoaDon;
    private DoAn doAn;
    private int tongTien;
    private LocalDateTime thoiGianThanhToan;
    private phuongThuc phuongThuc;
    private String CCCD;

    public HoaDon() {}

    public HoaDon(String maHoaDon, DoAn doAn, int tongTien, LocalDateTime thoiGianThanhToan, phuongThuc phuongThuc, String CCCD) {
        setMaHoaDon(maHoaDon);
        setDoAn(doAn);
        setTongTien(tongTien);
        setThoiGianThanhToan(thoiGianThanhToan);
        setphuongThuc(phuongThuc);
        setCCCD(CCCD);
    }

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
        if (doAn != null) {
            System.out.println("Đồ ăn: " + doAn.getTenDoAn() + " - " + doAn.getGia() + " VNĐ");
        } else {
            System.out.println("Đồ ăn: Không có");
        }
        System.out.println("Tổng tiền: " + tongTien + " VNĐ");
        System.out.println("Thời gian thanh toán: " + DateTimeUtils.formatVietDateTime(thoiGianThanhToan));
        System.out.println("Phương thức thanh toán: " + phuongThuc);
        System.out.println("=========================");
    }
}
