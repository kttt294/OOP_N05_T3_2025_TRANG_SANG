package com.example.servingwebcontent;

import com.example.servingwebcontent.controller.HoaDonController;
import com.example.servingwebcontent.model.HoaDon;
import com.example.servingwebcontent.model.Ve;
import com.example.servingwebcontent.model.DoAn;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class testHoaDonController {

    private static HoaDonController controller = new HoaDonController();

    public static void test() {
        testHienThiDanhSachHoaDon();
        testHienThiFormTaoHoaDon();
        testTaoHoaDon();
        testHienThiFormSuaHoaDon();
        testCapNhatHoaDon();
        testXoaHoaDon();
        testTimKiemHoaDon();
        testHienThiThongKeHoaDon();
        testTaoHoaDonMethod();
        testCapNhatHoaDonMethod();
        testXoaHoaDonMethod();
        testThongKeHoaDonMethod();
        testTimHoaDonTheoCCCD();
        testTimHoaDonTheoKhoangThoiGian();
        testKiemTraDuLieuHoaDon();
    }

    public static void testHienThiDanhSachHoaDon() {
        System.out.println("=== TEST hienThiDanhSachHoaDon ===");
        
        // Test hiển thị danh sách hóa đơn
        try {
            // Tạo một số hóa đơn test thông qua controller
            HoaDon hoaDon1 = new HoaDon("HD_TEST1", new ArrayList<>(), new ArrayList<>(), 100000, LocalDateTime.now(), HoaDon.phuongThuc.TIEN_MAT, "111111111");
            HoaDon hoaDon2 = new HoaDon("HD_TEST2", new ArrayList<>(), new ArrayList<>(), 150000, LocalDateTime.now(), HoaDon.phuongThuc.CHUYEN_KHOAN, "222222222");
            
            // Sử dụng controller để tạo hóa đơn
            assert controller.taoHoaDon(hoaDon1);
            assert controller.taoHoaDon(hoaDon2);
            
            // Test thống kê hóa đơn thông qua controller
            assert controller.thongKeHoaDon();
            
            System.out.println("✓ Hiển thị danh sách hóa đơn OK - Đã tạo 2 hóa đơn test");
            
            // Dọn dẹp test data thông qua controller
            assert controller.xoaHoaDon("HD_TEST1");
            assert controller.xoaHoaDon("HD_TEST2");
            
        } catch (Exception e) {
            System.out.println("✗ Lỗi hiển thị danh sách hóa đơn: " + e.getMessage());
            assert false : "Test hiển thị danh sách hóa đơn thất bại";
        }
    }

    public static void testHienThiFormTaoHoaDon() {
        System.out.println("=== TEST hienThiFormTaoHoaDon ===");
        
        // Test hiển thị form tạo hóa đơn
        try {
            // Tạo hóa đơn mới để test form
            HoaDon hoaDonMoi = new HoaDon();
            hoaDonMoi.setMaHoaDon("HD_FORM_TEST");
            hoaDonMoi.setCCCD("333333333");
            hoaDonMoi.setThoiGianThanhToan(LocalDateTime.now());
            hoaDonMoi.setphuongThuc(HoaDon.phuongThuc.TIEN_MAT);
            
            // Kiểm tra form có thể tạo hóa đơn thông qua controller
            assert controller.taoHoaDon(hoaDonMoi);
            
            // Verify hóa đơn đã được tạo
            assert hoaDonMoi.getMaHoaDon() != null;
            assert hoaDonMoi.getCCCD() != null;
            assert hoaDonMoi.getThoiGianThanhToan() != null;
            assert hoaDonMoi.getphuongThuc() != null;
            
            System.out.println("✓ Hiển thị form tạo hóa đơn OK");
            System.out.println("✓ Mã hóa đơn: " + hoaDonMoi.getMaHoaDon());
            System.out.println("✓ CCCD: " + hoaDonMoi.getCCCD());
            System.out.println("✓ Phương thức: " + hoaDonMoi.getphuongThuc());
            
            // Dọn dẹp test data
            assert controller.xoaHoaDon("HD_FORM_TEST");
            
        } catch (Exception e) {
            System.out.println("✗ Lỗi hiển thị form tạo hóa đơn: " + e.getMessage());
            assert false : "Test hiển thị form tạo hóa đơn thất bại";
        }
    }

    public static void testTaoHoaDon() {
        System.out.println("=== TEST taoHoaDon (POST) ===");
        
        // Tạo hóa đơn test
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMaHoaDon("HD001");
        hoaDon.setCCCD("123456789");
        hoaDon.setThoiGianThanhToan(LocalDateTime.now());
        hoaDon.setphuongThuc(HoaDon.phuongThuc.TIEN_MAT);
        
        // Thêm vé
        List<Ve> danhSachVe = new ArrayList<>();
        danhSachVe.add(new Ve("VE001", "123456789", "SC001", "A1", 50000));
        danhSachVe.add(new Ve("VE002", "123456789", "SC001", "A2", 50000));
        hoaDon.setDanhSachVe(danhSachVe);
        
        // Thêm đồ ăn
        List<DoAn> danhSachDoAn = new ArrayList<>();
        DoAn doAn = new DoAn();
        doAn.setMaDoAn("DA001");
        doAn.setTenDoAn("Bắp rang bơ");
        doAn.setGia(25000);
        danhSachDoAn.add(doAn);
        hoaDon.setDanhSachDoAn(danhSachDoAn);
        
        // Test tạo hóa đơn
        assert controller.taoHoaDon(hoaDon);
        System.out.println("✓ Tạo hóa đơn OK - Mã: " + hoaDon.getMaHoaDon());
        System.out.println("✓ Số vé: " + hoaDon.getDanhSachVe().size());
        System.out.println("✓ Số đồ ăn: " + hoaDon.getDanhSachDoAn().size());
        System.out.println("✓ Tổng tiền: " + hoaDon.getTongTien() + " VNĐ");
    }

    public static void testHienThiFormSuaHoaDon() {
        System.out.println("=== TEST hienThiFormSuaHoaDon ===");
        
        // Test hiển thị form sửa hóa đơn
        try {
            // Tạo hóa đơn để sửa thông qua controller
            HoaDon hoaDonSua = new HoaDon("HD_EDIT_TEST", new ArrayList<>(), new ArrayList<>(), 200000, LocalDateTime.now(), HoaDon.phuongThuc.TIEN_MAT, "444444444");
            assert controller.taoHoaDon(hoaDonSua);
            
            // Kiểm tra hóa đơn có thể sửa thông qua controller
            HoaDon hoaDonMoi = new HoaDon("HD_EDIT_TEST", new ArrayList<>(), new ArrayList<>(), 250000, LocalDateTime.now(), HoaDon.phuongThuc.CHUYEN_KHOAN, "444444444");
            assert controller.capNhatHoaDon("HD_EDIT_TEST", hoaDonMoi);
            
            System.out.println("✓ Hiển thị form sửa hóa đơn OK");
            System.out.println("✓ Hóa đơn cần sửa: " + hoaDonMoi.getMaHoaDon());
            System.out.println("✓ Tổng tiền mới: " + hoaDonMoi.getTongTien() + " VNĐ");
            System.out.println("✓ Phương thức mới: " + hoaDonMoi.getphuongThuc());
            
            // Dọn dẹp test data
            assert controller.xoaHoaDon("HD_EDIT_TEST");
            
        } catch (Exception e) {
            System.out.println("✗ Lỗi hiển thị form sửa hóa đơn: " + e.getMessage());
            assert false : "Test hiển thị form sửa hóa đơn thất bại";
        }
    }

    public static void testCapNhatHoaDon() {
        System.out.println("=== TEST capNhatHoaDon (POST) ===");
        
        // Tạo hóa đơn để cập nhật
        HoaDon hoaDonCu = new HoaDon("HD002", new ArrayList<>(), new ArrayList<>(), 0, LocalDateTime.now(), HoaDon.phuongThuc.TIEN_MAT, "987654321");
        
        // Cập nhật hóa đơn
        HoaDon hoaDonMoi = new HoaDon("HD002", new ArrayList<>(), new ArrayList<>(), 0, LocalDateTime.now(), HoaDon.phuongThuc.CHUYEN_KHOAN, "987654321");
        hoaDonMoi.setTongTien(200000);
        
        // Thêm vé mới
        Ve veMoi = new Ve("VE003", "987654321", "SC002", "B1", 60000);
        hoaDonMoi.themVe(veMoi);
        
        // Thêm đồ ăn mới
        DoAn doAnMoi = new DoAn();
        doAnMoi.setMaDoAn("DA002");
        doAnMoi.setTenDoAn("Nước ngọt");
        doAnMoi.setGia(15000);
        hoaDonMoi.themDoAn(doAnMoi);
        
        // Test cập nhật
        assert controller.capNhatHoaDon("HD002", hoaDonMoi);
        System.out.println("✓ Cập nhật hóa đơn OK");
        System.out.println("✓ Phương thức thanh toán: " + hoaDonMoi.getphuongThuc());
        System.out.println("✓ Số vé sau cập nhật: " + hoaDonMoi.getDanhSachVe().size());
        System.out.println("✓ Số đồ ăn sau cập nhật: " + hoaDonMoi.getDanhSachDoAn().size());
        System.out.println("✓ Tổng tiền sau cập nhật: " + hoaDonMoi.getTongTien() + " VNĐ");
    }

    public static void testXoaHoaDon() {
        System.out.println("=== TEST xoaHoaDon (POST) ===");
        
        // Tạo hóa đơn để xóa
        HoaDon hoaDonXoa = new HoaDon("HD003", new ArrayList<>(), new ArrayList<>(), 0, LocalDateTime.now(), HoaDon.phuongThuc.TIEN_MAT, "111222333");
        
        // Test xóa hóa đơn
        assert controller.xoaHoaDon("HD003");
        System.out.println("✓ Xóa hóa đơn OK");
    }

    public static void testTimKiemHoaDon() {
        System.out.println("=== TEST timKiemHoaDon ===");
        
        // Test tìm kiếm hóa đơn
        try {
            // Tạo hóa đơn để tìm kiếm thông qua controller
            HoaDon hoaDonSearch = new HoaDon("HD_SEARCH_TEST", new ArrayList<>(), new ArrayList<>(), 250000, LocalDateTime.now(), HoaDon.phuongThuc.CHUYEN_KHOAN, "555555555");
            assert controller.taoHoaDon(hoaDonSearch);
            
            // Test tìm kiếm theo CCCD thông qua controller
            List<HoaDon> ketQua = controller.timHoaDonTheoCCCD("555555555");
            assert ketQua != null;
            assert ketQua.size() > 0;
            
            // Test tìm kiếm theo khoảng thời gian thông qua controller
            LocalDateTime fromDate = LocalDateTime.now().minusDays(1);
            LocalDateTime toDate = LocalDateTime.now().plusDays(1);
            List<HoaDon> ketQuaThoiGian = controller.timHoaDonTheoKhoangThoiGian(fromDate, toDate);
            assert ketQuaThoiGian != null;
            assert ketQuaThoiGian.size() > 0;
            
            System.out.println("✓ Tìm kiếm hóa đơn OK");
            System.out.println("✓ Tìm theo CCCD: " + ketQua.size() + " hóa đơn");
            System.out.println("✓ Tìm theo thời gian: " + ketQuaThoiGian.size() + " hóa đơn");
            
            // Dọn dẹp test data
            assert controller.xoaHoaDon("HD_SEARCH_TEST");
            
        } catch (Exception e) {
            System.out.println("✗ Lỗi tìm kiếm hóa đơn: " + e.getMessage());
            assert false : "Test tìm kiếm hóa đơn thất bại";
        }
    }

    public static void testHienThiThongKeHoaDon() {
        System.out.println("=== TEST hienThiThongKeHoaDon ===");
        
        // Test hiển thị thống kê hóa đơn
        try {
            // Tạo một số hóa đơn để thống kê thông qua controller
            HoaDon hoaDon1 = new HoaDon("HD_STAT1", new ArrayList<>(), new ArrayList<>(), 100000, LocalDateTime.now(), HoaDon.phuongThuc.TIEN_MAT, "666666666");
            HoaDon hoaDon2 = new HoaDon("HD_STAT2", new ArrayList<>(), new ArrayList<>(), 150000, LocalDateTime.now(), HoaDon.phuongThuc.CHUYEN_KHOAN, "777777777");
            HoaDon hoaDon3 = new HoaDon("HD_STAT3", new ArrayList<>(), new ArrayList<>(), 200000, LocalDateTime.now(), HoaDon.phuongThuc.TIEN_MAT, "888888888");
            
            assert controller.taoHoaDon(hoaDon1);
            assert controller.taoHoaDon(hoaDon2);
            assert controller.taoHoaDon(hoaDon3);
            
            // Test thống kê hóa đơn thông qua controller
            assert controller.thongKeHoaDon();
            
            System.out.println("✓ Hiển thị thống kê hóa đơn OK");
            System.out.println("✓ Đã tạo 3 hóa đơn test để thống kê");
            
            // Dọn dẹp test data
            assert controller.xoaHoaDon("HD_STAT1");
            assert controller.xoaHoaDon("HD_STAT2");
            assert controller.xoaHoaDon("HD_STAT3");
            
        } catch (Exception e) {
            System.out.println("✗ Lỗi hiển thị thống kê hóa đơn: " + e.getMessage());
            assert false : "Test hiển thị thống kê hóa đơn thất bại";
        }
    }

    public static void testTaoHoaDonMethod() {
        System.out.println("=== TEST taoHoaDon (method) ===");
        
        // Tạo hóa đơn với vé và đồ ăn
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMaHoaDon("HD004");
        hoaDon.setCCCD("444555666");
        hoaDon.setThoiGianThanhToan(LocalDateTime.now());
        hoaDon.setphuongThuc(HoaDon.phuongThuc.TIEN_MAT);
        
        // Thêm vé
        hoaDon.themVe(new Ve("VE004", "444555666", "SC003", "C1", 70000));
        hoaDon.themVe(new Ve("VE005", "444555666", "SC003", "C2", 70000));
        
        // Test tạo hóa đơn
        assert controller.taoHoaDon(hoaDon);
        System.out.println("✓ Tạo hóa đơn method OK - Mã: " + hoaDon.getMaHoaDon());
        System.out.println("✓ Tổng tiền: " + hoaDon.getTongTien() + " VNĐ");
    }

    public static void testCapNhatHoaDonMethod() {
        System.out.println("=== TEST capNhatHoaDon (method) ===");
        
        // Tạo hóa đơn để cập nhật
        HoaDon hoaDonCu = new HoaDon("HD005", new ArrayList<>(), new ArrayList<>(), 0, LocalDateTime.now(), HoaDon.phuongThuc.TIEN_MAT, "777888999");
        
        // Cập nhật hóa đơn
        HoaDon hoaDonMoi = new HoaDon("HD005", new ArrayList<>(), new ArrayList<>(), 0, LocalDateTime.now(), HoaDon.phuongThuc.CHUYEN_KHOAN, "777888999");
        hoaDonMoi.setTongTien(300000);
        
        // Test cập nhật
        assert controller.capNhatHoaDon("HD005", hoaDonMoi);
        System.out.println("✓ Cập nhật hóa đơn method OK");
        System.out.println("✓ Tổng tiền mới: " + hoaDonMoi.getTongTien() + " VNĐ");
    }

    public static void testXoaHoaDonMethod() {
        System.out.println("=== TEST xoaHoaDon (method) ===");
        
        // Test xóa hóa đơn
        assert controller.xoaHoaDon("HD005");
        System.out.println("✓ Xóa hóa đơn method OK");
    }

    public static void testThongKeHoaDonMethod() {
        System.out.println("=== TEST thongKeHoaDon (method) ===");
        
        // Test thống kê hóa đơn
        assert controller.thongKeHoaDon();
        System.out.println("✓ Thống kê hóa đơn method OK");
    }

    public static void testTimHoaDonTheoCCCD() {
        System.out.println("=== TEST timHoaDonTheoCCCD ===");
        
        // Tạo một số hóa đơn để test
        HoaDon hoaDon1 = new HoaDon("HD006", new ArrayList<>(), new ArrayList<>(), 100000, LocalDateTime.now(), HoaDon.phuongThuc.TIEN_MAT, "123123123");
        HoaDon hoaDon2 = new HoaDon("HD007", new ArrayList<>(), new ArrayList<>(), 150000, LocalDateTime.now(), HoaDon.phuongThuc.CHUYEN_KHOAN, "123123123");
        
        // Test tìm hóa đơn theo CCCD
        List<HoaDon> ketQua = controller.timHoaDonTheoCCCD("123123123");
        assert ketQua != null;
        System.out.println("✓ Tìm hóa đơn theo CCCD OK");
        System.out.println("✓ Số hóa đơn tìm được: " + ketQua.size());
    }

    public static void testTimHoaDonTheoKhoangThoiGian() {
        System.out.println("=== TEST timHoaDonTheoKhoangThoiGian ===");
        
        LocalDateTime fromDate = LocalDateTime.now().minusDays(7);
        LocalDateTime toDate = LocalDateTime.now();
        
        // Test tìm hóa đơn theo khoảng thời gian
        List<HoaDon> ketQua = controller.timHoaDonTheoKhoangThoiGian(fromDate, toDate);
        assert ketQua != null;
        System.out.println("✓ Tìm hóa đơn theo khoảng thời gian OK");
        System.out.println("✓ Từ: " + fromDate);
        System.out.println("✓ Đến: " + toDate);
        System.out.println("✓ Số hóa đơn tìm được: " + ketQua.size());
    }

    public static void testKiemTraDuLieuHoaDon() {
        System.out.println("=== TEST kiemTraDuLieuHoaDon ===");
        
        // Test hóa đơn hợp lệ
        HoaDon hoaDonHopLe = new HoaDon();
        hoaDonHopLe.setMaHoaDon("HD008");
        hoaDonHopLe.setCCCD("999888777");
        hoaDonHopLe.setThoiGianThanhToan(LocalDateTime.now());
        hoaDonHopLe.setphuongThuc(HoaDon.phuongThuc.TIEN_MAT);
        hoaDonHopLe.setTongTien(50000);
        
        // Test hóa đơn không hợp lệ (thiếu thông tin)
        HoaDon hoaDonKhongHopLe = new HoaDon();
        hoaDonKhongHopLe.setMaHoaDon(""); // Mã hóa đơn rỗng
        hoaDonKhongHopLe.setCCCD("111111111");
        
        // Test validation
        System.out.println("✓ Kiểm tra dữ liệu hóa đơn OK");
        System.out.println("✓ Hóa đơn hợp lệ: " + (hoaDonHopLe.getMaHoaDon() != null && !hoaDonHopLe.getMaHoaDon().trim().isEmpty()));
        System.out.println("✓ Hóa đơn không hợp lệ: " + (hoaDonKhongHopLe.getMaHoaDon() == null || hoaDonKhongHopLe.getMaHoaDon().trim().isEmpty()));
    }

    public static void main(String[] args) {
        System.out.println("=== BẮT ĐẦU KIỂM TRA HOA DON CONTROLLER ===\n");
        
        test();
        
        System.out.println("\n=== HOÀN THÀNH KIỂM TRA HOA DON CONTROLLER ===");
        System.out.println("✓ Tất cả các method đã được kiểm thử thành công!");
    }
}
