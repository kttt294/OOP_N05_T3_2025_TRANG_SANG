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
    
    public List<SuatChieu> getSuatChieuByPhong(String maPhong) {
        Connection conn = null;
        List<SuatChieu> danhSachSuatChieu = new ArrayList<>();
        try {

            myDBConnection mydb = new myDBConnection();

            conn = mydb.getOnlyConn();

             
            Statement sta = conn.createStatement();
            ResultSet reset = sta.executeQuery("select * from suatchieu where maPhong = '" + maPhong + "'");
            System.out.println("Tìm suất chiếu theo phòng: " + maPhong);
            while (reset.next()) {
                String maSuatChieu = reset.getString("maSuatChieu");
                String maPhim = reset.getString("maPhim");
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
                System.out.println("Tìm thấy: " + maSuatChieu + " - Phim: " + maPhim);

            }

            reset.close();
            sta.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi tìm suất chiếu theo phòng: " + e);

            e.printStackTrace();
        }
        return danhSachSuatChieu;
    }
    
    // Thêm method tạo suất chiếu mới
    public boolean createSuatChieu(SuatChieu suatChieu) {
        Connection conn = null;
        try {
            myDBConnection mydb = new myDBConnection();
            conn = mydb.getOnlyConn();
            Statement sta = conn.createStatement();
            
            String thoiGianBatDauStr = suatChieu.getThoiGianBatDau().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            String thoiGianKetThucStr = suatChieu.getThoiGianKetThuc().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            
            String sql = "INSERT INTO suatchieu (maSuatChieu, maPhim, maPhong, thoiGianBatDau, thoiGianKetThuc) VALUES " +
                        "('" + suatChieu.getMaSuatChieu() + "', '" + suatChieu.getMaPhim() + "', '" + suatChieu.getMaPhong() + 
                        "', '" + thoiGianBatDauStr + "', '" + thoiGianKetThucStr + "')";
            
            int result = sta.executeUpdate(sql);
            System.out.println("Tạo suất chiếu thành công: " + result + " dòng được thêm");
            
            sta.close();
            conn.close();
            return result > 0;
        } catch (Exception e) {
            System.out.println("Lỗi tạo suất chiếu: " + e);
            e.printStackTrace();
            return false;
        }
    }
    
    // Thêm method cập nhật suất chiếu
    public boolean updateSuatChieu(String maSuatChieu, SuatChieu suatChieuMoi) {
        Connection conn = null;
        try {
            myDBConnection mydb = new myDBConnection();
            conn = mydb.getOnlyConn();
            Statement sta = conn.createStatement();
            
            String thoiGianBatDauStr = suatChieuMoi.getThoiGianBatDau().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            String thoiGianKetThucStr = suatChieuMoi.getThoiGianKetThuc().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            
            String sql = "UPDATE suatchieu SET maPhim = '" + suatChieuMoi.getMaPhim() + "', maPhong = '" + suatChieuMoi.getMaPhong() + 
                        "', thoiGianBatDau = '" + thoiGianBatDauStr + "', thoiGianKetThuc = '" + thoiGianKetThucStr + 
                        "' WHERE maSuatChieu = '" + maSuatChieu + "'";
            
            int result = sta.executeUpdate(sql);
            System.out.println("Cập nhật suất chiếu thành công: " + result + " dòng được cập nhật");
            
            sta.close();
            conn.close();
            return result > 0;
        } catch (Exception e) {
            System.out.println("Lỗi cập nhật suất chiếu: " + e);
            e.printStackTrace();
            return false;
        }
    }
    
    // Thêm method xóa suất chiếu
    public boolean deleteSuatChieu(String maSuatChieu) {
        Connection conn = null;
        try {
            myDBConnection mydb = new myDBConnection();
            conn = mydb.getOnlyConn();
            Statement sta = conn.createStatement();
            
            String sql = "DELETE FROM suatchieu WHERE maSuatChieu = '" + maSuatChieu + "'";
            
            int result = sta.executeUpdate(sql);
            System.out.println("Xóa suất chiếu thành công: " + result + " dòng được xóa");
            
            sta.close();
            conn.close();
            return result > 0;
        } catch (Exception e) {
            System.out.println("Lỗi xóa suất chiếu: " + e);
            e.printStackTrace();
            return false;
        }
    }
} 