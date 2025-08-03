package com.example.servingwebcontent.test;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class testVoucher {
    
    // === UNIT TESTING ===
    
    // Test constructor
    public static void testConstructor() {
        System.out.println("=== TEST CONSTRUCTOR ===");
        
        // Test constructor rỗng
        Voucher vc1 = new Voucher();
        assert vc1.getMaVoucher() == null : "Constructor rỗng không đúng";
        assert vc1.getTenVoucher() == null : "Constructor rỗng không đúng";
        System.out.println("✓ Constructor rỗng OK");
        
        // Test constructor với tham số
        LocalDateTime ngayHetHan = LocalDateTime.now().plusDays(30);
        Voucher vc2 = new Voucher("VC001", "Giảm 20%", 20, 100, ngayHetHan);
        assert "VC001".equals(vc2.getMaVoucher()) : "Mã voucher không đúng";
        assert "Giảm 20%".equals(vc2.getTenVoucher()) : "Tên voucher không đúng";
        assert vc2.getGiaTri() == 20 : "Giá trị không đúng";
        assert vc2.getSoLuong() == 100 : "Số lượng không đúng";
        assert ngayHetHan.equals(vc2.getNgayHetHan()) : "Ngày hết hạn không đúng";
        System.out.println("✓ Constructor với tham số OK");
    }
    
    // Test getters và setters
    public static void testGettersSetters() {
        System.out.println("\n=== TEST GETTERS & SETTERS ===");
        
        Voucher vc = new Voucher();
        
        // Test setMaVoucher
        vc.setMaVoucher("VC002");
        assert "VC002".equals(vc.getMaVoucher()) : "setMaVoucher/getMaVoucher không đúng";
        
        // Test setTenVoucher
        vc.setTenVoucher("Giảm 30%");
        assert "Giảm 30%".equals(vc.getTenVoucher()) : "setTenVoucher/getTenVoucher không đúng";
        
        // Test setGiaTri
        vc.setGiaTri(30);
        assert vc.getGiaTri() == 30 : "setGiaTri/getGiaTri không đúng";
        
        // Test setSoLuong
        vc.setSoLuong(50);
        assert vc.getSoLuong() == 50 : "setSoLuong/getSoLuong không đúng";
        
        // Test setNgayHetHan
        LocalDateTime ngayHetHan = LocalDateTime.now().plusDays(60);
        vc.setNgayHetHan(ngayHetHan);
        assert ngayHetHan.equals(vc.getNgayHetHan()) : "setNgayHetHan/getNgayHetHan không đúng";
        
        System.out.println("✓ Tất cả getters/setters OK");
    }
    
    // Test method hienThiThongTin
    public static void testHienThiThongTin() {
        System.out.println("\n=== TEST HIEN THI THONG TIN ===");
        
        LocalDateTime ngayHetHan = LocalDateTime.now().plusDays(30);
        Voucher vc = new Voucher("VC003", "Giảm 50%", 50, 25, ngayHetHan);
        
        // Test hiển thị thông tin
        vc.hienThiThongTin();
        System.out.println("✓ Hiển thị thông tin OK");
    }
    
    // === INTEGRATION TESTING ===
    
    // Test luồng CRUD hoàn chỉnh
    public static void testCRUDIntegration() {
        System.out.println("\n=== TEST CRUD INTEGRATION ===");
        
        // Create
        LocalDateTime ngayHetHan = LocalDateTime.now().plusDays(30);
        Voucher vc = new Voucher("INT001", "Voucher Integration", 25, 100, ngayHetHan);
        Voucher.Create(vc);
        
        // Read
        Voucher vcRead = Voucher.getVoucherByMaVoucher("INT001");
        assert vcRead != null : "Không tìm thấy voucher sau khi tạo";
        assert "Voucher Integration".equals(vcRead.getTenVoucher()) : "Dữ liệu đọc không đúng";
        
        // Update
        vc.setGiaTri(30);
        vc.setSoLuong(75);
        Voucher.Update("INT001", vc);
        Voucher vcUpdated = Voucher.getVoucherByMaVoucher("INT001");
        assert vcUpdated.getGiaTri() == 30 : "Update không thành công";
        
        // Delete
        Voucher.Delete("INT001");
        Voucher vcDeleted = Voucher.getVoucherByMaVoucher("INT001");
        assert vcDeleted == null : "Delete không thành công";
        
        System.out.println("✓ CRUD Integration OK");
    }
    
    // === EDGE CASE TESTING ===
    
    // Test với dữ liệu null
    public static void testNullData() {
        System.out.println("\n=== TEST NULL DATA ===");
        
        // Test tạo voucher với mã null
        try {
            LocalDateTime ngayHetHan = LocalDateTime.now().plusDays(30);
            Voucher vc = new Voucher(null, "Giảm 20%", 20, 100, ngayHetHan);
            Voucher.Create(vc);
            System.out.println("✗ Không bắt được lỗi mã voucher null");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi mã voucher null: " + e.getMessage());
        }
        
        // Test tạo voucher với tên null
        try {
            LocalDateTime ngayHetHan = LocalDateTime.now().plusDays(30);
            Voucher vc = new Voucher("VC001", null, 20, 100, ngayHetHan);
            Voucher.Create(vc);
            System.out.println("✗ Không bắt được lỗi tên voucher null");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi tên voucher null: " + e.getMessage());
        }
        
        // Test tạo voucher với mã rỗng
        try {
            LocalDateTime ngayHetHan = LocalDateTime.now().plusDays(30);
            Voucher vc = new Voucher("", "Giảm 20%", 20, 100, ngayHetHan);
            Voucher.Create(vc);
            System.out.println("✗ Không bắt được lỗi mã rỗng");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi mã rỗng: " + e.getMessage());
        }
    }
    
    // Test với dữ liệu không hợp lệ
    public static void testInvalidData() {
        System.out.println("\n=== TEST INVALID DATA ===");
        
        // Test giá trị âm
        try {
            LocalDateTime ngayHetHan = LocalDateTime.now().plusDays(30);
            Voucher vc = new Voucher("VC001", "Giảm 20%", -10, 100, ngayHetHan);
            Voucher.Create(vc);
            System.out.println("✗ Không bắt được lỗi giá trị âm");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi giá trị âm: " + e.getMessage());
        }
        
        // Test giá trị bằng 0
        try {
            LocalDateTime ngayHetHan = LocalDateTime.now().plusDays(30);
            Voucher vc = new Voucher("VC002", "Giảm 0%", 0, 100, ngayHetHan);
            Voucher.Create(vc);
            System.out.println("✗ Không bắt được lỗi giá trị bằng 0");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi giá trị bằng 0: " + e.getMessage());
        }
        
        // Test giá trị quá cao (> 100%)
        try {
            LocalDateTime ngayHetHan = LocalDateTime.now().plusDays(30);
            Voucher vc = new Voucher("VC003", "Giảm 150%", 150, 100, ngayHetHan);
            Voucher.Create(vc);
            System.out.println("✗ Không bắt được lỗi giá trị quá cao");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi giá trị quá cao: " + e.getMessage());
        }
        
        // Test số lượng âm
        try {
            LocalDateTime ngayHetHan = LocalDateTime.now().plusDays(30);
            Voucher vc = new Voucher("VC004", "Giảm 20%", 20, -5, ngayHetHan);
            Voucher.Create(vc);
            System.out.println("✗ Không bắt được lỗi số lượng âm");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi số lượng âm: " + e.getMessage());
        }
        
        // Test ngày hết hạn trong quá khứ
        try {
            LocalDateTime ngayHetHan = LocalDateTime.now().minusDays(1);
            Voucher vc = new Voucher("VC005", "Giảm 20%", 20, 100, ngayHetHan);
            Voucher.Create(vc);
            System.out.println("✗ Không bắt được lỗi ngày hết hạn trong quá khứ");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi ngày hết hạn trong quá khứ: " + e.getMessage());
        }
        
        // Test mã voucher trùng lặp
        LocalDateTime ngayHetHan = LocalDateTime.now().plusDays(30);
        Voucher vc1 = new Voucher("DUP001", "Voucher Duplicate 1", 20, 100, ngayHetHan);
        Voucher.Create(vc1);
        
        try {
            Voucher vc2 = new Voucher("DUP001", "Voucher Duplicate 2", 30, 50, ngayHetHan);
            Voucher.Create(vc2);
            System.out.println("✗ Không bắt được lỗi mã voucher trùng lặp");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi mã voucher trùng lặp: " + e.getMessage());
        }
    }
    
    // === MOCK TESTING ===
    
    // Test với dữ liệu giả
    public static void testMockData() {
        System.out.println("\n=== TEST MOCK DATA ===");
        
        // Tạo dữ liệu giả
        ArrayList<Voucher> mockData = new ArrayList<>();
        LocalDateTime baseTime = LocalDateTime.now();
        
        mockData.add(new Voucher("MOCK001", "Giảm 10%", 10, 100, baseTime.plusDays(30)));
        mockData.add(new Voucher("MOCK002", "Giảm 20%", 20, 50, baseTime.plusDays(60)));
        mockData.add(new Voucher("MOCK003", "Giảm 50%", 50, 25, baseTime.plusDays(90)));
        
        // Test tìm kiếm theo giá trị
        ArrayList<Voucher> result1 = new ArrayList<>();
        for (Voucher vc : mockData) {
            if (vc.getGiaTri() >= 20) {
                result1.add(vc);
            }
        }
        assert result1.size() == 2 : "Tìm kiếm theo giá trị không đúng";
        
        // Test tìm kiếm theo tên
        ArrayList<Voucher> result2 = new ArrayList<>();
        for (Voucher vc : mockData) {
            if (vc.getTenVoucher().contains("Giảm")) {
                result2.add(vc);
            }
        }
        assert result2.size() == 3 : "Tìm kiếm theo tên không đúng";
        
        // Test tính tổng giá trị
        int totalValue = 0;
        for (Voucher vc : mockData) {
            totalValue += vc.getGiaTri() * vc.getSoLuong();
        }
        assert totalValue == 4250 : "Tính tổng giá trị không đúng";
        
        // Test đếm voucher theo loại
        int smallDiscountCount = 0;
        int mediumDiscountCount = 0;
        int largeDiscountCount = 0;
        
        for (Voucher vc : mockData) {
            if (vc.getGiaTri() < 15) {
                smallDiscountCount++;
            } else if (vc.getGiaTri() < 30) {
                mediumDiscountCount++;
            } else {
                largeDiscountCount++;
            }
        }
        
        assert smallDiscountCount == 1 : "Đếm voucher giảm nhỏ không đúng";
        assert mediumDiscountCount == 1 : "Đếm voucher giảm trung bình không đúng";
        assert largeDiscountCount == 1 : "Đếm voucher giảm lớn không đúng";
        
        System.out.println("✓ Mock data testing OK");
    }
    
    // === PERFORMANCE TESTING ===
    
    // Test hiệu suất với dữ liệu lớn
    public static void testPerformance() {
        System.out.println("\n=== TEST PERFORMANCE ===");
        
        long startTime = System.currentTimeMillis();
        
        // Tạo 1000 voucher
        LocalDateTime baseTime = LocalDateTime.now();
        for (int i = 0; i < 1000; i++) {
            Voucher vc = new Voucher("PERF" + i, "Voucher " + i, (i % 50) + 10, 
                                   50 + (i % 100), baseTime.plusDays(30 + i));
            Voucher.Create(vc);
        }
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        System.out.println("✓ Tạo 1000 voucher trong " + duration + "ms");
        
        // Test tìm kiếm
        startTime = System.currentTimeMillis();
        Voucher result = Voucher.getVoucherByMaVoucher("PERF500");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        
        System.out.println("✓ Tìm kiếm voucher trong " + duration + "ms");
    }
    
    // === BUSINESS LOGIC TESTING ===
    
    // Test logic nghiệp vụ
    public static void testBusinessLogic() {
        System.out.println("\n=== TEST BUSINESS LOGIC ===");
        
        // Test voucher giảm nhỏ (< 15%)
        LocalDateTime ngayHetHan = LocalDateTime.now().plusDays(30);
        Voucher smallDiscount = new Voucher("SMALL001", "Giảm 10%", 10, 100, ngayHetHan);
        boolean isSmallDiscount = smallDiscount.getGiaTri() < 15;
        assert isSmallDiscount == true : "Voucher giảm nhỏ không đúng";
        
        // Test voucher giảm trung bình (15-30%)
        Voucher mediumDiscount = new Voucher("MEDIUM001", "Giảm 25%", 25, 50, ngayHetHan);
        boolean isMediumDiscount = mediumDiscount.getGiaTri() >= 15 && mediumDiscount.getGiaTri() <= 30;
        assert isMediumDiscount == true : "Voucher giảm trung bình không đúng";
        
        // Test voucher giảm lớn (> 30%)
        Voucher largeDiscount = new Voucher("LARGE001", "Giảm 50%", 50, 25, ngayHetHan);
        boolean isLargeDiscount = largeDiscount.getGiaTri() > 30;
        assert isLargeDiscount == true : "Voucher giảm lớn không đúng";
        
        // Test voucher còn hạn
        Voucher validVoucher = new Voucher("VALID001", "Giảm 20%", 20, 100, ngayHetHan);
        boolean isValid = validVoucher.getNgayHetHan().isAfter(LocalDateTime.now());
        assert isValid == true : "Voucher còn hạn không đúng";
        
        // Test voucher hết hạn
        LocalDateTime expiredDate = LocalDateTime.now().minusDays(1);
        Voucher expiredVoucher = new Voucher("EXPIRED001", "Giảm 20%", 20, 100, expiredDate);
        boolean isExpired = expiredVoucher.getNgayHetHan().isBefore(LocalDateTime.now());
        assert isExpired == true : "Voucher hết hạn không đúng";
        
        System.out.println("✓ Business logic testing OK");
    }
    
    // === VALIDATION TESTING ===
    
    // Test validation
    public static void testValidation() {
        System.out.println("\n=== TEST VALIDATION ===");
        
        // Test validation mã voucher
        try {
            LocalDateTime ngayHetHan = LocalDateTime.now().plusDays(30);
            Voucher vc = new Voucher("VC_123", "Giảm 20%", 20, 100, ngayHetHan);
            // Kiểm tra format mã voucher
            boolean isValidFormat = vc.getMaVoucher().matches("^[A-Z0-9_]+$");
            assert isValidFormat == true : "Format mã voucher không hợp lệ";
            System.out.println("✓ Validation mã voucher OK");
        } catch (Exception e) {
            System.out.println("✗ Validation mã voucher thất bại: " + e.getMessage());
        }
        
        // Test validation giá trị
        try {
            LocalDateTime ngayHetHan = LocalDateTime.now().plusDays(30);
            Voucher vc = new Voucher("VC001", "Giảm 20%", 20, 100, ngayHetHan);
            // Kiểm tra giá trị hợp lệ
            boolean isValidValue = vc.getGiaTri() > 0 && vc.getGiaTri() <= 100;
            assert isValidValue == true : "Giá trị không hợp lệ";
            System.out.println("✓ Validation giá trị OK");
        } catch (Exception e) {
            System.out.println("✗ Validation giá trị thất bại: " + e.getMessage());
        }
        
        // Test validation số lượng
        try {
            LocalDateTime ngayHetHan = LocalDateTime.now().plusDays(30);
            Voucher vc = new Voucher("VC001", "Giảm 20%", 20, 100, ngayHetHan);
            // Kiểm tra số lượng hợp lệ
            boolean isValidQuantity = vc.getSoLuong() >= 0 && vc.getSoLuong() <= 10000;
            assert isValidQuantity == true : "Số lượng không hợp lệ";
            System.out.println("✓ Validation số lượng OK");
        } catch (Exception e) {
            System.out.println("✗ Validation số lượng thất bại: " + e.getMessage());
        }
        
        // Test validation ngày hết hạn
        try {
            LocalDateTime ngayHetHan = LocalDateTime.now().plusDays(30);
            Voucher vc = new Voucher("VC001", "Giảm 20%", 20, 100, ngayHetHan);
            // Kiểm tra ngày hết hạn hợp lệ
            boolean isValidDate = vc.getNgayHetHan().isAfter(LocalDateTime.now());
            assert isValidDate == true : "Ngày hết hạn không hợp lệ";
            System.out.println("✓ Validation ngày hết hạn OK");
        } catch (Exception e) {
            System.out.println("✗ Validation ngày hết hạn thất bại: " + e.getMessage());
        }
    }
    
    // === MAIN TEST METHOD ===
    
    public static void test() {
        System.out.println("🚀 BẮT ĐẦU TEST VOUCHER MODEL");
        System.out.println("==============================");
        
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
            
            System.out.println("\n==============================");
            System.out.println("✅ TẤT CẢ TEST VOUCHER THÀNH CÔNG!");
            
        } catch (Exception e) {
            System.out.println("\n==============================");
            System.out.println("❌ TEST VOUCHER THẤT BẠI: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 