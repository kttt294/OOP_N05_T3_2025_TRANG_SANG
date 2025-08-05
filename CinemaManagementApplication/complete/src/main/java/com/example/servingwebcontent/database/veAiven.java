package com.example.servingwebcontent.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import com.example.servingwebcontent.model.Ve;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class veAiven {
  
    public List<Ve> getAllVe() {
        Connection conn = null;
        List<Ve> danhSachVe = new ArrayList<>();
        try {

            myDBConnection mydb = new myDBConnection();

            conn = mydb.getOnlyConn();

             
            Statement sta = conn.createStatement();
            ResultSet reset = sta.executeQuery("select * from ve");
            System.out.println("Lấy tất cả dữ liệu vé từ database: ");
            while (reset.next()) {
                String maVe = reset.getString("maVe");
                String CCCD = reset.getString("CCCD");
                String maSuatChieu = reset.getString("maSuatChieu");
                String maGhe = reset.getString("maGhe");
                int giaVe = reset.getInt("giaVe");
                String trangThaiStr = reset.getString("trangThai");
                
                Ve.TrangThaiVe trangThai = Ve.TrangThaiVe.CHUA_THANH_TOAN;
                if (trangThaiStr != null) {
                    switch (trangThaiStr.toUpperCase()) {
                        case "DA_THANH_TOAN":
                            trangThai = Ve.TrangThaiVe.DA_THANH_TOAN;
                            break;
                        case "DA_HUY":
                            trangThai = Ve.TrangThaiVe.DA_HUY;
                            break;
                        default:
                            trangThai = Ve.TrangThaiVe.CHUA_THANH_TOAN;
                    }
                }
                
                Ve ve = new Ve(maVe, CCCD, maSuatChieu, maGhe, giaVe);
                ve.setTrangThai(trangThai);
                danhSachVe.add(ve);
                System.out.println("Mã vé: " + maVe + " | CCCD: " + CCCD + " | Giá: " + giaVe);

            }

            reset.close();
            sta.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi lấy dữ liệu vé: " + e);

            e.printStackTrace();
        }
        return danhSachVe;
    }
    
    public Ve getVeById(String maVe) {
        Connection conn = null;
        Ve ve = null;
        try {

            myDBConnection mydb = new myDBConnection();

            conn = mydb.getOnlyConn();

             
            Statement sta = conn.createStatement();
            ResultSet reset = sta.executeQuery("select * from ve where maVe = '" + maVe + "'");
            System.out.println("Tìm vé theo mã: " + maVe);
            if (reset.next()) {
                String CCCD = reset.getString("CCCD");
                String maSuatChieu = reset.getString("maSuatChieu");
                String maGhe = reset.getString("maGhe");
                int giaVe = reset.getInt("giaVe");
                String trangThaiStr = reset.getString("trangThai");
                
                Ve.TrangThaiVe trangThai = Ve.TrangThaiVe.CHUA_THANH_TOAN;
                if (trangThaiStr != null) {
                    switch (trangThaiStr.toUpperCase()) {
                        case "DA_THANH_TOAN":
                            trangThai = Ve.TrangThaiVe.DA_THANH_TOAN;
                            break;
                        case "DA_HUY":
                            trangThai = Ve.TrangThaiVe.DA_HUY;
                            break;
                        default:
                            trangThai = Ve.TrangThaiVe.CHUA_THANH_TOAN;
                    }
                }
                
                ve = new Ve(maVe, CCCD, maSuatChieu, maGhe, giaVe);
                ve.setTrangThai(trangThai);
                System.out.println("Tìm thấy vé: " + maVe);

            }

            reset.close();
            sta.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi tìm vé: " + e);

            e.printStackTrace();
        }
        return ve;
    }
    
    public List<Ve> getVeByCCCD(String CCCD) {
        Connection conn = null;
        List<Ve> danhSachVe = new ArrayList<>();
        try {

            myDBConnection mydb = new myDBConnection();

            conn = mydb.getOnlyConn();

             
            Statement sta = conn.createStatement();
            ResultSet reset = sta.executeQuery("select * from ve where CCCD = '" + CCCD + "'");
            System.out.println("Tìm vé theo CCCD: " + CCCD);
            while (reset.next()) {
                String maVe = reset.getString("maVe");
                String maSuatChieu = reset.getString("maSuatChieu");
                String maGhe = reset.getString("maGhe");
                int giaVe = reset.getInt("giaVe");
                String trangThaiStr = reset.getString("trangThai");
                
                Ve.TrangThaiVe trangThai = Ve.TrangThaiVe.CHUA_THANH_TOAN;
                if (trangThaiStr != null) {
                    switch (trangThaiStr.toUpperCase()) {
                        case "DA_THANH_TOAN":
                            trangThai = Ve.TrangThaiVe.DA_THANH_TOAN;
                            break;
                        case "DA_HUY":
                            trangThai = Ve.TrangThaiVe.DA_HUY;
                            break;
                        default:
                            trangThai = Ve.TrangThaiVe.CHUA_THANH_TOAN;
                    }
                }
                
                Ve ve = new Ve(maVe, CCCD, maSuatChieu, maGhe, giaVe);
                ve.setTrangThai(trangThai);
                danhSachVe.add(ve);
                System.out.println("Tìm thấy: " + maVe + " - Giá: " + giaVe);

            }

            reset.close();
            sta.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi tìm vé theo CCCD: " + e);

            e.printStackTrace();
        }
        return danhSachVe;
    }
    
    public List<Ve> getVeByTrangThai(Ve.TrangThaiVe trangThai) {
        Connection conn = null;
        List<Ve> danhSachVe = new ArrayList<>();
        try {

            myDBConnection mydb = new myDBConnection();

            conn = mydb.getOnlyConn();

             
            Statement sta = conn.createStatement();
            String trangThaiStr = trangThai.toString();
            ResultSet reset = sta.executeQuery("select * from ve where trangThai = '" + trangThaiStr + "'");
            System.out.println("Tìm vé theo trạng thái: " + trangThaiStr);
            while (reset.next()) {
                String maVe = reset.getString("maVe");
                String CCCD = reset.getString("CCCD");
                String maSuatChieu = reset.getString("maSuatChieu");
                String maGhe = reset.getString("maGhe");
                int giaVe = reset.getInt("giaVe");
                
                Ve ve = new Ve(maVe, CCCD, maSuatChieu, maGhe, giaVe);
                ve.setTrangThai(trangThai);
                danhSachVe.add(ve);
                System.out.println("Tìm thấy: " + maVe + " - CCCD: " + CCCD);

            }

            reset.close();
            sta.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi tìm vé theo trạng thái: " + e);

            e.printStackTrace();
        }
        return danhSachVe;
    }
    
    // Thêm method tạo vé mới
    public boolean createVe(Ve ve) {
        Connection conn = null;
        try {
            myDBConnection mydb = new myDBConnection();
            conn = mydb.getOnlyConn();
            Statement sta = conn.createStatement();
            
            String sql = "INSERT INTO ve (maVe, CCCD, maSuatChieu, maGhe, giaVe, trangThai) VALUES " +
                        "('" + ve.getMaVe() + "', '" + ve.getCCCD() + "', '" + ve.getMaSuatChieu() + 
                        "', '" + ve.getMaGhe() + "', " + ve.getGiaVe() + ", '" + ve.getTrangThai().toString() + "')";
            
            int result = sta.executeUpdate(sql);
            System.out.println("Tạo vé thành công: " + result + " dòng được thêm");
            
            sta.close();
            conn.close();
            return result > 0;
        } catch (Exception e) {
            System.out.println("Lỗi tạo vé: " + e);
            e.printStackTrace();
            return false;
        }
    }
    
    // Thêm method cập nhật vé
    public boolean updateVe(String maVe, Ve veMoi) {
        Connection conn = null;
        try {
            myDBConnection mydb = new myDBConnection();
            conn = mydb.getOnlyConn();
            Statement sta = conn.createStatement();
            
            String sql = "UPDATE ve SET CCCD = '" + veMoi.getCCCD() + "', maSuatChieu = '" + veMoi.getMaSuatChieu() + 
                        "', maGhe = '" + veMoi.getMaGhe() + "', giaVe = " + veMoi.getGiaVe() + 
                        ", trangThai = '" + veMoi.getTrangThai().toString() + "' WHERE maVe = '" + maVe + "'";
            
            int result = sta.executeUpdate(sql);
            System.out.println("Cập nhật vé thành công: " + result + " dòng được cập nhật");
            
            sta.close();
            conn.close();
            return result > 0;
        } catch (Exception e) {
            System.out.println("Lỗi cập nhật vé: " + e);
            e.printStackTrace();
            return false;
        }
    }
    
    // Thêm method xóa vé
    public boolean deleteVe(String maVe) {
        Connection conn = null;
        try {
            myDBConnection mydb = new myDBConnection();
            conn = mydb.getOnlyConn();
            Statement sta = conn.createStatement();
            
            String sql = "DELETE FROM ve WHERE maVe = '" + maVe + "'";
            
            int result = sta.executeUpdate(sql);
            System.out.println("Xóa vé thành công: " + result + " dòng được xóa");
            
            sta.close();
            conn.close();
            return result > 0;
        } catch (Exception e) {
            System.out.println("Lỗi xóa vé: " + e);
            e.printStackTrace();
            return false;
        }
    }
} 