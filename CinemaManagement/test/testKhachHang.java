import java.util.Scanner;
import java.util.ArrayList;

public class testKhachHang {
    public static void inputCreateKhachHang(Scanner sc) {
        System.out.println("\n=== Thêm khách hàng mới ===");

        System.out.print("CCCD khách hàng: ");
        String CCCD = sc.nextLine().trim();
        if (CCCD.isEmpty()) {
            System.out.println("CCCD khách hàng không được để trống.");
            return;
        }
        if (KhachHangController.timKhachHangTheoCCCD(CCCD) != null) {
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

        // Tạo khách hàng mới
        KhachHang kh = new KhachHang(CCCD, tenKH, tuoi, sdt, email, gioiTinh);
        KhachHangController.taoKhachHang(kh);
    }

    public static void inputReadKhachHang(Scanner sc){
        System.out.println("Nhập mã khách hàng cần xem thông tin: ");
        String maKH = sc.nextLine().trim();
        KhachHangController.xemThongTin(maKH);
    }

    public static void inputUpdateKhachHang(Scanner sc) {
        System.out.print("Nhập mã khách hàng cần sửa: ");
        String maKH = sc.nextLine().trim();

        KhachHang kh = KhachHangController.timKhachHangTheoCCCD(maKH);
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

        KhachHangController.capNhatThongTin(maKH, kh);
    }

    public static void inputDeleteKhachHang(Scanner sc) {
        System.out.print("Nhập mã khách hàng cần xoá: ");
        String maKH = sc.nextLine().trim();

        KhachHangController.xoaKhachHang(maKH);
    }

    public static void test() {
        Scanner sc = new Scanner(System.in);
        int luaChon;
        do {
            System.out.println("\n=== MENU QUẢN LÝ KHÁCH HÀNG ===");
            System.out.println("1. Thêm khách hàng");
            System.out.println("2. Xem danh sách khách hàng");
            System.out.println("3. Sửa thông tin khách hàng");
            System.out.println("4. Xóa khách hàng");
            System.out.println("5. Kiểm tra tổng tiền khách hàng đã sử dụng");
            System.out.println("6. Xem thống kê tổng quan");
            System.out.println("7. Tìm kiếm theo tên");
            System.out.println("8. Tìm kiếm theo giới tính");
            System.out.println("9. Xem lịch sử đặt vé");
            System.out.println("10. Báo cáo khách hàng VIP");
            System.out.println("11. Báo cáo khách hàng mới");
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
                    KhachHangController.xemTatCaKhachHang();
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
                case 6:
                    KhachHangController.xemThongKeKhachHang();
                    break;
                case 7:
                    timKiemTheoTen(sc);
                    break;
                case 8:
                    timKiemTheoGioiTinh(sc);
                    break;
                case 9:
                    xemLichSuDatVe(sc);
                    break;
                case 10:
                    KhachHangController.baoCaoKhachHangVIP();
                    break;
                case 11:
                    KhachHangController.baoCaoKhachHangMoi();
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
        
        double tongTien = KhachHangController.tinhTongTienKhachHang(cccd);
        System.out.println("Tổng số tiền khách hàng với CCCD " + cccd + " đã sử dụng là: " + tongTien + " VNĐ");
    }

    public static void timKiemTheoTen(Scanner sc) {
        System.out.print("Nhập tên cần tìm kiếm: ");
        String ten = sc.nextLine().trim();
        
        ArrayList<KhachHang> ketQua = KhachHangController.timKhachHangTheoTen(ten);
        if (ketQua.isEmpty()) {
            System.out.println("Không tìm thấy khách hàng nào có tên chứa: " + ten);
        } else {
            System.out.println("Tìm thấy " + ketQua.size() + " khách hàng:");
            for (KhachHang kh : ketQua) {
                kh.hienThiThongTin();
                System.out.println("---");
            }
        }
    }

    public static void timKiemTheoGioiTinh(Scanner sc) {
        System.out.print("Nhập giới tính cần tìm kiếm (Nam/Nữ/Khác): ");
        String gioiTinh = sc.nextLine().trim();
        
        ArrayList<KhachHang> ketQua = KhachHangController.timKhachHangTheoGioiTinh(gioiTinh);
        if (ketQua.isEmpty()) {
            System.out.println("Không tìm thấy khách hàng nào có giới tính: " + gioiTinh);
        } else {
            System.out.println("Tìm thấy " + ketQua.size() + " khách hàng có giới tính " + gioiTinh + ":");
            for (KhachHang kh : ketQua) {
                kh.hienThiThongTin();
                System.out.println("---");
            }
        }
    }

    public static void xemLichSuDatVe(Scanner sc) {
        System.out.print("Nhập CCCD khách hàng cần xem lịch sử đặt vé: ");
        String cccd = sc.nextLine().trim();
        
        KhachHangController.xemLichSuDatVe(cccd);
    }
}

