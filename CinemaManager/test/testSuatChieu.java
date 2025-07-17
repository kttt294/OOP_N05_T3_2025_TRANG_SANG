import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        SuatChieu scObj = new SuatChieu(maSuatChieu, maPhim, maPhong, thoiGianBatDau, danhSachGheTrong);
        SuatChieu.Create(scObj);
    }

    public static void inputReadSuatChieu(Scanner sc){
        System.out.println("Nhập mã suất chiếu cần kiểm tra: ");
        String maSuatChieu = sc.nextLine().trim();
        SuatChieu.Read(maSuatChieu);
    }

    public static void inputUpdateSuatChieu(Scanner sc) {
        System.out.print("Nhập mã suất chiếu cần sửa: ");
        String maSuatChieu = sc.nextLine().trim();
        SuatChieu scObj = SuatChieu.getSuatChieuById(maSuatChieu);
        if (scObj == null) {
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
                    scObj.getPhim().setTenPhim(sc.nextLine());
                    System.out.println("Đã cập nhật tên phim.");
                    break;
                case 2:
                    System.out.print("Nhập tên phòng chiếu mới: ");
                    String tenPhongMoi = sc.nextLine();
                    PhongChieu phongCu = scObj.getPhongChieu();
                    PhongChieu phongMoi = new PhongChieu(
                            phongCu != null ? phongCu.getMaPhong() : "",
                            tenPhongMoi,
                            phongCu != null ? phongCu.getSoHangGhe() : 0,
                            phongCu != null ? phongCu.getSoCotGhe() : 0);
                    scObj.setPhongChieu(phongMoi);
                    System.out.println("Đã cập nhật tên phòng chiếu.");
                    break;
                case 3:
                    System.out.print("Nhập thời gian bắt đầu mới (yyyy-MM-ddTHH:mm): ");
                    scObj.setThoiGianBatDau(LocalDateTime.parse(sc.nextLine()));
                    System.out.println("Đã cập nhật thời gian bắt đầu.");
                    break;
                case 0:
                    System.out.println("Thoát chức năng sửa thông tin.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (luaChonUpdate != 0);
        SuatChieu.Update(maSuatChieu, scObj);
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