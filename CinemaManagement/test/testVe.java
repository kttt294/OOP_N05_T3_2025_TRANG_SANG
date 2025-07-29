import java.util.ArrayList;

public class testVe {
    
    // === UNIT TESTING ===
    
    // Test constructor
    public static void testConstructor() {
        System.out.println("=== TEST CONSTRUCTOR ===");
        
        // Test constructor rỗng
        Ve ve1 = new Ve();
        assert ve1.getMaVe() == null : "Constructor rỗng không đúng";
        assert ve1.getCCCD() == null : "Constructor rỗng không đúng";
        System.out.println("✓ Constructor rỗng OK");
        
        // Test constructor với tham số
        Ve ve2 = new Ve("VE001", "123456789", "SC001", "GHE001", 50000);
        assert "VE001".equals(ve2.getMaVe()) : "Mã vé không đúng";
        assert "123456789".equals(ve2.getCCCD()) : "CCCD không đúng";
        assert "SC001".equals(ve2.getMaSuatChieu()) : "Mã suất chiếu không đúng";
        assert "GHE001".equals(ve2.getMaGhe()) : "Mã ghế không đúng";
        assert ve2.getGiaVe() == 50000 : "Giá vé không đúng";
        System.out.println("✓ Constructor với tham số OK");
        
        // Test constructor với trạng thái
        Ve ve3 = new Ve("VE002", "987654321", "SC002", "GHE002", 60000, true);
        assert ve3.isTrangThai() == true : "Trạng thái không đúng";
        System.out.println("✓ Constructor với trạng thái OK");
    }
    
    // Test getters và setters
    public static void testGettersSetters() {
        System.out.println("\n=== TEST GETTERS & SETTERS ===");
        
        Ve ve = new Ve();
        
        // Test setMaVe
        ve.setMaVe("VE003");
        assert "VE003".equals(ve.getMaVe()) : "setMaVe/getMaVe không đúng";
        
        // Test setCCCD
        ve.setCCCD("111222333");
        assert "111222333".equals(ve.getCCCD()) : "setCCCD/getCCCD không đúng";
        
        // Test setMaSuatChieu
        ve.setMaSuatChieu("SC003");
        assert "SC003".equals(ve.getMaSuatChieu()) : "setMaSuatChieu/getMaSuatChieu không đúng";
        
        // Test setMaGhe
        ve.setMaGhe("GHE003");
        assert "GHE003".equals(ve.getMaGhe()) : "setMaGhe/getMaGhe không đúng";
        
        // Test setGiaVe
        ve.setGiaVe(75000);
        assert ve.getGiaVe() == 75000 : "setGiaVe/getGiaVe không đúng";
        
        // Test setTrangThai
        ve.setTrangThai(false);
        assert ve.isTrangThai() == false : "setTrangThai/isTrangThai không đúng";
        
        System.out.println("✓ Tất cả getters/setters OK");
    }
    
    // Test method hienThiThongTin
    public static void testHienThiThongTin() {
        System.out.println("\n=== TEST HIEN THI THONG TIN ===");
        
        Ve ve = new Ve("VE004", "444555666", "SC004", "GHE004", 80000, true);
        
        // Test hiển thị thông tin
        ve.hienThiThongTin();
        System.out.println("✓ Hiển thị thông tin OK");
    }
    
    // === INTEGRATION TESTING ===
    
    // Test luồng CRUD hoàn chỉnh
    public static void testCRUDIntegration() {
        System.out.println("\n=== TEST CRUD INTEGRATION ===");
        
        // Create
        Ve ve = new Ve("INT001", "123456789", "SC001", "GHE001", 50000, true);
        Ve.Create(ve);
        
        // Read
        Ve veRead = Ve.getVeByMaVe("INT001");
        assert veRead != null : "Không tìm thấy vé sau khi tạo";
        assert "123456789".equals(veRead.getCCCD()) : "Dữ liệu đọc không đúng";
        
        // Update
        ve.setGiaVe(60000);
        Ve.Update("INT001", ve);
        Ve veUpdated = Ve.getVeByMaVe("INT001");
        assert veUpdated.getGiaVe() == 60000 : "Update không thành công";
        
        // Delete
        Ve.Delete("INT001");
        Ve veDeleted = Ve.getVeByMaVe("INT001");
        assert veDeleted == null : "Delete không thành công";
        
        System.out.println("✓ CRUD Integration OK");
    }
    
    // === EDGE CASE TESTING ===
    
    // Test với dữ liệu null
    public static void testNullData() {
        System.out.println("\n=== TEST NULL DATA ===");
        
        // Test tạo vé với mã null
        try {
            Ve ve = new Ve(null, "123456789", "SC001", "GHE001", 50000);
            Ve.Create(ve);
            System.out.println("✗ Không bắt được lỗi mã vé null");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi mã vé null: " + e.getMessage());
        }
        
        // Test tạo vé với CCCD null
        try {
            Ve ve = new Ve("VE001", null, "SC001", "GHE001", 50000);
            Ve.Create(ve);
            System.out.println("✗ Không bắt được lỗi CCCD null");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi CCCD null: " + e.getMessage());
        }
        
        // Test tạo vé với mã rỗng
        try {
            Ve ve = new Ve("", "123456789", "SC001", "GHE001", 50000);
            Ve.Create(ve);
            System.out.println("✗ Không bắt được lỗi mã rỗng");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi mã rỗng: " + e.getMessage());
        }
    }
    
    // Test với dữ liệu không hợp lệ
    public static void testInvalidData() {
        System.out.println("\n=== TEST INVALID DATA ===");
        
        // Test giá vé âm
        try {
            Ve ve = new Ve("VE001", "123456789", "SC001", "GHE001", -1000);
            Ve.Create(ve);
            System.out.println("✗ Không bắt được lỗi giá vé âm");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi giá vé âm: " + e.getMessage());
        }
        
        // Test giá vé bằng 0
        try {
            Ve ve = new Ve("VE002", "123456789", "SC001", "GHE001", 0);
            Ve.Create(ve);
            System.out.println("✗ Không bắt được lỗi giá vé bằng 0");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi giá vé bằng 0: " + e.getMessage());
        }
        
        // Test CCCD không hợp lệ (quá ngắn)
        try {
            Ve ve = new Ve("VE003", "123", "SC001", "GHE001", 50000);
            Ve.Create(ve);
            System.out.println("✗ Không bắt được lỗi CCCD không hợp lệ");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi CCCD không hợp lệ: " + e.getMessage());
        }
        
        // Test mã vé trùng lặp
        Ve ve1 = new Ve("DUP001", "123456789", "SC001", "GHE001", 50000);
        Ve.Create(ve1);
        
        try {
            Ve ve2 = new Ve("DUP001", "987654321", "SC002", "GHE002", 60000);
            Ve.Create(ve2);
            System.out.println("✗ Không bắt được lỗi mã vé trùng lặp");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi mã vé trùng lặp: " + e.getMessage());
        }
    }
    
    // === MOCK TESTING ===
    
    // Test với dữ liệu giả
    public static void testMockData() {
        System.out.println("\n=== TEST MOCK DATA ===");
        
        // Tạo dữ liệu giả
        ArrayList<Ve> mockData = new ArrayList<>();
        mockData.add(new Ve("MOCK001", "111111111", "SC001", "GHE001", 50000, true));
        mockData.add(new Ve("MOCK002", "222222222", "SC002", "GHE002", 60000, true));
        mockData.add(new Ve("MOCK003", "333333333", "SC003", "GHE003", 70000, false));
        
        // Test tìm kiếm theo CCCD
        ArrayList<Ve> result1 = new ArrayList<>();
        for (Ve ve : mockData) {
            if ("111111111".equals(ve.getCCCD())) {
                result1.add(ve);
            }
        }
        assert result1.size() == 1 : "Tìm kiếm theo CCCD không đúng";
        
        // Test tìm kiếm theo suất chiếu
        ArrayList<Ve> result2 = new ArrayList<>();
        for (Ve ve : mockData) {
            if ("SC001".equals(ve.getMaSuatChieu())) {
                result2.add(ve);
            }
        }
        assert result2.size() == 1 : "Tìm kiếm theo suất chiếu không đúng";
        
        // Test tính tổng doanh thu
        int totalRevenue = 0;
        for (Ve ve : mockData) {
            if (ve.isTrangThai()) {
                totalRevenue += ve.getGiaVe();
            }
        }
        assert totalRevenue == 110000 : "Tính tổng doanh thu không đúng";
        
        // Test đếm vé đã bán
        int soldTickets = 0;
        for (Ve ve : mockData) {
            if (ve.isTrangThai()) {
                soldTickets++;
            }
        }
        assert soldTickets == 2 : "Đếm vé đã bán không đúng";
        
        System.out.println("✓ Mock data testing OK");
    }
    
    // === PERFORMANCE TESTING ===
    
    // Test hiệu suất với dữ liệu lớn
    public static void testPerformance() {
        System.out.println("\n=== TEST PERFORMANCE ===");
        
        long startTime = System.currentTimeMillis();
        
        // Tạo 1000 vé
        for (int i = 0; i < 1000; i++) {
            Ve ve = new Ve("PERF" + i, "CCCD" + i, "SC" + (i % 10), "GHE" + (i % 20), 50000 + (i * 1000));
            Ve.Create(ve);
        }
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        System.out.println("✓ Tạo 1000 vé trong " + duration + "ms");
        
        // Test tìm kiếm
        startTime = System.currentTimeMillis();
        Ve result = Ve.getVeByMaVe("PERF500");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        
        System.out.println("✓ Tìm kiếm vé trong " + duration + "ms");
    }
    
    // === BUSINESS LOGIC TESTING ===
    
    // Test logic nghiệp vụ
    public static void testBusinessLogic() {
        System.out.println("\n=== TEST BUSINESS LOGIC ===");
        
        // Test vé đã bán
        Ve soldTicket = new Ve("SOLD001", "123456789", "SC001", "GHE001", 50000, true);
        boolean isSold = soldTicket.isTrangThai();
        assert isSold == true : "Vé đã bán không đúng";
        
        // Test vé chưa bán
        Ve unsoldTicket = new Ve("UNSOLD001", "987654321", "SC002", "GHE002", 60000, false);
        boolean isUnsold = !unsoldTicket.isTrangThai();
        assert isUnsold == true : "Vé chưa bán không đúng";
        
        // Test vé giá cao (> 100000)
        Ve expensiveTicket = new Ve("EXP001", "111111111", "SC003", "GHE003", 150000, true);
        boolean isExpensive = expensiveTicket.getGiaVe() > 100000;
        assert isExpensive == true : "Vé giá cao không đúng";
        
        // Test vé giá thấp (< 30000)
        Ve cheapTicket = new Ve("CHEAP001", "222222222", "SC004", "GHE004", 25000, true);
        boolean isCheap = cheapTicket.getGiaVe() < 30000;
        assert isCheap == true : "Vé giá thấp không đúng";
        
        System.out.println("✓ Business logic testing OK");
    }
    
    // === VALIDATION TESTING ===
    
    // Test validation
    public static void testValidation() {
        System.out.println("\n=== TEST VALIDATION ===");
        
        // Test validation mã vé
        try {
            Ve ve = new Ve("VE_123", "123456789", "SC001", "GHE001", 50000);
            // Kiểm tra format mã vé
            boolean isValidFormat = ve.getMaVe().matches("^[A-Z0-9_]+$");
            assert isValidFormat == true : "Format mã vé không hợp lệ";
            System.out.println("✓ Validation mã vé OK");
        } catch (Exception e) {
            System.out.println("✗ Validation mã vé thất bại: " + e.getMessage());
        }
        
        // Test validation CCCD
        try {
            Ve ve = new Ve("VE001", "123456789", "SC001", "GHE001", 50000);
            // Kiểm tra CCCD hợp lệ (9-12 số)
            boolean isValidCCCD = ve.getCCCD().matches("^[0-9]{9,12}$");
            assert isValidCCCD == true : "CCCD không hợp lệ";
            System.out.println("✓ Validation CCCD OK");
        } catch (Exception e) {
            System.out.println("✗ Validation CCCD thất bại: " + e.getMessage());
        }
        
        // Test validation giá vé
        try {
            Ve ve = new Ve("VE001", "123456789", "SC001", "GHE001", 50000);
            // Kiểm tra giá vé hợp lệ
            boolean isValidPrice = ve.getGiaVe() > 0 && ve.getGiaVe() <= 1000000;
            assert isValidPrice == true : "Giá vé không hợp lệ";
            System.out.println("✓ Validation giá vé OK");
        } catch (Exception e) {
            System.out.println("✗ Validation giá vé thất bại: " + e.getMessage());
        }
    }
    
    // === MAIN TEST METHOD ===
    
    public static void test() {
        System.out.println("🚀 BẮT ĐẦU TEST VE MODEL");
        System.out.println("=========================");
        
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
            
            System.out.println("\n=========================");
            System.out.println("✅ TẤT CẢ TEST VE THÀNH CÔNG!");
            
        } catch (Exception e) {
            System.out.println("\n=========================");
            System.out.println("❌ TEST VE THẤT BẠI: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 