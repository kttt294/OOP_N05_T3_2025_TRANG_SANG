import java.time.LocalDateTime;
import java.util.ArrayList;

public class GiuGhe {
    private String maHold;
    private String maGhe;
    private String maSuatChieu;
    private String maKhachHang;
    private LocalDateTime thoiGianBatDauHold;
    private LocalDateTime thoiGianKetThucHold;
    private String trangThai; // DANG_GIU, DA_HUY, DA_DAT_THANH_CONG

    private static ArrayList<GiuGhe> danhSachHold = new ArrayList<>();

    public GiuGhe() {}
    public GiuGhe(String maHold, String maGhe, String maSuatChieu, String maKhachHang, LocalDateTime thoiGianBatDauHold, LocalDateTime thoiGianKetThucHold, String trangThai) {
        this.maHold = maHold;
        this.maGhe = maGhe;
        this.maSuatChieu = maSuatChieu;
        this.maKhachHang = maKhachHang;
        this.thoiGianBatDauHold = thoiGianBatDauHold;
        this.thoiGianKetThucHold = thoiGianKetThucHold;
        this.trangThai = trangThai;
    }

    public String getMaHold() { return maHold; }
    public void setMaHold(String maHold) { this.maHold = maHold; }
    public String getMaGhe() { return maGhe; }
    public void setMaGhe(String maGhe) { this.maGhe = maGhe; }
    public String getMaSuatChieu() { return maSuatChieu; }
    public void setMaSuatChieu(String maSuatChieu) { this.maSuatChieu = maSuatChieu; }
    public String getMaKhachHang() { return maKhachHang; }
    public void setMaKhachHang(String maKhachHang) { this.maKhachHang = maKhachHang; }
    public LocalDateTime getThoiGianBatDauHold() { return thoiGianBatDauHold; }
    public void setThoiGianBatDauHold(LocalDateTime thoiGianBatDauHold) { this.thoiGianBatDauHold = thoiGianBatDauHold; }
    public LocalDateTime getThoiGianKetThucHold() { return thoiGianKetThucHold; }
    public void setThoiGianKetThucHold(LocalDateTime thoiGianKetThucHold) { this.thoiGianKetThucHold = thoiGianKetThucHold; }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    // CRUD
    public static void Create(GiuGhe hold) {
        if (hold == null || hold.getMaHold() == null || hold.getMaHold().trim().isEmpty() ||
            hold.getMaGhe() == null || hold.getMaGhe().trim().isEmpty() ||
            hold.getMaSuatChieu() == null || hold.getMaSuatChieu().trim().isEmpty() ||
            hold.getMaKhachHang() == null || hold.getMaKhachHang().trim().isEmpty() ||
            hold.getThoiGianBatDauHold() == null || hold.getThoiGianKetThucHold() == null ||
            hold.getTrangThai() == null || hold.getTrangThai().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin giữ ghế không được để trống.");
            return;
        }
        if (getByMaHold(hold.getMaHold()) != null) {
            System.out.println("Lỗi: Mã giữ ghế đã tồn tại.");
            return;
        }
        danhSachHold.add(hold);
        System.out.println("Đã thêm giữ ghế thành công.");
    }
    public static ArrayList<GiuGhe> Read() {
        if (danhSachHold.isEmpty()) {
            System.out.println("Danh sách giữ ghế trống.");
            return new ArrayList<>();
        }
        System.out.println("Tổng số giữ ghế: " + danhSachHold.size());
        return new ArrayList<>(danhSachHold);
    }
    public static void Read(String maHold) {
        if (danhSachHold.isEmpty()) {
            System.out.println("Danh sách giữ ghế trống.");
            return;
        }
        GiuGhe hold = getByMaHold(maHold);
        if (hold != null) {
            hold.hienThiThongTin();
        } else {
            System.out.println("Không tìm thấy giữ ghế với mã: " + maHold);
        }
    }
    public static void Update(String maHold, GiuGhe hold) {
        if (hold == null || hold.getMaHold() == null || hold.getMaHold().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin giữ ghế không được để trống.");
            return;
        }
        int idx = getIndexByMaHold(maHold);
        if (idx == -1) {
            System.out.println("Không tìm thấy giữ ghế với mã: " + maHold);
            return;
        }
        hold.setMaHold(maHold);
        danhSachHold.set(idx, hold);
        System.out.println("Cập nhật giữ ghế thành công.");
    }
    public static void Delete(String maHold) {
        int idx = getIndexByMaHold(maHold);
        if (idx == -1) {
            System.out.println("Không tìm thấy giữ ghế với mã: " + maHold);
            return;
        }
        danhSachHold.remove(idx);
        System.out.println("Đã xóa giữ ghế thành công.");
    }
    public static GiuGhe getByMaHold(String maHold) {
        for (GiuGhe hold : danhSachHold) {
            if (hold.getMaHold().equalsIgnoreCase(maHold)) return hold;
        }
        return null;
    }
    public static int getIndexByMaHold(String maHold) {
        for (int i = 0; i < danhSachHold.size(); i++) {
            if (danhSachHold.get(i).getMaHold().equalsIgnoreCase(maHold)) return i;
        }
        return -1;
    }
    public void hienThiThongTin() {
        System.out.println("=== THÔNG TIN GIỮ GHẾ TẠM THỜI ===");
        System.out.println("Mã giữ ghế: " + this.maHold);
        System.out.println("Mã ghế: " + this.maGhe);
        System.out.println("Mã suất chiếu: " + this.maSuatChieu);
        System.out.println("Mã khách hàng: " + this.maKhachHang);
        System.out.println("Thời gian bắt đầu giữ: " + this.thoiGianBatDauHold);
        System.out.println("Thời gian kết thúc giữ: " + this.thoiGianKetThucHold);
        System.out.println("Trạng thái: " + this.trangThai);
        System.out.println("====================================");
    }
} 