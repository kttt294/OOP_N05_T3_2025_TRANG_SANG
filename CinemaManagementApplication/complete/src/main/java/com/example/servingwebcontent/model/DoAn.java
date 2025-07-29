import java.util.ArrayList;

public class DoAn {
    private String maDoAn;
    private String tenDoAn;
    private int gia; // tính bằng Việt Nam Đồng
    private int soLuongCon;

    private static ArrayList<DoAn> danhSachDoAn = new ArrayList<>();

    public DoAn() {}

    public DoAn(String maDoAn, String tenDoAn, int gia, int soLuong) {
        setMaDoAn(maDoAn);
        setTenDoAn(tenDoAn);
        setGia(gia);
        setSoLuong(soLuong);
    }

    public String getMaDoAn() { return maDoAn; }
    public void setMaDoAn(String maDoAn) {
        if (maDoAn == null || maDoAn.trim().isEmpty())
            throw new IllegalArgumentException("Mã đồ ăn không được để trống!");
        this.maDoAn = maDoAn.trim();
    }
    public String getTenDoAn() { return tenDoAn; }
    public void setTenDoAn(String tenDoAn) {
        if (tenDoAn == null || tenDoAn.trim().isEmpty())
            throw new IllegalArgumentException("Tên đồ ăn không được để trống!");
        this.tenDoAn = tenDoAn.trim();
    }
    public int getGia() { return gia; }
    public void setGia(int gia) {
        if (gia < 0) throw new IllegalArgumentException("Giá không hợp lệ!");
        this.gia = gia;
    }
    public int getSoLuong() { return soLuongCon; }
    public void setSoLuong(int soLuong) {
        if (soLuong < 0) throw new IllegalArgumentException("Số lượng không hợp lệ!");
        this.soLuongCon = soLuong;
    }

    // CRUD
    public static boolean Create(DoAn doAn) {
        if (doAn == null) return false;
        if (getDoAnByMa(doAn.getMaDoAn()) != null) return false;
        danhSachDoAn.add(doAn);
        return true;
    }

    // Read toàn bộ danh sách đồ ăn
    public static ArrayList<DoAn> Read() {
        if (danhSachDoAn.isEmpty()) {
            System.out.println("Danh sách đồ ăn trống.");
            return new ArrayList<>();
        }
        System.out.println("Tổng số đồ ăn: " + danhSachDoAn.size());
        return new ArrayList<>(danhSachDoAn);
    }
    
    // Read đồ ăn theo mã
    public static void Read(String maDoAn) {
        if (danhSachDoAn.isEmpty()) {
            System.out.println("Danh sách đồ ăn trống.");
            return;
        }
        DoAn da = getDoAnByMa(maDoAn);
        if (da != null) {
            da.hienThiThongTin();
        } else {
            System.out.println("Không tìm thấy đồ ăn với mã: " + maDoAn);
        }
    }
    public static boolean Update(DoAn doAn) {
        if (doAn == null) return false;
        int idx = getDoAnIndexByMa(doAn.getMaDoAn());
        if (idx == -1) return false;
        danhSachDoAn.set(idx, doAn);
        return true;
    }
    public static boolean Delete(String maDoAn) {
        int idx = getDoAnIndexByMa(maDoAn);
        if (idx == -1) return false;
        danhSachDoAn.remove(idx);
        return true;
    }
    // Nếu muốn xem thông tin Đồ Ăn thông cụ thể thông qua maDoAn
    public static DoAn getDoAnByMa(String maDoAn) {
        for (DoAn da : danhSachDoAn) {
            if (da.getMaDoAn().equalsIgnoreCase(maDoAn)) return da;
        }
        return null;
    }
    public static int getDoAnIndexByMa(String maDoAn) {
        for (int i = 0; i < danhSachDoAn.size(); i++) {
            if (danhSachDoAn.get(i).getMaDoAn().equalsIgnoreCase(maDoAn)) return i;
        }
        return -1;
    }
    
    // Hiển thị thông tin chi tiết của một đồ ăn
    public void hienThiThongTin() {
        System.out.println("=== THÔNG TIN ĐỒ ĂN ===");
        System.out.println("Mã đồ ăn: " + this.maDoAn);
        System.out.println("Tên đồ ăn: " + this.tenDoAn);
        System.out.println("Giá: " + this.gia + " VNĐ");
        System.out.println("Số lượng còn: " + this.soLuongCon);
        System.out.println("======================");
    }
}
