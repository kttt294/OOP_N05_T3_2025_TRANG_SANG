import java.util.Scanner;

public class testPhimCRUD {
    public static void test() {
        Scanner sc = new Scanner(System.in);
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
                luaChon = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                luaChon = -1;
            }
            switch (luaChon) {
                case 1:
                    PhimCRUD.createPhim(sc);
                    break;
                case 2:
                    PhimCRUD.readPhim(sc);
                    break;
                case 3:
                    System.out.println("Nhập mã phim cần sửa: ");
                    String maSua = sc.next();
                    PhimCRUD.updatePhim(sc, maSua);
                    break;
                case 4:
                    System.out.println("Nhập mã phim cần xóa: ");
                    String maXoa = sc.next();
                    PhimCRUD.deletePhim(sc, maXoa);
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (luaChon != 0);
        sc.close();
    }
}
