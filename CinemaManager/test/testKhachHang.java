package test;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class testKhachHang {
    public static void test(Scanner scanner) {
        System.out.print("Nhập mã khách hàng: ");
        String maKH = scanner.nextLine();

        System.out.print("Nhập tên khách hàng: ");
        String tenKH = scanner.nextLine();

        System.out.print("Nhập tuổi: ");
        int tuoi = Integer.parseInt(scanner.nextLine());

        System.out.print("Nhập số điện thoại: ");
        String sdt = scanner.nextLine();

        System.out.print("Nhập email: ");
        String email = scanner.nextLine();

        System.out.print("Nhập giới tính (NAM/NU/KHAC): ");
        GioiTinh gioiTinh = GioiTinh.valueOf(scanner.nextLine().toUpperCase());

        List<Ve> lichSu = new ArrayList<>();

        KhachHang kh = new KhachHang(maKH, tenKH, tuoi, sdt, email, gioiTinh, lichSu);

        System.out.println("\n== THÔNG TIN KHÁCH HÀNG ==");
        System.out.println("Mã KH: " + kh.getMaKH());
        System.out.println("Tên KH: " + kh.getTenKH());
        System.out.println("Tuổi: " + kh.getTuoi());
        System.out.println("SĐT: " + kh.getSdt());
        System.out.println("Email: " + kh.getEmail());
        System.out.println("Giới tính: " + kh.getGioiTinh());

        scanner.close();
    }
}
