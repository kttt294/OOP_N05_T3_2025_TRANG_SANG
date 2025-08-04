package com.example.servingwebcontent;

import java.time.LocalDateTime;
import com.example.servingwebcontent.model.DanhGia;

public class testDanhGia {

    public static void test() {
        testConstructor();
        testGettersSetters();
        testHienThiThongTin();
        testCRUD();
        testGetByMaPhimAndCCCD();
        testTinhDiemTrungBinh();
        testThongKe();
    }

    public static void testConstructor() {
        System.out.println("=== TEST CONSTRUCTOR ===");
        DanhGia dg1 = new DanhGia();
        assert dg1.getMaDanhGia() == null;
        assert dg1.getCCCD() == null;
        System.out.println("✓ Constructor rỗng OK");

        DanhGia dg2 = new DanhGia("DG001", "123456789", "PHIM001", 5, "Phim rất hay!", LocalDateTime.now());
        assert "DG001".equals(dg2.getMaDanhGia());
        assert "123456789".equals(dg2.getCCCD());
        assert "PHIM001".equals(dg2.getMaPhim());
        assert dg2.getSoSao() == 5;
        assert "Phim rất hay!".equals(dg2.getNoiDung());
        System.out.println("✓ Constructor với tham số OK");
    }

    public static void testGettersSetters() {
        System.out.println("\n=== TEST GETTERS & SETTERS ===");
        DanhGia dg = new DanhGia();
        dg.setMaDanhGia("DG002");
        assert "DG002".equals(dg.getMaDanhGia());
        dg.setCCCD("987654321");
        assert "987654321".equals(dg.getCCCD());
        dg.setMaPhim("PHIM002");
        assert "PHIM002".equals(dg.getMaPhim());
        dg.setSoSao(4);
        assert dg.getSoSao() == 4;
        dg.setNoiDung("Phim tạm được");
        assert "Phim tạm được".equals(dg.getNoiDung());
        dg.setThoiGian(LocalDateTime.now());
        assert dg.getThoiGian() != null;
        System.out.println("✓ Tất cả getters/setters OK");
    }

    public static void testHienThiThongTin() {
        System.out.println("\n=== TEST HIEN THI THONG TIN ===");
        DanhGia dg = new DanhGia("DG003", "111222333", "PHIM003", 3, "Phim bình thường", LocalDateTime.now());
        dg.hienThiThongTin();
        System.out.println("✓ Hiển thị thông tin OK");
    }

    public static void testCRUD() {
        System.out.println("\n=== TEST CRUD INTEGRATION ===");
        DanhGia dg = new DanhGia("INT001", "123456789", "PHIM001", 5, "Phim rất hay!", LocalDateTime.now());
        DanhGia.Create(dg);
        DanhGia dgRead = DanhGia.getDanhGiaByMa("INT001");
        assert dgRead != null;
        assert "123456789".equals(dgRead.getCCCD());
        dg.setSoSao(4);
        dg.setNoiDung("Phim hay nhưng chưa xuất sắc");
        DanhGia.Update("INT001", dg);
        DanhGia dgUpdated = DanhGia.getDanhGiaByMa("INT001");
        assert dgUpdated.getSoSao() == 4;
        DanhGia.Delete("INT001");
        DanhGia dgDeleted = DanhGia.getDanhGiaByMa("INT001");
        assert dgDeleted == null;
        System.out.println("✓ CRUD Integration OK");
    }

    public static void testGetByMaPhimAndCCCD() {
        System.out.println("\n=== TEST GET BY MA PHIM & CCCD ===");
        DanhGia dg1 = new DanhGia("TEST001", "CCCD001", "P001", 5, "Good", LocalDateTime.now());
        DanhGia dg2 = new DanhGia("TEST002", "CCCD001", "P002", 4, "Good", LocalDateTime.now());
        DanhGia.Create(dg1);
        DanhGia.Create(dg2);
        assert DanhGia.getDanhGiaByMaPhim("P001").size() >= 1;
        assert DanhGia.getDanhGiaByCCCD("CCCD001").size() >= 2;
        System.out.println("✓ Lấy theo mã phim và CCCD OK");
    }

    public static void testTinhDiemTrungBinh() {
        System.out.println("\n=== TEST TINH DIEM TRUNG BINH ===");
        String maPhim = "PHIMAVG";
        DanhGia dg1 = new DanhGia("AVG1", "KH1", maPhim, 4, "Hay", LocalDateTime.now());
        DanhGia dg2 = new DanhGia("AVG2", "KH2", maPhim, 2, "Binh thuong", LocalDateTime.now());
        DanhGia.Create(dg1);
        DanhGia.Create(dg2);
        double avg = DanhGia.tinhDiemTrungBinhPhim(maPhim);
        assert avg == 3.0 : "Trung bình phải là 3.0";
        System.out.println("✓ Tính trung bình OK");
    }

    public static void testThongKe() {
        System.out.println("\n=== TEST THONG KE ===");
        DanhGia.thongKeDanhGia();
        System.out.println("✓ Thống kê OK (Xem output thủ công)");
    }
}
