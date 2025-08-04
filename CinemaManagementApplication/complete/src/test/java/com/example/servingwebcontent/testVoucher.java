package com.example.servingwebcontent.test;

import com.example.servingwebcontent.model.Voucher;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class testVoucher {

    public static void test() {
        testConstructor();
        testGettersandSetters();
        testCRUD();
        testGetVoucherById();
        testGetVoucherIndexById();
        testHienThiThongTin();
    }
    
    public static void testConstructor() {
        System.out.println("=== TEST CONSTRUCTOR ===");

        Voucher v1 = new Voucher();
        assert v1.getMaVoucher() == null;
        assert v1.getMoTa() == null;
        assert v1.getPhanTramGiamGia() == 0;
        assert v1.getNgayBatDau() == null;
        assert v1.getNgayKetThuc() == null;
        assert v1.getSoLuongConLai() == null;
        assert v1.getTrangThai() == null;
        System.out.println("✓ Constructor rỗng OK");

        LocalDateTime now = LocalDateTime.now();
        Voucher v2 = new Voucher("VC001", "Giảm giá 20%", 20, now, now.plusDays(5), "10", "Còn hiệu lực");
        assert "VC001".equals(v2.getMaVoucher());
        assert "Giảm giá 20%".equals(v2.getMoTa());
        assert v2.getPhanTramGiamGia() == 20;
        assert v2.getNgayBatDau().equals(now);
        assert v2.getNgayKetThuc().equals(now.plusDays(5));
        assert "10".equals(v2.getSoLuongConLai());
        assert "Còn hiệu lực".equals(v2.getTrangThai());
        System.out.println("✓ Constructor có tham số OK");
    }

    public static void testGettersandSetters() {
        System.out.println("=== TEST GETTERS & SETTERS ===");

        Voucher v = new Voucher();
        LocalDateTime ngayBD = LocalDateTime.of(2025, 8, 1, 10, 0);
        LocalDateTime ngayKT = LocalDateTime.of(2025, 8, 31, 23, 59);

        v.setMaVoucher("VC002");
        v.setMoTa("Voucher sinh nhật");
        v.setPhanTramGiamGia(15);
        v.setNgayBatDau(ngayBD);
        v.setNgayKetThuc(ngayKT);
        v.setSoLuongConLai("20");
        v.setTrangThai("Chưa sử dụng");

        assert "VC002".equals(v.getMaVoucher());
        assert "Voucher sinh nhật".equals(v.getMoTa());
        assert v.getPhanTramGiamGia() == 15;
        assert v.getNgayBatDau().equals(ngayBD);
        assert v.getNgayKetThuc().equals(ngayKT);
        assert "20".equals(v.getSoLuongConLai());
        assert "Chưa sử dụng".equals(v.getTrangThai());

        System.out.println("✓ Getters & Setters OK");
    }

    public static void testCRUD() {
        System.out.println("=== TEST CRUD ===");

        LocalDateTime now = LocalDateTime.now();
        Voucher v = new Voucher("VC003", "Giảm 10%", 10, now, now.plusDays(3), "5", "Còn hiệu lực");
        Voucher.Create(v);

        ArrayList<Voucher> list = Voucher.Read();
        assert list.stream().anyMatch(x -> "VC003".equals(x.getMaVoucher()));
        System.out.println("✓ Create & Read OK");

        Voucher vMoi = new Voucher("DUMMY", "Update mô tả", 25, now.plusDays(1), now.plusDays(6), "3", "Hết hạn");
        Voucher.Update("VC003", vMoi);
        Voucher updated = Voucher.getVoucherById("VC003");

        assert updated != null;
        assert "Update mô tả".equals(updated.getMoTa());
        assert updated.getPhanTramGiamGia() == 25;
        System.out.println("✓ Update OK");

        Voucher.Delete("VC003");
        assert Voucher.getVoucherById("VC003") == null;
        System.out.println("✓ Delete OK");
    }

    public static void testGetVoucherById() {
        System.out.println("=== TEST GET VOUCHER BY ID ===");
        LocalDateTime now = LocalDateTime.now();
        Voucher v = new Voucher("VC004", "Voucher Tết", 30, now, now.plusDays(7), "15", "Còn hiệu lực");
        Voucher.Create(v);

        Voucher found = Voucher.getVoucherById("VC004");
        assert found != null;
        assert "Voucher Tết".equals(found.getMoTa());
        System.out.println("✓ getVoucherById OK");
    }

    public static void testGetVoucherIndexById() {
        System.out.println("=== TEST GET VOUCHER INDEX BY ID ===");
        LocalDateTime now = LocalDateTime.now();
        Voucher v = new Voucher("VC005", "Test index", 5, now, now.plusDays(2), "2", "Còn");
        Voucher.Create(v);

        int index = getVoucherIndexById("VC005");
        assert index >= 0;
        assert Voucher.Read().get(index).getMaVoucher().equals("VC005");
        System.out.println("✓ getVoucherIndexById OK");
    }

    public static void testHienThiThongTin() {
        System.out.println("=== TEST HIỂN THỊ THÔNG TIN ===");
        LocalDateTime now = LocalDateTime.now();
        Voucher v = new Voucher("VC006", "Thông tin mẫu", 12, now, now.plusDays(1), "1", "Còn");
        v.hienThiThongTin();
        System.out.println("✓ hienThiThongTin OK");
    }

    private static int getVoucherIndexById(String maVoucher) {
        try {
            var method = Voucher.class.getDeclaredMethod("getVoucherIndexById", String.class);
            method.setAccessible(true);
            return (int) method.invoke(null, maVoucher);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
