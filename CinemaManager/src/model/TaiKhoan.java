import java.util.ArrayList;

public class TaiKhoan {
    private String tenDangNhap;
    private String matKhau;
    private String vaiTro; // "Admin", "NhanVien", "KhachHang"
    private boolean trangThai; // true: hoạt động, false: bị khóa
    private String maNguoiDung; // Liên kết với KhachHang hoặc NhanVien

    private static ArrayList<TaiKhoan> danhSachTaiKhoan = new ArrayList<>();

    public TaiKhoan() {}
    public TaiKhoan(String tenDangNhap, String matKhau, String vaiTro, boolean trangThai, String maNguoiDung) {
        setTenDangNhap(tenDangNhap);
        setMatKhau(matKhau);
        setVaiTro(vaiTro);
        this.trangThai = trangThai;
        setMaNguoiDung(maNguoiDung);
    }

    // Getter & Setter (có kiểm tra hợp lệ)
    public String getTenDangNhap() { return tenDangNhap; }
    public void setTenDangNhap(String tenDangNhap) {
        if (tenDangNhap == null || tenDangNhap.trim().isEmpty())
            throw new IllegalArgumentException("Tên đăng nhập không được để trống!");
        this.tenDangNhap = tenDangNhap.trim();
    }

    public String getMatKhau() { return matKhau; }
    public void setMatKhau(String matKhau) {
        if (matKhau == null || matKhau.trim().isEmpty())
            throw new IllegalArgumentException("Mật khẩu không được để trống!");
        this.matKhau = matKhau;
    }

    public String getVaiTro() { return vaiTro; }
    public void setVaiTro(String vaiTro) {
        if (vaiTro == null || vaiTro.trim().isEmpty())
            throw new IllegalArgumentException("Vai trò không được để trống!");
        String v = vaiTro.trim();
        if (!v.equalsIgnoreCase("Admin") && !v.equalsIgnoreCase("NhanVien") && !v.equalsIgnoreCase("KhachHang"))
            throw new IllegalArgumentException("Vai trò chỉ được là 'Admin', 'NhanVien' hoặc 'KhachHang'!");
        // Lưu đúng định dạng hoa đầu
        if (v.equalsIgnoreCase("Admin")) this.vaiTro = "Admin";
        else if (v.equalsIgnoreCase("NhanVien")) this.vaiTro = "NhanVien";
        else this.vaiTro = "KhachHang";
    }

    public boolean isTrangThai() { return trangThai; }
    public void setTrangThai(boolean trangThai) { this.trangThai = trangThai; }

    public String getMaNguoiDung() { return maNguoiDung; }
    public void setMaNguoiDung(String maNguoiDung) {
        if (maNguoiDung == null || maNguoiDung.trim().isEmpty())
            throw new IllegalArgumentException("Mã người dùng không được để trống!");
        this.maNguoiDung = maNguoiDung.trim();
    }

    // CRUD methods
    public static boolean Create(TaiKhoan tk) {
        if (tk == null) return false;
        if (getTaiKhoanByTenDangNhap(tk.getTenDangNhap()) != null) return false; // Đã tồn tại
        danhSachTaiKhoan.add(tk);
        return true;
    }

    public static ArrayList<TaiKhoan> Read() {
        return new ArrayList<>(danhSachTaiKhoan);
    }

    public static boolean Update(TaiKhoan tk) {
        if (tk == null) return false;
        int idx = getTaiKhoanIndexByTenDangNhap(tk.getTenDangNhap());
        if (idx == -1) return false;
        danhSachTaiKhoan.set(idx, tk);
        return true;
    }

    public static boolean Delete(String tenDangNhap) {
        int idx = getTaiKhoanIndexByTenDangNhap(tenDangNhap);
        if (idx == -1) return false;
        danhSachTaiKhoan.remove(idx);
        return true;
    }

    // Đăng nhập
    public static TaiKhoan dangNhap(String tenDangNhap, String matKhau) {
        TaiKhoan tk = getTaiKhoanByTenDangNhap(tenDangNhap);
        if (tk != null && tk.isTrangThai() && tk.getMatKhau().equals(matKhau)) {
            return tk;
        }
        return null;
    }

    // Đổi mật khẩu
    public boolean doiMatKhau(String matKhauMoi) {
        if (matKhauMoi == null || matKhauMoi.trim().isEmpty()) return false;
        setMatKhau(matKhauMoi);
        return true;
    }

    // Kiểm tra vai trò
    public boolean isAdmin() { return "Admin".equals(vaiTro); }
    public boolean isNhanVien() { return "NhanVien".equals(vaiTro); }
    public boolean isKhachHang() { return "KhachHang".equals(vaiTro); }

    // Helper methods
    public static TaiKhoan getTaiKhoanByTenDangNhap(String tenDangNhap) {
        for (TaiKhoan tk : danhSachTaiKhoan) {
            if (tk.getTenDangNhap().equalsIgnoreCase(tenDangNhap)) return tk;
        }
        return null;
    }

    public static int getTaiKhoanIndexByTenDangNhap(String tenDangNhap) {
        for (int i = 0; i < danhSachTaiKhoan.size(); i++) {
            if (danhSachTaiKhoan.get(i).getTenDangNhap().equalsIgnoreCase(tenDangNhap)) return i;
        }
        return -1;
    }
}
