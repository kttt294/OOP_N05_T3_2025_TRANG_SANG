package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.model.KhachHang;
import com.example.servingwebcontent.model.SuatChieu;
import com.example.servingwebcontent.model.Ghe;
import com.example.servingwebcontent.model.Ve;

import java.util.ArrayList;

public class DatVe {
    KhachHang kh;
    SuatChieu suatChieu;
    String maVe;
    String maGhe;
    int giaVe;

    public DatVe(KhachHang kh, SuatChieu suatChieu, String maVe, String maGhe, int giaVe){
        this.kh = kh;
        this.suatChieu = suatChieu;
        this.maVe = maVe;
        this.maGhe = maGhe;
        this.giaVe = giaVe;
    }

    public boolean datVe() {
        try {
            String CCCD = kh.getCCCD();
            String maSuatChieu = suatChieu.getMaSuatChieu();

            // Lấy ghế tương ứng
            Ghe ghe = Ghe.getGheByMaGhe(maGhe);
            if (ghe == null) {
                System.out.println("Không tìm thấy ghế!");
                return false;
            }
            // Kiểm tra trạng thái ghế
            if (!"BinhThuong".equalsIgnoreCase(ghe.getTrangThai().toString())) {
                System.out.println("Ghế đã được đặt hoặc không khả dụng!");
                return false;
            }
            // Tạo vé mới
            Ve veDat = new Ve(maVe, CCCD, maSuatChieu, maGhe, giaVe);
            Ve.Create(veDat);
            // Cập nhật trạng thái ghế thành "Khoa" (đã đặt)
            ghe.setTrangThai(Ghe.TrangThaiGhe.KHOA);
            Ghe.Update(maGhe, ghe);
            System.out.println("Đặt vé thành công!");
            return true;
            
        } catch (Exception e) {
            System.out.println("Lỗi khi đặt vé: " + e.getMessage());
            return false;
        }
    }
}