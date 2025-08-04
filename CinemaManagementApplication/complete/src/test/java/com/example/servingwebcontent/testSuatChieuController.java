package com.example.servingwebcontent;

import com.example.servingwebcontent.controller.SuatChieuController;
import com.example.servingwebcontent.model.SuatChieu;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class testSuatChieuController {

    public static void test() {
        testTaoSuatChieu();
        testCapNhatSuatChieu();
        testXemThongTinSuatChieu();
        testTimSuatChieuTheoMa();
        testTimSuatChieuTheoPhim();
        testTimSuatChieuTheoPhong();
        testXemTatCaSuatChieu();
        testHienThiSuatChieuTrongNgay();
        testXoaSuatChieu();
    }

    public static void testHienThiSuatChieuTrongNgay() {
        System.out.println("=== TEST: HIỂN THỊ SUẤT CHIẾU TRONG NGÀY ===");
        List<SuatChieu> danhSach = new ArrayList<>();
        LocalDateTime start = LocalDateTime.of(2025, 8, 4, 10, 0);
        danhSach.add(new SuatChieu("SC001", "PHIM001", "PHONG01", start));
        assert SuatChieuController.hienThiSuatChieuTrongNgay(danhSach) : "Hiển thị thất bại";
        System.out.println("✓ Hiển thị suất chiếu trong ngày OK\n");
    }

    public static void testTaoSuatChieu() {
        System.out.println("=== TEST: TẠO SUẤT CHIẾU ===");
        LocalDateTime start = LocalDateTime.of(2025, 8, 5, 14, 0);
        SuatChieu sc = new SuatChieu("SC002", "PHIM001", "PHONG01", start);
        assert SuatChieuController.taoSuatChieu(sc) : "Tạo suất chiếu thất bại";
        System.out.println("✓ Tạo suất chiếu OK\n");
    }

    public static void testCapNhatSuatChieu() {
        System.out.println("=== TEST: CẬP NHẬT SUẤT CHIẾU ===");
        LocalDateTime newStart = LocalDateTime.of(2025, 8, 5, 15, 0);
        SuatChieu scMoi = new SuatChieu("SC002", "PHIM001", "PHONG01", newStart);
        assert SuatChieuController.capNhatSuatChieu("SC002", scMoi) : "Cập nhật thất bại";
        System.out.println("✓ Cập nhật suất chiếu OK\n");
    }

    public static void testXoaSuatChieu() {
        System.out.println("=== TEST: XOÁ SUẤT CHIẾU ===");
        assert SuatChieuController.xoaSuatChieu("SC002") : "Xoá suất chiếu thất bại";
        System.out.println("✓ Xoá suất chiếu OK\n");
    }

    public static void testXemThongTinSuatChieu() {
        System.out.println("=== TEST: XEM THÔNG TIN SUẤT CHIẾU ===");
        assert SuatChieuController.xemThongTinSuatChieu("SC001") : "Xem suất chiếu thất bại";
        System.out.println("✓ Xem thông tin suất chiếu OK\n");
    }

    public static void testXemTatCaSuatChieu() {
        System.out.println("=== TEST: XEM TẤT CẢ SUẤT CHIẾU ===");
        assert SuatChieuController.xemTatCaSuatChieu() : "Xem tất cả suất chiếu thất bại";
        System.out.println("✓ Xem tất cả suất chiếu OK\n");
    }

    public static void testTimSuatChieuTheoMa() {
        System.out.println("=== TEST: TÌM THEO MÃ SUẤT CHIẾU ===");
        SuatChieu sc = SuatChieuController.timSuatChieuTheoMa("SC001");
        assert sc != null : "Không tìm thấy suất chiếu";
        System.out.println("✓ Tìm suất chiếu theo mã OK\n");
    }

    public static void testTimSuatChieuTheoPhim() {
        System.out.println("=== TEST: TÌM THEO MÃ PHIM ===");
        ArrayList<SuatChieu> list = SuatChieuController.timSuatChieuTheoPhim("PHIM001");
        assert list != null : "Không tìm thấy theo phim";
        System.out.println("✓ Tìm suất chiếu theo phim OK\n");
    }

    public static void testTimSuatChieuTheoPhong() {
        System.out.println("=== TEST: TÌM THEO MÃ PHÒNG ===");
        ArrayList<SuatChieu> list = SuatChieuController.timSuatChieuTheoPhong("PHONG01");
        assert list != null : "Không tìm thấy theo phòng";
        System.out.println("✓ Tìm suất chiếu theo phòng OK\n");
    }
}
