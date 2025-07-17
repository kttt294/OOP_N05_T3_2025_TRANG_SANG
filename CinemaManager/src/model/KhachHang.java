import java.util.ArrayList;

public class KhachHang extends Nguoi {
    private String maKH;
    private String gioiTinh; // Nam, Nu, Khac
    private ArrayList<Ve> lichSuDatVe;
    private TaiKhoan taiKhoan;

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
        // Có thể in thêm lịch sử đặt vé nếu muốn
    }
}
