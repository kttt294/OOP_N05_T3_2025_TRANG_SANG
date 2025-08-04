package com.example.servingwebcontent;

import com.example.servingwebcontent.model.Ghe;
import com.example.servingwebcontent.model.Phim;
import com.example.servingwebcontent.model.SuatChieu;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class testSuatChieu {

    public static void test() {
        // Tạo phim mẫu để tính thời lượng
        Phim.Create(new Phim("PH001", "Avengers", "Hành động", 120, "Anh", 13, "Bom tấn Marvel"));

        testConstructor();
        testGettersandSetters();
        testCRUD();
        testGetSuatChieuById();
        testGetSuatChieuByPhim();
        testGetSuatChieuByPhong();
        testGetSuatChieuIndexById();
        testHienThiThongTin();
    }

    public static void testConstructor() {
        System.out.println("=== TEST CONSTRUCTOR ===");

        List<Ghe> gheList = new ArrayList<>();
        gheList.add(new Ghe("G01", 1, 1, "P001"));

        SuatChieu sc = new SuatChieu("SC001", "PH001", "P001", LocalDateTime.of(2025, 8, 4, 19, 0), gheList);

        assert "SC001".equals(sc.getMaSuatChieu());
        assert "PH001".equals(sc.getMaPhim());
        assert "P001".equals(sc.getMaPhong());
        assert sc.getThoiGianBatDau() != null;
        assert sc.getDanhSachGheTrong().size() == 1;

        System.out.println("✓ Constructor OK");
    }

    public static void testGettersandSetters() {
        System.out.println("=== TEST GETTERS/SETTERS ===");

        SuatChieu sc = new SuatChieu();
        List<Ghe> listGhe = new ArrayList<>();
        listGhe.add(new Ghe("G02", 1, 2, "P002"));

        sc.setMaSuatChieu("SC002");
        sc.setMaPhim("PH001");
        sc.setMaPhong("P002");
        sc.setThoiGianBatDau(LocalDateTime.of(2025, 8, 4, 20, 30));
        sc.setThoiGianKetThuc(LocalDateTime.of(2025, 8, 4, 22, 30));
        sc.setDanhSachGheTrong(listGhe);

        assert sc.getMaSuatChieu().equals("SC002");
        assert sc.getMaPhim().equals("PH001");
        assert sc.getMaPhong().equals("P002");
        assert sc.getDanhSachGheTrong().size() == 1;

        System.out.println("✓ Getters/Setters OK");
    }

    public static void testCRUD() {
        System.out.println("=== TEST CRUD ===");

        List<Ghe> gheList = new ArrayList<>();
        gheList.add(new Ghe("G03", 2, 3, "P003"));
        SuatChieu sc = new SuatChieu("SC003", "PH001", "P003", LocalDateTime.now(), gheList);

        SuatChieu.Create(sc);
        assert SuatChieu.getSuatChieuById("SC003") != null;

        // Update
        List<Ghe> newList = new ArrayList<>();
        newList.add(new Ghe("G04", 3, 4, "P003"));
        SuatChieu updated = new SuatChieu("NEWID", "PH001", "P003", LocalDateTime.now(), newList);
        SuatChieu.Update("SC003", updated);
        SuatChieu scUpdated = SuatChieu.getSuatChieuById("SC003");

        assert scUpdated.getDanhSachGheTrong().size() == 1;

        // Delete
        SuatChieu.Delete("SC003");
        assert SuatChieu.getSuatChieuById("SC003") == null;

        System.out.println("✓ CRUD OK");
    }

    public static void testGetSuatChieuById() {
        System.out.println("=== TEST getSuatChieuById ===");

        List<Ghe> list = new ArrayList<>();
        list.add(new Ghe("G05", 1, 1, "P004"));
        SuatChieu.Create(new SuatChieu("SC004", "PH001", "P004", LocalDateTime.now(), list));

        SuatChieu result = SuatChieu.getSuatChieuById("SC004");
        assert result != null;
        assert "P004".equals(result.getMaPhong());

        System.out.println("✓ getSuatChieuById OK");
    }

    public static void testGetSuatChieuByPhim() {
        System.out.println("=== TEST getSuatChieuByPhim ===");

        SuatChieu.Create(new SuatChieu("SC005", "PH001", "P005", LocalDateTime.now(), new ArrayList<>()));
        SuatChieu.Create(new SuatChieu("SC006", "PH001", "P006", LocalDateTime.now(), new ArrayList<>()));

        List<SuatChieu> list = SuatChieu.getSuatChieuByPhim("PH001");
        assert list.size() >= 2;

        System.out.println("✓ getSuatChieuByPhim OK");
    }

    public static void testGetSuatChieuByPhong() {
        System.out.println("=== TEST getSuatChieuByPhong ===");

        SuatChieu.Create(new SuatChieu("SC007", "PH001", "P007", LocalDateTime.now(), new ArrayList<>()));
        List<SuatChieu> result = SuatChieu.getSuatChieuByPhong("P007");

        assert result.size() >= 1;
        System.out.println("✓ getSuatChieuByPhong OK");
    }

    public static void testGetSuatChieuIndexById() {
        System.out.println("=== TEST getSuatChieuIndexById ===");

        int index = getSuatChieuIndexByIdProxy("SC007");
        assert index >= 0;
        System.out.println("✓ getSuatChieuIndexById OK – Index: " + index);
    }

    private static int getSuatChieuIndexByIdProxy(String maSuatChieu) {
        try {
            java.lang.reflect.Method method = SuatChieu.class.getDeclaredMethod("getSuatChieuIndexById", String.class);
            method.setAccessible(true);
            return (int) method.invoke(null, maSuatChieu);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void testHienThiThongTin() {
        System.out.println("=== TEST hienThiThongTin ===");

        List<Ghe> gheList = new ArrayList<>();
        gheList.add(new Ghe("G06", 1, 1, "P008"));

        SuatChieu sc = new SuatChieu("SC008", "PH001", "P008", LocalDateTime.of(2025, 8, 4, 18, 0), gheList);
        sc.setThoiGianKetThuc(sc.tinhThoiGianKetThuc());
        sc.hienThiThongTin();  // kiểm tra bằng mắt

        System.out.println("✓ hienThiThongTin OK (xem console)");
    }
}
