import java.time.LocalDateTime;
import java.util.ArrayList;

public class HoaDon {
    private String maHoaDon;
    private KhachHang khachHang;
    private Ve veDaDat;
    private DoAn doAn;
    private int tongTien;
    private LocalDateTime thoiGianThanhToan;
    private String phuongThucThanhToan; // Tiền mặt hoặc chuyển khoản

    private static java.util.ArrayList<HoaDon> danhSachHoaDon = new java.util.ArrayList<>();

    public HoaDon(){};

    public HoaDon(String maHoaDon, KhachHang khachHang, Ve veDaDat, DoAn doAn,
                  int tongTien, LocalDateTime thoiGianThanhToan, String phuongThucThanhToan) {
        this.maHoaDon = maHoaDon;
        this.khachHang = khachHang;
        this.veDaDat = veDaDat;
        this.doAn = doAn;
        this.tongTien = tongTien;
        this.thoiGianThanhToan = thoiGianThanhToan;
        this.phuongThucThanhToan = phuongThucThanhToan;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public Ve getVeDaDat() {
        return veDaDat;
    }

    public void setVeDaDat(Ve veDaDat) {
        this.veDaDat = veDaDat;
    }

    public DoAn getDoAn() {
        return doAn;
    }

    public void setDoAn(DoAn doAn) {
        this.doAn = doAn;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public LocalDateTime getThoiGianThanhToan() {
        return thoiGianThanhToan;
    }

    public void setThoiGianThanhToan(LocalDateTime thoiGianThanhToan) {
        this.thoiGianThanhToan = thoiGianThanhToan;
    }

    public String getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }

    public void setPhuongThucThanhToan(String phuongThucThanhToan) {
        this.phuongThucThanhToan = phuongThucThanhToan;
    }

    // CRUD static
    public static void Create(HoaDon hd) {
        if (hd == null || hd.getMaHoaDon() == null || hd.getMaHoaDon().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin hóa đơn không được để trống.");
            return;
        }
        if (getHoaDonByMa(hd.getMaHoaDon()) != null) {
            System.out.println("Lỗi: Hóa đơn đã tồn tại.");
            return;
        }
        danhSachHoaDon.add(hd);
        System.out.println("Đã thêm hóa đơn thành công.");
    }

    // Read toàn bộ danh sách hóa đơn
    public static ArrayList<HoaDon> Read() {
        if (danhSachHoaDon.isEmpty()) {
            System.out.println("Danh sách hóa đơn trống.");
            return new java.util.ArrayList<>();
        }
        System.out.println("Tổng số hóa đơn: " + danhSachHoaDon.size());
        return new java.util.ArrayList<>(danhSachHoaDon);
    }
    
    // Read hóa đơn theo mã
    public static void Read(String maHoaDon) {
        if (danhSachHoaDon.isEmpty()) {
            System.out.println("Danh sách hóa đơn trống.");
            return;
        }
        HoaDon hd = getHoaDonByMa(maHoaDon);
        if (hd != null) {
            hd.hienThiThongTin();
        } else {
            System.out.println("Không tìm thấy hóa đơn với mã: " + maHoaDon);
        }
    }
    
    // Hiển thị thông tin chi tiết của một hóa đơn
    public void hienThiThongTin() {
        System.out.println("=== THÔNG TIN HÓA ĐƠN ===");
        System.out.println("Mã hóa đơn: " + this.maHoaDon);
        System.out.println("Khách hàng: " + (this.khachHang != null ? this.khachHang.getCCCD() : "N/A"));
        System.out.println("Vé đã đặt: " + (this.veDaDat != null ? this.veDaDat.getMaVe() : "N/A"));
        System.out.println("Đồ ăn: " + (this.doAn != null ? this.doAn.getMaDoAn() : "N/A"));
        System.out.println("Tổng tiền: " + this.tongTien + " VNĐ");
        System.out.println("Thời gian thanh toán: " + this.thoiGianThanhToan);
        System.out.println("Phương thức thanh toán: " + this.phuongThucThanhToan);
        System.out.println("========================");
    }

    public static void Update(String maHoaDon, HoaDon hd) {
        if (hd == null || hd.getMaHoaDon() == null || hd.getMaHoaDon().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin hóa đơn không được để trống.");
            return;
        }
        int index = getHoaDonIndexByMa(maHoaDon);
        if (index != -1) {
            hd.setMaHoaDon(maHoaDon);
            danhSachHoaDon.set(index, hd);
            System.out.println("Cập nhật thông tin hóa đơn thành công.");
        } else {
            System.out.println("Không tìm thấy hóa đơn với mã đã nhập.");
        }
    }

    public static void Delete(String maHoaDon) {
        int index = getHoaDonIndexByMa(maHoaDon);
        if (index != -1) {
            danhSachHoaDon.remove(index);
            System.out.println("Đã xóa hóa đơn thành công.");
        } else {
            System.out.println("Không tìm thấy hóa đơn với mã đã nhập.");
        }
    }

    public static HoaDon getHoaDonByMa(String maHoaDon) {
        for (HoaDon hd : danhSachHoaDon) {
            if (hd.getMaHoaDon().equalsIgnoreCase(maHoaDon)) return hd;
        }
        return null;
    }

    private static int getHoaDonIndexByMa(String maHoaDon) {
        for (int i = 0; i < danhSachHoaDon.size(); i++) {
            if (danhSachHoaDon.get(i).getMaHoaDon().equalsIgnoreCase(maHoaDon)) return i;
        }
        return -1;
    }
}

