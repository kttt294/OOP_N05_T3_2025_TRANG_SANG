package test;

public class TaiKhoan {
    private String tenDangNhap;
    private String matKhau;
    private VaiTro vaiTro;
    public enum VaiTro {ADMIN, NHANVIEN, KHACHHANG};
    private boolean trangThai; // true: hoạt động, false: bị khóa

    public TaiKhoan() {};

    public TaiKhoan(String tenDangNhap, String matKhau, VaiTro vaiTro) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.vaiTro = vaiTro;
        this.trangThai = true;
    }
}