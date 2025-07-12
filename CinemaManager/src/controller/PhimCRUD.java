package controller;

import model.Phim;

import java.util.ArrayList;
import java.util.Scanner;

public class PhimCRUD {
    private ArrayList<Phim> danhSachPhim = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public void createPhim() {
        System.out.println("\n=== Thêm phim mới ===");

        System.out.print("Mã phim: ");
        String maPhim = sc.nextLine();

        System.out.print("Tên phim: ");
        String tenPhim = sc.nextLine();

        System.out.print("Thể loại: ");
        String theLoai = sc.nextLine();

        System.out.print("Thời lượng (phút): ");
        int thoiLuong = Integer.parseInt(sc.nextLine());

        System.out.print("Ngôn ngữ: ");
        String ngonNgu = sc.nextLine();

        System.out.print("Giới hạn tuổi: ");
        int gioiHanTuoi = Integer.parseInt(sc.nextLine());

        System.out.print("Mô tả: ");
        String moTa = sc.nextLine();

        Phim p = new Phim(maPhim, tenPhim, theLoai, thoiLuong, ngonNgu, gioiHanTuoi, moTa);
        danhSachPhim.add(p);
        System.out.println("=> Đã thêm phim thành công.");
    }

    public void readPhim() {
        System.out.println("\n=== Danh sách phim ===");
        if (danhSachPhim.isEmpty()) {
            System.out.println("Không có phim nào.");
            return;
        }
        for (Phim p : danhSachPhim) {
            System.out.println("---------------------------");
            p.hienThiThongTin();
        }
    }

    public void updatePhim() {
        System.out.print("\nNhập mã phim cần sửa: ");
        String ma = sc.nextLine();
        for (Phim p : danhSachPhim) {
            if (p.getMaPhim().equalsIgnoreCase(ma)) {
                System.out.println("=== Cập nhật thông tin phim ===");

                System.out.print("Tên phim mới: ");
                p.setTenPhim(sc.nextLine());

                System.out.print("Thể loại mới: ");
                p.setTheLoai(sc.nextLine());

                System.out.print("Thời lượng mới: ");
                p.setThoiLuong(Integer.parseInt(sc.nextLine()));

                System.out.print("Ngôn ngữ mới: ");
                p.setNgonNgu(sc.nextLine());

                System.out.print("Giới hạn tuổi mới: ");
                p.setGioiHanTuoi(Integer.parseInt(sc.nextLine()));

                System.out.print("Mô tả mới: ");
                p.setMoTa(sc.nextLine());

                System.out.println("=> Đã cập nhật thông tin phim.");
                return;
            }
        }
        System.out.println("Không tìm thấy mã phim.");
    }

    public void deletePhim() {
        System.out.print("\nNhập mã phim cần xoá: ");
        String ma = sc.nextLine();
        for (Phim p : danhSachPhim) {
            if (p.getMaPhim().equalsIgnoreCase(ma)) {
                danhSachPhim.remove(p);
                System.out.println("=> Đã xoá phim.");
                return;
            }
        }
        System.out.println("Không tìm thấy mã phim.");
    }
}
