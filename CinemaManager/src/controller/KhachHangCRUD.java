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
        if (danhSachKH.isEmpty()) {
            System.out.println(" Danh sách khách hàng rỗng.");
        } else {
            System.out.println("\nDANH SÁCH KHÁCH HÀNG");
            for (KhachHang kh : danhSachKH) {
                System.out.println("Mã KH: " + kh.getMaKH());
                System.out.println("Tên KH: " + kh.getTenKH());
                System.out.println("Tuổi: " + kh.getTuoi());
                System.out.println("SĐT: " + kh.getSdt());
                System.out.println("Email: " + kh.getEmail());
                System.out.println("Giới tính: " + kh.getGioiTinh());
                System.out.println("-----------------------------");
            }
        }
    }

    public static void suaKhachHang(Scanner sc) {
        System.out.print("Nhập mã khách hàng cần sửa: ");
        String maKH = sc.nextLine();

        KhachHang kh = timKhachHang(maKH);
        if (kh == null) {
            System.out.println("⚠ Không tìm thấy khách hàng.");
            return;
        }

        System.out.print("Nhập tên mới: ");
        kh.setTenKH(sc.nextLine());

        System.out.print("Nhập tuổi mới: ");
        kh.setTuoi(Integer.parseInt(sc.nextLine()));

        System.out.print("Nhập SĐT mới: ");
        kh.setSdt(sc.nextLine());

        System.out.print("Nhập email mới: ");
        kh.setEmail(sc.nextLine());

        System.out.print("Nhập giới tính mới (NAM/NU/KHAC): ");
        kh.setGioiTinh(sc.nextLine().toUpperCase());

        System.out.println("Cập nhật thành công.");
    }

    public static void xoaKhachHang(Scanner sc) {
        System.out.print("Nhập mã khách hàng cần xóa: ");
        String maKH = sc.nextLine();

        KhachHang kh = timKhachHang(maKH);
        if (kh == null) {
            System.out.println("Không tìm thấy khách hàng.");
            return;
        }

        danhSachKH.remove(kh);
        System.out.println(" Đã xóa khách hàng.");
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
