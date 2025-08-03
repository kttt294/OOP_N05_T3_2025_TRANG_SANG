package com.example.servingwebcontent;

import com.example.servingwebcontent.model.KhachHang;
import com.example.servingwebcontent.model.Ve;
import com.example.servingwebcontent.model.Ve.TrangThaiVe;

import java.util.ArrayList;

public class testKhachHang {
    public static void testConstructor() {
        System.out.println("=== TEST CONSTRUCTOR ===");
        KhachHang kh1 = new KhachHang();
        assert kh1.getLichSuDatVe().isEmpty();

        KhachHang kh2 = new KhachHang("001", "Nguyen Van A", 25, "0123456789", "a@email.com");
        assert kh2.getCCCD().equals("001");
        assert kh2.getTuoi() == 25;

        KhachHang kh3 = new KhachHang("002", "Tran Thi B", 30, "0999999999", "b@email.com", "Nu");
        assert kh3.getGioiTinh().equalsIgnoreCase("Nu");

        System.out.println("✓ Constructor OK");
    }

    public static void testGetterSetter() {
        System.out.println("=== TEST GETTER/SETTER ===");
        KhachHang kh = new KhachHang();
        kh.setCCCD("123");
        kh.setTen("Test");
        kh.setTuoi(20);
        kh.setSdt("0900000000");
        kh.setEmail("test@test.com");
        kh.setGioiTinh("Nam");

        assert kh.getCCCD().equals("123");
        assert kh.getTen().equals("Test");
        assert kh.getTuoi() == 20;
        assert kh.getGioiTinh().equals("Nam");

        ArrayList<Ve> veList = new ArrayList<>();
        Ve ve = new Ve("VE001", "123", "SC001", "GHE01", 100000);
        ve.setTrangThai(TrangThaiVe.DA_THANH_TOAN);
        veList.add(ve);
        kh.setLichSuDatVe(veList);

        assert kh.getLichSuDatVe().size() == 1;

        System.out.println("✓ Getter/Setter OK");
    }

    public static void testCreateReadUpdateDelete() {
        System.out.println("=== TEST CRUD ===");
        KhachHang kh = new KhachHang("456", "Le Van C", 40, "0888888888", "c@email.com", "Nam");
        KhachHang.Create(kh);
        assert KhachHang.getKhachHangByCCCD("456") != null;

        KhachHang khUpdate = new KhachHang("456", "Le Van C Updated", 45, "0777777777", "c2@email.com", "Nam");
        KhachHang.Update("456", khUpdate);
        assert KhachHang.getKhachHangByCCCD("456").getTuoi() == 45;

        KhachHang.Delete("456");
        assert KhachHang.getKhachHangByCCCD("456") == null;

        System.out.println("✓ CRUD OK");
    }

    public static void testThemVeVaTinhTien() {
        System.out.println("=== TEST THÊM VÉ + TÍNH TIỀN ===");
        KhachHang kh = new KhachHang("789", "Nguyen Thi D", 32, "0666666666", "d@email.com");
        KhachHang.Create(kh);

        Ve ve1 = new Ve("VE001", "789", "SC001", "GHE01", 90000);
        ve1.setTrangThai(TrangThaiVe.DA_THANH_TOAN);

        Ve ve2 = new Ve("VE002", "789", "SC001", "GHE02", 120000);
        ve2.setTrangThai(TrangThaiVe.CHUA_THANH_TOAN);

        Ve ve3 = new Ve("VE003", "789", "SC002", "GHE03", 70000);
        ve3.setTrangThai(TrangThaiVe.DA_THANH_TOAN);

        kh.themVe(ve1);
        kh.themVe(ve2);
        kh.themVe(ve3);

        assert kh.getLichSuDatVe().size() == 3;
        double tongTien = KhachHang.tinhTongTienKhachHang("789");
        assert tongTien == 160000;

        System.out.println("✓ Thêm vé + Tính tiền OK");
    }

    public static void testTimKiem() {
        System.out.println("=== TEST TÌM KIẾM ===");
        KhachHang kh = new KhachHang("999", "Nguyen Van Tim", 22, "0555555555", "tim@email.com", "Nam");
        KhachHang.Create(kh);

        ArrayList<KhachHang> theoTen = KhachHang.timKiemTheoTen("Tim");
        assert !theoTen.isEmpty();

        ArrayList<KhachHang> theoGT = KhachHang.timKiemTheoGioiTinh("Nam");
        assert !theoGT.isEmpty();

        System.out.println("✓ Tìm kiếm OK");
    }

    public static void testThongKe() {
        System.out.println("=== TEST THỐNG KÊ ===");
        KhachHang xem = new KhachHang("888", "Nguoi Thong Ke", 17, "0111111111", "stat@email.com", "Khac");
        KhachHang.Create(xem);

        KhachHang.xemThongKe(); // In ra màn hình
        System.out.println("✓ Thống kê OK");
    }

    public static void main() {
        testConstructor();
        testGetterSetter();
        testCreateReadUpdateDelete();
        testThemVeVaTinhTien();
        testTimKiem();
        testThongKe();
    }
}
