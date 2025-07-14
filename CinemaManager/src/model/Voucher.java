import java.time.LocalDateTime;

public class Voucher {
    private String maVoucher;
    private String moTa;
    private float phanTramGiamGia;
    private LocalDateTime ngayBatDau;
    private LocalDateTime ngayKetThuc;
    private String soLuongConLai;
    private String trangThai;

    public Voucher() {}

    public Voucher(String maVoucher, String moTa, float phanTramGiamGia,
                   LocalDateTime ngayBatDau, LocalDateTime ngayKetThuc,
                   String soLuongConLai, String trangThai) {
        this.maVoucher = maVoucher;
        this.moTa = moTa;
        this.phanTramGiamGia = phanTramGiamGia;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.soLuongConLai = soLuongConLai;
        this.trangThai = trangThai;
    }

    public String getMaVoucher() {
        return maVoucher;
    }

    public void setMaVoucher(String maVoucher) {
        this.maVoucher = maVoucher;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public float getPhanTramGiamGia() {
        return phanTramGiamGia;
    }

    public void setPhanTramGiamGia(float phanTramGiamGia) {
        this.phanTramGiamGia = phanTramGiamGia;
    }

    public LocalDateTime getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDateTime ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public LocalDateTime getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDateTime ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getSoLuongConLai() {
        return soLuongConLai;
    }

    public void setSoLuongConLai(String soLuongConLai) {
        this.soLuongConLai = soLuongConLai;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
