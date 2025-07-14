import java.time.LocalDateTime;

public class DoanhThuThongKe{
    private LocalDateTime ngayThongKe;
    private int soLuongVeBanRa;
    private int tongDoanhThu; // tính bằng Việt Nam Đồng
    private Phim phimBanChayNhat;
    private float tiLeChoNgoiDay;// tỉ lệ chỗ ngồi đầy trong rạp

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

