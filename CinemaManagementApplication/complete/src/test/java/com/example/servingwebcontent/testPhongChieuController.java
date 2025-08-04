package com.example.servingwebcontent;

import com.example.servingwebcontent.controller.PhongChieuController;
import com.example.servingwebcontent.model.PhongChieu;

import java.util.ArrayList;

public class testPhongChieuController {

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
        assert PhongChieuController.taoPhongChieu(phong) : "Tạo thất bại";
        System.out.println("✓ Tạo phòng chiếu OK\n");
    }

    public static void testCapNhatPhongChieu() {
        System.out.println("=== TEST: CẬP NHẬT PHÒNG CHIẾU ===");
        PhongChieu phongMoi = new PhongChieu("PH001", "Phòng Siêu VIP", 6, 12);
        assert PhongChieuController.capNhatPhongChieu("PH001", phongMoi) : "Cập nhật thất bại";
        System.out.println("✓ Cập nhật phòng chiếu OK\n");
    }

    public static void testXoaPhongChieu() {
        System.out.println("=== TEST: XOÁ PHÒNG CHIẾU ===");
        assert PhongChieuController.xoaPhongChieu("PH001") : "Xoá thất bại";
        System.out.println("✓ Xoá phòng chiếu OK\n");
    }

    public static void testXemThongTinPhongChieu() {
        System.out.println("=== TEST: XEM THÔNG TIN PHÒNG CHIẾU ===");
        assert PhongChieuController.xemThongTinPhongChieu("PH001") : "Xem thất bại";
        System.out.println("✓ Xem thông tin phòng chiếu OK\n");
    }

    public static void testXemTatCaPhongChieu() {
        System.out.println("=== TEST: XEM TẤT CẢ PHÒNG CHIẾU ===");
        assert PhongChieuController.xemTatCaPhongChieu() : "Xem tất cả thất bại";
        System.out.println("✓ Xem tất cả phòng chiếu OK\n");
    }

    public static void testTimPhongChieuTheoMa() {
        System.out.println("=== TEST: TÌM PHÒNG THEO MÃ ===");
        PhongChieu phong = PhongChieuController.timPhongChieuTheoMa("PH001");
        assert phong != null : "Không tìm thấy";
        System.out.println("✓ Tìm theo mã phòng OK\n");
    }

    public static void testTimPhongChieuTheoTen() {
        System.out.println("=== TEST: TÌM PHÒNG THEO TÊN ===");
        ArrayList<PhongChieu> ds = PhongChieuController.timPhongChieuTheoTen("VIP");
        assert ds != null && !ds.isEmpty() : "Không tìm thấy";
        System.out.println("✓ Tìm theo tên phòng OK\n");
    }

    public static void testThongKePhongChieu() {
        System.out.println("=== TEST: THỐNG KÊ PHÒNG CHIẾU ===");
        assert PhongChieuController.thongKePhongChieu() : "Thống kê thất bại";
        System.out.println("✓ Thống kê phòng chiếu OK\n");
    }
}
