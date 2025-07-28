import java.util.ArrayList;

public abstract class Nguoi {
    private String CCCD;
    private String ten;
    private int tuoi;
    private String sdt;
    private String email;

    private static ArrayList<Nguoi> danhSachNguoi = new ArrayList<>();

    public Nguoi() {}

    // Constructor cơ bản cho người
    public Nguoi(String CCCD, String ten, int tuoi, String sdt, String email) {
        this.CCCD = CCCD;
        this.ten = ten;
        this.tuoi = tuoi;
        this.sdt = sdt;
        this.email = email;
    }

    // Getters và Setters cơ bản
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

    // Phương thức trừu tượng
    public abstract String getLoaiNguoi();
    public abstract void hienThiThongTin();

    // CRUD static cho Admin quản lý
    public static void Create(Nguoi nguoi) {
        if (nguoi == null || nguoi.getCCCD() == null || nguoi.getCCCD().trim().isEmpty() ||
            nguoi.getTen() == null || nguoi.getTen().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin người không được để trống.");
            return;
        }
        if (getNguoiByCCCD(nguoi.getCCCD()) != null) {
            System.out.println("Lỗi: Người này đã tồn tại.");
            return;
        }
        danhSachNguoi.add(nguoi);
        System.out.println("Đã thêm " + nguoi.getLoaiNguoi() + " thành công.");
    }

    public static ArrayList<Nguoi> Read() {
        if (danhSachNguoi.isEmpty()) {
            System.out.println("Danh sách người trống.");
            return new ArrayList<>();
        }
        System.out.println("Tổng số người: " + danhSachNguoi.size());
        return new ArrayList<>(danhSachNguoi);
    }

    public static void Read(String CCCD) {
        if (danhSachNguoi.isEmpty()) {
            System.out.println("Danh sách người trống.");
            return;
        }
        Nguoi nguoi = getNguoiByCCCD(CCCD);
        if (nguoi != null) {
            nguoi.hienThiThongTin();
        } else {
            System.out.println("Không tìm thấy người với CCCD: " + CCCD);
        }
    }

    public static void Update(String CCCD, Nguoi nguoi) {
        if (nguoi == null || nguoi.getCCCD() == null || nguoi.getCCCD().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin người không được để trống.");
            return;
        }
        int idx = getNguoiIndexByCCCD(CCCD);
        if (idx == -1) {
            System.out.println("Không tìm thấy người với CCCD: " + CCCD);
            return;
        }
        nguoi.setCCCD(CCCD);
        danhSachNguoi.set(idx, nguoi);
        System.out.println("Cập nhật " + nguoi.getLoaiNguoi() + " thành công.");
    }

    public static void Delete(String CCCD) {
        int idx = getNguoiIndexByCCCD(CCCD);
        if (idx == -1) {
            System.out.println("Không tìm thấy người với CCCD: " + CCCD);
            return;
        }
        Nguoi nguoi = danhSachNguoi.get(idx);
        danhSachNguoi.remove(idx);
        System.out.println("Đã xóa " + nguoi.getLoaiNguoi() + " thành công.");
    }

    // Phương thức tìm kiếm
    public static Nguoi getNguoiByCCCD(String CCCD) {
        for (Nguoi nguoi : danhSachNguoi) {
            if (nguoi.getCCCD() != null && nguoi.getCCCD().equals(CCCD)) {
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

    // Phương thức tìm kiếm theo loại người
    public static ArrayList<Nguoi> getNguoiByLoai(String loaiNguoi) {
        ArrayList<Nguoi> result = new ArrayList<>();
        for (Nguoi nguoi : danhSachNguoi) {
            if (nguoi.getLoaiNguoi().equals(loaiNguoi)) {
                result.add(nguoi);
            }
        }
        return result;
    }

    // Phương thức thống kê cho Admin
    public static void xemThongKe() {
        if (danhSachNguoi.isEmpty()) {
            System.out.println("Danh sách người trống.");
            return;
        }
        System.out.println("=== THỐNG KÊ TỔNG QUAN ===");
        System.out.println("Tổng số người: " + danhSachNguoi.size());
        // Thống kê theo loại người
        int soKhachHang = 0;
        int soNhanVien = 0;
        for (Nguoi nguoi : danhSachNguoi) {
            if (nguoi.getLoaiNguoi().equals("Khách Hàng")) {
                soKhachHang++;
            } else if (nguoi.getLoaiNguoi().equals("Nhân Viên")) {
                soNhanVien++;
            }
        }
        System.out.println("Số khách hàng: " + soKhachHang);
        System.out.println("Số nhân viên: " + soNhanVien);
    }
} 