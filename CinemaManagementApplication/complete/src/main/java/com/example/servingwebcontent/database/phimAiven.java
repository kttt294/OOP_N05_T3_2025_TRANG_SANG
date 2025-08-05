package com.example.servingwebcontent.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import com.example.servingwebcontent.model.Phim;
import java.util.ArrayList;
import java.util.List;

public class phimAiven {
  
    public List<Phim> getAllPhim() {
        Connection conn = null;
        List<Phim> danhSachPhim = new ArrayList<>();
        try {

            myDBConnection mydb = new myDBConnection();

            conn = mydb.getOnlyConn();

             
            Statement sta = conn.createStatement();
            ResultSet reset = sta.executeQuery("select * from phim");
            System.out.println("Lấy tất cả dữ liệu phim từ database: ");
            while (reset.next()) {
                String maPhim = reset.getString("maPhim");
                String tenPhim = reset.getString("tenPhim");
                String theLoai = reset.getString("theLoai");
                int thoiLuong = reset.getInt("thoiLuong");
                String ngonNgu = reset.getString("ngonNgu");
                int gioiHanTuoi = reset.getInt("gioiHanTuoi");
                String moTa = reset.getString("moTa");
                
                Phim phim = new Phim(maPhim, tenPhim, theLoai, thoiLuong, ngonNgu, gioiHanTuoi, moTa);
                danhSachPhim.add(phim);
                System.out.println("Mã phim: " + maPhim + " | Tên phim: " + tenPhim + " | Thể loại: " + theLoai);

            }

            reset.close();
            sta.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi lấy dữ liệu phim: " + e);

            e.printStackTrace();
        }
        return danhSachPhim;
    }
    
    public Phim getPhimById(String maPhim) {
        Connection conn = null;
        Phim phim = null;
        try {

            myDBConnection mydb = new myDBConnection();

            conn = mydb.getOnlyConn();

             
            Statement sta = conn.createStatement();
            ResultSet reset = sta.executeQuery("select * from phim where maPhim = '" + maPhim + "'");
            System.out.println("Tìm phim theo mã: " + maPhim);
            if (reset.next()) {
                String tenPhim = reset.getString("tenPhim");
                String theLoai = reset.getString("theLoai");
                int thoiLuong = reset.getInt("thoiLuong");
                String ngonNgu = reset.getString("ngonNgu");
                int gioiHanTuoi = reset.getInt("gioiHanTuoi");
                String moTa = reset.getString("moTa");
                
                phim = new Phim(maPhim, tenPhim, theLoai, thoiLuong, ngonNgu, gioiHanTuoi, moTa);
                System.out.println("Tìm thấy phim: " + tenPhim);

            }

            reset.close();
            sta.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi tìm phim: " + e);

            e.printStackTrace();
        }
        return phim;
    }
    
    // Thêm method tạo phim mới
    public boolean createPhim(Phim phim) {
        Connection conn = null;
        try {
            myDBConnection mydb = new myDBConnection();
            conn = mydb.getOnlyConn();
            Statement sta = conn.createStatement();
            
            String sql = "INSERT INTO phim (maPhim, tenPhim, theLoai, thoiLuong, ngonNgu, gioiHanTuoi, moTa) VALUES " +
                        "('" + phim.getMaPhim() + "', '" + phim.getTenPhim() + "', '" + phim.getTheLoai() + "', " +
                        phim.getThoiLuong() + ", '" + phim.getNgonNgu() + "', " + phim.getGioiHanTuoi() + ", '" + phim.getMoTa() + "')";
            
            int result = sta.executeUpdate(sql);
            System.out.println("Tạo phim thành công: " + result + " dòng được thêm");
            
            sta.close();
            conn.close();
            return result > 0;
        } catch (Exception e) {
            System.out.println("Lỗi tạo phim: " + e);
            e.printStackTrace();
            return false;
        }
    }
    
    // Thêm method cập nhật phim
    public boolean updatePhim(String maPhim, Phim phim) {
        Connection conn = null;
        try {
            myDBConnection mydb = new myDBConnection();
            conn = mydb.getOnlyConn();
            Statement sta = conn.createStatement();
            
            String sql = "UPDATE phim SET tenPhim = '" + phim.getTenPhim() + "', theLoai = '" + phim.getTheLoai() + 
                        "', thoiLuong = " + phim.getThoiLuong() + ", ngonNgu = '" + phim.getNgonNgu() + 
                        "', gioiHanTuoi = " + phim.getGioiHanTuoi() + ", moTa = '" + phim.getMoTa() + 
                        "' WHERE maPhim = '" + maPhim + "'";
            
            int result = sta.executeUpdate(sql);
            System.out.println("Cập nhật phim thành công: " + result + " dòng được cập nhật");
            
            sta.close();
            conn.close();
            return result > 0;
        } catch (Exception e) {
            System.out.println("Lỗi cập nhật phim: " + e);
            e.printStackTrace();
            return false;
        }
    }
    
    // Thêm method xóa phim
    public boolean deletePhim(String maPhim) {
        Connection conn = null;
        try {
            myDBConnection mydb = new myDBConnection();
            conn = mydb.getOnlyConn();
            Statement sta = conn.createStatement();
            
            String sql = "DELETE FROM phim WHERE maPhim = '" + maPhim + "'";
            
            int result = sta.executeUpdate(sql);
            System.out.println("Xóa phim thành công: " + result + " dòng được xóa");
            
            sta.close();
            conn.close();
            return result > 0;
        } catch (Exception e) {
            System.out.println("Lỗi xóa phim: " + e);
            e.printStackTrace();
            return false;
        }
    }
} 