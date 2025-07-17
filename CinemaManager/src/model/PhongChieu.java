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

    // CRUD static
    public static void Create(PhongChieu phong) {
        if (phong == null || phong.getMaPhong() == null || phong.getMaPhong().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin phòng chiếu không được để trống.");
            return;
        }
        if (getPhongByMa(phong.getMaPhong()) != null) {
            System.out.println("Lỗi: Phòng chiếu đã tồn tại.");
            return;
        }
        danhSachPhong.add(phong);
        System.out.println("Đã thêm phòng chiếu thành công.");
    }

    public static void Read(String maPhong) {
        if (danhSachPhong.isEmpty()) {
            System.out.println("Danh sách phòng chiếu trống.");
        } else {
            PhongChieu phong = getPhongByMa(maPhong);
            if (phong != null) {
                System.out.println("Mã phòng: " + phong.getMaPhong());
                System.out.println("Tên phòng: " + phong.getTenPhong());
                System.out.println("Số hàng ghế: " + phong.getSoHangGhe());
                System.out.println("Số cột ghế: " + phong.getSoCotGhe());
            } else {
                System.out.println("Không tìm thấy phòng chiếu với mã đã nhập!");
            }
        }
    }

    public static void Update(String maPhong, PhongChieu phong) {
        if (phong == null || phong.getMaPhong() == null || phong.getMaPhong().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin phòng chiếu không được để trống.");
            return;
        }
        int index = getPhongIndexByMa(maPhong);
        if (index != -1) {
            phong.setMaPhong(maPhong);
            danhSachPhong.set(index, phong);
            System.out.println("Cập nhật thông tin phòng chiếu thành công.");
        } else {
            System.out.println("Không tìm thấy phòng chiếu với mã đã nhập.");
        }
    }

    public static void Delete(String maPhong) {
        int index = getPhongIndexByMa(maPhong);
        if (index != -1) {
            danhSachPhong.remove(index);
            System.out.println("Đã xóa phòng chiếu thành công.");
        } else {
            System.out.println("Không tìm thấy phòng chiếu với mã đã nhập.");
        }
    }

    public static PhongChieu getPhongByMa(String maPhong) {
        for (PhongChieu phong : danhSachPhong) {
            if (phong.getMaPhong().equalsIgnoreCase(maPhong)) return phong;
        }
        return null;
    }

    private static int getPhongIndexByMa(String maPhong) {
        for (int i = 0; i < danhSachPhong.size(); i++) {
            if (danhSachPhong.get(i).getMaPhong().equalsIgnoreCase(maPhong)) return i;
        }
        return -1;
    }
}
