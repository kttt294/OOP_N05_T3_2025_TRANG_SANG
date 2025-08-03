package com.example.servingwebcontent;

import com.example.servingwebcontent.model.PhongChieu;
import java.util.ArrayList;

public class testPhongChieu {
    public static void testConstructor() {
        System.out.println("=== TEST CONSTRUCTOR ===");
        PhongChieu p1 = new PhongChieu();
        assert p1.getMaPhong() == null;

        PhongChieu p2 = new PhongChieu("PC01", "Phòng VIP");
        assert p2.getTenPhong().equals("Phòng VIP");

        PhongChieu p3 = new PhongChieu("PC02", "Phòng 2D", 8, 10);
        assert p3.getSoHangGhe() == 8;
        assert p3.getSoCotGhe() == 10;

        System.out.println("✓ Constructor OK");
    }

    public static void testGetterSetter() {
        System.out.println("=== TEST GETTER/SETTER ===");
        PhongChieu p = new PhongChieu();
        p.setMaPhong("PC99");
        p.setTenPhong("Phòng test");
        p.setSoHangGhe(5);
        p.setSoCotGhe(6);

        assert p.getMaPhong().equals("PC99");
        assert p.getTenPhong().equals("Phòng test");
        assert p.getSoHangGhe() == 5;
        assert p.getSoCotGhe() == 6;

        System.out.println("✓ Getter/Setter OK");
    }

    public static void testCreateReadUpdateDelete() {
        System.out.println("=== TEST CRUD ===");
        PhongChieu p = new PhongChieu("PC123", "Phòng chiếu test", 7, 8);
        PhongChieu.Create(p);
        assert PhongChieu.getPhongByMa("PC123") != null;

        ArrayList<PhongChieu> list = PhongChieu.Read();
        assert list.size() > 0;

        PhongChieu pUpdate = new PhongChieu("PC123", "Phòng chiếu cập nhật", 10, 10);
        PhongChieu.Update("PC123", pUpdate);
        PhongChieu pAfterUpdate = PhongChieu.getPhongByMa("PC123");
        assert pAfterUpdate.getTenPhong().contains("cập nhật");
        assert pAfterUpdate.getSoHangGhe() == 10;

        PhongChieu.Delete("PC123");
        assert PhongChieu.getPhongByMa("PC123") == null;

        System.out.println("✓ CRUD OK");
    }

    public static void testTimKiem() {
        System.out.println("=== TEST TÌM KIẾM ===");

        PhongChieu p1 = new PhongChieu("PC201", "Phòng A", 5, 5);
        PhongChieu p2 = new PhongChieu("PC202", "Phòng B", 7, 7);
        PhongChieu p3 = new PhongChieu("PC203", "Phòng siêu lớn", 15, 15);

        PhongChieu.Create(p1);
        PhongChieu.Create(p2);
        PhongChieu.Create(p3);

        ArrayList<PhongChieu> result = PhongChieu.timKiemTheoTen("phòng");
        assert result.size() >= 3;

        ArrayList<PhongChieu> result2 = PhongChieu.timKiemTheoTen("siêu");
        assert result2.size() == 1;
        assert result2.get(0).getTenPhong().equals("Phòng siêu lớn");

        System.out.println("✓ Tìm kiếm OK");
    }

    public static void testThongKe() {
        System.out.println("=== TEST THỐNG KÊ ===");
        PhongChieu.thongKePhongChieu(); // In ra thống kê tổng ghế, loại phòng
        System.out.println("✓ Thống kê OK");
    }

    public static void test() {
        testConstructor();
        testGetterSetter();
        testCreateReadUpdateDelete();
        testTimKiem();
        testThongKe();
    }

}
