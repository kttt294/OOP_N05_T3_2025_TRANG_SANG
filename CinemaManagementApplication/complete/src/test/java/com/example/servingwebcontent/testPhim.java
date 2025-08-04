package com.example.servingwebcontent;

import com.example.servingwebcontent.model.Phim;

import java.util.ArrayList;

public class testPhim {

    public static void test() {
        testConstructor();
        testGettersAndSetters();
        testCRUD();
        testGetPhimById();
        testTimKiemTheoTen();
        testTimKiemTheoTheLoai();
        testHienThiThongTin();
        testThongKePhim();
    }

    public static void testConstructor() {
        System.out.println("=== TEST CONSTRUCTOR ===");

        Phim p1 = new Phim();
        assert p1.getMaPhim() == null;

        Phim p2 = new Phim("P001", "Avengers");
        assert "P001".equals(p2.getMaPhim());
        assert "Avengers".equals(p2.getTenPhim());

        Phim p3 = new Phim("P002", "Inception", "Khoa học viễn tưởng", 148, "Tiếng Anh", 16, "Giấc mơ lồng giấc mơ");
        assert "Inception".equals(p3.getTenPhim());
        assert p3.getThoiLuong() == 148;
        assert "Tiếng Anh".equals(p3.getNgonNgu());

        System.out.println("✓ Constructor OK");
    }

    public static void testGettersAndSetters() {
        System.out.println("=== TEST GETTERS/SETTERS ===");

        Phim p = new Phim();
        p.setMaPhim("P003");
        p.setTenPhim("Interstellar");
        p.setTheLoai("Khoa học");
        p.setThoiLuong(169);
        p.setNgonNgu("Anh");
        p.setGioiHanTuoi(13);
        p.setMoTa("Du hành không gian");

        assert "P003".equals(p.getMaPhim());
        assert "Interstellar".equals(p.getTenPhim());
        assert p.getThoiLuong() == 169;
        assert p.getGioiHanTuoi() == 13;

        System.out.println("✓ Getters/Setters OK");
    }

    public static void testCRUD() {
        System.out.println("=== TEST CRUD ===");

        Phim phim = new Phim("P004", "Up", "Hoạt hình", 96, "Tiếng Anh", 0, "Chuyến phiêu lưu của ông lão và bé trai");
        Phim.Create(phim);
        assert Phim.getPhimById("P004") != null;

        Phim updated = new Phim("NEW", "Up 2", "Hoạt hình", 100, "Tiếng Anh", 0, "Tiếp tục hành trình");
        Phim.Update("P004", updated);
        assert "Up 2".equals(Phim.getPhimById("P004").getTenPhim());

        Phim.Delete("P004");
        assert Phim.getPhimById("P004") == null;

        System.out.println("✓ CRUD OK");
    }

    public static void testGetPhimById() {
        System.out.println("=== TEST getPhimById ===");

        Phim.Create(new Phim("P005", "Iron Man", "Hành động", 120, "Anh", 13, ""));
        Phim result = Phim.getPhimById("P005");

        assert result != null;
        assert "Iron Man".equals(result.getTenPhim());

        System.out.println("✓ getPhimById OK");
    }
        
    public static void testGetPhimIndexById() {
        System.out.println("=== TEST getPhimIndexById ===");

        Phim.Create(new Phim("P014", "The Matrix", "Hành động", 136, "Anh", 18, ""));
        int index = getPhimIndexByIdProxy("P014");

        assert index >= 0 : "✓ Phải tìm thấy index lớn hơn hoặc bằng 0";
        System.out.println("✓ getPhimIndexById OK. Index: " + index);
    }

    public static void testTimKiemTheoTen() {
        System.out.println("=== TEST timKiemTheoTen ===");

        Phim.Create(new Phim("P006", "Iron Man 2", "Hành động", 124, "Anh", 13, ""));
        Phim.Create(new Phim("P007", "Iron Heart", "Hành động", 115, "Anh", 13, ""));

        ArrayList<Phim> result = Phim.timKiemTheoTen("iron");
        assert result.size() >= 2;

        System.out.println("✓ timKiemTheoTen OK");
    }

    public static void testTimKiemTheoTheLoai() {
        System.out.println("=== TEST timKiemTheoTheLoai ===");

        Phim.Create(new Phim("P008", "Love Actually", "Tình cảm", 135, "Anh", 16, ""));
        Phim.Create(new Phim("P009", "Notting Hill", "Tình cảm", 125, "Anh", 13, ""));

        ArrayList<Phim> result = Phim.timKiemTheoTheLoai("tình cảm");
        assert result.size() >= 2;

        System.out.println("✓ timKiemTheoTheLoai OK");
    }

    public static void testHienThiThongTin() {
        System.out.println("=== TEST hienThiThongTin ===");

        Phim p = new Phim("P010", "Your Name", "Hoạt hình", 106, "Nhật", 0, "Hai người lạ hoán đổi thân xác");
        p.hienThiThongTin();

        System.out.println("✓ hienThiThongTin OK (kiểm tra bằng console)");
    }

    public static void testThongKePhim() {
        System.out.println("=== TEST thongKePhim ===");

        Phim.Create(new Phim("P011", "Minions", "Hoạt hình", 90, "Anh", 0, ""));
        Phim.Create(new Phim("P012", "Kung Fu Panda", "Hoạt hình", 95, "Anh", 0, ""));
        Phim.Create(new Phim("P013", "Parasite", "Kịch tính", 132, "Hàn", 18, ""));

        Phim.thongKePhim(); 

        System.out.println("✓ thongKePhim OK (kiểm tra bằng console)");
    }
}
