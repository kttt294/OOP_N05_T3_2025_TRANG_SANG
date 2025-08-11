package com.example.servingwebcontent.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import com.example.servingwebcontent.model.Voucher;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.sql.PreparedStatement;

@Component
public class voucherAiven {
    
    @Autowired
    private myDBConnection mydb;
  
    public List<Voucher> getAllVoucher() {
        List<Voucher> danhSachVoucher = new ArrayList<>();
        try (Connection conn = mydb.getOnlyConn();
             Statement sta = conn.createStatement();
             ResultSet reset = sta.executeQuery("select * from voucher")) {
            
            // Tạo bảng nếu chưa tồn tại
            createTableIfNotExists(conn);
             
            System.out.println("Lấy tất cả dữ liệu voucher từ database: ");
            while (reset.next()) {
                String maVoucher = reset.getString("maVoucher");
                String moTa = reset.getString("moTa");
                float phanTramGiamGia = reset.getFloat("phanTramGiamGia");
                String ngayBatDauStr = reset.getString("ngayBatDau");
                String ngayKetThucStr = reset.getString("ngayKetThuc");
                int soLuongConLai = reset.getInt("soLuongConLai");
                String trangThai = reset.getString("trangThai");
                
                LocalDateTime ngayBatDau = null;
                LocalDateTime ngayKetThuc = null;
                
                if (ngayBatDauStr != null) {
                    ngayBatDau = LocalDateTime.parse(ngayBatDauStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                }
                if (ngayKetThucStr != null) {
                    ngayKetThuc = LocalDateTime.parse(ngayKetThucStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                }
                
                Voucher voucher = new Voucher(maVoucher, moTa, phanTramGiamGia, ngayBatDau, ngayKetThuc, soLuongConLai, trangThai);
                danhSachVoucher.add(voucher);
                System.out.println("Mã voucher: " + maVoucher + " | Mô tả: " + moTa + " | Giảm giá: " + phanTramGiamGia + "%");
            }
        } catch (Exception e) {
            System.out.println("Lỗi lấy dữ liệu voucher: " + e);
            e.printStackTrace();
        }
        return danhSachVoucher;
    }
    
    private void createTableIfNotExists(Connection conn) {
        try (PreparedStatement pstmt = conn.prepareStatement(
                "CREATE TABLE IF NOT EXISTS voucher (" +
                "maVoucher VARCHAR(50) PRIMARY KEY," +
                "moTa TEXT," +
                "phanTramGiamGia FLOAT," +
                "ngayBatDau DATETIME," +
                "ngayKetThuc DATETIME," +
                "soLuongConLai INT," +
                "trangThai VARCHAR(20)" +
                ")")) {
            
            pstmt.executeUpdate();
            System.out.println("Bảng voucher đã được tạo hoặc đã tồn tại");
        } catch (Exception e) {
            System.out.println("Lỗi tạo bảng voucher: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public Voucher getVoucherById(String maVoucher) {
        Voucher voucher = null;
        try (Connection conn = mydb.getOnlyConn();
             PreparedStatement pstmt = conn.prepareStatement("select * from voucher where maVoucher = ?")) {
            
            pstmt.setString(1, maVoucher);
            System.out.println("Tìm voucher theo mã: " + maVoucher);
            
            try (ResultSet reset = pstmt.executeQuery()) {
                if (reset.next()) {
                    String moTa = reset.getString("moTa");
                    float phanTramGiamGia = reset.getFloat("phanTramGiamGia");
                    String ngayBatDauStr = reset.getString("ngayBatDau");
                    String ngayKetThucStr = reset.getString("ngayKetThuc");
                    int soLuongConLai = reset.getInt("soLuongConLai");
                    String trangThai = reset.getString("trangThai");
                    
                    LocalDateTime ngayBatDau = null;
                    LocalDateTime ngayKetThuc = null;
                    
                    if (ngayBatDauStr != null) {
                        ngayBatDau = LocalDateTime.parse(ngayBatDauStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    }
                    if (ngayKetThucStr != null) {
                        ngayKetThuc = LocalDateTime.parse(ngayKetThucStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    }
                    
                    voucher = new Voucher(maVoucher, moTa, phanTramGiamGia, ngayBatDau, ngayKetThuc, soLuongConLai, trangThai);
                    System.out.println("Tìm thấy voucher: " + maVoucher);
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi tìm voucher: " + e);
            e.printStackTrace();
        }
        return voucher;
    }
    
    public List<Voucher> getVoucherByTrangThai(String trangThai) {
        List<Voucher> danhSachVoucher = new ArrayList<>();
        try (Connection conn = mydb.getOnlyConn();
             PreparedStatement pstmt = conn.prepareStatement("select * from voucher where trangThai = ?")) {
            
            pstmt.setString(1, trangThai);
            System.out.println("Tìm voucher theo trạng thái: " + trangThai);
            
            try (ResultSet reset = pstmt.executeQuery()) {
                while (reset.next()) {
                    String maVoucher = reset.getString("maVoucher");
                    String moTa = reset.getString("moTa");
                    float phanTramGiamGia = reset.getFloat("phanTramGiamGia");
                    String ngayBatDauStr = reset.getString("ngayBatDau");
                    String ngayKetThucStr = reset.getString("ngayKetThuc");
                    int soLuongConLai = reset.getInt("soLuongConLai");
                    
                    LocalDateTime ngayBatDau = null;
                    LocalDateTime ngayKetThuc = null;
                    
                    if (ngayBatDauStr != null) {
                        ngayBatDau = LocalDateTime.parse(ngayBatDauStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    }
                    if (ngayKetThucStr != null) {
                        ngayKetThuc = LocalDateTime.parse(ngayKetThucStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    }
                    
                    Voucher voucher = new Voucher(maVoucher, moTa, phanTramGiamGia, ngayBatDau, ngayKetThuc, soLuongConLai, trangThai);
                    danhSachVoucher.add(voucher);
                    System.out.println("Tìm thấy: " + maVoucher + " - " + moTa);
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi tìm voucher theo trạng thái: " + e);
            e.printStackTrace();
        }
        return danhSachVoucher;
    }
    
    public List<Voucher> getVoucherByPhanTramGiamGia(float phanTramMin, float phanTramMax) {
        List<Voucher> danhSachVoucher = new ArrayList<>();
        try (Connection conn = mydb.getOnlyConn();
             PreparedStatement pstmt = conn.prepareStatement("select * from voucher where phanTramGiamGia >= ? and phanTramGiamGia <= ?")) {
            
            pstmt.setFloat(1, phanTramMin);
            pstmt.setFloat(2, phanTramMax);
            System.out.println("Tìm voucher theo phần trăm giảm giá: " + phanTramMin + "% - " + phanTramMax + "%");
            
            try (ResultSet reset = pstmt.executeQuery()) {
                while (reset.next()) {
                    String maVoucher = reset.getString("maVoucher");
                    String moTa = reset.getString("moTa");
                    float phanTramGiamGia = reset.getFloat("phanTramGiamGia");
                    String ngayBatDauStr = reset.getString("ngayBatDau");
                    String ngayKetThucStr = reset.getString("ngayKetThuc");
                    int soLuongConLai = reset.getInt("soLuongConLai");
                    String trangThai = reset.getString("trangThai");
                    
                    LocalDateTime ngayBatDau = null;
                    LocalDateTime ngayKetThuc = null;
                    
                    if (ngayBatDauStr != null) {
                        ngayBatDau = LocalDateTime.parse(ngayBatDauStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    }
                    if (ngayKetThucStr != null) {
                        ngayKetThuc = LocalDateTime.parse(ngayKetThucStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    }
                    
                    Voucher voucher = new Voucher(maVoucher, moTa, phanTramGiamGia, ngayBatDau, ngayKetThuc, soLuongConLai, trangThai);
                    danhSachVoucher.add(voucher);
                    System.out.println("Tìm thấy: " + maVoucher + " - Giảm giá: " + phanTramGiamGia + "%");
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi tìm voucher theo phần trăm giảm giá: " + e);
            e.printStackTrace();
        }
        return danhSachVoucher;
    }
    
    public boolean createVoucher(Voucher voucher) {
        try (Connection conn = mydb.getOnlyConn();
             PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO voucher (maVoucher, moTa, phanTramGiamGia, ngayBatDau, ngayKetThuc, soLuongConLai, trangThai) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            
            pstmt.setString(1, voucher.getMaVoucher());
            pstmt.setString(2, voucher.getMoTa());
            pstmt.setFloat(3, voucher.getPhanTramGiamGia());
            
            if (voucher.getNgayBatDau() != null) {
                pstmt.setString(4, voucher.getNgayBatDau().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            } else {
                pstmt.setNull(4, java.sql.Types.VARCHAR);
            }
            
            if (voucher.getNgayKetThuc() != null) {
                pstmt.setString(5, voucher.getNgayKetThuc().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            } else {
                pstmt.setNull(5, java.sql.Types.VARCHAR);
            }
            
            pstmt.setInt(6, voucher.getSoLuongConLai());
            pstmt.setString(7, voucher.getTrangThai());
            
            int result = pstmt.executeUpdate();
            System.out.println("Tạo voucher thành công: " + voucher.getMaVoucher());
            return result > 0;
        } catch (Exception e) {
            System.out.println("Lỗi tạo voucher: " + e);
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateVoucher(String maVoucher, Voucher voucherMoi) {
        try (Connection conn = mydb.getOnlyConn();
             PreparedStatement pstmt = conn.prepareStatement(
                "UPDATE voucher SET moTa = ?, phanTramGiamGia = ?, ngayBatDau = ?, ngayKetThuc = ?, soLuongConLai = ?, trangThai = ? WHERE maVoucher = ?")) {
            
            pstmt.setString(1, voucherMoi.getMoTa());
            pstmt.setFloat(2, voucherMoi.getPhanTramGiamGia());
            
            if (voucherMoi.getNgayBatDau() != null) {
                pstmt.setString(3, voucherMoi.getNgayBatDau().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            } else {
                pstmt.setNull(3, java.sql.Types.VARCHAR);
            }
            
            if (voucherMoi.getNgayKetThuc() != null) {
                pstmt.setString(4, voucherMoi.getNgayKetThuc().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            } else {
                pstmt.setNull(4, java.sql.Types.VARCHAR);
            }
            
            pstmt.setInt(5, voucherMoi.getSoLuongConLai());
            pstmt.setString(6, voucherMoi.getTrangThai());
            pstmt.setString(7, maVoucher);
            
            int result = pstmt.executeUpdate();
            System.out.println("Cập nhật voucher thành công: " + maVoucher);
            return result > 0;
        } catch (Exception e) {
            System.out.println("Lỗi cập nhật voucher: " + e);
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteVoucher(String maVoucher) {
        try (Connection conn = mydb.getOnlyConn();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM voucher WHERE maVoucher = ?")) {
            
            pstmt.setString(1, maVoucher);
            int result = pstmt.executeUpdate();
            System.out.println("Xóa voucher thành công: " + maVoucher);
            return result > 0;
        } catch (Exception e) {
            System.out.println("Lỗi xóa voucher: " + e);
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Voucher> searchVoucherByMoTa(String keyword) {
        List<Voucher> danhSachVoucher = new ArrayList<>();
        try (Connection conn = mydb.getOnlyConn();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM voucher WHERE moTa LIKE ?")) {
            
            pstmt.setString(1, "%" + keyword + "%");
            System.out.println("Tìm voucher theo mô tả: " + keyword);
            
            try (ResultSet reset = pstmt.executeQuery()) {
                while (reset.next()) {
                    String maVoucher = reset.getString("maVoucher");
                    String moTa = reset.getString("moTa");
                    float phanTramGiamGia = reset.getFloat("phanTramGiamGia");
                    String ngayBatDauStr = reset.getString("ngayBatDau");
                    String ngayKetThucStr = reset.getString("ngayKetThuc");
                    int soLuongConLai = reset.getInt("soLuongConLai");
                    String trangThai = reset.getString("trangThai");
                    
                    LocalDateTime ngayBatDau = null;
                    LocalDateTime ngayKetThuc = null;
                    
                    if (ngayBatDauStr != null) {
                        ngayBatDau = LocalDateTime.parse(ngayBatDauStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    }
                    if (ngayKetThucStr != null) {
                        ngayKetThuc = LocalDateTime.parse(ngayKetThucStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    }
                    
                    Voucher voucher = new Voucher(maVoucher, moTa, phanTramGiamGia, ngayBatDau, ngayKetThuc, soLuongConLai, trangThai);
                    danhSachVoucher.add(voucher);
                    System.out.println("Tìm thấy: " + maVoucher + " - " + moTa);
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi tìm voucher theo mô tả: " + e);
            e.printStackTrace();
        }
        return danhSachVoucher;
    }
} 