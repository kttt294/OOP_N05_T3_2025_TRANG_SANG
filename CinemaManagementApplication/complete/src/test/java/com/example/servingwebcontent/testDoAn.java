package com.example.servingwebcontent;

import com.example.servingwebcontent.model.DoAn;
import java.util.ArrayList;

public class testDoAn {

    public static void testConstructor() {
        System.out.println("=== TEST CONSTRUCTOR ===");

        DoAn da1 = new DoAn();
        assert da1.getMaDoAn() == null;
        assert da1.getTenDoAn() == null;
        System.out.println("✓ Constructor rỗng OK");

        DoAn da2 = new DoAn("DA001", "Bap rang bo", 20000, 50);
        assert "DA001".equals(da2.getMaDoAn());
        assert "Bap rang bo".equals(da2.getTenDoAn());
        assert da2.getGia() == 20000;
        assert da2.getSoLuong() == 50;
        System.out.println("✓ Constructor có tham số OK");
    }

    public static void testGetterSetter() {
        System.out.println("\n=== TEST GETTERS & SETTERS ===");
        DoAn da = new DoAn();
        da.setMaDoAn("DA002");
        da.setTenDoAn("Nuoc ngot");
        da.setGia(15000);
        da.setSoLuong(100);

        assert "DA002".equals(da.getMaDoAn());
        assert "Nuoc ngot".equals(da.getTenDoAn());
        assert da.getGia() == 15000;
        assert da.getSoLuong() == 100;
        System.out.println("✓ Getter/Setter OK");
    }

    public static void testCRUD() {
        System.out.println("\n=== TEST CRUD ===");
        DoAn da = new DoAn("DA003", "Banh mi", 10000, 20);
        assert DoAn.Create(da);
        assert DoAn.getDoAnByMa("DA003") != null;

        da.setSoLuong(10);
        assert DoAn.Update(da);
        assert DoAn.getDoAnByMa("DA003").getSoLuong() == 10;

        assert DoAn.Delete("DA003");
        assert DoAn.getDoAnByMa("DA003") == null;
        System.out.println("✓ CRUD OK");
    }

    public static void testReadAndDisplay() {
        System.out.println("\n=== TEST READ & DISPLAY ===");
        DoAn da = new DoAn("DA004", "Khoai tay chien", 30000, 15);
        DoAn.Create(da);
        DoAn.Read("DA004");
        ArrayList<DoAn> all = DoAn.Read();
        assert all.size() >= 1;
        System.out.println("✓ Read và hiển thị OK");
    }

    public static void testTimKiem() {
        System.out.println("\n=== TEST TIM KIEM ===");
        DoAn da1 = new DoAn("DA005", "Pepsi", 15000, 50);
        DoAn da2 = new DoAn("DA006", "Pepsi lon", 18000, 30);
        DoAn.Create(da1);
        DoAn.Create(da2);

        ArrayList<DoAn> ketQuaTen = DoAn.timKiemTheoTen("Pepsi");
        assert ketQuaTen.size() >= 2;

        ArrayList<DoAn> ketQuaLoai = DoAn.timKiemTheoLoai("lon");
        assert ketQuaLoai.size() >= 1;
        System.out.println("✓ Tìm kiếm OK");
    }

    public static void testThongKe() {
        System.out.println("\n=== TEST THONG KE ===");
        DoAn da1 = new DoAn("DA007", "Banh gao", 10000, 0);
        DoAn da2 = new DoAn("DA008", "Banh quy", 20000, 5);
        DoAn.Create(da1);
        DoAn.Create(da2);
        DoAn.thongKeDoAn();
        System.out.println("✓ Thống kê OK (Xem output thủ công)");
    }

    public static void testValidation() {
        System.out.println("\n=== TEST VALIDATION ===");
        try {
            new DoAn(null, "Nuoc cam", 10000, 10);
            System.out.println("✗ Không bắt được lỗi mã null");
        } catch (Exception e) {
            System.out.println("✓ Bắt lỗi mã null: " + e.getMessage());
        }

        try {
            new DoAn("DA009", null, 10000, 10);
            System.out.println("✗ Không bắt được lỗi tên null");
        } catch (Exception e) {
            System.out.println("✓ Bắt lỗi tên null: " + e.getMessage());
        }

        try {
            new DoAn("DA010", "Com rang", -5000, 10);
            System.out.println("✗ Không bắt được lỗi giá âm");
        } catch (Exception e) {
            System.out.println("✓ Bắt lỗi giá âm: " + e.getMessage());
        }

        try {
            new DoAn("DA011", "My xao", 20000, -1);
            System.out.println("✗ Không bắt được lỗi số lượng âm");
        } catch (Exception e) {
            System.out.println("✓ Bắt lỗi số lượng âm: " + e.getMessage());
        }
    }

    public static void test() {
        testConstructor();
        testGetterSetter();
        testCRUD();
        testReadAndDisplay();
        testTimKiem();
        testThongKe();
        testValidation();
    }
}
