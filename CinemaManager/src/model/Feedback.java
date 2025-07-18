import java.time.LocalDateTime;
import java.util.ArrayList;

public class Feedback {
    private String maFeedback;
    private String maKhachHang;
    private String noiDung;
    private LocalDateTime thoiGian;
    private String trangThaiXuLy; // mới, đang xử lý, đã xử lý
    private String loaiFeedback; // góp ý, khiếu nại, khác

    private static ArrayList<Feedback> danhSachFeedback = new ArrayList<>();

    public Feedback() {}
    public Feedback(String maFeedback, String maKhachHang, String noiDung, LocalDateTime thoiGian, String trangThaiXuLy, String loaiFeedback) {
        this.maFeedback = maFeedback;
        this.maKhachHang = maKhachHang;
        this.noiDung = noiDung;
        this.thoiGian = thoiGian;
        this.trangThaiXuLy = trangThaiXuLy;
        this.loaiFeedback = loaiFeedback;
    }

    public String getMaFeedback() { return maFeedback; }
    public void setMaFeedback(String maFeedback) { this.maFeedback = maFeedback; }
    public String getMaKhachHang() { return maKhachHang; }
    public void setMaKhachHang(String maKhachHang) { this.maKhachHang = maKhachHang; }
    public String getNoiDung() { return noiDung; }
    public void setNoiDung(String noiDung) { this.noiDung = noiDung; }
    public LocalDateTime getThoiGian() { return thoiGian; }
    public void setThoiGian(LocalDateTime thoiGian) { this.thoiGian = thoiGian; }
    public String getTrangThaiXuLy() { return trangThaiXuLy; }
    public void setTrangThaiXuLy(String trangThaiXuLy) { this.trangThaiXuLy = trangThaiXuLy; }
    public String getLoaiFeedback() { return loaiFeedback; }
    public void setLoaiFeedback(String loaiFeedback) { this.loaiFeedback = loaiFeedback; }

    // CRUD
    public static void Create(Feedback fb) {
        if (fb == null || fb.getMaFeedback() == null || fb.getMaFeedback().trim().isEmpty() ||
            fb.getMaKhachHang() == null || fb.getMaKhachHang().trim().isEmpty() ||
            fb.getNoiDung() == null || fb.getNoiDung().trim().isEmpty() ||
            fb.getThoiGian() == null || fb.getTrangThaiXuLy() == null || fb.getTrangThaiXuLy().trim().isEmpty() ||
            fb.getLoaiFeedback() == null || fb.getLoaiFeedback().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin feedback không được để trống.");
            return;
        }
        if (getByMaFeedback(fb.getMaFeedback()) != null) {
            System.out.println("Lỗi: Feedback đã tồn tại.");
            return;
        }
        danhSachFeedback.add(fb);
        System.out.println("Đã thêm feedback thành công.");
    }
    public static ArrayList<Feedback> Read() {
        if (danhSachFeedback.isEmpty()) {
            System.out.println("Danh sách feedback trống.");
            return new ArrayList<>();
        }
        System.out.println("Tổng số feedback: " + danhSachFeedback.size());
        return new ArrayList<>(danhSachFeedback);
    }
    public static void Read(String maFeedback) {
        if (danhSachFeedback.isEmpty()) {
            System.out.println("Danh sách feedback trống.");
            return;
        }
        Feedback fb = getByMaFeedback(maFeedback);
        if (fb != null) {
            fb.hienThiThongTin();
        } else {
            System.out.println("Không tìm thấy feedback với mã: " + maFeedback);
        }
    }
    public static void Update(String maFeedback, Feedback fb) {
        if (fb == null || fb.getMaFeedback() == null || fb.getMaFeedback().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin feedback không được để trống.");
            return;
        }
        int idx = getIndexByMaFeedback(maFeedback);
        if (idx == -1) {
            System.out.println("Không tìm thấy feedback với mã: " + maFeedback);
            return;
        }
        fb.setMaFeedback(maFeedback);
        danhSachFeedback.set(idx, fb);
        System.out.println("Cập nhật feedback thành công.");
    }
    public static void Delete(String maFeedback) {
        int idx = getIndexByMaFeedback(maFeedback);
        if (idx == -1) {
            System.out.println("Không tìm thấy feedback với mã: " + maFeedback);
            return;
        }
        danhSachFeedback.remove(idx);
        System.out.println("Đã xóa feedback thành công.");
    }
    public static Feedback getByMaFeedback(String maFeedback) {
        for (Feedback fb : danhSachFeedback) {
            if (fb.getMaFeedback().equalsIgnoreCase(maFeedback)) return fb;
        }
        return null;
    }
    public static int getIndexByMaFeedback(String maFeedback) {
        for (int i = 0; i < danhSachFeedback.size(); i++) {
            if (danhSachFeedback.get(i).getMaFeedback().equalsIgnoreCase(maFeedback)) return i;
        }
        return -1;
    }
    public void hienThiThongTin() {
        System.out.println("=== THÔNG TIN FEEDBACK ===");
        System.out.println("Mã feedback: " + this.maFeedback);
        System.out.println("Mã khách hàng: " + this.maKhachHang);
        System.out.println("Nội dung: " + this.noiDung);
        System.out.println("Thời gian: " + this.thoiGian);
        System.out.println("Trạng thái xử lý: " + this.trangThaiXuLy);
        System.out.println("Loại feedback: " + this.loaiFeedback);
        System.out.println("=========================");
    }
} 