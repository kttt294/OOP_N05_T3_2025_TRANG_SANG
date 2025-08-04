package com.example.servingwebcontent;

import com.example.servingwebcontent.model.Ve;
import com.example.servingwebcontent.model.Ve.TrangThaiVe;

public class testVe {

    public static void testConstructor() {
        System.out.println("=== TEST CONSTRUCTOR ===");
        Ve ve1 = new Ve();
        assert ve1.getMaVe() == null;
        assert ve1.getCCCD() == null;
        assert ve1.getMaSuatChieu() == null;
        assert ve1.getMaGhe() == null;
        assert ve1.getGiaVe() == 0;
        assert ve1.getTrangThai() == null;
        System.out.println("✓ Constructor rỗng OK");

        Ve ve2 = new Ve("VE001", "123456789", "SC001", "A1", 100000);
        assert "VE001".equals(ve2.getMaVe());
        assert "123456789".equals(ve2.getCCCD());
        assert "SC001".equals(ve2.getMaSuatChieu());
        assert "A1".equals(ve2.getMaGhe());
        assert ve2.getGiaVe() == 100000;
        assert ve2.getTrangThai() == TrangThaiVe.CHUA_THANH_TOAN;
        System.out.println("✓ Constructor có tham số OK");
    }

    public static void testGettersSetters() {
        System.out.println("\n=== TEST GETTERS & SETTERS ===");
        Ve ve = new Ve();
        ve.setMaVe("VE002");
        ve.setCCCD("999888777");
        ve.setMaSuatChieu("SC002");
        ve.setMaGhe("B2");
        ve.setGiaVe(120000);
        ve.setTrangThai(TrangThaiVe.DA_THANH_TOAN);

        assert "VE002".equals(ve.getMaVe());
        assert "999888777".equals(ve.getCCCD());
        assert "SC002".equals(ve.getMaSuatChieu());
        assert "B2".equals(ve.getMaGhe());
        assert ve.getGiaVe() == 120000;
        assert ve.getTrangThai() == TrangThaiVe.DA_THANH_TOAN;
        System.out.println("✓ Getters và setters OK");
    }

    public static void testCRUD() {
        System.out.println("\n=== TEST CRUD ===");
        Ve ve = new Ve("VE100", "KH001", "SC100", "C5", 90000);
        Ve.Create(ve);
        assert Ve.getDanhSachVe().contains(ve);

        Ve veRead = Ve.getVeById("VE100");
        assert veRead != null && veRead.getMaGhe().equals("C5");

        ve.setGiaVe(95000);
        ve.setTrangThai(TrangThaiVe.DA_THANH_TOAN);
        Ve.Update("VE100", ve);
        assert Ve.getVeById("VE100").getGiaVe() == 95000;

        Ve.Delete("VE100");
        assert Ve.getVeById("VE100") == null;
        System.out.println("✓ CRUD OK");
    }

    public static void testGetVeIndex() {
        System.out.println("\n=== TEST GET INDEX ===");
        Ve ve = new Ve("VE200", "KH002", "SC200", "D7", 110000);
        Ve.Create(ve);
        assert Ve.getVeById("VE200") != null;
        Ve.Delete("VE200");
        System.out.println("✓ Get index by mã vé OK");
    }

    public static void testHienThiThongTin() {
        System.out.println("\n=== TEST HIEN THI THONG TIN ===");
        Ve ve = new Ve("VE300", "KH003", "SC300", "E3", 80000);
        ve.hienThiThongTin();
        System.out.println("✓ Hiển thị thông tin OK (kiểm tra thủ công)");
    }

    public static void testInvalidGiaVe() {
        System.out.println("\n=== TEST INVALID GIA VE ===");
        try {
            Ve ve = new Ve();
            ve.setGiaVe(-50000);
            assert false : "Phải ném lỗi vì giá vé âm";
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Ném lỗi khi giá vé âm OK");
        }
    }

    public static void test() {
        testConstructor();
        testGettersSetters();
        testCRUD();
        testGetVeIndex();
        testHienThiThongTin();
        testInvalidGiaVe();
    }
}
