package com.example.servingwebcontent;

import com.example.servingwebcontent.model.Phim;
import java.util.ArrayList;

public class testPhim {
    public static void testConstructor() {
        System.out.println("=== TEST CONSTRUCTOR ===");
        Phim p1 = new Phim();
        assert p1.getMaPhim() == null;

        Phim p2 = new Phim("P001", "Avengers");
        assert p2.getMaPhim().equals("P001");
        assert p2.getTenPhim().equals("Avengers");

        Phim p3 = new Phim("P002", "Inception", "Khoa học viễn tưởng", 148, "Anh", 13, "Phim hack não");
        assert p3.getThoiLuong() == 148;
        assert p3.getGioiHanTuoi() == 13;
        assert p3.getMoTa().equals("Phim hack não");

        System.out.println("✓ Constructor OK");
    }

    public static void testGetterSetter() {
        System.out.println("=== TEST GETTER/SETTER ===");
        Phim p = new Phim();
        p.setMaPhim("P100");
        p.setTenPhim("Test Movie");
        p.setTheLoai("Tình cảm");
        p.setThoiLuong(120);
        p.setNgonNgu("Việt");
        p.setGioiHanTuoi(16);
        p.setMoTa("Phim test đơn giản");

        assert p.getMaPhim().equals("P100");
        assert p.getTenPhim().equals("Test Movie");
        assert p.getTheLoai().equals("Tình cảm");
        assert p.getThoiLuong() == 120;
        assert p.getNgonNgu().equals("Việt");
        assert p.getGioiHanTuoi() == 16;
        assert p.getMoTa().equals("Phim test đơn giản");

        System.out.println("✓ Getter/Setter OK");
    }

    public static void testCreateReadUpdateDelete() {
        System.out.println("=== TEST CRUD ===");
        Phim p = new Phim("P123", "Titanic", "Tình cảm", 195, "Anh", 16, "Kinh điển");
        Phim.Create(p);
        assert Phim.getPhimById("P123") != null;

        ArrayList<Phim> list = Phim.Read();
        assert list.size() > 0;

        Phim updated = new Phim("P123", "Titanic (Remastered)", "Tình cảm", 200, "Anh", 16, "Bản mới");
        Phim.Update("P123", updated);
        assert Phim.getPhimById("P123").getThoiLuong() == 200;
        assert Phim.getPhimById("P123").getTenPhim().contains("Remastered");

        Phim.Delete("P123");
        assert Phim.getPhimById("P123") == null;

        System.out.println("✓ CRUD OK");
    }

    public static void testTimKiem() {
        System.out.println("=== TEST TÌM KIẾM ===");

        Phim p1 = new Phim("P201", "Spider-Man", "Hành động", 130, "Anh", 13, "Siêu anh hùng");
        Phim p2 = new Phim("P202", "Spider-Verse", "Hoạt hình", 115, "Anh", 10, "Phim hoạt hình siêu đỉnh");
        Phim p3 = new Phim("P203", "Romance in Paris", "Tình cảm", 100, "Pháp", 16, "Lãng mạn");

        Phim.Create(p1);
        Phim.Create(p2);
        Phim.Create(p3);

        ArrayList<Phim> ten = Phim.timKiemTheoTen("Spider");
        assert ten.size() == 2;

        ArrayList<Phim> theLoai = Phim.timKiemTheoTheLoai("Tình cảm");
        assert theLoai.size() >= 1;

        System.out.println("✓ Tìm kiếm OK");
    }

    public static void testThongKe() {
        System.out.println("=== TEST THỐNG KÊ ===");
        Phim.thongKePhim(); // In ra console
        System.out.println("✓ Thống kê OK");
    }

    public static void main(String[] args) {
        testConstructor();
        testGetterSetter();
        testCreateReadUpdateDelete();
        testTimKiem();
        testThongKe();
    }
}
