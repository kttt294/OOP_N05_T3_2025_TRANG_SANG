import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Mock class Ghe cho mục đích test
class Ghe {}
// Mock class Phim cho mục đích test
class Phim {
    private String maPhim, tenPhim;
    public Phim(String maPhim, String tenPhim) { this.maPhim = maPhim; this.tenPhim = tenPhim; }
    public void setTenPhim(String tenPhim) { this.tenPhim = tenPhim; }
    public String getTenPhim() { return tenPhim; }
    public static Phim getPhimById(String maPhim) { return new Phim(maPhim, "Mock phim"); }
}
// Mock class PhongChieu cho mục đích test
class PhongChieu {
    private String maPhong, tenPhong; int soHangGhe, soCotGhe;
    public PhongChieu(String maPhong, String tenPhong, int soHangGhe, int soCotGhe) {
        this.maPhong = maPhong; this.tenPhong = tenPhong; this.soHangGhe = soHangGhe; this.soCotGhe = soCotGhe;
    }
    public String getMaPhong() { return maPhong; }
    public String getTenPhong() { return tenPhong; }
    public int getSoHangGhe() { return soHangGhe; }
    public int getSoCotGhe() { return soCotGhe; }
}
// Mock class DateTimeUtils cho mục đích test
class DateTimeUtils {
    public static java.time.LocalDateTime nhapThoiGian(java.util.Scanner sc, String msg) { return java.time.LocalDateTime.now(); }
}
// Mock class SuatChieu cho mục đích test
class SuatChieu {
    private String maSuatChieu, maPhim, maPhong;
    private java.time.LocalDateTime thoiGianBatDau;
    private java.util.List<Ghe> danhSachGheTrong;
    public SuatChieu(String maSuatChieu, String maPhim, String maPhong, java.time.LocalDateTime thoiGianBatDau, java.util.List<Ghe> danhSachGheTrong) {
        this.maSuatChieu = maSuatChieu; this.maPhim = maPhim; this.maPhong = maPhong; this.thoiGianBatDau = thoiGianBatDau; this.danhSachGheTrong = danhSachGheTrong;
    }
    public static void Create(SuatChieu sc) {}
    public static void Read(String maSuatChieu) {}
    public static void Update(String maSuatChieu, SuatChieu sc) {}
    public static void Delete(String maSuatChieu) {}
    public static SuatChieu getSuatChieuById(String maSuatChieu) { return null; }
    public void setThoiGianBatDau(java.time.LocalDateTime t) { this.thoiGianBatDau = t; }
    public String getMaPhim() { return maPhim; }
    public String getMaPhong() { return maPhong; }
}

public class testSuatChieu {
public static void inputCreateSuatChieu(Scanner sc) {
        System.out.print("Nhập mã suất chiếu: ");
        String maSuatChieu = sc.nextLine();
        if (SuatChieu.getSuatChieuById(maSuatChieu) != null) {
            System.out.println("Lỗi: Mã suất chiếu đã tồn tại!");
            return;
        }
        // Nhập thông tin phim
        System.out.print("Nhập mã phim: ");
        String maPhim = sc.nextLine();
        Phim phim = Phim.getPhimById(maPhim);
        if (phim == null) {
            System.out.println("Không tìm thấy phim với mã này.");
            return;
        }

        // Nhập thông tin phòng chiếu
        System.out.print("Nhập mã phòng: ");
        String maPhong = sc.nextLine();
        System.out.print("Nhập tên phòng: ");
        String tenPhong = sc.nextLine();
        System.out.print("Nhập số hàng ghế: ");
        int soHangGhe = Integer.parseInt(sc.nextLine());
        System.out.print("Nhập số cột ghế: ");
        int soCotGhe = Integer.parseInt(sc.nextLine());
        PhongChieu phong = new PhongChieu(maPhong, tenPhong, soHangGhe, soCotGhe);
        LocalDateTime thoiGianBatDau = DateTimeUtils.nhapThoiGian(sc, "Nhập thời gian bắt đầu");
        List<Ghe> danhSachGheTrong = new ArrayList<>(); // Để rỗng khi tạo mới
        SuatChieu suatChieu = new SuatChieu(maSuatChieu, maPhim, maPhong, thoiGianBatDau, danhSachGheTrong);
        SuatChieu.Create(suatChieu);
    }

    public static void inputReadSuatChieu(Scanner sc){
        System.out.println("Nhập mã suất chiếu cần kiểm tra: ");
        String maSuatChieu = sc.nextLine().trim();
        SuatChieu.Read(maSuatChieu);
    }

    public static void inputUpdateSuatChieu(Scanner sc) {
        System.out.print("Nhập mã suất chiếu cần sửa: ");
        String maSuatChieu = sc.nextLine().trim();
        SuatChieu suatChieu = SuatChieu.getSuatChieuById(maSuatChieu);
        if (suatChieu == null) {
            System.out.println("Không tìm thấy suất chiếu");
        }

        int luaChonUpdate;
        do {
             System.out.println("\nMENU SỬA THÔNG TIN SUẤT CHIẾU");
            System.out.println("1. Sửa tên phim");
            System.out.println("2. Sửa tên phòng chiếu");
            System.out.println("3. Sửa thời gian bắt đầu");
            System.out.println("0. Thoát");
            System.out.print("Chọn thông tin cần sửa: ");
            luaChonUpdate = Integer.parseInt(sc.nextLine());
            switch (luaChonUpdate) {
                case 1:
                    System.out.print("Nhập tên phim mới: ");
                    suatChieu.getMaPhim(); // Assuming Phim class has a method to update name
                    System.out.println("Đã cập nhật tên phim.");
                    break;
                case 2:
                    System.out.print("Nhập tên phòng chiếu mới: ");
                    String tenPhongMoi = sc.nextLine();
                    String maPhongCu = suatChieu.getMaPhong(); // Assuming SuatChieu class has a method to get maPhong
                    PhongChieu phongCu = new PhongChieu(maPhongCu, "", 0, 0); // Mock PhongChieu
                    PhongChieu phongMoi = new PhongChieu(
                            maPhongCu,
                            tenPhongMoi,
                            phongCu.getSoHangGhe(),
                            phongCu.getSoCotGhe());
                    suatChieu.setThoiGianBatDau(LocalDateTime.now()); // Assuming SuatChieu class has a method to update time
                    System.out.println("Đã cập nhật tên phòng chiếu.");
                    break;
                case 3:
                    System.out.print("Nhập thời gian bắt đầu mới (yyyy-MM-ddTHH:mm): ");
                    suatChieu.setThoiGianBatDau(LocalDateTime.parse(sc.nextLine()));
                    System.out.println("Đã cập nhật thời gian bắt đầu.");
                    break;
                case 0:
                    System.out.println("Thoát chức năng sửa thông tin.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (luaChonUpdate != 0);
        SuatChieu.Update(maSuatChieu, suatChieu);
    }

    public static void inputDeleteSuatChieu(Scanner sc) {
        System.out.print("Nhập mã suất chiếu cần xoá: ");
        String maSuatChieu = sc.nextLine().trim();
        SuatChieu.Delete(maSuatChieu);
    }

    public static void test() {
        Scanner sc = new Scanner(System.in);
        int luaChon;
        do {
            System.out.println("\nMENU SUẤT CHIẾU");
            System.out.println("1. Thêm suất chiếu");
            System.out.println("2. Xem danh sách suất chiếu");
            System.out.println("3. Sửa thông tin suất chiếu");
            System.out.println("4. Xóa suất chiếu");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            try {
                luaChon = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                luaChon = -1;
            }
            switch (luaChon) {
                case 1:
                    inputCreateSuatChieu(sc);
                    break;
                case 2:
                    inputReadSuatChieu(sc);
                    break;
                case 3:
                    inputUpdateSuatChieu(sc);
                    break;
                case 4:
                    inputDeleteSuatChieu(sc);
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (luaChon != 0);
    }
}