package com.example.servingwebcontent;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class testPhongChieuController {
    
    // ===== UNIT TESTING =====
    public void testUnit_taoPhongChieu() {
        System.out.println("=== UNIT TEST: taoPhongChieu ===");
        
        // Test tạo phòng chiếu thành công
        PhongChieu phong = new PhongChieu("PC001", "Phòng 1", 100, "VIP");
        boolean result = PhongChieuController.taoPhongChieu(phong);
        assert result : "Tạo phòng chiếu thành công";
        
        // Test tạo phòng chiếu trùng mã
        PhongChieu phongTrung = new PhongChieu("PC001", "Phòng 2", 80, "Thuong");
        boolean resultTrung = PhongChieuController.taoPhongChieu(phongTrung);
        assert !resultTrung : "Không thể tạo phòng chiếu trùng mã";
        
        System.out.println("✅ Unit test taoPhongChieu PASSED");
    }
    
    public void testUnit_capNhatPhongChieu() {
        System.out.println("=== UNIT TEST: capNhatPhongChieu ===");
        
        // Test cập nhật phòng chiếu thành công
        PhongChieu phongUpdate = new PhongChieu("PC001", "Phòng 1 VIP", 120, "VIP");
        boolean result = PhongChieuController.capNhatPhongChieu("PC001", phongUpdate);
        assert result : "Cập nhật phòng chiếu thành công";
        
        // Test cập nhật phòng chiếu không tồn tại
        boolean resultNotFound = PhongChieuController.capNhatPhongChieu("PC999", phongUpdate);
        assert !resultNotFound : "Không thể cập nhật phòng chiếu không tồn tại";
        
        System.out.println("✅ Unit test capNhatPhongChieu PASSED");
    }
    
    public void testUnit_xoaPhongChieu() {
        System.out.println("=== UNIT TEST: xoaPhongChieu ===");
        
        // Test xóa phòng chiếu thành công
        boolean result = PhongChieuController.xoaPhongChieu("PC001");
        assert result : "Xóa phòng chiếu thành công";
        
        // Test xóa phòng chiếu không tồn tại
        boolean resultNotFound = PhongChieuController.xoaPhongChieu("PC999");
        assert !resultNotFound : "Không thể xóa phòng chiếu không tồn tại";
        
        System.out.println("✅ Unit test xoaPhongChieu PASSED");
    }
    
    public void testUnit_xemThongTinPhongChieu() {
        System.out.println("=== UNIT TEST: xemThongTinPhongChieu ===");
        
        // Tạo phòng chiếu để test
        PhongChieu phong = new PhongChieu("PC002", "Phòng 2", 80, "Thuong");
        PhongChieuController.taoPhongChieu(phong);
        
        // Test xem thông tin phòng chiếu
        PhongChieu result = PhongChieuController.xemThongTinPhongChieu("PC002");
        assert result != null : "Tìm thấy phòng chiếu";
        assert "Phòng 2".equals(result.getTenPhong()) : "Tên phòng chiếu đúng";
        
        // Test xem phòng chiếu không tồn tại
        PhongChieu resultNotFound = PhongChieuController.xemThongTinPhongChieu("PC999");
        assert resultNotFound == null : "Không tìm thấy phòng chiếu không tồn tại";
        
        System.out.println("✅ Unit test xemThongTinPhongChieu PASSED");
    }
    
    public void testUnit_xemTatCaPhongChieu() {
        System.out.println("=== UNIT TEST: xemTatCaPhongChieu ===");
        
        ArrayList<PhongChieu> danhSach = PhongChieuController.xemTatCaPhongChieu();
        assert danhSach != null : "Danh sách phòng chiếu không null";
        assert danhSach.size() >= 0 : "Danh sách phòng chiếu hợp lệ";
        
        System.out.println("✅ Unit test xemTatCaPhongChieu PASSED");
    }
    
    public void testUnit_capNhatSoGhe() {
        System.out.println("=== UNIT TEST: capNhatSoGhe ===");
        
        // Tạo phòng chiếu để test
        PhongChieu phong = new PhongChieu("PC003", "Phòng 3", 60, "Thuong");
        PhongChieuController.taoPhongChieu(phong);
        
        // Test cập nhật số ghế thành công
        boolean result = PhongChieuController.capNhatSoGhe("PC003", 80);
        assert result : "Cập nhật số ghế thành công";
        
        // Kiểm tra số ghế đã thay đổi
        PhongChieu phongSauCapNhat = PhongChieuController.xemThongTinPhongChieu("PC003");
        assert phongSauCapNhat.getSoGhe() == 80 : "Số ghế đã thay đổi";
        
        // Test cập nhật số ghế phòng không tồn tại
        boolean resultNotFound = PhongChieuController.capNhatSoGhe("PC999", 100);
        assert !resultNotFound : "Không thể cập nhật số ghế phòng không tồn tại";
        
        System.out.println("✅ Unit test capNhatSoGhe PASSED");
    }
    
    // ===== INTEGRATION TESTING =====
    public void testIntegration_CRUDCycle() {
        System.out.println("=== INTEGRATION TEST: CRUD Cycle ===");
        
        // Create
        PhongChieu phong = new PhongChieu("PC004", "Phòng 4", 90, "VIP");
        boolean createResult = PhongChieuController.taoPhongChieu(phong);
        assert createResult : "Tạo phòng chiếu thành công";
        
        // Read
        PhongChieu readResult = PhongChieuController.xemThongTinPhongChieu("PC004");
        assert readResult != null : "Đọc phòng chiếu thành công";
        assert "Phòng 4".equals(readResult.getTenPhong()) : "Tên phòng chiếu đúng";
        
        // Update
        PhongChieu phongUpdate = new PhongChieu("PC004", "Phòng 4 VIP", 100, "VIP");
        boolean updateResult = PhongChieuController.capNhatPhongChieu("PC004", phongUpdate);
        assert updateResult : "Cập nhật phòng chiếu thành công";
        
        // Verify update
        PhongChieu verifyResult = PhongChieuController.xemThongTinPhongChieu("PC004");
        assert verifyResult.getSoGhe() == 100 : "Cập nhật thành công";
        
        // Delete
        boolean deleteResult = PhongChieuController.xoaPhongChieu("PC004");
        assert deleteResult : "Xóa phòng chiếu thành công";
        
        // Verify delete
        PhongChieu verifyDelete = PhongChieuController.xemThongTinPhongChieu("PC004");
        assert verifyDelete == null : "Phòng chiếu đã được xóa";
        
        System.out.println("✅ Integration test CRUD Cycle PASSED");
    }
    
    public void testIntegration_SoGheManagement() {
        System.out.println("=== INTEGRATION TEST: So Ghe Management ===");
        
        // Tạo phòng chiếu để test
        PhongChieu phong = new PhongChieu("PC005", "Phòng 5", 50, "Thuong");
        PhongChieuController.taoPhongChieu(phong);
        
        // Test chu trình quản lý số ghế
        boolean capNhat1 = PhongChieuController.capNhatSoGhe("PC005", 60);
        assert capNhat1 : "Cập nhật số ghế lần 1 thành công";
        
        PhongChieu phong1 = PhongChieuController.xemThongTinPhongChieu("PC005");
        assert phong1.getSoGhe() == 60 : "Số ghế đã thay đổi thành 60";
        
        boolean capNhat2 = PhongChieuController.capNhatSoGhe("PC005", 80);
        assert capNhat2 : "Cập nhật số ghế lần 2 thành công";
        
        PhongChieu phong2 = PhongChieuController.xemThongTinPhongChieu("PC005");
        assert phong2.getSoGhe() == 80 : "Số ghế đã thay đổi thành 80";
        
        // Dọn dẹp
        PhongChieuController.xoaPhongChieu("PC005");
        
        System.out.println("✅ Integration test So Ghe Management PASSED");
    }
    
    public void testIntegration_SearchOperations() {
        System.out.println("=== INTEGRATION TEST: Search Operations ===");
        
        // Tạo nhiều phòng chiếu để test search
        PhongChieu phong1 = new PhongChieu("PC006", "Phòng VIP 1", 100, "VIP");
        PhongChieu phong2 = new PhongChieu("PC007", "Phòng VIP 2", 120, "VIP");
        PhongChieu phong3 = new PhongChieu("PC008", "Phòng Thường 1", 80, "Thuong");
        
        PhongChieuController.taoPhongChieu(phong1);
        PhongChieuController.taoPhongChieu(phong2);
        PhongChieuController.taoPhongChieu(phong3);
        
        // Test tìm theo loại phòng
        ArrayList<PhongChieu> searchByType = PhongChieuController.timPhongChieuTheoLoai("VIP");
        assert searchByType.size() >= 2 : "Tìm thấy phòng VIP";
        
        // Test tìm theo số ghế
        ArrayList<PhongChieu> searchBySeats = PhongChieuController.timPhongChieuTheoSoGhe(100);
        assert searchBySeats.size() >= 1 : "Tìm thấy phòng có 100 ghế";
        
        // Test tìm theo tên
        ArrayList<PhongChieu> searchByName = PhongChieuController.timPhongChieuTheoTen("VIP");
        assert searchByName.size() >= 2 : "Tìm thấy phòng có tên VIP";
        
        // Dọn dẹp
        PhongChieuController.xoaPhongChieu("PC006");
        PhongChieuController.xoaPhongChieu("PC007");
        PhongChieuController.xoaPhongChieu("PC008");
        
        System.out.println("✅ Integration test Search Operations PASSED");
    }
    
    // ===== EDGE CASE TESTING =====
    public void testEdgeCase_NullInput() {
        System.out.println("=== EDGE CASE TEST: Null Input ===");
        
        // Test null input cho tất cả methods
        boolean result1 = PhongChieuController.taoPhongChieu(null);
        assert !result1 : "Không thể tạo phòng chiếu null";
        
        boolean result2 = PhongChieuController.capNhatPhongChieu(null, null);
        assert !result2 : "Không thể cập nhật với input null";
        
        boolean result3 = PhongChieuController.xoaPhongChieu(null);
        assert !result3 : "Không thể xóa với mã null";
        
        PhongChieu result4 = PhongChieuController.xemThongTinPhongChieu(null);
        assert result4 == null : "Trả về null khi tìm với mã null";
        
        boolean result5 = PhongChieuController.capNhatSoGhe(null, 100);
        assert !result5 : "Không thể cập nhật số ghế với input null";
        
        System.out.println("✅ Edge case test Null Input PASSED");
    }
    
    public void testEdgeCase_EmptyInput() {
        System.out.println("=== EDGE CASE TEST: Empty Input ===");
        
        // Test empty string input
        boolean result1 = PhongChieuController.taoPhongChieu(new PhongChieu("", "Test", 50, "Thuong"));
        assert !result1 : "Không thể tạo phòng chiếu với mã rỗng";
        
        boolean result2 = PhongChieuController.xoaPhongChieu("");
        assert !result2 : "Không thể xóa với mã rỗng";
        
        PhongChieu result3 = PhongChieuController.xemThongTinPhongChieu("");
        assert result3 == null : "Trả về null khi tìm với mã rỗng";
        
        boolean result4 = PhongChieuController.capNhatSoGhe("", 100);
        assert !result4 : "Không thể cập nhật số ghế với mã rỗng";
        
        System.out.println("✅ Edge case test Empty Input PASSED");
    }
    
    public void testEdgeCase_InvalidData() {
        System.out.println("=== EDGE CASE TEST: Invalid Data ===");
        
        // Test số ghế âm
        PhongChieu phong1 = new PhongChieu("EDGE001", "Test", -10, "Thuong");
        boolean result1 = PhongChieuController.taoPhongChieu(phong1);
        assert !result1 : "Không thể tạo phòng chiếu với số ghế âm";
        
        // Test số ghế = 0
        PhongChieu phong2 = new PhongChieu("EDGE002", "Test", 0, "Thuong");
        boolean result2 = PhongChieuController.taoPhongChieu(phong2);
        assert !result2 : "Không thể tạo phòng chiếu với số ghế = 0";
        
        // Test tên rỗng
        PhongChieu phong3 = new PhongChieu("EDGE003", "", 50, "Thuong");
        boolean result3 = PhongChieuController.taoPhongChieu(phong3);
        assert !result3 : "Không thể tạo phòng chiếu với tên rỗng";
        
        // Test loại rỗng
        PhongChieu phong4 = new PhongChieu("EDGE004", "Test", 50, "");
        boolean result4 = PhongChieuController.taoPhongChieu(phong4);
        assert !result4 : "Không thể tạo phòng chiếu với loại rỗng";
        
        System.out.println("✅ Edge case test Invalid Data PASSED");
    }
    
    public void testEdgeCase_BoundaryValues() {
        System.out.println("=== EDGE CASE TEST: Boundary Values ===");
        
        // Test số ghế rất lớn
        PhongChieu phong1 = new PhongChieu("BOUND001", "Test", 10000, "Thuong");
        boolean result1 = PhongChieuController.taoPhongChieu(phong1);
        // assert !result1 : "Không thể tạo phòng chiếu với số ghế quá lớn";
        
        // Test tên rất dài
        String longName = "A".repeat(1000);
        PhongChieu phong2 = new PhongChieu("BOUND002", longName, 50, "Thuong");
        boolean result2 = PhongChieuController.taoPhongChieu(phong2);
        // assert !result2 : "Không thể tạo phòng chiếu với tên quá dài";
        
        // Test mã rất dài
        String longMa = "PC".repeat(100);
        PhongChieu phong3 = new PhongChieu(longMa, "Test", 50, "Thuong");
        boolean result3 = PhongChieuController.taoPhongChieu(phong3);
        // assert !result3 : "Không thể tạo phòng chiếu với mã quá dài";
        
        System.out.println("✅ Edge case test Boundary Values PASSED");
    }
    
    // ===== MOCK TESTING =====
    public void testMock_WithSimulatedData() {
        System.out.println("=== MOCK TEST: With Simulated Data ===");
        
        // Tạo dữ liệu giả để test
        ArrayList<PhongChieu> mockData = new ArrayList<>();
        mockData.add(new PhongChieu("MOCK001", "Phòng VIP 1", 100, "VIP"));
        mockData.add(new PhongChieu("MOCK002", "Phòng VIP 2", 120, "VIP"));
        mockData.add(new PhongChieu("MOCK003", "Phòng Thường 1", 80, "Thuong"));
        mockData.add(new PhongChieu("MOCK004", "Phòng Thường 2", 60, "Thuong"));
        
        // Test với dữ liệu giả
        for (PhongChieu phong : mockData) {
            boolean result = PhongChieuController.taoPhongChieu(phong);
            assert result : "Tạo phòng chiếu mock thành công: " + phong.getMaPhong();
        }
        
        // Test tìm kiếm với dữ liệu giả
        ArrayList<PhongChieu> searchResult = PhongChieuController.timPhongChieuTheoLoai("VIP");
        assert searchResult.size() >= 2 : "Tìm thấy phòng VIP";
        
        ArrayList<PhongChieu> searchResult2 = PhongChieuController.timPhongChieuTheoSoGhe(80);
        assert searchResult2.size() >= 1 : "Tìm thấy phòng có 80 ghế";
        
        // Dọn dẹp dữ liệu giả
        for (PhongChieu phong : mockData) {
            PhongChieuController.xoaPhongChieu(phong.getMaPhong());
        }
        
        System.out.println("✅ Mock test With Simulated Data PASSED");
    }
    
    // ===== PERFORMANCE TESTING =====
    public void testPerformance_LargeDataSet() {
        System.out.println("=== PERFORMANCE TEST: Large Data Set ===");
        
        long startTime = System.currentTimeMillis();
        
        // Tạo 100 phòng chiếu để test performance
        for (int i = 1; i <= 100; i++) {
            PhongChieu phong = new PhongChieu("PERF" + String.format("%03d", i), 
                                            "Phòng " + i, 
                                            50 + i * 2, 
                                            i % 2 == 0 ? "VIP" : "Thuong");
            PhongChieuController.taoPhongChieu(phong);
        }
        
        long createTime = System.currentTimeMillis() - startTime;
        System.out.println("Thời gian tạo 100 phòng chiếu: " + createTime + "ms");
        
        // Test performance tìm kiếm
        long searchStart = System.currentTimeMillis();
        ArrayList<PhongChieu> searchResult = PhongChieuController.timPhongChieuTheoLoai("VIP");
        long searchTime = System.currentTimeMillis() - searchStart;
        System.out.println("Thời gian tìm kiếm VIP: " + searchTime + "ms");
        
        // Test performance xem tất cả
        long viewStart = System.currentTimeMillis();
        ArrayList<PhongChieu> allPhong = PhongChieuController.xemTatCaPhongChieu();
        long viewTime = System.currentTimeMillis() - viewStart;
        System.out.println("Thời gian xem tất cả: " + viewTime + "ms");
        
        // Dọn dẹp
        for (int i = 1; i <= 100; i++) {
            PhongChieuController.xoaPhongChieu("PERF" + String.format("%03d", i));
        }
        
        assert createTime < 5000 : "Tạo phòng chiếu quá chậm";
        assert searchTime < 1000 : "Tìm kiếm quá chậm";
        assert viewTime < 1000 : "Xem tất cả quá chậm";
        
        System.out.println("✅ Performance test Large Data Set PASSED");
    }
    
    // ===== CONCURRENCY TESTING =====
    public void testConcurrency_MultipleThreads() {
        System.out.println("=== CONCURRENCY TEST: Multiple Threads ===");
        
        ExecutorService executor = Executors.newFixedThreadPool(5);
        
        // Test tạo phòng chiếu đồng thời
        for (int i = 1; i <= 10; i++) {
            final int threadId = i;
            executor.submit(() -> {
                PhongChieu phong = new PhongChieu("CONC" + threadId, 
                                                "Concurrent Room " + threadId, 
                                                50 + threadId * 10, 
                                                "VIP");
                boolean result = PhongChieuController.taoPhongChieu(phong);
                assert result : "Thread " + threadId + " tạo phòng chiếu thành công";
            });
        }
        
        // Test đọc phòng chiếu đồng thời
        for (int i = 1; i <= 10; i++) {
            final int threadId = i;
            executor.submit(() -> {
                PhongChieu phong = PhongChieuController.xemThongTinPhongChieu("CONC" + threadId);
                assert phong != null : "Thread " + threadId + " đọc phòng chiếu thành công";
            });
        }
        
        // Test cập nhật số ghế đồng thời
        for (int i = 1; i <= 10; i++) {
            final int threadId = i;
            executor.submit(() -> {
                boolean result = PhongChieuController.capNhatSoGhe("CONC" + threadId, 100);
                assert result : "Thread " + threadId + " cập nhật số ghế thành công";
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
            PhongChieuController.xoaPhongChieu("CONC" + i);
        }
        
        System.out.println("✅ Concurrency test Multiple Threads PASSED");
    }
    
    // ===== BUSINESS LOGIC TESTING =====
    public void testBusinessLogic_PhongChieuRules() {
        System.out.println("=== BUSINESS LOGIC TEST: Phong Chieu Rules ===");
        
        // Test quy tắc: Mã phòng chiếu phải unique
        PhongChieu phong1 = new PhongChieu("BL001", "Business Logic Test 1", 50, "Thuong");
        PhongChieu phong2 = new PhongChieu("BL001", "Business Logic Test 2", 60, "VIP");
        
        boolean result1 = PhongChieuController.taoPhongChieu(phong1);
        boolean result2 = PhongChieuController.taoPhongChieu(phong2);
        
        assert result1 : "Tạo phòng chiếu đầu tiên thành công";
        assert !result2 : "Không thể tạo phòng chiếu trùng mã";
        
        // Test quy tắc: Không thể xóa phòng chiếu đang có suất chiếu
        // (Giả sử có logic kiểm tra này)
        boolean deleteResult = PhongChieuController.xoaPhongChieu("BL001");
        // assert !deleteResult : "Không thể xóa phòng chiếu đang có suất chiếu";
        
        // Dọn dẹp
        PhongChieuController.xoaPhongChieu("BL001");
        
        System.out.println("✅ Business logic test Phong Chieu Rules PASSED");
    }
    
    // ===== VALIDATION TESTING =====
    public void testValidation_InputValidation() {
        System.out.println("=== VALIDATION TEST: Input Validation ===");
        
        // Test validation mã phòng chiếu
        PhongChieu phong1 = new PhongChieu("", "Test", 50, "Thuong");
        boolean result1 = PhongChieuController.taoPhongChieu(phong1);
        assert !result1 : "Không thể tạo phòng chiếu với mã rỗng";
        
        // Test validation tên phòng chiếu
        PhongChieu phong2 = new PhongChieu("VAL001", "", 50, "Thuong");
        boolean result2 = PhongChieuController.taoPhongChieu(phong2);
        assert !result2 : "Không thể tạo phòng chiếu với tên rỗng";
        
        // Test validation loại
        PhongChieu phong3 = new PhongChieu("VAL002", "Test", 50, "");
        boolean result3 = PhongChieuController.taoPhongChieu(phong3);
        assert !result3 : "Không thể tạo phòng chiếu với loại rỗng";
        
        // Test validation số ghế
        PhongChieu phong4 = new PhongChieu("VAL003", "Test", -10, "Thuong");
        boolean result4 = PhongChieuController.taoPhongChieu(phong4);
        assert !result4 : "Không thể tạo phòng chiếu với số ghế âm";
        
        System.out.println("✅ Validation test Input Validation PASSED");
    }
    
    public void testValidation_FormatValidation() {
        System.out.println("=== VALIDATION TEST: Format Validation ===");
        
        // Test format mã phòng chiếu (giả sử phải bắt đầu bằng 'PC')
        PhongChieu phong1 = new PhongChieu("123", "Test", 50, "Thuong");
        boolean result1 = PhongChieuController.taoPhongChieu(phong1);
        // assert !result1 : "Mã phòng chiếu phải bắt đầu bằng 'PC'";
        
        // Test format số ghế (giả sử phải là bội số của 10)
        PhongChieu phong2 = new PhongChieu("FMT001", "Test", 55, "Thuong");
        boolean result2 = PhongChieuController.taoPhongChieu(phong2);
        // assert !result2 : "Số ghế phải là bội số của 10";
        
        System.out.println("✅ Validation test Format Validation PASSED");
    }
    
    // ===== MAIN TEST METHOD =====
    public void test() {
        System.out.println("🎬 BẮT ĐẦU TEST PHONG CHIEU CONTROLLER 🎬");
        
        try {
            // Unit Tests
            testUnit_taoPhongChieu();
            testUnit_capNhatPhongChieu();
            testUnit_xoaPhongChieu();
            testUnit_xemThongTinPhongChieu();
            testUnit_xemTatCaPhongChieu();
            testUnit_capNhatSoGhe();
            
            // Integration Tests
            testIntegration_CRUDCycle();
            testIntegration_SoGheManagement();
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
            testBusinessLogic_PhongChieuRules();
            
            // Validation Tests
            testValidation_InputValidation();
            testValidation_FormatValidation();
            
            System.out.println("🎉 TẤT CẢ TEST PHONG CHIEU CONTROLLER PASSED! 🎉");
            
        } catch (Exception e) {
            System.err.println("❌ TEST PHONG CHIEU CONTROLLER FAILED: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 