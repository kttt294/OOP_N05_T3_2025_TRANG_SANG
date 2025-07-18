import java.util.ArrayList;
import java.util.Objects;

public class Ghe {
    public enum LoaiGhe { THUONG, VIP, COUPLE }

    private String maGhe;
    private int hang;
    private int cot;
    private LoaiGhe loaiGheMacDinh;
    private String maPhong;

    private static ArrayList<Ghe> danhSachGhe = new ArrayList<>();

    public Ghe() {}

    public Ghe(String maGhe, int hang, int cot, LoaiGhe loaiGheMacDinh, String maPhong) {
        setMaGhe(maGhe);
        setHang(hang);
        setCot(cot);
        setLoaiGheMacDinh(loaiGheMacDinh);
        setMaPhong(maPhong);
    }

    public String getMaGhe() { return maGhe; }
    public void setMaGhe(String maGhe) {
        if (maGhe == null || maGhe.trim().isEmpty())
            throw new IllegalArgumentException("Mã ghế không được để trống!");
        this.maGhe = maGhe.trim();
    }
    public int getHang() { return hang; }
    public void setHang(int hang) {
        if (hang < 0) throw new IllegalArgumentException("Hàng ghế không hợp lệ!");
        this.hang = hang;
    }
    public int getCot() { return cot; }
    public void setCot(int cot) {
        if (cot < 0) throw new IllegalArgumentException("Cột ghế không hợp lệ!");
        this.cot = cot;
    }
    public LoaiGhe getLoaiGheMacDinh() { return loaiGheMacDinh; }
    public void setLoaiGheMacDinh(LoaiGhe loaiGheMacDinh) {
        this.loaiGheMacDinh = (loaiGheMacDinh != null) ? loaiGheMacDinh : LoaiGhe.THUONG;
    }
    public String getMaPhong() { return maPhong; }
    public void setMaPhong(String maPhong) {
        this.maPhong = (maPhong != null) ? maPhong.trim() : "";
    }

    // CRUD static
    public static void Create(Ghe ghe) {
        if (ghe == null || ghe.getMaGhe() == null || ghe.getMaGhe().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin ghế không được để trống.");
            return;
        }
        if (getGheByMaGhe(ghe.getMaGhe()) != null) {
            System.out.println("Lỗi: Ghế đã tồn tại.");
            return;
        }
        danhSachGhe.add(ghe);
        System.out.println("Đã thêm ghế thành công.");
    }
    
    // Read toàn bộ danh sách ghế
    public static ArrayList<Ghe> Read() {
        if (danhSachGhe.isEmpty()) {
            System.out.println("Danh sách ghế trống.");
            return new ArrayList<>();
        }
        System.out.println("Tổng số ghế: " + danhSachGhe.size());
        return new ArrayList<>(danhSachGhe);
    }
    
    // Read ghế theo mã
    public static void Read(String maGhe) {
        if (danhSachGhe.isEmpty()) {
            System.out.println("Danh sách ghế trống.");
            return;
        }
        Ghe ghe = getGheByMaGhe(maGhe);
        if (ghe != null) {
            ghe.hienThiThongTin();
        } else {
            System.out.println("Không tìm thấy ghế với mã: " + maGhe);
        }
    }
    
    public static void Update(String maGhe, Ghe ghe) {
        if (ghe == null || ghe.getMaGhe() == null || ghe.getMaGhe().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin ghế không được để trống.");
            return;
        }
        int idx = getGheIndexByMaGhe(maGhe);
        if (idx == -1) {
            System.out.println("Không tìm thấy ghế với mã: " + maGhe);
            return;
        }
        ghe.setMaGhe(maGhe);
        danhSachGhe.set(idx, ghe);
        System.out.println("Cập nhật ghế thành công.");
    }
    
    public static void Delete(String maGhe) {
        int idx = getGheIndexByMaGhe(maGhe);
        if (idx == -1) {
            System.out.println("Không tìm thấy ghế với mã: " + maGhe);
            return;
        }
        danhSachGhe.remove(idx);
        System.out.println("Đã xóa ghế thành công.");
    }
    public static Ghe getGheByMaGhe(String maGhe) {
        for (Ghe ghe : danhSachGhe) {
            if (ghe.getMaGhe().equalsIgnoreCase(maGhe)) return ghe;
        }
        return null;
    }
    public static int getGheIndexByMaGhe(String maGhe) {
        for (int i = 0; i < danhSachGhe.size(); i++) {
            if (danhSachGhe.get(i).getMaGhe().equalsIgnoreCase(maGhe)) return i;
        }
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ghe)) return false;
        Ghe ghe = (Ghe) o;
        return Objects.equals(maGhe, ghe.maGhe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maGhe);
    }
    
    // Hiển thị thông tin chi tiết của một ghế
    public void hienThiThongTin() {
        System.out.println("=== THÔNG TIN GHẾ ===");
        System.out.println("Mã ghế: " + this.maGhe);
        System.out.println("Hàng: " + this.hang);
        System.out.println("Cột: " + this.cot);
        System.out.println("Loại ghế: " + this.loaiGheMacDinh);
        System.out.println("Mã phòng: " + this.maPhong);
        System.out.println("====================");
    }
}
