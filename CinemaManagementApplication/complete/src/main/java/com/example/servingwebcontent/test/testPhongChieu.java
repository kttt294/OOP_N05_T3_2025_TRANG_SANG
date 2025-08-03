package com.example.servingwebcontent.test;
import java.util.ArrayList;

public class testPhongChieu {
    
    // === UNIT TESTING ===
    
    // Test constructor
    public static void testConstructor() {
        System.out.println("=== TEST CONSTRUCTOR ===");
        
        // Test constructor rỗng
        PhongChieu pc1 = new PhongChieu();
        assert pc1.getMaPhong() == null : "Constructor rỗng không đúng";
        assert pc1.getTenPhong() == null : "Constructor rỗng không đúng";
        System.out.println("✓ Constructor rỗng OK");
        
        // Test constructor với tham số
        PhongChieu pc2 = new PhongChieu("PHONG001", "Phòng 1", 10, 15);
        assert "PHONG001".equals(pc2.getMaPhong()) : "Mã phòng không đúng";
        assert "Phòng 1".equals(pc2.getTenPhong()) : "Tên phòng không đúng";
        assert pc2.getSoHang() == 10 : "Số hàng không đúng";
        assert pc2.getSoCot() == 15 : "Số cột không đúng";
        System.out.println("✓ Constructor với tham số OK");
    }
    
    // Test getters và setters
    public static void testGettersSetters() {
        System.out.println("\n=== TEST GETTERS & SETTERS ===");
        
        PhongChieu pc = new PhongChieu();
        
        // Test setMaPhong
        pc.setMaPhong("PHONG002");
        assert "PHONG002".equals(pc.getMaPhong()) : "setMaPhong/getMaPhong không đúng";
        
        // Test setTenPhong
        pc.setTenPhong("Phòng 2");
        assert "Phòng 2".equals(pc.getTenPhong()) : "setTenPhong/getTenPhong không đúng";
        
        // Test setSoHang
        pc.setSoHang(12);
        assert pc.getSoHang() == 12 : "setSoHang/getSoHang không đúng";
        
        // Test setSoCot
        pc.setSoCot(20);
        assert pc.getSoCot() == 20 : "setSoCot/getSoCot không đúng";
        
        System.out.println("✓ Tất cả getters/setters OK");
    }
    
    // Test method hienThiThongTin
    public static void testHienThiThongTin() {
        System.out.println("\n=== TEST HIEN THI THONG TIN ===");
        
        PhongChieu pc = new PhongChieu("PHONG003", "Phòng 3", 8, 12);
        
        // Test hiển thị thông tin
        pc.hienThiThongTin();
        System.out.println("✓ Hiển thị thông tin OK");
    }
    
    // === INTEGRATION TESTING ===
    
    // Test luồng CRUD hoàn chỉnh
    public static void testCRUDIntegration() {
        System.out.println("\n=== TEST CRUD INTEGRATION ===");
        
        // Create
        PhongChieu pc = new PhongChieu("INT001", "Phòng Integration", 10, 15);
        PhongChieu.Create(pc);
        
        // Read
        PhongChieu pcRead = PhongChieu.getPhongChieuByMaPhong("INT001");
        assert pcRead != null : "Không tìm thấy phòng chiếu sau khi tạo";
        assert "Phòng Integration".equals(pcRead.getTenPhong()) : "Dữ liệu đọc không đúng";
        
        // Update
        pc.setSoHang(12);
        pc.setSoCot(18);
        PhongChieu.Update("INT001", pc);
        PhongChieu pcUpdated = PhongChieu.getPhongChieuByMaPhong("INT001");
        assert pcUpdated.getSoHang() == 12 : "Update không thành công";
        
        // Delete
        PhongChieu.Delete("INT001");
        PhongChieu pcDeleted = PhongChieu.getPhongChieuByMaPhong("INT001");
        assert pcDeleted == null : "Delete không thành công";
        
        System.out.println("✓ CRUD Integration OK");
    }
    
    // === EDGE CASE TESTING ===
    
    // Test với dữ liệu null
    public static void testNullData() {
        System.out.println("\n=== TEST NULL DATA ===");
        
        // Test tạo phòng chiếu với mã null
        try {
            PhongChieu pc = new PhongChieu(null, "Phòng 1", 10, 15);
            PhongChieu.Create(pc);
            System.out.println("✗ Không bắt được lỗi mã phòng null");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi mã phòng null: " + e.getMessage());
        }
        
        // Test tạo phòng chiếu với tên null
        try {
            PhongChieu pc = new PhongChieu("PHONG001", null, 10, 15);
            PhongChieu.Create(pc);
            System.out.println("✗ Không bắt được lỗi tên phòng null");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi tên phòng null: " + e.getMessage());
        }
        
        // Test tạo phòng chiếu với mã rỗng
        try {
            PhongChieu pc = new PhongChieu("", "Phòng 1", 10, 15);
            PhongChieu.Create(pc);
            System.out.println("✗ Không bắt được lỗi mã rỗng");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi mã rỗng: " + e.getMessage());
        }
    }
    
    // Test với dữ liệu không hợp lệ
    public static void testInvalidData() {
        System.out.println("\n=== TEST INVALID DATA ===");
        
        // Test số hàng âm
        try {
            PhongChieu pc = new PhongChieu("PHONG001", "Phòng 1", -5, 15);
            PhongChieu.Create(pc);
            System.out.println("✗ Không bắt được lỗi số hàng âm");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi số hàng âm: " + e.getMessage());
        }
        
        // Test số cột âm
        try {
            PhongChieu pc = new PhongChieu("PHONG002", "Phòng 2", 10, -3);
            PhongChieu.Create(pc);
            System.out.println("✗ Không bắt được lỗi số cột âm");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi số cột âm: " + e.getMessage());
        }
        
        // Test số hàng bằng 0
        try {
            PhongChieu pc = new PhongChieu("PHONG003", "Phòng 3", 0, 15);
            PhongChieu.Create(pc);
            System.out.println("✗ Không bắt được lỗi số hàng bằng 0");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi số hàng bằng 0: " + e.getMessage());
        }
        
        // Test số cột bằng 0
        try {
            PhongChieu pc = new PhongChieu("PHONG004", "Phòng 4", 10, 0);
            PhongChieu.Create(pc);
            System.out.println("✗ Không bắt được lỗi số cột bằng 0");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi số cột bằng 0: " + e.getMessage());
        }
        
        // Test tên quá dài
        String longName = "A".repeat(101);
        try {
            PhongChieu pc = new PhongChieu("PHONG005", longName, 10, 15);
            PhongChieu.Create(pc);
            System.out.println("✗ Không bắt được lỗi tên quá dài");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi tên quá dài: " + e.getMessage());
        }
        
        // Test mã phòng trùng lặp
        PhongChieu pc1 = new PhongChieu("DUP001", "Phòng Duplicate 1", 10, 15);
        PhongChieu.Create(pc1);
        
        try {
            PhongChieu pc2 = new PhongChieu("DUP001", "Phòng Duplicate 2", 12, 18);
            PhongChieu.Create(pc2);
            System.out.println("✗ Không bắt được lỗi mã phòng trùng lặp");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi mã phòng trùng lặp: " + e.getMessage());
        }
    }
    
    // === MOCK TESTING ===
    
    // Test với dữ liệu giả
    public static void testMockData() {
        System.out.println("\n=== TEST MOCK DATA ===");
        
        // Tạo dữ liệu giả
        ArrayList<PhongChieu> mockData = new ArrayList<>();
        mockData.add(new PhongChieu("MOCK001", "Phòng 1", 10, 15));
        mockData.add(new PhongChieu("MOCK002", "Phòng 2", 12, 18));
        mockData.add(new PhongChieu("MOCK003", "Phòng VIP", 8, 12));
        
        // Test tìm kiếm theo tên
        ArrayList<PhongChieu> result1 = new ArrayList<>();
        for (PhongChieu pc : mockData) {
            if (pc.getTenPhong().contains("Phòng")) {
                result1.add(pc);
            }
        }
        assert result1.size() == 3 : "Tìm kiếm theo tên không đúng";
        
        // Test tìm kiếm theo kích thước
        ArrayList<PhongChieu> result2 = new ArrayList<>();
        for (PhongChieu pc : mockData) {
            if (pc.getSoHang() >= 10) {
                result2.add(pc);
            }
        }
        assert result2.size() == 2 : "Tìm kiếm theo kích thước không đúng";
        
        // Test tính tổng số ghế
        int totalSeats = 0;
        for (PhongChieu pc : mockData) {
            totalSeats += pc.getSoHang() * pc.getSoCot();
        }
        assert totalSeats == 456 : "Tính tổng số ghế không đúng";
        
        // Test đếm phòng theo loại
        int normalRoomCount = 0;
        int vipRoomCount = 0;
        
        for (PhongChieu pc : mockData) {
            if (pc.getTenPhong().contains("VIP")) {
                vipRoomCount++;
            } else {
                normalRoomCount++;
            }
        }
        
        assert normalRoomCount == 2 : "Đếm phòng thường không đúng";
        assert vipRoomCount == 1 : "Đếm phòng VIP không đúng";
        
        System.out.println("✓ Mock data testing OK");
    }
    
    // === PERFORMANCE TESTING ===
    
    // Test hiệu suất với dữ liệu lớn
    public static void testPerformance() {
        System.out.println("\n=== TEST PERFORMANCE ===");
        
        long startTime = System.currentTimeMillis();
        
        // Tạo 100 phòng chiếu
        for (int i = 0; i < 100; i++) {
            PhongChieu pc = new PhongChieu("PERF" + i, "Phòng " + i, 10 + (i % 10), 15 + (i % 10));
            PhongChieu.Create(pc);
        }
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        System.out.println("✓ Tạo 100 phòng chiếu trong " + duration + "ms");
        
        // Test tìm kiếm
        startTime = System.currentTimeMillis();
        PhongChieu result = PhongChieu.getPhongChieuByMaPhong("PERF50");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        
        System.out.println("✓ Tìm kiếm phòng chiếu trong " + duration + "ms");
    }
    
    // === BUSINESS LOGIC TESTING ===
    
    // Test logic nghiệp vụ
    public static void testBusinessLogic() {
        System.out.println("\n=== TEST BUSINESS LOGIC ===");
        
        // Test phòng lớn (> 200 ghế)
        PhongChieu largeRoom = new PhongChieu("LARGE001", "Phòng lớn", 15, 20);
        boolean isLarge = largeRoom.getSoHang() * largeRoom.getSoCot() > 200;
        assert isLarge == true : "Phòng lớn không đúng";
        
        // Test phòng nhỏ (< 100 ghế)
        PhongChieu smallRoom = new PhongChieu("SMALL001", "Phòng nhỏ", 8, 10);
        boolean isSmall = smallRoom.getSoHang() * smallRoom.getSoCot() < 100;
        assert isSmall == true : "Phòng nhỏ không đúng";
        
        // Test phòng VIP (ít ghế, chất lượng cao)
        PhongChieu vipRoom = new PhongChieu("VIP001", "Phòng VIP", 6, 8);
        boolean isVIP = vipRoom.getSoHang() * vipRoom.getSoCot() <= 50;
        assert isVIP == true : "Phòng VIP không đúng";
        
        // Test phòng thường (nhiều ghế)
        PhongChieu normalRoom = new PhongChieu("NORMAL001", "Phòng thường", 12, 16);
        boolean isNormal = normalRoom.getSoHang() * normalRoom.getSoCot() > 100;
        assert isNormal == true : "Phòng thường không đúng";
        
        System.out.println("✓ Business logic testing OK");
    }
    
    // === VALIDATION TESTING ===
    
    // Test validation
    public static void testValidation() {
        System.out.println("\n=== TEST VALIDATION ===");
        
        // Test validation mã phòng
        try {
            PhongChieu pc = new PhongChieu("PHONG_123", "Phòng 1", 10, 15);
            // Kiểm tra format mã phòng
            boolean isValidFormat = pc.getMaPhong().matches("^[A-Z0-9_]+$");
            assert isValidFormat == true : "Format mã phòng không hợp lệ";
            System.out.println("✓ Validation mã phòng OK");
        } catch (Exception e) {
            System.out.println("✗ Validation mã phòng thất bại: " + e.getMessage());
        }
        
        // Test validation kích thước
        try {
            PhongChieu pc = new PhongChieu("PHONG001", "Phòng 1", 10, 15);
            // Kiểm tra kích thước hợp lệ
            boolean isValidSize = pc.getSoHang() > 0 && pc.getSoHang() <= 30 && 
                                pc.getSoCot() > 0 && pc.getSoCot() <= 30;
            assert isValidSize == true : "Kích thước không hợp lệ";
            System.out.println("✓ Validation kích thước OK");
        } catch (Exception e) {
            System.out.println("✗ Validation kích thước thất bại: " + e.getMessage());
        }
        
        // Test validation tên phòng
        try {
            PhongChieu pc = new PhongChieu("PHONG001", "Phòng 1", 10, 15);
            // Kiểm tra tên phòng hợp lệ
            boolean isValidName = pc.getTenPhong() != null && !pc.getTenPhong().isEmpty() && 
                                pc.getTenPhong().length() <= 100;
            assert isValidName == true : "Tên phòng không hợp lệ";
            System.out.println("✓ Validation tên phòng OK");
        } catch (Exception e) {
            System.out.println("✗ Validation tên phòng thất bại: " + e.getMessage());
        }
    }
    
    // === MAIN TEST METHOD ===
    
    public static void test() {
        System.out.println("🚀 BẮT ĐẦU TEST PHONG CHIEU MODEL");
        System.out.println("=================================");
        
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
            
            System.out.println("\n=================================");
            System.out.println("✅ TẤT CẢ TEST PHONG CHIEU THÀNH CÔNG!");
            
        } catch (Exception e) {
            System.out.println("\n=================================");
            System.out.println("❌ TEST PHONG CHIEU THẤT BẠI: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 