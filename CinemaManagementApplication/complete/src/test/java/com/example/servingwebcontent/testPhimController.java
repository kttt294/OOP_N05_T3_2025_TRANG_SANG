package com.example.servingwebcontent;

import com.example.servingwebcontent.controller.PhimController;
import com.example.servingwebcontent.model.Phim;

import java.util.ArrayList;

public class testPhimController {

    private static PhimController controller = new PhimController();

    public static void test() {
        testTaoPhim();
        testCapNhatPhim();
        testXoaPhim();
        testXemThongTinPhim();
        testXemTatCaPhim();
        testTimPhimTheoMa();
        testTimPhimTheoTen();
        testTimPhimTheoTheLoai();
        testThongKePhim();
    }

    public static void testTaoPhim() {
        System.out.println("=== TEST taoPhim ===");
        Phim phim = new Phim("PHIM001", "Inception", "Sci-Fi", 148, "English", 13, "Dream in a dream");
        assert controller.taoPhim(phim);
        assert Phim.getPhimById("PHIM001") != null;
        System.out.println("✓ Tạo phim OK");
    }

    public static void testCapNhatPhim() {
        System.out.println("=== TEST capNhatPhim ===");
        Phim phimMoi = new Phim("PHIM001", "Inception 2", "Action", 150, "English", 16, "New sequel");
        assert controller.capNhatPhim("PHIM001", phimMoi);
        Phim phim = Phim.getPhimById("PHIM001");
        assert "Inception 2".equals(phim.getTenPhim());
        assert phim.getThoiLuong() == 150;
        assert "Action".equals(phim.getTheLoai());
        System.out.println("✓ Cập nhật phim OK");
    }

    public static void testXoaPhim() {
        System.out.println("=== TEST xoaPhim ===");
        assert controller.xoaPhim("PHIM001");
        assert Phim.getPhimById("PHIM001") == null;
        System.out.println("✓ Xóa phim OK");
    }

    public static void testXemThongTinPhim() {
        System.out.println("=== TEST xemThongTinPhim ===");
        Phim phim = new Phim("PHIM002", "Avatar", "Fantasy", 162, "English", 10, "Blue people on Pandora");
        Phim.Create(phim);
        assert controller.xemThongTinPhim("PHIM002");
        System.out.println("✓ Xem thông tin phim OK");
    }

    public static void testXemTatCaPhim() {
        System.out.println("=== TEST xemTatCaPhim ===");
        Phim.Create(new Phim("PHIM003", "Titanic", "Romance", 195, "English", 12, "Ship sinks"));
        Phim.Create(new Phim("PHIM004", "Up", "Animation", 96, "English", 6, "Old man flies house"));
        assert controller.xemTatCaPhim();
        System.out.println("✓ Xem tất cả phim OK");
    }

    public static void testTimPhimTheoMa() {
        System.out.println("=== TEST timPhimTheoMa ===");
        Phim phim = new Phim("PHIM005", "The Matrix", "Sci-Fi", 136, "English", 16, "Simulation world");
        Phim.Create(phim);
        Phim found = controller.timPhimTheoMa("PHIM005");
        assert found != null;
        assert "The Matrix".equals(found.getTenPhim());
        System.out.println("✓ Tìm phim theo mã OK");
    }

    public static void testTimPhimTheoTen() {
        System.out.println("=== TEST timPhimTheoTen ===");
        Phim.Create(new Phim("PHIM006", "Avengers", "Action", 143, "English", 13, "Superhero team"));
        ArrayList<Phim> list = controller.timPhimTheoTen("Avengers");
        assert !list.isEmpty();
        System.out.println("✓ Tìm phim theo tên OK");
    }

    public static void testTimPhimTheoTheLoai() {
        System.out.println("=== TEST timPhimTheoTheLoai ===");
        Phim.Create(new Phim("PHIM007", "Iron Man", "Action", 126, "English", 13, "Billionaire superhero"));
        ArrayList<Phim> list = controller.timPhimTheoTheLoai("Action");
        assert list.size() > 0;
        System.out.println("✓ Tìm phim theo thể loại OK");
    }

    public static void testThongKePhim() {
        System.out.println("=== TEST thongKePhim ===");
        assert controller.thongKePhim();
        System.out.println("✓ Thống kê phim OK");
    }
}
