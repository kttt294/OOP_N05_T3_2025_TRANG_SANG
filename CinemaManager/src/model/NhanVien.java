
public class NhanVien extends Nguoi {
    private String chucVu;
    private double luong;
    private TaiKhoan taiKhoan;
    private static java.util.ArrayList<NhanVien> danhSachNhanVien = new java.util.ArrayList<>();

    public NhanVien() {
        super();
    }

    public NhanVien(String CCCD, String ten, int tuoi, String sdt, String email, String chucVu, double luong, TaiKhoan taiKhoan) {
        super(CCCD, ten, tuoi, sdt, email);
        this.chucVu = chucVu;
        this.luong = luong;
        this.taiKhoan = taiKhoan;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }
    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    // CRUD static
    public static boolean Create(NhanVien nv) {
        if (nv == null || nv.getCCCD() == null || nv.getCCCD().trim().isEmpty() ||
            nv.getTen() == null || nv.getTen().trim().isEmpty()) {
            return false;
        }
        if (getNhanVienByCCCD(nv.getCCCD()) != null) return false;
        danhSachNhanVien.add(nv);
        return true;
    }
    public static java.util.ArrayList<NhanVien> Read() {
        return new java.util.ArrayList<>(danhSachNhanVien);
    }
    public static boolean Update(NhanVien nv) {
        if (nv == null || nv.getCCCD() == null || nv.getCCCD().trim().isEmpty()) return false;
        int idx = getNhanVienIndexByCCCD(nv.getCCCD());
        if (idx == -1) return false;
        danhSachNhanVien.set(idx, nv);
        return true;
    }
    public static boolean Delete(String CCCD) {
        int idx = getNhanVienIndexByCCCD(CCCD);
        if (idx == -1) return false;
        danhSachNhanVien.remove(idx);
        return true;
    }
    public static NhanVien getNhanVienByCCCD(String CCCD) {
        for (NhanVien nv : danhSachNhanVien) {
            if (nv.getCCCD().equalsIgnoreCase(CCCD)) return nv;
        }
        return null;
    }
    public static int getNhanVienIndexByCCCD(String CCCD) {
        for (int i = 0; i < danhSachNhanVien.size(); i++) {
            if (danhSachNhanVien.get(i).getCCCD().equalsIgnoreCase(CCCD)) return i;
        }
        return -1;
    }

    @Override
    public String getLoaiNguoi() {
        return "Nhân Viên";
    }

    @Override
    public void hienThiThongTin() {
        System.out.println("CCCD: " + getCCCD());
        System.out.println("Tên nhân viên: " + getTen());
        System.out.println("Tuổi: " + getTuoi());
        System.out.println("SĐT: " + getSdt());
        System.out.println("Email: " + getEmail());
        System.out.println("Chức vụ: " + chucVu);
        System.out.println("Lương: " + luong);
    }
}
