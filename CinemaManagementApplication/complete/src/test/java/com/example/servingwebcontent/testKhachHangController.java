package com.example.servingwebcontent;

import com.example.servingwebcontent.controller.KhachHangController;
import com.example.servingwebcontent.model.KhachHang;
import com.example.servingwebcontent.model.Ve;
import com.example.servingwebcontent.model.DanhGia;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class testKhachHangController {

    private static KhachHangController controller = new KhachHangController();
    
    public static void testTaoKhachHang() {
        System.out.println("=== TEST: TẠO KHÁCH HÀNG ===");
        KhachHang kh = new KhachHang("123456789", "Nguyen Van A", 25, "0123456789", "a@gmail.com", "Nam");
        assert controller.taoKhachHang(kh) : "Tạo thất bại";
        System.out.println("✓ Tạo khách hàng OK\n");
    }

    public static void testCapNhatThongTin() {
        System.out.println("=== TEST: CẬP NHẬT THÔNG TIN ===");
        KhachHang khMoi = new KhachHang("123456789", "Nguyen Van B", 30, "0999888777", "b@gmail.com", "Nam");
        assert controller.capNhatThongTin("123456789", khMoi) : "Cập nhật thất bại";
        System.out.println("✓ Cập nhật OK\n");
    }

    public static void testXoaKhachHang() {
        System.out.println("=== TEST: XOÁ KHÁCH HÀNG ===");
        assert controller.xoaKhachHang("123456789") : "Xoá thất bại";
        System.out.println("✓ Xoá khách hàng OK\n");
    }

    public static void testXemThongTin() {
        System.out.println("=== TEST: XEM THÔNG TIN ===");
        assert controller.xemThongTin("123456789") : "Xem thất bại";
        System.out.println("✓ Xem thông tin OK\n");
    }

    public static void testXemTatCaKhachHang() {
        System.out.println("=== TEST: XEM TẤT CẢ KHÁCH HÀNG ===");
        assert controller.xemTatCaKhachHang() : "Xem tất cả thất bại";
        System.out.println("✓ Xem tất cả OK\n");
    }

    public static void testTimKhachHangTheoCCCD() {
        System.out.println("=== TEST: TÌM THEO CCCD ===");
        KhachHang kh = controller.timKhachHangTheoCCCD("123456789");
        assert kh != null : "Không tìm thấy khách";
        System.out.println("✓ Tìm theo CCCD OK\n");
    }

    public static void testTimKhachHangTheoTen() {
        System.out.println("=== TEST: TÌM THEO TÊN ===");
        ArrayList<KhachHang> ds = controller.timKhachHangTheoTen("Nguyen");
        assert ds != null : "Không tìm thấy";
        System.out.println("✓ Tìm theo tên OK\n");
    }

    public static void testTimKhachHangTheoGioiTinh() {
        System.out.println("=== TEST: TÌM THEO GIỚI TÍNH ===");
        ArrayList<KhachHang> ds = controller.timKhachHangTheoGioiTinh("Nam");
        assert ds != null : "Không tìm thấy";
        System.out.println("✓ Tìm theo giới tính OK\n");
    }

    public static void testXemThongKeKhachHang() {
        System.out.println("=== TEST: THỐNG KÊ KHÁCH HÀNG ===");
        assert controller.xemThongKeKhachHang() : "Thống kê thất bại";
        System.out.println("✓ Thống kê OK\n");
    }

    public static void testTinhTongTienKhachHang() {
        System.out.println("=== TEST: TỔNG TIỀN KHÁCH HÀNG ===");
        double tongTien = controller.tinhTongTienKhachHang("123456789");
        assert tongTien >= 0 : "Tính sai";
        System.out.println("✓ Tổng tiền: " + tongTien + " VNĐ\n");
    }

    public static void testXemLichSuDatVe() {
        System.out.println("=== TEST: XEM LỊCH SỬ ĐẶT VÉ ===");
        assert controller.xemLichSuDatVe("123456789") : "Không xem được";
        System.out.println("✓ Xem lịch sử vé OK\n");
    }

    public static void testThemVeChoKhachHang() {
        System.out.println("=== TEST: THÊM VÉ CHO KHÁCH HÀNG ===");
        Ve ve = new Ve("VE001", "001306063562", "SC02", "Ghe01", 45000);
        assert controller.themVeChoKhachHang("123456789", ve) : "Thêm vé thất bại";
        System.out.println("✓ Thêm vé OK\n");
    }

    public static void testBaoCaoKhachHangVIP() {
        System.out.println("=== TEST: BÁO CÁO KHÁCH VIP ===");
        assert controller.baoCaoKhachHangVIP() : "Báo cáo VIP lỗi";
        System.out.println("✓ Báo cáo VIP OK\n");
    }

    public static void testBaoCaoKhachHangMoi() {
        System.out.println("=== TEST: BÁO CÁO KHÁCH MỚI ===");
        assert controller.baoCaoKhachHangMoi() : "Báo cáo lỗi";
        System.out.println("✓ Báo cáo khách mới OK\n");
    }

    public static void testGuiDanhGia() {
        System.out.println("=== TEST: GỬI ĐÁNH GIÁ ===");
        DanhGia dg = new DanhGia("DG001", "123456789", "PHIM001", 4, "Hay!", LocalDateTime.now());
        assert controller.guiDanhGia(dg) : "Gửi đánh giá lỗi";
        System.out.println("✓ Gửi đánh giá OK\n");
    }

    public static void main(String[] args) {
        testTaoKhachHang();
        testCapNhatThongTin();
        testXoaKhachHang();
        testXemThongTin();
        testXemTatCaKhachHang();
        testTimKhachHangTheoCCCD();
        testTimKhachHangTheoTen();
        testTimKhachHangTheoGioiTinh();
        testXemThongKeKhachHang();
        testTinhTongTienKhachHang();
        testThemVeChoKhachHang();
        testXemLichSuDatVe();
        testBaoCaoKhachHangVIP();
        testBaoCaoKhachHangMoi();
        testGuiDanhGia();
    }
}
