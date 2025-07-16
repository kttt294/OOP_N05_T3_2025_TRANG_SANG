import java.util.Scanner;

public class testSuatChieuCRUD {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SuatChieuCRUD crud = new SuatChieuCRUD();
        while (true) {
            System.out.println("=== MENU ===");
            System.out.println("1. Thêm suất chiếu");
            System.out.println("2. Hiển thị tất cả suất chiếu");
            System.out.println("3. Tìm kiếm suất chiếu theo mã");
            System.out.println("4. Xóa suất chiếu theo mã");
            System.out.println("5. Cập nhật thời gian bắt đầu");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            int chon;
            try {
                chon = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                chon = -1;
            }
            switch (chon) {
                case 1:
                    System.out.println("Tính năng thêm suất chiếu cần dữ liệu Phim, Phòng chiếu, Ghế sẵn.");
                    break;
                case 2:
                    crud.hienThiTatCaSuatChieu();
                    break;
                case 3:
                    System.out.print("Nhập mã suất chiếu cần tìm: ");
                    String ma = scanner.nextLine();
                    SuatChieu sc = crud.timSuatChieuTheoMa(ma);
                    if (sc != null) {
                        sc.hienThiThongTin();
                    } else {
                        System.out.println("Không tìm thấy.");
                    }
                    break;
                case 4:
                    System.out.print("Nhập mã suất chiếu cần xóa: ");
                    String maXoa = scanner.nextLine();
                    crud.xoaSuatChieu(maXoa);
                    break;
                case 5:
                    System.out.print("Nhập mã suất chiếu cần cập nhật: ");
                    String maCapNhat = scanner.nextLine();
                    crud.capNhatThoiGianBatDau(maCapNhat);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Chọn lại.");
            }
        }
    }
}
