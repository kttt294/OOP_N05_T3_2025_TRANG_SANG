package com.example.servingwebcontent;

import com.example.servingwebcontent.controller.GheController;
import com.example.servingwebcontent.model.Ghe;

public class testGheController {

    private static GheController controller = new GheController();

    public static void test() {
        testTaoGhe();
        testCapNhatGhe();
        testXoaGhe();
        testXemThongTinGhe();
        testXemTatCaGhe();
        testTimGheTheoMa();
    }

    public static void testTaoGhe() {
        System.out.println("=== TEST taoGhe ===");
        Ghe ghe = new Ghe("GHE001", 1, 2, "PHONG01");
        assert controller.taoGhe(ghe);
        assert Ghe.getGheByMaGhe("GHE001") != null;
        System.out.println("✓ Tạo ghế OK");
    }

    public static void testCapNhatGhe() {
        System.out.println("=== TEST capNhatGhe ===");
        Ghe gheMoi = new Ghe("GHE001", 3, 4, "PHONG01");
        assert controller.capNhatGhe("GHE001", gheMoi);
        Ghe ghe = Ghe.getGheByMaGhe("GHE001");
        assert ghe.getHang() == 3;
        assert ghe.getCot() == 4;
        assert "PHONG01".equals(ghe.getMaPhong());
        System.out.println("✓ Cập nhật ghế OK");
    }

    public static void testXoaGhe() {
        System.out.println("=== TEST xoaGhe ===");
        assert controller.xoaGhe("GHE001");
        assert Ghe.getGheByMaGhe("GHE001") == null;
        System.out.println("✓ Xóa ghế OK");
    }

    public static void testXemThongTinGhe() {
        System.out.println("=== TEST xemThongTinGhe ===");
        Ghe ghe = new Ghe("GHE002", 5, 6, "PHONG02");
        Ghe.Create(ghe);
        assert controller.xemThongTinGhe("GHE002");
        System.out.println("✓ Xem thông tin ghế OK");
    }

    public static void testXemTatCaGhe() {
        System.out.println("=== TEST xemTatCaGhe ===");
        Ghe.Create(new Ghe("GHE003", 2, 2, "PHONG03"));
        Ghe.Create(new Ghe("GHE004", 3, 3, "PHONG03"));
        assert controller.xemTatCaGhe();
        System.out.println("✓ Xem tất cả ghế OK");
    }

    public static void testTimGheTheoMa() {
        System.out.println("=== TEST timGheTheoMa ===");
        Ghe ghe = new Ghe("GHE005", 1, 1, "PHONG05");
        Ghe.Create(ghe);
        Ghe found = controller.timGheTheoMa("GHE005");
        assert found != null;
        assert "GHE005".equals(found.getMaGhe());
        System.out.println("✓ Tìm ghế theo mã OK");
    }
}
