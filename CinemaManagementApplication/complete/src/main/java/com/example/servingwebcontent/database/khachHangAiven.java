package com.example.servingwebcontent.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import com.example.servingwebcontent.model.KhachHang;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class khachHangAiven {
    
    @Autowired
    private myDBConnection mydb;
  
    public List<KhachHang> getAllKhachHang() {
        Connection conn = null;
        List<KhachHang> danhSachKhachHang = new ArrayList<>();
        try {
            conn = mydb.getOnlyConn();
            
            // Tạo bảng nếu chưa tồn tại
            createTableIfNotExists(conn);
            
            String sql = "SELECT * FROM khachhang ORDER BY ten";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet reset = pstmt.executeQuery();
            
            System.out.println("Lấy tất cả dữ liệu khách hàng từ database: ");
            while (reset.next()) {
                String CCCD = reset.getString("CCCD");
                String ten = reset.getString("ten");
                int tuoi = reset.getInt("tuoi");
                String sdt = reset.getString("sdt");
                String email = reset.getString("email");
                String gioiTinh = reset.getString("gioiTinh");
                String diaChi = reset.getString("diaChi");
                String ngheNghiep = reset.getString("ngheNghiep");
                String ngaySinh = reset.getString("ngaySinh");
                String soVisa = reset.getString("soVisa");
                
                KhachHang khachHang = new KhachHang(CCCD, ten, tuoi, sdt, email, gioiTinh);
                khachHang.setDiaChi(diaChi);
                khachHang.setNgheNghiep(ngheNghiep);
                khachHang.setNgaySinh(ngaySinh);
                khachHang.setSoVisa(soVisa);
                
                danhSachKhachHang.add(khachHang);
                System.out.println("CCCD: " + CCCD + " | Tên: " + ten + " | Tuổi: " + tuoi);
            }

            reset.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi lấy dữ liệu khách hàng: " + e);
            e.printStackTrace();
        }
        return danhSachKhachHang;
    }
    
    private void createTableIfNotExists(Connection conn) {
        try {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS khachhang (" +
                "CCCD VARCHAR(20) PRIMARY KEY," +
                "ten VARCHAR(255) NOT NULL," +
                "tuoi INT," +
                "sdt VARCHAR(15)," +
                "email VARCHAR(255)," +
                "gioiTinh VARCHAR(10)," +
                "diaChi TEXT," +
                "ngheNghiep VARCHAR(100)," +
                "ngaySinh VARCHAR(20)," +
                "soVisa VARCHAR(20)" +
                ")";
            
            PreparedStatement pstmt = conn.prepareStatement(createTableSQL);
            pstmt.executeUpdate();
            pstmt.close();
            
            System.out.println("Bảng khachhang đã được tạo hoặc đã tồn tại");
        } catch (Exception e) {
            System.out.println("Lỗi tạo bảng khachhang: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public KhachHang getKhachHangByCCCD(String CCCD) {
        Connection conn = null;
        KhachHang khachHang = null;
        try {
            conn = mydb.getOnlyConn();
            
            String sql = "SELECT * FROM khachhang WHERE CCCD = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, CCCD);
            ResultSet reset = pstmt.executeQuery();
            
            System.out.println("Tìm khách hàng theo CCCD: " + CCCD);
            if (reset.next()) {
                String ten = reset.getString("ten");
                int tuoi = reset.getInt("tuoi");
                String sdt = reset.getString("sdt");
                String email = reset.getString("email");
                String gioiTinh = reset.getString("gioiTinh");
                String diaChi = reset.getString("diaChi");
                String ngheNghiep = reset.getString("ngheNghiep");
                String ngaySinh = reset.getString("ngaySinh");
                String soVisa = reset.getString("soVisa");
                
                khachHang = new KhachHang(CCCD, ten, tuoi, sdt, email, gioiTinh);
                khachHang.setDiaChi(diaChi);
                khachHang.setNgheNghiep(ngheNghiep);
                khachHang.setNgaySinh(ngaySinh);
                khachHang.setSoVisa(soVisa);
                
                System.out.println("Tìm thấy khách hàng: " + ten);
            }

            reset.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi tìm khách hàng: " + e);
            e.printStackTrace();
        }
        return khachHang;
    }
    
    public List<KhachHang> searchKhachHangByTen(String ten) {
        Connection conn = null;
        List<KhachHang> danhSachKhachHang = new ArrayList<>();
        try {
            conn = mydb.getOnlyConn();
            
            String sql = "SELECT * FROM khachhang WHERE ten LIKE ? ORDER BY ten";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + ten + "%");
            ResultSet reset = pstmt.executeQuery();
            
            System.out.println("Tìm kiếm khách hàng theo tên: " + ten);
            while (reset.next()) {
                String CCCD = reset.getString("CCCD");
                String tenResult = reset.getString("ten");
                int tuoi = reset.getInt("tuoi");
                String sdt = reset.getString("sdt");
                String email = reset.getString("email");
                String gioiTinh = reset.getString("gioiTinh");
                String diaChi = reset.getString("diaChi");
                String ngheNghiep = reset.getString("ngheNghiep");
                String ngaySinh = reset.getString("ngaySinh");
                String soVisa = reset.getString("soVisa");
                
                KhachHang khachHang = new KhachHang(CCCD, tenResult, tuoi, sdt, email, gioiTinh);
                khachHang.setDiaChi(diaChi);
                khachHang.setNgheNghiep(ngheNghiep);
                khachHang.setNgaySinh(ngaySinh);
                khachHang.setSoVisa(soVisa);
                
                danhSachKhachHang.add(khachHang);
                System.out.println("Tìm thấy: " + CCCD + " - " + tenResult);
            }

            reset.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi tìm kiếm khách hàng theo tên: " + e);
            e.printStackTrace();
        }
        return danhSachKhachHang;
    }
    
    // Thêm method tạo khách hàng mới
    public boolean createKhachHang(KhachHang khachHang) {
        Connection conn = null;
        try {
            conn = mydb.getOnlyConn();
            
            String sql = "INSERT INTO khachhang (CCCD, ten, tuoi, sdt, email, gioiTinh, diaChi, ngheNghiep, ngaySinh, soVisa) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, khachHang.getCCCD());
            pstmt.setString(2, khachHang.getTen());
            pstmt.setInt(3, khachHang.getTuoi());
            pstmt.setString(4, khachHang.getSdt());
            pstmt.setString(5, khachHang.getEmail());
            pstmt.setString(6, khachHang.getGioiTinh());
            pstmt.setString(7, khachHang.getDiaChi());
            pstmt.setString(8, khachHang.getNgheNghiep());
            pstmt.setString(9, khachHang.getNgaySinh());
            pstmt.setString(10, khachHang.getSoVisa());
            
            int result = pstmt.executeUpdate();
            System.out.println("Tạo khách hàng thành công: " + result + " dòng được thêm");
            
            pstmt.close();
            conn.close();
            return result > 0;
        } catch (Exception e) {
            System.out.println("Lỗi tạo khách hàng: " + e);
            e.printStackTrace();
            return false;
        }
    }
    
    // Thêm method cập nhật khách hàng
    public boolean updateKhachHang(String CCCD, KhachHang khachHang) {
        Connection conn = null;
        try {
            conn = mydb.getOnlyConn();
            
            String sql = "UPDATE khachhang SET ten = ?, tuoi = ?, sdt = ?, email = ?, gioiTinh = ?, " +
                        "diaChi = ?, ngheNghiep = ?, ngaySinh = ?, soVisa = ? WHERE CCCD = ?";
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, khachHang.getTen());
            pstmt.setInt(2, khachHang.getTuoi());
            pstmt.setString(3, khachHang.getSdt());
            pstmt.setString(4, khachHang.getEmail());
            pstmt.setString(5, khachHang.getGioiTinh());
            pstmt.setString(6, khachHang.getDiaChi());
            pstmt.setString(7, khachHang.getNgheNghiep());
            pstmt.setString(8, khachHang.getNgaySinh());
            pstmt.setString(9, khachHang.getSoVisa());
            pstmt.setString(10, CCCD);
            
            int result = pstmt.executeUpdate();
            System.out.println("Cập nhật khách hàng thành công: " + result + " dòng được cập nhật");
            
            pstmt.close();
            conn.close();
            return result > 0;
        } catch (Exception e) {
            System.out.println("Lỗi cập nhật khách hàng: " + e);
            e.printStackTrace();
            return false;
        }
    }
    
    // Thêm method xóa khách hàng
    public boolean deleteKhachHang(String CCCD) {
        Connection conn = null;
        try {
            conn = mydb.getOnlyConn();
            
            String sql = "DELETE FROM khachhang WHERE CCCD = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, CCCD);
            
            int result = pstmt.executeUpdate();
            System.out.println("Xóa khách hàng thành công: " + result + " dòng được xóa");
            
            pstmt.close();
            conn.close();
            return result > 0;
        } catch (Exception e) {
            System.out.println("Lỗi xóa khách hàng: " + e);
            e.printStackTrace();
            return false;
        }
    }
    
    // Thêm method thống kê
    public int getTotalKhachHang() {
        Connection conn = null;
        int total = 0;
        try {
            conn = mydb.getOnlyConn();
            
            String sql = "SELECT COUNT(*) as total FROM khachhang";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet reset = pstmt.executeQuery();
            
            if (reset.next()) {
                total = reset.getInt("total");
            }
            
            reset.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi đếm tổng khách hàng: " + e);
            e.printStackTrace();
        }
        return total;
    }
    
    // Thêm method tìm kiếm theo giới tính
    public List<KhachHang> searchKhachHangByGioiTinh(String gioiTinh) {
        Connection conn = null;
        List<KhachHang> danhSachKhachHang = new ArrayList<>();
        try {
            conn = mydb.getOnlyConn();
            
            String sql = "SELECT * FROM khachhang WHERE gioiTinh = ? ORDER BY ten";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, gioiTinh);
            ResultSet reset = pstmt.executeQuery();
            
            while (reset.next()) {
                String CCCD = reset.getString("CCCD");
                String ten = reset.getString("ten");
                int tuoi = reset.getInt("tuoi");
                String sdt = reset.getString("sdt");
                String email = reset.getString("email");
                String gioiTinhResult = reset.getString("gioiTinh");
                String diaChi = reset.getString("diaChi");
                String ngheNghiep = reset.getString("ngheNghiep");
                String ngaySinh = reset.getString("ngaySinh");
                String soVisa = reset.getString("soVisa");
                
                KhachHang khachHang = new KhachHang(CCCD, ten, tuoi, sdt, email, gioiTinhResult);
                khachHang.setDiaChi(diaChi);
                khachHang.setNgheNghiep(ngheNghiep);
                khachHang.setNgaySinh(ngaySinh);
                khachHang.setSoVisa(soVisa);
                
                danhSachKhachHang.add(khachHang);
            }
            
            reset.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi tìm kiếm theo giới tính: " + e);
            e.printStackTrace();
        }
        return danhSachKhachHang;
    }
} 