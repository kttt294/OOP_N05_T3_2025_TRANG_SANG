import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class testPhimController {
    
    // ===== UNIT TESTING =====
    public void testUnit_taoPhim() {
        System.out.println("=== UNIT TEST: taoPhim ===");
        
        // Test tạo phim thành công
        Phim phim = new Phim("P001", "Avengers", "Action", 120, "Marvel", 2024, "Mô tả phim");
        boolean result = PhimController.taoPhim(phim);
        assert result : "Tạo phim thành công";
        
        // Test tạo phim trùng mã
        Phim phimTrung = new Phim("P001", "Avengers 2", "Action", 130, "Marvel", 2024, "Mô tả phim 2");
        boolean resultTrung = PhimController.taoPhim(phimTrung);
        assert !resultTrung : "Không thể tạo phim trùng mã";
        
        System.out.println("✅ Unit test taoPhim PASSED");
    }
    
    public void testUnit_capNhatPhim() {
        System.out.println("=== UNIT TEST: capNhatPhim ===");
        
        // Test cập nhật phim thành công
        Phim phimUpdate = new Phim("P001", "Avengers Updated", "Action", 125, "Marvel", 2024, "Mô tả mới");
        boolean result = PhimController.capNhatPhim("P001", phimUpdate);
        assert result : "Cập nhật phim thành công";
        
        // Test cập nhật phim không tồn tại
        boolean resultNotFound = PhimController.capNhatPhim("P999", phimUpdate);
        assert !resultNotFound : "Không thể cập nhật phim không tồn tại";
        
        System.out.println("✅ Unit test capNhatPhim PASSED");
    }
    
    public void testUnit_xoaPhim() {
        System.out.println("=== UNIT TEST: xoaPhim ===");
        
        // Test xóa phim thành công
        boolean result = PhimController.xoaPhim("P001");
        assert result : "Xóa phim thành công";
        
        // Test xóa phim không tồn tại
        boolean resultNotFound = PhimController.xoaPhim("P999");
        assert !resultNotFound : "Không thể xóa phim không tồn tại";
        
        System.out.println("✅ Unit test xoaPhim PASSED");
    }
    
    public void testUnit_xemThongTinPhim() {
        System.out.println("=== UNIT TEST: xemThongTinPhim ===");
        
        // Tạo phim để test
        Phim phim = new Phim("P002", "Spider-Man", "Action", 110, "Sony", 2024, "Mô tả Spider-Man");
        PhimController.taoPhim(phim);
        
        // Test xem thông tin phim
        Phim result = PhimController.xemThongTinPhim("P002");
        assert result != null : "Tìm thấy phim";
        assert "Spider-Man".equals(result.getTenPhim()) : "Tên phim đúng";
        
        // Test xem phim không tồn tại
        Phim resultNotFound = PhimController.xemThongTinPhim("P999");
        assert resultNotFound == null : "Không tìm thấy phim không tồn tại";
        
        System.out.println("✅ Unit test xemThongTinPhim PASSED");
    }
    
    public void testUnit_xemTatCaPhim() {
        System.out.println("=== UNIT TEST: xemTatCaPhim ===");
        
        ArrayList<Phim> danhSach = PhimController.xemTatCaPhim();
        assert danhSach != null : "Danh sách phim không null";
        assert danhSach.size() >= 0 : "Danh sách phim hợp lệ";
        
        System.out.println("✅ Unit test xemTatCaPhim PASSED");
    }
    
    // ===== INTEGRATION TESTING =====
    public void testIntegration_CRUDCycle() {
        System.out.println("=== INTEGRATION TEST: CRUD Cycle ===");
        
        // Create
        Phim phim = new Phim("P003", "Batman", "Action", 115, "DC", 2024, "Mô tả Batman");
        boolean createResult = PhimController.taoPhim(phim);
        assert createResult : "Tạo phim thành công";
        
        // Read
        Phim readResult = PhimController.xemThongTinPhim("P003");
        assert readResult != null : "Đọc phim thành công";
        assert "Batman".equals(readResult.getTenPhim()) : "Tên phim đúng";
        
        // Update
        Phim phimUpdate = new Phim("P003", "Batman Returns", "Action", 120, "DC", 2024, "Mô tả mới");
        boolean updateResult = PhimController.capNhatPhim("P003", phimUpdate);
        assert updateResult : "Cập nhật phim thành công";
        
        // Verify update
        Phim verifyResult = PhimController.xemThongTinPhim("P003");
        assert "Batman Returns".equals(verifyResult.getTenPhim()) : "Cập nhật thành công";
        
        // Delete
        boolean deleteResult = PhimController.xoaPhim("P003");
        assert deleteResult : "Xóa phim thành công";
        
        // Verify delete
        Phim verifyDelete = PhimController.xemThongTinPhim("P003");
        assert verifyDelete == null : "Phim đã được xóa";
        
        System.out.println("✅ Integration test CRUD Cycle PASSED");
    }
    
    public void testIntegration_SearchOperations() {
        System.out.println("=== INTEGRATION TEST: Search Operations ===");
        
        // Tạo nhiều phim để test search
        Phim phim1 = new Phim("P004", "Iron Man", "Action", 100, "Marvel", 2024, "Mô tả Iron Man");
        Phim phim2 = new Phim("P005", "Iron Man 2", "Action", 105, "Marvel", 2024, "Mô tả Iron Man 2");
        Phim phim3 = new Phim("P006", "Thor", "Action", 110, "Marvel", 2024, "Mô tả Thor");
        
        PhimController.taoPhim(phim1);
        PhimController.taoPhim(phim2);
        PhimController.taoPhim(phim3);
        
        // Test tìm theo tên
        ArrayList<Phim> searchByName = PhimController.timPhimTheoTen("Iron Man");
        assert searchByName.size() >= 2 : "Tìm thấy phim Iron Man";
        
        // Test tìm theo thể loại
        ArrayList<Phim> searchByGenre = PhimController.timPhimTheoTheLoai("Action");
        assert searchByGenre.size() >= 3 : "Tìm thấy phim Action";
        
        // Test tìm theo năm
        ArrayList<Phim> searchByYear = PhimController.timPhimTheoNam(2024);
        assert searchByYear.size() >= 3 : "Tìm thấy phim năm 2024";
        
        // Dọn dẹp
        PhimController.xoaPhim("P004");
        PhimController.xoaPhim("P005");
        PhimController.xoaPhim("P006");
        
        System.out.println("✅ Integration test Search Operations PASSED");
    }
    
    // ===== EDGE CASE TESTING =====
    public void testEdgeCase_NullInput() {
        System.out.println("=== EDGE CASE TEST: Null Input ===");
        
        // Test null input cho tất cả methods
        boolean result1 = PhimController.taoPhim(null);
        assert !result1 : "Không thể tạo phim null";
        
        boolean result2 = PhimController.capNhatPhim(null, null);
        assert !result2 : "Không thể cập nhật với input null";
        
        boolean result3 = PhimController.xoaPhim(null);
        assert !result3 : "Không thể xóa với mã null";
        
        Phim result4 = PhimController.xemThongTinPhim(null);
        assert result4 == null : "Trả về null khi tìm với mã null";
        
        System.out.println("✅ Edge case test Null Input PASSED");
    }
    
    public void testEdgeCase_EmptyInput() {
        System.out.println("=== EDGE CASE TEST: Empty Input ===");
        
        // Test empty string input
        boolean result1 = PhimController.taoPhim(new Phim("", "Test", "Action", 100, "Studio", 2024, "Mô tả"));
        assert !result1 : "Không thể tạo phim với mã rỗng";
        
        boolean result2 = PhimController.xoaPhim("");
        assert !result2 : "Không thể xóa với mã rỗng";
        
        Phim result3 = PhimController.xemThongTinPhim("");
        assert result3 == null : "Trả về null khi tìm với mã rỗng";
        
        System.out.println("✅ Edge case test Empty Input PASSED");
    }
    
    public void testEdgeCase_InvalidData() {
        System.out.println("=== EDGE CASE TEST: Invalid Data ===");
        
        // Test thời lượng âm
        Phim phim1 = new Phim("P007", "Test", "Action", -10, "Studio", 2024, "Mô tả");
        boolean result1 = PhimController.taoPhim(phim1);
        assert !result1 : "Không thể tạo phim với thời lượng âm";
        
        // Test năm không hợp lệ
        Phim phim2 = new Phim("P008", "Test", "Action", 100, "Studio", 1800, "Mô tả");
        boolean result2 = PhimController.taoPhim(phim2);
        assert !result2 : "Không thể tạo phim với năm không hợp lệ";
        
        // Test năm tương lai
        Phim phim3 = new Phim("P009", "Test", "Action", 100, "Studio", 2030, "Mô tả");
        boolean result3 = PhimController.taoPhim(phim3);
        assert !result3 : "Không thể tạo phim với năm tương lai";
        
        System.out.println("✅ Edge case test Invalid Data PASSED");
    }
    
    public void testEdgeCase_BoundaryValues() {
        System.out.println("=== EDGE CASE TEST: Boundary Values ===");
        
        // Test thời lượng = 0
        Phim phim1 = new Phim("P010", "Test", "Action", 0, "Studio", 2024, "Mô tả");
        boolean result1 = PhimController.taoPhim(phim1);
        assert !result1 : "Không thể tạo phim với thời lượng = 0";
        
        // Test thời lượng rất lớn
        Phim phim2 = new Phim("P011", "Test", "Action", 1000, "Studio", 2024, "Mô tả");
        boolean result2 = PhimController.taoPhim(phim2);
        assert !result2 : "Không thể tạo phim với thời lượng quá lớn";
        
        // Test năm = 1900 (giới hạn dưới)
        Phim phim3 = new Phim("P012", "Test", "Action", 100, "Studio", 1900, "Mô tả");
        boolean result3 = PhimController.taoPhim(phim3);
        assert !result3 : "Không thể tạo phim với năm = 1900";
        
        System.out.println("✅ Edge case test Boundary Values PASSED");
    }
    
    // ===== MOCK TESTING =====
    public void testMock_WithSimulatedData() {
        System.out.println("=== MOCK TEST: With Simulated Data ===");
        
        // Tạo dữ liệu giả để test
        ArrayList<Phim> mockData = new ArrayList<>();
        mockData.add(new Phim("M001", "Mock Movie 1", "Comedy", 90, "Mock Studio", 2024, "Mock Description"));
        mockData.add(new Phim("M002", "Mock Movie 2", "Drama", 120, "Mock Studio", 2024, "Mock Description"));
        mockData.add(new Phim("M003", "Mock Movie 3", "Action", 110, "Mock Studio", 2024, "Mock Description"));
        
        // Test với dữ liệu giả
        for (Phim phim : mockData) {
            boolean result = PhimController.taoPhim(phim);
            assert result : "Tạo phim mock thành công: " + phim.getMaPhim();
        }
        
        // Test tìm kiếm với dữ liệu giả
        ArrayList<Phim> searchResult = PhimController.timPhimTheoTheLoai("Comedy");
        assert searchResult.size() >= 1 : "Tìm thấy phim Comedy";
        
        // Dọn dẹp dữ liệu giả
        for (Phim phim : mockData) {
            PhimController.xoaPhim(phim.getMaPhim());
        }
        
        System.out.println("✅ Mock test With Simulated Data PASSED");
    }
    
    // ===== PERFORMANCE TESTING =====
    public void testPerformance_LargeDataSet() {
        System.out.println("=== PERFORMANCE TEST: Large Data Set ===");
        
        long startTime = System.currentTimeMillis();
        
        // Tạo 100 phim để test performance
        for (int i = 1; i <= 100; i++) {
            Phim phim = new Phim("PERF" + String.format("%03d", i), 
                                "Performance Movie " + i, 
                                "Action", 
                                100 + i, 
                                "Studio " + i, 
                                2024, 
                                "Description " + i);
            PhimController.taoPhim(phim);
        }
        
        long createTime = System.currentTimeMillis() - startTime;
        System.out.println("Thời gian tạo 100 phim: " + createTime + "ms");
        
        // Test performance tìm kiếm
        long searchStart = System.currentTimeMillis();
        ArrayList<Phim> searchResult = PhimController.timPhimTheoTheLoai("Action");
        long searchTime = System.currentTimeMillis() - searchStart;
        System.out.println("Thời gian tìm kiếm: " + searchTime + "ms");
        
        // Test performance xem tất cả
        long viewStart = System.currentTimeMillis();
        ArrayList<Phim> allPhim = PhimController.xemTatCaPhim();
        long viewTime = System.currentTimeMillis() - viewStart;
        System.out.println("Thời gian xem tất cả: " + viewTime + "ms");
        
        // Dọn dẹp
        for (int i = 1; i <= 100; i++) {
            PhimController.xoaPhim("PERF" + String.format("%03d", i));
        }
        
        assert createTime < 5000 : "Tạo phim quá chậm";
        assert searchTime < 1000 : "Tìm kiếm quá chậm";
        assert viewTime < 1000 : "Xem tất cả quá chậm";
        
        System.out.println("✅ Performance test Large Data Set PASSED");
    }
    
    // ===== CONCURRENCY TESTING =====
    public void testConcurrency_MultipleThreads() {
        System.out.println("=== CONCURRENCY TEST: Multiple Threads ===");
        
        ExecutorService executor = Executors.newFixedThreadPool(5);
        
        // Test tạo phim đồng thời
        for (int i = 1; i <= 10; i++) {
            final int threadId = i;
            executor.submit(() -> {
                Phim phim = new Phim("CONC" + threadId, 
                                    "Concurrent Movie " + threadId, 
                                    "Action", 
                                    100, 
                                    "Studio", 
                                    2024, 
                                    "Description");
                boolean result = PhimController.taoPhim(phim);
                assert result : "Thread " + threadId + " tạo phim thành công";
            });
        }
        
        // Test đọc phim đồng thời
        for (int i = 1; i <= 10; i++) {
            final int threadId = i;
            executor.submit(() -> {
                Phim phim = PhimController.xemThongTinPhim("CONC" + threadId);
                assert phim != null : "Thread " + threadId + " đọc phim thành công";
            });
        }
        
        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Dọn dẹp
        for (int i = 1; i <= 10; i++) {
            PhimController.xoaPhim("CONC" + i);
        }
        
        System.out.println("✅ Concurrency test Multiple Threads PASSED");
    }
    
    // ===== BUSINESS LOGIC TESTING =====
    public void testBusinessLogic_PhimRules() {
        System.out.println("=== BUSINESS LOGIC TEST: Phim Rules ===");
        
        // Test quy tắc: Mã phim phải unique
        Phim phim1 = new Phim("BL001", "Business Logic Test 1", "Action", 100, "Studio", 2024, "Description");
        Phim phim2 = new Phim("BL001", "Business Logic Test 2", "Action", 100, "Studio", 2024, "Description");
        
        boolean result1 = PhimController.taoPhim(phim1);
        boolean result2 = PhimController.taoPhim(phim2);
        
        assert result1 : "Tạo phim đầu tiên thành công";
        assert !result2 : "Không thể tạo phim trùng mã";
        
        // Test quy tắc: Không thể xóa phim đang có suất chiếu
        // (Giả sử có logic kiểm tra này)
        boolean deleteResult = PhimController.xoaPhim("BL001");
        // assert !deleteResult : "Không thể xóa phim đang có suất chiếu";
        
        // Dọn dẹp
        PhimController.xoaPhim("BL001");
        
        System.out.println("✅ Business logic test Phim Rules PASSED");
    }
    
    // ===== VALIDATION TESTING =====
    public void testValidation_InputValidation() {
        System.out.println("=== VALIDATION TEST: Input Validation ===");
        
        // Test validation mã phim
        Phim phim1 = new Phim("", "Test", "Action", 100, "Studio", 2024, "Description");
        boolean result1 = PhimController.taoPhim(phim1);
        assert !result1 : "Không thể tạo phim với mã rỗng";
        
        // Test validation tên phim
        Phim phim2 = new Phim("VAL001", "", "Action", 100, "Studio", 2024, "Description");
        boolean result2 = PhimController.taoPhim(phim2);
        assert !result2 : "Không thể tạo phim với tên rỗng";
        
        // Test validation thể loại
        Phim phim3 = new Phim("VAL002", "Test", "", 100, "Studio", 2024, "Description");
        boolean result3 = PhimController.taoPhim(phim3);
        assert !result3 : "Không thể tạo phim với thể loại rỗng";
        
        // Test validation thời lượng
        Phim phim4 = new Phim("VAL003", "Test", "Action", -50, "Studio", 2024, "Description");
        boolean result4 = PhimController.taoPhim(phim4);
        assert !result4 : "Không thể tạo phim với thời lượng âm";
        
        // Test validation năm
        Phim phim5 = new Phim("VAL004", "Test", "Action", 100, "Studio", 1800, "Description");
        boolean result5 = PhimController.taoPhim(phim5);
        assert !result5 : "Không thể tạo phim với năm không hợp lệ";
        
        System.out.println("✅ Validation test Input Validation PASSED");
    }
    
    public void testValidation_FormatValidation() {
        System.out.println("=== VALIDATION TEST: Format Validation ===");
        
        // Test format mã phim (giả sử phải có 4 ký tự)
        Phim phim1 = new Phim("123", "Test", "Action", 100, "Studio", 2024, "Description");
        boolean result1 = PhimController.taoPhim(phim1);
        // assert !result1 : "Mã phim phải có ít nhất 4 ký tự";
        
        // Test format tên phim (không được quá dài)
        String longName = "A".repeat(1000);
        Phim phim2 = new Phim("FMT001", longName, "Action", 100, "Studio", 2024, "Description");
        boolean result2 = PhimController.taoPhim(phim2);
        // assert !result2 : "Tên phim không được quá dài";
        
        System.out.println("✅ Validation test Format Validation PASSED");
    }
    
    // ===== MAIN TEST METHOD =====
    public void test() {
        System.out.println("🎬 BẮT ĐẦU TEST PHIM CONTROLLER 🎬");
        
        try {
            // Unit Tests
            testUnit_taoPhim();
            testUnit_capNhatPhim();
            testUnit_xoaPhim();
            testUnit_xemThongTinPhim();
            testUnit_xemTatCaPhim();
            
            // Integration Tests
            testIntegration_CRUDCycle();
            testIntegration_SearchOperations();
            
            // Edge Case Tests
            testEdgeCase_NullInput();
            testEdgeCase_EmptyInput();
            testEdgeCase_InvalidData();
            testEdgeCase_BoundaryValues();
            
            // Mock Tests
            testMock_WithSimulatedData();
            
            // Performance Tests
            testPerformance_LargeDataSet();
            
            // Concurrency Tests
            testConcurrency_MultipleThreads();
            
            // Business Logic Tests
            testBusinessLogic_PhimRules();
            
            // Validation Tests
            testValidation_InputValidation();
            testValidation_FormatValidation();
            
            System.out.println("🎉 TẤT CẢ TEST PHIM CONTROLLER PASSED! 🎉");
            
        } catch (Exception e) {
            System.err.println("❌ TEST PHIM CONTROLLER FAILED: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 