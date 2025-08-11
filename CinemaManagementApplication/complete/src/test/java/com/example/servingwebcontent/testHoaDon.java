package com.example.servingwebcontent;

import com.example.servingwebcontent.model.HoaDon;
import com.example.servingwebcontent.model.Ve;
import com.example.servingwebcontent.model.DoAn;
import com.example.servingwebcontent.model.Voucher;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class testHoaDon {

    public static void testConstructor() {
        System.out.println("=== TEST CONSTRUCTOR ===");
        
        // Test constructor rỗng
        HoaDon hoaDon1 = new HoaDon();
        assert hoaDon1.getMaHoaDon() == null;
        assert hoaDon1.getCCCD() == null;
        assert hoaDon1.getThoiGianThanhToan() == null;
        assert hoaDon1.getTongTien() == 0;
        assert hoaDon1.getDanhSachVe() != null && hoaDon1.getDanhSachVe().isEmpty();
        assert hoaDon1.getDanhSachDoAn() != null && hoaDon1.getDanhSachDoAn().isEmpty();
        assert hoaDon1.getDanhSachVoucher() != null && hoaDon1.getDanhSachVoucher().isEmpty();
        System.out.println("✓ Constructor rỗng OK");

        // Test constructor với tham số đầy đủ
        List<Ve> danhSachVe = new ArrayList<>();
        danhSachVe.add(new Ve("VE001", "123456789", "SC001", "A1", 50000));
        
        List<DoAn> danhSachDoAn = new ArrayList<>();
        DoAn doAn = new DoAn();
        doAn.setMaDoAn("DA001");
        doAn.setTenDoAn("Bắp rang bơ");
        doAn.setGia(25000);
        danhSachDoAn.add(doAn);
        
        LocalDateTime now = LocalDateTime.now();
        HoaDon hoaDon2 = new HoaDon("HD001", danhSachVe, danhSachDoAn, 0, now, HoaDon.PhuongThuc.TIEN_MAT, "123456789");
        
        assert "HD001".equals(hoaDon2.getMaHoaDon());
        assert "123456789".equals(hoaDon2.getCCCD());
        assert now.equals(hoaDon2.getThoiGianThanhToan());
        assert hoaDon2.getTongTien() == 75000; // 50000 + 25000
        assert hoaDon2.getDanhSachVe().size() == 1;
        assert hoaDon2.getDanhSachDoAn().size() == 1;
        System.out.println("✓ Constructor với tham số đầy đủ OK");

        // Test constructor cũ (tương thích ngược)
        HoaDon hoaDon3 = new HoaDon("HD002", doAn, 30000, now, HoaDon.PhuongThuc.CHUYEN_KHOAN, "987654321");
        assert "HD002".equals(hoaDon3.getMaHoaDon());
        assert hoaDon3.getDanhSachDoAn().size() == 1;
        assert hoaDon3.getDanhSachDoAn().get(0).getMaDoAn().equals("DA001");
        System.out.println("✓ Constructor cũ (tương thích ngược) OK");
    }

    public static void testGettersandSetters() {
        System.out.println("=== TEST GETTERS & SETTERS ===");
        HoaDon hoaDon = new HoaDon();
        
        hoaDon.setMaHoaDon("HD003");
        hoaDon.setCCCD("111222333");
        hoaDon.setThoiGianThanhToan(LocalDateTime.now());
        hoaDon.setPhuongThuc(HoaDon.PhuongThuc.CHUYEN_KHOAN);
        hoaDon.setTongTien(100000);

        assert "HD003".equals(hoaDon.getMaHoaDon());
        assert "111222333".equals(hoaDon.getCCCD());
        assert hoaDon.getThoiGianThanhToan() != null;
        assert hoaDon.getPhuongThuc() == HoaDon.PhuongThuc.CHUYEN_KHOAN;
        assert hoaDon.getTongTien() == 100000;
        System.out.println("✓ Getters & Setters cơ bản OK");

        // Test getters & setters cho danh sách
        List<Ve> danhSachVe = new ArrayList<>();
        danhSachVe.add(new Ve("VE002", "111222333", "SC002", "B1", 60000));
        hoaDon.setDanhSachVe(danhSachVe);
        assert hoaDon.getDanhSachVe().size() == 1;
        assert hoaDon.getDanhSachVe().get(0).getMaVe().equals("VE002");

        List<DoAn> danhSachDoAn = new ArrayList<>();
        DoAn doAn = new DoAn();
        doAn.setMaDoAn("DA002");
        doAn.setTenDoAn("Nước ngọt");
        doAn.setGia(15000);
        danhSachDoAn.add(doAn);
        hoaDon.setDanhSachDoAn(danhSachDoAn);
        assert hoaDon.getDanhSachDoAn().size() == 1;
        assert hoaDon.getDanhSachDoAn().get(0).getMaDoAn().equals("DA002");

        List<Voucher> danhSachVoucher = new ArrayList<>();
        Voucher voucher = new Voucher();
        voucher.setMaVoucher("VC001");
        voucher.setPhanTramGiamGia(10.0f);
        voucher.setTrangThai("HoatDong");
        danhSachVoucher.add(voucher);
        hoaDon.setDanhSachVoucher(danhSachVoucher);
        assert hoaDon.getDanhSachVoucher().size() == 1;
        assert hoaDon.getDanhSachVoucher().get(0).getMaVoucher().equals("VC001");

        System.out.println("✓ Getters & Setters cho danh sách OK");
    }

    public static void testCRUD() {
        System.out.println("=== TEST CRUD ===");
        
        // Test Create
        HoaDon hoaDon = new HoaDon("HD004", new ArrayList<>(), new ArrayList<>(), 0, LocalDateTime.now(), HoaDon.PhuongThuc.TIEN_MAT, "444555666");
        HoaDon.Create(hoaDon);
        ArrayList<HoaDon> list = HoaDon.Read();
        assert list.stream().anyMatch(h -> "HD004".equals(h.getMaHoaDon()));
        System.out.println("✓ Create OK");

        // Test Update
        HoaDon hoaDonMoi = new HoaDon("HD004", new ArrayList<>(), new ArrayList<>(), 0, LocalDateTime.now(), HoaDon.PhuongThuc.CHUYEN_KHOAN, "444555666");
        hoaDonMoi.setTongTien(200000);
        HoaDon.Update("HD004", hoaDonMoi);
        HoaDon updated = HoaDon.getHoaDonById("HD004");
        assert updated != null;
        assert updated.getTongTien() == 200000;
        assert updated.getPhuongThuc() == HoaDon.PhuongThuc.CHUYEN_KHOAN;
        System.out.println("✓ Update OK");

        // Test Delete
        HoaDon.Delete("HD004");
        assert HoaDon.getHoaDonById("HD004") == null;
        System.out.println("✓ Delete OK");
    }

    public static void testGetHoaDonById() {
        System.out.println("=== TEST GET HOA DON BY ID ===");
        HoaDon hoaDon = new HoaDon("HD005", new ArrayList<>(), new ArrayList<>(), 0, LocalDateTime.now(), HoaDon.PhuongThuc.TIEN_MAT, "777888999");
        HoaDon.Create(hoaDon);
        HoaDon found = HoaDon.getHoaDonById("HD005");
        assert found != null;
        assert "777888999".equals(found.getCCCD());
        System.out.println("✓ GetHoaDonById OK");
    }

    public static void testThemXoaVe() {
        System.out.println("=== TEST THÊM/XÓA VÉ ===");
        HoaDon hoaDon = new HoaDon();
        
        // Test thêm vé
        Ve ve1 = new Ve("VE003", "123456789", "SC003", "C1", 70000);
        Ve ve2 = new Ve("VE004", "123456789", "SC003", "C2", 70000);
        
        hoaDon.themVe(ve1);
        assert hoaDon.getDanhSachVe().size() == 1;
        assert hoaDon.getTongTien() == 70000;
        
        hoaDon.themVe(ve2);
        assert hoaDon.getDanhSachVe().size() == 2;
        assert hoaDon.getTongTien() == 140000;
        
        // Test xóa vé
        hoaDon.xoaVe(ve1);
        assert hoaDon.getDanhSachVe().size() == 1;
        assert hoaDon.getTongTien() == 70000;
        
        hoaDon.xoaVeTheoMa("VE004");
        assert hoaDon.getDanhSachVe().isEmpty();
        assert hoaDon.getTongTien() == 0;
        
        System.out.println("✓ Thêm/xóa vé OK");
    }

    public static void testThemXoaDoAn() {
        System.out.println("=== TEST THÊM/XÓA ĐỒ ĂN ===");
        HoaDon hoaDon = new HoaDon();
        
        // Test thêm đồ ăn
        DoAn doAn1 = new DoAn();
        doAn1.setMaDoAn("DA003");
        doAn1.setTenDoAn("Bánh ngọt");
        doAn1.setGia(20000);
        
        DoAn doAn2 = new DoAn();
        doAn2.setMaDoAn("DA004");
        doAn2.setTenDoAn("Kẹo");
        doAn2.setGia(10000);
        
        hoaDon.themDoAn(doAn1);
        assert hoaDon.getDanhSachDoAn().size() == 1;
        assert hoaDon.getTongTien() == 20000;
        
        hoaDon.themDoAn(doAn2);
        assert hoaDon.getDanhSachDoAn().size() == 2;
        assert hoaDon.getTongTien() == 30000;
        
        // Test xóa đồ ăn
        hoaDon.xoaDoAn(doAn1);
        assert hoaDon.getDanhSachDoAn().size() == 1;
        assert hoaDon.getTongTien() == 10000;
        
        hoaDon.xoaDoAnTheoMa("DA004");
        assert hoaDon.getDanhSachDoAn().isEmpty();
        assert hoaDon.getTongTien() == 0;
        
        System.out.println("✓ Thêm/xóa đồ ăn OK");
    }

    public static void testThemXoaVoucher() {
        System.out.println("=== TEST THÊM/XÓA VOUCHER ===");
        HoaDon hoaDon = new HoaDon();
        
        // Thêm vé và đồ ăn để có tổng tiền
        Ve ve = new Ve("VE005", "123456789", "SC004", "D1", 100000);
        hoaDon.themVe(ve);
        
        DoAn doAn = new DoAn();
        doAn.setMaDoAn("DA005");
        doAn.setTenDoAn("Combo");
        doAn.setGia(50000);
        hoaDon.themDoAn(doAn);
        
        // Test thêm voucher
        Voucher voucher1 = new Voucher();
        voucher1.setMaVoucher("VC002");
        voucher1.setPhanTramGiamGia(20.0f);
        voucher1.setTrangThai("HoatDong");
        
        Voucher voucher2 = new Voucher();
        voucher2.setMaVoucher("VC003");
        voucher2.setPhanTramGiamGia(10.0f);
        voucher2.setTrangThai("HoatDong");
        
        hoaDon.themVoucher(voucher1);
        assert hoaDon.getDanhSachVoucher().size() == 1;
        // Tổng tiền trước: 150000, giảm 20% = 120000
        assert hoaDon.getTongTien() == 120000;
        
        hoaDon.themVoucher(voucher2);
        assert hoaDon.getDanhSachVoucher().size() == 2;
        // Tổng tiền trước: 150000, giảm 30% = 105000
        assert hoaDon.getTongTien() == 105000;
        
        // Test xóa voucher
        hoaDon.xoaVoucher(voucher1);
        assert hoaDon.getDanhSachVoucher().size() == 1;
        // Chỉ còn voucher 10%: 150000 - 15% = 127500
        assert hoaDon.getTongTien() == 127500;
        
        hoaDon.xoaVoucherTheoMa("VC003");
        assert hoaDon.getDanhSachVoucher().isEmpty();
        // Không còn voucher: 150000
        assert hoaDon.getTongTien() == 150000;
        
        System.out.println("✓ Thêm/xóa voucher OK");
    }

    public static void testTinhTongTien() {
        System.out.println("=== TEST TÍNH TỔNG TIỀN ===");
        HoaDon hoaDon = new HoaDon();
        
        // Thêm vé
        hoaDon.themVe(new Ve("VE006", "123456789", "SC005", "E1", 80000));
        hoaDon.themVe(new Ve("VE007", "123456789", "SC005", "E2", 80000));
        
        // Thêm đồ ăn
        DoAn doAn = new DoAn();
        doAn.setMaDoAn("DA006");
        doAn.setTenDoAn("Snack");
        doAn.setGia(25000);
        hoaDon.themDoAn(doAn);
        
        // Tổng tiền: 160000 + 25000 = 185000
        assert hoaDon.getTongTien() == 185000;
        
        // Thêm voucher giảm 25%
        Voucher voucher = new Voucher();
        voucher.setMaVoucher("VC004");
        voucher.setPhanTramGiamGia(25.0f);
        voucher.setTrangThai("HoatDong");
        hoaDon.themVoucher(voucher);
        
        // Tổng tiền sau giảm: 185000 - 25% = 138750
        assert hoaDon.getTongTien() == 138750;
        
        // Test tính tổng tiền thủ công
        hoaDon.tinhTongTien();
        assert hoaDon.getTongTien() == 138750;
        
        System.out.println("✓ Tính tổng tiền OK");
    }

    public static void testHienThiThongTin() {
        System.out.println("=== TEST HIỂN THỊ THÔNG TIN ===");
        HoaDon hoaDon = new HoaDon("HD006", new ArrayList<>(), new ArrayList<>(), 0, LocalDateTime.now(), HoaDon.PhuongThuc.TIEN_MAT, "999888777");
        
        // Thêm vé
        hoaDon.themVe(new Ve("VE008", "999888777", "SC006", "F1", 90000));
        
        // Hiển thị thông tin
        hoaDon.hienThiThongTin();
        System.out.println("✓ Hiển thị thông tin OK");
    }

    public static void test() {
        testConstructor();
        testGettersandSetters();
        testCRUD();
        testGetHoaDonById();
        testThemXoaVe();
        testThemXoaDoAn();
        testThemXoaVoucher();
        testTinhTongTien();
        testHienThiThongTin();
    }
}
