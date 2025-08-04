package com.example.servingwebcontent;

import com.example.servingwebcontent.model.Ghe;
import com.example.servingwebcontent.model.Ghe.TrangThaiGhe;

import java.util.ArrayList;

public class testGhe {

    public static void test() {
        testConstructor();
        testGettersAndSetters();
        testTrangThaiLogic();
        testCRUD();
        testGetGheByMaGhe();
        testGetGheIndexByMaGhe();
    }

    public static void testConstructor() {
        System.out.println("=== TEST CONSTRUCTOR ===");

        Ghe ghe1 = new Ghe();
        assert ghe1.getMaGhe() == null;

        Ghe ghe2 = new Ghe("G01", 2, 3, "P01");
        assert "G01".equals(ghe2.getMaGhe());
        assert ghe2.getHang() == 2;
        assert ghe2.getCot() == 3;
        assert "P01".equals(ghe2.getMaPhong());
        assert ghe2.getTrangThai() == TrangThaiGhe.TRONG;

        Ghe ghe3 = new Ghe("G02", 1, 1, "P02", "SC01", TrangThaiGhe.DA_DAT);
        assert "SC01".equals(ghe3.getMaSuatChieu());
        assert ghe3.getTrangThai() == TrangThaiGhe.DA_DAT;

        System.out.println("✓ Constructor OK");
    }

    public static void testGettersAndSetters() {
        System.out.println("=== TEST GETTERS/SETTERS ===");

        Ghe ghe = new Ghe();
        ghe.setMaGhe("G10");
        ghe.setHang(4);
        ghe.setCot(5);
        ghe.setMaPhong("P03");
        ghe.setMaSuatChieu("SC99");
        ghe.setTrangThai(TrangThaiGhe.KHOA);

        assert "G10".equals(ghe.getMaGhe());
        assert ghe.getHang() == 4;
        assert ghe.getCot() == 5;
        assert "P03".equals(ghe.getMaPhong());
        assert "SC99".equals(ghe.getMaSuatChieu());
        assert ghe.getTrangThai() == TrangThaiGhe.KHOA;

        System.out.println("✓ Getters/Setters OK");
    }

    public static void testTrangThaiLogic() {
        System.out.println("=== TEST TRẠNG THÁI LOGIC ===");

        Ghe ghe = new Ghe("G20", 2, 2, "P01");
        assert ghe.isTrong();
        assert !ghe.isDaDat();

        ghe.datGhe();
        assert ghe.isDaDat();

        ghe.huyDat();
        assert ghe.isTrong();

        System.out.println("✓ Trạng thái logic OK");
    }

    public static void testCRUD() {
        System.out.println("=== TEST CRUD ===");

        Ghe ghe = new Ghe("G30", 1, 1, "P04");
        Ghe.Create(ghe);
        assert Ghe.getGheByMaGhe("G30") != null;

        Ghe gheUpdate = new Ghe("NEWID", 5, 5, "P05", "SC05", TrangThaiGhe.KHOA);
        Ghe.Update("G30", gheUpdate);
        Ghe gAfterUpdate = Ghe.getGheByMaGhe("G30");
        assert gAfterUpdate != null;
        assert gAfterUpdate.getTrangThai() == TrangThaiGhe.KHOA;
        assert gAfterUpdate.getHang() == 5;

        Ghe.Delete("G30");
        assert Ghe.getGheByMaGhe("G30") == null;

        System.out.println("✓ CRUD OK");
    }

    public static void testGetGheByMaGhe() {
        System.out.println("=== TEST getGheByMaGhe ===");

        Ghe ghe = new Ghe("G40", 3, 3, "P06");
        Ghe.Create(ghe);

        Ghe result = Ghe.getGheByMaGhe("G40");
        assert result != null;
        assert "G40".equals(result.getMaGhe());

        System.out.println("✓ getGheByMaGhe OK");
    }

    public static void testGetGheIndexByMaGhe() {
        System.out.println("=== TEST getGheIndexByMaGhe ===");

        Ghe ghe = new Ghe("G50", 6, 6, "P07");
        Ghe.Create(ghe);

        int index = Ghe.getGheIndexByMaGhe("G50");
        assert index >= 0;

        System.out.println("✓ getGheIndexByMaGhe OK");
    }
}
