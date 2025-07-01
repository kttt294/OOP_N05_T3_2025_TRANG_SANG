package model;

import java.time.LocalDateTime;

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
}