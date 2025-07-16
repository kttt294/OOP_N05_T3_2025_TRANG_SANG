import java.util.Scanner;

public class testSuatChieuCRUD {
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
                    SuatChieuCRUD.createSuatChieu(sc);
                    break;
                case 2:
                    SuatChieuCRUD.readDanhSach(sc);
                    break;
                case 3:
                    SuatChieuCRUD.updateSuatChieu(sc);
                    break;
                case 4:
                    SuatChieuCRUD.deleteSuatChieu(sc);
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
