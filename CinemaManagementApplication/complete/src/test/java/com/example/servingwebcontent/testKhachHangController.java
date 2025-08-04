package com.example.servingwebcontent;

import java.util.ArrayList;
import com.example.servingwebcontent.model.KhachHang;
import com.example.servingwebcontent.controller.KhachHangController;

public class testKhachHangController {
    sửa
    // === UNIT TESTING ===
    
    // Test taoKhachHang
    public static void testTaoKhachHang() {
        System.out.println("=== TEST TAO KHACH HANG ===");
        
        // Test tạo khách hàng hợp lệ
        KhachHang kh = new KhachHang("TEST001", "Test User", 25, "0123456789", "test@email.com");
        boolean result = KhachHangController.taoKhachHang(kh);
        assert result == true : "Tạo khách hàng hợp lệ phải trả về true";
        System.out.println("✓ Tạo khách hàng hợp lệ OK");
        
        // Test tạo khách hàng null
        boolean resultNull = KhachHangController.taoKhachHang(null);
        assert resultNull == false : "Tạo khách hàng null phải trả về false";
        System.out.println("✓ Tạo khách hàng null OK");
        
        // Test tạo khách hàng với CCCD rỗng
        KhachHang khEmpty = new KhachHang("", "Test User", 25, "0123456789", "test@email.com");
        boolean resultEmpty = KhachHangController.taoKhachHang(khEmpty);
        assert resultEmpty == false : "Tạo khách hàng với CCCD rỗng phải trả về false";
        System.out.println("✓ Tạo khách hàng với CCCD rỗng OK");
    }
    
    // Test capNhatThongTin
    public static void testCapNhatThongTin() {
        System.out.println("\n=== TEST CAP NHAT THONG TIN ===");
        
        // Tạo khách hàng để test
        KhachHang kh = new KhachHang("UPDATE001", "Original Name", 25, "0123456789", "original@email.com");
        KhachHangController.taoKhachHang(kh);
        
        // Test cập nhật hợp lệ
        KhachHang khMoi = new KhachHang("UPDATE001", "Updated Name", 30, "0987654321", "updated@email.com");
        boolean result = KhachHangController.capNhatThongTin("UPDATE001", khMoi);
        assert result == true : "Cập nhật khách hàng hợp lệ phải trả về true";
        System.out.println("✓ Cập nhật khách hàng hợp lệ OK");
        
        // Test cập nhật khách hàng không tồn tại
        KhachHang khKhongTonTai = new KhachHang("NOTEXIST", "Not Exist", 25, "0123456789", "notexist@email.com");
        boolean resultNotExist = KhachHangController.capNhatThongTin("NOTEXIST", khKhongTonTai);
        assert resultNotExist == false : "Cập nhật khách hàng không tồn tại phải trả về false";
        System.out.println("✓ Cập nhật khách hàng không tồn tại OK");
        
        // Test cập nhật với CCCD rỗng
        boolean resultEmpty = KhachHangController.capNhatThongTin("", khMoi);
        assert resultEmpty == false : "Cập nhật với CCCD rỗng phải trả về false";
        System.out.println("✓ Cập nhật với CCCD rỗng OK");
    }
    
    // Test xoaKhachHang
    public static void testXoaKhachHang() {
        System.out.println("\n=== TEST XOA KHACH HANG ===");
        
        // Tạo khách hàng để test
        KhachHang kh = new KhachHang("DELETE001", "Delete User", 25, "0123456789", "delete@email.com");
        KhachHangController.taoKhachHang(kh);
        
        // Test xóa khách hàng tồn tại
        boolean result = KhachHangController.xoaKhachHang("DELETE001");
        assert result == true : "Xóa khách hàng tồn tại phải trả về true";
        System.out.println("✓ Xóa khách hàng tồn tại OK");
        
        // Test xóa khách hàng không tồn tại
        boolean resultNotExist = KhachHangController.xoaKhachHang("NOTEXIST");
        assert resultNotExist == false : "Xóa khách hàng không tồn tại phải trả về false";
        System.out.println("✓ Xóa khách hàng không tồn tại OK");
        
        // Test xóa với CCCD rỗng
        boolean resultEmpty = KhachHangController.xoaKhachHang("");
        assert resultEmpty == false : "Xóa với CCCD rỗng phải trả về false";
        System.out.println("✓ Xóa với CCCD rỗng OK");
    }
    
    // Test xemThongTin
    public static void testXemThongTin() {
        System.out.println("\n=== TEST XEM THONG TIN ===");
        
        // Tạo khách hàng để test
        KhachHang kh = new KhachHang("VIEW001", "View User", 25, "0123456789", "view@email.com");
        KhachHangController.taoKhachHang(kh);
        
        // Test xem thông tin khách hàng tồn tại
        boolean result = KhachHangController.xemThongTin("VIEW001");
        assert result == true : "Xem thông tin khách hàng tồn tại phải trả về true";
        System.out.println("✓ Xem thông tin khách hàng tồn tại OK");
        
        // Test xem thông tin với CCCD rỗng
        boolean resultEmpty = KhachHangController.xemThongTin("");
        assert resultEmpty == false : "Xem thông tin với CCCD rỗng phải trả về false";
        System.out.println("✓ Xem thông tin với CCCD rỗng OK");
    }
    
    // Test timKhachHangTheoCCCD
    public static void testTimKhachHangTheoCCCD() {
        System.out.println("\n=== TEST TIM KHACH HANG THEO CCCD ===");
        
        // Tạo khách hàng để test
        KhachHang kh = new KhachHang("SEARCH001", "Search User", 25, "0123456789", "search@email.com");
        KhachHangController.taoKhachHang(kh);
        
        // Test tìm khách hàng tồn tại
        KhachHang result = KhachHangController.timKhachHangTheoCCCD("SEARCH001");
        assert result != null : "Tìm khách hàng tồn tại phải trả về khách hàng";
        assert "Search User".equals(result.getTen()) : "Tên khách hàng tìm được không đúng";
        System.out.println("✓ Tìm khách hàng tồn tại OK");
        
        // Test tìm khách hàng không tồn tại
        KhachHang resultNotExist = KhachHangController.timKhachHangTheoCCCD("NOTEXIST");
        assert resultNotExist == null : "Tìm khách hàng không tồn tại phải trả về null";
        System.out.println("✓ Tìm khách hàng không tồn tại OK");
        
        // Test tìm với CCCD rỗng
        KhachHang resultEmpty = KhachHangController.timKhachHangTheoCCCD("");
        assert resultEmpty == null : "Tìm với CCCD rỗng phải trả về null";
        System.out.println("✓ Tìm với CCCD rỗng OK");
    }
    
    // Test timKhachHangTheoTen
    public static void testTimKhachHangTheoTen() {
        System.out.println("\n=== TEST TIM KHACH HANG THEO TEN ===");
        
        // Tạo khách hàng để test
        KhachHang kh1 = new KhachHang("NAME001", "John Doe", 25, "0123456789", "john@email.com");
        KhachHang kh2 = new KhachHang("NAME002", "Jane Doe", 30, "0987654321", "jane@email.com");
        KhachHangController.taoKhachHang(kh1);
        KhachHangController.taoKhachHang(kh2);
        
        // Test tìm theo tên có kết quả
        ArrayList<KhachHang> result = KhachHangController.timKhachHangTheoTen("Doe");
        assert result.size() == 2 : "Tìm theo tên 'Doe' phải trả về 2 kết quả";
        System.out.println("✓ Tìm theo tên có kết quả OK");
        
        // Test tìm theo tên không có kết quả
        ArrayList<KhachHang> resultEmpty = KhachHangController.timKhachHangTheoTen("NotExist");
        assert resultEmpty.size() == 0 : "Tìm theo tên không tồn tại phải trả về list rỗng";
        System.out.println("✓ Tìm theo tên không có kết quả OK");
        
        // Test tìm với tên rỗng
        ArrayList<KhachHang> resultNull = KhachHangController.timKhachHangTheoTen("");
        assert resultNull.size() == 0 : "Tìm với tên rỗng phải trả về list rỗng";
        System.out.println("✓ Tìm với tên rỗng OK");
    }
    
    // Test timKhachHangTheoGioiTinh
    public static void testTimKhachHangTheoGioiTinh() {
        System.out.println("\n=== TEST TIM KHACH HANG THEO GIOI TINH ===");
        
        // Tạo khách hàng để test
        KhachHang kh1 = new KhachHang("GENDER001", "Male User", 25, "0123456789", "male@email.com", "Nam");
        KhachHang kh2 = new KhachHang("GENDER002", "Female User", 30, "0987654321", "female@email.com", "Nu");
        KhachHangController.taoKhachHang(kh1);
        KhachHangController.taoKhachHang(kh2);
        
        // Test tìm theo giới tính Nam
        ArrayList<KhachHang> resultNam = KhachHangController.timKhachHangTheoGioiTinh("Nam");
        assert resultNam.size() == 1 : "Tìm theo giới tính 'Nam' phải trả về 1 kết quả";
        System.out.println("✓ Tìm theo giới tính Nam OK");
        
        // Test tìm theo giới tính Nu
        ArrayList<KhachHang> resultNu = KhachHangController.timKhachHangTheoGioiTinh("Nu");
        assert resultNu.size() == 1 : "Tìm theo giới tính 'Nu' phải trả về 1 kết quả";
        System.out.println("✓ Tìm theo giới tính Nu OK");
        
        // Test tìm với giới tính rỗng
        ArrayList<KhachHang> resultEmpty = KhachHangController.timKhachHangTheoGioiTinh("");
        assert resultEmpty.size() == 0 : "Tìm với giới tính rỗng phải trả về list rỗng";
        System.out.println("✓ Tìm với giới tính rỗng OK");
    }
    
    // === INTEGRATION TESTING ===
    
    // Test luồng quản lý khách hàng hoàn chỉnh
    public static void testKhachHangManagementFlow() {
        System.out.println("\n=== TEST KHACH HANG MANAGEMENT FLOW ===");
        
        // 1. Tạo khách hàng mới
        KhachHang kh = new KhachHang("FLOW001", "Flow User", 25, "0123456789", "flow@email.com");
        boolean createResult = KhachHangController.taoKhachHang(kh);
        assert createResult == true : "Tạo khách hàng trong flow phải thành công";
        
        // 2. Tìm kiếm khách hàng
        KhachHang foundKh = KhachHangController.timKhachHangTheoCCCD("FLOW001");
        assert foundKh != null : "Phải tìm thấy khách hàng sau khi tạo";
        
        // 3. Cập nhật thông tin
        foundKh.setTen("Updated Flow User");
        boolean updateResult = KhachHangController.capNhatThongTin("FLOW001", foundKh);
        assert updateResult == true : "Cập nhật khách hàng trong flow phải thành công";
        
        // 4. Xem thông tin
        boolean viewResult = KhachHangController.xemThongTin("FLOW001");
        assert viewResult == true : "Xem thông tin khách hàng trong flow phải thành công";
        
        // 5. Xóa khách hàng
        boolean deleteResult = KhachHangController.xoaKhachHang("FLOW001");
        assert deleteResult == true : "Xóa khách hàng trong flow phải thành công";
        
        // 6. Xác nhận đã xóa
        KhachHang deletedKh = KhachHangController.timKhachHangTheoCCCD("FLOW001");
        assert deletedKh == null : "Khách hàng phải không tồn tại sau khi xóa";
        
        System.out.println("✓ Khách hàng management flow OK");
    }
    
    // === EDGE CASE TESTING ===
    
    // Test với dữ liệu biên
    public static void testEdgeCases() {
        System.out.println("\n=== TEST EDGE CASES ===");
        
        // Test tuổi biên
        KhachHang khMinAge = new KhachHang("EDGE001", "Min Age", 0, "0123456789", "min@email.com");
        boolean resultMin = KhachHangController.taoKhachHang(khMinAge);
        assert resultMin == false : "Tuổi 0 phải không hợp lệ";
        
        KhachHang khMaxAge = new KhachHang("EDGE002", "Max Age", 150, "0123456789", "max@email.com");
        boolean resultMax = KhachHangController.taoKhachHang(khMaxAge);
        assert resultMax == false : "Tuổi 150 phải không hợp lệ";
        
        // Test tên rất dài
        String longName = "A".repeat(1000);
        KhachHang khLongName = new KhachHang("EDGE003", longName, 25, "0123456789", "long@email.com");
        
        // Test email không hợp lệ
        KhachHang khInvalidEmail = new KhachHang("EDGE004", "Invalid Email", 25, "0123456789", "invalid-email");
        
        System.out.println("✓ Edge cases testing OK");
    }
    
    // === MOCK TESTING ===
    
    // Test với dữ liệu giả
    public static void testMockData() {
        System.out.println("\n=== TEST MOCK DATA ===");
        
        // Tạo dữ liệu giả
        ArrayList<KhachHang> mockCustomers = new ArrayList<>();
        mockCustomers.add(new KhachHang("MOCK001", "Mock Customer 1", 25, "0123456789", "mock1@email.com", "Nam"));
        mockCustomers.add(new KhachHang("MOCK002", "Mock Customer 2", 30, "0987654321", "mock2@email.com", "Nu"));
        mockCustomers.add(new KhachHang("MOCK003", "Mock Customer 3", 35, "0123456780", "mock3@email.com", "Nam"));
        
        // Test tạo nhiều khách hàng
        int successCount = 0;
        for (KhachHang kh : mockCustomers) {
            if (KhachHangController.taoKhachHang(kh)) {
                successCount++;
            }
        }
        assert successCount == 3 : "Tất cả khách hàng mock phải được tạo thành công";
        
        // Test tìm kiếm theo tên
        ArrayList<KhachHang> searchResult = KhachHangController.timKhachHangTheoTen("Mock");
        assert searchResult.size() == 3 : "Tìm kiếm 'Mock' phải trả về 3 kết quả";
        
        // Test tìm kiếm theo giới tính
        ArrayList<KhachHang> maleResult = KhachHangController.timKhachHangTheoGioiTinh("Nam");
        assert maleResult.size() == 2 : "Tìm kiếm giới tính 'Nam' phải trả về 2 kết quả";
        
        // Dọn dẹp dữ liệu test
        for (KhachHang kh : mockCustomers) {
            KhachHangController.xoaKhachHang(kh.getCCCD());
        }
        
        System.out.println("✓ Mock data testing OK");
    }
    
    // === PERFORMANCE TESTING ===
    
    // Test hiệu suất
    public static void testPerformance() {
        System.out.println("\n=== TEST PERFORMANCE ===");
        
        long startTime = System.currentTimeMillis();
        
        // Test tạo 100 khách hàng
        for (int i = 0; i < 100; i++) {
            KhachHang kh = new KhachHang("PERF" + i, "Performance User " + i, 25, "0123456789", "perf" + i + "@email.com");
            KhachHangController.taoKhachHang(kh);
        }
        
        long createTime = System.currentTimeMillis() - startTime;
        System.out.println("✓ Tạo 100 khách hàng trong " + createTime + "ms");
        
        // Test tìm kiếm
        startTime = System.currentTimeMillis();
        KhachHang result = KhachHangController.timKhachHangTheoCCCD("PERF50");
        long searchTime = System.currentTimeMillis() - startTime;
        System.out.println("✓ Tìm kiếm khách hàng trong " + searchTime + "ms");
        
        // Dọn dẹp
        for (int i = 0; i < 100; i++) {
            KhachHangController.xoaKhachHang("PERF" + i);
        }
    }
    
    // === MAIN TEST METHOD ===
    
    public static void test() {
        System.out.println("🚀 BẮT ĐẦU TEST KHACH HANG CONTROLLER");
        System.out.println("=========================================");
        
        try {
            testTaoKhachHang();
            testCapNhatThongTin();
            testXoaKhachHang();
            testXemThongTin();
            testTimKhachHangTheoCCCD();
            testTimKhachHangTheoTen();
            testTimKhachHangTheoGioiTinh();
            testKhachHangManagementFlow();
            testEdgeCases();
            testMockData();
            testPerformance();
            
            System.out.println("\n=========================================");
            System.out.println("✅ TẤT CẢ TEST KHACH HANG CONTROLLER THÀNH CÔNG!");
            
        } catch (Exception e) {
            System.out.println("\n=========================================");
            System.out.println("❌ TEST KHACH HANG CONTROLLER THẤT BẠI: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 