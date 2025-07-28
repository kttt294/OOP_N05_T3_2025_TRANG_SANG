import java.time.LocalDateTime;
import java.util.ArrayList;

public class ThongBao {
    private String maThongBao;
    private String maNguoiNhan; // Có thể là mã khách hàng hoặc nhân viên
    private String noiDung;
    private LocalDateTime thoiGianGui;
    private boolean trangThai; // true: đã đọc, false: chưa đọc

    private static ArrayList<ThongBao> danhSachThongBao = new ArrayList<>();

    public ThongBao() {}
    public ThongBao(String maThongBao, String maNguoiNhan, String noiDung, LocalDateTime thoiGianGui, boolean trangThai) {
        this.maThongBao = maThongBao;
        this.maNguoiNhan = maNguoiNhan;
        this.noiDung = noiDung;
        this.thoiGianGui = thoiGianGui;
        this.trangThai = trangThai;
    }

    public String getMaThongBao() { return maThongBao; }
    public void setMaThongBao(String maThongBao) { this.maThongBao = maThongBao; }
    public String getMaNguoiNhan() { return maNguoiNhan; }
    public void setMaNguoiNhan(String maNguoiNhan) { this.maNguoiNhan = maNguoiNhan; }
    public String getNoiDung() { return noiDung; }
    public void setNoiDung(String noiDung) { this.noiDung = noiDung; }
    public LocalDateTime getThoiGianGui() { return thoiGianGui; }
    public void setThoiGianGui(LocalDateTime thoiGianGui) { this.thoiGianGui = thoiGianGui; }
    public boolean isTrangThai() { return trangThai; }
    public void setTrangThai(boolean trangThai) { this.trangThai = trangThai; }

    // CRUD
    public static void Create(ThongBao tb) {
        if (tb == null || tb.getMaThongBao() == null || tb.getMaThongBao().trim().isEmpty() ||
            tb.getMaNguoiNhan() == null || tb.getMaNguoiNhan().trim().isEmpty() ||
            tb.getNoiDung() == null || tb.getNoiDung().trim().isEmpty() ||
            tb.getThoiGianGui() == null) {
            System.out.println("Lỗi: Thông tin thông báo không được để trống.");
            return;
        }
        if (getByMaThongBao(tb.getMaThongBao()) != null) {
            System.out.println("Lỗi: Thông báo đã tồn tại.");
            return;
        }
        danhSachThongBao.add(tb);
        System.out.println("Đã thêm thông báo thành công.");
    }
    public static ArrayList<ThongBao> Read() {
        if (danhSachThongBao.isEmpty()) {
            System.out.println("Danh sách thông báo trống.");
            return new ArrayList<>();
        }
        System.out.println("Tổng số thông báo: " + danhSachThongBao.size());
        return new ArrayList<>(danhSachThongBao);
    }
    public static void Read(String maThongBao) {
        if (danhSachThongBao.isEmpty()) {
            System.out.println("Danh sách thông báo trống.");
            return;
        }
        ThongBao tb = getByMaThongBao(maThongBao);
        if (tb != null) {
            tb.hienThiThongTin();
        } else {
            System.out.println("Không tìm thấy thông báo với mã: " + maThongBao);
        }
    }
    public static void Update(String maThongBao, ThongBao tb) {
        if (tb == null || tb.getMaThongBao() == null || tb.getMaThongBao().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin thông báo không được để trống.");
            return;
        }
        int idx = getIndexByMaThongBao(maThongBao);
        if (idx == -1) {
            System.out.println("Không tìm thấy thông báo với mã: " + maThongBao);
            return;
        }
        tb.setMaThongBao(maThongBao);
        danhSachThongBao.set(idx, tb);
        System.out.println("Cập nhật thông báo thành công.");
    }
    public static void Delete(String maThongBao) {
        int idx = getIndexByMaThongBao(maThongBao);
        if (idx == -1) {
            System.out.println("Không tìm thấy thông báo với mã: " + maThongBao);
            return;
        }
        danhSachThongBao.remove(idx);
        System.out.println("Đã xóa thông báo thành công.");
    }
    public static ThongBao getByMaThongBao(String maThongBao) {
        for (ThongBao tb : danhSachThongBao) {
            if (tb.getMaThongBao().equalsIgnoreCase(maThongBao)) return tb;
        }
        return null;
    }
    public static int getIndexByMaThongBao(String maThongBao) {
        for (int i = 0; i < danhSachThongBao.size(); i++) {
            if (danhSachThongBao.get(i).getMaThongBao().equalsIgnoreCase(maThongBao)) return i;
        }
        return -1;
    }
    public void hienThiThongTin() {
        System.out.println("=== THÔNG TIN THÔNG BÁO ===");
        System.out.println("Mã thông báo: " + this.maThongBao);
        System.out.println("Mã người nhận: " + this.maNguoiNhan);
        System.out.println("Nội dung: " + this.noiDung);
        System.out.println("Thời gian gửi: " + this.thoiGianGui);
        System.out.println("Trạng thái: " + (this.trangThai ? "Đã đọc" : "Chưa đọc"));
        System.out.println("===========================");
    }
} 