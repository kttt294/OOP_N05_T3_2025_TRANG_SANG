package com.example.servingwebcontent.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import com.example.servingwebcontent.model.HoaDon;
import com.example.servingwebcontent.model.DoAn;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Repository
public class hoaDonAiven {
    
    @Autowired
    private myDBConnection mydb;
    
    public List<HoaDon> getAllHoaDon() {
        Connection conn = null;
        List<HoaDon> danhSachHoaDon = new ArrayList<>();
        try {
            conn = mydb.getOnlyConn();
            
            // Tạo bảng nếu chưa tồn tại
            createTableIfNotExists(conn);
            
            String sql = "SELECT * FROM hoadon ORDER BY thoiGianThanhToan DESC";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet reset = pstmt.executeQuery();
            
            System.out.println("Lấy tất cả dữ liệu hóa đơn từ database: ");
            while (reset.next()) {
                String maHoaDon = reset.getString("maHoaDon");
                String maDoAn = reset.getString("maDoAn");
                int tongTien = reset.getInt("tongTien");
                String thoiGianThanhToanStr = reset.getString("thoiGianThanhToan");
                String phuongThucThanhToanStr = reset.getString("phuongThucThanhToan");
                String CCCD = reset.getString("CCCD");
                
                // Parse thời gian thanh toán
                LocalDateTime thoiGianThanhToan = null;
                if (thoiGianThanhToanStr != null && !thoiGianThanhToanStr.trim().isEmpty()) {
                    try {
                        thoiGianThanhToan = LocalDateTime.parse(thoiGianThanhToanStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    } catch (Exception e) {
                        System.out.println("Lỗi parse thời gian: " + e.getMessage());
                    }
                }
                
                // Parse phương thức thanh toán
                HoaDon.PhuongThucThanhToan phuongThucThanhToan = HoaDon.PhuongThucThanhToan.TIEN_MAT;
                if (phuongThucThanhToanStr != null && phuongThucThanhToanStr.equals("CHUYEN_KHOAN")) {
                    phuongThucThanhToan = HoaDon.PhuongThucThanhToan.CHUYEN_KHOAN;
                }
                
                // Tạo đối tượng DoAn (có thể null nếu không có đồ ăn)
                DoAn doAn = null;
                if (maDoAn != null && !maDoAn.trim().isEmpty()) {
                    // Cần tạo DoAnAiven để lấy thông tin đồ ăn
                    // Tạm thời tạo DoAn với thông tin cơ bản
                    doAn = new DoAn();
                    doAn.setMaDoAn(maDoAn);
                }
                
                HoaDon hoaDon = new HoaDon(maHoaDon, doAn, tongTien, thoiGianThanhToan, phuongThucThanhToan, CCCD);
                danhSachHoaDon.add(hoaDon);
                System.out.println("Mã HD: " + maHoaDon + " | CCCD: " + CCCD + " | Tổng tiền: " + tongTien);
            }

            reset.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi lấy dữ liệu hóa đơn: " + e);
            e.printStackTrace();
        }
        return danhSachHoaDon;
    }
    
    private void createTableIfNotExists(Connection conn) {
        try {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS hoadon (" +
                "maHoaDon VARCHAR(20) PRIMARY KEY," +
                "maDoAn VARCHAR(20)," +
                "tongTien INT NOT NULL," +
                "thoiGianThanhToan DATETIME," +
                "phuongThucThanhToan VARCHAR(20)," +
                "CCCD VARCHAR(20)," +
                "FOREIGN KEY (CCCD) REFERENCES khachhang(CCCD)" +
                ")";
            
            PreparedStatement pstmt = conn.prepareStatement(createTableSQL);
            pstmt.executeUpdate();
            pstmt.close();
            
            System.out.println("Bảng hoadon đã được tạo hoặc đã tồn tại");
        } catch (Exception e) {
            System.out.println("Lỗi tạo bảng hoadon: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public HoaDon getHoaDonById(String maHoaDon) {
        Connection conn = null;
        HoaDon hoaDon = null;
        try {
            conn = mydb.getOnlyConn();
            
            String sql = "SELECT * FROM hoadon WHERE maHoaDon = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maHoaDon);
            ResultSet reset = pstmt.executeQuery();
            
            if (reset.next()) {
                String maDoAn = reset.getString("maDoAn");
                int tongTien = reset.getInt("tongTien");
                String thoiGianThanhToanStr = reset.getString("thoiGianThanhToan");
                String phuongThucThanhToanStr = reset.getString("phuongThucThanhToan");
                String CCCD = reset.getString("CCCD");
                
                // Parse thời gian thanh toán
                LocalDateTime thoiGianThanhToan = null;
                if (thoiGianThanhToanStr != null && !thoiGianThanhToanStr.trim().isEmpty()) {
                    try {
                        thoiGianThanhToan = LocalDateTime.parse(thoiGianThanhToanStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    } catch (Exception e) {
                        System.out.println("Lỗi parse thời gian: " + e.getMessage());
                    }
                }
                
                // Parse phương thức thanh toán
                HoaDon.PhuongThucThanhToan phuongThucThanhToan = HoaDon.PhuongThucThanhToan.TIEN_MAT;
                if (phuongThucThanhToanStr != null && phuongThucThanhToanStr.equals("CHUYEN_KHOAN")) {
                    phuongThucThanhToan = HoaDon.PhuongThucThanhToan.CHUYEN_KHOAN;
                }
                
                // Tạo đối tượng DoAn
                DoAn doAn = null;
                if (maDoAn != null && !maDoAn.trim().isEmpty()) {
                    doAn = new DoAn();
                    doAn.setMaDoAn(maDoAn);
                }
                
                hoaDon = new HoaDon(maHoaDon, doAn, tongTien, thoiGianThanhToan, phuongThucThanhToan, CCCD);
            }

            reset.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi lấy hóa đơn theo mã: " + e);
            e.printStackTrace();
        }
        return hoaDon;
    }
    
    public List<HoaDon> getHoaDonByCCCD(String CCCD) {
        Connection conn = null;
        List<HoaDon> danhSachHoaDon = new ArrayList<>();
        try {
            conn = mydb.getOnlyConn();
            
            String sql = "SELECT * FROM hoadon WHERE CCCD = ? ORDER BY thoiGianThanhToan DESC";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, CCCD);
            ResultSet reset = pstmt.executeQuery();
            
            while (reset.next()) {
                String maHoaDon = reset.getString("maHoaDon");
                String maDoAn = reset.getString("maDoAn");
                int tongTien = reset.getInt("tongTien");
                String thoiGianThanhToanStr = reset.getString("thoiGianThanhToan");
                String phuongThucThanhToanStr = reset.getString("phuongThucThanhToan");
                
                // Parse thời gian thanh toán
                LocalDateTime thoiGianThanhToan = null;
                if (thoiGianThanhToanStr != null && !thoiGianThanhToanStr.trim().isEmpty()) {
                    try {
                        thoiGianThanhToan = LocalDateTime.parse(thoiGianThanhToanStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    } catch (Exception e) {
                        System.out.println("Lỗi parse thời gian: " + e.getMessage());
                    }
                }
                
                // Parse phương thức thanh toán
                HoaDon.PhuongThucThanhToan phuongThucThanhToan = HoaDon.PhuongThucThanhToan.TIEN_MAT;
                if (phuongThucThanhToanStr != null && phuongThucThanhToanStr.equals("CHUYEN_KHOAN")) {
                    phuongThucThanhToan = HoaDon.PhuongThucThanhToan.CHUYEN_KHOAN;
                }
                
                // Tạo đối tượng DoAn
                DoAn doAn = null;
                if (maDoAn != null && !maDoAn.trim().isEmpty()) {
                    doAn = new DoAn();
                    doAn.setMaDoAn(maDoAn);
                }
                
                HoaDon hoaDon = new HoaDon(maHoaDon, doAn, tongTien, thoiGianThanhToan, phuongThucThanhToan, CCCD);
                danhSachHoaDon.add(hoaDon);
            }

            reset.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi lấy hóa đơn theo CCCD: " + e);
            e.printStackTrace();
        }
        return danhSachHoaDon;
    }
    
    public boolean createHoaDon(HoaDon hoaDon) {
        Connection conn = null;
        try {
            conn = mydb.getOnlyConn();
            
            String sql = "INSERT INTO hoadon (maHoaDon, maDoAn, tongTien, thoiGianThanhToan, phuongThucThanhToan, CCCD) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, hoaDon.getMaHoaDon());
            pstmt.setString(2, hoaDon.getDoAn() != null ? hoaDon.getDoAn().getMaDoAn() : null);
            pstmt.setInt(3, hoaDon.getTongTien());
            pstmt.setString(4, hoaDon.getThoiGianThanhToan() != null ? hoaDon.getThoiGianThanhToan().toString() : null);
            pstmt.setString(5, hoaDon.getPhuongThucThanhToan().toString());
            pstmt.setString(6, hoaDon.getCCCD());
            
            int result = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            
            if (result > 0) {
                System.out.println("Tạo hóa đơn thành công: " + hoaDon.getMaHoaDon());
                return true;
            } else {
                System.out.println("Lỗi khi tạo hóa đơn");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Lỗi tạo hóa đơn: " + e);
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateHoaDon(String maHoaDon, HoaDon hoaDon) {
        Connection conn = null;
        try {
            conn = mydb.getOnlyConn();
            
            String sql = "UPDATE hoadon SET maDoAn = ?, tongTien = ?, thoiGianThanhToan = ?, phuongThucThanhToan = ?, CCCD = ? WHERE maHoaDon = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, hoaDon.getDoAn() != null ? hoaDon.getDoAn().getMaDoAn() : null);
            pstmt.setInt(2, hoaDon.getTongTien());
            pstmt.setString(3, hoaDon.getThoiGianThanhToan() != null ? hoaDon.getThoiGianThanhToan().toString() : null);
            pstmt.setString(4, hoaDon.getPhuongThucThanhToan().toString());
            pstmt.setString(5, hoaDon.getCCCD());
            pstmt.setString(6, maHoaDon);
            
            int result = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            
            if (result > 0) {
                System.out.println("Cập nhật hóa đơn thành công: " + maHoaDon);
                return true;
            } else {
                System.out.println("Lỗi khi cập nhật hóa đơn");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Lỗi cập nhật hóa đơn: " + e);
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteHoaDon(String maHoaDon) {
        Connection conn = null;
        try {
            conn = mydb.getOnlyConn();
            
            String sql = "DELETE FROM hoadon WHERE maHoaDon = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maHoaDon);
            
            int result = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            
            if (result > 0) {
                System.out.println("Xóa hóa đơn thành công: " + maHoaDon);
                return true;
            } else {
                System.out.println("Lỗi khi xóa hóa đơn");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Lỗi xóa hóa đơn: " + e);
            e.printStackTrace();
            return false;
        }
    }
    
    public int getTotalHoaDon() {
        Connection conn = null;
        int total = 0;
        try {
            conn = mydb.getOnlyConn();
            
            String sql = "SELECT COUNT(*) as total FROM hoadon";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet reset = pstmt.executeQuery();
            
            if (reset.next()) {
                total = reset.getInt("total");
            }

            reset.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi đếm tổng hóa đơn: " + e);
            e.printStackTrace();
        }
        return total;
    }
    
    public List<HoaDon> searchHoaDonByDateRange(LocalDateTime fromDate, LocalDateTime toDate) {
        Connection conn = null;
        List<HoaDon> danhSachHoaDon = new ArrayList<>();
        try {
            conn = mydb.getOnlyConn();
            
            String sql = "SELECT * FROM hoadon WHERE thoiGianThanhToan BETWEEN ? AND ? ORDER BY thoiGianThanhToan DESC";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, fromDate.toString());
            pstmt.setString(2, toDate.toString());
            ResultSet reset = pstmt.executeQuery();
            
            while (reset.next()) {
                String maHoaDon = reset.getString("maHoaDon");
                String maDoAn = reset.getString("maDoAn");
                int tongTien = reset.getInt("tongTien");
                String thoiGianThanhToanStr = reset.getString("thoiGianThanhToan");
                String phuongThucThanhToanStr = reset.getString("phuongThucThanhToan");
                String CCCD = reset.getString("CCCD");
                
                // Parse thời gian thanh toán
                LocalDateTime thoiGianThanhToan = null;
                if (thoiGianThanhToanStr != null && !thoiGianThanhToanStr.trim().isEmpty()) {
                    try {
                        thoiGianThanhToan = LocalDateTime.parse(thoiGianThanhToanStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    } catch (Exception e) {
                        System.out.println("Lỗi parse thời gian: " + e.getMessage());
                    }
                }
                
                // Parse phương thức thanh toán
                HoaDon.PhuongThucThanhToan phuongThucThanhToan = HoaDon.PhuongThucThanhToan.TIEN_MAT;
                if (phuongThucThanhToanStr != null && phuongThucThanhToanStr.equals("CHUYEN_KHOAN")) {
                    phuongThucThanhToan = HoaDon.PhuongThucThanhToan.CHUYEN_KHOAN;
                }
                
                // Tạo đối tượng DoAn
                DoAn doAn = null;
                if (maDoAn != null && !maDoAn.trim().isEmpty()) {
                    doAn = new DoAn();
                    doAn.setMaDoAn(maDoAn);
                }
                
                HoaDon hoaDon = new HoaDon(maHoaDon, doAn, tongTien, thoiGianThanhToan, phuongThucThanhToan, CCCD);
                danhSachHoaDon.add(hoaDon);
            }

            reset.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi tìm kiếm hóa đơn theo khoảng thời gian: " + e);
            e.printStackTrace();
        }
        return danhSachHoaDon;
    }
}
