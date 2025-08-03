package com.example.servingwebcontent.model;

import java.util.ArrayList;
import java.util.Objects;

public class Ghe {
    public enum TrangThaiGhe { TRONG, DA_DAT, KHOA }

    private String maGhe;
    private int hang;
    private int cot;
    private String maPhong;
    private String maSuatChieu;
    private TrangThaiGhe trangThai;

    private static ArrayList<Ghe> danhSachGhe = new ArrayList<>();

    public Ghe() {}

    public Ghe(String maGhe, int hang, int cot, String maPhong) {
        setMaGhe(maGhe);
        setHang(hang);
        setCot(cot);
        setMaPhong(maPhong);
        this.trangThai = TrangThaiGhe.TRONG;
    }

    public Ghe(String maGhe, int hang, int cot, String maPhong,
               String maSuatChieu, TrangThaiGhe trangThai) {
        this(maGhe, hang, cot, maPhong);
        setMaSuatChieu(maSuatChieu);
        setTrangThai(trangThai);
    }

    // Getters & Setters
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

    public String getMaPhong() { return maPhong; }
    public void setMaPhong(String maPhong) {
        this.maPhong = (maPhong != null) ? maPhong.trim() : "";
    }

    public String getMaSuatChieu() { return maSuatChieu; }
    public void setMaSuatChieu(String maSuatChieu) {
        this.maSuatChieu = (maSuatChieu != null) ? maSuatChieu.trim() : "";
    }

    public TrangThaiGhe getTrangThai() { return trangThai; }
    public void setTrangThai(TrangThaiGhe trangThai) {
        this.trangThai = (trangThai != null) ? trangThai : TrangThaiGhe.TRONG;
    }

    // Trạng thái logic
    public boolean isTrong() { return trangThai == TrangThaiGhe.TRONG; }
    public boolean isDaDat() { return trangThai == TrangThaiGhe.DA_DAT; }

    public void datGhe() {
        if (isTrong()) this.trangThai = TrangThaiGhe.DA_DAT;
    }

    public void huyDat() {
        this.trangThai = TrangThaiGhe.TRONG;
    }

    // CRUD
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

    public static void Read(String maGhe) {
        Ghe ghe = getGheByMaGhe(maGhe);
        if (ghe != null) {
            ghe.hienThiThongTin();
        } else {
            System.out.println("Không tìm thấy ghế với mã: " + maGhe);
        }
    }

    public static ArrayList<Ghe> Read() {
        return new ArrayList<>(danhSachGhe);
    }

    public static void Update(String maGhe, Ghe gheMoi) {
        int idx = getGheIndexByMaGhe(maGhe);
        if (idx == -1) {
            System.out.println("Không tìm thấy ghế để cập nhật.");
            return;
        }
        gheMoi.setMaGhe(maGhe);
        danhSachGhe.set(idx, gheMoi);
        System.out.println("Cập nhật ghế thành công.");
    }

    public static void Delete(String maGhe) {
        int idx = getGheIndexByMaGhe(maGhe);
        if (idx != -1) {
            danhSachGhe.remove(idx);
            System.out.println("Đã xóa ghế thành công.");
        } else {
            System.out.println("Không tìm thấy ghế để xóa.");
        }
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

    public void hienThiThongTin() {
        System.out.println("=== THÔNG TIN GHẾ ===");
        System.out.println("Mã ghế: " + maGhe);
        System.out.println("Vị trí: Hàng " + hang + ", Cột " + cot);
        System.out.println("Phòng: " + maPhong);
        if (maSuatChieu != null && !maSuatChieu.isEmpty()) {
            System.out.println("Suất chiếu: " + maSuatChieu);
        }
        System.out.println("Trạng thái: " + trangThai);
        System.out.println("=====================");
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
}
