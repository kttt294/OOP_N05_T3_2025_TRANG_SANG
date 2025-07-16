import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SuatChieuCRUD {
    private static List<SuatChieu> danhSachSuatChieu = new ArrayList<>();

    public static void createSuatChieu(Scanner sc) {
        System.out.print("Nhập mã suất chiếu: ");
        String maSC = sc.nextLine();
        if (filterSuatChieu(maSC) != null) {
            System.out.println("Lỗi: Mã suất chiếu đã tồn tại!");
            return;
        }
        // Nhập thông tin phim
        System.out.print("Nhập mã phim: ");
        String maPhim = sc.nextLine();
        Phim phim = PhimCRUD.getPhimById(maPhim);
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
        System.out.print("Nhập thời gian bắt đầu (yyyy-MM-ddTHH:mm): ");
        String thoiGianStr = sc.nextLine();
        LocalDateTime thoiGianBatDau = LocalDateTime.parse(thoiGianStr);
        List<Ghe> danhSachGheTrong = new ArrayList<>(); // Để rỗng khi tạo mới
        SuatChieu scObj = new SuatChieu(maSC, phim, phong, thoiGianBatDau, danhSachGheTrong);
        danhSachSuatChieu.add(scObj);
        System.out.println("Thêm suất chiếu thành công.");
    }

    public static void readDanhSach(Scanner sc) {
        System.out.println("\nCHỌN CHẾ ĐỘ HIỂN THỊ");
        System.out.println("1. Hiển thị tất cả suất chiếu");
        System.out.println("2. Tìm kiếm suất chiếu theo mã");
        System.out.print("Chọn chế độ: ");
        int luaChon = Integer.parseInt(sc.nextLine());
        switch (luaChon) {
            case 1:
                readTatCaSuatChieu();
                break;
            case 2:
                filterKiemSuatChieu(sc);
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
        }
    }

    private static void readTatCaSuatChieu() {
        if (danhSachSuatChieu.isEmpty()) {
            System.out.println("Danh sách suất chiếu rỗng.");
        } else {
            System.out.println("\nDANH SÁCH SUẤT CHIẾU");
            for (SuatChieu sc : danhSachSuatChieu) {
                readThongTinSuatChieu(sc);
                System.out.println("-----------------------------");
            }
        }
    }

    private static void filterKiemSuatChieu(Scanner sc) {
        System.out.print("Nhập mã suất chiếu cần tìm: ");
        String maSC = sc.nextLine();
        SuatChieu scObj = filterSuatChieu(maSC);
        if (scObj == null) {
            System.out.println("Không tìm thấy suất chiếu với mã: " + maSC);
        } else {
            System.out.println("\nTHÔNG TIN SUẤT CHIẾU");
            readThongTinSuatChieu(scObj);
        }
    }

    private static void readThongTinSuatChieu(SuatChieu sc) {
        System.out.println("Mã suất chiếu: " + sc.getMaSuatChieu());
        System.out.println("Phim: " + (sc.getPhim() != null ? sc.getPhim().getTenPhim() : ""));
        System.out.println("Phòng chiếu: " + (sc.getPhongChieu() != null ? sc.getPhongChieu().getTenPhong() : ""));
        System.out.println("Bắt đầu: " + sc.getThoiGianBatDau());
        System.out.println("Kết thúc: " + sc.getThoiGianKetThuc());
        System.out.println(
                "Số ghế còn trống: " + (sc.getDanhSachGheTrong() != null ? sc.getDanhSachGheTrong().size() : 0));
    }

    public static void updateSuatChieu(Scanner sc) {
        System.out.print("Nhập mã suất chiếu cần sửa: ");
        String maSC = sc.nextLine();
        SuatChieu scObj = filterSuatChieu(maSC);
        if (scObj == null) {
            System.out.println("Không tìm thấy suất chiếu với mã: " + maSC);
            return;
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
    }

    public static void deleteSuatChieu(Scanner sc) {
        System.out.print("Nhập mã suất chiếu cần xóa: ");
        String maSC = sc.nextLine();
        SuatChieu scObj = filterSuatChieu(maSC);
        if (scObj == null) {
            System.out.println("Không tìm thấy suất chiếu với mã: " + maSC);
            return;
        }
        System.out.println("Thông tin suất chiếu sẽ bị xóa:");
        readThongTinSuatChieu(scObj);
        System.out.print("Bạn có chắc chắn muốn xóa? (y/n): ");
        String xacNhan = sc.nextLine().toLowerCase();
        if (xacNhan.equals("y") || xacNhan.equals("yes")) {
            danhSachSuatChieu.remove(scObj);
            System.out.println("Đã xóa suất chiếu thành công.");
        } else {
            System.out.println("Hủy bỏ việc xóa suất chiếu.");
        }
    }

    private static SuatChieu filterSuatChieu(String maSC) {
        for (SuatChieu sc : danhSachSuatChieu) {
            if (sc.getMaSuatChieu().equalsIgnoreCase(maSC)) {
                return sc;
            }
        }
        return null;
    }
}
