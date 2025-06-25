package test;

import model.Phim;
import java.util.Scanner;

public class testPhim {
    public static void test(Scanner scanner) {
        System.out.print("Nhập mã phim: ");
        String maPhim = scanner.nextLine();

        System.out.print("Nhập tên phim: ");
        String tenPhim = scanner.nextLine();

        System.out.print("Nhập thể loại: ");
        String theLoai = scanner.nextLine();

        System.out.print("Nhập thời lượng (phút): ");
        int thoiLuong = Integer.parseInt(scanner.nextLine());

        System.out.print("Nhập ngôn ngữ: ");
        String ngonNgu = scanner.nextLine();

        System.out.print("Nhập giới hạn tuổi: ");
        int gioiHanTuoi = Integer.parseInt(scanner.nextLine());

        System.out.print("Nhập mô tả phim: ");
        String moTa = scanner.nextLine();

        Phim phim = new Phim(maPhim, tenPhim, theLoai, thoiLuong, ngonNgu, gioiHanTuoi, moTa);

        System.out.println("\n== Thông tin phim ==");
        phim.hienThiThongTin();

        System.out.print("\nNhập tuổi khán giả muốn mua vé để kiểm tra: ");
        int tuoi = scanner.nextInt();

        if (phim.kiemTraDoTuoi(tuoi)) {
            System.out.println("Khán giả " + tuoi + " tuổi được phép xem phim.");
        } else {
            System.out.println("Khán giả " + tuoi + " tuổi KHÔNG được phép xem phim.");
        }
    }
}
