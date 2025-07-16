import java.util.List;
import java.util.Scanner;

public class testKhachHangCRUD {
    public static void inputCreateKhachHang(Scanner sc) {
        System.out.println("\n=== Thêm khách hàng mới ===");

        System.out.print("Mã khách hàng: ");
        String maKH = sc.nextLine().trim();
        if (maKH.isEmpty()) {
            System.out.println("Mã khách hàng không được để trống.");
            return;
        }

        if (KhachHang.getKhachHangById(maKH) != null) {
            System.out.println("Mã khách hàng đã tồn tại.");
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

        List<Ve> lichSuDatVe = null; // Khởi tạo danh sách vé rỗng

        KhachHang kh = new KhachHang(maKH, tenKH, tuoi, sdt, email, gioiTinh, lichSuDatVe);
        KhachHang.createKhachHang(kh);
    }

    public static void inputUpdateKhachHang(Scanner sc) {
        System.out.print("Nhập mã khách hàng cần sửa: ");
        String maKH = sc.nextLine().trim();

        KhachHang kh = KhachHang.getKhachHangById(maKH);
        if (kh == null) {
            System.out.println("Không tìm thấy khách hàng.");
            return;
        }

        System.out.println("\n=== Cập nhật thông tin khách hàng ===");

        System.out.print("Tên khách hàng mới: ");
        String tenKH = sc.nextLine().trim();
        if (!tenKH.isEmpty()) {
            kh.setTenKH(tenKH);
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

        KhachHang.updateKhachHang(maKH, kh);
    }

    public static void inputDeleteKhachHang(Scanner sc) {
        System.out.print("Nhập mã khách hàng cần xoá: ");
        String maKH = sc.nextLine().trim();

        KhachHang.deleteKhachHang(maKH);
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
                    KhachHang.readDanhSach();
                    break;
                case 3:
                    inputUpdateKhachHang(sc);
                    break;
                case 4:
                    inputDeleteKhachHang(sc);
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

