package com.example.servingwebcontent;

import com.example.servingwebcontent.model.Ve;
import com.example.servingwebcontent.model.Ve.TrangThaiVe;

import java.util.ArrayList;

public class testVe {

    public static void testConstructor() {
        System.out.println("=== TEST CONSTRUCTOR ===");
        Ve ve1 = new Ve();
        assert ve1.getMaVe() == null;
        assert ve1.getCCCD() == null;
        assert ve1.getMaSuatChieu() == null;
        assert ve1.getMaGhe() == null;
        assert ve1.getGiaVe() == 0;
        assert ve1.getTrangThai() == null;
        System.out.println("✓ Constructor rỗng OK");

        Ve ve2 = new Ve("VE001", "123456789", "SC001", "GHE01", 90000);
        assert "VE001".equals(ve2.getMaVe());
        assert "123456789".equals(ve2.getCCCD());
        assert "SC001".equals(ve2.getMaSuatChieu());
        assert "GHE01".equals(ve2.getMaGhe());
        assert ve2.getGiaVe() == 90000;
        assert ve2.getTrangThai() == TrangThaiVe.CHUA_THANH_TOAN;
        System.out.println("✓ Constructor với tham số OK");
    }

    public static void testGettersandSetters() {
        System.out.println("=== TEST GETTERS & SETTERS ===");
        Ve ve = new Ve();
        ve.setMaVe("VE002");
        ve.setCCCD("987654321");
        ve.setMaSuatChieu("SC002");
        ve.setMaGhe("GHE02");
        ve.setGiaVe(100000);
        ve.setTrangThai(TrangThaiVe.DA_THANH_TOAN);

        assert "VE002".equals(ve.getMaVe());
        assert "987654321".equals(ve.getCCCD());
        assert "SC002".equals(ve.getMaSuatChieu());
        assert "GHE02".equals(ve.getMaGhe());
        assert ve.getGiaVe() == 100000;
        assert ve.getTrangThai() == TrangThaiVe.DA_THANH_TOAN;
        System.out.println("✓ Getters & Setters OK");
    }

    public static void testCRUD() {
        System.out.println("=== TEST CRUD ===");
        Ve ve = new Ve("VE003", "111222333", "SC003", "GHE03", 75000);
        Ve.Create(ve);
        ArrayList<Ve> list = Ve.Read();
        assert list.stream().anyMatch(v -> "VE003".equals(v.getMaVe()));
        System.out.println("✓ Create OK");

        Ve veMoi = new Ve("NEW", "000111222", "SC_NEW", "GHE_NEW", 85000);
        veMoi.setTrangThai(TrangThaiVe.DA_HUY);
        Ve.Update("VE003", veMoi);
        Ve updated = Ve.getVeById("VE003");
        assert updated != null;
        assert "SC_NEW".equals(updated.getMaSuatChieu());
        assert updated.getTrangThai() == TrangThaiVe.DA_HUY;
        System.out.println("✓ Update OK");

        Ve.Delete("VE003");
        assert Ve.getVeById("VE003") == null;
        System.out.println("✓ Delete OK");
    }

    public static void testGetVeById() {
        System.out.println("=== TEST GET VE BY ID ===");
        Ve ve = new Ve("VE004", "321654987", "SC004", "GHE04", 95000);
        Ve.Create(ve);
        Ve found = Ve.getVeById("VE004");
        assert found != null;
        assert "SC004".equals(found.getMaSuatChieu());
        System.out.println("✓ GetVeById OK");
    }

    public static void testGetVeIndexById() {
        System.out.println("=== TEST GET VE INDEX BY ID ===");
        Ve ve = new Ve("VE005", "111999333", "SC005", "GHE05", 70000);
        Ve.Create(ve);
        int index = getVeIndexById("VE005");
        assert index != -1;
        assert Ve.getDanhSachVe().get(index).getMaVe().equals("VE005");
        System.out.println("✓ GetVeIndexById OK");
    }

    public static void testHienThiThongTin() {
        System.out.println("=== TEST HIỂN THỊ THÔNG TIN ===");
        Ve ve = new Ve("VE006", "123789456", "SC006", "GHE06", 120000);
        ve.setTrangThai(TrangThaiVe.DA_THANH_TOAN);
        ve.hienThiThongTin();
        System.out.println("✓ Hiển thị thông tin OK");
    }

    private static int getVeIndexById(String maVe) {
        // Giả lập gọi hàm private bằng cách sao chép logic từ Ve.java
        ArrayList<Ve> list = Ve.getDanhSachVe();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMaVe().equalsIgnoreCase(maVe)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        testConstructor();
        testGettersandSetters();
        testCRUD();
        testGetVeById();
        testGetVeIndexById();
        testHienThiThongTin();
    }
}
