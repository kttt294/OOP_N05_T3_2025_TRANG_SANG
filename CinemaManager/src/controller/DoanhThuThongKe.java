package controller;

import model.Phim;
import java.time.LocalDateTime;

<<<<<<< HEAD
public class DoanhThuThongKe {
    private LocalDateTime ngayThongKe;
    private int soLuongVeBanRa;
    private int tongDoanhThu;
    private Phim phimBanChayNhat;
    private float tiLeChoNgoiDay;
=======
public class DoanhThuThongKe{
    private LocalDateTime ngayThongKe;
    private int soLuongVeBanRa;
    private int tongDoanhThu; // tính bằng Việt Nam Đồng
    private Phim phimBanChayNhat;
    private float tiLeChoNgoiDay;// tỉ lệ chỗ ngồi đầy trong rạp
>>>>>>> 8cd6e89d87aa971c828bf8a964449e4996e0481a

    public DoanhThuThongKe() {}

    public DoanhThuThongKe(LocalDateTime ngayThongKe, int soLuongVeBanRa, int tongDoanhThu, Phim phimBanChayNhat, float tiLeChoNgoiDay) {
        this.ngayThongKe = ngayThongKe;
        this.soLuongVeBanRa = soLuongVeBanRa;
        this.tongDoanhThu = tongDoanhThu;
        this.phimBanChayNhat = phimBanChayNhat;
        this.tiLeChoNgoiDay = tiLeChoNgoiDay;
    }

    public LocalDateTime getNgayThongKe() {
        return ngayThongKe;
    }

    public void setNgayThongKe(LocalDateTime ngayThongKe) {
        this.ngayThongKe = ngayThongKe;
    }

    public int getSoLuongVeBanRa() {
        return soLuongVeBanRa;
    }

    public void setSoLuongVeBanRa(int soLuongVeBanRa) {
        this.soLuongVeBanRa = soLuongVeBanRa;
    }

    public int getTongDoanhThu() {
        return tongDoanhThu;
    }

    public void setTongDoanhThu(int tongDoanhThu) {
        this.tongDoanhThu = tongDoanhThu;
    }

    public Phim getPhimBanChayNhat() {
        return phimBanChayNhat;
    }

    public void setPhimBanChayNhat(Phim phimBanChayNhat) {
        this.phimBanChayNhat = phimBanChayNhat;
    }

    public float getTiLeChoNgoiDay() {
        return tiLeChoNgoiDay;
    }

    public void setTiLeChoNgoiDay(float tiLeChoNgoiDay) {
        this.tiLeChoNgoiDay = tiLeChoNgoiDay;
    }
}
