package com.example.servingwebcontent.test;

import com.example.servingwebcontent.model.Ghe;
import com.example.servingwebcontent.model.Ghe.LoaiGhe;
import com.example.servingwebcontent.model.Ghe.TrangThaiGhe;
import com.example.servingwebcontent.model.Ghe.TrangThaiGiu;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class testGhe {
    public static void testConstructor() {
        System.out.println("=== TEST CONSTRUCTOR ===");
        Ghe g1 = new Ghe();
        assert g1.getMaGhe() == null : "Constructor rỗng không đúng";

        Ghe g2 = new Ghe("GHE001", 1, 2, LoaiGhe.VIP, "PHONG001");
        assert "GHE001".equals(g2.getMaGhe());
        assert g2.getHang() == 1;
        assert g2.getCot() == 2;
        assert g2.getLoaiGheMacDinh() == LoaiGhe.VIP;
        assert g2.getTrangThai() == TrangThaiGhe.TRONG;

        Ghe g3 = new Ghe("GHE002", 3, 4, LoaiGhe.COUPLE, "PHONG002",
                "SC001", 120000, TrangThaiGhe.DANG_GIU, "VIP", "Ghế đôi đẹp");
        assert "SC001".equals(g3.getMaSuatChieu());
        assert g3.getGiaGhe() == 120000;
        assert g3.getLoaiGheHienTai().equals("VIP");

        Ghe g4 = new Ghe("GHE003", 5, 6, LoaiGhe.THUONG, "PHONG003",
                "SC002", 90000, TrangThaiGhe.DA_DAT, "THUONG", "Mô tả",
                "HOLD001", "123456789", LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), TrangThaiGiu.DANG_GIU);
        assert g4.getMaHold().equals("HOLD001");

        System.out.println("✓ Constructor OK");
    }

    public static void testGetterSetter() {
        System.out.println("=== TEST GETTER/SETTER ===");
        Ghe ghe = new Ghe();
        ghe.setMaGhe("GH999");
        ghe.setHang(5);
        ghe.setCot(7);
        ghe.setLoaiGheMacDinh(LoaiGhe.VIP);
        ghe.setMaPhong("PHONG99");
        ghe.setMaSuatChieu("SC99");
        ghe.setGiaGhe(100000);
        ghe.setTrangThai(TrangThaiGhe.KHOA);
        ghe.setLoaiGheHienTai("VIP");
        ghe.setMoTa("Góc đẹp");

        assert ghe.getMaGhe().equals("GH999");
        assert ghe.getHang() == 5;
        assert ghe.getCot() == 7;
        assert ghe.getLoaiGheMacDinh() == LoaiGhe.VIP;
        assert ghe.getMaPhong().equals("PHONG99");
        assert ghe.getGiaGhe() == 100000;
        assert ghe.getTrangThai() == TrangThaiGhe.KHOA;
        assert ghe.getLoaiGheHienTai().equals("VIP");

        ghe.setCCCD("012345678");
        ghe.setMaHold("HOLD999");
        ghe.setTrangThaiGiu(TrangThaiGiu.DANG_GIU);

        assert ghe.getCCCD().equals("012345678");
        assert ghe.getTrangThaiGiu() == TrangThaiGiu.DANG_GIU;

        System.out.println("✓ Getter/Setter OK");
    }

    public static void testGiuGheVaDatGhe() {
        System.out.println("=== TEST GIỮ GHẾ – ĐẶT GHẾ ===");
        Ghe ghe = new Ghe("GH001", 2, 3, LoaiGhe.THUONG, "PH001");
        ghe.giuGhe("987654321", LocalDateTime.now().plusMinutes(10));
        assert ghe.isDangGiuGhe();

        ghe.datGhe();
        assert ghe.isDaDat();
        assert ghe.getTrangThaiGiu() == TrangThaiGiu.DA_DAT_THANH_CONG;

        ghe.huyGiuGhe();
        assert ghe.getTrangThaiGiu() == TrangThaiGiu.KHONG_GIU;
        System.out.println("✓ Giữ ghế và đặt ghế OK");
    }

    public static void testCRUD() {
        System.out.println("=== TEST CRUD ===");
        Ghe g1 = new Ghe("GHCRUD", 1, 1, LoaiGhe.THUONG, "PHCRUD");
        Ghe.Create(g1);

        assert Ghe.Read().size() > 0;
        Ghe.Read("GHCRUD");

        Ghe g2 = new Ghe("GHCRUD", 9, 9, LoaiGhe.VIP, "PHCRUD2");
        Ghe.Update("GHCRUD", g2);
        assert Ghe.getGheByMaGhe("GHCRUD").getHang() == 9;

        Ghe.Delete("GHCRUD");
        assert Ghe.getGheByMaGhe("GHCRUD") == null;
        System.out.println("✓ CRUD OK");
    }

    public static void testThongKe() {
        System.out.println("=== TEST THỐNG KÊ ===");
        Ghe.Create(new Ghe("TK1", 1, 1, LoaiGhe.THUONG, "P1"));
        Ghe.Create(new Ghe("TK2", 2, 2, LoaiGhe.VIP, "P1"));
        Ghe.Create(new Ghe("TK3", 3, 3, LoaiGhe.COUPLE, "P1"));

        Ghe.thongKeGhe();
        System.out.println("✓ Thống kê OK");
    }

    public static void testTimKiem() {
        System.out.println("=== TEST TÌM KIẾM ===");
        Ghe.Create(new Ghe("FIND1", 1, 1, LoaiGhe.THUONG, "F1"));
        Ghe ghe = Ghe.getGheByMaGhe("FIND1");
        ghe.setMaSuatChieu("SCTIM");
        ghe.giuGhe("999888", LocalDateTime.now().plusMinutes(5));

        ArrayList<Ghe> listSuatChieu = Ghe.getGheBySuatChieu("SCTIM");
        assert listSuatChieu.size() >= 1;

        ArrayList<Ghe> listKH = Ghe.getGheDangGiuByKhachHang("999888");
        assert listKH.size() >= 1;

        ArrayList<Ghe> listPhong = Ghe.getGheByMaPhong("F1");
        assert listPhong.size() >= 1;

        System.out.println("✓ Tìm kiếm OK");
    }

    public static void test() {
        testConstructor();
        testGetterSetter();
        testGiuGheVaDatGhe();
        testCRUD();
        testThongKe();
        testTimKiem();
    }
}
