package controller;

import model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class KhachHangCRUD {
    private static List<KhachHang> danhSachKH = new ArrayList<>();

    public static void CRUD() {
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
            luaChon = Integer.parseInt(sc.nextLine());

            switch (luaChon) {
                case 1 : 
                themKhachHang(sc);
                case 2 : 
                hienThiDanhSach();
                case 3 : 
                suaKhachHang(sc);
                case 4 : 
                xoaKhachHang(sc);
                case 0 : 
                System.out.println("Thoát chương trình.");
                default :
                 System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (luaChon != 0);
    }

    public static void themKhachHang(Scanner sc) {
        System.out.print("Nhập mã khách hàng: ");
        String maKH = sc.nextLine();

        // Kiểm tra xem mã khách hàng đã tồn tại chưa
        if (timKhachHang(maKH) != null) {
            System.out.println("Lỗi: Mã khách hàng '" + maKH + "' đã tồn tại trong danh sách!");
            System.out.println("Vui lòng chọn mã khách hàng khác.");
            return;
        }

        System.out.print("Nhập tên khách hàng: ");
        String tenKH = sc.nextLine();

        System.out.print("Nhập tuổi: ");
        int tuoi = Integer.parseInt(sc.nextLine());

        System.out.print("Nhập số điện thoại: ");
        String sdt = sc.nextLine();

        System.out.print("Nhập email: ");
        String email = sc.nextLine();

        System.out.print("Nhập giới tính (NAM/NU/KHAC): ");
        String gioiTinh = sc.nextLine().toUpperCase();

        List<Ve> lichSu = new ArrayList<>();

        KhachHang kh = new KhachHang(maKH, tenKH, tuoi, sdt, email, gioiTinh, lichSu);
        danhSachKH.add(kh);

        System.out.println("Thêm khách hàng thành công.");
    }

    public static void hienThiDanhSach() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nCHỌN CHẾ ĐỘ HIỂN THỊ");
        System.out.println("1. Hiển thị tất cả khách hàng");
        System.out.println("2. Tìm kiếm khách hàng theo mã");
        System.out.print("Chọn chế độ: ");
        int luaChon = Integer.parseInt(sc.nextLine());

        switch (luaChon) {
            case 1:
                hienThiTatCaKhachHang();
                break;
            case 2:
                timKiemKhachHang(sc);
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
        }
    }

    private static void hienThiTatCaKhachHang() {
        if (danhSachKH.isEmpty()) {
            System.out.println("Danh sách khách hàng rỗng.");
        } else {
            System.out.println("\nDANH SÁCH KHÁCH HÀNG");
            for (KhachHang kh : danhSachKH) {
                hienThiThongTinKhachHang(kh);
                System.out.println("-----------------------------");
            }
        }
    }

    private static void timKiemKhachHang(Scanner sc) {
        System.out.print("Nhập mã khách hàng cần tìm: ");
        String maKH = sc.nextLine();

        KhachHang kh = timKhachHang(maKH);
        if (kh == null) {
            System.out.println("Không tìm thấy khách hàng với mã: " + maKH);
        } else {
            System.out.println("\nTHÔNG TIN KHÁCH HÀNG");
            hienThiThongTinKhachHang(kh);
        }
    }

    private static void hienThiThongTinKhachHang(KhachHang kh) {
        System.out.println("Mã KH: " + kh.getMaKH());
        System.out.println("Tên KH: " + kh.getTenKH());
        System.out.println("Tuổi: " + kh.getTuoi());
        System.out.println("SĐT: " + kh.getSdt());
        System.out.println("Email: " + kh.getEmail());
        System.out.println("Giới tính: " + kh.getGioiTinh());
    }

    public static void suaKhachHang(Scanner sc) {
        System.out.print("Nhập mã khách hàng cần sửa: ");
        String maKH = sc.nextLine();

        KhachHang kh = timKhachHang(maKH);
        if (kh == null) {
            System.out.println("Không tìm thấy khách hàng với mã: " + maKH);
            return;
        }

        int luaChonSua;
        do {
            System.out.println("\nMENU SỬA THÔNG TIN KHÁCH HÀNG");
            System.out.println("1. Sửa tên khách hàng");
            System.out.println("2. Sửa tuổi");
            System.out.println("3. Sửa số điện thoại");
            System.out.println("4. Sửa email");
            System.out.println("5. Sửa giới tính");
            System.out.println("0. Thoát");
            System.out.print("Chọn thông tin cần sửa: ");
            luaChonSua = Integer.parseInt(sc.nextLine());

            switch (luaChonSua) {
                case 1:
                    System.out.print("Nhập tên mới: ");
                    kh.setTenKH(sc.nextLine());
                    System.out.println("Đã cập nhật tên khách hàng.");
                    break;
                case 2:
                    System.out.print("Nhập tuổi mới: ");
                    kh.setTuoi(Integer.parseInt(sc.nextLine()));
                    System.out.println("Đã cập nhật tuổi.");
                    break;
                case 3:
                    System.out.print("Nhập SĐT mới: ");
                    kh.setSdt(sc.nextLine());
                    System.out.println("Đã cập nhật số điện thoại.");
                    break;
                case 4:
                    System.out.print("Nhập email mới: ");
                    kh.setEmail(sc.nextLine());
                    System.out.println("Đã cập nhật email.");
                    break;
                case 5:
                    System.out.print("Nhập giới tính mới (NAM/NU/KHAC): ");
                    kh.setGioiTinh(sc.nextLine().toUpperCase());
                    System.out.println("Đã cập nhật giới tính.");
                    break;
                case 0:
                    System.out.println("Thoát chức năng sửa thông tin.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (luaChonSua != 0);
    }

    public static void xoaKhachHang(Scanner sc) {
        System.out.print("Nhập mã khách hàng cần xóa: ");
        String maKH = sc.nextLine();

        KhachHang kh = timKhachHang(maKH);
        if (kh == null) {
            System.out.println("Không tìm thấy khách hàng với mã: " + maKH);
            return;
        }

        System.out.println("Thông tin khách hàng sẽ bị xóa:");
        hienThiThongTinKhachHang(kh);
        System.out.print("Bạn có chắc chắn muốn xóa? (y/n): ");
        String xacNhan = sc.nextLine().toLowerCase();
        
        if (xacNhan.equals("y") || xacNhan.equals("yes")) {
            danhSachKH.remove(kh);
            System.out.println("Đã xóa khách hàng thành công.");
        } else {
            System.out.println("Hủy bỏ việc xóa khách hàng.");
        }
    }

    private static KhachHang timKhachHang(String maKH) {
        for (KhachHang kh : danhSachKH) {
            if (kh.getMaKH().equalsIgnoreCase(maKH)) {
                return kh;
            }
        }
        return null;
    }
}
