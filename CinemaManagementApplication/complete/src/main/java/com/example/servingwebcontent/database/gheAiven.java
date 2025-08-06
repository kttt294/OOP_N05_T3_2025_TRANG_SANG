package com.example.servingwebcontent.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import com.example.servingwebcontent.model.Ghe;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class gheAiven {
  
    public List<Ghe> getAllGhe() {
        Connection conn = null;
        List<Ghe> danhSachGhe = new ArrayList<>();
        try {

            myDBConnection mydb = new myDBConnection();

            conn = mydb.getOnlyConn();

             
            Statement sta = conn.createStatement();
            ResultSet reset = sta.executeQuery("select * from ghe");
            System.out.println("Lấy tất cả dữ liệu ghế từ database: ");
            while (reset.next()) {
                String maGhe = reset.getString("maGhe");
                int hang = reset.getInt("hang");
                int cot = reset.getInt("cot");
                String maPhong = reset.getString("maPhong");
                String maSuatChieu = reset.getString("maSuatChieu");
                String trangThaiStr = reset.getString("trangThai");
                
                Ghe.TrangThaiGhe trangThai = Ghe.TrangThaiGhe.TRONG;
                if (trangThaiStr != null) {
                    switch (trangThaiStr.toUpperCase()) {
                        case "DA_DAT":
                            trangThai = Ghe.TrangThaiGhe.DA_DAT;
                            break;
                        case "KHOA":
                            trangThai = Ghe.TrangThaiGhe.KHOA;
                            break;
                        default:
                            trangThai = Ghe.TrangThaiGhe.TRONG;
                    }
                }
                
                Ghe ghe = new Ghe(maGhe, hang, cot, maPhong, maSuatChieu, trangThai);
                danhSachGhe.add(ghe);
                System.out.println("Mã ghế: " + maGhe + " | Hàng: " + hang + " | Cột: " + cot + " | Trạng thái: " + trangThai);

            }

            reset.close();
            sta.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi lấy dữ liệu ghế: " + e);

            e.printStackTrace();
        }
        return danhSachGhe;
    }
    
    public Ghe getGheById(String maGhe) {
        Connection conn = null;
        Ghe ghe = null;
        try {

            myDBConnection mydb = new myDBConnection();

            conn = mydb.getOnlyConn();

             
            Statement sta = conn.createStatement();
            ResultSet reset = sta.executeQuery("select * from ghe where maGhe = '" + maGhe + "'");
            System.out.println("Tìm ghế theo mã: " + maGhe);
            if (reset.next()) {
                int hang = reset.getInt("hang");
                int cot = reset.getInt("cot");
                String maPhong = reset.getString("maPhong");
                String maSuatChieu = reset.getString("maSuatChieu");
                String trangThaiStr = reset.getString("trangThai");
                
                Ghe.TrangThaiGhe trangThai = Ghe.TrangThaiGhe.TRONG;
                if (trangThaiStr != null) {
                    switch (trangThaiStr.toUpperCase()) {
                        case "DA_DAT":
                            trangThai = Ghe.TrangThaiGhe.DA_DAT;
                            break;
                        case "KHOA":
                            trangThai = Ghe.TrangThaiGhe.KHOA;
                            break;
                        default:
                            trangThai = Ghe.TrangThaiGhe.TRONG;
                    }
                }
                
                ghe = new Ghe(maGhe, hang, cot, maPhong, maSuatChieu, trangThai);
                System.out.println("Tìm thấy ghế: " + maGhe);

            }

            reset.close();
            sta.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi tìm ghế: " + e);

            e.printStackTrace();
        }
        return ghe;
    }
    
    public List<Ghe> getGheByPhong(String maPhong) {
        Connection conn = null;
        List<Ghe> danhSachGhe = new ArrayList<>();
        try {

            myDBConnection mydb = new myDBConnection();

            conn = mydb.getOnlyConn();

             
            Statement sta = conn.createStatement();
            ResultSet reset = sta.executeQuery("select * from ghe where maPhong = '" + maPhong + "'");
            System.out.println("Tìm ghế theo phòng: " + maPhong);
            while (reset.next()) {
                String maGhe = reset.getString("maGhe");
                int hang = reset.getInt("hang");
                int cot = reset.getInt("cot");
                String maSuatChieu = reset.getString("maSuatChieu");
                String trangThaiStr = reset.getString("trangThai");
                
                Ghe.TrangThaiGhe trangThai = Ghe.TrangThaiGhe.TRONG;
                if (trangThaiStr != null) {
                    switch (trangThaiStr.toUpperCase()) {
                        case "DA_DAT":
                            trangThai = Ghe.TrangThaiGhe.DA_DAT;
                            break;
                        case "KHOA":
                            trangThai = Ghe.TrangThaiGhe.KHOA;
                            break;
                        default:
                            trangThai = Ghe.TrangThaiGhe.TRONG;
                    }
                }
                
                Ghe ghe = new Ghe(maGhe, hang, cot, maPhong, maSuatChieu, trangThai);
                danhSachGhe.add(ghe);
                System.out.println("Tìm thấy: " + maGhe + " - Hàng " + hang + ", Cột " + cot);

            }

            reset.close();
            sta.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi tìm ghế theo phòng: " + e);

            e.printStackTrace();
        }
        return danhSachGhe;
    }
    
    public List<Ghe> getGheByTrangThai(Ghe.TrangThaiGhe trangThai) {
        Connection conn = null;
        List<Ghe> danhSachGhe = new ArrayList<>();
        try {

            myDBConnection mydb = new myDBConnection();

            conn = mydb.getOnlyConn();

             
            Statement sta = conn.createStatement();
            String trangThaiStr = trangThai.toString();
            ResultSet reset = sta.executeQuery("select * from ghe where trangThai = '" + trangThaiStr + "'");
            System.out.println("Tìm ghế theo trạng thái: " + trangThaiStr);
            while (reset.next()) {
                String maGhe = reset.getString("maGhe");
                int hang = reset.getInt("hang");
                int cot = reset.getInt("cot");
                String maPhong = reset.getString("maPhong");
                String maSuatChieu = reset.getString("maSuatChieu");
                
                Ghe ghe = new Ghe(maGhe, hang, cot, maPhong, maSuatChieu, trangThai);
                danhSachGhe.add(ghe);
                System.out.println("Tìm thấy: " + maGhe + " - Phòng: " + maPhong);

            }

            reset.close();
            sta.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi tìm ghế theo trạng thái: " + e);

            e.printStackTrace();
        }
        return danhSachGhe;
    }
    
    public boolean createGhe(Ghe ghe) {
        Connection conn = null;
        try {
            myDBConnection mydb = new myDBConnection();
            conn = mydb.getOnlyConn();
            
            Statement sta = conn.createStatement();
            String sql = "INSERT INTO ghe (maGhe, hang, cot, maPhong, maSuatChieu, trangThai) VALUES ('" +
                ghe.getMaGhe() + "', " + ghe.getHang() + ", " + ghe.getCot() + ", '" + 
                ghe.getMaPhong() + "', '" + ghe.getMaSuatChieu() + "', '" + 
                ghe.getTrangThai().toString() + "')";
            
            int result = sta.executeUpdate(sql);
            System.out.println("Tạo ghế thành công: " + ghe.getMaGhe());
            
            sta.close();
            conn.close();
            return result > 0;
        } catch (Exception e) {
            System.out.println("Lỗi tạo ghế: " + e);
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateGhe(String maGhe, Ghe gheMoi) {
        Connection conn = null;
        try {
            myDBConnection mydb = new myDBConnection();
            conn = mydb.getOnlyConn();
            
            Statement sta = conn.createStatement();
            String sql = "UPDATE ghe SET hang = " + gheMoi.getHang() + 
                ", cot = " + gheMoi.getCot() + 
                ", maPhong = '" + gheMoi.getMaPhong() + "'" +
                ", maSuatChieu = '" + gheMoi.getMaSuatChieu() + "'" +
                ", trangThai = '" + gheMoi.getTrangThai().toString() + "'" +
                " WHERE maGhe = '" + maGhe + "'";
            
            int result = sta.executeUpdate(sql);
            System.out.println("Cập nhật ghế thành công: " + maGhe);
            
            sta.close();
            conn.close();
            return result > 0;
        } catch (Exception e) {
            System.out.println("Lỗi cập nhật ghế: " + e);
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteGhe(String maGhe) {
        Connection conn = null;
        try {
            myDBConnection mydb = new myDBConnection();
            conn = mydb.getOnlyConn();
            
            Statement sta = conn.createStatement();
            String sql = "DELETE FROM ghe WHERE maGhe = '" + maGhe + "'";
            
            int result = sta.executeUpdate(sql);
            System.out.println("Xóa ghế thành công: " + maGhe);
            
            sta.close();
            conn.close();
            return result > 0;
        } catch (Exception e) {
            System.out.println("Lỗi xóa ghế: " + e);
            e.printStackTrace();
            return false;
        }
    }
} 