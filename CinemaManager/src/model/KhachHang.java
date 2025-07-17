import java.util.ArrayList;

public class KhachHang extends Nguoi {
    private String gioiTinh; // Nam, Nu, Khac
    private ArrayList<Ve> lichSuDatVe;
    private TaiKhoan taiKhoan;
    private static ArrayList<KhachHang> danhSachKhachHang = new ArrayList<>();

    public KhachHang() {
        super();
    }

    public KhachHang(String CCCD, String tenKH, int tuoi, String sdt, String email, String gioiTinh, ArrayList<Ve> lichSuDatVe, TaiKhoan taiKhoan) {
        super(CCCD, tenKH, tuoi, sdt, email);
        this.gioiTinh = gioiTinh;
        this.lichSuDatVe = (lichSuDatVe != null) ? lichSuDatVe : new ArrayList<>();
        this.taiKhoan = taiKhoan;
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

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }
    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public void themVe(Ve ve) {
        if (lichSuDatVe == null) {
            lichSuDatVe = new ArrayList<>();
        }
        lichSuDatVe.add(ve);
    }

    public static void Create(KhachHang kh) {
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
        System.out.println("Đã thêm khách hàng thành công.");
    }

    public static void Read(String CCCD) {
        if (danhSachKhachHang.isEmpty()) {
            System.out.println("Danh sách khách hàng trống.");
        } else {
            KhachHang kh = getKhachHangByCCCD(CCCD);
            if (kh != null) {
                kh.hienThiThongTin();
            } else {
                System.out.println("Không tìm thấy khách hàng với CCCD đã nhập!");
            }
        }
    }

    public static void Update(String CCCD, KhachHang kh) {
        if (kh == null || kh.getCCCD() == null || kh.getCCCD().trim().isEmpty() ||
            kh.getTen() == null || kh.getTen().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin khách hàng không được để trống.");
            return;
        }
        int index = getKhachHangIndexByCCCD(CCCD);
        if (index != -1) {
            kh.setCCCD(CCCD);
            danhSachKhachHang.set(index, kh);
            System.out.println("Cập nhật thông tin khách hàng thành công.");
        } else {
            System.out.println("Không tìm thấy khách hàng với CCCD đã nhập.");
        }
    }

    public static void Delete(String CCCD) {
        int index = getKhachHangIndexByCCCD(CCCD);
        if (index != -1) {
            danhSachKhachHang.remove(index);
            System.out.println("Đã xóa khách hàng thành công.");
        } else {
            System.out.println("Không tìm thấy khách hàng với CCCD đã nhập.");
        }
    }

    public static KhachHang getKhachHangByCCCD(String CCCD) {
        for (KhachHang kh : danhSachKhachHang) {
            if (kh.getCCCD().equalsIgnoreCase(CCCD)) return kh;
        }
        return null;
    }

    private static int getKhachHangIndexByCCCD(String CCCD) {
        for (int i = 0; i < danhSachKhachHang.size(); i++) {
            if (danhSachKhachHang.get(i).getCCCD().equalsIgnoreCase(CCCD)) return i;
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
    }
}
