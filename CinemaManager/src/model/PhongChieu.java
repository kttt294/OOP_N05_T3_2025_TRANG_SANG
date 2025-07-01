package model;

import java.util.List;

public class PhongChieu {
    private String maPhong;
    private String tenPhong;
    private int soHangGhe;
    private int soCotGhe;
    private List<Ghe> danhSachGheTrong;

    public PhongChieu(){};

    public PhongChieu(String maPhong, String tenPhong) {
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
    }

    public PhongChieu(String maPhong, String tenPhong, int soHangGhe, int soCotGhe) {
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
        this.soHangGhe = soHangGhe;
        this.soCotGhe = soCotGhe;
    }

    public String getTenPhong() {
        return tenPhong;
    }
}
