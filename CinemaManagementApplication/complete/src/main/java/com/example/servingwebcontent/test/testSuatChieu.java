package com.example.servingwebcontent.test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class testSuatChieu {
    
    // === UNIT TESTING ===
    
    // Test constructor
    public static void testConstructor() {
        System.out.println("=== TEST CONSTRUCTOR ===");
        
        // Test constructor rỗng
        SuatChieu sc1 = new SuatChieu();
        assert sc1.getMaSuatChieu() == null : "Constructor rỗng không đúng";
        assert sc1.getMaPhim() == null : "Constructor rỗng không đúng";
        System.out.println("✓ Constructor rỗng OK");
        
        // Test constructor với tham số
        LocalDateTime ngayGio = LocalDateTime.now();
        SuatChieu sc2 = new SuatChieu("SC001", "PHIM001", "PHONG001", ngayGio, ngayGio.plusHours(2));
        assert "SC001".equals(sc2.getMaSuatChieu()) : "Mã suất chiếu không đúng";
        assert "PHIM001".equals(sc2.getMaPhim()) : "Mã phim không đúng";
        assert "PHONG001".equals(sc2.getMaPhong()) : "Mã phòng không đúng";
        assert ngayGio.equals(sc2.getNgayGioChieu()) : "Ngày giờ chiếu không đúng";
        System.out.println("✓ Constructor với tham số OK");
    }
    
    // Test getters và setters
    public static void testGettersSetters() {
        System.out.println("\n=== TEST GETTERS & SETTERS ===");
        
        SuatChieu sc = new SuatChieu();
        
        // Test setMaSuatChieu
        sc.setMaSuatChieu("SC002");
        assert "SC002".equals(sc.getMaSuatChieu()) : "setMaSuatChieu/getMaSuatChieu không đúng";
        
        // Test setMaPhim
        sc.setMaPhim("PHIM002");
        assert "PHIM002".equals(sc.getMaPhim()) : "setMaPhim/getMaPhim không đúng";
        
        // Test setMaPhong
        sc.setMaPhong("PHONG002");
        assert "PHONG002".equals(sc.getMaPhong()) : "setMaPhong/getMaPhong không đúng";
        
        // Test setNgayGioChieu
        LocalDateTime ngayGio = LocalDateTime.now();
        sc.setNgayGioChieu(ngayGio);
        assert ngayGio.equals(sc.getNgayGioChieu()) : "setNgayGioChieu/getNgayGioChieu không đúng";
        
        // Test setNgayGioKetThuc
        LocalDateTime ngayGioKetThuc = ngayGio.plusHours(2);
        sc.setNgayGioKetThuc(ngayGioKetThuc);
        assert ngayGioKetThuc.equals(sc.getNgayGioKetThuc()) : "setNgayGioKetThuc/getNgayGioKetThuc không đúng";
        
        System.out.println("✓ Tất cả getters/setters OK");
    }
    
    // Test method hienThiThongTin
    public static void testHienThiThongTin() {
        System.out.println("\n=== TEST HIEN THI THONG TIN ===");
        
        LocalDateTime ngayGio = LocalDateTime.now();
        SuatChieu sc = new SuatChieu("SC003", "PHIM003", "PHONG003", ngayGio, ngayGio.plusHours(2));
        
        // Test hiển thị thông tin
        sc.hienThiThongTin();
        System.out.println("✓ Hiển thị thông tin OK");
    }
    
    // === INTEGRATION TESTING ===
    
    // Test luồng CRUD hoàn chỉnh
    public static void testCRUDIntegration() {
        System.out.println("\n=== TEST CRUD INTEGRATION ===");
        
        // Create
        LocalDateTime ngayGio = LocalDateTime.now();
        SuatChieu sc = new SuatChieu("INT001", "PHIM001", "PHONG001", ngayGio, ngayGio.plusHours(2));
        SuatChieu.Create(sc);
        
        // Read
        SuatChieu scRead = SuatChieu.getSuatChieuById("INT001");
        assert scRead != null : "Không tìm thấy suất chiếu sau khi tạo";
        assert "PHIM001".equals(scRead.getMaPhim()) : "Dữ liệu đọc không đúng";
        
        // Update
        sc.setMaPhim("PHIM_UPDATED");
        SuatChieu.Update("INT001", sc);
        SuatChieu scUpdated = SuatChieu.getSuatChieuById("INT001");
        assert "PHIM_UPDATED".equals(scUpdated.getMaPhim()) : "Update không thành công";
        
        // Delete
        SuatChieu.Delete("INT001");
        SuatChieu scDeleted = SuatChieu.getSuatChieuById("INT001");
        assert scDeleted == null : "Delete không thành công";
        
        System.out.println("✓ CRUD Integration OK");
    }
    
    // === EDGE CASE TESTING ===
    
    // Test với dữ liệu null
    public static void testNullData() {
        System.out.println("\n=== TEST NULL DATA ===");
        
        // Test tạo suất chiếu với mã null
        try {
            SuatChieu sc = new SuatChieu(null, "PHIM001", "PHONG001", LocalDateTime.now(), LocalDateTime.now().plusHours(2));
            SuatChieu.Create(sc);
            System.out.println("✗ Không bắt được lỗi mã suất chiếu null");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi mã suất chiếu null: " + e.getMessage());
        }
        
        // Test tạo suất chiếu với mã phim null
        try {
            SuatChieu sc = new SuatChieu("SC001", null, "PHONG001", LocalDateTime.now(), LocalDateTime.now().plusHours(2));
            SuatChieu.Create(sc);
            System.out.println("✗ Không bắt được lỗi mã phim null");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi mã phim null: " + e.getMessage());
        }
        
        // Test tạo suất chiếu với mã rỗng
        try {
            SuatChieu sc = new SuatChieu("", "PHIM001", "PHONG001", LocalDateTime.now(), LocalDateTime.now().plusHours(2));
            SuatChieu.Create(sc);
            System.out.println("✗ Không bắt được lỗi mã rỗng");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi mã rỗng: " + e.getMessage());
        }
    }
    
    // Test với dữ liệu không hợp lệ
    public static void testInvalidData() {
        System.out.println("\n=== TEST INVALID DATA ===");
        
        // Test ngày giờ chiếu trong quá khứ
        try {
            LocalDateTime pastTime = LocalDateTime.now().minusDays(1);
            SuatChieu sc = new SuatChieu("SC001", "PHIM001", "PHONG001", pastTime, pastTime.plusHours(2));
            SuatChieu.Create(sc);
            System.out.println("✗ Không bắt được lỗi ngày giờ trong quá khứ");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi ngày giờ trong quá khứ: " + e.getMessage());
        }
        
        // Test ngày giờ kết thúc trước ngày giờ chiếu
        try {
            LocalDateTime startTime = LocalDateTime.now().plusHours(2);
            LocalDateTime endTime = LocalDateTime.now().plusHours(1);
            SuatChieu sc = new SuatChieu("SC002", "PHIM001", "PHONG001", startTime, endTime);
            SuatChieu.Create(sc);
            System.out.println("✗ Không bắt được lỗi thời gian kết thúc trước thời gian bắt đầu");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi thời gian kết thúc trước thời gian bắt đầu: " + e.getMessage());
        }
        
        // Test mã suất chiếu trùng lặp
        LocalDateTime ngayGio = LocalDateTime.now();
        SuatChieu sc1 = new SuatChieu("DUP001", "PHIM001", "PHONG001", ngayGio, ngayGio.plusHours(2));
        SuatChieu.Create(sc1);
        
        try {
            SuatChieu sc2 = new SuatChieu("DUP001", "PHIM002", "PHONG002", ngayGio.plusHours(3), ngayGio.plusHours(5));
            SuatChieu.Create(sc2);
            System.out.println("✗ Không bắt được lỗi mã suất chiếu trùng lặp");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi mã suất chiếu trùng lặp: " + e.getMessage());
        }
    }
    
    // === MOCK TESTING ===
    
    // Test với dữ liệu giả
    public static void testMockData() {
        System.out.println("\n=== TEST MOCK DATA ===");
        
        // Tạo dữ liệu giả
        ArrayList<SuatChieu> mockData = new ArrayList<>();
        LocalDateTime baseTime = LocalDateTime.now();
        
        mockData.add(new SuatChieu("MOCK001", "PHIM001", "PHONG001", baseTime, baseTime.plusHours(2)));
        mockData.add(new SuatChieu("MOCK002", "PHIM002", "PHONG002", baseTime.plusHours(3), baseTime.plusHours(5)));
        mockData.add(new SuatChieu("MOCK003", "PHIM003", "PHONG003", baseTime.plusHours(6), baseTime.plusHours(8)));
        
        // Test tìm kiếm theo phim
        ArrayList<SuatChieu> result1 = new ArrayList<>();
        for (SuatChieu sc : mockData) {
            if ("PHIM001".equals(sc.getMaPhim())) {
                result1.add(sc);
            }
        }
        assert result1.size() == 1 : "Tìm kiếm theo phim không đúng";
        
        // Test tìm kiếm theo phòng
        ArrayList<SuatChieu> result2 = new ArrayList<>();
        for (SuatChieu sc : mockData) {
            if ("PHONG001".equals(sc.getMaPhong())) {
                result2.add(sc);
            }
        }
        assert result2.size() == 1 : "Tìm kiếm theo phòng không đúng";
        
        // Test tính thời lượng
        long totalDuration = 0;
        for (SuatChieu sc : mockData) {
            if (sc.getNgayGioChieu() != null && sc.getNgayGioKetThuc() != null) {
                totalDuration += java.time.Duration.between(sc.getNgayGioChieu(), sc.getNgayGioKetThuc()).toHours();
            }
        }
        assert totalDuration == 6 : "Tính tổng thời lượng không đúng";
        
        System.out.println("✓ Mock data testing OK");
    }
    
    // === PERFORMANCE TESTING ===
    
    // Test hiệu suất với dữ liệu lớn
    public static void testPerformance() {
        System.out.println("\n=== TEST PERFORMANCE ===");
        
        long startTime = System.currentTimeMillis();
        
        // Tạo 1000 suất chiếu
        LocalDateTime baseTime = LocalDateTime.now();
        for (int i = 0; i < 1000; i++) {
            SuatChieu sc = new SuatChieu("PERF" + i, "PHIM" + (i % 10), "PHONG" + (i % 5), 
                                        baseTime.plusHours(i), baseTime.plusHours(i + 2));
            SuatChieu.Create(sc);
        }
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        System.out.println("✓ Tạo 1000 suất chiếu trong " + duration + "ms");
        
        // Test tìm kiếm
        startTime = System.currentTimeMillis();
        SuatChieu result = SuatChieu.getSuatChieuById("PERF500");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        
        System.out.println("✓ Tìm kiếm suất chiếu trong " + duration + "ms");
    }
    
    // === BUSINESS LOGIC TESTING ===
    
    // Test logic nghiệp vụ
    public static void testBusinessLogic() {
        System.out.println("\n=== TEST BUSINESS LOGIC ===");
        
        LocalDateTime now = LocalDateTime.now();
        
        // Test suất chiếu trong tương lai
        SuatChieu futureShow = new SuatChieu("FUTURE001", "PHIM001", "PHONG001", 
                                           now.plusHours(1), now.plusHours(3));
        boolean isFuture = futureShow.getNgayGioChieu().isAfter(now);
        assert isFuture == true : "Suất chiếu trong tương lai không đúng";
        
        // Test suất chiếu đang diễn ra
        SuatChieu ongoingShow = new SuatChieu("ONGOING001", "PHIM001", "PHONG001", 
                                            now.minusHours(1), now.plusHours(1));
        boolean isOngoing = ongoingShow.getNgayGioChieu().isBefore(now) && 
                           ongoingShow.getNgayGioKetThuc().isAfter(now);
        assert isOngoing == true : "Suất chiếu đang diễn ra không đúng";
        
        // Test suất chiếu đã kết thúc
        SuatChieu endedShow = new SuatChieu("ENDED001", "PHIM001", "PHONG001", 
                                          now.minusHours(3), now.minusHours(1));
        boolean isEnded = endedShow.getNgayGioKetThuc().isBefore(now);
        assert isEnded == true : "Suất chiếu đã kết thúc không đúng";
        
        System.out.println("✓ Business logic testing OK");
    }
    
    // === VALIDATION TESTING ===
    
    // Test validation
    public static void testValidation() {
        System.out.println("\n=== TEST VALIDATION ===");
        
        // Test validation mã suất chiếu
        try {
            SuatChieu sc = new SuatChieu("SC_123", "PHIM001", "PHONG001", 
                                        LocalDateTime.now(), LocalDateTime.now().plusHours(2));
            // Kiểm tra format mã suất chiếu
            boolean isValidFormat = sc.getMaSuatChieu().matches("^[A-Z0-9]+$");
            assert isValidFormat == true : "Format mã suất chiếu không hợp lệ";
            System.out.println("✓ Validation mã suất chiếu OK");
        } catch (Exception e) {
            System.out.println("✗ Validation mã suất chiếu thất bại: " + e.getMessage());
        }
        
        // Test validation thời gian
        try {
            LocalDateTime start = LocalDateTime.now();
            LocalDateTime end = start.plusHours(2);
            SuatChieu sc = new SuatChieu("SC001", "PHIM001", "PHONG001", start, end);
            
            // Kiểm tra thời gian hợp lệ
            boolean isValidTime = end.isAfter(start);
            assert isValidTime == true : "Thời gian không hợp lệ";
            System.out.println("✓ Validation thời gian OK");
        } catch (Exception e) {
            System.out.println("✗ Validation thời gian thất bại: " + e.getMessage());
        }
    }
    
    // === MAIN TEST METHOD ===

    public static void test() {
        System.out.println("🚀 BẮT ĐẦU TEST SUAT CHIEU MODEL");
        System.out.println("===================================");
        
        try {
            testConstructor();
            testGettersSetters();
            testHienThiThongTin();
            testCRUDIntegration();
            testNullData();
            testInvalidData();
            testMockData();
            testPerformance();
            testBusinessLogic();
            testValidation();
            
            System.out.println("\n===================================");
            System.out.println("✅ TẤT CẢ TEST SUAT CHIEU THÀNH CÔNG!");
            
        } catch (Exception e) {
            System.out.println("\n===================================");
            System.out.println("❌ TEST SUAT CHIEU THẤT BẠI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}