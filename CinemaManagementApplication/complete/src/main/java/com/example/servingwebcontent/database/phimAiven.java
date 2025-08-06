package com.example.servingwebcontent.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import com.example.servingwebcontent.model.Phim;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class phimAiven {
  
    public List<Phim> getAllPhim() {
        Connection conn = null;
        List<Phim> danhSachPhim = new ArrayList<>();
        try {
            myDBConnection mydb = new myDBConnection();
            conn = mydb.getOnlyConn();
            
            String sql = "SELECT * FROM phim ORDER BY tenPhim";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet reset = pstmt.executeQuery();
            
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
            pstmt.close();
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
            
            String sql = "SELECT * FROM phim WHERE maPhim = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maPhim);
            ResultSet reset = pstmt.executeQuery();
            
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
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi tìm phim: " + e);
            e.printStackTrace();
        }
        return phim;
    }

    // thêm method tạo phim mới
    public boolean createPhim(Phim phim) {
        Connection conn = null;
        try {
            myDBConnection mydb = new myDBConnection();
            conn = mydb.getOnlyConn();
            
            String sql = "INSERT INTO phim (maPhim, tenPhim, theLoai, thoiLuong, ngonNgu, gioiHanTuoi, moTa) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, phim.getMaPhim());
            pstmt.setString(2, phim.getTenPhim());
            pstmt.setString(3, phim.getTheLoai());
            pstmt.setInt(4, phim.getThoiLuong());
            pstmt.setString(5, phim.getNgonNgu());
            pstmt.setInt(6, phim.getGioiHanTuoi());
            pstmt.setString(7, phim.getMoTa());
            
            int result = pstmt.executeUpdate();
            System.out.println("Tạo phim thành công: " + result + " dòng được thêm");
            
            pstmt.close();
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
            
            String sql = "UPDATE phim SET tenPhim = ?, theLoai = ?, thoiLuong = ?, ngonNgu = ?, gioiHanTuoi = ?, moTa = ? WHERE maPhim = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, phim.getTenPhim());
            pstmt.setString(2, phim.getTheLoai());
            pstmt.setInt(3, phim.getThoiLuong());
            pstmt.setString(4, phim.getNgonNgu());
            pstmt.setInt(5, phim.getGioiHanTuoi());
            pstmt.setString(6, phim.getMoTa());
            pstmt.setString(7, maPhim);
            
            int result = pstmt.executeUpdate();
            System.out.println("Cập nhật phim thành công: " + result + " dòng được cập nhật");
            
            pstmt.close();
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
            
            String sql = "DELETE FROM phim WHERE maPhim = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maPhim);
            
            int result = pstmt.executeUpdate();
            System.out.println("Xóa phim thành công: " + result + " dòng được xóa");
            
            pstmt.close();
            conn.close();
            return result > 0;
        } catch (Exception e) {
            System.out.println("Lỗi xóa phim: " + e);
            e.printStackTrace();
            return false;
        }
    }
    
    // Thêm method tìm kiếm theo tên
    public List<Phim> searchPhimByTen(String tenPhim) {
        Connection conn = null;
        List<Phim> danhSachPhim = new ArrayList<>();
        try {
            myDBConnection mydb = new myDBConnection();
            conn = mydb.getOnlyConn();
            
            String sql = "SELECT * FROM phim WHERE tenPhim LIKE ? ORDER BY tenPhim";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + tenPhim + "%");
            ResultSet reset = pstmt.executeQuery();
            
            while (reset.next()) {
                String maPhim = reset.getString("maPhim");
                String tenPhimResult = reset.getString("tenPhim");
                String theLoai = reset.getString("theLoai");
                int thoiLuong = reset.getInt("thoiLuong");
                String ngonNgu = reset.getString("ngonNgu");
                int gioiHanTuoi = reset.getInt("gioiHanTuoi");
                String moTa = reset.getString("moTa");
                
                Phim phim = new Phim(maPhim, tenPhimResult, theLoai, thoiLuong, ngonNgu, gioiHanTuoi, moTa);
                danhSachPhim.add(phim);
            }
            
            reset.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi tìm kiếm phim theo tên: " + e);
            e.printStackTrace();
        }
        return danhSachPhim;
    }
    
    // Thêm method tìm kiếm theo thể loại
    public List<Phim> searchPhimByTheLoai(String theLoai) {
        Connection conn = null;
        List<Phim> danhSachPhim = new ArrayList<>();
        try {
            myDBConnection mydb = new myDBConnection();
            conn = mydb.getOnlyConn();
            
            String sql = "SELECT * FROM phim WHERE theLoai LIKE ? ORDER BY tenPhim";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + theLoai + "%");
            ResultSet reset = pstmt.executeQuery();
            
            while (reset.next()) {
                String maPhim = reset.getString("maPhim");
                String tenPhim = reset.getString("tenPhim");
                String theLoaiResult = reset.getString("theLoai");
                int thoiLuong = reset.getInt("thoiLuong");
                String ngonNgu = reset.getString("ngonNgu");
                int gioiHanTuoi = reset.getInt("gioiHanTuoi");
                String moTa = reset.getString("moTa");
                
                Phim phim = new Phim(maPhim, tenPhim, theLoaiResult, thoiLuong, ngonNgu, gioiHanTuoi, moTa);
                danhSachPhim.add(phim);
            }
            
            reset.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi tìm kiếm phim theo thể loại: " + e);
            e.printStackTrace();
        }
        return danhSachPhim;
    }
} 