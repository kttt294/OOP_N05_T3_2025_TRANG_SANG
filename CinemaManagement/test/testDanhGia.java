import java.util.ArrayList;

public class testDanhGia {
    
    // === UNIT TESTING ===
    
    // Test constructor
    public static void testConstructor() {
        System.out.println("=== TEST CONSTRUCTOR ===");
        
        // Test constructor rỗng
        DanhGia dg1 = new DanhGia();
        assert dg1.getMaDanhGia() == null : "Constructor rỗng không đúng";
        assert dg1.getCCCD() == null : "Constructor rỗng không đúng";
        System.out.println("✓ Constructor rỗng OK");
        
        // Test constructor với tham số
        DanhGia dg2 = new DanhGia("DG001", "123456789", "PHIM001", 5, "Phim rất hay!");
        assert "DG001".equals(dg2.getMaDanhGia()) : "Mã đánh giá không đúng";
        assert "123456789".equals(dg2.getCCCD()) : "CCCD không đúng";
        assert "PHIM001".equals(dg2.getMaPhim()) : "Mã phim không đúng";
        assert dg2.getDiem() == 5 : "Điểm không đúng";
        assert "Phim rất hay!".equals(dg2.getNoiDung()) : "Nội dung không đúng";
        System.out.println("✓ Constructor với tham số OK");
    }
    
    // Test getters và setters
    public static void testGettersSetters() {
        System.out.println("\n=== TEST GETTERS & SETTERS ===");
        
        DanhGia dg = new DanhGia();
        
        // Test setMaDanhGia
        dg.setMaDanhGia("DG002");
        assert "DG002".equals(dg.getMaDanhGia()) : "setMaDanhGia/getMaDanhGia không đúng";
        
        // Test setCCCD
        dg.setCCCD("987654321");
        assert "987654321".equals(dg.getCCCD()) : "setCCCD/getCCCD không đúng";
        
        // Test setMaPhim
        dg.setMaPhim("PHIM002");
        assert "PHIM002".equals(dg.getMaPhim()) : "setMaPhim/getMaPhim không đúng";
        
        // Test setDiem
        dg.setDiem(4);
        assert dg.getDiem() == 4 : "setDiem/getDiem không đúng";
        
        // Test setNoiDung
        dg.setNoiDung("Phim tạm được");
        assert "Phim tạm được".equals(dg.getNoiDung()) : "setNoiDung/getNoiDung không đúng";
        
        System.out.println("✓ Tất cả getters/setters OK");
    }
    
    // Test method hienThiThongTin
    public static void testHienThiThongTin() {
        System.out.println("\n=== TEST HIEN THI THONG TIN ===");
        
        DanhGia dg = new DanhGia("DG003", "111222333", "PHIM003", 3, "Phim bình thường");
        
        // Test hiển thị thông tin
        dg.hienThiThongTin();
        System.out.println("✓ Hiển thị thông tin OK");
    }
    
    // === INTEGRATION TESTING ===
    
    // Test luồng CRUD hoàn chỉnh
    public static void testCRUDIntegration() {
        System.out.println("\n=== TEST CRUD INTEGRATION ===");
        
        // Create
        DanhGia dg = new DanhGia("INT001", "123456789", "PHIM001", 5, "Phim rất hay!");
        DanhGia.Create(dg);
        
        // Read
        DanhGia dgRead = DanhGia.getDanhGiaByMaDanhGia("INT001");
        assert dgRead != null : "Không tìm thấy đánh giá sau khi tạo";
        assert "123456789".equals(dgRead.getCCCD()) : "Dữ liệu đọc không đúng";
        
        // Update
        dg.setDiem(4);
        dg.setNoiDung("Phim hay nhưng chưa xuất sắc");
        DanhGia.Update("INT001", dg);
        DanhGia dgUpdated = DanhGia.getDanhGiaByMaDanhGia("INT001");
        assert dgUpdated.getDiem() == 4 : "Update không thành công";
        
        // Delete
        DanhGia.Delete("INT001");
        DanhGia dgDeleted = DanhGia.getDanhGiaByMaDanhGia("INT001");
        assert dgDeleted == null : "Delete không thành công";
        
        System.out.println("✓ CRUD Integration OK");
    }
    
    // === EDGE CASE TESTING ===
    
    // Test với dữ liệu null
    public static void testNullData() {
        System.out.println("\n=== TEST NULL DATA ===");
        
        // Test tạo đánh giá với mã null
        try {
            DanhGia dg = new DanhGia(null, "123456789", "PHIM001", 5, "Phim hay");
            DanhGia.Create(dg);
            System.out.println("✗ Không bắt được lỗi mã đánh giá null");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi mã đánh giá null: " + e.getMessage());
        }
        
        // Test tạo đánh giá với CCCD null
        try {
            DanhGia dg = new DanhGia("DG001", null, "PHIM001", 5, "Phim hay");
            DanhGia.Create(dg);
            System.out.println("✗ Không bắt được lỗi CCCD null");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi CCCD null: " + e.getMessage());
        }
        
        // Test tạo đánh giá với mã rỗng
        try {
            DanhGia dg = new DanhGia("", "123456789", "PHIM001", 5, "Phim hay");
            DanhGia.Create(dg);
            System.out.println("✗ Không bắt được lỗi mã rỗng");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi mã rỗng: " + e.getMessage());
        }
    }
    
    // Test với dữ liệu không hợp lệ
    public static void testInvalidData() {
        System.out.println("\n=== TEST INVALID DATA ===");
        
        // Test điểm âm
        try {
            DanhGia dg = new DanhGia("DG001", "123456789", "PHIM001", -1, "Phim hay");
            DanhGia.Create(dg);
            System.out.println("✗ Không bắt được lỗi điểm âm");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi điểm âm: " + e.getMessage());
        }
        
        // Test điểm quá cao
        try {
            DanhGia dg = new DanhGia("DG002", "123456789", "PHIM001", 10, "Phim hay");
            DanhGia.Create(dg);
            System.out.println("✗ Không bắt được lỗi điểm quá cao");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi điểm quá cao: " + e.getMessage());
        }
        
        // Test điểm bằng 0
        try {
            DanhGia dg = new DanhGia("DG003", "123456789", "PHIM001", 0, "Phim hay");
            DanhGia.Create(dg);
            System.out.println("✗ Không bắt được lỗi điểm bằng 0");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi điểm bằng 0: " + e.getMessage());
        }
        
        // Test nội dung rỗng
        try {
            DanhGia dg = new DanhGia("DG004", "123456789", "PHIM001", 5, "");
            DanhGia.Create(dg);
            System.out.println("✗ Không bắt được lỗi nội dung rỗng");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi nội dung rỗng: " + e.getMessage());
        }
        
        // Test nội dung quá dài
        String longContent = "A".repeat(1001);
        try {
            DanhGia dg = new DanhGia("DG005", "123456789", "PHIM001", 5, longContent);
            DanhGia.Create(dg);
            System.out.println("✗ Không bắt được lỗi nội dung quá dài");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi nội dung quá dài: " + e.getMessage());
        }
        
        // Test mã đánh giá trùng lặp
        DanhGia dg1 = new DanhGia("DUP001", "123456789", "PHIM001", 5, "Phim hay");
        DanhGia.Create(dg1);
        
        try {
            DanhGia dg2 = new DanhGia("DUP001", "987654321", "PHIM002", 4, "Phim tạm được");
            DanhGia.Create(dg2);
            System.out.println("✗ Không bắt được lỗi mã đánh giá trùng lặp");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi mã đánh giá trùng lặp: " + e.getMessage());
        }
    }
    
    // === MOCK TESTING ===
    
    // Test với dữ liệu giả
    public static void testMockData() {
        System.out.println("\n=== TEST MOCK DATA ===");
        
        // Tạo dữ liệu giả
        ArrayList<DanhGia> mockData = new ArrayList<>();
        mockData.add(new DanhGia("MOCK001", "111111111", "PHIM001", 5, "Phim rất hay!"));
        mockData.add(new DanhGia("MOCK002", "222222222", "PHIM001", 4, "Phim hay"));
        mockData.add(new DanhGia("MOCK003", "333333333", "PHIM002", 3, "Phim bình thường"));
        
        // Test tìm kiếm theo phim
        ArrayList<DanhGia> result1 = new ArrayList<>();
        for (DanhGia dg : mockData) {
            if ("PHIM001".equals(dg.getMaPhim())) {
                result1.add(dg);
            }
        }
        assert result1.size() == 2 : "Tìm kiếm theo phim không đúng";
        
        // Test tìm kiếm theo CCCD
        ArrayList<DanhGia> result2 = new ArrayList<>();
        for (DanhGia dg : mockData) {
            if ("111111111".equals(dg.getCCCD())) {
                result2.add(dg);
            }
        }
        assert result2.size() == 1 : "Tìm kiếm theo CCCD không đúng";
        
        // Test tính điểm trung bình
        double totalScore = 0;
        for (DanhGia dg : mockData) {
            totalScore += dg.getDiem();
        }
        double avgScore = totalScore / mockData.size();
        assert avgScore == 4.0 : "Tính điểm trung bình không đúng";
        
        // Test đếm đánh giá theo mức độ
        int excellentCount = 0;
        int goodCount = 0;
        int averageCount = 0;
        
        for (DanhGia dg : mockData) {
            if (dg.getDiem() >= 4) {
                excellentCount++;
            } else if (dg.getDiem() >= 3) {
                goodCount++;
            } else {
                averageCount++;
            }
        }
        
        assert excellentCount == 2 : "Đếm đánh giá xuất sắc không đúng";
        assert goodCount == 1 : "Đếm đánh giá tốt không đúng";
        assert averageCount == 0 : "Đếm đánh giá trung bình không đúng";
        
        System.out.println("✓ Mock data testing OK");
    }
    
    // === PERFORMANCE TESTING ===
    
    // Test hiệu suất với dữ liệu lớn
    public static void testPerformance() {
        System.out.println("\n=== TEST PERFORMANCE ===");
        
        long startTime = System.currentTimeMillis();
        
        // Tạo 1000 đánh giá
        for (int i = 0; i < 1000; i++) {
            DanhGia dg = new DanhGia("PERF" + i, "CCCD" + i, "PHIM" + (i % 10), 
                                    (i % 5) + 1, "Đánh giá " + i);
            DanhGia.Create(dg);
        }
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        System.out.println("✓ Tạo 1000 đánh giá trong " + duration + "ms");
        
        // Test tìm kiếm
        startTime = System.currentTimeMillis();
        DanhGia result = DanhGia.getDanhGiaByMaDanhGia("PERF500");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        
        System.out.println("✓ Tìm kiếm đánh giá trong " + duration + "ms");
    }
    
    // === BUSINESS LOGIC TESTING ===
    
    // Test logic nghiệp vụ
    public static void testBusinessLogic() {
        System.out.println("\n=== TEST BUSINESS LOGIC ===");
        
        // Test đánh giá xuất sắc (5 sao)
        DanhGia excellentReview = new DanhGia("EXC001", "123456789", "PHIM001", 5, "Phim xuất sắc!");
        boolean isExcellent = excellentReview.getDiem() == 5;
        assert isExcellent == true : "Đánh giá xuất sắc không đúng";
        
        // Test đánh giá tốt (4 sao)
        DanhGia goodReview = new DanhGia("GOOD001", "123456789", "PHIM002", 4, "Phim hay");
        boolean isGood = goodReview.getDiem() == 4;
        assert isGood == true : "Đánh giá tốt không đúng";
        
        // Test đánh giá trung bình (3 sao)
        DanhGia averageReview = new DanhGia("AVG001", "123456789", "PHIM003", 3, "Phim bình thường");
        boolean isAverage = averageReview.getDiem() == 3;
        assert isAverage == true : "Đánh giá trung bình không đúng";
        
        // Test đánh giá kém (1-2 sao)
        DanhGia poorReview = new DanhGia("POOR001", "123456789", "PHIM004", 1, "Phim không hay");
        boolean isPoor = poorReview.getDiem() <= 2;
        assert isPoor == true : "Đánh giá kém không đúng";
        
        System.out.println("✓ Business logic testing OK");
    }
    
    // === VALIDATION TESTING ===
    
    // Test validation
    public static void testValidation() {
        System.out.println("\n=== TEST VALIDATION ===");
        
        // Test validation mã đánh giá
        try {
            DanhGia dg = new DanhGia("DG_123", "123456789", "PHIM001", 5, "Phim hay");
            // Kiểm tra format mã đánh giá
            boolean isValidFormat = dg.getMaDanhGia().matches("^[A-Z0-9_]+$");
            assert isValidFormat == true : "Format mã đánh giá không hợp lệ";
            System.out.println("✓ Validation mã đánh giá OK");
        } catch (Exception e) {
            System.out.println("✗ Validation mã đánh giá thất bại: " + e.getMessage());
        }
        
        // Test validation điểm
        try {
            DanhGia dg = new DanhGia("DG001", "123456789", "PHIM001", 5, "Phim hay");
            // Kiểm tra điểm hợp lệ
            boolean isValidScore = dg.getDiem() >= 1 && dg.getDiem() <= 5;
            assert isValidScore == true : "Điểm không hợp lệ";
            System.out.println("✓ Validation điểm OK");
        } catch (Exception e) {
            System.out.println("✗ Validation điểm thất bại: " + e.getMessage());
        }
        
        // Test validation nội dung
        try {
            DanhGia dg = new DanhGia("DG001", "123456789", "PHIM001", 5, "Phim hay");
            // Kiểm tra nội dung hợp lệ
            boolean isValidContent = dg.getNoiDung() != null && !dg.getNoiDung().isEmpty() && 
                                   dg.getNoiDung().length() <= 1000;
            assert isValidContent == true : "Nội dung không hợp lệ";
            System.out.println("✓ Validation nội dung OK");
        } catch (Exception e) {
            System.out.println("✗ Validation nội dung thất bại: " + e.getMessage());
        }
    }
    
    // === MAIN TEST METHOD ===
    
    public static void test() {
        System.out.println("🚀 BẮT ĐẦU TEST DANH GIA MODEL");
        System.out.println("===============================");
        
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
            
            System.out.println("\n===============================");
            System.out.println("✅ TẤT CẢ TEST DANH GIA THÀNH CÔNG!");
            
        } catch (Exception e) {
            System.out.println("\n===============================");
            System.out.println("❌ TEST DANH GIA THẤT BẠI: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 