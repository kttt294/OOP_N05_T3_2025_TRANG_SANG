package controller;

import model.Phim;
import java.time.LocalDateTime;

public class DoanhThuThongKe{
    private LocalDateTime ngayThongKe;
    private int soLuongVeBanRa;
    private int tongDoanhThu; // tính bằng Việt Nam Đồng
    private Phim phimBanChayNhat;
    private float tiLeChoNgoiDay;// tỉ lệ chỗ ngồi đầy trong rạp

    DoanhThuThongKe(){};

    public DoanhThuThongKe(LocalDateTime ngayThongKe, int soLuongVeBanRa, int tongDoanhThu, Phim phimBanChayNhat, float tiLeChoNgoiDay){
        this.ngayThongKe = ngayThongKe;
        this.soLuongVeBanRa = soLuongVeBanRa;
        this.tongDoanhThu = tongDoanhThu;
        this.phimBanChayNhat= phimBanChayNhat;
        this.tiLeChoNgoiDay = tiLeChoNgoiDay;
    }
}