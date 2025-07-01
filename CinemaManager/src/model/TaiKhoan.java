package model;

public class TaiKhoan{
    String tenDangNhap;
    String matKhau;
    VaiTro vaiTro;
    boolean trangThai;  // true: hoạt động, false: bị khóa

    public TaiKhoan(){};
    
    public TaiKhoan(String tenDangNhap, String matKhau, VaiTro vaiTro){
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.vaiTro = vaiTro;
        this.trangThai = true;
    }
}