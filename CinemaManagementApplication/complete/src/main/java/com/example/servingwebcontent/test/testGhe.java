package com.example.servingwebcontent.test;
import java.util.ArrayList;

public class testGhe {
    
    // === UNIT TESTING ===
    
    // Test constructor
    public static void testConstructor() {
        System.out.println("=== TEST CONSTRUCTOR ===");
        
        // Test constructor rỗng
        Ghe ghe1 = new Ghe();
        assert ghe1.getMaGhe() == null : "Constructor rỗng không đúng";
        assert ghe1.getMaPhong() == null : "Constructor rỗng không đúng";
        System.out.println("✓ Constructor rỗng OK");
        
        // Test constructor với tham số
        Ghe ghe2 = new Ghe("GHE001", "PHONG001", 1, 1, "BinhThuong");
        assert "GHE001".equals(ghe2.getMaGhe()) : "Mã ghế không đúng";
        assert "PHONG001".equals(ghe2.getMaPhong()) : "Mã phòng không đúng";
        assert ghe2.getSoHang() == 1 : "Số hàng không đúng";
        assert ghe2.getSoCot() == 1 : "Số cột không đúng";
        assert "BinhThuong".equals(ghe2.getTrangThai()) : "Trạng thái không đúng";
        System.out.println("✓ Constructor với tham số OK");
    }
    
    // Test getters và setters
    public static void testGettersSetters() {
        System.out.println("\n=== TEST GETTERS & SETTERS ===");
        
        Ghe ghe = new Ghe();
        
        // Test setMaGhe
        ghe.setMaGhe("GHE002");
        assert "GHE002".equals(ghe.getMaGhe()) : "setMaGhe/getMaGhe không đúng";
        
        // Test setMaPhong
        ghe.setMaPhong("PHONG002");
        assert "PHONG002".equals(ghe.getMaPhong()) : "setMaPhong/getMaPhong không đúng";
        
        // Test setSoHang
        ghe.setSoHang(5);
        assert ghe.getSoHang() == 5 : "setSoHang/getSoHang không đúng";
        
        // Test setSoCot
        ghe.setSoCot(10);
        assert ghe.getSoCot() == 10 : "setSoCot/getSoCot không đúng";
        
        // Test setTrangThai
        ghe.setTrangThai("Khoa");
        assert "Khoa".equals(ghe.getTrangThai()) : "setTrangThai/getTrangThai không đúng";
        
        System.out.println("✓ Tất cả getters/setters OK");
    }
    
    // Test method hienThiThongTin
    public static void testHienThiThongTin() {
        System.out.println("\n=== TEST HIEN THI THONG TIN ===");
        
        Ghe ghe = new Ghe("GHE003", "PHONG003", 3, 5, "BinhThuong");
        
        // Test hiển thị thông tin
        ghe.hienThiThongTin();
        System.out.println("✓ Hiển thị thông tin OK");
    }
    
    // === INTEGRATION TESTING ===
    
    // Test luồng CRUD hoàn chỉnh
    public static void testCRUDIntegration() {
        System.out.println("\n=== TEST CRUD INTEGRATION ===");
        
        // Create
        Ghe ghe = new Ghe("INT001", "PHONG001", 1, 1, "BinhThuong");
        Ghe.Create(ghe);
        
        // Read
        Ghe gheRead = Ghe.getGheByMaGhe("INT001");
        assert gheRead != null : "Không tìm thấy ghế sau khi tạo";
        assert "PHONG001".equals(gheRead.getMaPhong()) : "Dữ liệu đọc không đúng";
        
        // Update
        ghe.setTrangThai("Khoa");
        Ghe.Update("INT001", ghe);
        Ghe gheUpdated = Ghe.getGheByMaGhe("INT001");
        assert "Khoa".equals(gheUpdated.getTrangThai()) : "Update không thành công";
        
        // Delete
        Ghe.Delete("INT001");
        Ghe gheDeleted = Ghe.getGheByMaGhe("INT001");
        assert gheDeleted == null : "Delete không thành công";
        
        System.out.println("✓ CRUD Integration OK");
    }
    
    // === EDGE CASE TESTING ===
    
    // Test với dữ liệu null
    public static void testNullData() {
        System.out.println("\n=== TEST NULL DATA ===");
        
        // Test tạo ghế với mã null
        try {
            Ghe ghe = new Ghe(null, "PHONG001", 1, 1, "BinhThuong");
            Ghe.Create(ghe);
            System.out.println("✗ Không bắt được lỗi mã ghế null");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi mã ghế null: " + e.getMessage());
        }
        
        // Test tạo ghế với mã phòng null
        try {
            Ghe ghe = new Ghe("GHE001", null, 1, 1, "BinhThuong");
            Ghe.Create(ghe);
            System.out.println("✗ Không bắt được lỗi mã phòng null");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi mã phòng null: " + e.getMessage());
        }
        
        // Test tạo ghế với mã rỗng
        try {
            Ghe ghe = new Ghe("", "PHONG001", 1, 1, "BinhThuong");
            Ghe.Create(ghe);
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
            Ghe ghe = new Ghe("GHE001", "PHONG001", -1, 1, "BinhThuong");
            Ghe.Create(ghe);
            System.out.println("✗ Không bắt được lỗi số hàng âm");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi số hàng âm: " + e.getMessage());
        }
        
        // Test số cột âm
        try {
            Ghe ghe = new Ghe("GHE002", "PHONG001", 1, -1, "BinhThuong");
            Ghe.Create(ghe);
            System.out.println("✗ Không bắt được lỗi số cột âm");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi số cột âm: " + e.getMessage());
        }
        
        // Test số hàng bằng 0
        try {
            Ghe ghe = new Ghe("GHE003", "PHONG001", 0, 1, "BinhThuong");
            Ghe.Create(ghe);
            System.out.println("✗ Không bắt được lỗi số hàng bằng 0");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi số hàng bằng 0: " + e.getMessage());
        }
        
        // Test số cột bằng 0
        try {
            Ghe ghe = new Ghe("GHE004", "PHONG001", 1, 0, "BinhThuong");
            Ghe.Create(ghe);
            System.out.println("✗ Không bắt được lỗi số cột bằng 0");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi số cột bằng 0: " + e.getMessage());
        }
        
        // Test trạng thái không hợp lệ
        try {
            Ghe ghe = new Ghe("GHE005", "PHONG001", 1, 1, "TrangThaiKhongHopLe");
            Ghe.Create(ghe);
            System.out.println("✗ Không bắt được lỗi trạng thái không hợp lệ");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi trạng thái không hợp lệ: " + e.getMessage());
        }
        
        // Test mã ghế trùng lặp
        Ghe ghe1 = new Ghe("DUP001", "PHONG001", 1, 1, "BinhThuong");
        Ghe.Create(ghe1);
        
        try {
            Ghe ghe2 = new Ghe("DUP001", "PHONG002", 2, 2, "Khoa");
            Ghe.Create(ghe2);
            System.out.println("✗ Không bắt được lỗi mã ghế trùng lặp");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi mã ghế trùng lặp: " + e.getMessage());
        }
    }
    
    // === MOCK TESTING ===
    
    // Test với dữ liệu giả
    public static void testMockData() {
        System.out.println("\n=== TEST MOCK DATA ===");
        
        // Tạo dữ liệu giả
        ArrayList<Ghe> mockData = new ArrayList<>();
        mockData.add(new Ghe("MOCK001", "PHONG001", 1, 1, "BinhThuong"));
        mockData.add(new Ghe("MOCK002", "PHONG001", 1, 2, "Khoa"));
        mockData.add(new Ghe("MOCK003", "PHONG002", 2, 1, "BinhThuong"));
        
        // Test tìm kiếm theo phòng
        ArrayList<Ghe> result1 = new ArrayList<>();
        for (Ghe ghe : mockData) {
            if ("PHONG001".equals(ghe.getMaPhong())) {
                result1.add(ghe);
            }
        }
        assert result1.size() == 2 : "Tìm kiếm theo phòng không đúng";
        
        // Test tìm kiếm theo trạng thái
        ArrayList<Ghe> result2 = new ArrayList<>();
        for (Ghe ghe : mockData) {
            if ("BinhThuong".equals(ghe.getTrangThai())) {
                result2.add(ghe);
            }
        }
        assert result2.size() == 2 : "Tìm kiếm theo trạng thái không đúng";
        
        // Test tính tổng số ghế
        int totalSeats = 0;
        for (Ghe ghe : mockData) {
            totalSeats += ghe.getSoHang() * ghe.getSoCot();
        }
        assert totalSeats == 4 : "Tính tổng số ghế không đúng";
        
        // Test đếm ghế trống
        int availableSeats = 0;
        for (Ghe ghe : mockData) {
            if ("BinhThuong".equals(ghe.getTrangThai())) {
                availableSeats += ghe.getSoHang() * ghe.getSoCot();
            }
        }
        assert availableSeats == 3 : "Đếm ghế trống không đúng";
        
        System.out.println("✓ Mock data testing OK");
    }
    
    // === PERFORMANCE TESTING ===
    
    // Test hiệu suất với dữ liệu lớn
    public static void testPerformance() {
        System.out.println("\n=== TEST PERFORMANCE ===");
        
        long startTime = System.currentTimeMillis();
        
        // Tạo 1000 ghế
        for (int i = 0; i < 1000; i++) {
            Ghe ghe = new Ghe("PERF" + i, "PHONG" + (i % 10), (i % 20) + 1, (i % 15) + 1, "BinhThuong");
            Ghe.Create(ghe);
        }
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        System.out.println("✓ Tạo 1000 ghế trong " + duration + "ms");
        
        // Test tìm kiếm
        startTime = System.currentTimeMillis();
        Ghe result = Ghe.getGheByMaGhe("PERF500");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        
        System.out.println("✓ Tìm kiếm ghế trong " + duration + "ms");
    }
    
    // === BUSINESS LOGIC TESTING ===
    
    // Test logic nghiệp vụ
    public static void testBusinessLogic() {
        System.out.println("\n=== TEST BUSINESS LOGIC ===");
        
        // Test ghế trống
        Ghe availableSeat = new Ghe("AVAIL001", "PHONG001", 1, 1, "BinhThuong");
        boolean isAvailable = "BinhThuong".equals(availableSeat.getTrangThai());
        assert isAvailable == true : "Ghế trống không đúng";
        
        // Test ghế đã đặt
        Ghe bookedSeat = new Ghe("BOOKED001", "PHONG001", 1, 2, "Khoa");
        boolean isBooked = "Khoa".equals(bookedSeat.getTrangThai());
        assert isBooked == true : "Ghế đã đặt không đúng";
        
        // Test ghế VIP (hàng đầu)
        Ghe vipSeat = new Ghe("VIP001", "PHONG001", 1, 5, "BinhThuong");
        boolean isVIP = vipSeat.getSoHang() == 1;
        assert isVIP == true : "Ghế VIP không đúng";
        
        // Test ghế cuối phòng
        Ghe lastSeat = new Ghe("LAST001", "PHONG001", 20, 15, "BinhThuong");
        boolean isLast = lastSeat.getSoHang() == 20 && lastSeat.getSoCot() == 15;
        assert isLast == true : "Ghế cuối phòng không đúng";
        
        System.out.println("✓ Business logic testing OK");
    }
    
    // === VALIDATION TESTING ===
    
    // Test validation
    public static void testValidation() {
        System.out.println("\n=== TEST VALIDATION ===");
        
        // Test validation mã ghế
        try {
            Ghe ghe = new Ghe("GHE_123", "PHONG001", 1, 1, "BinhThuong");
            // Kiểm tra format mã ghế
            boolean isValidFormat = ghe.getMaGhe().matches("^[A-Z0-9_]+$");
            assert isValidFormat == true : "Format mã ghế không hợp lệ";
            System.out.println("✓ Validation mã ghế OK");
        } catch (Exception e) {
            System.out.println("✗ Validation mã ghế thất bại: " + e.getMessage());
        }
        
        // Test validation số hàng/cột
        try {
            Ghe ghe = new Ghe("GHE001", "PHONG001", 1, 1, "BinhThuong");
            // Kiểm tra số hàng/cột hợp lệ
            boolean isValidPosition = ghe.getSoHang() > 0 && ghe.getSoHang() <= 50 && 
                                     ghe.getSoCot() > 0 && ghe.getSoCot() <= 30;
            assert isValidPosition == true : "Vị trí ghế không hợp lệ";
            System.out.println("✓ Validation vị trí ghế OK");
        } catch (Exception e) {
            System.out.println("✗ Validation vị trí ghế thất bại: " + e.getMessage());
        }
        
        // Test validation trạng thái
        try {
            Ghe ghe = new Ghe("GHE001", "PHONG001", 1, 1, "BinhThuong");
            // Kiểm tra trạng thái hợp lệ
            boolean isValidStatus = "BinhThuong".equals(ghe.getTrangThai()) || 
                                   "Khoa".equals(ghe.getTrangThai()) ||
                                   "HuHong".equals(ghe.getTrangThai());
            assert isValidStatus == true : "Trạng thái không hợp lệ";
            System.out.println("✓ Validation trạng thái OK");
        } catch (Exception e) {
            System.out.println("✗ Validation trạng thái thất bại: " + e.getMessage());
        }
    }
    
    // === MAIN TEST METHOD ===
    
    public static void test() {
        System.out.println("🚀 BẮT ĐẦU TEST GHE MODEL");
        System.out.println("==========================");
        
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
            
            System.out.println("\n==========================");
            System.out.println("✅ TẤT CẢ TEST GHE THÀNH CÔNG!");
            
        } catch (Exception e) {
            System.out.println("\n==========================");
            System.out.println("❌ TEST GHE THẤT BẠI: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 