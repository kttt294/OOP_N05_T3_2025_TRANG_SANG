import java.util.Scanner;

public class testKhachHang {
    public static void inputCreateKhachHang(Scanner sc) {
        System.out.println("\n=== Thêm khách hàng mới ===");

        System.out.print("CCCD khách hàng: ");
        String CCCD = sc.nextLine().trim();
        if (CCCD.isEmpty()) {
            System.out.println("CCCD khách hàng không được để trống.");
            return;
        }
        if (KhachHang.getKhachHangByCCCD(CCCD) != null) {
            System.out.println("Khách hàng đã tồn tại.");
            return;
        }

        System.out.print("Tên khách hàng: ");
        String tenKH = sc.nextLine().trim();
        if (tenKH.isEmpty()) {
            System.out.println("Tên khách hàng không được để trống.");
            return;
        }

        System.out.print("Tuổi: ");
        int tuoi = Integer.parseInt(sc.nextLine().trim());

        System.out.print("Số điện thoại: ");
        String sdt = sc.nextLine().trim();

        System.out.print("Email: ");
        String email = sc.nextLine().trim();

        System.out.print("Giới tính: ");
        String gioiTinh = sc.nextLine().trim();

        KhachHang kh = new KhachHang(CCCD, tenKH, tuoi, sdt, email, gioiTinh, null, null);
        KhachHang.Create(kh);
    }

    public static void inputReadKhachHang(Scanner sc){
        System.out.println("Nhập mã khách hàng cần xem thông tin: ");
        String maKH = sc.nextLine().trim();
        // KhachHang.Read(maKH); // Chưa có static CRUD
        KhachHang.Read(maKH);
    }

    public static void inputUpdateKhachHang(Scanner sc) {
        System.out.print("Nhập mã khách hàng cần sửa: ");
        String maKH = sc.nextLine().trim();

        KhachHang kh = KhachHang.getKhachHangByCCCD(maKH);
        if (kh == null) {
            System.out.println("Không tìm thấy khách hàng.");
            return;
        }

        System.out.println("\n=== Cập nhật thông tin khách hàng ===");

        System.out.print("Tên khách hàng mới: ");
        String tenKH = sc.nextLine().trim();
        if (!tenKH.isEmpty()) {
            kh.setTen(tenKH);
        }

        System.out.print("Tuổi mới: ");
        String tuoiInput = sc.nextLine().trim();
        if (!tuoiInput.isEmpty()) {
            kh.setTuoi(Integer.parseInt(tuoiInput));
        }

        System.out.print("Số điện thoại mới: ");
        String sdt = sc.nextLine().trim();
        if (!sdt.isEmpty()) {
            kh.setSdt(sdt);
        }

        System.out.print("Email mới: ");
        String email = sc.nextLine().trim();
        if (!email.isEmpty()) {
            kh.setEmail(email);
        }

        System.out.print("Giới tính mới: ");
        String gioiTinh = sc.nextLine().trim();
        if (!gioiTinh.isEmpty()) {
            kh.setGioiTinh(gioiTinh);
        }

        KhachHang.Update(maKH, kh);
    }

    public static void inputDeleteKhachHang(Scanner sc) {
        System.out.print("Nhập mã khách hàng cần xoá: ");
        String maKH = sc.nextLine().trim();

        KhachHang.Delete(maKH);
    }

    public static void test() {
        Scanner sc = new Scanner(System.in);
        int luaChon;
        do {
            System.out.println("\nMENU KHÁCH HÀNG");
            System.out.println("1. Thêm khách hàng");
            System.out.println("2. Xem danh sách khách hàng");
            System.out.println("3. Sửa thông tin khách hàng");
            System.out.println("4. Xóa khách hàng");
            System.out.println("5. Kiểm tra tổng tiền khách hàng đã sử dụng");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            try {
                luaChon = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                luaChon = -1;
            }
            switch (luaChon) {
                case 1:
                    inputCreateKhachHang(sc);
                    break;
                case 2:
                    inputReadKhachHang(sc);
                    break;
                case 3:
                    inputUpdateKhachHang(sc);
                    break;
                case 4:
                    inputDeleteKhachHang(sc);
                    break;
                case 5:
                    testTinhTongTienDaSuDung(sc);
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (luaChon != 0);
    }

    public static void testTinhTongTienDaSuDung(Scanner sc) {
        System.out.print("Nhập CCCD khách hàng cần kiểm tra tổng tiền đã sử dụng: ");
        String cccd = sc.nextLine().trim();
        KhachHangController.tinhTongTienDaSuDung(cccd);
    }
}

