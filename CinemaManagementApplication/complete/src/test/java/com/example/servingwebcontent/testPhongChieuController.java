package com.example.servingwebcontent;

import com.example.servingwebcontent.controller.PhongChieuController;
import com.example.servingwebcontent.model.PhongChieu;

import java.util.ArrayList;

public class testPhongChieuController {

    private static PhongChieuController controller = new PhongChieuController();

    public static void test() {
        testTaoPhongChieu();
        testCapNhatPhongChieu();
        testXemThongTinPhongChieu();
        testTimPhongChieuTheoMa();
        testTimPhongChieuTheoTen();
        testXemTatCaPhongChieu();
        testThongKePhongChieu();
        testXoaPhongChieu();
    }

    public static void testTaoPhongChieu() {
        System.out.println("=== TEST: TẠO PHÒNG CHIẾU ===");
        PhongChieu phong = new PhongChieu("PH001", "Phòng VIP", 5, 10);
        assert controller.taoPhongChieu(phong) : "Tạo thất bại";
        System.out.println("✓ Tạo phòng chiếu OK\n");
    }

    public static void testCapNhatPhongChieu() {
        System.out.println("=== TEST: CẬP NHẬT PHÒNG CHIẾU ===");
        PhongChieu phongMoi = new PhongChieu("PH001", "Phòng Siêu VIP", 6, 12);
        assert controller.capNhatPhongChieu("PH001", phongMoi) : "Cập nhật thất bại";
        System.out.println("✓ Cập nhật phòng chiếu OK\n");
    }

    public static void testXoaPhongChieu() {
        System.out.println("=== TEST: XOÁ PHÒNG CHIẾU ===");
        assert controller.xoaPhongChieu("PH001") : "Xoá thất bại";
        System.out.println("✓ Xoá phòng chiếu OK\n");
    }

    public static void testXemThongTinPhongChieu() {
        System.out.println("=== TEST: XEM THÔNG TIN PHÒNG CHIẾU ===");
        assert controller.xemThongTinPhongChieu("PH001") : "Xem thất bại";
        System.out.println("✓ Xem thông tin phòng chiếu OK\n");
    }

    public static void testXemTatCaPhongChieu() {
        System.out.println("=== TEST: XEM TẤT CẢ PHÒNG CHIẾU ===");
        assert controller.xemTatCaPhongChieu() : "Xem tất cả thất bại";
        System.out.println("✓ Xem tất cả phòng chiếu OK\n");
    }

    public static void testTimPhongChieuTheoMa() {
        System.out.println("=== TEST: TÌM PHÒNG THEO MÃ ===");
        PhongChieu phong = controller.timPhongChieuTheoMa("PH001");
        assert phong != null : "Không tìm thấy";
        System.out.println("✓ Tìm theo mã phòng OK\n");
    }

    public static void testTimPhongChieuTheoTen() {
        System.out.println("=== TEST: TÌM PHÒNG THEO TÊN ===");
        ArrayList<PhongChieu> ds = controller.timPhongChieuTheoTen("VIP");
        assert ds != null && !ds.isEmpty() : "Không tìm thấy";
        System.out.println("✓ Tìm theo tên phòng OK\n");
    }

    public static void testThongKePhongChieu() {
        System.out.println("=== TEST: THỐNG KÊ PHÒNG CHIẾU ===");
        assert controller.thongKePhongChieu() : "Thống kê thất bại";
        System.out.println("✓ Thống kê phòng chiếu OK\n");
    }
}
