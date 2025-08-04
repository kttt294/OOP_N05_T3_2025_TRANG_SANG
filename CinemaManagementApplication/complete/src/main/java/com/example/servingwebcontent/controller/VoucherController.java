package com.example.servingwebcontent.test;

import com.example.servingwebcontent.controller.VoucherController;
import com.example.servingwebcontent.model.Voucher;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class testVoucherController {

    public static void test() {
        testTaoVoucher();
        testCapNhatVoucher();
        testXemThongTinVoucher();
        testXemTatCaVoucher();
        testTimVoucherTheoMa();
        testTimVoucherTheoTen();
        testKiemTraVoucherHopLe();
        testSuDungVoucher();
        testThongKeVoucher();
        testXoaVoucher();
    }

    public static void testTaoVoucher() {
        System.out.println("=== TEST: TẠO VOUCHER ===");
        Voucher v = new Voucher("VC001", "Giảm 10%", 10, LocalDateTime.now().minusDays(1),
                LocalDateTime.now().plusDays(10), "10", "HoatDong");
        assert VoucherController.taoVoucher(v) : "Tạo voucher thất bại";
        System.out.println("✓ Tạo voucher OK\n");
    }

    public static void testCapNhatVoucher() {
        System.out.println("=== TEST: CẬP NHẬT VOUCHER ===");
        Voucher vMoi = new Voucher("VC001", "Giảm 15%", 15, LocalDateTime.now().minusDays(1),
                LocalDateTime.now().plusDays(5), "5", "HoatDong");
        assert VoucherController.capNhatVoucher("VC001", vMoi) : "Cập nhật voucher thất bại";
        System.out.println("✓ Cập nhật voucher OK\n");
    }

    public static void testXoaVoucher() {
        System.out.println("=== TEST: XOÁ VOUCHER ===");
        assert VoucherController.xoaVoucher("VC001") : "Xoá voucher thất bại";
        System.out.println("✓ Xoá voucher OK\n");
    }

    public static void testXemThongTinVoucher() {
        System.out.println("=== TEST: XEM THÔNG TIN VOUCHER ===");
        assert VoucherController.xemThongTinVoucher("VC001") : "Xem thông tin thất bại";
        System.out.println("✓ Xem thông tin voucher OK\n");
    }

    public static void testXemTatCaVoucher() {
        System.out.println("=== TEST: XEM TẤT CẢ VOUCHER ===");
        assert VoucherController.xemTatCaVoucher() : "Xem tất cả thất bại";
        System.out.println("✓ Xem tất cả voucher OK\n");
    }

    public static void testTimVoucherTheoMa() {
        System.out.println("=== TEST: TÌM VOUCHER THEO MÃ ===");
        Voucher v = VoucherController.timVoucherTheoMa("VC001");
        assert v != null : "Không tìm thấy voucher";
        System.out.println("✓ Tìm voucher theo mã OK\n");
    }

    public static void testTimVoucherTheoTen() {
        System.out.println("=== TEST: TÌM VOUCHER THEO TÊN ===");
        ArrayList<Voucher> list = VoucherController.timVoucherTheoTen("Giảm");
        assert list != null && !list.isEmpty() : "Không tìm thấy theo mô tả";
        System.out.println("✓ Tìm voucher theo tên OK\n");
    }

    public static void testKiemTraVoucherHopLe() {
        System.out.println("=== TEST: KIỂM TRA VOUCHER HỢP LỆ ===");
        assert VoucherController.kiemTraVoucherHopLe("VC001") : "Voucher không hợp lệ";
        System.out.println("✓ Voucher hợp lệ OK\n");
    }

    public static void testSuDungVoucher() {
        System.out.println("=== TEST: SỬ DỤNG VOUCHER ===");
        assert VoucherController.suDungVoucher("VC001") : "Không sử dụng được";
        System.out.println("✓ Sử dụng voucher OK\n");
    }

    public static void testThongKeVoucher() {
        System.out.println("=== TEST: THỐNG KÊ VOUCHER ===");
        assert VoucherController.thongKeVoucher() : "Thống kê thất bại";
        System.out.println("✓ Thống kê voucher OK\n");
    }
}
