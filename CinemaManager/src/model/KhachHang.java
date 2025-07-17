import java.util.List;
import java.util.ArrayList;

public class KhachHang {
    private String maKH;
    private String tenKH;
    private int tuoi;
    private String sdt;
    private String email;
    private String gioiTinh; // Nam, Nu, Khac
    private List<Ve> lichSuDatVe;
    // thêm TaiKhoan taikhoan

    public KhachHang() {
    };

    public KhachHang(String maKH, String tenKH, int tuoi, String sdt, String email, String gioiTinh,
            List<Ve> lichSuDatVe) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.tuoi = tuoi;
        this.sdt = sdt;
        this.email = email;
        this.gioiTinh = gioiTinh;
        this.lichSuDatVe = (lichSuDatVe != null) ? lichSuDatVe : new ArrayList<>();
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
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

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public List<Ve> getLichSuDatVe() {
        return lichSuDatVe;
    }

    public void setLichSuDatVe(List<Ve> lichSuDatVe) {
        this.lichSuDatVe = lichSuDatVe;
    }

    public void themVe(Ve ve) {
        if (lichSuDatVe == null) {
            lichSuDatVe = new ArrayList<>();
        }
        this.lichSuDatVe.add(ve);
    }

    private static List<KhachHang> danhSachKH = new ArrayList<>();

    // CRUD

    public static void createKhachHang(KhachHang kh) {
        danhSachKH.add(kh);
        System.out.println("Đã thêm khách hàng thành công.");
    }

    public static void readKhachHang(String maKH) {
        if (danhSachKH.isEmpty()) {
            System.out.println("Danh sách khách hàng trống.");
        } else {
            boolean check = true;
            for (KhachHang p : danhSachKH) {
                if (p.getMaKH().equalsIgnoreCase(maKH)) {
                    System.out.println("Mã khách hàng: " + maKH);
                    System.out.println("Tên khách hàng: " + p.getTenKH());
                    System.out.println("Tuổi: " + p.getTuoi());
                    System.out.println("Số điện thoại: " + p.getSdt());
                    System.out.println("Email: " + p.getEmail());
                    System.out.println("Lịch sử đặt vé: " + p.getLichSuDatVe());
                    check = false;
                    break;
                }
            }
            if (check)
                System.out.println("Không tìm thấy mã khách hàng cần tìm!");
        }
    }

    public static void updateKhachHang(String maKh, KhachHang kh) {
        boolean check = true;
        for (int i = 0; i < danhSachKH.size(); i++) {
            if (danhSachKH.get(i).getMaKH().equalsIgnoreCase(maKh)) {
                danhSachKH.set(i, kh);
                System.out.println("Đã cập nhật thông tin khách hàng thành công.");
                check = false;
                break;
            }
        }
        if(check) System.out.println("Không tìm thấy khách hàng với mã: " + maKh);
    }

    public static void deleteKhachHang(String maKh) {
        boolean check = true;
        for (KhachHang kh : danhSachKH) {
            if (kh.getMaKH().equalsIgnoreCase(maKh)) {
                danhSachKH.remove(kh);
                System.out.println("Đã xoá khách hàng thành công.");
                check = false;
            }
        }
        if(check) System.out.println("Không tìm thấy khách hàng với mã: " + maKh);

    }

    public static KhachHang getKhachHangById(String maKH) {
        return danhSachKH.stream()
                .filter(kh -> kh.getMaKH().equalsIgnoreCase(maKH))
                .findFirst()
                .orElse(null);
    }
}
