package model;

import java.time.LocalDateTime;

public class DanhGia{
    private String maDanhGia;
    private KhachHang nguoiDanhGia;
    private Phim phim;
    private int soSao;   // từ 0 đến 5
    private String binhLuan;
    private LocalDateTime thoiGian;

    public DanhGia(){};

    public DanhGia(String maDanhGia, KhachHang nguoiDanhGia, Phim phim, int soSao, String binhLuan, LocalDateTime thoiGian){
        this.maDanhGia = maDanhGia;
        this.nguoiDanhGia = nguoiDanhGia;
        this.phim = phim;
        this.soSao = soSao;
        this.binhLuan = binhLuan;
        this.thoiGian = thoiGian;
    }
}