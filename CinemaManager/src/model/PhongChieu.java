// ... existing code ...
import java.util.ArrayList;

public class PhongChieu {
    private String maPhong;
    private String tenPhong;
    private int soHangGhe;
    private int soCotGhe;

    private static ArrayList<PhongChieu> danhSachPhong = new ArrayList<>();

    public PhongChieu(){}

    public PhongChieu(String maPhong, String tenPhong) {
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
    }

    public PhongChieu(String maPhong, String tenPhong, int soHangGhe, int soCotGhe) {
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
        setSoHangGhe(soHangGhe);
        setSoCotGhe(soCotGhe);
    }

    public void setTenPhong(String tenPhong){
        this.tenPhong = tenPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setMaPhong(String maPhong){
        this.maPhong = maPhong;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setSoHangGhe(int soHang){
        if (soHang > 0) this.soHangGhe = soHang;
        else throw new IllegalArgumentException("Số hàng ghế phải lớn hơn 0");
    }

    public int getSoHangGhe() {
        return soHangGhe;
    }

    public void setSoCotGhe(int soCot){
        if (soCot > 0) this.soCotGhe = soCot;
        else throw new IllegalArgumentException("Số cột ghế phải lớn hơn 0");
    }

    public int getSoCotGhe() {
        return soCotGhe;
    }

    // CRUD
    public static void Create(PhongChieu phong) {
        if (phong.getMaPhong() == null || phong.getMaPhong().trim().isEmpty() ||
            phong.getTenPhong() == null || phong.getTenPhong().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin phòng chiếu không được để trống.");
            return;
        }
        if (phong.getSoHangGhe() <= 0 || phong.getSoCotGhe() <= 0) {
            System.out.println("Lỗi: Số hàng ghế và số cột ghế phải lớn hơn 0.");
            return;
        }
        danhSachPhong.add(phong);
        System.out.println("Đã thêm phòng chiếu thành công.");
    }

    public static void Read(String maPhong) {
        if (danhSachPhong.isEmpty()) {
            System.out.println("Danh sách phòng chiếu trống.");
        } else {
            PhongChieu p = getPhongById(maPhong);
            if (p != null) {
                System.out.println("Mã phòng: " + p.getMaPhong());
                System.out.println("Tên phòng: " + p.getTenPhong());
                System.out.println("Số hàng ghế: " + p.getSoHangGhe());
                System.out.println("Số cột ghế: " + p.getSoCotGhe());
            } else {
                System.out.println("Không tìm thấy mã phòng cần tìm!");
            }
        }
    }

    public static void Update(String maPhong, PhongChieu phong) {
        if (phong.getMaPhong() == null || phong.getMaPhong().trim().isEmpty() ||
            phong.getTenPhong() == null || phong.getTenPhong().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin phòng chiếu không được để trống.");
            return;
        }
        if (phong.getSoHangGhe() <= 0 || phong.getSoCotGhe() <= 0) {
            System.out.println("Lỗi: Số hàng ghế và số cột ghế phải lớn hơn 0.");
            return;
        }
        int index = getPhongIndexById(maPhong);
        if (index != -1) {
            phong.setMaPhong(maPhong);
            danhSachPhong.set(index, phong);
            System.out.println("Cập nhật thông tin phòng chiếu thành công.");
        } else {
            System.out.println("Không tìm thấy phòng chiếu với mã đã nhập.");
        }
    }

    public static void Delete(String maPhong) {
        PhongChieu p = getPhongById(maPhong);
        if (p != null) {
            danhSachPhong.remove(p);
            System.out.println("Xoá phòng chiếu thành công.");
        } else {
            System.out.println("Không tìm thấy phòng chiếu với mã đã nhập.");
        }
    }

    public static PhongChieu getPhongById(String maPhong) {
        return danhSachPhong.stream()
                .filter(p -> p.getMaPhong().equalsIgnoreCase(maPhong))
                .findFirst()
                .orElse(null);
    }

    private static int getPhongIndexById(String maPhong) {
        for (int i = 0; i < danhSachPhong.size(); i++) {
            if (danhSachPhong.get(i).getMaPhong().equalsIgnoreCase(maPhong)) {
                return i;
            }
        }
        return -1;
    }
}
