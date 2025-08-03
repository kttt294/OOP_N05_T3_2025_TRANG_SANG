package com.example.servingwebcontent;

import com.example.servingwebcontent.model.Ghe;
import com.example.servingwebcontent.model.Phim;
import com.example.servingwebcontent.model.SuatChieu;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class testSuatChieu {
    public static void testConstructor() {
        System.out.println("=== TEST CONSTRUCTOR ===");
        List<Ghe> danhSachGhe = new ArrayList<>();
        danhSachGhe.add(new Ghe("GHE01", 1, 1, Ghe.LoaiGhe.THUONG, "PH01"));

        SuatChieu sc = new SuatChieu("SC001", "P001", "PH01", LocalDateTime.now(), danhSachGhe);
        assert sc.getMaSuatChieu().equals("SC001");
        assert sc.getMaPhim().equals("P001");
        assert sc.getDanhSachGheTrong().size() == 1;

        System.out.println("✓ Constructor OK");
    }

    public static void testGetterSetter() {
        System.out.println("=== TEST GETTER/SETTER ===");
        SuatChieu sc = new SuatChieu();
        sc.setMaSuatChieu("SC002");
        sc.setMaPhim("P002");
        sc.setMaPhong("PH02");
        sc.setThoiGianBatDau(LocalDateTime.of(2025, 8, 1, 15, 0));

        List<Ghe> gheList = new ArrayList<>();
        gheList.add(new Ghe("GHE02", 2, 2, Ghe.LoaiGhe.VIP, "PH02"));
        sc.setDanhSachGheTrong(gheList);

        assert sc.getMaSuatChieu().equals("SC002");
        assert sc.getMaPhim().equals("P002");
        assert sc.getDanhSachGheTrong().size() == 1;

        System.out.println("✓ Getter/Setter OK");
    }

    public static void testCreateReadUpdateDelete() {
        System.out.println("=== TEST CRUD ===");

        List<Ghe> gheList = new ArrayList<>();
        gheList.add(new Ghe("GHE03", 3, 3, Ghe.LoaiGhe.COUPLE, "PH03"));

        SuatChieu sc = new SuatChieu("SC003", "P003", "PH03", LocalDateTime.of(2025, 8, 2, 18, 0), gheList);
        SuatChieu.Create(sc);
        assert SuatChieu.getSuatChieuById("SC003") != null;

        SuatChieu updated = new SuatChieu("SC003", "P003", "PH03", LocalDateTime.of(2025, 8, 2, 20, 0), gheList);
        SuatChieu.Update("SC003", updated);
        assert SuatChieu.getSuatChieuById("SC003").getThoiGianBatDau().getHour() == 20;

        SuatChieu.Delete("SC003");
        assert SuatChieu.getSuatChieuById("SC003") == null;

        System.out.println("✓ CRUD OK");
    }

    public static void testTimKiem() {
        System.out.println("=== TEST TÌM KIẾM ===");

        List<Ghe> gheList = new ArrayList<>();
        gheList.add(new Ghe("GHE04", 4, 4, Ghe.LoaiGhe.THUONG, "PH04"));

        SuatChieu sc1 = new SuatChieu("SC101", "P101", "PH04", LocalDateTime.now(), gheList);
        SuatChieu sc2 = new SuatChieu("SC102", "P101", "PH05", LocalDateTime.now(), gheList);
        SuatChieu sc3 = new SuatChieu("SC103", "P102", "PH04", LocalDateTime.now(), gheList);

        SuatChieu.Create(sc1);
        SuatChieu.Create(sc2);
        SuatChieu.Create(sc3);

        assert SuatChieu.getSuatChieuByPhim("P101").size() == 2;
        assert SuatChieu.getSuatChieuByPhong("PH04").size() >= 2;

        System.out.println("✓ Tìm kiếm OK");
    }

    public static void testTinhThoiGianKetThuc() {
        System.out.println("=== TEST TÍNH THỜI GIAN KẾT THÚC ===");

        Phim.Create(new Phim("P888", "Phim thử nghiệm", "Hành động", 120, "Việt", 13, ""));
        SuatChieu sc = new SuatChieu("SC888", "P888", "PH88", LocalDateTime.of(2025, 8, 3, 14, 0), new ArrayList<>());
        LocalDateTime ketThuc = sc.tinhThoiGianKetThuc();
        assert ketThuc.getHour() == 16;

        System.out.println("✓ Tính thời gian kết thúc OK");
    }

    public static void testCapNhatGheTrong() {
        System.out.println("=== TEST CẬP NHẬT GHẾ TRỐNG ===");
        Ghe ghe = new Ghe("GHE999", 1, 1, Ghe.LoaiGhe.THUONG, "PHX");

        List<Ghe> danhSach = new ArrayList<>();
        danhSach.add(ghe);
        danhSach.add(new Ghe("GHE998", 1, 2, Ghe.LoaiGhe.THUONG, "PHX"));

        SuatChieu sc = new SuatChieu("SC999", "P999", "PHX", LocalDateTime.now(), new ArrayList<>(danhSach));
        assert sc.getDanhSachGheTrong().size() == 2;

        sc.capNhatGheTrong(ghe);
        assert sc.getDanhSachGheTrong().size() == 1;

        System.out.println("✓ Cập nhật ghế trống OK");
    }

    public static void test() {
        testConstructor();
        testGetterSetter();
        testCreateReadUpdateDelete();
        testTimKiem();
        testTinhThoiGianKetThuc();
        testCapNhatGheTrong();
    }
}
