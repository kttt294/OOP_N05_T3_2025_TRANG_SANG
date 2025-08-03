package com.example.servingwebcontent;

import com.example.servingwebcontent.model.Ve;
import com.example.servingwebcontent.model.Ve.TrangThaiVe;
import com.example.servingwebcontent.model.Ve.PhuongThucThanhToan;
import com.example.servingwebcontent.model.DoAn;

import java.time.LocalDateTime;

public class testVe {
    public static void testConstructor() {
        System.out.println("=== TEST CONSTRUCTOR ===");
        Ve ve1 = new Ve("V001", "123456789", "SC001", "GHE01", 100000);
        assert ve1.getMaVe().equals("V001");
        assert ve1.getGiaVe() == 100000;
        assert ve1.getTrangThai() == TrangThaiVe.CHUA_THANH_TOAN;

        DoAn doAn = new DoAn("DA01", "Bắp rang", 50000, 10);
        Ve ve2 = new Ve("V002", "987654321", "SC002", "GHE02", 120000,
                "HD123", doAn, 170000, LocalDateTime.now(), PhuongThucThanhToan.CHUYEN_KHOAN);
        assert ve2.isDaThanhToan();
        assert ve2.getTongTien() == 170000;

        System.out.println("✓ Constructor OK");
    }

    public static void testGetterSetter() {
        System.out.println("=== TEST GETTER/SETTER ===");
        Ve ve = new Ve();
        ve.setMaVe("V999");
        ve.setCCCD("999888777");
        ve.setMaSuatChieu("SC999");
        ve.setMaGhe("GHE99");
        ve.setGiaVe(90000);
        ve.setTrangThai(TrangThaiVe.DA_HUY);

        assert ve.getMaVe().equals("V999");
        assert ve.getTrangThai() == TrangThaiVe.DA_HUY;
        assert ve.getGiaVe() == 90000;

        DoAn da = new DoAn("DA02", "Combo nước", 30000, 5);
        ve.setDoAn(da);
        ve.setTongTien(120000);
        assert ve.getDoAn().getTenDoAn().equals("Combo nước");

        System.out.println("✓ Getter/Setter OK");
    }

    public static void testThanhToanVaHuyVe() {
        System.out.println("=== TEST THANH TOÁN / HỦY VÉ ===");
        Ve ve = new Ve("V100", "KH001", "SC100", "GHE100", 150000);
        DoAn doAn = new DoAn("DA03", "Pepsi", 20000, 3);

        ve.thanhToan(doAn, PhuongThucThanhToan.TIEN_MAT);
        assert ve.isDaThanhToan();
        assert ve.getTongTien() == 170000;
        assert ve.getMaHoaDon() != null;

        ve.huyVe();
        assert ve.isDaHuy();
        assert ve.getTongTien() == 0;
        assert ve.getDoAn() == null;

        System.out.println("✓ Thanh toán và huỷ vé OK");
    }

    public static void testCreateReadUpdateDelete() {
        System.out.println("=== TEST CRUD ===");
        Ve ve = new Ve("V200", "KH200", "SC200", "GHE200", 100000);
        Ve.Create(ve);
        assert Ve.getVeById("V200") != null;

        Ve veUpdate = new Ve("V200", "KH200", "SC200", "GHE201", 120000);
        Ve.Update("V200", veUpdate);
        assert Ve.getVeById("V200").getMaGhe().equals("GHE201");

        Ve.Delete("V200");
        assert Ve.getVeById("V200") == null;

        System.out.println("✓ CRUD OK");
    }

    public static void testTimKiem() {
        System.out.println("=== TEST TÌM KIẾM ===");
        Ve ve1 = new Ve("V301", "KH301", "SC301", "GHE301", 80000);
        Ve ve2 = new Ve("V302", "KH301", "SC302", "GHE302", 80000);
        Ve ve3 = new Ve("V303", "KH302", "SC301", "GHE303", 80000);
        ve1.setTrangThai(TrangThaiVe.CHUA_THANH_TOAN);
        ve2.setTrangThai(TrangThaiVe.DA_THANH_TOAN);
        ve3.setTrangThai(TrangThaiVe.CHUA_THANH_TOAN);

        Ve.Create(ve1);
        Ve.Create(ve2);
        Ve.Create(ve3);

        assert Ve.getVeByKhachHang("KH301").size() == 2;
        assert Ve.getVeBySuatChieu("SC301").size() == 2;
        assert Ve.getVeByTrangThai(TrangThaiVe.CHUA_THANH_TOAN).size() >= 2;

        System.out.println("✓ Tìm kiếm OK");
    }

    public static void testThongKeVaDoanhThu() {
        System.out.println("=== TEST THỐNG KÊ + DOANH THU ===");

        Ve ve = new Ve("V400", "KH400", "SC400", "GHE400", 100000);
        ve.thanhToan(null, PhuongThucThanhToan.CHUYEN_KHOAN);
        ve.setThoiGianThanhToan(LocalDateTime.now().minusHours(1));
        Ve.Create(ve);

        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now().plusDays(1);
        int doanhThu = Ve.tinhDoanhThu(start, end);
        assert doanhThu >= 100000;

        Ve.thongKeVe(); // In kết quả thống kê
        System.out.println("✓ Thống kê và tính doanh thu OK");
    }

    public static void test() {
        testConstructor();
        testGetterSetter();
        testThanhToanVaHuyVe();
        testCreateReadUpdateDelete();
        testTimKiem();
        testThongKeVaDoanhThu();
    }
}
