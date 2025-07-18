import java.time.LocalDateTime;
import java.util.ArrayList;

public class DanhGia {
    private String maDanhGia;
    private String maKhachHang;
    private String maPhim;
    private int soSao; // từ 0 đến 5
    private String binhLuan;
    private LocalDateTime thoiGian;

    private static ArrayList<DanhGia> danhSachDanhGia = new ArrayList<>();

    public DanhGia() {}

    public DanhGia(String maDanhGia, String maKhachHang, String maPhim, int soSao, String binhLuan, LocalDateTime thoiGian) {
        setMaDanhGia(maDanhGia);
        setMaKhachHang(maKhachHang);
        setMaPhim(maPhim);
        setSoSao(soSao);
        setBinhLuan(binhLuan);
        setThoiGian(thoiGian);
    }

    public String getMaDanhGia() { return maDanhGia; }
    public void setMaDanhGia(String maDanhGia) {
        if (maDanhGia == null || maDanhGia.trim().isEmpty())
            throw new IllegalArgumentException("Mã đánh giá không được để trống!");
        this.maDanhGia = maDanhGia.trim();
    }
    public String getMaKhachHang() { return maKhachHang; }
    public void setMaKhachHang(String maKhachHang) {
        if (maKhachHang == null || maKhachHang.trim().isEmpty())
            throw new IllegalArgumentException("Mã khách hàng không được để trống!");
        this.maKhachHang = maKhachHang.trim();
    }
    public String getMaPhim() { return maPhim; }
    public void setMaPhim(String maPhim) {
        if (maPhim == null || maPhim.trim().isEmpty())
            throw new IllegalArgumentException("Mã phim không được để trống!");
        this.maPhim = maPhim.trim();
    }
    public int getSoSao() { return soSao; }
    public void setSoSao(int soSao) {
        if (soSao < 0 || soSao > 5) throw new IllegalArgumentException("Số sao phải từ 0 đến 5!");
        this.soSao = soSao;
    }
    public String getBinhLuan() { return binhLuan; }
    public void setBinhLuan(String binhLuan) {
        this.binhLuan = (binhLuan != null) ? binhLuan.trim() : "";
    }
    public LocalDateTime getThoiGian() { return thoiGian; }
    public void setThoiGian(LocalDateTime thoiGian) {
        this.thoiGian = thoiGian;
    }

    // CRUD
    public static void Create(DanhGia dg) {
        if (dg == null || dg.getMaDanhGia() == null || dg.getMaDanhGia().trim().isEmpty() ||
            dg.getMaKhachHang() == null || dg.getMaKhachHang().trim().isEmpty() ||
            dg.getMaPhim() == null || dg.getMaPhim().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin đánh giá không được để trống.");
            return;
        }
        if (getDanhGiaByMa(dg.getMaDanhGia()) != null) {
            System.out.println("Lỗi: Đánh giá đã tồn tại.");
            return;
        }
        danhSachDanhGia.add(dg);
        System.out.println("Đã thêm đánh giá thành công.");
    }
    // Read toàn bộ danh sách đánh giá
    public static ArrayList<DanhGia> Read() {
        if (danhSachDanhGia.isEmpty()) {
            System.out.println("Danh sách đánh giá trống.");
            return new ArrayList<>();
        }
        System.out.println("Tổng số đánh giá: " + danhSachDanhGia.size());
        return new ArrayList<>(danhSachDanhGia);
    }
    
    // Read đánh giá theo mã
    public static void Read(String maDanhGia) {
        if (danhSachDanhGia.isEmpty()) {
            System.out.println("Danh sách đánh giá trống.");
            return;
        }
        DanhGia dg = getDanhGiaByMa(maDanhGia);
        if (dg != null) {
            dg.hienThiThongTin();
        } else {
            System.out.println("Không tìm thấy đánh giá với mã: " + maDanhGia);
        }
    }
    public static void Update(String maDanhGia, DanhGia dg) {
        if (dg == null || dg.getMaDanhGia() == null || dg.getMaDanhGia().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin đánh giá không được để trống.");
            return;
        }
        int idx = getDanhGiaIndexByMa(maDanhGia);
        if (idx == -1) {
            System.out.println("Không tìm thấy đánh giá với mã: " + maDanhGia);
            return;
        }
        dg.setMaDanhGia(maDanhGia);
        danhSachDanhGia.set(idx, dg);
        System.out.println("Cập nhật đánh giá thành công.");
    }
    
    public static void Delete(String maDanhGia) {
        int idx = getDanhGiaIndexByMa(maDanhGia);
        if (idx == -1) {
            System.out.println("Không tìm thấy đánh giá với mã: " + maDanhGia);
            return;
        }
        danhSachDanhGia.remove(idx);
        System.out.println("Đã xóa đánh giá thành công.");
    }
    public static DanhGia getDanhGiaByMa(String maDanhGia) {
        for (DanhGia dg : danhSachDanhGia) {
            if (dg.getMaDanhGia().equalsIgnoreCase(maDanhGia)) return dg;
        }
        return null;
    }
    public static int getDanhGiaIndexByMa(String maDanhGia) {
        for (int i = 0; i < danhSachDanhGia.size(); i++) {
            if (danhSachDanhGia.get(i).getMaDanhGia().equalsIgnoreCase(maDanhGia)) return i;
        }
        return -1;
    }
    
    // Hiển thị thông tin chi tiết của một đánh giá
    public void hienThiThongTin() {
        System.out.println("=== THÔNG TIN ĐÁNH GIÁ ===");
        System.out.println("Mã đánh giá: " + this.maDanhGia);
        System.out.println("Mã khách hàng: " + this.maKhachHang);
        System.out.println("Mã phim: " + this.maPhim);
        System.out.println("Số sao: " + this.soSao + "/5");
        System.out.println("Bình luận: " + this.binhLuan);
        System.out.println("Thời gian: " + this.thoiGian);
        System.out.println("=========================");
    }
}