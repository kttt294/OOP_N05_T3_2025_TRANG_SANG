package com.example.servingwebcontent;

import com.example.servingwebcontent.controller.DatVe;
import com.example.servingwebcontent.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class testDatVe {

    public static void test() {
        System.out.println("=== TEST ĐẶT VÉ ===");

        // Bước 1: Tạo khách hàng
        KhachHang kh = new KhachHang("123456789", "Nguyen Van A", 30, "0123456789", "NguyenVanA89@gmail.com");

        // Bước 2: Tạo ghế và thêm vào danh sách ghế
        Ghe ghe = new Ghe("G100", 1, 1, "P100");
        ghe.setTrangThai(Ghe.TrangThaiGhe.TRONG);
        Ghe.Create(ghe);  // thêm vào danh sách static

        // Bước 3: Tạo phim và suất chiếu
        Phim.Create(new Phim("PH100", "Avengers", "Hành động", 120, "Anh", 13, "Bom tấn"));
        ArrayList<Ghe> danhSachGhe = new ArrayList<>();
        danhSachGhe.add(ghe);

        SuatChieu sc = new SuatChieu("SC100", "PH100", "P100", LocalDateTime.of(2025, 8, 4, 18, 0), danhSachGhe);
        SuatChieu.Create(sc);

        // Bước 4: Gọi DatVe
        DatVe datVe = new DatVe(kh, sc, "VE100", "G100", 90000);
        boolean result = datVe.datVe();

        assert result : "Đặt vé thất bại!";
        assert Ve.getVeById("VE100") != null : "Vé không được tạo!";
        assert Ghe.getGheByMaGhe("G100").getTrangThai() == Ghe.TrangThaiGhe.KHOA : "Ghế chưa được cập nhật trạng thái!";
        assert kh.getLichSuDatVe().size() == 1 : "Lịch sử đặt vé của khách hàng không được cập nhật!";

        System.out.println("✓ Đặt vé OK");
    }
}
