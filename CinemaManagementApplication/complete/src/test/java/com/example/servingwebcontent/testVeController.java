package com.example.servingwebcontent;

import com.example.servingwebcontent.controller.VeController;
import com.example.servingwebcontent.model.Ve;

import java.util.ArrayList;

public class testVeController {

    private static VeController controller = new VeController();

    public static void test() {
        testTaoVe();
        testCapNhatVe();
        testXemThongTinVe();
        testXemTatCaVe();
        testTimVeTheoMa();
        testTimVeTheoKhachHang();
        testTimVeTheoSuatChieu();
        testThongKeVe();
        testHuyVe();
        testTinhTongDoanhThu();
        testXoaVe();
    }

    public static void testTaoVe() {
        System.out.println("=== TEST: TẠO VÉ ===");
        Ve ve = new Ve("VE001", "123456789", "SC001", "A1", 50000);
        assert controller.taoVe(ve) : "Tạo vé thất bại";
        System.out.println("✓ Tạo vé OK\n");
    }

    public static void testCapNhatVe() {
        System.out.println("=== TEST: CẬP NHẬT VÉ ===");
        Ve veMoi = new Ve("VE001", "123456789", "SC001", "A1", 55000);
        veMoi.setTrangThai(Ve.TrangThaiVe.DA_THANH_TOAN);
        assert controller.capNhatVe("VE001", veMoi) : "Cập nhật vé thất bại";
        System.out.println("✓ Cập nhật vé OK\n");
    }

    public static void testXoaVe() {
        System.out.println("=== TEST: XOÁ VÉ ===");
        assert controller.xoaVe("VE001") : "Xoá vé thất bại";
        System.out.println("✓ Xoá vé OK\n");
    }

    public static void testXemThongTinVe() {
        System.out.println("=== TEST: XEM THÔNG TIN VÉ ===");
        assert controller.xemThongTinVe("VE001") : "Xem vé thất bại";
        System.out.println("✓ Xem thông tin vé OK\n");
    }

    public static void testXemTatCaVe() {
        System.out.println("=== TEST: XEM TẤT CẢ VÉ ===");
        assert controller.xemTatCaVe() : "Xem tất cả vé thất bại";
        System.out.println("✓ Xem tất cả vé OK\n");
    }

    public static void testHuyVe() {
        System.out.println("=== TEST: HỦY VÉ ===");
        Ve ve = new Ve("VE002", "123456789", "SC002", "B2", 60000);
        controller.taoVe(ve);
        assert controller.huyVe("VE002") : "Hủy vé thất bại";
        System.out.println("✓ Hủy vé OK\n");
    }

    public static void testTinhTongDoanhThu() {
        System.out.println("=== TEST: TỔNG DOANH THU ===");
        Ve ve1 = new Ve("VE003", "123456789", "SC003", "C3", 70000);
        ve1.setTrangThai(Ve.TrangThaiVe.DA_THANH_TOAN);
        controller.taoVe(ve1);
        double doanhThu = controller.tinhTongDoanhThu(); // Nên sửa phương thức này nếu đang chờ input
        assert doanhThu >= 0 : "Doanh thu tính sai";
        System.out.println("✓ Doanh thu: " + doanhThu + " VND\n");
    }

    public static void testThongKeVe() {
        System.out.println("=== TEST: THỐNG KÊ VÉ ===");
        assert controller.thongKeVe() : "Thống kê vé thất bại";
        System.out.println("✓ Thống kê vé OK\n");
    }

    public static void testTimVeTheoMa() {
        System.out.println("=== TEST: TÌM VÉ THEO MÃ ===");
        Ve ve = controller.timVeTheoMa("VE001");
        assert ve != null : "Không tìm thấy vé";
        System.out.println("✓ Tìm vé theo mã OK\n");
    }

    public static void testTimVeTheoKhachHang() {
        System.out.println("=== TEST: TÌM VÉ THEO KHÁCH HÀNG ===");
        ArrayList<Ve> list = controller.timVeTheoKhachHang("123456789");
        assert list != null && !list.isEmpty() : "Không có vé cho khách";
        System.out.println("✓ Tìm vé theo khách OK\n");
    }

    public static void testTimVeTheoSuatChieu() {
        System.out.println("=== TEST: TÌM VÉ THEO SUẤT CHIẾU ===");
        ArrayList<Ve> list = controller.timVeTheoSuatChieu("SC001");
        assert list != null && !list.isEmpty() : "Không có vé suất chiếu";
        System.out.println("✓ Tìm vé theo suất chiếu OK\n");
    }
}
