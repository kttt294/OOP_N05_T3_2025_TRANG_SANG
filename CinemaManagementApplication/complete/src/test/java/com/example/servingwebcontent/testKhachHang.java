package com.example.servingwebcontent;

import com.example.servingwebcontent.model.KhachHang;
import com.example.servingwebcontent.model.HoaDon;
import com.example.servingwebcontent.model.DoAn;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class testKhachHang {

    public static void main(String[] args) {
        testConstructor();
        testGettersAndSetters();
        testCRUD();
        testGetKhachHangByCCCD();
        testGetKhachHangIndexByCCCD();
        testTimKiemTheoTen();
        testTimKiemTheoGioiTinh();
        testHienThiThongTin();
        testXemThongKe();
        testTinhTongTienKhachHang();
    }

    public static void testConstructor() {
        System.out.println("=== TEST CONSTRUCTOR ===");

        KhachHang kh1 = new KhachHang();
        assert kh1.getLichSuDatVe() != null;

        KhachHang kh2 = new KhachHang("123", "Nguyen Van A", 25, "0123456789", "a@gmail.com");
        assert "123".equals(kh2.getCCCD());
        assert "Nguyen Van A".equals(kh2.getTen());

        KhachHang kh3 = new KhachHang("456", "Tran Thi B", 30, "0987654321", "b@gmail.com", "Nữ");
        assert "Nữ".equals(kh3.getGioiTinh());

        System.out.println("✓ Constructor OK");
    }

    public static void testGettersAndSetters() {
        System.out.println("=== TEST GETTERS/SETTERS ===");

        KhachHang kh = new KhachHang();
        kh.setCCCD("001");
        kh.setTen("Test");
        kh.setTuoi(40);
        kh.setSdt("09090909");
        kh.setEmail("test@example.com");
        kh.setGioiTinh("Nam");

        assert "001".equals(kh.getCCCD());
        assert "Test".equals(kh.getTen());
        assert kh.getTuoi() == 40;
        assert "09090909".equals(kh.getSdt());
        assert "test@example.com".equals(kh.getEmail());
        assert "Nam".equals(kh.getGioiTinh());

        System.out.println("✓ Getters/Setters OK");
    }

    public static void testCRUD() {
        System.out.println("=== TEST CRUD ===");

        KhachHang kh = new KhachHang("999", "Tester", 20, "011", "t@a.com", "Khác");
        KhachHang.Create(kh);
        assert KhachHang.getKhachHangByCCCD("999") != null;

        KhachHang update = new KhachHang("000", "Updated", 21, "022", "new@email.com", "Nữ");
        KhachHang.Update("999", update);
        assert KhachHang.getKhachHangByCCCD("999").getTen().equals("Updated");

        KhachHang.Delete("999");
        assert KhachHang.getKhachHangByCCCD("999") == null;

        System.out.println("✓ CRUD OK");
    }

    public static void testGetKhachHangByCCCD() {
        System.out.println("=== TEST getKhachHangByCCCD ===");

        KhachHang kh = new KhachHang("K001", "Tên A", 18, "111", "a@a.com", "Nam");
        KhachHang.Create(kh);

        KhachHang result = KhachHang.getKhachHangByCCCD("K001");
        assert result != null;
        assert "Tên A".equals(result.getTen());

        System.out.println("✓ getKhachHangByCCCD OK");
    }

    public static void testGetKhachHangIndexByCCCD() {
        System.out.println("=== TEST getKhachHangIndexByCCCD ===");

        int index = KhachHang.getKhachHangIndexByCCCD("K001");
        assert index >= 0;

        System.out.println("✓ getKhachHangIndexByCCCD OK");
    }

    public static void testTimKiemTheoTen() {
        System.out.println("=== TEST timKiemTheoTen ===");

        KhachHang.Create(new KhachHang("K002", "Nguyen Thanh", 22, "222", "nt@a.com", "Nam"));
        ArrayList<KhachHang> result = KhachHang.timKiemTheoTen("nguyen");

        assert result.size() >= 1;
        System.out.println("✓ timKiemTheoTen OK");
    }

    public static void testTimKiemTheoGioiTinh() {
        System.out.println("=== TEST timKiemTheoGioiTinh ===");

        ArrayList<KhachHang> result = KhachHang.timKiemTheoGioiTinh("Nam");
        assert result.size() >= 1;
        System.out.println("✓ timKiemTheoGioiTinh OK");
    }

    public static void testHienThiThongTin() {
        System.out.println("=== TEST hienThiThongTin ===");

        KhachHang kh = new KhachHang("K003", "Nguyen Thi C", 35, "333", "c@a.com", "Nữ");
        kh.hienThiThongTin();
        System.out.println("✓ hienThiThongTin OK (xem console)");
    }

    public static void testXemThongKe() {
        System.out.println("=== TEST xemThongKe ===");

        KhachHang.Create(new KhachHang("K004", "Nguyen D", 17, "444", "d@a.com", "Nam"));
        KhachHang.Create(new KhachHang("K005", "Nguyen E", 65, "555", "e@a.com", "Nữ"));
        KhachHang.xemThongKe();
        System.out.println("✓ xemThongKe OK (xem console)");
    }

    public static void testTinhTongTienKhachHang() {
        System.out.println("=== TEST tinhTongTienKhachHang ===");

        // Giả sử CCCD là "K006"
        KhachHang kh = new KhachHang("K006", "Nguyen F", 50, "666", "f@a.com", "Nam");
        KhachHang.Create(kh);

        // Tạo hóa đơn cho khách hàng
        HoaDon.Read().add(new HoaDon("HD001", new DoAn("DA001", "Bắp", 30000, 10), 30000, LocalDateTime.now(), HoaDon.PhuongThuc.TIEN_MAT, "K006"));
        HoaDon.Read().add(new HoaDon("HD002", new DoAn("DA002", "Nước", 20000, 5), 20000, LocalDateTime.now(), HoaDon.PhuongThuc.CHUYEN_KHOAN, "K006"));

        double tong = kh.tinhTongTienKhachHang();
        assert tong == 50000;

        System.out.println("✓ Tính tổng tiền khách hàng OK: " + tong);
    }
}
