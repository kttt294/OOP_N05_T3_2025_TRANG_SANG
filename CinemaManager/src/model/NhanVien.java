
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
    public static void Create(NhanVien nv) {
        if (nv == null || nv.getCCCD() == null || nv.getCCCD().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin nhân viên không được để trống.");
            return;
        }
        if (getNhanVienByCCCD(nv.getCCCD()) != null) {
            System.out.println("Lỗi: Nhân viên đã tồn tại.");
            return;
        }
        danhSachNhanVien.add(nv);
        System.out.println("Đã thêm nhân viên thành công.");
    }
    // Read toàn bộ danh sách nhân viên
    public static java.util.ArrayList<NhanVien> Read() {
        if (danhSachNhanVien.isEmpty()) {
            System.out.println("Danh sách nhân viên trống.");
            return new java.util.ArrayList<>();
        }
        System.out.println("Tổng số nhân viên: " + danhSachNhanVien.size());
        return new java.util.ArrayList<>(danhSachNhanVien);
    }
    
    // Read nhân viên theo CCCD
    public static void Read(String CCCD) {
        if (danhSachNhanVien.isEmpty()) {
            System.out.println("Danh sách nhân viên trống.");
            return;
        }
        NhanVien nv = getNhanVienByCCCD(CCCD);
        if (nv != null) {
            nv.hienThiThongTin();
        } else {
            System.out.println("Không tìm thấy nhân viên với CCCD: " + CCCD);
        }
    }
    public static void Update(String CCCD, NhanVien nv) {
        if (nv == null || nv.getCCCD() == null || nv.getCCCD().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin nhân viên không được để trống.");
            return;
        }
        int idx = getNhanVienIndexByCCCD(CCCD);
        if (idx == -1) {
            System.out.println("Không tìm thấy nhân viên với CCCD: " + CCCD);
            return;
        }
        nv.setCCCD(CCCD);
        danhSachNhanVien.set(idx, nv);
        System.out.println("Cập nhật nhân viên thành công.");
    }
    
    public static void Delete(String CCCD) {
        int idx = getNhanVienIndexByCCCD(CCCD);
        if (idx == -1) {
            System.out.println("Không tìm thấy nhân viên với CCCD: " + CCCD);
            return;
        }
        danhSachNhanVien.remove(idx);
        System.out.println("Đã xóa nhân viên thành công.");
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
