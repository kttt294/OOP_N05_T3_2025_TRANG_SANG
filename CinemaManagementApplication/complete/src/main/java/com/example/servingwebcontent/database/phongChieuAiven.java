package com.example.servingwebcontent.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import com.example.servingwebcontent.model.PhongChieu;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class phongChieuAiven {
    
    @Autowired
    private myDBConnection mydb;
  
    public List<PhongChieu> getAllPhongChieu() {
        Connection conn = null;
        List<PhongChieu> danhSachPhong = new ArrayList<>();
        try {
            conn = mydb.getOnlyConn();

             
            Statement sta = conn.createStatement();
            ResultSet reset = sta.executeQuery("select * from phongchieu");
            System.out.println("Lấy tất cả dữ liệu phòng chiếu từ database: ");
            while (reset.next()) {
                String maPhong = reset.getString("maPhong");
                String tenPhong = reset.getString("tenPhong");
                int soHangGhe = reset.getInt("soHangGhe");
                int soCotGhe = reset.getInt("soCotGhe");
                
                PhongChieu phongChieu = new PhongChieu(maPhong, tenPhong, soHangGhe, soCotGhe);
                danhSachPhong.add(phongChieu);
                System.out.println("Mã phòng: " + maPhong + " | Tên phòng: " + tenPhong + " | Số hàng: " + soHangGhe + " | Số cột: " + soCotGhe);

            }

            reset.close();
            sta.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi lấy dữ liệu phòng chiếu: " + e);

            e.printStackTrace();
        }
        return danhSachPhong;
    }
    
    public PhongChieu getPhongChieuById(String maPhong) {
        Connection conn = null;
        PhongChieu phongChieu = null;
        try {
            conn = mydb.getOnlyConn();

             
            Statement sta = conn.createStatement();
            ResultSet reset = sta.executeQuery("select * from phongchieu where maPhong = '" + maPhong + "'");
            System.out.println("Tìm phòng chiếu theo mã: " + maPhong);
            if (reset.next()) {
                String tenPhong = reset.getString("tenPhong");
                int soHangGhe = reset.getInt("soHangGhe");
                int soCotGhe = reset.getInt("soCotGhe");
                
                phongChieu = new PhongChieu(maPhong, tenPhong, soHangGhe, soCotGhe);
                System.out.println("Tìm thấy phòng chiếu: " + tenPhong);

            }

            reset.close();
            sta.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi tìm phòng chiếu: " + e);

            e.printStackTrace();
        }
        return phongChieu;
    }
    
    public List<PhongChieu> searchPhongChieuByTen(String tenPhong) {
        Connection conn = null;
        List<PhongChieu> danhSachPhong = new ArrayList<>();
        try {
            conn = mydb.getOnlyConn();

             
            Statement sta = conn.createStatement();
            ResultSet reset = sta.executeQuery("select * from phongchieu where tenPhong LIKE '%" + tenPhong + "%'");
            System.out.println("Tìm kiếm phòng chiếu theo tên: " + tenPhong);
            while (reset.next()) {
                String maPhong = reset.getString("maPhong");
                String tenPhongResult = reset.getString("tenPhong");
                int soHangGhe = reset.getInt("soHangGhe");
                int soCotGhe = reset.getInt("soCotGhe");
                
                PhongChieu phongChieu = new PhongChieu(maPhong, tenPhongResult, soHangGhe, soCotGhe);
                danhSachPhong.add(phongChieu);
                System.out.println("Tìm thấy: " + maPhong + " - " + tenPhongResult);

            }

            reset.close();
            sta.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi tìm kiếm phòng chiếu theo tên: " + e);

            e.printStackTrace();
        }
        return danhSachPhong;
    }
    
    // Thêm method tạo phòng chiếu mới
    public boolean createPhongChieu(PhongChieu phongChieu) {
        Connection conn = null;
        try {
            conn = mydb.getOnlyConn();
            Statement sta = conn.createStatement();
            
            String sql = "INSERT INTO phongchieu (maPhong, tenPhong, soHangGhe, soCotGhe) VALUES " +
                        "('" + phongChieu.getMaPhong() + "', '" + phongChieu.getTenPhong() + "', " + 
                        phongChieu.getSoHangGhe() + ", " + phongChieu.getSoCotGhe() + ")";
            
            int result = sta.executeUpdate(sql);
            System.out.println("Tạo phòng chiếu thành công: " + result + " dòng được thêm");
            
            sta.close();
            conn.close();
            return result > 0;
        } catch (Exception e) {
            System.out.println("Lỗi tạo phòng chiếu: " + e);
            e.printStackTrace();
            return false;
        }
    }
    
    // Thêm method cập nhật phòng chiếu
    public boolean updatePhongChieu(String maPhong, PhongChieu phongChieu) {
        Connection conn = null;
        try {
            conn = mydb.getOnlyConn();
            Statement sta = conn.createStatement();
            
            String sql = "UPDATE phongchieu SET tenPhong = '" + phongChieu.getTenPhong() + 
                        "', soHangGhe = " + phongChieu.getSoHangGhe() + ", soCotGhe = " + phongChieu.getSoCotGhe() + 
                        " WHERE maPhong = '" + maPhong + "'";
            
            int result = sta.executeUpdate(sql);
            System.out.println("Cập nhật phòng chiếu thành công: " + result + " dòng được cập nhật");
            
            sta.close();
            conn.close();
            return result > 0;
        } catch (Exception e) {
            System.out.println("Lỗi cập nhật phòng chiếu: " + e);
            e.printStackTrace();
            return false;
        }
    }
    
    // Thêm method xóa phòng chiếu
    public boolean deletePhongChieu(String maPhong) {
        Connection conn = null;
        try {
            conn = mydb.getOnlyConn();
            Statement sta = conn.createStatement();
            
            String sql = "DELETE FROM phongchieu WHERE maPhong = '" + maPhong + "'";
            
            int result = sta.executeUpdate(sql);
            System.out.println("Xóa phòng chiếu thành công: " + result + " dòng được xóa");
            
            sta.close();
            conn.close();
            return result > 0;
        } catch (Exception e) {
            System.out.println("Lỗi xóa phòng chiếu: " + e);
            e.printStackTrace();
            return false;
        }
    }
} 