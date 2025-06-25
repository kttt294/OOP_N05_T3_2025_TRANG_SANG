package test;

import model.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class testSuatChieu {
    public static void test() {
        Phim phim = new Phim("P001", "Inception", "Khoa học viễn tưởng", 148, "Tiếng Anh", 16, "Giấc mơ trong giấc mơ");

        PhongChieu phong = new PhongChieu("PC01", "Phòng 1");

        List<Ghe> dsGhe = new ArrayList<>();
        dsGhe.add(new Ghe("A1"));
        dsGhe.add(new Ghe("A2"));
        dsGhe.add(new Ghe("A3"));

        LocalDateTime thoiGianBatDau = LocalDateTime.of(2025, 7, 1, 18, 0);

        SuatChieu suat = new SuatChieu("SC001", phim, phong, thoiGianBatDau, dsGhe);

        System.out.println("== Trước khi đặt ghế ==");
        suat.hienThiThongTin();

        suat.capNhatGheTrong(new Ghe("A2"));

        System.out.println("\n== Sau khi đặt ghế A2 ==");
        suat.hienThiThongTin();
    }
}
