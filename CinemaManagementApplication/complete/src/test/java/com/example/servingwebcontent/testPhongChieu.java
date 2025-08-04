package com.example.servingwebcontent;

import com.example.servingwebcontent.model.PhongChieu;

public class testPhongChieu {

    public static void test() {
        testConstructor();
        testGettersandSetters();
        testCRUD();
        testGetPhongByMa();
        testGetPhongIndexByMa();
        testHienThiThongTin();
        testThongKePhongChieu();
    }

    public static void testConstructor() {
        System.out.println("=== TEST CONSTRUCTOR ===");

        PhongChieu pc1 = new PhongChieu();
        assert pc1.getMaPhong() == null;

        PhongChieu pc2 = new PhongChieu("P001", "Phòng VIP");
        assert "P001".equals(pc2.getMaPhong());
        assert "Phòng VIP".equals(pc2.getTenPhong());

        PhongChieu pc3 = new PhongChieu("P002", "Phòng thường", 5, 10);
        assert pc3.getSoHangGhe() == 5;
        assert pc3.getSoCotGhe() == 10;

        System.out.println("✓ Constructor OK");
    }

    public static void testGettersandSetters() {
        System.out.println("=== TEST GETTERS/SETTERS ===");

        PhongChieu pc = new PhongChieu();
        pc.setMaPhong("P003");
        pc.setTenPhong("Phòng A");
        pc.setSoHangGhe(6);
        pc.setSoCotGhe(8);

        assert "P003".equals(pc.getMaPhong());
        assert "Phòng A".equals(pc.getTenPhong());
        assert pc.getSoHangGhe() == 6;
        assert pc.getSoCotGhe() == 8;

        System.out.println("✓ Getters/Setters OK");
    }

    public static void testCRUD() {
        System.out.println("=== TEST CRUD ===");

        PhongChieu phong = new PhongChieu("P004", "Phòng B", 4, 10);
        PhongChieu.Create(phong);
        assert PhongChieu.getPhongByMa("P004") != null;

        PhongChieu updated = new PhongChieu("NEW", "Phòng B Updated", 5, 12);
        PhongChieu.Update("P004", updated);
        PhongChieu result = PhongChieu.getPhongByMa("P004");
        assert result != null;
        assert result.getTenPhong().equals("Phòng B Updated");

        PhongChieu.Delete("P004");
        assert PhongChieu.getPhongByMa("P004") == null;

        System.out.println("✓ CRUD OK");
    }

    public static void testGetPhongByMa() {
        System.out.println("=== TEST getPhongByMa ===");

        PhongChieu.Create(new PhongChieu("P005", "Phòng C", 3, 10));
        PhongChieu result = PhongChieu.getPhongByMa("P005");

        assert result != null;
        assert result.getTenPhong().equals("Phòng C");

        System.out.println("✓ getPhongByMa OK");
    }

    public static void testGetPhongIndexByMa() {
        System.out.println("=== TEST getPhongIndexByMa ===");

        int index = getPhongIndexByMaProxy("P005");
        assert index >= 0;
        System.out.println("✓ getPhongIndexByMa OK – Index: " + index);
    }

    // Dùng reflection vì getPhongIndexByMa là private
    private static int getPhongIndexByMaProxy(String maPhong) {
        try {
            java.lang.reflect.Method method = PhongChieu.class.getDeclaredMethod("getPhongIndexByMa", String.class);
            method.setAccessible(true);
            return (int) method.invoke(null, maPhong);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void testHienThiThongTin() {
        System.out.println("=== TEST hienThiThongTin ===");

        PhongChieu pc = new PhongChieu("P006", "Phòng D", 7, 9);
        pc.hienThiThongTin();

        System.out.println("✓ hienThiThongTin OK (kiểm tra console)");
    }

    public static void testThongKePhongChieu() {
        System.out.println("=== TEST thongKePhongChieu ===");

        PhongChieu.Create(new PhongChieu("P007", "Phòng E", 3, 10));  // 30 ghế
        PhongChieu.Create(new PhongChieu("P008", "Phòng F", 5, 10));  // 50 ghế
        PhongChieu.Create(new PhongChieu("P009", "Phòng G", 10, 12)); // 120 ghế

        PhongChieu.thongKePhongChieu();

        System.out.println("✓ thongKePhongChieu OK (kiểm tra console)");
    }
}
