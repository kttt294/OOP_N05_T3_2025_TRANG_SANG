package com.example.servingwebcontent;

import com.example.servingwebcontent.model.DoAn;

import java.util.ArrayList;

public class testDoAn {

    public static void test() {
        testConstructor();
        testGettersAndSetters();
        testCRUD();
        testHienThiThongTin();
        testTimKiemTheoTen();
        testThongKeDoAn();
    }

    public static void testConstructor() {
        System.out.println("=== TEST CONSTRUCTOR ===");

        DoAn da1 = new DoAn();
        assert da1.getMaDoAn() == null;

        DoAn da2 = new DoAn("DA01", "Bắp rang bơ", 30000, 50);
        assert "DA01".equals(da2.getMaDoAn());
        assert "Bắp rang bơ".equals(da2.getTenDoAn());
        assert da2.getGia() == 30000;
        assert da2.getSoLuong() == 50;

        System.out.println("✓ Constructor OK");
    }

    public static void testGettersAndSetters() {
        System.out.println("=== TEST GETTERS/SETTERS ===");

        DoAn da = new DoAn();
        da.setMaDoAn("DA02");
        da.setTenDoAn("Nước ngọt");
        da.setGia(20000);
        da.setSoLuong(100);

        assert "DA02".equals(da.getMaDoAn());
        assert "Nước ngọt".equals(da.getTenDoAn());
        assert da.getGia() == 20000;
        assert da.getSoLuong() == 100;

        System.out.println("✓ Getters/Setters OK");
    }

    public static void testCRUD() {
        System.out.println("=== TEST CRUD ===");

        DoAn da = new DoAn("DA03", "Hotdog", 40000, 30);

        // Create
        boolean created = DoAn.Create(da);
        assert created : "✓ Tạo đồ ăn thành công";

        // Update
        DoAn daCapNhat = new DoAn("DA03", "Hotdog Phô Mai", 45000, 25);
        boolean updated = DoAn.Update(daCapNhat);
        assert updated;
        DoAn daGet = DoAn.getDoAnByMa("DA03");
        assert "Hotdog Phô Mai".equals(daGet.getTenDoAn());

        // Delete
        boolean deleted = DoAn.Delete("DA03");
        assert deleted;
        assert DoAn.getDoAnByMa("DA03") == null;

        System.out.println("✓ CRUD OK");
    }

    public static void testHienThiThongTin() {
        System.out.println("=== TEST HIỂN THỊ THÔNG TIN ===");

        DoAn da = new DoAn("DA04", "Khoai tây chiên", 25000, 10);
        da.hienThiThongTin();

        System.out.println("✓ Hiển thị thông tin OK (xem bằng mắt)");
    }

    public static void testTimKiemTheoTen() {
        System.out.println("=== TEST TÌM KIẾM THEO TÊN ===");

        DoAn.Create(new DoAn("DA05", "Pizza Gà", 70000, 5));
        DoAn.Create(new DoAn("DA06", "Pizza Hải sản", 80000, 8));
        DoAn.Create(new DoAn("DA07", "Bánh mì", 20000, 15));

        ArrayList<DoAn> result = DoAn.timKiemTheoTen("pizza");
        assert result.size() == 2 : "✓ Tìm thấy đúng 2 món có từ 'pizza'";

        System.out.println("✓ Tìm kiếm theo tên OK");
    }

    public static void testThongKeDoAn() {
        System.out.println("=== TEST THỐNG KÊ ĐỒ ĂN ===");

        DoAn.Create(new DoAn("DA08", "Bánh quy", 10000, 0));  // hết hàng
        DoAn.Create(new DoAn("DA09", "Kẹo dẻo", 15000, 0));   // hết hàng
        DoAn.Create(new DoAn("DA10", "Trà sữa", 45000, 20));  // còn hàng

        DoAn.thongKeDoAn();

        System.out.println("✓ Thống kê OK (kiểm tra số lượng và số hết hàng bằng mắt)");
    }
}
