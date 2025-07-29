import java.time.LocalDateTime;
import java.util.ArrayList;

public class testDateTimeUtilsExample {
    
    // ===== VÍ DỤ SỬ DỤNG DATETIMEUTILS =====
    
    public void testSuatChieuWithDateTimeUtils() {
        System.out.println("=== VÍ DỤ SỬ DỤNG DATETIMEUTILS ===");
        
        // ✅ CÁCH ĐÚNG: Sử dụng DateTimeUtils
        LocalDateTime startTime = DateTimeUtils.parseVietDateTime("20:00 25/12/2024");
        LocalDateTime endTime = DateTimeUtils.parseVietDateTime("22:30 25/12/2024");
        
        SuatChieu suatChieu = new SuatChieu("SC001", "P001", "F001", startTime, endTime);
        boolean result = SuatChieuController.taoSuatChieu(suatChieu);
        assert result : "Tạo suất chiếu thành công với DateTimeUtils";
        
        // Hiển thị thời gian theo định dạng Việt Nam
        String startTimeFormatted = DateTimeUtils.formatVietDateTime(startTime);
        String endTimeFormatted = DateTimeUtils.formatVietDateTime(endTime);
        System.out.println("Thời gian bắt đầu: " + startTimeFormatted);
        System.out.println("Thời gian kết thúc: " + endTimeFormatted);
        
        // Dọn dẹp
        SuatChieuController.xoaSuatChieu("SC001");
        
        System.out.println("✅ Test DateTimeUtils PASSED");
    }
    
    public void testValidationWithDateTimeUtils() {
        System.out.println("=== TEST VALIDATION VỚI DATETIMEUTILS ===");
        
        // Test thời gian không hợp lệ
        LocalDateTime invalidTime = DateTimeUtils.parseVietDateTime("25:00 32/13/2024");
        assert invalidTime == null : "Thời gian không hợp lệ phải trả về null";
        
        // Test thời gian hợp lệ
        LocalDateTime validTime = DateTimeUtils.parseVietDateTime("20:30 15/12/2024");
        assert validTime != null : "Thời gian hợp lệ phải không null";
        
        // Test format thời gian
        String formatted = DateTimeUtils.formatVietDateTime(validTime);
        assert "20:30 15/12/2024".equals(formatted) : "Format thời gian đúng";
        
        System.out.println("✅ Test Validation DateTimeUtils PASSED");
    }
    
    public void testEdgeCasesWithDateTimeUtils() {
        System.out.println("=== TEST EDGE CASES VỚI DATETIMEUTILS ===");
        
        // Test null input
        LocalDateTime nullResult = DateTimeUtils.parseVietDateTime(null);
        assert nullResult == null : "Null input phải trả về null";
        
        // Test empty string
        LocalDateTime emptyResult = DateTimeUtils.parseVietDateTime("");
        assert emptyResult == null : "Empty string phải trả về null";
        
        // Test sai format
        LocalDateTime wrongFormat = DateTimeUtils.parseVietDateTime("2024-12-25 20:00");
        assert wrongFormat == null : "Sai format phải trả về null";
        
        // Test đúng format
        LocalDateTime correctFormat = DateTimeUtils.parseVietDateTime("20:00 25/12/2024");
        assert correctFormat != null : "Đúng format phải không null";
        
        System.out.println("✅ Test Edge Cases DateTimeUtils PASSED");
    }
    
    public void testBusinessLogicWithDateTimeUtils() {
        System.out.println("=== TEST BUSINESS LOGIC VỚI DATETIMEUTILS ===");
        
        // Test thời gian bắt đầu > thời gian kết thúc
        LocalDateTime startTime = DateTimeUtils.parseVietDateTime("22:00 25/12/2024");
        LocalDateTime endTime = DateTimeUtils.parseVietDateTime("20:00 25/12/2024");
        
        SuatChieu invalidSuatChieu = new SuatChieu("SC002", "P001", "F001", startTime, endTime);
        boolean result = SuatChieuController.taoSuatChieu(invalidSuatChieu);
        assert !result : "Không thể tạo suất chiếu với thời gian bắt đầu > kết thúc";
        
        // Test thời gian hợp lệ
        LocalDateTime validStart = DateTimeUtils.parseVietDateTime("20:00 25/12/2024");
        LocalDateTime validEnd = DateTimeUtils.parseVietDateTime("22:30 25/12/2024");
        
        SuatChieu validSuatChieu = new SuatChieu("SC003", "P001", "F001", validStart, validEnd);
        boolean validResult = SuatChieuController.taoSuatChieu(validSuatChieu);
        assert validResult : "Tạo suất chiếu với thời gian hợp lệ thành công";
        
        // Dọn dẹp
        SuatChieuController.xoaSuatChieu("SC003");
        
        System.out.println("✅ Test Business Logic DateTimeUtils PASSED");
    }
    
    public void testPerformanceWithDateTimeUtils() {
        System.out.println("=== TEST PERFORMANCE VỚI DATETIMEUTILS ===");
        
        long startTime = System.currentTimeMillis();
        
        // Tạo 100 suất chiếu với DateTimeUtils
        for (int i = 1; i <= 100; i++) {
            LocalDateTime start = DateTimeUtils.parseVietDateTime("20:00 25/12/2024");
            LocalDateTime end = DateTimeUtils.parseVietDateTime("22:30 25/12/2024");
            
            SuatChieu suatChieu = new SuatChieu("PERF" + i, "P001", "F001", start, end);
            SuatChieuController.taoSuatChieu(suatChieu);
        }
        
        long createTime = System.currentTimeMillis() - startTime;
        System.out.println("Thời gian tạo 100 suất chiếu với DateTimeUtils: " + createTime + "ms");
        
        // Dọn dẹp
        for (int i = 1; i <= 100; i++) {
            SuatChieuController.xoaSuatChieu("PERF" + i);
        }
        
        assert createTime < 5000 : "Tạo suất chiếu với DateTimeUtils quá chậm";
        
        System.out.println("✅ Test Performance DateTimeUtils PASSED");
    }
    
    // ===== SO SÁNH CÁCH SỬ DỤNG =====
    
    public void compareDateTimeUsage() {
        System.out.println("=== SO SÁNH CÁCH SỬ DỤNG DATETIME ===");
        
        // ❌ CÁCH CŨ: Sử dụng LocalDateTime.of() (khó đọc)
        LocalDateTime oldWay = LocalDateTime.of(2024, 12, 25, 20, 0);
        System.out.println("Cách cũ: LocalDateTime.of(2024, 12, 25, 20, 0)");
        System.out.println("Kết quả: " + oldWay);
        
        // ✅ CÁCH MỚI: Sử dụng DateTimeUtils (dễ đọc, thân thiện người dùng)
        LocalDateTime newWay = DateTimeUtils.parseVietDateTime("20:00 25/12/2024");
        System.out.println("Cách mới: DateTimeUtils.parseVietDateTime(\"20:00 25/12/2024\")");
        System.out.println("Kết quả: " + newWay);
        
        // Format theo định dạng Việt Nam
        String vietFormat = DateTimeUtils.formatVietDateTime(newWay);
        System.out.println("Format Việt Nam: " + vietFormat);
        
        assert oldWay.equals(newWay) : "Hai cách phải cho kết quả giống nhau";
        
        System.out.println("✅ So sánh DateTime usage PASSED");
    }
    
    // ===== MAIN TEST METHOD =====
    
    public void test() {
        System.out.println("🕐 BẮT ĐẦU TEST DATETIMEUTILS 🕐");
        
        try {
            testSuatChieuWithDateTimeUtils();
            testValidationWithDateTimeUtils();
            testEdgeCasesWithDateTimeUtils();
            testBusinessLogicWithDateTimeUtils();
            testPerformanceWithDateTimeUtils();
            compareDateTimeUsage();
            
            System.out.println("🎉 TẤT CẢ TEST DATETIMEUTILS PASSED! 🎉");
            
        } catch (Exception e) {
            System.err.println("❌ TEST DATETIMEUTILS FAILED: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 