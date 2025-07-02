package model;

import java.time.LocalDateTime;

<<<<<<< HEAD
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
=======
public class Voucher{
    private String maVoucher;
    private String moTa;
    private int phanTramGiamGia;
    private LocalDateTime ngayBatDau;
    private LocalDateTime ngayKetThuc;
    private boolean conHieuLuc;

    public Voucher(){};
    public Voucher(String maVoucher, String moTa, int phanTramGiamGia, LocalDateTime ngayBatDau, LocalDateTime ngayKetThuc, boolean conHieuLuc){
        this.maVoucher = maVoucher;
        this.moTa = moTa;

    }

    public void setMaVoucher(String ma){
        this.maVoucher = ma;
    }

    public String getMaVoucher(){
        return this.maVoucher;
    }
<<<<<<< HEAD
}
>>>>>>> 8cd6e89d87aa971c828bf8a964449e4996e0481a
=======
}
>>>>>>> 8cd6e89d87aa971c828bf8a964449e4996e0481a
