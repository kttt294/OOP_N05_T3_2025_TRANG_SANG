package model;

public class PhongChieu {
    String maPhong;
    String tenPhong;

    public PhongChieu(){};
    
    public PhongChieu(String maPhong, String tenPhong) {
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }
}
