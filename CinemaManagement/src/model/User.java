
import java.util.ArrayList;

public abstract class User {
    public enum VaiTro { ADMIN, NHAN_VIEN, KHACH_HANG }
    public enum TrangThaiTaiKhoan { HOAT_DONG, BI_KHOA }

    private String CCCD;
    private String ten;
    private int tuoi;
    private String sdt;
    private String email;
    
    // Thuộc tính cho tài khoản
    private String tenDangNhap;
    private String matKhau;
    private VaiTro vaiTro;
    private TrangThaiTaiKhoan trangThaiTaiKhoan;

    private static ArrayList<User> danhSachNguoi = new ArrayList<>();

    public User() {}

    // Constructor cơ bản cho người
    public User(String CCCD, String ten, int tuoi, String sdt, String email) {
        this.CCCD = CCCD;
        this.ten = ten;
        this.tuoi = tuoi;
        this.sdt = sdt;
        this.email = email;
    }

    // Constructor đầy đủ bao gồm tài khoản
    public User(String CCCD, String ten, int tuoi, String sdt, String email,
                 String tenDangNhap, String matKhau, VaiTro vaiTro) {
        this(CCCD, ten, tuoi, sdt, email);
        setTenDangNhap(tenDangNhap);
        setMatKhau(matKhau);
        setVaiTro(vaiTro);
        this.trangThaiTaiKhoan = TrangThaiTaiKhoan.HOAT_DONG;
    }

    public String getCCCD() { return CCCD; }
    public void setCCCD(String CCCD) { this.CCCD = CCCD; }
    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }
    public int getTuoi() { return tuoi; }
    public void setTuoi(int tuoi) { this.tuoi = tuoi; }
    public String getSdt() { return sdt; }
    public void setSdt(String sdt) { this.sdt = sdt; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    // Getters và Setters cho tài khoản
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
    public VaiTro getVaiTro() { return vaiTro; }
    public void setVaiTro(VaiTro vaiTro) {
        this.vaiTro = (vaiTro != null) ? vaiTro : VaiTro.KHACH_HANG;
    }
    public TrangThaiTaiKhoan getTrangThaiTaiKhoan() { return trangThaiTaiKhoan; }
    public void setTrangThaiTaiKhoan(TrangThaiTaiKhoan trangThaiTaiKhoan) {
        this.trangThaiTaiKhoan = (trangThaiTaiKhoan != null) ? trangThaiTaiKhoan : TrangThaiTaiKhoan.HOAT_DONG;
    }

    // Phương thức kiểm tra vai trò
    public boolean isAdmin() { return vaiTro == VaiTro.ADMIN; }
    public boolean isNhanVien() { return vaiTro == VaiTro.NHAN_VIEN; }
    public boolean isKhachHang() { return vaiTro == VaiTro.KHACH_HANG; }
    public boolean isTaiKhoanHoatDong() { return trangThaiTaiKhoan == TrangThaiTaiKhoan.HOAT_DONG; }

    // Phương thức quản lý tài khoản
    public boolean doiMatKhau(String matKhauCu, String matKhauMoi) {
        if (matKhauCu != null && matKhauCu.equals(this.matKhau)) {
            setMatKhau(matKhauMoi);
            return true;
        }
        return false;
    }

    public void khoaTaiKhoan() {
        this.trangThaiTaiKhoan = TrangThaiTaiKhoan.BI_KHOA;
    }

    public void moKhoaTaiKhoan() {
        this.trangThaiTaiKhoan = TrangThaiTaiKhoan.HOAT_DONG;
    }

    // Phương thức trừu tượng
    public abstract String getLoaiNguoi();
    public abstract void hienThiThongTin();

    // CRUD static cho tài khoản
    public static void Create(User nguoi) {
        if (nguoi == null || nguoi.getCCCD() == null || nguoi.getCCCD().trim().isEmpty() ||
            nguoi.getTenDangNhap() == null || nguoi.getTenDangNhap().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin người dùng không được để trống.");
            return;
        }
        if (getNguoiByCCCD(nguoi.getCCCD()) != null) {
            System.out.println("Lỗi: Người dùng đã tồn tại.");
            return;
        }
        if (getNguoiByTenDangNhap(nguoi.getTenDangNhap()) != null) {
            System.out.println("Lỗi: Tên đăng nhập đã tồn tại.");
            return;
        }
        danhSachNguoi.add(nguoi);
        System.out.println("Đã thêm " + nguoi.getLoaiNguoi() + " thành công.");
    }

    public static ArrayList<User> Read() {
        if (danhSachNguoi.isEmpty()) {
            System.out.println("Danh sách người dùng trống.");
            return new ArrayList<>();
        }
        System.out.println("Tổng số người dùng: " + danhSachNguoi.size());
        return new ArrayList<>(danhSachNguoi);
    }

    public static void Read(String CCCD) {
        if (danhSachNguoi.isEmpty()) {
            System.out.println("Danh sách người dùng trống.");
            return;
        }
        User nguoi = getNguoiByCCCD(CCCD);
        if (nguoi != null) {
            nguoi.hienThiThongTin();
        } else {
            System.out.println("Không tìm thấy người dùng với CCCD: " + CCCD);
        }
    }

    public static void Update(String CCCD, User nguoi) {
        if (nguoi == null || nguoi.getCCCD() == null || nguoi.getCCCD().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin người dùng không được để trống.");
            return;
        }
        int idx = getNguoiIndexByCCCD(CCCD);
        if (idx == -1) {
            System.out.println("Không tìm thấy người dùng với CCCD: " + CCCD);
            return;
        }
        nguoi.setCCCD(CCCD);
        danhSachNguoi.set(idx, nguoi);
        System.out.println("Cập nhật " + nguoi.getLoaiNguoi() + " thành công.");
    }

    public static void Delete(String CCCD) {
        int idx = getNguoiIndexByCCCD(CCCD);
        if (idx == -1) {
            System.out.println("Không tìm thấy người dùng với CCCD: " + CCCD);
            return;
        }
        User nguoi = danhSachNguoi.get(idx);
        danhSachNguoi.remove(idx);
        System.out.println("Đã xóa " + nguoi.getLoaiNguoi() + " thành công.");
    }

    // Phương thức tìm kiếm
    public static User getNguoiByCCCD(String CCCD) {
        for (User nguoi : danhSachNguoi) {
            if (nguoi.getCCCD() != null && nguoi.getCCCD().equals(CCCD)) {
                return nguoi;
            }
        }
        return null;
    }

    public static User getNguoiByTenDangNhap(String tenDangNhap) {
        for (User nguoi : danhSachNguoi) {
            if (nguoi.getTenDangNhap() != null && nguoi.getTenDangNhap().equals(tenDangNhap)) {
                return nguoi;
            }
        }
        return null;
    }

    public static int getNguoiIndexByCCCD(String CCCD) {
        for (int i = 0; i < danhSachNguoi.size(); i++) {
            if (danhSachNguoi.get(i).getCCCD() != null && danhSachNguoi.get(i).getCCCD().equals(CCCD)) {
                return i;
            }
        }
        return -1;
    }

    // Phương thức đăng nhập
    public static User dangNhap(String tenDangNhap, String matKhau) {
        User nguoi = getNguoiByTenDangNhap(tenDangNhap);
        if (nguoi != null && nguoi.getMatKhau() != null && nguoi.getMatKhau().equals(matKhau) 
            && nguoi.isTaiKhoanHoatDong()) {
            return nguoi;
        }
        return null;
    }

    // Phương thức tìm kiếm theo vai trò
    public static ArrayList<User> getNguoiByVaiTro(VaiTro vaiTro) {
        ArrayList<User> result = new ArrayList<>();
        for (User nguoi : danhSachNguoi) {
            if (nguoi.getVaiTro() == vaiTro) {
                result.add(nguoi);
            }
        }
        return result;
    }
}