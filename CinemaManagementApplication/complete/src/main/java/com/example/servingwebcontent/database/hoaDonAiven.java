package com.example.servingwebcontent.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import com.example.servingwebcontent.model.HoaDon;
import com.example.servingwebcontent.model.DoAn;
import com.example.servingwebcontent.model.Ve;
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
        List<HoaDon> danhSachHoaDon = new ArrayList<>();
        try (Connection conn = mydb.getOnlyConn();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM hoadon ORDER BY thoiGianThanhToan DESC")) {
            
            // Tạo bảng nếu chưa tồn tại
            createTableIfNotExists(conn);
            
            ResultSet reset = pstmt.executeQuery();
            
            System.out.println("Lấy tất cả dữ liệu hóa đơn từ database: ");
            while (reset.next()) {
                String maHoaDon = reset.getString("maHoaDon");
                String maDoAn = reset.getString("maDoAn");
                int tongTien = reset.getInt("tongTien");
                String thoiGianThanhToanStr = reset.getString("thoiGianThanhToan");
                String phuongThucStr = reset.getString("phuongThuc");
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
                HoaDon.phuongThuc phuongThuc = HoaDon.phuongThuc.TIEN_MAT;
                if (phuongThucStr != null && phuongThucStr.equals("CHUYEN_KHOAN")) {
                    phuongThuc = HoaDon.phuongThuc.CHUYEN_KHOAN;
                }
                
                // Tạo đối tượng DoAn (có thể null nếu không có đồ ăn)
                List<DoAn> danhSachDoAn = new ArrayList<>();
                if (maDoAn != null && !maDoAn.trim().isEmpty()) {
                    // Cần tạo DoAnAiven để lấy thông tin đồ ăn
                    // Tạm thời tạo DoAn với thông tin cơ bản
                    DoAn doAn = new DoAn();
                    doAn.setMaDoAn(maDoAn);
                    danhSachDoAn.add(doAn);
                }
                
                // Lấy danh sách vé cho hóa đơn này
                List<Ve> danhSachVe = getVeByHoaDon(conn, maHoaDon);
                
                HoaDon hoaDon = new HoaDon(maHoaDon, danhSachVe, danhSachDoAn, tongTien, thoiGianThanhToan, phuongThuc, CCCD);
                danhSachHoaDon.add(hoaDon);
                System.out.println("Mã HD: " + maHoaDon + " | CCCD: " + CCCD + " | Tổng tiền: " + tongTien + " | Số vé: " + danhSachVe.size());
            }
        } catch (Exception e) {
            System.out.println("Lỗi lấy dữ liệu hóa đơn: " + e);
            e.printStackTrace();
        }
        return danhSachHoaDon;
    }
    
    // Phương thức lấy vé theo mã hóa đơn
    private List<Ve> getVeByHoaDon(Connection conn, String maHoaDon) {
        List<Ve> danhSachVe = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM ve WHERE maHoaDon = ?")) {
            pstmt.setString(1, maHoaDon);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                String maVe = rs.getString("maVe");
                String CCCD = rs.getString("CCCD");
                String maSuatChieu = rs.getString("maSuatChieu");
                String maGhe = rs.getString("maGhe");
                int giaVe = rs.getInt("giaVe");
                
                Ve ve = new Ve(maVe, CCCD, maSuatChieu, maGhe, giaVe);
                danhSachVe.add(ve);
            }
        } catch (Exception e) {
            System.out.println("Lỗi lấy vé cho hóa đơn " + maHoaDon + ": " + e.getMessage());
        }
        return danhSachVe;
    }
    
    private void createTableIfNotExists(Connection conn) {
        try {
            // Cập nhật bảng hoadon để hỗ trợ vé
            String createTableSQL = "CREATE TABLE IF NOT EXISTS hoadon (" +
                "maHoaDon VARCHAR(20) PRIMARY KEY," +
                "maDoAn VARCHAR(20)," +
                "tongTien INT NOT NULL," +
                "thoiGianThanhToan DATETIME," +
                "phuongThuc VARCHAR(20)," +
                "CCCD VARCHAR(20)," +
                "FOREIGN KEY (CCCD) REFERENCES khachhang(CCCD)" +
                ")";
            
            PreparedStatement pstmt = conn.prepareStatement(createTableSQL);
            pstmt.executeUpdate();
            pstmt.close();
            
            // Tạo bảng ve nếu chưa tồn tại
            String createVeTableSQL = "CREATE TABLE IF NOT EXISTS ve (" +
                "maVe VARCHAR(20) PRIMARY KEY," +
                "CCCD VARCHAR(20)," +
                "maSuatChieu VARCHAR(20)," +
                "maGhe VARCHAR(20)," +
                "giaVe INT NOT NULL," +
                "trangThai VARCHAR(20) DEFAULT 'CHUA_THANH_TOAN'," +
                "maHoaDon VARCHAR(20)," +
                "FOREIGN KEY (CCCD) REFERENCES khachhang(CCCD)," +
                "FOREIGN KEY (maHoaDon) REFERENCES hoadon(maHoaDon)" +
                ")";
            
            PreparedStatement pstmtVe = conn.prepareStatement(createVeTableSQL);
            pstmtVe.executeUpdate();
            pstmtVe.close();
            
            System.out.println("Bảng hoadon và ve đã được tạo hoặc đã tồn tại");
        } catch (Exception e) {
            System.out.println("Lỗi tạo bảng: " + e.getMessage());
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
                String phuongThucStr = reset.getString("phuongThuc");
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
                HoaDon.phuongThuc phuongThuc = HoaDon.phuongThuc.TIEN_MAT;
                if (phuongThucStr != null && phuongThucStr.equals("CHUYEN_KHOAN")) {
                    phuongThuc = HoaDon.phuongThuc.CHUYEN_KHOAN;
                }
                
                // Tạo đối tượng DoAn
                List<DoAn> danhSachDoAn = new ArrayList<>();
                if (maDoAn != null && !maDoAn.trim().isEmpty()) {
                    DoAn doAn = new DoAn();
                    doAn.setMaDoAn(maDoAn);
                    danhSachDoAn.add(doAn);
                }
                
                // Lấy danh sách vé cho hóa đơn này
                List<Ve> danhSachVe = getVeByHoaDon(conn, maHoaDon);
                
                hoaDon = new HoaDon(maHoaDon, danhSachVe, danhSachDoAn, tongTien, thoiGianThanhToan, phuongThuc, CCCD);
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
                String phuongThucStr = reset.getString("phuongThuc");
                
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
                HoaDon.phuongThuc phuongThuc = HoaDon.phuongThuc.TIEN_MAT;
                if (phuongThucStr != null && phuongThucStr.equals("CHUYEN_KHOAN")) {
                    phuongThuc = HoaDon.phuongThuc.CHUYEN_KHOAN;
                }
                
                // Tạo đối tượng DoAn
                List<DoAn> danhSachDoAn = new ArrayList<>();
                if (maDoAn != null && !maDoAn.trim().isEmpty()) {
                    DoAn doAn = new DoAn();
                    doAn.setMaDoAn(maDoAn);
                    danhSachDoAn.add(doAn);
                }
                
                // Lấy danh sách vé cho hóa đơn này
                List<Ve> danhSachVe = getVeByHoaDon(conn, maHoaDon);
                
                HoaDon hoaDon = new HoaDon(maHoaDon, danhSachVe, danhSachDoAn, tongTien, thoiGianThanhToan, phuongThuc, CCCD);
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
            conn.setAutoCommit(false); // Bắt đầu transaction
            
            // 1. Tạo hóa đơn
            String sql = "INSERT INTO hoadon (maHoaDon, maDoAn, tongTien, thoiGianThanhToan, phuongThuc, CCCD) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, hoaDon.getMaHoaDon());
            pstmt.setString(2, hoaDon.getDoAn() != null ? hoaDon.getDoAn().getMaDoAn() : null);
            pstmt.setInt(3, hoaDon.getTongTien());
            pstmt.setString(4, hoaDon.getThoiGianThanhToan() != null ? hoaDon.getThoiGianThanhToan().toString() : null);
            pstmt.setString(5, hoaDon.getphuongThuc().toString());
            pstmt.setString(6, hoaDon.getCCCD());
            
            int result = pstmt.executeUpdate();
            pstmt.close();
            
            if (result <= 0) {
                conn.rollback();
                return false;
            }
            
            // 2. Lưu vé nếu có
            if (hoaDon.getDanhSachVe() != null && !hoaDon.getDanhSachVe().isEmpty()) {
                String veSql = "INSERT INTO ve (maVe, CCCD, maSuatChieu, maGhe, giaVe, trangThai, maHoaDon) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement vePstmt = conn.prepareStatement(veSql);
                
                for (Ve ve : hoaDon.getDanhSachVe()) {
                    vePstmt.setString(1, ve.getMaVe());
                    vePstmt.setString(2, ve.getCCCD());
                    vePstmt.setString(3, ve.getMaSuatChieu());
                    vePstmt.setString(4, ve.getMaGhe());
                    vePstmt.setInt(5, ve.getGiaVe());
                    vePstmt.setString(6, ve.getTrangThai().toString());
                    vePstmt.setString(7, hoaDon.getMaHoaDon());
                    
                    int veResult = vePstmt.executeUpdate();
                    if (veResult <= 0) {
                        conn.rollback();
                        vePstmt.close();
                        return false;
                    }
                }
                vePstmt.close();
            }
            
            conn.commit(); // Commit transaction
            conn.close();
            
            System.out.println("Tạo hóa đơn thành công: " + hoaDon.getMaHoaDon());
            return true;
            
        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback();
            } catch (Exception rollbackEx) {
                System.out.println("Lỗi rollback: " + rollbackEx.getMessage());
            }
            System.out.println("Lỗi tạo hóa đơn: " + e);
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateHoaDon(String maHoaDon, HoaDon hoaDon) {
        Connection conn = null;
        try {
            conn = mydb.getOnlyConn();
            
            String sql = "UPDATE hoadon SET maDoAn = ?, tongTien = ?, thoiGianThanhToan = ?, phuongThuc = ?, CCCD = ? WHERE maHoaDon = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, hoaDon.getDoAn() != null ? hoaDon.getDoAn().getMaDoAn() : null);
            pstmt.setInt(2, hoaDon.getTongTien());
            pstmt.setString(3, hoaDon.getThoiGianThanhToan() != null ? hoaDon.getThoiGianThanhToan().toString() : null);
            pstmt.setString(4, hoaDon.getphuongThuc().toString());
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
                String phuongThucStr = reset.getString("phuongThuc");
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
                HoaDon.phuongThuc phuongThuc = HoaDon.phuongThuc.TIEN_MAT;
                if (phuongThucStr != null && phuongThucStr.equals("CHUYEN_KHOAN")) {
                    phuongThuc = HoaDon.phuongThuc.CHUYEN_KHOAN;
                }
                
                // Tạo đối tượng DoAn
                List<DoAn> danhSachDoAn = new ArrayList<>();
                if (maDoAn != null && !maDoAn.trim().isEmpty()) {
                    DoAn doAn = new DoAn();
                    doAn.setMaDoAn(maDoAn);
                    danhSachDoAn.add(doAn);
                }
                
                // Lấy danh sách vé cho hóa đơn này
                List<Ve> danhSachVe = getVeByHoaDon(conn, maHoaDon);
                
                HoaDon hoaDon = new HoaDon(maHoaDon, danhSachVe, danhSachDoAn, tongTien, thoiGianThanhToan, phuongThuc, CCCD);
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
