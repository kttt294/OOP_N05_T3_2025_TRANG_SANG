import java.util.ArrayList;

public class Ve {
    private String maVe;
    private String maKH;
    private String maSuatChieu;
    private String maGhe;
    private double giaVe;
    private boolean daThanhToan;

    private static ArrayList<Ve> danhSachVe = new ArrayList<>();

    public Ve(){};
    
    public Ve(String maVe, String maKH, String maSuatChieu, String maGhe, double giaVe, boolean daThanhToan) {
        this.maVe = maVe;
        this.maKH = maKH;
        this.maSuatChieu = maSuatChieu;
        this.maGhe = maGhe;
        setGiaVe(giaVe);
        this.daThanhToan = daThanhToan;
    }

    public String getMaVe() { return maVe; }
    public void setMaVe(String maVe) { this.maVe = maVe; }
    public String getMaKH() { return maKH; }
    public void setMaKH(String maKH) { this.maKH = maKH; }
    public String getMaSuatChieu() { return maSuatChieu; }
    public void setMaSuatChieu(String maSuatChieu) { this.maSuatChieu = maSuatChieu; }
    public String getMaGhe() { return maGhe; }
    public void setMaGhe(String maGhe) { this.maGhe = maGhe; }
    public double getGiaVe() { return giaVe; }
    public void setGiaVe(double giaVe) { if (giaVe > 0) this.giaVe = giaVe; else throw new IllegalArgumentException("Giá vé phải lớn hơn 0"); }
    public boolean isDaThanhToan() { return daThanhToan; }
    public void setDaThanhToan(boolean daThanhToan) { this.daThanhToan = daThanhToan; }
    public void xacNhanThanhToan() { this.daThanhToan = true; }

    // CRUD
    public static void Create(Ve ve) {
        if (ve.getMaVe() == null || ve.getMaVe().trim().isEmpty() ||
            ve.getMaKH() == null || ve.getMaKH().trim().isEmpty() ||
            ve.getMaSuatChieu() == null || ve.getMaSuatChieu().trim().isEmpty() ||
            ve.getMaGhe() == null || ve.getMaGhe().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin vé không được để trống.");
            return;
        }
        if (ve.getGiaVe() <= 0) {
            System.out.println("Lỗi: Giá vé phải lớn hơn 0.");
            return;
        }
        danhSachVe.add(ve);
        System.out.println("Đã thêm vé thành công.");
    }

    // Read toàn bộ danh sách vé
    public static ArrayList<Ve> Read() {
        if (danhSachVe.isEmpty()) {
            System.out.println("Danh sách vé trống.");
            return new ArrayList<>();
        }
        System.out.println("Tổng số vé: " + danhSachVe.size());
        return new ArrayList<>(danhSachVe);
    }
    
    // Read vé theo mã
    public static void Read(String maVe) {
        if (danhSachVe.isEmpty()) {
            System.out.println("Danh sách vé trống.");
            return;
        }
            Ve v = getVeById(maVe);
            if (v != null) {
            v.hienThiThongTin();
            } else {
            System.out.println("Không tìm thấy vé với mã: " + maVe);
        }
    }

    public static void Update(String maVe, Ve ve) {
        if (ve.getMaVe() == null || ve.getMaVe().trim().isEmpty() ||
            ve.getMaKH() == null || ve.getMaKH().trim().isEmpty() ||
            ve.getMaSuatChieu() == null || ve.getMaSuatChieu().trim().isEmpty() ||
            ve.getMaGhe() == null || ve.getMaGhe().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin vé không được để trống.");
            return;
        }
        if (ve.getGiaVe() <= 0) {
            System.out.println("Lỗi: Giá vé phải lớn hơn 0.");
            return;
        }
        int index = getVeIndexById(maVe);
        if (index != -1) {
            ve.setMaVe(maVe);
            danhSachVe.set(index, ve);
            System.out.println("Cập nhật thông tin vé thành công.");
        } else {
            System.out.println("Không tìm thấy vé với mã đã nhập.");
        }
    }

    public static void Delete(String maVe) {
        int index = getVeIndexById(maVe);
        if (index != -1) {
            danhSachVe.remove(index);
            System.out.println("Đã xoá vé thành công.");
        } else {
            System.out.println("Không tìm thấy vé với mã đã nhập.");
        }
    }

    public static Ve getVeById(String maVe) {
        return danhSachVe.stream()
                .filter(v -> v.getMaVe().equalsIgnoreCase(maVe))
                .findFirst()
                .orElse(null);
    }

    private static int getVeIndexById(String maVe) {
        for (int i = 0; i < danhSachVe.size(); i++) {
            if (danhSachVe.get(i).getMaVe().equalsIgnoreCase(maVe)) {
                return i;
            }
        }
        return -1;
    }
    
    // Hiển thị thông tin chi tiết của một vé
    public void hienThiThongTin() {
        System.out.println("=== THÔNG TIN VÉ ===");
        System.out.println("Mã vé: " + this.maVe);
        System.out.println("Mã khách hàng: " + this.maKH);
        System.out.println("Mã suất chiếu: " + this.maSuatChieu);
        System.out.println("Mã ghế: " + this.maGhe);
        System.out.println("Giá vé: " + this.giaVe + " VNĐ");
        System.out.println("Trạng thái: " + (this.daThanhToan ? "Đã thanh toán" : "Chưa thanh toán"));
        System.out.println("====================");
    }
}
