package com.example.servingwebcontent;

import com.example.servingwebcontent.model.Ghe;

public class testGhe {

    public static void main(String[] args) {

        // Test constructor và getter
        Ghe ghe1 = new Ghe("GHE001", 1, 2, "PHONG01");
        assert ghe1.getMaGhe().equals("GHE001");
        assert ghe1.getHang() == 1;
        assert ghe1.getCot() == 2;
        assert ghe1.getMaPhong().equals("PHONG01");
        assert ghe1.getTrangThai() == Ghe.TrangThaiGhe.TRONG;

        // Test setter
        ghe1.setMaSuatChieu("SC01");
        ghe1.setTrangThai(Ghe.TrangThaiGhe.DA_DAT);
        assert ghe1.getMaSuatChieu().equals("SC01");
        assert ghe1.getTrangThai() == Ghe.TrangThaiGhe.DA_DAT;

        // Test trạng thái logic
        assert ghe1.isDaDat();
        ghe1.huyDat();
        assert ghe1.isTrong();

        // Test datGhe
        ghe1.datGhe();
        assert ghe1.getTrangThai() == Ghe.TrangThaiGhe.DA_DAT;

        // Test Create
        Ghe.Create(ghe1); // tạo thành công
        Ghe.Create(ghe1); // tạo trùng -> in lỗi

        // Test Read by mã
        Ghe ghe2 = Ghe.getGheByMaGhe("GHE001");
        assert ghe2 != null;
        assert ghe2.getMaGhe().equals("GHE001");

        // Test Read toàn bộ
        assert Ghe.Read().size() >= 1;

        // Test Update
        Ghe gheCapNhat = new Ghe("IGNORED", 9, 9, "PHONG99");
        Ghe.Update("GHE001", gheCapNhat);
        Ghe gheSauUpdate = Ghe.getGheByMaGhe("GHE001");
        assert gheSauUpdate.getHang() == 9;
        assert gheSauUpdate.getCot() == 9;

        // Test getGheIndexByMaGhe
        int index = Ghe.getGheIndexByMaGhe("GHE001");
        assert index != -1;

        // Test Delete
        Ghe.Delete("GHE001");
        assert Ghe.getGheByMaGhe("GHE001") == null;

        // Test equals và hashCode
        Ghe g1 = new Ghe("GHE123", 1, 1, "P1");
        Ghe g2 = new Ghe("GHE123", 2, 2, "P2");
        assert g1.equals(g2);
        assert g1.hashCode() == g2.hashCode();

        // Test hienThiThongTin (chạy không lỗi là được)
        g1.hienThiThongTin();

        System.out.println("✓ Tất cả kiểm thử Ghe đã thực thi xong.");
    }
}
