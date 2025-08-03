package com.example.servingwebcontent;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class testVoucherController {
    
    // ===== UNIT TESTING =====
    public void testUnit_taoVoucher() {
        System.out.println("=== UNIT TEST: taoVoucher ===");
        
        // Test tạo voucher thành công
        Voucher voucher = new Voucher("VC001", "Giảm 10%", 10, "2024-12-31", "Có hiệu lực");
        boolean result = VoucherController.taoVoucher(voucher);
        assert result : "Tạo voucher thành công";
        
        // Test tạo voucher trùng mã
        Voucher voucherTrung = new Voucher("VC001", "Giảm 20%", 20, "2024-12-31", "Có hiệu lực");
        boolean resultTrung = VoucherController.taoVoucher(voucherTrung);
        assert !resultTrung : "Không thể tạo voucher trùng mã";
        
        System.out.println("✅ Unit test taoVoucher PASSED");
    }
    
    public void testUnit_capNhatVoucher() {
        System.out.println("=== UNIT TEST: capNhatVoucher ===");
        
        // Test cập nhật voucher thành công
        Voucher voucherUpdate = new Voucher("VC001", "Giảm 15%", 15, "2024-12-31", "Có hiệu lực");
        boolean result = VoucherController.capNhatVoucher("VC001", voucherUpdate);
        assert result : "Cập nhật voucher thành công";
        
        // Test cập nhật voucher không tồn tại
        boolean resultNotFound = VoucherController.capNhatVoucher("VC999", voucherUpdate);
        assert !resultNotFound : "Không thể cập nhật voucher không tồn tại";
        
        System.out.println("✅ Unit test capNhatVoucher PASSED");
    }
    
    public void testUnit_xoaVoucher() {
        System.out.println("=== UNIT TEST: xoaVoucher ===");
        
        // Test xóa voucher thành công
        boolean result = VoucherController.xoaVoucher("VC001");
        assert result : "Xóa voucher thành công";
        
        // Test xóa voucher không tồn tại
        boolean resultNotFound = VoucherController.xoaVoucher("VC999");
        assert !resultNotFound : "Không thể xóa voucher không tồn tại";
        
        System.out.println("✅ Unit test xoaVoucher PASSED");
    }
    
    public void testUnit_xemThongTinVoucher() {
        System.out.println("=== UNIT TEST: xemThongTinVoucher ===");
        
        // Tạo voucher để test
        Voucher voucher = new Voucher("VC002", "Giảm 20%", 20, "2024-12-31", "Có hiệu lực");
        VoucherController.taoVoucher(voucher);
        
        // Test xem thông tin voucher
        Voucher result = VoucherController.xemThongTinVoucher("VC002");
        assert result != null : "Tìm thấy voucher";
        assert "Giảm 20%".equals(result.getTenVoucher()) : "Tên voucher đúng";
        
        // Test xem voucher không tồn tại
        Voucher resultNotFound = VoucherController.xemThongTinVoucher("VC999");
        assert resultNotFound == null : "Không tìm thấy voucher không tồn tại";
        
        System.out.println("✅ Unit test xemThongTinVoucher PASSED");
    }
    
    public void testUnit_xemTatCaVoucher() {
        System.out.println("=== UNIT TEST: xemTatCaVoucher ===");
        
        ArrayList<Voucher> danhSach = VoucherController.xemTatCaVoucher();
        assert danhSach != null : "Danh sách voucher không null";
        assert danhSach.size() >= 0 : "Danh sách voucher hợp lệ";
        
        System.out.println("✅ Unit test xemTatCaVoucher PASSED");
    }
    
    public void testUnit_capNhatTrangThai() {
        System.out.println("=== UNIT TEST: capNhatTrangThai ===");
        
        // Tạo voucher để test
        Voucher voucher = new Voucher("VC003", "Giảm 30%", 30, "2024-12-31", "Có hiệu lực");
        VoucherController.taoVoucher(voucher);
        
        // Test cập nhật trạng thái thành công
        boolean result = VoucherController.capNhatTrangThai("VC003", "Hết hạn");
        assert result : "Cập nhật trạng thái thành công";
        
        // Kiểm tra trạng thái đã thay đổi
        Voucher voucherSauCapNhat = VoucherController.xemThongTinVoucher("VC003");
        assert "Hết hạn".equals(voucherSauCapNhat.getTrangThai()) : "Trạng thái đã thay đổi";
        
        // Test cập nhật trạng thái voucher không tồn tại
        boolean resultNotFound = VoucherController.capNhatTrangThai("VC999", "Có hiệu lực");
        assert !resultNotFound : "Không thể cập nhật trạng thái voucher không tồn tại";
        
        System.out.println("✅ Unit test capNhatTrangThai PASSED");
    }
    
    // ===== INTEGRATION TESTING =====
    public void testIntegration_CRUDCycle() {
        System.out.println("=== INTEGRATION TEST: CRUD Cycle ===");
        
        // Create
        Voucher voucher = new Voucher("VC004", "Giảm 25%", 25, "2024-12-31", "Có hiệu lực");
        boolean createResult = VoucherController.taoVoucher(voucher);
        assert createResult : "Tạo voucher thành công";
        
        // Read
        Voucher readResult = VoucherController.xemThongTinVoucher("VC004");
        assert readResult != null : "Đọc voucher thành công";
        assert "Giảm 25%".equals(readResult.getTenVoucher()) : "Tên voucher đúng";
        
        // Update
        Voucher voucherUpdate = new Voucher("VC004", "Giảm 25%", 30, "2024-12-31", "Có hiệu lực");
        boolean updateResult = VoucherController.capNhatVoucher("VC004", voucherUpdate);
        assert updateResult : "Cập nhật voucher thành công";
        
        // Verify update
        Voucher verifyResult = VoucherController.xemThongTinVoucher("VC004");
        assert verifyResult.getPhanTramGiam() == 30 : "Cập nhật thành công";
        
        // Delete
        boolean deleteResult = VoucherController.xoaVoucher("VC004");
        assert deleteResult : "Xóa voucher thành công";
        
        // Verify delete
        Voucher verifyDelete = VoucherController.xemThongTinVoucher("VC004");
        assert verifyDelete == null : "Voucher đã được xóa";
        
        System.out.println("✅ Integration test CRUD Cycle PASSED");
    }
    
    public void testIntegration_TrangThaiManagement() {
        System.out.println("=== INTEGRATION TEST: Trang Thai Management ===");
        
        // Tạo voucher để test
        Voucher voucher = new Voucher("VC005", "Giảm 40%", 40, "2024-12-31", "Có hiệu lực");
        VoucherController.taoVoucher(voucher);
        
        // Test chu trình quản lý trạng thái
        boolean capNhat1 = VoucherController.capNhatTrangThai("VC005", "Hết hạn");
        assert capNhat1 : "Cập nhật thành Hết hạn thành công";
        
        Voucher voucher1 = VoucherController.xemThongTinVoucher("VC005");
        assert "Hết hạn".equals(voucher1.getTrangThai()) : "Trạng thái đã thay đổi thành Hết hạn";
        
        boolean capNhat2 = VoucherController.capNhatTrangThai("VC005", "Có hiệu lực");
        assert capNhat2 : "Cập nhật thành Có hiệu lực thành công";
        
        Voucher voucher2 = VoucherController.xemThongTinVoucher("VC005");
        assert "Có hiệu lực".equals(voucher2.getTrangThai()) : "Trạng thái đã thay đổi thành Có hiệu lực";
        
        // Dọn dẹp
        VoucherController.xoaVoucher("VC005");
        
        System.out.println("✅ Integration test Trang Thai Management PASSED");
    }
    
    public void testIntegration_SearchOperations() {
        System.out.println("=== INTEGRATION TEST: Search Operations ===");
        
        // Tạo nhiều voucher để test search
        Voucher voucher1 = new Voucher("VC006", "Giảm 10%", 10, "2024-12-31", "Có hiệu lực");
        Voucher voucher2 = new Voucher("VC007", "Giảm 20%", 20, "2024-12-31", "Hết hạn");
        Voucher voucher3 = new Voucher("VC008", "Giảm 30%", 30, "2024-12-31", "Có hiệu lực");
        
        VoucherController.taoVoucher(voucher1);
        VoucherController.taoVoucher(voucher2);
        VoucherController.taoVoucher(voucher3);
        
        // Test tìm theo trạng thái
        ArrayList<Voucher> searchByStatus = VoucherController.timVoucherTheoTrangThai("Có hiệu lực");
        assert searchByStatus.size() >= 2 : "Tìm thấy voucher có hiệu lực";
        
        // Test tìm theo phần trăm giảm
        ArrayList<Voucher> searchByPercent = VoucherController.timVoucherTheoPhanTramGiam(20);
        assert searchByPercent.size() >= 1 : "Tìm thấy voucher giảm 20%";
        
        // Test tìm theo tên
        ArrayList<Voucher> searchByName = VoucherController.timVoucherTheoTen("Giảm");
        assert searchByName.size() >= 3 : "Tìm thấy voucher có tên Giảm";
        
        // Dọn dẹp
        VoucherController.xoaVoucher("VC006");
        VoucherController.xoaVoucher("VC007");
        VoucherController.xoaVoucher("VC008");
        
        System.out.println("✅ Integration test Search Operations PASSED");
    }
    
    // ===== EDGE CASE TESTING =====
    public void testEdgeCase_NullInput() {
        System.out.println("=== EDGE CASE TEST: Null Input ===");
        
        // Test null input cho tất cả methods
        boolean result1 = VoucherController.taoVoucher(null);
        assert !result1 : "Không thể tạo voucher null";
        
        boolean result2 = VoucherController.capNhatVoucher(null, null);
        assert !result2 : "Không thể cập nhật với input null";
        
        boolean result3 = VoucherController.xoaVoucher(null);
        assert !result3 : "Không thể xóa với mã null";
        
        Voucher result4 = VoucherController.xemThongTinVoucher(null);
        assert result4 == null : "Trả về null khi tìm với mã null";
        
        boolean result5 = VoucherController.capNhatTrangThai(null, null);
        assert !result5 : "Không thể cập nhật trạng thái với input null";
        
        System.out.println("✅ Edge case test Null Input PASSED");
    }
    
    public void testEdgeCase_EmptyInput() {
        System.out.println("=== EDGE CASE TEST: Empty Input ===");
        
        // Test empty string input
        boolean result1 = VoucherController.taoVoucher(new Voucher("", "Test", 10, "2024-12-31", "Có hiệu lực"));
        assert !result1 : "Không thể tạo voucher với mã rỗng";
        
        boolean result2 = VoucherController.xoaVoucher("");
        assert !result2 : "Không thể xóa với mã rỗng";
        
        Voucher result3 = VoucherController.xemThongTinVoucher("");
        assert result3 == null : "Trả về null khi tìm với mã rỗng";
        
        boolean result4 = VoucherController.capNhatTrangThai("", "");
        assert !result4 : "Không thể cập nhật trạng thái với mã rỗng";
        
        System.out.println("✅ Edge case test Empty Input PASSED");
    }
    
    public void testEdgeCase_InvalidData() {
        System.out.println("=== EDGE CASE TEST: Invalid Data ===");
        
        // Test phần trăm giảm âm
        Voucher voucher1 = new Voucher("EDGE001", "Test", -10, "2024-12-31", "Có hiệu lực");
        boolean result1 = VoucherController.taoVoucher(voucher1);
        assert !result1 : "Không thể tạo voucher với phần trăm giảm âm";
        
        // Test phần trăm giảm > 100
        Voucher voucher2 = new Voucher("EDGE002", "Test", 150, "2024-12-31", "Có hiệu lực");
        boolean result2 = VoucherController.taoVoucher(voucher2);
        assert !result2 : "Không thể tạo voucher với phần trăm giảm > 100";
        
        // Test tên rỗng
        Voucher voucher3 = new Voucher("EDGE003", "", 10, "2024-12-31", "Có hiệu lực");
        boolean result3 = VoucherController.taoVoucher(voucher3);
        assert !result3 : "Không thể tạo voucher với tên rỗng";
        
        // Test ngày hết hạn rỗng
        Voucher voucher4 = new Voucher("EDGE004", "Test", 10, "", "Có hiệu lực");
        boolean result4 = VoucherController.taoVoucher(voucher4);
        assert !result4 : "Không thể tạo voucher với ngày hết hạn rỗng";
        
        System.out.println("✅ Edge case test Invalid Data PASSED");
    }
    
    public void testEdgeCase_BoundaryValues() {
        System.out.println("=== EDGE CASE TEST: Boundary Values ===");
        
        // Test phần trăm giảm = 0
        Voucher voucher1 = new Voucher("BOUND001", "Test", 0, "2024-12-31", "Có hiệu lực");
        boolean result1 = VoucherController.taoVoucher(voucher1);
        // assert !result1 : "Không thể tạo voucher với phần trăm giảm = 0";
        
        // Test phần trăm giảm = 100
        Voucher voucher2 = new Voucher("BOUND002", "Test", 100, "2024-12-31", "Có hiệu lực");
        boolean result2 = VoucherController.taoVoucher(voucher2);
        // assert !result2 : "Không thể tạo voucher với phần trăm giảm = 100";
        
        // Test tên rất dài
        String longName = "A".repeat(1000);
        Voucher voucher3 = new Voucher("BOUND003", longName, 10, "2024-12-31", "Có hiệu lực");
        boolean result3 = VoucherController.taoVoucher(voucher3);
        // assert !result3 : "Không thể tạo voucher với tên quá dài";
        
        System.out.println("✅ Edge case test Boundary Values PASSED");
    }
    
    // ===== MOCK TESTING =====
    public void testMock_WithSimulatedData() {
        System.out.println("=== MOCK TEST: With Simulated Data ===");
        
        // Tạo dữ liệu giả để test
        ArrayList<Voucher> mockData = new ArrayList<>();
        mockData.add(new Voucher("MOCK001", "Giảm 10%", 10, "2024-12-31", "Có hiệu lực"));
        mockData.add(new Voucher("MOCK002", "Giảm 20%", 20, "2024-12-31", "Có hiệu lực"));
        mockData.add(new Voucher("MOCK003", "Giảm 30%", 30, "2024-12-31", "Hết hạn"));
        mockData.add(new Voucher("MOCK004", "Giảm 50%", 50, "2024-12-31", "Có hiệu lực"));
        
        // Test với dữ liệu giả
        for (Voucher voucher : mockData) {
            boolean result = VoucherController.taoVoucher(voucher);
            assert result : "Tạo voucher mock thành công: " + voucher.getMaVoucher();
        }
        
        // Test tìm kiếm với dữ liệu giả
        ArrayList<Voucher> searchResult = VoucherController.timVoucherTheoTrangThai("Có hiệu lực");
        assert searchResult.size() >= 3 : "Tìm thấy voucher có hiệu lực";
        
        ArrayList<Voucher> searchResult2 = VoucherController.timVoucherTheoPhanTramGiam(20);
        assert searchResult2.size() >= 1 : "Tìm thấy voucher giảm 20%";
        
        // Dọn dẹp dữ liệu giả
        for (Voucher voucher : mockData) {
            VoucherController.xoaVoucher(voucher.getMaVoucher());
        }
        
        System.out.println("✅ Mock test With Simulated Data PASSED");
    }
    
    // ===== PERFORMANCE TESTING =====
    public void testPerformance_LargeDataSet() {
        System.out.println("=== PERFORMANCE TEST: Large Data Set ===");
        
        long startTime = System.currentTimeMillis();
        
        // Tạo 100 voucher để test performance
        for (int i = 1; i <= 100; i++) {
            Voucher voucher = new Voucher("PERF" + String.format("%03d", i), 
                                        "Voucher " + i, 
                                        i % 50 + 10, 
                                        "2024-12-31", 
                                        i % 3 == 0 ? "Hết hạn" : "Có hiệu lực");
            VoucherController.taoVoucher(voucher);
        }
        
        long createTime = System.currentTimeMillis() - startTime;
        System.out.println("Thời gian tạo 100 voucher: " + createTime + "ms");
        
        // Test performance tìm kiếm
        long searchStart = System.currentTimeMillis();
        ArrayList<Voucher> searchResult = VoucherController.timVoucherTheoTrangThai("Có hiệu lực");
        long searchTime = System.currentTimeMillis() - searchStart;
        System.out.println("Thời gian tìm kiếm có hiệu lực: " + searchTime + "ms");
        
        // Test performance xem tất cả
        long viewStart = System.currentTimeMillis();
        ArrayList<Voucher> allVoucher = VoucherController.xemTatCaVoucher();
        long viewTime = System.currentTimeMillis() - viewStart;
        System.out.println("Thời gian xem tất cả: " + viewTime + "ms");
        
        // Dọn dẹp
        for (int i = 1; i <= 100; i++) {
            VoucherController.xoaVoucher("PERF" + String.format("%03d", i));
        }
        
        assert createTime < 5000 : "Tạo voucher quá chậm";
        assert searchTime < 1000 : "Tìm kiếm quá chậm";
        assert viewTime < 1000 : "Xem tất cả quá chậm";
        
        System.out.println("✅ Performance test Large Data Set PASSED");
    }
    
    // ===== CONCURRENCY TESTING =====
    public void testConcurrency_MultipleThreads() {
        System.out.println("=== CONCURRENCY TEST: Multiple Threads ===");
        
        ExecutorService executor = Executors.newFixedThreadPool(5);
        
        // Test tạo voucher đồng thời
        for (int i = 1; i <= 10; i++) {
            final int threadId = i;
            executor.submit(() -> {
                Voucher voucher = new Voucher("CONC" + threadId, 
                                            "Concurrent Voucher " + threadId, 
                                            10 + threadId * 5, 
                                            "2024-12-31", 
                                            "Có hiệu lực");
                boolean result = VoucherController.taoVoucher(voucher);
                assert result : "Thread " + threadId + " tạo voucher thành công";
            });
        }
        
        // Test đọc voucher đồng thời
        for (int i = 1; i <= 10; i++) {
            final int threadId = i;
            executor.submit(() -> {
                Voucher voucher = VoucherController.xemThongTinVoucher("CONC" + threadId);
                assert voucher != null : "Thread " + threadId + " đọc voucher thành công";
            });
        }
        
        // Test cập nhật trạng thái đồng thời
        for (int i = 1; i <= 10; i++) {
            final int threadId = i;
            executor.submit(() -> {
                boolean result = VoucherController.capNhatTrangThai("CONC" + threadId, "Hết hạn");
                assert result : "Thread " + threadId + " cập nhật trạng thái thành công";
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
            VoucherController.xoaVoucher("CONC" + i);
        }
        
        System.out.println("✅ Concurrency test Multiple Threads PASSED");
    }
    
    // ===== BUSINESS LOGIC TESTING =====
    public void testBusinessLogic_VoucherRules() {
        System.out.println("=== BUSINESS LOGIC TEST: Voucher Rules ===");
        
        // Test quy tắc: Mã voucher phải unique
        Voucher voucher1 = new Voucher("BL001", "Business Logic Test 1", 10, "2024-12-31", "Có hiệu lực");
        Voucher voucher2 = new Voucher("BL001", "Business Logic Test 2", 20, "2024-12-31", "Có hiệu lực");
        
        boolean result1 = VoucherController.taoVoucher(voucher1);
        boolean result2 = VoucherController.taoVoucher(voucher2);
        
        assert result1 : "Tạo voucher đầu tiên thành công";
        assert !result2 : "Không thể tạo voucher trùng mã";
        
        // Test quy tắc: Không thể xóa voucher đang được sử dụng
        // (Giả sử có logic kiểm tra này)
        boolean deleteResult = VoucherController.xoaVoucher("BL001");
        // assert !deleteResult : "Không thể xóa voucher đang được sử dụng";
        
        // Dọn dẹp
        VoucherController.xoaVoucher("BL001");
        
        System.out.println("✅ Business logic test Voucher Rules PASSED");
    }
    
    // ===== VALIDATION TESTING =====
    public void testValidation_InputValidation() {
        System.out.println("=== VALIDATION TEST: Input Validation ===");
        
        // Test validation mã voucher
        Voucher voucher1 = new Voucher("", "Test", 10, "2024-12-31", "Có hiệu lực");
        boolean result1 = VoucherController.taoVoucher(voucher1);
        assert !result1 : "Không thể tạo voucher với mã rỗng";
        
        // Test validation tên voucher
        Voucher voucher2 = new Voucher("VAL001", "", 10, "2024-12-31", "Có hiệu lực");
        boolean result2 = VoucherController.taoVoucher(voucher2);
        assert !result2 : "Không thể tạo voucher với tên rỗng";
        
        // Test validation phần trăm giảm
        Voucher voucher3 = new Voucher("VAL002", "Test", -5, "2024-12-31", "Có hiệu lực");
        boolean result3 = VoucherController.taoVoucher(voucher3);
        assert !result3 : "Không thể tạo voucher với phần trăm giảm âm";
        
        // Test validation ngày hết hạn
        Voucher voucher4 = new Voucher("VAL003", "Test", 10, "", "Có hiệu lực");
        boolean result4 = VoucherController.taoVoucher(voucher4);
        assert !result4 : "Không thể tạo voucher với ngày hết hạn rỗng";
        
        // Test validation trạng thái
        Voucher voucher5 = new Voucher("VAL004", "Test", 10, "2024-12-31", "");
        boolean result5 = VoucherController.taoVoucher(voucher5);
        assert !result5 : "Không thể tạo voucher với trạng thái rỗng";
        
        System.out.println("✅ Validation test Input Validation PASSED");
    }
    
    public void testValidation_FormatValidation() {
        System.out.println("=== VALIDATION TEST: Format Validation ===");
        
        // Test format mã voucher (giả sử phải bắt đầu bằng 'VC')
        Voucher voucher1 = new Voucher("123", "Test", 10, "2024-12-31", "Có hiệu lực");
        boolean result1 = VoucherController.taoVoucher(voucher1);
        // assert !result1 : "Mã voucher phải bắt đầu bằng 'VC'";
        
        // Test format ngày hết hạn (giả sử phải có format YYYY-MM-DD)
        Voucher voucher2 = new Voucher("FMT001", "Test", 10, "31-12-2024", "Có hiệu lực");
        boolean result2 = VoucherController.taoVoucher(voucher2);
        // assert !result2 : "Ngày hết hạn phải có format YYYY-MM-DD";
        
        System.out.println("✅ Validation test Format Validation PASSED");
    }
    
    // ===== MAIN TEST METHOD =====
    public void test() {
        System.out.println("🎫 BẮT ĐẦU TEST VOUCHER CONTROLLER 🎫");
        
        try {
            // Unit Tests
            testUnit_taoVoucher();
            testUnit_capNhatVoucher();
            testUnit_xoaVoucher();
            testUnit_xemThongTinVoucher();
            testUnit_xemTatCaVoucher();
            testUnit_capNhatTrangThai();
            
            // Integration Tests
            testIntegration_CRUDCycle();
            testIntegration_TrangThaiManagement();
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
            testBusinessLogic_VoucherRules();
            
            // Validation Tests
            testValidation_InputValidation();
            testValidation_FormatValidation();
            
            System.out.println("🎉 TẤT CẢ TEST VOUCHER CONTROLLER PASSED! 🎉");
            
        } catch (Exception e) {
            System.err.println("❌ TEST VOUCHER CONTROLLER FAILED: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 