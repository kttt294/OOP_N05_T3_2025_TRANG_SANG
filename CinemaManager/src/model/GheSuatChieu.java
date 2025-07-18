import java.util.ArrayList;

public class GheSuatChieu {
    private String maGhe;
    private String maSuatChieu;
    private double giaGhe;
    private String trangThai; // "BinhThuong", "Khoa", ...
    private String loaiGhe;   // Có thể khác với mặc định
    private String moTa;

    private static ArrayList<GheSuatChieu> danhSachGheSuatChieu = new ArrayList<>();

    public GheSuatChieu(String maGhe, String maSuatChieu, double giaGhe, String trangThai, String loaiGhe, String moTa) {
        setMaGhe(maGhe);
        setMaSuatChieu(maSuatChieu);
        setGiaGhe(giaGhe);
        setTrangThai(trangThai);
        setLoaiGhe(loaiGhe);
        setMoTa(moTa);
    }

    public String getMaGhe() { return maGhe; }
    public void setMaGhe(String maGhe) {
        if (maGhe == null || maGhe.trim().isEmpty())
            throw new IllegalArgumentException("Mã ghế không được để trống!");
        this.maGhe = maGhe.trim();
    }
    public String getMaSuatChieu() { return maSuatChieu; }
    public void setMaSuatChieu(String maSuatChieu) {
        if (maSuatChieu == null || maSuatChieu.trim().isEmpty())
            throw new IllegalArgumentException("Mã suất chiếu không được để trống!");
        this.maSuatChieu = maSuatChieu.trim();
    }
    public double getGiaGhe() { return giaGhe; }
    public void setGiaGhe(double giaGhe) {
        if (giaGhe < 0) throw new IllegalArgumentException("Giá ghế không hợp lệ!");
        this.giaGhe = giaGhe;
    }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) {
        this.trangThai = (trangThai != null) ? trangThai.trim() : "BinhThuong";
    }
    public String getLoaiGhe() { return loaiGhe; }
    public void setLoaiGhe(String loaiGhe) {
        this.loaiGhe = (loaiGhe != null) ? loaiGhe.trim() : "Thuong";
    }
    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) {
        this.moTa = (moTa != null) ? moTa.trim() : "";
    }

    // CRUD static
    public static void Create(GheSuatChieu gsc) {
        if (gsc == null || gsc.getMaGhe() == null || gsc.getMaGhe().trim().isEmpty() ||
            gsc.getMaSuatChieu() == null || gsc.getMaSuatChieu().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin ghế suất chiếu không được để trống.");
            return;
        }
        if (getByMaGheAndMaSuatChieu(gsc.getMaGhe(), gsc.getMaSuatChieu()) != null) {
            System.out.println("Lỗi: Ghế suất chiếu đã tồn tại.");
            return;
        }
        danhSachGheSuatChieu.add(gsc);
        System.out.println("Đã thêm ghế suất chiếu thành công.");
    }
    
    // Read toàn bộ danh sách ghế suất chiếu
    public static ArrayList<GheSuatChieu> Read() {
        if (danhSachGheSuatChieu.isEmpty()) {
            System.out.println("Danh sách ghế suất chiếu trống.");
            return new ArrayList<>();
        }
        System.out.println("Tổng số ghế suất chiếu: " + danhSachGheSuatChieu.size());
        return new ArrayList<>(danhSachGheSuatChieu);
    }
    
    // Read ghế suất chiếu theo mã ghế và mã suất chiếu
    public static void Read(String maGhe, String maSuatChieu) {
        if (danhSachGheSuatChieu.isEmpty()) {
            System.out.println("Danh sách ghế suất chiếu trống.");
            return;
        }
        GheSuatChieu gsc = getByMaGheAndMaSuatChieu(maGhe, maSuatChieu);
        if (gsc != null) {
            gsc.hienThiThongTin();
        } else {
            System.out.println("Không tìm thấy ghế suất chiếu với mã ghế: " + maGhe + " và mã suất chiếu: " + maSuatChieu);
        }
    }
    
    public static void Update(String maGhe, String maSuatChieu, GheSuatChieu gsc) {
        if (gsc == null || gsc.getMaGhe() == null || gsc.getMaGhe().trim().isEmpty() ||
            gsc.getMaSuatChieu() == null || gsc.getMaSuatChieu().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin ghế suất chiếu không được để trống.");
            return;
        }
        int idx = getIndexByMaGheAndMaSuatChieu(maGhe, maSuatChieu);
        if (idx == -1) {
            System.out.println("Không tìm thấy ghế suất chiếu với mã ghế: " + maGhe + " và mã suất chiếu: " + maSuatChieu);
            return;
        }
        gsc.setMaGhe(maGhe);
        gsc.setMaSuatChieu(maSuatChieu);
        danhSachGheSuatChieu.set(idx, gsc);
        System.out.println("Cập nhật ghế suất chiếu thành công.");
    }
    
    public static void Delete(String maGhe, String maSuatChieu) {
        int idx = getIndexByMaGheAndMaSuatChieu(maGhe, maSuatChieu);
        if (idx == -1) {
            System.out.println("Không tìm thấy ghế suất chiếu với mã ghế: " + maGhe + " và mã suất chiếu: " + maSuatChieu);
            return;
        }
        danhSachGheSuatChieu.remove(idx);
        System.out.println("Đã xóa ghế suất chiếu thành công.");
    }
    public static GheSuatChieu getByMaGheAndMaSuatChieu(String maGhe, String maSuatChieu) {
        for (GheSuatChieu gsc : danhSachGheSuatChieu) {
            if (gsc.getMaGhe().equalsIgnoreCase(maGhe) && gsc.getMaSuatChieu().equalsIgnoreCase(maSuatChieu))
                return gsc;
        }
        return null;
    }
    public static int getIndexByMaGheAndMaSuatChieu(String maGhe, String maSuatChieu) {
        for (int i = 0; i < danhSachGheSuatChieu.size(); i++) {
            GheSuatChieu gsc = danhSachGheSuatChieu.get(i);
            if (gsc.getMaGhe().equalsIgnoreCase(maGhe) && gsc.getMaSuatChieu().equalsIgnoreCase(maSuatChieu))
                return i;
        }
        return -1;
    }
    
    // Hiển thị thông tin chi tiết của một ghế suất chiếu
    public void hienThiThongTin() {
        System.out.println("=== THÔNG TIN GHẾ SUẤT CHIẾU ===");
        System.out.println("Mã ghế: " + this.maGhe);
        System.out.println("Mã suất chiếu: " + this.maSuatChieu);
        System.out.println("Giá ghế: " + this.giaGhe + " VNĐ");
        System.out.println("Trạng thái: " + this.trangThai);
        System.out.println("Loại ghế: " + this.loaiGhe);
        System.out.println("Mô tả: " + this.moTa);
        System.out.println("================================");
    }
} 