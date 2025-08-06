package com.example.servingwebcontent.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import com.example.servingwebcontent.model.DoAn;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class doAnAiven {
    
    @Autowired
    private myDBConnection mydb;
  
    public List<DoAn> getAllDoAn() {
        Connection conn = null;
        List<DoAn> danhSachDoAn = new ArrayList<>();
        try {
            conn = mydb.getOnlyConn();

             
            Statement sta = conn.createStatement();
            ResultSet reset = sta.executeQuery("select * from doan");
            System.out.println("Lấy tất cả dữ liệu đồ ăn từ database: ");
            while (reset.next()) {
                String maDoAn = reset.getString("maDoAn");
                String tenDoAn = reset.getString("tenDoAn");
                int gia = reset.getInt("gia");
                int soLuongCon = reset.getInt("soLuongCon");
                
                DoAn doAn = new DoAn(maDoAn, tenDoAn, gia, soLuongCon);
                danhSachDoAn.add(doAn);
                System.out.println("Mã đồ ăn: " + maDoAn + " | Tên: " + tenDoAn + " | Giá: " + gia + " | Số lượng: " + soLuongCon);

            }

            reset.close();
            sta.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi lấy dữ liệu đồ ăn: " + e);

            e.printStackTrace();
        }
        return danhSachDoAn;
    }
    
    public DoAn getDoAnById(String maDoAn) {
        Connection conn = null;
        DoAn doAn = null;
        try {
            conn = mydb.getOnlyConn();

             
            Statement sta = conn.createStatement();
            ResultSet reset = sta.executeQuery("select * from doan where maDoAn = '" + maDoAn + "'");
            System.out.println("Tìm đồ ăn theo mã: " + maDoAn);
            if (reset.next()) {
                String tenDoAn = reset.getString("tenDoAn");
                int gia = reset.getInt("gia");
                int soLuongCon = reset.getInt("soLuongCon");
                
                doAn = new DoAn(maDoAn, tenDoAn, gia, soLuongCon);
                System.out.println("Tìm thấy đồ ăn: " + tenDoAn);

            }

            reset.close();
            sta.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi tìm đồ ăn: " + e);

            e.printStackTrace();
        }
        return doAn;
    }
    
    public List<DoAn> searchDoAnByTen(String tenDoAn) {
        Connection conn = null;
        List<DoAn> danhSachDoAn = new ArrayList<>();
        try {
            conn = mydb.getOnlyConn();

             
            Statement sta = conn.createStatement();
            ResultSet reset = sta.executeQuery("select * from doan where tenDoAn LIKE '%" + tenDoAn + "%'");
            System.out.println("Tìm kiếm đồ ăn theo tên: " + tenDoAn);
            while (reset.next()) {
                String maDoAn = reset.getString("maDoAn");
                String tenDoAnResult = reset.getString("tenDoAn");
                int gia = reset.getInt("gia");
                int soLuongCon = reset.getInt("soLuongCon");
                
                DoAn doAn = new DoAn(maDoAn, tenDoAnResult, gia, soLuongCon);
                danhSachDoAn.add(doAn);
                System.out.println("Tìm thấy: " + maDoAn + " - " + tenDoAnResult);

            }

            reset.close();
            sta.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi tìm kiếm đồ ăn theo tên: " + e);

            e.printStackTrace();
        }
        return danhSachDoAn;
    }
    
    public List<DoAn> getDoAnByGia(int giaMin, int giaMax) {
        Connection conn = null;
        List<DoAn> danhSachDoAn = new ArrayList<>();
        try {
            conn = mydb.getOnlyConn();

             
            Statement sta = conn.createStatement();
            ResultSet reset = sta.executeQuery("select * from doan where gia >= " + giaMin + " and gia <= " + giaMax);
            System.out.println("Tìm đồ ăn theo khoảng giá: " + giaMin + " - " + giaMax);
            while (reset.next()) {
                String maDoAn = reset.getString("maDoAn");
                String tenDoAn = reset.getString("tenDoAn");
                int gia = reset.getInt("gia");
                int soLuongCon = reset.getInt("soLuongCon");
                
                DoAn doAn = new DoAn(maDoAn, tenDoAn, gia, soLuongCon);
                danhSachDoAn.add(doAn);
                System.out.println("Tìm thấy: " + maDoAn + " - " + tenDoAn + " (Giá: " + gia + ")");

            }

            reset.close();
            sta.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi tìm đồ ăn theo giá: " + e);

            e.printStackTrace();
        }
        return danhSachDoAn;
    }
    
    // Thêm method tạo đồ ăn mới
    public boolean createDoAn(DoAn doAn) {
        Connection conn = null;
        try {
            conn = mydb.getOnlyConn();
            Statement sta = conn.createStatement();
            
            String sql = "INSERT INTO doan (maDoAn, tenDoAn, gia, soLuongCon) VALUES " +
                        "('" + doAn.getMaDoAn() + "', '" + doAn.getTenDoAn() + "', " + 
                        doAn.getGia() + ", " + doAn.getSoLuong() + ")";
            
            int result = sta.executeUpdate(sql);
            System.out.println("Tạo đồ ăn thành công: " + result + " dòng được thêm");
            
            sta.close();
            conn.close();
            return result > 0;
        } catch (Exception e) {
            System.out.println("Lỗi tạo đồ ăn: " + e);
            e.printStackTrace();
            return false;
        }
    }
    
    // Thêm method cập nhật đồ ăn
    public boolean updateDoAn(String maDoAn, DoAn doAn) {
        Connection conn = null;
        try {
            conn = mydb.getOnlyConn();
            Statement sta = conn.createStatement();
            
            String sql = "UPDATE doan SET tenDoAn = '" + doAn.getTenDoAn() + "', gia = " + doAn.getGia() + 
                        ", soLuongCon = " + doAn.getSoLuong() + " WHERE maDoAn = '" + maDoAn + "'";
            
            int result = sta.executeUpdate(sql);
            System.out.println("Cập nhật đồ ăn thành công: " + result + " dòng được cập nhật");
            
            sta.close();
            conn.close();
            return result > 0;
        } catch (Exception e) {
            System.out.println("Lỗi cập nhật đồ ăn: " + e);
            e.printStackTrace();
            return false;
        }
    }
    
    // Thêm method xóa đồ ăn
    public boolean deleteDoAn(String maDoAn) {
        Connection conn = null;
        try {
            conn = mydb.getOnlyConn();
            Statement sta = conn.createStatement();
            
            String sql = "DELETE FROM doan WHERE maDoAn = '" + maDoAn + "'";
            
            int result = sta.executeUpdate(sql);
            System.out.println("Xóa đồ ăn thành công: " + result + " dòng được xóa");
            
            sta.close();
            conn.close();
            return result > 0;
        } catch (Exception e) {
            System.out.println("Lỗi xóa đồ ăn: " + e);
            e.printStackTrace();
            return false;
        }
    }
} 