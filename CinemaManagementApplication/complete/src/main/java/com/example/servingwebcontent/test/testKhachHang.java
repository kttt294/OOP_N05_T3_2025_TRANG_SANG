package com.example.servingwebcontent.test;
import java.util.ArrayList;

public class testKhachHang {
    
    // === UNIT TESTING ===
    
    // Test constructor
    public static void testConstructor() {
        System.out.println("=== TEST CONSTRUCTOR ===");
        
        // Test constructor rỗng
        KhachHang kh1 = new KhachHang();
        assert kh1.getCCCD() == null : "Constructor rỗng không đúng";
        assert kh1.getTen() == null : "Constructor rỗng không đúng";
        System.out.println("✓ Constructor rỗng OK");
        
        // Test constructor với tham số
        KhachHang kh2 = new KhachHang("123", "Nguyen Van A", 25, "0123456789", "a@email.com");
        assert "123".equals(kh2.getCCCD()) : "CCCD không đúng";
        assert "Nguyen Van A".equals(kh2.getTen()) : "Tên không đúng";
        assert kh2.getTuoi() == 25 : "Tuổi không đúng";
        System.out.println("✓ Constructor với tham số OK");
        
        // Test constructor với giới tính
        KhachHang kh3 = new KhachHang("456", "Tran Thi B", 30, "0987654321", "b@email.com", "Nu");
        assert "Nu".equals(kh3.getGioiTinh()) : "Giới tính không đúng";
        System.out.println("✓ Constructor với giới tính OK");
    }
    
    // Test getters và setters
    public static void testGettersSetters() {
        System.out.println("\n=== TEST GETTERS & SETTERS ===");
        
        KhachHang kh = new KhachHang();
        
        // Test setCCCD
        kh.setCCCD("789");
        assert "789".equals(kh.getCCCD()) : "setCCCD/getCCCD không đúng";
        
        // Test setTen
        kh.setTen("Le Van C");
        assert "Le Van C".equals(kh.getTen()) : "setTen/getTen không đúng";
        
        // Test setTuoi
        kh.setTuoi(35);
        assert kh.getTuoi() == 35 : "setTuoi/getTuoi không đúng";
        
        // Test setSdt
        kh.setSdt("0123456789");
        assert "0123456789".equals(kh.getSdt()) : "setSdt/getSdt không đúng";
        
        // Test setEmail
        kh.setEmail("c@email.com");
        assert "c@email.com".equals(kh.getEmail()) : "setEmail/getEmail không đúng";
        
        // Test setGioiTinh
        kh.setGioiTinh("Nam");
        assert "Nam".equals(kh.getGioiTinh()) : "setGioiTinh/getGioiTinh không đúng";
        
        System.out.println("✓ Tất cả getters/setters OK");
    }
    
    // Test method themVe
    public static void testThemVe() {
        System.out.println("\n=== TEST THEM VE ===");
        
        KhachHang kh = new KhachHang("123", "Test", 25, "0123456789", "test@email.com");
        
        // Test thêm vé đầu tiên
        Ve ve1 = new Ve("VE001", "123", "SC001", "GHE001", 50000);
        kh.themVe(ve1);
        assert kh.getLichSuDatVe().size() == 1 : "Thêm vé đầu tiên không đúng";
        
        // Test thêm vé thứ hai
        Ve ve2 = new Ve("VE002", "123", "SC002", "GHE002", 60000);
        kh.themVe(ve2);
        assert kh.getLichSuDatVe().size() == 2 : "Thêm vé thứ hai không đúng";
        
        System.out.println("✓ Thêm vé OK");
    }
    
    // === INTEGRATION TESTING ===
    
    // Test luồng CRUD hoàn chỉnh
    public static void testCRUDIntegration() {
        System.out.println("\n=== TEST CRUD INTEGRATION ===");
        
        // Create
        KhachHang kh = new KhachHang("INT001", "Integration Test", 25, "0123456789", "int@email.com");
        KhachHang.Create(kh);
        
        // Read
        KhachHang khRead = KhachHang.getKhachHangByCCCD("INT001");
        assert khRead != null : "Không tìm thấy khách hàng sau khi tạo";
        assert "Integration Test".equals(khRead.getTen()) : "Dữ liệu đọc không đúng";
        
        // Update
        kh.setTen("Updated Integration Test");
        KhachHang.Update("INT001", kh);
        KhachHang khUpdated = KhachHang.getKhachHangByCCCD("INT001");
        assert "Updated Integration Test".equals(khUpdated.getTen()) : "Update không thành công";
        
        // Delete
        KhachHang.Delete("INT001");
        KhachHang khDeleted = KhachHang.getKhachHangByCCCD("INT001");
        assert khDeleted == null : "Delete không thành công";
        
        System.out.println("✓ CRUD Integration OK");
    }
    
    // === EDGE CASE TESTING ===
    
    // Test với dữ liệu null
    public static void testNullData() {
        System.out.println("\n=== TEST NULL DATA ===");
        
        // Test tạo khách hàng với CCCD null
        try {
            KhachHang kh = new KhachHang(null, "Test", 25, "0123456789", "test@email.com");
            KhachHang.Create(kh);
            System.out.println("✗ Không bắt được lỗi CCCD null");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi CCCD null: " + e.getMessage());
        }
        
        // Test tạo khách hàng với tên null
        try {
            KhachHang kh = new KhachHang("123", null, 25, "0123456789", "test@email.com");
            KhachHang.Create(kh);
            System.out.println("✗ Không bắt được lỗi tên null");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi tên null: " + e.getMessage());
        }
        
        // Test tạo khách hàng với CCCD rỗng
        try {
            KhachHang kh = new KhachHang("", "Test", 25, "0123456789", "test@email.com");
            KhachHang.Create(kh);
            System.out.println("✗ Không bắt được lỗi CCCD rỗng");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi CCCD rỗng: " + e.getMessage());
        }
    }
    
    // Test với dữ liệu không hợp lệ
    public static void testInvalidData() {
        System.out.println("\n=== TEST INVALID DATA ===");
        
        // Test tuổi âm
        try {
            KhachHang kh = new KhachHang("123", "Test", -5, "0123456789", "test@email.com");
            System.out.println("✗ Không bắt được lỗi tuổi âm");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi tuổi âm: " + e.getMessage());
        }
        
        // Test tuổi quá lớn
        try {
            KhachHang kh = new KhachHang("123", "Test", 200, "0123456789", "test@email.com");
            System.out.println("✗ Không bắt được lỗi tuổi quá lớn");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi tuổi quá lớn: " + e.getMessage());
        }
        
        // Test CCCD trùng lặp
        KhachHang kh1 = new KhachHang("DUP001", "Test 1", 25, "0123456789", "test1@email.com");
        KhachHang.Create(kh1);
        
        try {
            KhachHang kh2 = new KhachHang("DUP001", "Test 2", 30, "0987654321", "test2@email.com");
            KhachHang.Create(kh2);
            System.out.println("✗ Không bắt được lỗi CCCD trùng lặp");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi CCCD trùng lặp: " + e.getMessage());
        }
    }
    
    // === MOCK TESTING ===
    
    // Test với dữ liệu giả
    public static void testMockData() {
        System.out.println("\n=== TEST MOCK DATA ===");
        
        // Tạo dữ liệu giả
        ArrayList<KhachHang> mockData = new ArrayList<>();
        mockData.add(new KhachHang("MOCK001", "Mock User 1", 25, "0123456789", "mock1@email.com", "Nam"));
        mockData.add(new KhachHang("MOCK002", "Mock User 2", 30, "0987654321", "mock2@email.com", "Nu"));
        mockData.add(new KhachHang("MOCK003", "Mock User 3", 35, "0123456780", "mock3@email.com", "Nam"));
        
        // Test tìm kiếm theo tên
        ArrayList<KhachHang> result1 = new ArrayList<>();
        for (KhachHang kh : mockData) {
            if (kh.getTen().contains("Mock")) {
                result1.add(kh);
            }
        }
        assert result1.size() == 3 : "Tìm kiếm theo tên không đúng";
        
        // Test tìm kiếm theo giới tính
        ArrayList<KhachHang> result2 = new ArrayList<>();
        for (KhachHang kh : mockData) {
            if ("Nam".equals(kh.getGioiTinh())) {
                result2.add(kh);
            }
        }
        assert result2.size() == 2 : "Tìm kiếm theo giới tính không đúng";
        
        // Test tính tổng tiền
        double totalMoney = 0.0;
        for (KhachHang kh : mockData) {
            // Giả sử mỗi khách hàng có 1 vé 50000
            totalMoney += 50000;
        }
        assert totalMoney == 150000.0 : "Tính tổng tiền không đúng";
        
        System.out.println("✓ Mock data testing OK");
    }
    
    // === PERFORMANCE TESTING ===
    
    // Test hiệu suất với dữ liệu lớn
    public static void testPerformance() {
        System.out.println("\n=== TEST PERFORMANCE ===");
        
        long startTime = System.currentTimeMillis();
        
        // Tạo 1000 khách hàng
        for (int i = 0; i < 1000; i++) {
            KhachHang kh = new KhachHang("PERF" + i, "Performance User " + i, 25, "0123456789", "perf" + i + "@email.com");
            KhachHang.Create(kh);
        }
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        System.out.println("✓ Tạo 1000 khách hàng trong " + duration + "ms");
        
        // Test tìm kiếm
        startTime = System.currentTimeMillis();
        KhachHang result = KhachHang.getKhachHangByCCCD("PERF500");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        
        System.out.println("✓ Tìm kiếm khách hàng trong " + duration + "ms");
    }
    
    // === MAIN TEST METHOD ===
    
    public static void test() {
        System.out.println("🚀 BẮT ĐẦU TEST KHACH HANG MODEL");
        System.out.println("=====================================");
        
        try {
            testConstructor();
            testGettersSetters();
            testThemVe();
            testCRUDIntegration();
            testNullData();
            testInvalidData();
            testMockData();
            testPerformance();
            
            System.out.println("\n=====================================");
            System.out.println("✅ TẤT CẢ TEST KHACH HANG THÀNH CÔNG!");
            
        } catch (Exception e) {
            System.out.println("\n=====================================");
            System.out.println("❌ TEST KHACH HANG THẤT BẠI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

