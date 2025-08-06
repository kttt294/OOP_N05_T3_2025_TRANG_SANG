package com.example.servingwebcontent.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import com.example.servingwebcontent.model.DanhGia;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class danhGiaAiven {
  
    public List<DanhGia> getAllDanhGia() {
        Connection conn = null;
        List<DanhGia> danhSachDanhGia = new ArrayList<>();
        try {

            myDBConnection mydb = new myDBConnection();

            conn = mydb.getOnlyConn();

             
            Statement sta = conn.createStatement();
            ResultSet reset = sta.executeQuery("select * from danhgia");
            System.out.println("Lấy tất cả dữ liệu đánh giá từ database: ");
            while (reset.next()) {
                String maDanhGia = reset.getString("maDanhGia");
                String CCCD = reset.getString("CCCD");
                String maPhim = reset.getString("maPhim");
                int soSao = reset.getInt("soSao");
                String noiDung = reset.getString("noiDung");
                String thoiGianStr = reset.getString("thoiGian");
                
                LocalDateTime thoiGian = null;
                if (thoiGianStr != null) {
                    thoiGian = LocalDateTime.parse(thoiGianStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                }
                
                DanhGia danhGia = new DanhGia(maDanhGia, CCCD, maPhim, soSao, noiDung, thoiGian);
                danhSachDanhGia.add(danhGia);
                System.out.println("Mã đánh giá: " + maDanhGia + " | CCCD: " + CCCD + " | Số sao: " + soSao);

            }

            reset.close();
            sta.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi lấy dữ liệu đánh giá: " + e);

            e.printStackTrace();
        }
        return danhSachDanhGia;
    }
    
    public DanhGia getDanhGiaById(String maDanhGia) {
        Connection conn = null;
        DanhGia danhGia = null;
        try {

            myDBConnection mydb = new myDBConnection();

            conn = mydb.getOnlyConn();

             
            Statement sta = conn.createStatement();
            ResultSet reset = sta.executeQuery("select * from danhgia where maDanhGia = '" + maDanhGia + "'");
            System.out.println("Tìm đánh giá theo mã: " + maDanhGia);
            if (reset.next()) {
                String CCCD = reset.getString("CCCD");
                String maPhim = reset.getString("maPhim");
                int soSao = reset.getInt("soSao");
                String noiDung = reset.getString("noiDung");
                String thoiGianStr = reset.getString("thoiGian");
                
                LocalDateTime thoiGian = null;
                if (thoiGianStr != null) {
                    thoiGian = LocalDateTime.parse(thoiGianStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                }
                
                danhGia = new DanhGia(maDanhGia, CCCD, maPhim, soSao, noiDung, thoiGian);
                System.out.println("Tìm thấy đánh giá: " + maDanhGia);

            }

            reset.close();
            sta.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi tìm đánh giá: " + e);

            e.printStackTrace();
        }
        return danhGia;
    }
    
    public List<DanhGia> getDanhGiaByPhim(String maPhim) {
        Connection conn = null;
        List<DanhGia> danhSachDanhGia = new ArrayList<>();
        try {

            myDBConnection mydb = new myDBConnection();

            conn = mydb.getOnlyConn();

             
            Statement sta = conn.createStatement();
            ResultSet reset = sta.executeQuery("select * from danhgia where maPhim = '" + maPhim + "'");
            System.out.println("Tìm đánh giá theo phim: " + maPhim);
            while (reset.next()) {
                String maDanhGia = reset.getString("maDanhGia");
                String CCCD = reset.getString("CCCD");
                int soSao = reset.getInt("soSao");
                String noiDung = reset.getString("noiDung");
                String thoiGianStr = reset.getString("thoiGian");
                
                LocalDateTime thoiGian = null;
                if (thoiGianStr != null) {
                    thoiGian = LocalDateTime.parse(thoiGianStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                }
                
                DanhGia danhGia = new DanhGia(maDanhGia, CCCD, maPhim, soSao, noiDung, thoiGian);
                danhSachDanhGia.add(danhGia);
                System.out.println("Tìm thấy: " + maDanhGia + " - Số sao: " + soSao);

            }

            reset.close();
            sta.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi tìm đánh giá theo phim: " + e);

            e.printStackTrace();
        }
        return danhSachDanhGia;
    }
    
    public List<DanhGia> getDanhGiaByCCCD(String CCCD) {
        Connection conn = null;
        List<DanhGia> danhSachDanhGia = new ArrayList<>();
        try {

            myDBConnection mydb = new myDBConnection();

            conn = mydb.getOnlyConn();

             
            Statement sta = conn.createStatement();
            ResultSet reset = sta.executeQuery("select * from danhgia where CCCD = '" + CCCD + "'");
            System.out.println("Tìm đánh giá theo CCCD: " + CCCD);
            while (reset.next()) {
                String maDanhGia = reset.getString("maDanhGia");
                String maPhim = reset.getString("maPhim");
                int soSao = reset.getInt("soSao");
                String noiDung = reset.getString("noiDung");
                String thoiGianStr = reset.getString("thoiGian");
                
                LocalDateTime thoiGian = null;
                if (thoiGianStr != null) {
                    thoiGian = LocalDateTime.parse(thoiGianStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                }
                
                DanhGia danhGia = new DanhGia(maDanhGia, CCCD, maPhim, soSao, noiDung, thoiGian);
                danhSachDanhGia.add(danhGia);
                System.out.println("Tìm thấy: " + maDanhGia + " - Phim: " + maPhim + " - Số sao: " + soSao);

            }

            reset.close();
            sta.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi tìm đánh giá theo CCCD: " + e);

            e.printStackTrace();
        }
        return danhSachDanhGia;
    }
    
    public List<DanhGia> getDanhGiaBySoSao(int soSao) {
        Connection conn = null;
        List<DanhGia> danhSachDanhGia = new ArrayList<>();
        try {

            myDBConnection mydb = new myDBConnection();

            conn = mydb.getOnlyConn();

             
            Statement sta = conn.createStatement();
            ResultSet reset = sta.executeQuery("select * from danhgia where soSao = " + soSao);
            System.out.println("Tìm đánh giá theo số sao: " + soSao);
            while (reset.next()) {
                String maDanhGia = reset.getString("maDanhGia");
                String CCCD = reset.getString("CCCD");
                String maPhim = reset.getString("maPhim");
                String noiDung = reset.getString("noiDung");
                String thoiGianStr = reset.getString("thoiGian");
                
                LocalDateTime thoiGian = null;
                if (thoiGianStr != null) {
                    thoiGian = LocalDateTime.parse(thoiGianStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                }
                
                DanhGia danhGia = new DanhGia(maDanhGia, CCCD, maPhim, soSao, noiDung, thoiGian);
                danhSachDanhGia.add(danhGia);
                System.out.println("Tìm thấy: " + maDanhGia + " - Phim: " + maPhim);

            }

            reset.close();
            sta.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi tìm đánh giá theo số sao: " + e);

            e.printStackTrace();
        }
        return danhSachDanhGia;
    }
    
    // Thêm method tạo đánh giá mới
    public boolean createDanhGia(DanhGia danhGia) {
        Connection conn = null;
        try {
            myDBConnection mydb = new myDBConnection();
            conn = mydb.getOnlyConn();
            Statement sta = conn.createStatement();
            
            String thoiGianStr = danhGia.getThoiGian() != null ? 
                danhGia.getThoiGian().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : null;
            
            String sql = "INSERT INTO danhgia (maDanhGia, CCCD, maPhim, soSao, noiDung, thoiGian) VALUES " +
                        "('" + danhGia.getMaDanhGia() + "', '" + danhGia.getCCCD() + "', '" + danhGia.getMaPhim() + 
                        "', " + danhGia.getSoSao() + ", '" + danhGia.getNoiDung() + "', " + 
                        (thoiGianStr != null ? "'" + thoiGianStr + "'" : "NULL") + ")";
            
            int result = sta.executeUpdate(sql);
            System.out.println("Tạo đánh giá thành công: " + result + " dòng được thêm");
            
            sta.close();
            conn.close();
            return result > 0;
        } catch (Exception e) {
            System.out.println("Lỗi tạo đánh giá: " + e);
            e.printStackTrace();
            return false;
        }
    }
    
    // Thêm method cập nhật đánh giá
    public boolean updateDanhGia(String maDanhGia, DanhGia danhGia) {
        Connection conn = null;
        try {
            myDBConnection mydb = new myDBConnection();
            conn = mydb.getOnlyConn();
            Statement sta = conn.createStatement();
            
            String thoiGianStr = danhGia.getThoiGian() != null ? 
                danhGia.getThoiGian().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : null;
            
            String sql = "UPDATE danhgia SET CCCD = '" + danhGia.getCCCD() + "', maPhim = '" + danhGia.getMaPhim() + 
                        "', soSao = " + danhGia.getSoSao() + ", noiDung = '" + danhGia.getNoiDung() + 
                        "', thoiGian = " + (thoiGianStr != null ? "'" + thoiGianStr + "'" : "NULL") + 
                        " WHERE maDanhGia = '" + maDanhGia + "'";
            
            int result = sta.executeUpdate(sql);
            System.out.println("Cập nhật đánh giá thành công: " + result + " dòng được cập nhật");
            
            sta.close();
            conn.close();
            return result > 0;
        } catch (Exception e) {
            System.out.println("Lỗi cập nhật đánh giá: " + e);
            e.printStackTrace();
            return false;
        }
    }
    
    // Thêm method xóa đánh giá
    public boolean deleteDanhGia(String maDanhGia) {
        Connection conn = null;
        try {
            myDBConnection mydb = new myDBConnection();
            conn = mydb.getOnlyConn();
            Statement sta = conn.createStatement();
            
            String sql = "DELETE FROM danhgia WHERE maDanhGia = '" + maDanhGia + "'";
            
            int result = sta.executeUpdate(sql);
            System.out.println("Xóa đánh giá thành công: " + result + " dòng được xóa");
            
            sta.close();
            conn.close();
            return result > 0;
        } catch (Exception e) {
            System.out.println("Lỗi xóa đánh giá: " + e);
            e.printStackTrace();
            return false;
        }
    }
} 