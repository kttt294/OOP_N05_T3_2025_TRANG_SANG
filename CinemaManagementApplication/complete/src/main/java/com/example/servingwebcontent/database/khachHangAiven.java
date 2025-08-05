package com.example.servingwebcontent.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import com.example.servingwebcontent.model.KhachHang;
import java.util.ArrayList;
import java.util.List;

public class khachHangAiven {
  
    public List<KhachHang> getAllKhachHang() {
        Connection conn = null;
        List<KhachHang> danhSachKhachHang = new ArrayList<>();
        try {

            myDBConnection mydb = new myDBConnection();

            conn = mydb.getOnlyConn();

             
            Statement sta = conn.createStatement();
            ResultSet reset = sta.executeQuery("select * from khachhang");
            System.out.println("Lấy tất cả dữ liệu khách hàng từ database: ");
            while (reset.next()) {
                String CCCD = reset.getString("CCCD");
                String ten = reset.getString("ten");
                int tuoi = reset.getInt("tuoi");
                String sdt = reset.getString("sdt");
                String email = reset.getString("email");
                String gioiTinh = reset.getString("gioiTinh");
                
                KhachHang khachHang = new KhachHang(CCCD, ten, tuoi, sdt, email, gioiTinh);
                danhSachKhachHang.add(khachHang);
                System.out.println("CCCD: " + CCCD + " | Tên: " + ten + " | Tuổi: " + tuoi);

            }

            reset.close();
            sta.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi lấy dữ liệu khách hàng: " + e);

            e.printStackTrace();
        }
        return danhSachKhachHang;
    }
    
    public KhachHang getKhachHangByCCCD(String CCCD) {
        Connection conn = null;
        KhachHang khachHang = null;
        try {

            myDBConnection mydb = new myDBConnection();

            conn = mydb.getOnlyConn();

             
            Statement sta = conn.createStatement();
            ResultSet reset = sta.executeQuery("select * from khachhang where CCCD = '" + CCCD + "'");
            System.out.println("Tìm khách hàng theo CCCD: " + CCCD);
            if (reset.next()) {
                String ten = reset.getString("ten");
                int tuoi = reset.getInt("tuoi");
                String sdt = reset.getString("sdt");
                String email = reset.getString("email");
                String gioiTinh = reset.getString("gioiTinh");
                
                khachHang = new KhachHang(CCCD, ten, tuoi, sdt, email, gioiTinh);
                System.out.println("Tìm thấy khách hàng: " + ten);

            }

            reset.close();
            sta.close();
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

            myDBConnection mydb = new myDBConnection();

            conn = mydb.getOnlyConn();

             
            Statement sta = conn.createStatement();
            ResultSet reset = sta.executeQuery("select * from khachhang where ten LIKE '%" + ten + "%'");
            System.out.println("Tìm kiếm khách hàng theo tên: " + ten);
            while (reset.next()) {
                String CCCD = reset.getString("CCCD");
                String tenResult = reset.getString("ten");
                int tuoi = reset.getInt("tuoi");
                String sdt = reset.getString("sdt");
                String email = reset.getString("email");
                String gioiTinh = reset.getString("gioiTinh");
                
                KhachHang khachHang = new KhachHang(CCCD, tenResult, tuoi, sdt, email, gioiTinh);
                danhSachKhachHang.add(khachHang);
                System.out.println("Tìm thấy: " + CCCD + " - " + tenResult);

            }

            reset.close();
            sta.close();
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
            myDBConnection mydb = new myDBConnection();
            conn = mydb.getOnlyConn();
            Statement sta = conn.createStatement();
            
            String sql = "INSERT INTO khachhang (CCCD, ten, tuoi, sdt, email, gioiTinh) VALUES " +
                        "('" + khachHang.getCCCD() + "', '" + khachHang.getTen() + "', " + khachHang.getTuoi() + 
                        ", '" + khachHang.getSdt() + "', '" + khachHang.getEmail() + "', '" + khachHang.getGioiTinh() + "')";
            
            int result = sta.executeUpdate(sql);
            System.out.println("Tạo khách hàng thành công: " + result + " dòng được thêm");
            
            sta.close();
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
            myDBConnection mydb = new myDBConnection();
            conn = mydb.getOnlyConn();
            Statement sta = conn.createStatement();
            
            String sql = "UPDATE khachhang SET ten = '" + khachHang.getTen() + "', tuoi = " + khachHang.getTuoi() + 
                        ", sdt = '" + khachHang.getSdt() + "', email = '" + khachHang.getEmail() + 
                        "', gioiTinh = '" + khachHang.getGioiTinh() + "' WHERE CCCD = '" + CCCD + "'";
            
            int result = sta.executeUpdate(sql);
            System.out.println("Cập nhật khách hàng thành công: " + result + " dòng được cập nhật");
            
            sta.close();
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
            myDBConnection mydb = new myDBConnection();
            conn = mydb.getOnlyConn();
            Statement sta = conn.createStatement();
            
            String sql = "DELETE FROM khachhang WHERE CCCD = '" + CCCD + "'";
            
            int result = sta.executeUpdate(sql);
            System.out.println("Xóa khách hàng thành công: " + result + " dòng được xóa");
            
            sta.close();
            conn.close();
            return result > 0;
        } catch (Exception e) {
            System.out.println("Lỗi xóa khách hàng: " + e);
            e.printStackTrace();
            return false;
        }
    }
} 