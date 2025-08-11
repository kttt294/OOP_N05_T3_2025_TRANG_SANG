package com.example.servingwebcontent.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import com.example.servingwebcontent.util.DateTimeUtils;

public class DanhGia {
    private String maDanhGia;
    private String CCCD;
    private String maPhim;
    private int soSao; // từ 0 đến 5
    private String noiDung;
    private LocalDateTime thoiGian;

    public DanhGia() {}

    public DanhGia(String maDanhGia, String CCCD, String maPhim, int soSao, String noiDung, LocalDateTime thoiGian) {
        setMaDanhGia(maDanhGia);
        setCCCD(CCCD);
        setMaPhim(maPhim);
        setSoSao(soSao);
        setNoiDung(noiDung);
        setThoiGian(thoiGian);
    }

    public String getMaDanhGia() { return maDanhGia; }
    public void setMaDanhGia(String maDanhGia) {
        if (maDanhGia == null || maDanhGia.trim().isEmpty())
            throw new IllegalArgumentException("Mã đánh giá không được để trống!");
        this.maDanhGia = maDanhGia.trim();
    }

    public String getCCCD() { return CCCD; }
    public void setCCCD(String CCCD) {
        if (CCCD == null || CCCD.trim().isEmpty())
            throw new IllegalArgumentException("CCCD không được để trống!");
        this.CCCD = CCCD.trim();
    }

    public String getMaPhim() { return maPhim; }
    public void setMaPhim(String maPhim) {
        this.maPhim = (maPhim != null) ? maPhim.trim() : null;
    }

    public int getSoSao() { return soSao; }
    public void setSoSao(int soSao) {
        if (soSao < 0 || soSao > 5)
            throw new IllegalArgumentException("Số sao phải từ 0 đến 5!");
        this.soSao = soSao;
    }

    public String getNoiDung() { return noiDung; }
    public void setNoiDung(String noiDung) {
        this.noiDung = (noiDung != null) ? noiDung.trim() : "";
    }

    public LocalDateTime getThoiGian() { return thoiGian; }
    public void setThoiGian(LocalDateTime thoiGian) {
        this.thoiGian = thoiGian;
    }

    private static ArrayList<DanhGia> danhSachDanhGia = new ArrayList<>();

    // === CRUD ===

    public static void Create(DanhGia dg) {
        if (dg == null || dg.getMaDanhGia() == null || dg.getMaDanhGia().trim().isEmpty() ||
            dg.getCCCD() == null || dg.getCCCD().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin đánh giá không được để trống.");
            return;
        }
        if (dg.getMaPhim() == null || dg.getMaPhim().trim().isEmpty()) {
            System.out.println("Lỗi: Mã phim không được để trống.");
            return;
        }
        if (getDanhGiaByMa(dg.getMaDanhGia()) != null) {
            System.out.println("Lỗi: Đánh giá đã tồn tại.");
            return;
        }
        danhSachDanhGia.add(dg);
        System.out.println("Đã thêm đánh giá thành công.");
    }

    public static ArrayList<DanhGia> Read() {
        if (danhSachDanhGia.isEmpty()) {
            System.out.println("Danh sách đánh giá trống.");
            return new ArrayList<>();
        }
        System.out.println("Tổng số đánh giá: " + danhSachDanhGia.size());
        return new ArrayList<>(danhSachDanhGia);
    }

    public static void Read(String maDanhGia) {
        if (danhSachDanhGia.isEmpty()) {
            System.out.println("Danh sách đánh giá trống.");
            return;
        }
        DanhGia dg = getDanhGiaByMa(maDanhGia);
        if (dg != null) {
            dg.hienThiThongTin();
        } else {
            System.out.println("Không tìm thấy đánh giá với mã: " + maDanhGia);
        }
    }

    public static void Update(String maDanhGia, DanhGia dg) {
        if (dg == null || dg.getMaDanhGia() == null || dg.getMaDanhGia().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin đánh giá không được để trống.");
            return;
        }
        int idx = getDanhGiaIndexByMa(maDanhGia);
        if (idx == -1) {
            System.out.println("Không tìm thấy đánh giá với mã: " + maDanhGia);
            return;
        }
        dg.setMaDanhGia(maDanhGia);
        danhSachDanhGia.set(idx, dg);
        System.out.println("Cập nhật đánh giá thành công.");
    }

    public static void Delete(String maDanhGia) {
        int idx = getDanhGiaIndexByMa(maDanhGia);
        if (idx == -1) {
            System.out.println("Không tìm thấy đánh giá với mã: " + maDanhGia);
            return;
        }
        danhSachDanhGia.remove(idx);
        System.out.println("Đã xoá đánh giá thành công.");
    }

    public static DanhGia getDanhGiaByMa(String maDanhGia) {
        for (DanhGia dg : danhSachDanhGia) {
            if (dg.getMaDanhGia().equalsIgnoreCase(maDanhGia)) return dg;
        }
        return null;
    }

    public static int getDanhGiaIndexByMa(String maDanhGia) {
        for (int i = 0; i < danhSachDanhGia.size(); i++) {
            if (danhSachDanhGia.get(i).getMaDanhGia().equalsIgnoreCase(maDanhGia)) return i;
        }
        return -1;
    }

    public static ArrayList<DanhGia> getDanhGiaByMaPhim(String maPhim) {
        ArrayList<DanhGia> result = new ArrayList<>();
        for (DanhGia dg : danhSachDanhGia) {
            if (dg.getMaPhim() != null && dg.getMaPhim().equals(maPhim)) {
                result.add(dg);
            }
        }
        return result;
    }

    public static ArrayList<DanhGia> getDanhGiaByCCCD(String CCCD) {
        ArrayList<DanhGia> ketQua = new ArrayList<>();
        for (DanhGia dg : danhSachDanhGia) {
            if (dg.getCCCD().equalsIgnoreCase(CCCD)) {
                ketQua.add(dg);
            }
        }
        return ketQua;
    }

    public static double tinhDiemTrungBinhPhim(String maPhim) {
        ArrayList<DanhGia> danhGiaPhim = getDanhGiaByMaPhim(maPhim);
        if (danhGiaPhim.isEmpty()) return 0.0;

        double tongDiem = 0.0;
        for (DanhGia dg : danhGiaPhim) {
            tongDiem += dg.getSoSao();
        }
        return tongDiem / danhGiaPhim.size();
    }

    public static void thongKeDanhGia() {
        System.out.println("=== THỐNG KÊ ĐÁNH GIÁ ===");
        System.out.println("Tổng số đánh giá: " + danhSachDanhGia.size());
        System.out.println("=========================");
    }

    public void hienThiThongTin() {
        System.out.println("=== THÔNG TIN ĐÁNH GIÁ ===");
        System.out.println("Mã đánh giá: " + maDanhGia);
        System.out.println("Mã khách hàng: " + CCCD);
        System.out.println("Mã phim: " + maPhim);
        System.out.println("Số sao: " + soSao + "/5");
        System.out.println("Bình luận: " + noiDung);
        System.out.println("Thời gian: " + DateTimeUtils.formatVietDateTime(thoiGian));
        System.out.println("=========================");
    }
}
