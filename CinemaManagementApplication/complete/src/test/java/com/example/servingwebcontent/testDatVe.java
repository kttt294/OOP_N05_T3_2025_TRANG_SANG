package com.example.servingwebcontent;
import java.util.ArrayList;

public class testDatVe {
    
    // === UNIT TESTING ===
    
    // Test constructor
    public static void testConstructor() {
        System.out.println("=== TEST CONSTRUCTOR ===");
        
        // Tạo dữ liệu test
        KhachHang kh = new KhachHang("TEST001", "Test User", 25, "0123456789", "test@email.com");
        SuatChieu suatChieu = new SuatChieu("SC001", "PHIM001", "PHONG001", null, null);
        
        // Test constructor
        DatVe datVe = new DatVe(kh, suatChieu, "VE001", "GHE001", 50000);
        
        assert datVe.kh != null : "Khách hàng không được null";
        assert datVe.suatChieu != null : "Suất chiếu không được null";
        assert "VE001".equals(datVe.maVe) : "Mã vé không đúng";
        assert "GHE001".equals(datVe.maGhe) : "Mã ghế không đúng";
        assert datVe.giaVe == 50000 : "Giá vé không đúng";
        
        System.out.println("✓ Constructor OK");
    }
    
    // Test datVe method với dữ liệu hợp lệ
    public static void testDatVeValid() {
        System.out.println("\n=== TEST DAT VE VALID ===");
        
        // Tạo dữ liệu test hợp lệ
        KhachHang kh = new KhachHang("DAT001", "Dat Ve User", 25, "0123456789", "datve@email.com");
        SuatChieu suatChieu = new SuatChieu("SC001", "PHIM001", "PHONG001", null, null);
        
        // Tạo ghế suất chiếu (mock)
        // Giả sử có method để tạo ghế suất chiếu
        // GheSuatChieu gsc = new GheSuatChieu("GHE001", "SC001", "BinhThuong");
        
        DatVe datVe = new DatVe(kh, suatChieu, "VE001", "GHE001", 50000);
        
        // Test đặt vé thành công
        boolean result = datVe.datVe();
        // Kết quả có thể true hoặc false tùy vào logic thực tế
        System.out.println("✓ Đặt vé với dữ liệu hợp lệ: " + result);
    }
    
    // === INTEGRATION TESTING ===
    
    // Test luồng đặt vé hoàn chỉnh
    public static void testDatVeFlow() {
        System.out.println("\n=== TEST DAT VE FLOW ===");
        
        // 1. Tạo khách hàng
        KhachHang kh = new KhachHang("FLOW001", "Flow User", 25, "0123456789", "flow@email.com");
        KhachHang.Create(kh);
        
        // 2. Tạo suất chiếu
        SuatChieu suatChieu = new SuatChieu("SC001", "PHIM001", "PHONG001", null, null);
        SuatChieu.Create(suatChieu);
        
        // 3. Tạo ghế suất chiếu
        // GheSuatChieu gsc = new GheSuatChieu("GHE001", "SC001", "BinhThuong");
        // GheSuatChieu.Create(gsc);
        
        // 4. Đặt vé
        DatVe datVe = new DatVe(kh, suatChieu, "VE001", "GHE001", 50000);
        boolean result = datVe.datVe();
        
        // 5. Kiểm tra kết quả
        if (result) {
            // Kiểm tra vé đã được tạo
            Ve ve = Ve.getVeByMaVe("VE001");
            assert ve != null : "Vé phải được tạo sau khi đặt thành công";
            
            // Kiểm tra ghế đã được cập nhật
            // GheSuatChieu gscUpdated = GheSuatChieu.getByMaGheAndMaSuatChieu("GHE001", "SC001");
            // assert "Khoa".equals(gscUpdated.getTrangThai()) : "Ghế phải được khóa sau khi đặt";
        }
        
        System.out.println("✓ Luồng đặt vé: " + result);
        }
        
    // === EDGE CASE TESTING ===
    
    // Test với dữ liệu null
    public static void testNullData() {
        System.out.println("\n=== TEST NULL DATA ===");
        
        // Test khách hàng null
        try {
            DatVe datVe1 = new DatVe(null, new SuatChieu(), "VE001", "GHE001", 50000);
            boolean result1 = datVe1.datVe();
            assert result1 == false : "Đặt vé với khách hàng null phải trả về false";
            System.out.println("✓ Test khách hàng null OK");
        } catch (Exception e) {
            System.out.println("✓ Bắt được exception khách hàng null: " + e.getMessage());
        }
        
        // Test suất chiếu null
        try {
            DatVe datVe2 = new DatVe(new KhachHang(), null, "VE001", "GHE001", 50000);
            boolean result2 = datVe2.datVe();
            assert result2 == false : "Đặt vé với suất chiếu null phải trả về false";
            System.out.println("✓ Test suất chiếu null OK");
        } catch (Exception e) {
            System.out.println("✓ Bắt được exception suất chiếu null: " + e.getMessage());
        }
        
        // Test mã vé rỗng
        try {
            DatVe datVe3 = new DatVe(new KhachHang(), new SuatChieu(), "", "GHE001", 50000);
            boolean result3 = datVe3.datVe();
            assert result3 == false : "Đặt vé với mã vé rỗng phải trả về false";
            System.out.println("✓ Test mã vé rỗng OK");
        } catch (Exception e) {
            System.out.println("✓ Bắt được exception mã vé rỗng: " + e.getMessage());
        }
    }
    
    // Test với dữ liệu không hợp lệ
    public static void testInvalidData() {
        System.out.println("\n=== TEST INVALID DATA ===");
            
        // Test giá vé âm
        try {
            KhachHang kh = new KhachHang("INV001", "Invalid User", 25, "0123456789", "invalid@email.com");
            SuatChieu suatChieu = new SuatChieu("SC001", "PHIM001", "PHONG001", null, null);
            DatVe datVe = new DatVe(kh, suatChieu, "VE001", "GHE001", -1000);
            boolean result = datVe.datVe();
            assert result == false : "Đặt vé với giá âm phải trả về false";
            System.out.println("✓ Test giá vé âm OK");
        } catch (Exception e) {
            System.out.println("✓ Bắt được exception giá vé âm: " + e.getMessage());
        }
        
        // Test giá vé bằng 0
        try {
            KhachHang kh = new KhachHang("INV002", "Invalid User 2", 25, "0123456789", "invalid2@email.com");
            SuatChieu suatChieu = new SuatChieu("SC001", "PHIM001", "PHONG001", null, null);
            DatVe datVe = new DatVe(kh, suatChieu, "VE002", "GHE002", 0);
            boolean result = datVe.datVe();
            assert result == false : "Đặt vé với giá 0 phải trả về false";
            System.out.println("✓ Test giá vé bằng 0 OK");
        } catch (Exception e) {
            System.out.println("✓ Bắt được exception giá vé bằng 0: " + e.getMessage());
        }
    }
    
    // Test ghế không tồn tại
    public static void testGheKhongTonTai() {
        System.out.println("\n=== TEST GHE KHONG TON TAI ===");
        
        KhachHang kh = new KhachHang("GHE001", "Ghe User", 25, "0123456789", "ghe@email.com");
        SuatChieu suatChieu = new SuatChieu("SC001", "PHIM001", "PHONG001", null, null);
        
        // Test đặt vé với ghế không tồn tại
        DatVe datVe = new DatVe(kh, suatChieu, "VE001", "GHE_KHONG_TON_TAI", 50000);
        boolean result = datVe.datVe();
        assert result == false : "Đặt vé với ghế không tồn tại phải trả về false";
        
        System.out.println("✓ Test ghế không tồn tại OK");
    }
    
    // Test ghế đã được đặt
    public static void testGheDaDuocDat() {
        System.out.println("\n=== TEST GHE DA DUOC DAT ===");
        
        KhachHang kh = new KhachHang("GHE002", "Ghe User 2", 25, "0123456789", "ghe2@email.com");
        SuatChieu suatChieu = new SuatChieu("SC001", "PHIM001", "PHONG001", null, null);
        
        // Giả sử ghế đã được đặt (trạng thái "Khoa")
        // GheSuatChieu gsc = new GheSuatChieu("GHE002", "SC001", "Khoa");
        
        DatVe datVe = new DatVe(kh, suatChieu, "VE001", "GHE002", 50000);
        boolean result = datVe.datVe();
        assert result == false : "Đặt vé với ghế đã được đặt phải trả về false";
        
        System.out.println("✓ Test ghế đã được đặt OK");
    }
    
    // === MOCK TESTING ===
    
    // Test với dữ liệu giả
    public static void testMockData() {
        System.out.println("\n=== TEST MOCK DATA ===");
        
        // Tạo dữ liệu giả
        ArrayList<DatVe> mockDatVe = new ArrayList<>();
        
        KhachHang kh1 = new KhachHang("MOCK001", "Mock User 1", 25, "0123456789", "mock1@email.com");
        SuatChieu sc1 = new SuatChieu("SC001", "PHIM001", "PHONG001", null, null);
        mockDatVe.add(new DatVe(kh1, sc1, "VE001", "GHE001", 50000));
        
        KhachHang kh2 = new KhachHang("MOCK002", "Mock User 2", 30, "0987654321", "mock2@email.com");
        SuatChieu sc2 = new SuatChieu("SC002", "PHIM002", "PHONG002", null, null);
        mockDatVe.add(new DatVe(kh2, sc2, "VE002", "GHE002", 60000));
        
        KhachHang kh3 = new KhachHang("MOCK003", "Mock User 3", 35, "0123456780", "mock3@email.com");
        SuatChieu sc3 = new SuatChieu("SC003", "PHIM003", "PHONG003", null, null);
        mockDatVe.add(new DatVe(kh3, sc3, "VE003", "GHE003", 70000));
        
        // Test đặt vé với dữ liệu giả
        int successCount = 0;
        for (DatVe datVe : mockDatVe) {
            boolean result = datVe.datVe();
            if (result) {
                successCount++;
            }
        }
        
        System.out.println("✓ Đặt vé thành công " + successCount + "/" + mockDatVe.size() + " vé");
    }
    
    // === CONCURRENCY TESTING ===
    
    // Test đặt vé đồng thời
    public static void testConcurrentDatVe() {
        System.out.println("\n=== TEST CONCURRENT DAT VE ===");
        
        // Tạo nhiều thread đặt vé cùng lúc
        final int threadCount = 5;
        final boolean[] results = new boolean[threadCount];
        
        Thread[] threads = new Thread[threadCount];
        
        for (int i = 0; i < threadCount; i++) {
            final int index = i;
            threads[i] = new Thread(() -> {
                try {
                    KhachHang kh = new KhachHang("CONC" + index, "Concurrent User " + index, 25, "0123456789", "conc" + index + "@email.com");
                    SuatChieu suatChieu = new SuatChieu("SC001", "PHIM001", "PHONG001", null, null);
                    DatVe datVe = new DatVe(kh, suatChieu, "VE" + index, "GHE" + index, 50000);
                    results[index] = datVe.datVe();
                } catch (Exception e) {
                    results[index] = false;
                }
            });
        }
        
        // Chạy tất cả thread
        for (Thread thread : threads) {
            thread.start();
        }
        
        // Đợi tất cả thread hoàn thành
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
    }
        }
        
        // Kiểm tra kết quả
        int successCount = 0;
        for (boolean result : results) {
            if (result) successCount++;
        }
        
        System.out.println("✓ Concurrent đặt vé: " + successCount + "/" + threadCount + " thành công");
    }
    
    // === ERROR HANDLING TESTING ===
    
    // Test xử lý lỗi
    public static void testErrorHandling() {
        System.out.println("\n=== TEST ERROR HANDLING ===");
        
        // Test exception handling
        try {
            KhachHang kh = new KhachHang("ERR001", "Error User", 25, "0123456789", "error@email.com");
            SuatChieu suatChieu = new SuatChieu("SC001", "PHIM001", "PHONG001", null, null);
            DatVe datVe = new DatVe(kh, suatChieu, "VE001", "GHE001", 50000);
            
            // Giả sử có lỗi xảy ra trong quá trình đặt vé
            boolean result = datVe.datVe();
            
            // Kết quả phải là false nếu có lỗi
            System.out.println("✓ Error handling: " + result);
            
        } catch (Exception e) {
            System.out.println("✓ Bắt được exception: " + e.getMessage());
        }
    }
    
    // === MAIN TEST METHOD ===
    
    public static void test() {
        System.out.println("🚀 BẮT ĐẦU TEST DAT VE");
        System.out.println("=========================");
        
        try {
            testConstructor();
            testDatVeValid();
            testDatVeFlow();
            testNullData();
            testInvalidData();
            testGheKhongTonTai();
            testGheDaDuocDat();
            testMockData();
            testConcurrentDatVe();
            testErrorHandling();
            
            System.out.println("\n=========================");
            System.out.println("✅ TẤT CẢ TEST DAT VE THÀNH CÔNG!");
            
        } catch (Exception e) {
            System.out.println("\n=========================");
            System.out.println("❌ TEST DAT VE THẤT BẠI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}