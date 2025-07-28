import java.util.ArrayList;

public class KhachHang extends Nguoi {
    private String gioiTinh; // Nam, Nu, Khac
    private ArrayList<Ve> lichSuDatVe;
    private static ArrayList<KhachHang> danhSachKhachHang = new ArrayList<>();

    public KhachHang() {
        super();
    }

    public KhachHang(String CCCD, String tenKH, int tuoi, String sdt, String email, String gioiTinh,
            ArrayList<Ve> lichSuDatVe) {
        super(CCCD, tenKH, tuoi, sdt, email);
        this.gioiTinh = gioiTinh;
        this.lichSuDatVe = (lichSuDatVe != null) ? lichSuDatVe : new ArrayList<>();
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public ArrayList<Ve> getLichSuDatVe() {
        return lichSuDatVe;
    }

    public void setLichSuDatVe(ArrayList<Ve> lichSuDatVe) {
        this.lichSuDatVe = lichSuDatVe;
    }

    public void themVe(Ve ve) {
        if (lichSuDatVe == null) {
            lichSuDatVe = new ArrayList<>();
        }
        lichSuDatVe.add(ve);
    }

    // CRUD cho Admin quản lý khách hàng
    public static void Create(KhachHang kh) {
        try {
            if (kh == null || kh.getCCCD() == null || kh.getCCCD().trim().isEmpty() ||
                    kh.getTen() == null || kh.getTen().trim().isEmpty()) {
                System.out.println("Lỗi: Thông tin khách hàng không được để trống.");
                return;
            }

            if (getKhachHangByCCCD(kh.getCCCD()) != null) {
                System.out.println("Lỗi: Khách hàng đã tồn tại.");
                return;
            }

            danhSachKhachHang.add(kh);
            // Thêm vào danh sách Nguoi để quản lý tài khoản
            Nguoi.Create(kh);
            System.out.println("Đã thêm khách hàng thành công.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<KhachHang> ReadKhachHang() {
        try {
            if (danhSachKhachHang.isEmpty()) {
                System.out.println("Danh sách khách hàng trống.");
            } else {
                for (KhachHang kh : danhSachKhachHang) {
                    kh.hienThiThongTin();
                }
            }
            System.out.println("Tổng số khách hàng: " + danhSachKhachHang.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(danhSachKhachHang);
    }

    public static void Read(String CCCD) {
        try {
            if (danhSachKhachHang.isEmpty()) {
                System.out.println("Danh sách khách hàng trống.");
                return;
            }
            KhachHang kh = getKhachHangByCCCD(CCCD);
            if (kh != null) {
                kh.hienThiThongTin();
            } else {
                System.out.println("Không tìm thấy khách hàng với CCCD: " + CCCD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Update(String CCCD, KhachHang kh) {
        try {
            if (kh == null || kh.getCCCD() == null || kh.getCCCD().trim().isEmpty() ||
                    kh.getTen() == null || kh.getTen().trim().isEmpty()) {
                System.out.println("Lỗi: Thông tin khách hàng không được để trống.");
                return;
            }
            int index = getKhachHangIndexByCCCD(CCCD);
            if (index != -1) {
                kh.setCCCD(CCCD);
                danhSachKhachHang.set(index, kh);
                // Cập nhật trong danh sách Nguoi
                Nguoi.Update(CCCD, kh);
                System.out.println("Cập nhật thông tin khách hàng thành công.");
            } else {
                System.out.println("Không tìm thấy khách hàng với CCCD đã nhập.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Delete(String CCCD) {
        try {
            int index = getKhachHangIndexByCCCD(CCCD);
            if (index != -1) {
                danhSachKhachHang.remove(index);
                // Xóa khỏi danh sách Nguoi
                Nguoi.Delete(CCCD);
                System.out.println("Đã xóa khách hàng thành công.");
            } else {
                System.out.println("Không tìm thấy khách hàng với CCCD đã nhập.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static KhachHang getKhachHangByCCCD(String CCCD) {
        for (KhachHang kh : danhSachKhachHang) {
            if (kh.getCCCD().equalsIgnoreCase(CCCD))
                return kh;
        }
        return null;
    }

    private static int getKhachHangIndexByCCCD(String CCCD) {
        for (int i = 0; i < danhSachKhachHang.size(); i++) {
            if (danhSachKhachHang.get(i).getCCCD().equalsIgnoreCase(CCCD))
                return i;
        }
        return -1;
    }

    @Override
    public String getLoaiNguoi() {
        return "Khách Hàng";
    }

    @Override
    public void hienThiThongTin() {
        System.out.println("CCCD: " + getCCCD());
        System.out.println("Tên khách hàng: " + getTen());
        System.out.println("Tuổi: " + getTuoi());
        System.out.println("SĐT: " + getSdt());
        System.out.println("Email: " + getEmail());
        System.out.println("Giới tính: " + gioiTinh);
        System.out.println("Số vé đã đặt: " + (lichSuDatVe != null ? lichSuDatVe.size() : 0));
    }
}
