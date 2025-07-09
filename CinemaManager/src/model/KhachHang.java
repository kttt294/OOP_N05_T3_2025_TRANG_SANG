maApackage model;

import java.util.List;
import java.util.ArrayList;

public class KhachHang {
    private String maKH;
    private String tenKH;
    private int tuoi;
    private String sdt;
    private String email;
    private String gioiTinh;   // Nam, Nu, Khac
    private TaiKhoan taiKhoan;
    private List<Ve> lichSuDatVe;
    
    public KhachHang(){};
    
    public KhachHang(String maKH, String tenKH, int tuoi, String sdt, String email, String gioiTinh, List<Ve> lichSuDatVe) {
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

    public void hienThiThongTin() {
        System.out.println("Mã KH: " + maKH);
        System.out.println("Tên KH: " + tenKH);
        System.out.println("Tuổi: " + tuoi);
        System.out.println("SĐT: " + sdt);
        System.out.println("Email: " + email);
        System.out.println("Giới tính: " + gioiTinh);
    }

    public void xemLichSuDatVe() {
        if (lichSuDatVe == null || lichSuDatVe.isEmpty()) {
            System.out.println("Chưa có vé nào.");
        } else {
            System.out.println("Lịch sử đặt vé:");
            for (Ve ve : lichSuDatVe) {
                ve.hienThiThongTin();
            }
        }
    }
}
