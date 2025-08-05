package com.example.servingwebcontent.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import com.example.servingwebcontent.model.SuatChieu;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class suatChieuAiven {
  
    public List<SuatChieu> getAllSuatChieu() {
        Connection conn = null;
        List<SuatChieu> danhSachSuatChieu = new ArrayList<>();
        try {

            myDBConnection mydb = new myDBConnection();

            conn = mydb.getOnlyConn();

             
            Statement sta = conn.createStatement();
            ResultSet reset = sta.executeQuery("select * from suatchieu");
            System.out.println("Lấy tất cả dữ liệu suất chiếu từ database: ");
            while (reset.next()) {
                String maSuatChieu = reset.getString("maSuatChieu");
                String maPhim = reset.getString("maPhim");
                String maPhong = reset.getString("maPhong");
                String thoiGianBatDauStr = reset.getString("thoiGianBatDau");
                String thoiGianKetThucStr = reset.getString("thoiGianKetThuc");
                
                LocalDateTime thoiGianBatDau = null;
                LocalDateTime thoiGianKetThuc = null;
                
                if (thoiGianBatDauStr != null) {
                    thoiGianBatDau = LocalDateTime.parse(thoiGianBatDauStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                }
                if (thoiGianKetThucStr != null) {
                    thoiGianKetThuc = LocalDateTime.parse(thoiGianKetThucStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                }
                
                SuatChieu suatChieu = new SuatChieu();
                suatChieu.setMaSuatChieu(maSuatChieu);
                suatChieu.setMaPhim(maPhim);
                suatChieu.setMaPhong(maPhong);
                suatChieu.setThoiGianBatDau(thoiGianBatDau);
                suatChieu.setThoiGianKetThuc(thoiGianKetThuc);
                
                danhSachSuatChieu.add(suatChieu);
                System.out.println("Mã suất chiếu: " + maSuatChieu + " | Mã phim: " + maPhim + " | Mã phòng: " + maPhong);

            }

            reset.close();
            sta.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi lấy dữ liệu suất chiếu: " + e);

            e.printStackTrace();
        }
        return danhSachSuatChieu;
    }
    
    public SuatChieu getSuatChieuById(String maSuatChieu) {
        Connection conn = null;
        SuatChieu suatChieu = null;
        try {

            myDBConnection mydb = new myDBConnection();

            conn = mydb.getOnlyConn();

             
            Statement sta = conn.createStatement();
            ResultSet reset = sta.executeQuery("select * from suatchieu where maSuatChieu = '" + maSuatChieu + "'");
            System.out.println("Tìm suất chiếu theo mã: " + maSuatChieu);
            if (reset.next()) {
                String maPhim = reset.getString("maPhim");
                String maPhong = reset.getString("maPhong");
                String thoiGianBatDauStr = reset.getString("thoiGianBatDau");
                String thoiGianKetThucStr = reset.getString("thoiGianKetThuc");
                
                LocalDateTime thoiGianBatDau = null;
                LocalDateTime thoiGianKetThuc = null;
                
                if (thoiGianBatDauStr != null) {
                    thoiGianBatDau = LocalDateTime.parse(thoiGianBatDauStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                }
                if (thoiGianKetThucStr != null) {
                    thoiGianKetThuc = LocalDateTime.parse(thoiGianKetThucStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                }
                
                suatChieu = new SuatChieu();
                suatChieu.setMaSuatChieu(maSuatChieu);
                suatChieu.setMaPhim(maPhim);
                suatChieu.setMaPhong(maPhong);
                suatChieu.setThoiGianBatDau(thoiGianBatDau);
                suatChieu.setThoiGianKetThuc(thoiGianKetThuc);
                
                System.out.println("Tìm thấy suất chiếu: " + maSuatChieu);

            }

            reset.close();
            sta.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi tìm suất chiếu: " + e);

            e.printStackTrace();
        }
        return suatChieu;
    }
    
    public List<SuatChieu> getSuatChieuByPhim(String maPhim) {
        Connection conn = null;
        List<SuatChieu> danhSachSuatChieu = new ArrayList<>();
        try {

            myDBConnection mydb = new myDBConnection();

            conn = mydb.getOnlyConn();

             
            Statement sta = conn.createStatement();
            ResultSet reset = sta.executeQuery("select * from suatchieu where maPhim = '" + maPhim + "'");
            System.out.println("Tìm suất chiếu theo phim: " + maPhim);
            while (reset.next()) {
                String maSuatChieu = reset.getString("maSuatChieu");
                String maPhong = reset.getString("maPhong");
                String thoiGianBatDauStr = reset.getString("thoiGianBatDau");
                String thoiGianKetThucStr = reset.getString("thoiGianKetThuc");
                
                LocalDateTime thoiGianBatDau = null;
                LocalDateTime thoiGianKetThuc = null;
                
                if (thoiGianBatDauStr != null) {
                    thoiGianBatDau = LocalDateTime.parse(thoiGianBatDauStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                }
                if (thoiGianKetThucStr != null) {
                    thoiGianKetThuc = LocalDateTime.parse(thoiGianKetThucStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                }
                
                SuatChieu suatChieu = new SuatChieu();
                suatChieu.setMaSuatChieu(maSuatChieu);
                suatChieu.setMaPhim(maPhim);
                suatChieu.setMaPhong(maPhong);
                suatChieu.setThoiGianBatDau(thoiGianBatDau);
                suatChieu.setThoiGianKetThuc(thoiGianKetThuc);
                
                danhSachSuatChieu.add(suatChieu);
                System.out.println("Tìm thấy: " + maSuatChieu + " - Phòng: " + maPhong);

            }

            reset.close();
            sta.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi tìm suất chiếu theo phim: " + e);

            e.printStackTrace();
        }
        return danhSachSuatChieu;
    }
} 