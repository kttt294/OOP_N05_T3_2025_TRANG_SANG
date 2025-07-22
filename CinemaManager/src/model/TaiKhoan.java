import java.util.ArrayList;

public class TaiKhoan {
    private String tenDangNhap;
    private String matKhau;
    private String vaiTro; // "Admin", "NhanVien", "KhachHang"
    private boolean trangThai = true; // true: hoạt động, false: bị khóa

    private static ArrayList<TaiKhoan> danhSachTaiKhoan = new ArrayList<>();

    public TaiKhoan() {}
    public TaiKhoan(String tenDangNhap, String matKhau, String vaiTro) {
        setTenDangNhap(tenDangNhap);
        setMatKhau(matKhau);
        setVaiTro(vaiTro);
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

    // CRUD static
    public static void Create(TaiKhoan tk) {
        if (tk == null || tk.getTenDangNhap() == null || tk.getTenDangNhap().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin tài khoản không được để trống.");
            return;
        }
        if (getTaiKhoanByTenDangNhap(tk.getTenDangNhap()) != null) {
            System.out.println("Lỗi: Tài khoản đã tồn tại.");
            return;
        }
        danhSachTaiKhoan.add(tk);
        System.out.println("Đã thêm tài khoản thành công.");
    }

    // Read toàn bộ danh sách tài khoản
    public static ArrayList<TaiKhoan> Read() {
        if (danhSachTaiKhoan.isEmpty()) {
            System.out.println("Danh sách tài khoản trống.");
            return new ArrayList<>();
        }
        System.out.println("Tổng số tài khoản: " + danhSachTaiKhoan.size());
        return new ArrayList<>(danhSachTaiKhoan);
    }
    
    // Read tài khoản theo tên đăng nhập
    public static void Read(String tenDangNhap) {
        if (danhSachTaiKhoan.isEmpty()) {
            System.out.println("Danh sách tài khoản trống.");
            return;
        }
            TaiKhoan tk = getTaiKhoanByTenDangNhap(tenDangNhap);
            if (tk != null) {
            tk.hienThiThongTin();
            } else {
            System.out.println("Không tìm thấy tài khoản với tên đăng nhập: " + tenDangNhap);
        }
    }

    public static void Update(String tenDangNhap, TaiKhoan tk) {
        if (tk == null || tk.getTenDangNhap() == null || tk.getTenDangNhap().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin tài khoản không được để trống.");
            return;
        }
        int index = getTaiKhoanIndexByTenDangNhap(tenDangNhap);
        if (index != -1) {
            tk.setTenDangNhap(tenDangNhap);
            danhSachTaiKhoan.set(index, tk);
            System.out.println("Cập nhật thông tin tài khoản thành công.");
        } else {
            System.out.println("Không tìm thấy tài khoản với tên đăng nhập đã nhập.");
        }
    }

    public static void Delete(String tenDangNhap) {
        int index = getTaiKhoanIndexByTenDangNhap(tenDangNhap);
        if (index != -1) {
            danhSachTaiKhoan.remove(index);
            System.out.println("Đã xóa tài khoản thành công.");
        } else {
            System.out.println("Không tìm thấy tài khoản với tên đăng nhập đã nhập.");
        }
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

    private static int getTaiKhoanIndexByTenDangNhap(String tenDangNhap) {
        for (int i = 0; i < danhSachTaiKhoan.size(); i++) {
            if (danhSachTaiKhoan.get(i).getTenDangNhap().equalsIgnoreCase(tenDangNhap)) return i;
        }
        return -1;
    }
    
    // Hiển thị thông tin chi tiết của một tài khoản
    public void hienThiThongTin() {
        System.out.println("=== THÔNG TIN TÀI KHOẢN ===");
        System.out.println("Tên đăng nhập: " + this.tenDangNhap);
        System.out.println("Vai trò: " + this.vaiTro);
        System.out.println("Trạng thái: " + (this.trangThai ? "Hoạt động" : "Bị khóa"));
        System.out.println("===========================");
    }
}
