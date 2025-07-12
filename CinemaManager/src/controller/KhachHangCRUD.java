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

            try {
                luaChon = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠ Vui lòng nhập số hợp lệ.");
                luaChon = -1;
            }

            switch (luaChon) {
                case 1: themKhachHang(sc);
                case 2: hienThiDanhSach();
                case 3: suaKhachHang(sc);
                case 4: xoaKhachHang(sc);
                case 0: System.out.println("Thoát chương trình.");
                default: System.out.println("⚠ Lựa chọn không hợp lệ.");
            }
        } while (luaChon != 0);
    }

    public static void themKhachHang(Scanner sc) {
        System.out.print("Nhập mã khách hàng: ");
        String maKH = sc.nextLine().trim();
        if (maKH.isEmpty()) {
            System.out.println("⚠ Mã KH không được để trống.");
            return;
        }
        if (timKhachHang(maKH) != null) {
            System.out.println("⚠ Mã KH đã tồn tại.");
            return;
        }

        System.out.print("Nhập tên khách hàng: ");
        String tenKH = sc.nextLine().trim();
        if (tenKH.isEmpty()) {
            System.out.println("⚠ Tên KH không được để trống.");
            return;
        }

        System.out.print("Nhập tuổi: ");
        int tuoi;
        try {
            tuoi = Integer.parseInt(sc.nextLine());
            if (tuoi <= 0 || tuoi > 120) {
                System.out.println("⚠ Tuổi không hợp lệ.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("⚠ Tuổi phải là số nguyên.");
            return;
        }

        System.out.print("Nhập số điện thoại: ");
        String sdt = sc.nextLine().trim();
        if (sdt.isEmpty()) {
            System.out.println("⚠ SĐT không được để trống.");
            return;
        }

        System.out.print("Nhập email: ");
        String email = sc.nextLine().trim();
        if (!email.contains("@") || !email.contains(".")) {
            System.out.println("⚠ Email không hợp lệ.");
            return;
        }

        System.out.print("Nhập giới tính (NAM/NU/KHAC): ");
        String gioiTinh = sc.nextLine().trim().toUpperCase();
        if (!gioiTinh.equals("NAM") && !gioiTinh.equals("NU") && !gioiTinh.equals("KHAC")) {
            System.out.println("⚠ Giới tính phải là NAM, NU hoặc KHAC.");
            return;
        }

        List<Ve> lichSu = new ArrayList<>();
        KhachHang kh = new KhachHang(maKH, tenKH, tuoi, sdt, email, gioiTinh, lichSu);
        danhSachKH.add(kh);
        System.out.println("✅ Thêm khách hàng thành công.");
    }

    public static void hienThiDanhSach() {
        if (danhSachKH.isEmpty()) {
            System.out.println("📭 Danh sách khách hàng rỗng.");
        } else {
            System.out.println("\n📋 DANH SÁCH KHÁCH HÀNG");
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
        String maKH = sc.nextLine().trim();

        KhachHang kh = timKhachHang(maKH);
        if (kh == null) {
            System.out.println("⚠ Không tìm thấy khách hàng.");
            return;
        }

        System.out.print("Nhập tên mới: ");
        String tenKH = sc.nextLine().trim();
        if (!tenKH.isEmpty()) kh.setTenKH(tenKH);

        System.out.print("Nhập tuổi mới: ");
        try {
            int tuoi = Integer.parseInt(sc.nextLine());
            if (tuoi > 0 && tuoi <= 120) kh.setTuoi(tuoi);
            else System.out.println("⚠ Bỏ qua cập nhật tuổi (giá trị không hợp lệ).");
        } catch (NumberFormatException e) {
            System.out.println("⚠ Bỏ qua cập nhật tuổi (không phải số).");
        }

        System.out.print("Nhập SĐT mới: ");
        String sdt = sc.nextLine().trim();
        if (!sdt.isEmpty()) kh.setSdt(sdt);

        System.out.print("Nhập email mới: ");
        String email = sc.nextLine().trim();
        if (email.contains("@") && email.contains(".")) kh.setEmail(email);
        else System.out.println("⚠ Bỏ qua cập nhật email (không hợp lệ).");

        System.out.print("Nhập giới tính mới (NAM/NU/KHAC): ");
        String gioiTinh = sc.nextLine().trim().toUpperCase();
        if (gioiTinh.equals("NAM") || gioiTinh.equals("NU") || gioiTinh.equals("KHAC")) {
            kh.setGioiTinh(gioiTinh);
        } else {
            System.out.println("⚠ Bỏ qua cập nhật giới tính (không hợp lệ).");
        }

        System.out.println("✅ Cập nhật thành công.");
    }

    public static void xoaKhachHang(Scanner sc) {
        System.out.print("Nhập mã khách hàng cần xóa: ");
        String maKH = sc.nextLine().trim();

        KhachHang kh = timKhachHang(maKH);
        if (kh == null) {
            System.out.println("⚠ Không tìm thấy khách hàng.");
            return;
        }

        danhSachKH.remove(kh);
        System.out.println("🗑️ Đã xóa khách hàng.");
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
