package model;

public class DoAn {
    private String maDoAn;
    private String tenDoAn;
    private int gia; // tính bằng Việt Nam Đồng
    private int soLuong;

    public DoAn() {}

    public DoAn(String maDoAn, String tenDoAn, int gia, int soLuong) {
        this.maDoAn = maDoAn;
        this.tenDoAn = tenDoAn;
        this.gia = gia;
        this.soLuong = soLuong;
    }

    public String getMaDoAn() {
        return maDoAn;
    }

    public void setMaDoAn(String maDoAn) {
        this.maDoAn = maDoAn;
    }

    public String getTenDoAn() {
        return tenDoAn;
    }

    public void setTenDoAn(String tenDoAn) {
        this.tenDoAn = tenDoAn;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
