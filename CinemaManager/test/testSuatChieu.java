package test;

import model.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class testSuatChieu {
    public static void test(Scanner scanner) {
        System.out.print("Nhập mã suất chiếu: ");
        String maSuat = scanner.nextLine();

        System.out.print("Nhập thời gian bắt đầu dưới định dạng (yyyy-MM-dd HH:mm): ");
        String timeInput = scanner.nextLine();
        LocalDateTime thoiGianBatDau = LocalDateTime.parse(timeInput, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        // Tạo đối tượng Phim
        Phim phim = new Phim("P001", "Inception", "Khoa học viễn tưởng", 148, "Tiếng Anh", 16, "Giấc mơ trong giấc mơ");

        // Tạo đối tượng Phòng chiếu
        PhongChieu phong = new PhongChieu("PC01", "Phòng 1");

        // Tạo danh sách ghế trống
        List<Ghe> dsGhe = new ArrayList<>();
        dsGhe.add(new Ghe("A1"));
        dsGhe.add(new Ghe("A2"));
        dsGhe.add(new Ghe("A3"));

        // Tạo đối tượng suất chiếu
        SuatChieu suat = new SuatChieu(maSuat, phim, phong, thoiGianBatDau, dsGhe);

        System.out.println("\n== Thông tin suất chiếu trước khi đặt ghế ==");
        suat.hienThiThongTin();

        System.out.print("\nNhập mã ghế muốn đặt (ví dụ A2): ");
        String maGheDat = scanner.nextLine();

        suat.capNhatGheTrong(new Ghe(maGheDat));

        System.out.println("\n== Thông tin suất chiếu sau khi đặt ghế " + maGheDat + " ==");
        suat.hienThiThongTin();
        System.out.println();
    }
}
