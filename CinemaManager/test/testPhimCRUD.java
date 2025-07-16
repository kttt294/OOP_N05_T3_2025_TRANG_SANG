import java.util.Scanner;

public class testPhimCRUD {
    public static void test() {
        Scanner scanner = new Scanner(System.in);
        int luaChon;
        do {
            System.out.println("\nMENU PHIM");
            System.out.println("1. Thêm phim");
            System.out.println("2. Xem danh sách phim");
            System.out.println("3. Sửa phim");
            System.out.println("4. Xoá phim");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            try {
                luaChon = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                luaChon = -1;
            }
            switch (luaChon) {
                case 1:
                    PhimCRUD.createPhim();
                    break;
                case 2:
                    PhimCRUD.readPhim();
                    break;
                case 3:
                    PhimCRUD.updatePhim();
                    break;
                case 4:
                    PhimCRUD.deletePhim();
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
