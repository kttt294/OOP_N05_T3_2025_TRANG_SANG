package model;

<<<<<<< HEAD
public class NhanVien extends TaiKhoan {
=======
public class NhanVien{
>>>>>>> 8cd6e89d87aa971c828bf8a964449e4996e0481a
    private String maNV;
    private String tenNV;
    private int tuoi;
    private String sdt;
    private String email;
<<<<<<< HEAD
    private GioiTinh gioiTinh;
    private String chucVu;

    NhanVien(){};

    public NhanVien(String maNV, String tenNV, int tuoi, String sdt, String email, GioiTinh gioiTinh, String chucVu) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.tuoi = tuoi;
        this.sdt = sdt;
        this.email = email;
        this.gioiTinh = gioiTinh;
        this.chucVu = chucVu;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public GioiTinh getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(GioiTinh gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
}
=======
    private String gioiTinh;
    private String chucVu;
    private TaiKhoan taiKhoan;

    public NhanVien(){};
}
>>>>>>> 8cd6e89d87aa971c828bf8a964449e4996e0481a
