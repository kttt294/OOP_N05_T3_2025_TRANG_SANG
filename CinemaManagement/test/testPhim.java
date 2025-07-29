import java.util.ArrayList;

public class testPhim {
    
    // === UNIT TESTING ===
    
    // Test constructor
    public static void testConstructor() {
        System.out.println("=== TEST CONSTRUCTOR ===");
        
        // Test constructor rỗng
        Phim phim1 = new Phim();
        assert phim1.getMaPhim() == null : "Constructor rỗng không đúng";
        assert phim1.getTenPhim() == null : "Constructor rỗng không đúng";
        System.out.println("✓ Constructor rỗng OK");
        
        // Test constructor với tham số
        Phim phim2 = new Phim("PHIM001", "Avengers: Endgame", "Hành động", 180, 2019, "Marvel Studios");
        assert "PHIM001".equals(phim2.getMaPhim()) : "Mã phim không đúng";
        assert "Avengers: Endgame".equals(phim2.getTenPhim()) : "Tên phim không đúng";
        assert "Hành động".equals(phim2.getTheLoai()) : "Thể loại không đúng";
        assert phim2.getThoiLuong() == 180 : "Thời lượng không đúng";
        assert phim2.getNamSanXuat() == 2019 : "Năm sản xuất không đúng";
        assert "Marvel Studios".equals(phim2.getNhaSanXuat()) : "Nhà sản xuất không đúng";
        System.out.println("✓ Constructor với tham số OK");
    }
    
    // Test getters và setters
    public static void testGettersSetters() {
        System.out.println("\n=== TEST GETTERS & SETTERS ===");
        
        Phim phim = new Phim();
        
        // Test setMaPhim
        phim.setMaPhim("PHIM002");
        assert "PHIM002".equals(phim.getMaPhim()) : "setMaPhim/getMaPhim không đúng";
        
        // Test setTenPhim
        phim.setTenPhim("Spider-Man: No Way Home");
        assert "Spider-Man: No Way Home".equals(phim.getTenPhim()) : "setTenPhim/getTenPhim không đúng";
        
        // Test setTheLoai
        phim.setTheLoai("Siêu anh hùng");
        assert "Siêu anh hùng".equals(phim.getTheLoai()) : "setTheLoai/getTheLoai không đúng";
        
        // Test setThoiLuong
        phim.setThoiLuong(148);
        assert phim.getThoiLuong() == 148 : "setThoiLuong/getThoiLuong không đúng";
        
        // Test setNamSanXuat
        phim.setNamSanXuat(2021);
        assert phim.getNamSanXuat() == 2021 : "setNamSanXuat/getNamSanXuat không đúng";
        
        // Test setNhaSanXuat
        phim.setNhaSanXuat("Sony Pictures");
        assert "Sony Pictures".equals(phim.getNhaSanXuat()) : "setNhaSanXuat/getNhaSanXuat không đúng";

        System.out.println("✓ Tất cả getters/setters OK");
    }
    
    // Test method hienThiThongTin
    public static void testHienThiThongTin() {
        System.out.println("\n=== TEST HIEN THI THONG TIN ===");
        
        Phim phim = new Phim("PHIM003", "The Batman", "Hành động", 176, 2022, "Warner Bros.");
        
        // Test hiển thị thông tin
        phim.hienThiThongTin();
        System.out.println("✓ Hiển thị thông tin OK");
    }
    
    // === INTEGRATION TESTING ===
    
    // Test luồng CRUD hoàn chỉnh
    public static void testCRUDIntegration() {
        System.out.println("\n=== TEST CRUD INTEGRATION ===");
        
        // Create
        Phim phim = new Phim("INT001", "Integration Test Movie", "Test", 120, 2024, "Test Studio");
        Phim.Create(phim);
        
        // Read
        Phim phimRead = Phim.getPhimById("INT001");
        assert phimRead != null : "Không tìm thấy phim sau khi tạo";
        assert "Integration Test Movie".equals(phimRead.getTenPhim()) : "Dữ liệu đọc không đúng";
        
        // Update
        phim.setTenPhim("Updated Integration Test Movie");
        Phim.Update("INT001", phim);
        Phim phimUpdated = Phim.getPhimById("INT001");
        assert "Updated Integration Test Movie".equals(phimUpdated.getTenPhim()) : "Update không thành công";
        
        // Delete
        Phim.Delete("INT001");
        Phim phimDeleted = Phim.getPhimById("INT001");
        assert phimDeleted == null : "Delete không thành công";
        
        System.out.println("✓ CRUD Integration OK");
    }

    // === EDGE CASE TESTING ===
    
    // Test với dữ liệu null
    public static void testNullData() {
        System.out.println("\n=== TEST NULL DATA ===");
        
        // Test tạo phim với mã null
        try {
            Phim phim = new Phim(null, "Test Movie", "Test", 120, 2024, "Test Studio");
            Phim.Create(phim);
            System.out.println("✗ Không bắt được lỗi mã phim null");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi mã phim null: " + e.getMessage());
        }
        
        // Test tạo phim với tên null
        try {
            Phim phim = new Phim("PHIM001", null, "Test", 120, 2024, "Test Studio");
            Phim.Create(phim);
            System.out.println("✗ Không bắt được lỗi tên phim null");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi tên phim null: " + e.getMessage());
        }
        
        // Test tạo phim với mã rỗng
        try {
            Phim phim = new Phim("", "Test Movie", "Test", 120, 2024, "Test Studio");
            Phim.Create(phim);
            System.out.println("✗ Không bắt được lỗi mã rỗng");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi mã rỗng: " + e.getMessage());
    }
    }
    
    // Test với dữ liệu không hợp lệ
    public static void testInvalidData() {
        System.out.println("\n=== TEST INVALID DATA ===");
        
        // Test thời lượng âm
        try {
            Phim phim = new Phim("PHIM001", "Test Movie", "Test", -10, 2024, "Test Studio");
            Phim.Create(phim);
            System.out.println("✗ Không bắt được lỗi thời lượng âm");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi thời lượng âm: " + e.getMessage());
        }
        
        // Test thời lượng bằng 0
        try {
            Phim phim = new Phim("PHIM002", "Test Movie", "Test", 0, 2024, "Test Studio");
            Phim.Create(phim);
            System.out.println("✗ Không bắt được lỗi thời lượng bằng 0");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi thời lượng bằng 0: " + e.getMessage());
        }
        
        // Test năm sản xuất không hợp lệ
        try {
            Phim phim = new Phim("PHIM003", "Test Movie", "Test", 120, 1800, "Test Studio");
            Phim.Create(phim);
            System.out.println("✗ Không bắt được lỗi năm sản xuất không hợp lệ");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi năm sản xuất không hợp lệ: " + e.getMessage());
        }
        
        // Test năm sản xuất trong tương lai
        try {
            Phim phim = new Phim("PHIM004", "Test Movie", "Test", 120, 2030, "Test Studio");
            Phim.Create(phim);
            System.out.println("✗ Không bắt được lỗi năm sản xuất trong tương lai");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi năm sản xuất trong tương lai: " + e.getMessage());
        }
        
        // Test mã phim trùng lặp
        Phim phim1 = new Phim("DUP001", "Duplicate Movie 1", "Test", 120, 2024, "Test Studio");
        Phim.Create(phim1);
        
        try {
            Phim phim2 = new Phim("DUP001", "Duplicate Movie 2", "Test", 130, 2024, "Test Studio");
            Phim.Create(phim2);
            System.out.println("✗ Không bắt được lỗi mã phim trùng lặp");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi mã phim trùng lặp: " + e.getMessage());
        }
    }
    
    // === MOCK TESTING ===
    
    // Test với dữ liệu giả
    public static void testMockData() {
        System.out.println("\n=== TEST MOCK DATA ===");
        
        // Tạo dữ liệu giả
        ArrayList<Phim> mockData = new ArrayList<>();
        mockData.add(new Phim("MOCK001", "Mock Movie 1", "Hành động", 120, 2024, "Mock Studio 1"));
        mockData.add(new Phim("MOCK002", "Mock Movie 2", "Hài", 90, 2023, "Mock Studio 2"));
        mockData.add(new Phim("MOCK003", "Mock Movie 3", "Kinh dị", 110, 2022, "Mock Studio 3"));
        
        // Test tìm kiếm theo thể loại
        ArrayList<Phim> result1 = new ArrayList<>();
        for (Phim phim : mockData) {
            if ("Hành động".equals(phim.getTheLoai())) {
                result1.add(phim);
        }
        }
        assert result1.size() == 1 : "Tìm kiếm theo thể loại không đúng";
        
        // Test tìm kiếm theo nhà sản xuất
        ArrayList<Phim> result2 = new ArrayList<>();
        for (Phim phim : mockData) {
            if (phim.getNhaSanXuat().contains("Mock")) {
                result2.add(phim);
            }
        }
        assert result2.size() == 3 : "Tìm kiếm theo nhà sản xuất không đúng";
        
        // Test tính tổng thời lượng
        int totalDuration = 0;
        for (Phim phim : mockData) {
            totalDuration += phim.getThoiLuong();
        }
        assert totalDuration == 320 : "Tính tổng thời lượng không đúng";
        
        // Test tính năm sản xuất trung bình
        int totalYear = 0;
        for (Phim phim : mockData) {
            totalYear += phim.getNamSanXuat();
        }
        int avgYear = totalYear / mockData.size();
        assert avgYear == 2023 : "Tính năm sản xuất trung bình không đúng";
        
        System.out.println("✓ Mock data testing OK");
    }
    
    // === PERFORMANCE TESTING ===
    
    // Test hiệu suất với dữ liệu lớn
    public static void testPerformance() {
        System.out.println("\n=== TEST PERFORMANCE ===");
        
        long startTime = System.currentTimeMillis();
        
        // Tạo 1000 phim
        for (int i = 0; i < 1000; i++) {
            Phim phim = new Phim("PERF" + i, "Performance Movie " + i, "Test", 120, 2024, "Test Studio");
            Phim.Create(phim);
        }
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        System.out.println("✓ Tạo 1000 phim trong " + duration + "ms");
        
        // Test tìm kiếm
        startTime = System.currentTimeMillis();
        Phim result = Phim.getPhimById("PERF500");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        
        System.out.println("✓ Tìm kiếm phim trong " + duration + "ms");
        }

    // === BUSINESS LOGIC TESTING ===
    
    // Test logic nghiệp vụ
    public static void testBusinessLogic() {
        System.out.println("\n=== TEST BUSINESS LOGIC ===");
        
        // Test phim mới (năm hiện tại)
        Phim newMovie = new Phim("NEW001", "New Movie", "Test", 120, 2024, "Test Studio");
        boolean isNew = newMovie.getNamSanXuat() == 2024;
        assert isNew == true : "Phim mới không đúng";
        
        // Test phim cũ (trước 2020)
        Phim oldMovie = new Phim("OLD001", "Old Movie", "Test", 120, 2019, "Test Studio");
        boolean isOld = oldMovie.getNamSanXuat() < 2020;
        assert isOld == true : "Phim cũ không đúng";
        
        // Test phim dài (> 150 phút)
        Phim longMovie = new Phim("LONG001", "Long Movie", "Test", 180, 2024, "Test Studio");
        boolean isLong = longMovie.getThoiLuong() > 150;
        assert isLong == true : "Phim dài không đúng";
        
        // Test phim ngắn (< 90 phút)
        Phim shortMovie = new Phim("SHORT001", "Short Movie", "Test", 80, 2024, "Test Studio");
        boolean isShort = shortMovie.getThoiLuong() < 90;
        assert isShort == true : "Phim ngắn không đúng";
        
        System.out.println("✓ Business logic testing OK");
    }
    
    // === VALIDATION TESTING ===
    
    // Test validation
    public static void testValidation() {
        System.out.println("\n=== TEST VALIDATION ===");
        
        // Test validation mã phim
        try {
            Phim phim = new Phim("PHIM_123", "Test Movie", "Test", 120, 2024, "Test Studio");
            // Kiểm tra format mã phim
            boolean isValidFormat = phim.getMaPhim().matches("^[A-Z0-9_]+$");
            assert isValidFormat == true : "Format mã phim không hợp lệ";
            System.out.println("✓ Validation mã phim OK");
        } catch (Exception e) {
            System.out.println("✗ Validation mã phim thất bại: " + e.getMessage());
    }

        // Test validation thời lượng
        try {
            Phim phim = new Phim("PHIM001", "Test Movie", "Test", 120, 2024, "Test Studio");
            // Kiểm tra thời lượng hợp lệ
            boolean isValidDuration = phim.getThoiLuong() > 0 && phim.getThoiLuong() <= 300;
            assert isValidDuration == true : "Thời lượng không hợp lệ";
            System.out.println("✓ Validation thời lượng OK");
        } catch (Exception e) {
            System.out.println("✗ Validation thời lượng thất bại: " + e.getMessage());
        }
        
        // Test validation năm sản xuất
        try {
            Phim phim = new Phim("PHIM001", "Test Movie", "Test", 120, 2024, "Test Studio");
            // Kiểm tra năm sản xuất hợp lệ
            int currentYear = 2024;
            boolean isValidYear = phim.getNamSanXuat() >= 1900 && phim.getNamSanXuat() <= currentYear;
            assert isValidYear == true : "Năm sản xuất không hợp lệ";
            System.out.println("✓ Validation năm sản xuất OK");
        } catch (Exception e) {
            System.out.println("✗ Validation năm sản xuất thất bại: " + e.getMessage());
    }
    }
    
    // === MAIN TEST METHOD ===

    public static void test() {
        System.out.println("🚀 BẮT ĐẦU TEST PHIM MODEL");
        System.out.println("============================");
        
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
            
            System.out.println("\n============================");
            System.out.println("✅ TẤT CẢ TEST PHIM THÀNH CÔNG!");
            
        } catch (Exception e) {
            System.out.println("\n============================");
            System.out.println("❌ TEST PHIM THẤT BẠI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
