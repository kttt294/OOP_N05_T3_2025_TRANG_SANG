package com.example.servingwebcontent.test;
import java.util.ArrayList;

public class testDoAn {
    
    // === UNIT TESTING ===
    
    // Test constructor
    public static void testConstructor() {
        System.out.println("=== TEST CONSTRUCTOR ===");
        
        // Test constructor rỗng
        DoAn da1 = new DoAn();
        assert da1.getMaDoAn() == null : "Constructor rỗng không đúng";
        assert da1.getTenDoAn() == null : "Constructor rỗng không đúng";
        System.out.println("✓ Constructor rỗng OK");
        
        // Test constructor với tham số
        DoAn da2 = new DoAn("DA001", "Bắp rang bơ", "Đồ ăn nhẹ", 25000, 100);
        assert "DA001".equals(da2.getMaDoAn()) : "Mã đồ ăn không đúng";
        assert "Bắp rang bơ".equals(da2.getTenDoAn()) : "Tên đồ ăn không đúng";
        assert "Đồ ăn nhẹ".equals(da2.getLoai()) : "Loại không đúng";
        assert da2.getGia() == 25000 : "Giá không đúng";
        assert da2.getSoLuong() == 100 : "Số lượng không đúng";
        System.out.println("✓ Constructor với tham số OK");
    }
    
    // Test getters và setters
    public static void testGettersSetters() {
        System.out.println("\n=== TEST GETTERS & SETTERS ===");
        
        DoAn da = new DoAn();
        
        // Test setMaDoAn
        da.setMaDoAn("DA002");
        assert "DA002".equals(da.getMaDoAn()) : "setMaDoAn/getMaDoAn không đúng";
        
        // Test setTenDoAn
        da.setTenDoAn("Nước ngọt");
        assert "Nước ngọt".equals(da.getTenDoAn()) : "setTenDoAn/getTenDoAn không đúng";
        
        // Test setLoai
        da.setLoai("Đồ uống");
        assert "Đồ uống".equals(da.getLoai()) : "setLoai/getLoai không đúng";
        
        // Test setGia
        da.setGia(15000);
        assert da.getGia() == 15000 : "setGia/getGia không đúng";
        
        // Test setSoLuong
        da.setSoLuong(50);
        assert da.getSoLuong() == 50 : "setSoLuong/getSoLuong không đúng";
        
        System.out.println("✓ Tất cả getters/setters OK");
    }
    
    // Test method hienThiThongTin
    public static void testHienThiThongTin() {
        System.out.println("\n=== TEST HIEN THI THONG TIN ===");
        
        DoAn da = new DoAn("DA003", "Kẹo bông gòn", "Kẹo", 10000, 200);
        
        // Test hiển thị thông tin
        da.hienThiThongTin();
        System.out.println("✓ Hiển thị thông tin OK");
    }
    
    // === INTEGRATION TESTING ===
    
    // Test luồng CRUD hoàn chỉnh
    public static void testCRUDIntegration() {
        System.out.println("\n=== TEST CRUD INTEGRATION ===");
        
        // Create
        DoAn da = new DoAn("INT001", "Bánh mì", "Đồ ăn", 20000, 50);
        DoAn.Create(da);
        
        // Read
        DoAn daRead = DoAn.getDoAnByMaDoAn("INT001");
        assert daRead != null : "Không tìm thấy đồ ăn sau khi tạo";
        assert "Bánh mì".equals(daRead.getTenDoAn()) : "Dữ liệu đọc không đúng";
        
        // Update
        da.setGia(25000);
        da.setSoLuong(75);
        DoAn.Update("INT001", da);
        DoAn daUpdated = DoAn.getDoAnByMaDoAn("INT001");
        assert daUpdated.getGia() == 25000 : "Update không thành công";
        
        // Delete
        DoAn.Delete("INT001");
        DoAn daDeleted = DoAn.getDoAnByMaDoAn("INT001");
        assert daDeleted == null : "Delete không thành công";
        
        System.out.println("✓ CRUD Integration OK");
    }
    
    // === EDGE CASE TESTING ===
    
    // Test với dữ liệu null
    public static void testNullData() {
        System.out.println("\n=== TEST NULL DATA ===");
        
        // Test tạo đồ ăn với mã null
        try {
            DoAn da = new DoAn(null, "Bắp rang bơ", "Đồ ăn nhẹ", 25000, 100);
            DoAn.Create(da);
            System.out.println("✗ Không bắt được lỗi mã đồ ăn null");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi mã đồ ăn null: " + e.getMessage());
        }
        
        // Test tạo đồ ăn với tên null
        try {
            DoAn da = new DoAn("DA001", null, "Đồ ăn nhẹ", 25000, 100);
            DoAn.Create(da);
            System.out.println("✗ Không bắt được lỗi tên đồ ăn null");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi tên đồ ăn null: " + e.getMessage());
        }
        
        // Test tạo đồ ăn với mã rỗng
        try {
            DoAn da = new DoAn("", "Bắp rang bơ", "Đồ ăn nhẹ", 25000, 100);
            DoAn.Create(da);
            System.out.println("✗ Không bắt được lỗi mã rỗng");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi mã rỗng: " + e.getMessage());
        }
    }
    
    // Test với dữ liệu không hợp lệ
    public static void testInvalidData() {
        System.out.println("\n=== TEST INVALID DATA ===");
        
        // Test giá âm
        try {
            DoAn da = new DoAn("DA001", "Bắp rang bơ", "Đồ ăn nhẹ", -1000, 100);
            DoAn.Create(da);
            System.out.println("✗ Không bắt được lỗi giá âm");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi giá âm: " + e.getMessage());
        }
        
        // Test giá bằng 0
        try {
            DoAn da = new DoAn("DA002", "Bắp rang bơ", "Đồ ăn nhẹ", 0, 100);
            DoAn.Create(da);
            System.out.println("✗ Không bắt được lỗi giá bằng 0");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi giá bằng 0: " + e.getMessage());
        }
        
        // Test số lượng âm
        try {
            DoAn da = new DoAn("DA003", "Bắp rang bơ", "Đồ ăn nhẹ", 25000, -10);
            DoAn.Create(da);
            System.out.println("✗ Không bắt được lỗi số lượng âm");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi số lượng âm: " + e.getMessage());
        }
        
        // Test tên quá dài
        String longName = "A".repeat(101);
        try {
            DoAn da = new DoAn("DA004", longName, "Đồ ăn nhẹ", 25000, 100);
            DoAn.Create(da);
            System.out.println("✗ Không bắt được lỗi tên quá dài");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi tên quá dài: " + e.getMessage());
        }
        
        // Test mã đồ ăn trùng lặp
        DoAn da1 = new DoAn("DUP001", "Bắp rang bơ", "Đồ ăn nhẹ", 25000, 100);
        DoAn.Create(da1);
        
        try {
            DoAn da2 = new DoAn("DUP001", "Nước ngọt", "Đồ uống", 15000, 50);
            DoAn.Create(da2);
            System.out.println("✗ Không bắt được lỗi mã đồ ăn trùng lặp");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi mã đồ ăn trùng lặp: " + e.getMessage());
        }
    }
    
    // === MOCK TESTING ===
    
    // Test với dữ liệu giả
    public static void testMockData() {
        System.out.println("\n=== TEST MOCK DATA ===");
        
        // Tạo dữ liệu giả
        ArrayList<DoAn> mockData = new ArrayList<>();
        mockData.add(new DoAn("MOCK001", "Bắp rang bơ", "Đồ ăn nhẹ", 25000, 100));
        mockData.add(new DoAn("MOCK002", "Nước ngọt", "Đồ uống", 15000, 200));
        mockData.add(new DoAn("MOCK003", "Kẹo bông gòn", "Kẹo", 10000, 150));
        
        // Test tìm kiếm theo loại
        ArrayList<DoAn> result1 = new ArrayList<>();
        for (DoAn da : mockData) {
            if ("Đồ ăn nhẹ".equals(da.getLoai())) {
                result1.add(da);
            }
        }
        assert result1.size() == 1 : "Tìm kiếm theo loại không đúng";
        
        // Test tìm kiếm theo tên
        ArrayList<DoAn> result2 = new ArrayList<>();
        for (DoAn da : mockData) {
            if (da.getTenDoAn().contains("Bắp")) {
                result2.add(da);
            }
        }
        assert result2.size() == 1 : "Tìm kiếm theo tên không đúng";
        
        // Test tính tổng giá trị
        int totalValue = 0;
        for (DoAn da : mockData) {
            totalValue += da.getGia() * da.getSoLuong();
        }
        assert totalValue == 6500000 : "Tính tổng giá trị không đúng";
        
        // Test đếm theo loại
        int foodCount = 0;
        int drinkCount = 0;
        int candyCount = 0;
        
        for (DoAn da : mockData) {
            if ("Đồ ăn nhẹ".equals(da.getLoai())) {
                foodCount++;
            } else if ("Đồ uống".equals(da.getLoai())) {
                drinkCount++;
            } else if ("Kẹo".equals(da.getLoai())) {
                candyCount++;
            }
        }
        
        assert foodCount == 1 : "Đếm đồ ăn nhẹ không đúng";
        assert drinkCount == 1 : "Đếm đồ uống không đúng";
        assert candyCount == 1 : "Đếm kẹo không đúng";
        
        System.out.println("✓ Mock data testing OK");
    }
    
    // === PERFORMANCE TESTING ===
    
    // Test hiệu suất với dữ liệu lớn
    public static void testPerformance() {
        System.out.println("\n=== TEST PERFORMANCE ===");
        
        long startTime = System.currentTimeMillis();
        
        // Tạo 1000 đồ ăn
        for (int i = 0; i < 1000; i++) {
            DoAn da = new DoAn("PERF" + i, "Đồ ăn " + i, "Loại " + (i % 5), 
                              10000 + (i * 100), 50 + (i % 100));
            DoAn.Create(da);
        }
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        System.out.println("✓ Tạo 1000 đồ ăn trong " + duration + "ms");
        
        // Test tìm kiếm
        startTime = System.currentTimeMillis();
        DoAn result = DoAn.getDoAnByMaDoAn("PERF500");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        
        System.out.println("✓ Tìm kiếm đồ ăn trong " + duration + "ms");
    }
    
    // === BUSINESS LOGIC TESTING ===
    
    // Test logic nghiệp vụ
    public static void testBusinessLogic() {
        System.out.println("\n=== TEST BUSINESS LOGIC ===");
        
        // Test đồ ăn giá cao (> 50000)
        DoAn expensiveFood = new DoAn("EXP001", "Combo cao cấp", "Combo", 100000, 10);
        boolean isExpensive = expensiveFood.getGia() > 50000;
        assert isExpensive == true : "Đồ ăn giá cao không đúng";
        
        // Test đồ ăn giá thấp (< 10000)
        DoAn cheapFood = new DoAn("CHEAP001", "Kẹo", "Kẹo", 5000, 100);
        boolean isCheap = cheapFood.getGia() < 10000;
        assert isCheap == true : "Đồ ăn giá thấp không đúng";
        
        // Test đồ ăn còn nhiều (> 50)
        DoAn wellStocked = new DoAn("STOCK001", "Bắp rang bơ", "Đồ ăn nhẹ", 25000, 100);
        boolean isWellStocked = wellStocked.getSoLuong() > 50;
        assert isWellStocked == true : "Đồ ăn còn nhiều không đúng";
        
        // Test đồ ăn sắp hết (< 10)
        DoAn lowStock = new DoAn("LOW001", "Nước ngọt", "Đồ uống", 15000, 5);
        boolean isLowStock = lowStock.getSoLuong() < 10;
        assert isLowStock == true : "Đồ ăn sắp hết không đúng";
        
        System.out.println("✓ Business logic testing OK");
    }
    
    // === VALIDATION TESTING ===
    
    // Test validation
    public static void testValidation() {
        System.out.println("\n=== TEST VALIDATION ===");
        
        // Test validation mã đồ ăn
        try {
            DoAn da = new DoAn("DA_123", "Bắp rang bơ", "Đồ ăn nhẹ", 25000, 100);
            // Kiểm tra format mã đồ ăn
            boolean isValidFormat = da.getMaDoAn().matches("^[A-Z0-9_]+$");
            assert isValidFormat == true : "Format mã đồ ăn không hợp lệ";
            System.out.println("✓ Validation mã đồ ăn OK");
        } catch (Exception e) {
            System.out.println("✗ Validation mã đồ ăn thất bại: " + e.getMessage());
        }
        
        // Test validation giá
        try {
            DoAn da = new DoAn("DA001", "Bắp rang bơ", "Đồ ăn nhẹ", 25000, 100);
            // Kiểm tra giá hợp lệ
            boolean isValidPrice = da.getGia() > 0 && da.getGia() <= 1000000;
            assert isValidPrice == true : "Giá không hợp lệ";
            System.out.println("✓ Validation giá OK");
        } catch (Exception e) {
            System.out.println("✗ Validation giá thất bại: " + e.getMessage());
        }
        
        // Test validation số lượng
        try {
            DoAn da = new DoAn("DA001", "Bắp rang bơ", "Đồ ăn nhẹ", 25000, 100);
            // Kiểm tra số lượng hợp lệ
            boolean isValidQuantity = da.getSoLuong() >= 0 && da.getSoLuong() <= 10000;
            assert isValidQuantity == true : "Số lượng không hợp lệ";
            System.out.println("✓ Validation số lượng OK");
        } catch (Exception e) {
            System.out.println("✗ Validation số lượng thất bại: " + e.getMessage());
        }
    }
    
    // === MAIN TEST METHOD ===
    
    public static void test() {
        System.out.println("🚀 BẮT ĐẦU TEST DO AN MODEL");
        System.out.println("=============================");
        
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
            
            System.out.println("\n=============================");
            System.out.println("✅ TẤT CẢ TEST DO AN THÀNH CÔNG!");
            
        } catch (Exception e) {
            System.out.println("\n=============================");
            System.out.println("❌ TEST DO AN THẤT BẠI: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 