package com.example.servingwebcontent;

import com.example.servingwebcontent.controller.DanhGiaController;
import com.example.servingwebcontent.model.DanhGia;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class testDanhGiaController {

    public static void test() {
        testTaoDanhGia();
        testCapNhatDanhGia();
        testXoaDanhGia();
        testXemThongTin();
        testXemTatCa();
        testTimKiem();
        testThongKe();
    }

    public static void testTaoDanhGia() {
        System.out.println("=== TEST TẠO ĐÁNH GIÁ ===");

        DanhGia dg1 = new DanhGia("DG001", "123456789012", "P001", 5, "Phim hay, rất cảm động!", LocalDateTime.now());
        boolean result = DanhGiaController.taoDanhGia(dg1);
        assert result : "Tạo đánh giá thất bại";

        DanhGia dg2 = new DanhGia("DG002", "123456789012", "P001", 3, "Bình thường thôi", LocalDateTime.now());
        assert DanhGiaController.taoDanhGia(dg2);

        // Trường hợp lỗi
        try {
            DanhGia dg3 = new DanhGia(null, "123456789012", "P001", 4, "Thiếu mã", LocalDateTime.now());
            DanhGiaController.taoDanhGia(dg3);
            assert false : "Không nên tạo được đánh giá thiếu mã";
        } catch (Exception e) {
            System.out.println("✓ Không tạo được đánh giá thiếu mã (OK)");
        }

        System.out.println("✓ Tạo đánh giá OK\n");
    }

    public static void testCapNhatDanhGia() {
        System.out.println("=== TEST CẬP NHẬT ĐÁNH GIÁ ===");

        DanhGia dgMoi = new DanhGia("DG001", "123456789012", "P001", 4, "Xem lại thấy cũng ổn", LocalDateTime.now());
        boolean result = DanhGiaController.capNhatDanhGia("DG001", dgMoi);
        assert result : "Cập nhật thất bại";

        DanhGia dgSai = new DanhGia("DG999", "000000000000", "P999", 2, "Không tồn tại", LocalDateTime.now());
        assert !DanhGiaController.capNhatDanhGia("DG999", dgSai);

        System.out.println("✓ Cập nhật đánh giá OK\n");
    }

    public static void testXoaDanhGia() {
        System.out.println("=== TEST XOÁ ĐÁNH GIÁ ===");

        assert DanhGiaController.xoaDanhGia("DG002") : "Xoá thất bại";
        assert !DanhGiaController.xoaDanhGia("DG999") : "Không nên xoá được DG999";

        System.out.println("✓ Xoá đánh giá OK\n");
    }

    public static void testXemThongTin() {
        System.out.println("=== TEST XEM THÔNG TIN ĐÁNH GIÁ ===");

        assert DanhGiaController.xemThongTinDanhGia("DG001");
        assert !DanhGiaController.xemThongTinDanhGia("KHONGTONTAI");

        System.out.println("✓ Xem thông tin đánh giá OK\n");
    }

    public static void testXemTatCa() {
        System.out.println("=== TEST XEM TẤT CẢ ĐÁNH GIÁ ===");

        assert DanhGiaController.xemTatCaDanhGia();

        System.out.println("✓ Xem tất cả đánh giá OK\n");
    }

    public static void testTimKiem() {
        System.out.println("=== TEST TÌM KIẾM ĐÁNH GIÁ ===");

        DanhGia dg = DanhGiaController.timDanhGiaTheoMa("DG001");
        assert dg != null : "Không tìm thấy đánh giá DG001";

        ArrayList<DanhGia> list = DanhGiaController.timDanhGiaTheoKhachHang("123456789012");
        assert !list.isEmpty() : "Không tìm thấy đánh giá nào theo CCCD";

        System.out.println("✓ Tìm kiếm đánh giá OK\n");
    }

    public static void testThongKe() {
        System.out.println("=== TEST THỐNG KÊ & TRUNG BÌNH ===");

        double avg = DanhGiaController.tinhDiemTrungBinh();
        assert avg >= 0 && avg <= 5 : "Trung bình không hợp lệ";

        assert DanhGiaController.thongKeDanhGia();

        System.out.println("✓ Thống kê & trung bình đánh giá OK\n");
    }
}
