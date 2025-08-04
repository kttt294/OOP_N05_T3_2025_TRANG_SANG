package com.example.servingwebcontent;

import com.example.servingwebcontent.controller.DoAnController;
import com.example.servingwebcontent.model.DoAn;

import java.util.ArrayList;

public class testDoAnController {

    public static void testTaoDoAn() {
        System.out.println("=== TEST TẠO ĐỒ ĂN ===");
        DoAn doAn = new DoAn("DA001", "Bắp rang", 45000, 10);
        boolean result = DoAnController.taoDoAn(doAn);
        assert result : "Tạo đồ ăn thất bại!";
        assert DoAn.getDoAnByMa("DA001") != null : "Không tìm thấy đồ ăn sau khi tạo!";
        System.out.println("✓ Tạo đồ ăn OK");
    }

    public static void testCapNhatDoAn() {
        System.out.println("=== TEST CẬP NHẬT ĐỒ ĂN ===");
        DoAn doAnMoi = new DoAn("DA001", "Bắp caramel", 50000, 8);
        boolean result = DoAnController.capNhatDoAn("DA001", doAnMoi);
        assert result : "Cập nhật đồ ăn thất bại!";
        assert DoAn.getDoAnByMa("DA001").getTenDoAn().equals("Bắp caramel") : "Tên đồ ăn chưa cập nhật!";
        System.out.println("✓ Cập nhật đồ ăn OK");
    }

    public static void testXoaDoAn() {
        System.out.println("=== TEST XÓA ĐỒ ĂN ===");
        boolean result = DoAnController.xoaDoAn("DA001");
        assert result : "Xóa đồ ăn thất bại!";
        assert DoAn.getDoAnByMa("DA001") == null : "Đồ ăn vẫn còn tồn tại sau khi xóa!";
        System.out.println("✓ Xóa đồ ăn OK");
    }

    public static void testXemThongTinDoAn() {
        System.out.println("=== TEST XEM THÔNG TIN ĐỒ ĂN ===");
        DoAn doAn = new DoAn("DA002", "Khoai tây chiên", 30000, 20);
        DoAn.Create(doAn);
        boolean result = DoAnController.xemThongTinDoAn("DA002");
        assert result : "Không thể xem thông tin đồ ăn!";
        System.out.println("✓ Xem thông tin đồ ăn OK");
    }

    public static void testXemTatCaDoAn() {
        System.out.println("=== TEST XEM TẤT CẢ ĐỒ ĂN ===");
        boolean result = DoAnController.xemTatCaDoAn();
        assert result : "Không thể xem danh sách đồ ăn!";
        System.out.println("✓ Xem tất cả đồ ăn OK");
    }

    public static void testTimDoAnTheoMa() {
        System.out.println("=== TEST TÌM ĐỒ ĂN THEO MÃ ===");
        DoAn doAn = DoAnController.timDoAnTheoMa("DA002");
        assert doAn != null && doAn.getTenDoAn().equals("Khoai tây chiên") : "Không tìm thấy đồ ăn theo mã!";
        System.out.println("✓ Tìm đồ ăn theo mã OK");
    }

    public static void testTimDoAnTheoTen() {
        System.out.println("=== TEST TÌM ĐỒ ĂN THEO TÊN ===");
        ArrayList<DoAn> results = DoAnController.timDoAnTheoTen("khoai");
        assert results.size() > 0 : "Không tìm thấy đồ ăn theo tên!";
        System.out.println("✓ Tìm đồ ăn theo tên OK");
    }

    public static void testCapNhatSoLuong() {
        System.out.println("=== TEST CẬP NHẬT SỐ LƯỢNG ===");
        boolean result = DoAnController.capNhatSoLuong("DA002", 50);
        assert result : "Cập nhật số lượng thất bại!";
        assert DoAn.getDoAnByMa("DA002").getSoLuong() == 50 : "Số lượng chưa được cập nhật!";
        System.out.println("✓ Cập nhật số lượng OK");
    }

    public static void testThongKeDoAn() {
        System.out.println("=== TEST THỐNG KÊ ĐỒ ĂN ===");
        boolean result = DoAnController.thongKeDoAn();
        assert result : "Thống kê đồ ăn thất bại!";
        System.out.println("✓ Thống kê đồ ăn OK");
    }

    public static void main(String[] args) {
        testTaoDoAn();
        testCapNhatDoAn();
        testXemThongTinDoAn();
        testXemTatCaDoAn();
        testTimDoAnTheoMa();
        testTimDoAnTheoTen();
        testCapNhatSoLuong();
        testThongKeDoAn();
        testXoaDoAn();
    }
}
