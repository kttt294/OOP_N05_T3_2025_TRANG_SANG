package com.example.servingwebcontent.model;

import java.util.ArrayList;

public class Ve {

    public enum TrangThaiVe { CHUA_THANH_TOAN, DA_THANH_TOAN, DA_HUY }

    private String maVe;
    private String CCCD;
    private String maSuatChieu;
    private String maGhe;
    private int giaVe;
    private TrangThaiVe trangThai;

    public Ve() {}

    public Ve(String maVe, String CCCD, String maSuatChieu, String maGhe, int giaVe) {
        setMaVe(maVe);
        setCCCD(CCCD);
        setMaSuatChieu(maSuatChieu);
        setMaGhe(maGhe);
        setGiaVe(giaVe);
        setTrangThai(TrangThaiVe.CHUA_THANH_TOAN);
    }

    public String getMaVe() { return maVe; }
    public void setMaVe(String maVe) { this.maVe = maVe; }

    public String getCCCD() { return CCCD; }
    public void setCCCD(String CCCD) { this.CCCD = CCCD; }

    public String getMaSuatChieu() { return maSuatChieu; }
    public void setMaSuatChieu(String maSuatChieu) { this.maSuatChieu = maSuatChieu; }

    public String getMaGhe() { return maGhe; }
    public void setMaGhe(String maGhe) { this.maGhe = maGhe; }

    public int getGiaVe() { return giaVe; }
    public void setGiaVe(int giaVe) {
        if (giaVe >= 0) this.giaVe = giaVe;
        else throw new IllegalArgumentException("Giá vé không được âm.");
    }

    public TrangThaiVe getTrangThai() { return trangThai; }
    public void setTrangThai(TrangThaiVe trangThai) { this.trangThai = trangThai; }

    public static ArrayList<Ve> getDanhSachVe() { return danhSachVe; }

    private static ArrayList<Ve> danhSachVe = new ArrayList<>();

    // === CRUD ===
    public static void Create(Ve ve) {
        if (ve.getMaVe() == null || ve.getMaVe().trim().isEmpty()) {
            System.out.println("Mã vé không hợp lệ.");
            return;
        }
        danhSachVe.add(ve);
        System.out.println("Đã thêm vé thành công.");
    }

    public static ArrayList<Ve> Read() {
        return new ArrayList<>(danhSachVe);
    }

    public static void Read(String maVe) {
        Ve ve = getVeById(maVe);
        if (ve != null) ve.hienThiThongTin();
        else System.out.println("Không tìm thấy vé.");
    }

    public static void Update(String maVe, Ve veMoi) {
        int index = getVeIndexById(maVe);
        if (index != -1) {
            veMoi.setMaVe(maVe); // giữ nguyên mã vé
            danhSachVe.set(index, veMoi);
            System.out.println("Cập nhật vé thành công.");
        } else {
            System.out.println("Không tìm thấy vé.");
        }
    }

    public static void Delete(String maVe) {
        int index = getVeIndexById(maVe);
        if (index != -1) {
            danhSachVe.remove(index);
            System.out.println("Xóa vé thành công.");
        } else {
            System.out.println("Không tìm thấy vé.");
        }
    }

    public static Ve getVeById(String maVe) {
        for (Ve ve : danhSachVe) {
            if (ve.getMaVe().equalsIgnoreCase(maVe)) return ve;
        }
        return null;
    }

    private static int getVeIndexById(String maVe) {
        for (int i = 0; i < danhSachVe.size(); i++) {
            if (danhSachVe.get(i).getMaVe().equalsIgnoreCase(maVe)) return i;
        }
        return -1;
    }

    public void hienThiThongTin() {
        System.out.println("=== THÔNG TIN VÉ ===");
        System.out.println("Mã vé: " + maVe);
        System.out.println("CCCD: " + CCCD);
        System.out.println("Mã suất chiếu: " + maSuatChieu);
        System.out.println("Mã ghế: " + maGhe);
        System.out.println("Giá vé: " + giaVe);
        System.out.println("Trạng thái: " + trangThai);
        System.out.println("=====================");
    }
}
