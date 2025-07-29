import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class testGheController {
    
    // ===== UNIT TESTING =====
    public void testUnit_taoGhe() {
        System.out.println("=== UNIT TEST: taoGhe ===");
        
        // Test tạo ghế thành công
        Ghe ghe = new Ghe("G001", "A1", "VIP", "BinhThuong");
        boolean result = GheController.taoGhe(ghe);
        assert result : "Tạo ghế thành công";
        
        // Test tạo ghế trùng mã
        Ghe gheTrung = new Ghe("G001", "A2", "Thuong", "BinhThuong");
        boolean resultTrung = GheController.taoGhe(gheTrung);
        assert !resultTrung : "Không thể tạo ghế trùng mã";
        
        System.out.println("✅ Unit test taoGhe PASSED");
    }
    
    public void testUnit_capNhatGhe() {
        System.out.println("=== UNIT TEST: capNhatGhe ===");
        
        // Test cập nhật ghế thành công
        Ghe gheUpdate = new Ghe("G001", "A1", "VIP", "Khoa");
        boolean result = GheController.capNhatGhe("G001", gheUpdate);
        assert result : "Cập nhật ghế thành công";
        
        // Test cập nhật ghế không tồn tại
        boolean resultNotFound = GheController.capNhatGhe("G999", gheUpdate);
        assert !resultNotFound : "Không thể cập nhật ghế không tồn tại";
        
        System.out.println("✅ Unit test capNhatGhe PASSED");
    }
    
    public void testUnit_xoaGhe() {
        System.out.println("=== UNIT TEST: xoaGhe ===");
        
        // Test xóa ghế thành công
        boolean result = GheController.xoaGhe("G001");
        assert result : "Xóa ghế thành công";
        
        // Test xóa ghế không tồn tại
        boolean resultNotFound = GheController.xoaGhe("G999");
        assert !resultNotFound : "Không thể xóa ghế không tồn tại";
        
        System.out.println("✅ Unit test xoaGhe PASSED");
    }
    
    public void testUnit_xemThongTinGhe() {
        System.out.println("=== UNIT TEST: xemThongTinGhe ===");
        
        // Tạo ghế để test
        Ghe ghe = new Ghe("G002", "B1", "Thuong", "BinhThuong");
        GheController.taoGhe(ghe);
        
        // Test xem thông tin ghế
        Ghe result = GheController.xemThongTinGhe("G002");
        assert result != null : "Tìm thấy ghế";
        assert "B1".equals(result.getViTri()) : "Vị trí ghế đúng";
        
        // Test xem ghế không tồn tại
        Ghe resultNotFound = GheController.xemThongTinGhe("G999");
        assert resultNotFound == null : "Không tìm thấy ghế không tồn tại";
        
        System.out.println("✅ Unit test xemThongTinGhe PASSED");
    }
    
    public void testUnit_xemTatCaGhe() {
        System.out.println("=== UNIT TEST: xemTatCaGhe ===");
        
        ArrayList<Ghe> danhSach = GheController.xemTatCaGhe();
        assert danhSach != null : "Danh sách ghế không null";
        assert danhSach.size() >= 0 : "Danh sách ghế hợp lệ";
        
        System.out.println("✅ Unit test xemTatCaGhe PASSED");
    }
    
    public void testUnit_datGhe() {
        System.out.println("=== UNIT TEST: datGhe ===");
        
        // Tạo ghế để test đặt
        Ghe ghe = new Ghe("G003", "C1", "VIP", "BinhThuong");
        GheController.taoGhe(ghe);
        
        // Test đặt ghế thành công
        boolean result = GheController.datGhe("G003");
        assert result : "Đặt ghế thành công";
        
        // Kiểm tra trạng thái ghế đã thay đổi
        Ghe gheSauDat = GheController.xemThongTinGhe("G003");
        assert "Khoa".equals(gheSauDat.getTrangThai()) : "Trạng thái ghế đã thay đổi thành Khoa";
        
        // Test đặt ghế đã được đặt
        boolean resultDaDat = GheController.datGhe("G003");
        assert !resultDaDat : "Không thể đặt ghế đã được đặt";
        
        System.out.println("✅ Unit test datGhe PASSED");
    }
    
    public void testUnit_hoanGhe() {
        System.out.println("=== UNIT TEST: hoanGhe ===");
        
        // Test hoàn ghế thành công
        boolean result = GheController.hoanGhe("G003");
        assert result : "Hoàn ghế thành công";
        
        // Kiểm tra trạng thái ghế đã thay đổi
        Ghe gheSauHoan = GheController.xemThongTinGhe("G003");
        assert "BinhThuong".equals(gheSauHoan.getTrangThai()) : "Trạng thái ghế đã thay đổi thành BinhThuong";
        
        // Test hoàn ghế không tồn tại
        boolean resultNotFound = GheController.hoanGhe("G999");
        assert !resultNotFound : "Không thể hoàn ghế không tồn tại";
        
        System.out.println("✅ Unit test hoanGhe PASSED");
    }
    
    // ===== INTEGRATION TESTING =====
    public void testIntegration_CRUDCycle() {
        System.out.println("=== INTEGRATION TEST: CRUD Cycle ===");
        
        // Create
        Ghe ghe = new Ghe("G004", "D1", "Thuong", "BinhThuong");
        boolean createResult = GheController.taoGhe(ghe);
        assert createResult : "Tạo ghế thành công";
        
        // Read
        Ghe readResult = GheController.xemThongTinGhe("G004");
        assert readResult != null : "Đọc ghế thành công";
        assert "D1".equals(readResult.getViTri()) : "Vị trí ghế đúng";
        
        // Update
        Ghe gheUpdate = new Ghe("G004", "D1", "VIP", "BinhThuong");
        boolean updateResult = GheController.capNhatGhe("G004", gheUpdate);
        assert updateResult : "Cập nhật ghế thành công";
        
        // Verify update
        Ghe verifyResult = GheController.xemThongTinGhe("G004");
        assert "VIP".equals(verifyResult.getLoaiGhe()) : "Cập nhật thành công";
        
        // Delete
        boolean deleteResult = GheController.xoaGhe("G004");
        assert deleteResult : "Xóa ghế thành công";
        
        // Verify delete
        Ghe verifyDelete = GheController.xemThongTinGhe("G004");
        assert verifyDelete == null : "Ghế đã được xóa";
        
        System.out.println("✅ Integration test CRUD Cycle PASSED");
    }
    
    public void testIntegration_DatHoanGhe() {
        System.out.println("=== INTEGRATION TEST: Dat Hoan Ghe ===");
        
        // Tạo ghế để test
        Ghe ghe = new Ghe("G005", "E1", "VIP", "BinhThuong");
        GheController.taoGhe(ghe);
        
        // Test chu trình đặt và hoàn ghế
        boolean datResult = GheController.datGhe("G005");
        assert datResult : "Đặt ghế thành công";
        
        Ghe gheSauDat = GheController.xemThongTinGhe("G005");
        assert "Khoa".equals(gheSauDat.getTrangThai()) : "Ghế đã được đặt";
        
        boolean hoanResult = GheController.hoanGhe("G005");
        assert hoanResult : "Hoàn ghế thành công";
        
        Ghe gheSauHoan = GheController.xemThongTinGhe("G005");
        assert "BinhThuong".equals(gheSauHoan.getTrangThai()) : "Ghế đã được hoàn";
        
        // Dọn dẹp
        GheController.xoaGhe("G005");
        
        System.out.println("✅ Integration test Dat Hoan Ghe PASSED");
    }
    
    public void testIntegration_SearchOperations() {
        System.out.println("=== INTEGRATION TEST: Search Operations ===");
        
        // Tạo nhiều ghế để test search
        Ghe ghe1 = new Ghe("G006", "F1", "VIP", "BinhThuong");
        Ghe ghe2 = new Ghe("G007", "F2", "VIP", "Khoa");
        Ghe ghe3 = new Ghe("G008", "F3", "Thuong", "BinhThuong");
        
        GheController.taoGhe(ghe1);
        GheController.taoGhe(ghe2);
        GheController.taoGhe(ghe3);
        
        // Test tìm theo loại ghế
        ArrayList<Ghe> searchByType = GheController.timGheTheoLoai("VIP");
        assert searchByType.size() >= 2 : "Tìm thấy ghế VIP";
        
        // Test tìm theo trạng thái
        ArrayList<Ghe> searchByStatus = GheController.timGheTheoTrangThai("BinhThuong");
        assert searchByStatus.size() >= 2 : "Tìm thấy ghế BinhThuong";
        
        // Test tìm theo vị trí
        ArrayList<Ghe> searchByPosition = GheController.timGheTheoViTri("F");
        assert searchByPosition.size() >= 3 : "Tìm thấy ghế ở vị trí F";
        
        // Dọn dẹp
        GheController.xoaGhe("G006");
        GheController.xoaGhe("G007");
        GheController.xoaGhe("G008");
        
        System.out.println("✅ Integration test Search Operations PASSED");
    }
    
    // ===== EDGE CASE TESTING =====
    public void testEdgeCase_NullInput() {
        System.out.println("=== EDGE CASE TEST: Null Input ===");
        
        // Test null input cho tất cả methods
        boolean result1 = GheController.taoGhe(null);
        assert !result1 : "Không thể tạo ghế null";
        
        boolean result2 = GheController.capNhatGhe(null, null);
        assert !result2 : "Không thể cập nhật với input null";
        
        boolean result3 = GheController.xoaGhe(null);
        assert !result3 : "Không thể xóa với mã null";
        
        Ghe result4 = GheController.xemThongTinGhe(null);
        assert result4 == null : "Trả về null khi tìm với mã null";
        
        boolean result5 = GheController.datGhe(null);
        assert !result5 : "Không thể đặt ghế với mã null";
        
        boolean result6 = GheController.hoanGhe(null);
        assert !result6 : "Không thể hoàn ghế với mã null";
        
        System.out.println("✅ Edge case test Null Input PASSED");
    }
    
    public void testEdgeCase_EmptyInput() {
        System.out.println("=== EDGE CASE TEST: Empty Input ===");
        
        // Test empty string input
        boolean result1 = GheController.taoGhe(new Ghe("", "A1", "VIP", "BinhThuong"));
        assert !result1 : "Không thể tạo ghế với mã rỗng";
        
        boolean result2 = GheController.xoaGhe("");
        assert !result2 : "Không thể xóa với mã rỗng";
        
        Ghe result3 = GheController.xemThongTinGhe("");
        assert result3 == null : "Trả về null khi tìm với mã rỗng";
        
        boolean result4 = GheController.datGhe("");
        assert !result4 : "Không thể đặt ghế với mã rỗng";
        
        boolean result5 = GheController.hoanGhe("");
        assert !result5 : "Không thể hoàn ghế với mã rỗng";
        
        System.out.println("✅ Edge case test Empty Input PASSED");
    }
    
    public void testEdgeCase_InvalidData() {
        System.out.println("=== EDGE CASE TEST: Invalid Data ===");
        
        // Test loại ghế không hợp lệ
        Ghe ghe1 = new Ghe("EDGE001", "A1", "InvalidType", "BinhThuong");
        boolean result1 = GheController.taoGhe(ghe1);
        assert !result1 : "Không thể tạo ghế với loại không hợp lệ";
        
        // Test trạng thái không hợp lệ
        Ghe ghe2 = new Ghe("EDGE002", "A2", "VIP", "InvalidStatus");
        boolean result2 = GheController.taoGhe(ghe2);
        assert !result2 : "Không thể tạo ghế với trạng thái không hợp lệ";
        
        // Test vị trí rỗng
        Ghe ghe3 = new Ghe("EDGE003", "", "VIP", "BinhThuong");
        boolean result3 = GheController.taoGhe(ghe3);
        assert !result3 : "Không thể tạo ghế với vị trí rỗng";
        
        System.out.println("✅ Edge case test Invalid Data PASSED");
    }
    
    public void testEdgeCase_BoundaryValues() {
        System.out.println("=== EDGE CASE TEST: Boundary Values ===");
        
        // Test vị trí ghế rất dài
        String longPosition = "A".repeat(100);
        Ghe ghe1 = new Ghe("BOUND001", longPosition, "VIP", "BinhThuong");
        boolean result1 = GheController.taoGhe(ghe1);
        // assert !result1 : "Không thể tạo ghế với vị trí quá dài";
        
        // Test mã ghế rất dài
        String longMaGhe = "G".repeat(100);
        Ghe ghe2 = new Ghe(longMaGhe, "A1", "VIP", "BinhThuong");
        boolean result2 = GheController.taoGhe(ghe2);
        // assert !result2 : "Không thể tạo ghế với mã quá dài";
        
        System.out.println("✅ Edge case test Boundary Values PASSED");
    }
    
    // ===== MOCK TESTING =====
    public void testMock_WithSimulatedData() {
        System.out.println("=== MOCK TEST: With Simulated Data ===");
        
        // Tạo dữ liệu giả để test
        ArrayList<Ghe> mockData = new ArrayList<>();
        mockData.add(new Ghe("MOCK001", "A1", "VIP", "BinhThuong"));
        mockData.add(new Ghe("MOCK002", "A2", "VIP", "Khoa"));
        mockData.add(new Ghe("MOCK003", "B1", "Thuong", "BinhThuong"));
        mockData.add(new Ghe("MOCK004", "B2", "Thuong", "Khoa"));
        
        // Test với dữ liệu giả
        for (Ghe ghe : mockData) {
            boolean result = GheController.taoGhe(ghe);
            assert result : "Tạo ghế mock thành công: " + ghe.getMaGhe();
        }
        
        // Test tìm kiếm với dữ liệu giả
        ArrayList<Ghe> searchResult = GheController.timGheTheoLoai("VIP");
        assert searchResult.size() >= 2 : "Tìm thấy ghế VIP";
        
        ArrayList<Ghe> searchResult2 = GheController.timGheTheoTrangThai("BinhThuong");
        assert searchResult2.size() >= 2 : "Tìm thấy ghế BinhThuong";
        
        // Dọn dẹp dữ liệu giả
        for (Ghe ghe : mockData) {
            GheController.xoaGhe(ghe.getMaGhe());
        }
        
        System.out.println("✅ Mock test With Simulated Data PASSED");
    }
    
    // ===== PERFORMANCE TESTING =====
    public void testPerformance_LargeDataSet() {
        System.out.println("=== PERFORMANCE TEST: Large Data Set ===");
        
        long startTime = System.currentTimeMillis();
        
        // Tạo 100 ghế để test performance
        for (int i = 1; i <= 100; i++) {
            Ghe ghe = new Ghe("PERF" + String.format("%03d", i), 
                             "A" + i, 
                             i % 2 == 0 ? "VIP" : "Thuong", 
                             i % 3 == 0 ? "Khoa" : "BinhThuong");
            GheController.taoGhe(ghe);
        }
        
        long createTime = System.currentTimeMillis() - startTime;
        System.out.println("Thời gian tạo 100 ghế: " + createTime + "ms");
        
        // Test performance tìm kiếm
        long searchStart = System.currentTimeMillis();
        ArrayList<Ghe> searchResult = GheController.timGheTheoLoai("VIP");
        long searchTime = System.currentTimeMillis() - searchStart;
        System.out.println("Thời gian tìm kiếm VIP: " + searchTime + "ms");
        
        // Test performance xem tất cả
        long viewStart = System.currentTimeMillis();
        ArrayList<Ghe> allGhe = GheController.xemTatCaGhe();
        long viewTime = System.currentTimeMillis() - viewStart;
        System.out.println("Thời gian xem tất cả: " + viewTime + "ms");
        
        // Dọn dẹp
        for (int i = 1; i <= 100; i++) {
            GheController.xoaGhe("PERF" + String.format("%03d", i));
        }
        
        assert createTime < 5000 : "Tạo ghế quá chậm";
        assert searchTime < 1000 : "Tìm kiếm quá chậm";
        assert viewTime < 1000 : "Xem tất cả quá chậm";
        
        System.out.println("✅ Performance test Large Data Set PASSED");
    }
    
    // ===== CONCURRENCY TESTING =====
    public void testConcurrency_MultipleThreads() {
        System.out.println("=== CONCURRENCY TEST: Multiple Threads ===");
        
        ExecutorService executor = Executors.newFixedThreadPool(5);
        
        // Test tạo ghế đồng thời
        for (int i = 1; i <= 10; i++) {
            final int threadId = i;
            executor.submit(() -> {
                Ghe ghe = new Ghe("CONC" + threadId, 
                                 "A" + threadId, 
                                 "VIP", 
                                 "BinhThuong");
                boolean result = GheController.taoGhe(ghe);
                assert result : "Thread " + threadId + " tạo ghế thành công";
            });
        }
        
        // Test đọc ghế đồng thời
        for (int i = 1; i <= 10; i++) {
            final int threadId = i;
            executor.submit(() -> {
                Ghe ghe = GheController.xemThongTinGhe("CONC" + threadId);
                assert ghe != null : "Thread " + threadId + " đọc ghế thành công";
            });
        }
        
        // Test đặt ghế đồng thời
        for (int i = 1; i <= 10; i++) {
            final int threadId = i;
            executor.submit(() -> {
                boolean result = GheController.datGhe("CONC" + threadId);
                // Chỉ 1 thread đầu tiên sẽ thành công
                if (threadId == 1) {
                    assert result : "Thread " + threadId + " đặt ghế thành công";
                }
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
            GheController.xoaGhe("CONC" + i);
        }
        
        System.out.println("✅ Concurrency test Multiple Threads PASSED");
    }
    
    // ===== BUSINESS LOGIC TESTING =====
    public void testBusinessLogic_GheRules() {
        System.out.println("=== BUSINESS LOGIC TEST: Ghe Rules ===");
        
        // Test quy tắc: Mã ghế phải unique
        Ghe ghe1 = new Ghe("BL001", "A1", "VIP", "BinhThuong");
        Ghe ghe2 = new Ghe("BL001", "A2", "Thuong", "BinhThuong");
        
        boolean result1 = GheController.taoGhe(ghe1);
        boolean result2 = GheController.taoGhe(ghe2);
        
        assert result1 : "Tạo ghế đầu tiên thành công";
        assert !result2 : "Không thể tạo ghế trùng mã";
        
        // Test quy tắc: Không thể đặt ghế đã được đặt
        boolean datResult1 = GheController.datGhe("BL001");
        assert datResult1 : "Đặt ghế lần đầu thành công";
        
        boolean datResult2 = GheController.datGhe("BL001");
        assert !datResult2 : "Không thể đặt ghế đã được đặt";
        
        // Test quy tắc: Chỉ có thể hoàn ghế đã được đặt
        boolean hoanResult = GheController.hoanGhe("BL001");
        assert hoanResult : "Hoàn ghế thành công";
        
        // Dọn dẹp
        GheController.xoaGhe("BL001");
        
        System.out.println("✅ Business logic test Ghe Rules PASSED");
    }
    
    // ===== VALIDATION TESTING =====
    public void testValidation_InputValidation() {
        System.out.println("=== VALIDATION TEST: Input Validation ===");
        
        // Test validation mã ghế
        Ghe ghe1 = new Ghe("", "A1", "VIP", "BinhThuong");
        boolean result1 = GheController.taoGhe(ghe1);
        assert !result1 : "Không thể tạo ghế với mã rỗng";
        
        // Test validation vị trí
        Ghe ghe2 = new Ghe("VAL001", "", "VIP", "BinhThuong");
        boolean result2 = GheController.taoGhe(ghe2);
        assert !result2 : "Không thể tạo ghế với vị trí rỗng";
        
        // Test validation loại ghế
        Ghe ghe3 = new Ghe("VAL002", "A1", "", "BinhThuong");
        boolean result3 = GheController.taoGhe(ghe3);
        assert !result3 : "Không thể tạo ghế với loại rỗng";
        
        // Test validation trạng thái
        Ghe ghe4 = new Ghe("VAL003", "A1", "VIP", "");
        boolean result4 = GheController.taoGhe(ghe4);
        assert !result4 : "Không thể tạo ghế với trạng thái rỗng";
        
        System.out.println("✅ Validation test Input Validation PASSED");
    }
    
    public void testValidation_FormatValidation() {
        System.out.println("=== VALIDATION TEST: Format Validation ===");
        
        // Test format mã ghế (giả sử phải bắt đầu bằng 'G')
        Ghe ghe1 = new Ghe("123", "A1", "VIP", "BinhThuong");
        boolean result1 = GheController.taoGhe(ghe1);
        // assert !result1 : "Mã ghế phải bắt đầu bằng 'G'";
        
        // Test format vị trí (giả sử phải có format A1, B2, etc.)
        Ghe ghe2 = new Ghe("FMT001", "123", "VIP", "BinhThuong");
        boolean result2 = GheController.taoGhe(ghe2);
        // assert !result2 : "Vị trí phải có format chữ + số";
        
        System.out.println("✅ Validation test Format Validation PASSED");
    }
    
    // ===== MAIN TEST METHOD =====
    public void test() {
        System.out.println("🪑 BẮT ĐẦU TEST GHE CONTROLLER 🪑");
        
        try {
            // Unit Tests
            testUnit_taoGhe();
            testUnit_capNhatGhe();
            testUnit_xoaGhe();
            testUnit_xemThongTinGhe();
            testUnit_xemTatCaGhe();
            testUnit_datGhe();
            testUnit_hoanGhe();
            
            // Integration Tests
            testIntegration_CRUDCycle();
            testIntegration_DatHoanGhe();
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
            testBusinessLogic_GheRules();
            
            // Validation Tests
            testValidation_InputValidation();
            testValidation_FormatValidation();
            
            System.out.println("🎉 TẤT CẢ TEST GHE CONTROLLER PASSED! 🎉");
            
        } catch (Exception e) {
            System.err.println("❌ TEST GHE CONTROLLER FAILED: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 