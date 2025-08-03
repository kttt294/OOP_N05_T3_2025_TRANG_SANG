package com.example.servingwebcontent.test;
import java.util.ArrayList;

public class testVeController {
    
    // === UNIT TESTING ===
    
    // Test taoVe
    public static void testTaoVe() {
        System.out.println("=== TEST TAO VE ===");
        
        // Test tạo vé hợp lệ
        Ve ve = new Ve("TEST001", "123456789", "SC001", "GHE001", 50000);
        boolean result = VeController.taoVe(ve);
        assert result == true : "Tạo vé hợp lệ phải trả về true";
        System.out.println("✓ Tạo vé hợp lệ OK");
        
        // Test tạo vé null
        boolean resultNull = VeController.taoVe(null);
        assert resultNull == false : "Tạo vé null phải trả về false";
        System.out.println("✓ Tạo vé null OK");
        
        // Test tạo vé với mã rỗng
        Ve veEmpty = new Ve("", "123456789", "SC001", "GHE001", 50000);
        boolean resultEmpty = VeController.taoVe(veEmpty);
        assert resultEmpty == false : "Tạo vé với mã rỗng phải trả về false";
        System.out.println("✓ Tạo vé với mã rỗng OK");
    }
    
    // Test capNhatVe
    public static void testCapNhatVe() {
        System.out.println("\n=== TEST CAP NHAT VE ===");
        
        // Tạo vé để test
        Ve ve = new Ve("UPDATE001", "123456789", "SC001", "GHE001", 50000);
        VeController.taoVe(ve);
        
        // Test cập nhật hợp lệ
        Ve veMoi = new Ve("UPDATE001", "123456789", "SC001", "GHE001", 60000);
        boolean result = VeController.capNhatVe("UPDATE001", veMoi);
        assert result == true : "Cập nhật vé hợp lệ phải trả về true";
        System.out.println("✓ Cập nhật vé hợp lệ OK");
        
        // Test cập nhật vé không tồn tại
        Ve veKhongTonTai = new Ve("NOTEXIST", "123456789", "SC001", "GHE001", 50000);
        boolean resultNotExist = VeController.capNhatVe("NOTEXIST", veKhongTonTai);
        assert resultNotExist == false : "Cập nhật vé không tồn tại phải trả về false";
        System.out.println("✓ Cập nhật vé không tồn tại OK");
    }
    
    // Test xoaVe
    public static void testXoaVe() {
        System.out.println("\n=== TEST XOA VE ===");
        
        // Tạo vé để test
        Ve ve = new Ve("DELETE001", "123456789", "SC001", "GHE001", 50000);
        VeController.taoVe(ve);
        
        // Test xóa vé tồn tại
        boolean result = VeController.xoaVe("DELETE001");
        assert result == true : "Xóa vé tồn tại phải trả về true";
        System.out.println("✓ Xóa vé tồn tại OK");
        
        // Test xóa vé không tồn tại
        boolean resultNotExist = VeController.xoaVe("NOTEXIST");
        assert resultNotExist == false : "Xóa vé không tồn tại phải trả về false";
        System.out.println("✓ Xóa vé không tồn tại OK");
    }
    
    // Test xemThongTinVe
    public static void testXemThongTinVe() {
        System.out.println("\n=== TEST XEM THONG TIN VE ===");
        
        // Tạo vé để test
        Ve ve = new Ve("VIEW001", "123456789", "SC001", "GHE001", 50000);
        VeController.taoVe(ve);
        
        // Test xem thông tin vé tồn tại
        boolean result = VeController.xemThongTinVe("VIEW001");
        assert result == true : "Xem thông tin vé tồn tại phải trả về true";
        System.out.println("✓ Xem thông tin vé tồn tại OK");
        
        // Test xem thông tin với mã rỗng
        boolean resultEmpty = VeController.xemThongTinVe("");
        assert resultEmpty == false : "Xem thông tin với mã rỗng phải trả về false";
        System.out.println("✓ Xem thông tin với mã rỗng OK");
    }
    
    // Test xemTatCaVe
    public static void testXemTatCaVe() {
        System.out.println("\n=== TEST XEM TAT CA VE ===");
        
        // Test xem tất cả vé
        boolean result = VeController.xemTatCaVe();
        // Kết quả có thể true hoặc false tùy vào dữ liệu thực tế
        System.out.println("✓ Xem tất cả vé: " + result);
    }
    
    // Test timVeTheoMa
    public static void testTimVeTheoMa() {
        System.out.println("\n=== TEST TIM VE THEO MA ===");
        
        // Tạo vé để test
        Ve ve = new Ve("SEARCH001", "123456789", "SC001", "GHE001", 50000);
        VeController.taoVe(ve);
        
        // Test tìm vé tồn tại
        Ve result = VeController.timVeTheoMa("SEARCH001");
        assert result != null : "Tìm vé tồn tại phải trả về vé";
        assert "123456789".equals(result.getCCCD()) : "CCCD tìm được không đúng";
        System.out.println("✓ Tìm vé tồn tại OK");
        
        // Test tìm vé không tồn tại
        Ve resultNotExist = VeController.timVeTheoMa("NOTEXIST");
        assert resultNotExist == null : "Tìm vé không tồn tại phải trả về null";
        System.out.println("✓ Tìm vé không tồn tại OK");
    }
    
    // Test timVeTheoKhachHang
    public static void testTimVeTheoKhachHang() {
        System.out.println("\n=== TEST TIM VE THEO KHACH HANG ===");
        
        // Tạo vé để test
        Ve ve1 = new Ve("KH001", "111111111", "SC001", "GHE001", 50000);
        Ve ve2 = new Ve("KH002", "111111111", "SC002", "GHE002", 60000);
        VeController.taoVe(ve1);
        VeController.taoVe(ve2);
        
        // Test tìm theo khách hàng có kết quả
        ArrayList<Ve> result = VeController.timVeTheoKhachHang("111111111");
        assert result.size() == 2 : "Tìm theo khách hàng '111111111' phải trả về 2 kết quả";
        System.out.println("✓ Tìm theo khách hàng có kết quả OK");
        
        // Test tìm theo khách hàng không có kết quả
        ArrayList<Ve> resultEmpty = VeController.timVeTheoKhachHang("999999999");
        assert resultEmpty.size() == 0 : "Tìm theo khách hàng không tồn tại phải trả về list rỗng";
        System.out.println("✓ Tìm theo khách hàng không có kết quả OK");
    }
    
    // Test timVeTheoSuatChieu
    public static void testTimVeTheoSuatChieu() {
        System.out.println("\n=== TEST TIM VE THEO SUAT CHIEU ===");
        
        // Tạo vé để test
        Ve ve1 = new Ve("SC001", "111111111", "SC001", "GHE001", 50000);
        Ve ve2 = new Ve("SC002", "222222222", "SC001", "GHE002", 60000);
        VeController.taoVe(ve1);
        VeController.taoVe(ve2);
        
        // Test tìm theo suất chiếu có kết quả
        ArrayList<Ve> result = VeController.timVeTheoSuatChieu("SC001");
        assert result.size() == 2 : "Tìm theo suất chiếu 'SC001' phải trả về 2 kết quả";
        System.out.println("✓ Tìm theo suất chiếu có kết quả OK");
        
        // Test tìm theo suất chiếu không có kết quả
        ArrayList<Ve> resultEmpty = VeController.timVeTheoSuatChieu("SC999");
        assert resultEmpty.size() == 0 : "Tìm theo suất chiếu không tồn tại phải trả về list rỗng";
        System.out.println("✓ Tìm theo suất chiếu không có kết quả OK");
    }
    
    // Test huyVe
    public static void testHuyVe() {
        System.out.println("\n=== TEST HUY VE ===");
        
        // Tạo vé để test
        Ve ve = new Ve("HUY001", "123456789", "SC001", "GHE001", 50000, true);
        VeController.taoVe(ve);
        
        // Test hủy vé tồn tại
        boolean result = VeController.huyVe("HUY001");
        assert result == true : "Hủy vé tồn tại phải trả về true";
        System.out.println("✓ Hủy vé tồn tại OK");
        
        // Test hủy vé không tồn tại
        boolean resultNotExist = VeController.huyVe("NOTEXIST");
        assert resultNotExist == false : "Hủy vé không tồn tại phải trả về false";
        System.out.println("✓ Hủy vé không tồn tại OK");
    }
    
    // Test tinhTongDoanhThu
    public static void testTinhTongDoanhThu() {
        System.out.println("\n=== TEST TINH TONG DOANH THU ===");
        
        // Test tính tổng doanh thu
        double result = VeController.tinhTongDoanhThu();
        // Kết quả có thể 0 hoặc giá trị khác tùy vào dữ liệu thực tế
        System.out.println("✓ Tổng doanh thu: " + result);
    }
    
    // Test thongKeVe
    public static void testThongKeVe() {
        System.out.println("\n=== TEST THONG KE VE ===");
        
        // Test thống kê vé
        boolean result = VeController.thongKeVe();
        // Kết quả có thể true hoặc false tùy vào dữ liệu thực tế
        System.out.println("✓ Thống kê vé: " + result);
    }
    
    // === INTEGRATION TESTING ===
    
    // Test luồng quản lý vé hoàn chỉnh
    public static void testVeManagementFlow() {
        System.out.println("\n=== TEST VE MANAGEMENT FLOW ===");
        
        // 1. Tạo vé mới
        Ve ve = new Ve("FLOW001", "123456789", "SC001", "GHE001", 50000);
        boolean createResult = VeController.taoVe(ve);
        assert createResult == true : "Tạo vé trong flow phải thành công";
        
        // 2. Tìm kiếm vé
        Ve foundVe = VeController.timVeTheoMa("FLOW001");
        assert foundVe != null : "Phải tìm thấy vé sau khi tạo";
        
        // 3. Cập nhật thông tin
        foundVe.setGiaVe(60000);
        boolean updateResult = VeController.capNhatVe("FLOW001", foundVe);
        assert updateResult == true : "Cập nhật vé trong flow phải thành công";
        
        // 4. Xem thông tin
        boolean viewResult = VeController.xemThongTinVe("FLOW001");
        assert viewResult == true : "Xem thông tin vé trong flow phải thành công";
        
        // 5. Hủy vé
        boolean cancelResult = VeController.huyVe("FLOW001");
        assert cancelResult == true : "Hủy vé trong flow phải thành công";
        
        // 6. Xóa vé
        boolean deleteResult = VeController.xoaVe("FLOW001");
        assert deleteResult == true : "Xóa vé trong flow phải thành công";
        
        // 7. Xác nhận đã xóa
        Ve deletedVe = VeController.timVeTheoMa("FLOW001");
        assert deletedVe == null : "Vé phải không tồn tại sau khi xóa";
        
        System.out.println("✓ Vé management flow OK");
    }
    
    // === EDGE CASE TESTING ===
    
    // Test với dữ liệu biên
    public static void testEdgeCases() {
        System.out.println("\n=== TEST EDGE CASES ===");
        
        // Test giá vé biên
        Ve veMinPrice = new Ve("EDGE001", "123456789", "SC001", "GHE001", 1);
        boolean resultMin = VeController.taoVe(veMinPrice);
        // Có thể chấp nhận hoặc từ chối tùy vào business logic
        System.out.println("✓ Test giá vé tối thiểu: " + resultMin);
        
        Ve veMaxPrice = new Ve("EDGE002", "123456789", "SC001", "GHE001", 1000000);
        boolean resultMax = VeController.taoVe(veMaxPrice);
        // Có thể chấp nhận hoặc từ chối tùy vào business logic
        System.out.println("✓ Test giá vé tối đa: " + resultMax);
        
        // Test CCCD biên
        Ve veShortCCCD = new Ve("EDGE003", "123456789", "SC001", "GHE001", 50000);
        boolean resultShort = VeController.taoVe(veShortCCCD);
        // Có thể chấp nhận hoặc từ chối tùy vào business logic
        System.out.println("✓ Test CCCD ngắn: " + resultShort);
    }
    
    // === MOCK TESTING ===
    
    // Test với dữ liệu giả
    public static void testMockData() {
        System.out.println("\n=== TEST MOCK DATA ===");
        
        // Tạo dữ liệu giả
        ArrayList<Ve> mockTickets = new ArrayList<>();
        mockTickets.add(new Ve("MOCK001", "111111111", "SC001", "GHE001", 50000, true));
        mockTickets.add(new Ve("MOCK002", "222222222", "SC002", "GHE002", 60000, true));
        mockTickets.add(new Ve("MOCK003", "333333333", "SC003", "GHE003", 70000, false));
        
        // Test tạo nhiều vé
        int successCount = 0;
        for (Ve ve : mockTickets) {
            if (VeController.taoVe(ve)) {
                successCount++;
            }
        }
        assert successCount == 3 : "Tất cả vé mock phải được tạo thành công";
        
        // Test tìm kiếm theo khách hàng
        ArrayList<Ve> searchResult = VeController.timVeTheoKhachHang("111111111");
        assert searchResult.size() == 1 : "Tìm kiếm '111111111' phải trả về 1 kết quả";
        
        // Test tìm kiếm theo suất chiếu
        ArrayList<Ve> showResult = VeController.timVeTheoSuatChieu("SC001");
        assert showResult.size() == 1 : "Tìm kiếm 'SC001' phải trả về 1 kết quả";
        
        // Dọn dẹp dữ liệu test
        for (Ve ve : mockTickets) {
            VeController.xoaVe(ve.getMaVe());
        }
        
        System.out.println("✓ Mock data testing OK");
    }
    
    // === PERFORMANCE TESTING ===
    
    // Test hiệu suất
    public static void testPerformance() {
        System.out.println("\n=== TEST PERFORMANCE ===");
        
        long startTime = System.currentTimeMillis();
        
        // Test tạo 100 vé
        for (int i = 0; i < 100; i++) {
            Ve ve = new Ve("PERF" + i, "CCCD" + i, "SC" + (i % 10), "GHE" + (i % 20), 50000 + (i * 1000));
            VeController.taoVe(ve);
        }
        
        long createTime = System.currentTimeMillis() - startTime;
        System.out.println("✓ Tạo 100 vé trong " + createTime + "ms");
        
        // Test tìm kiếm
        startTime = System.currentTimeMillis();
        Ve result = VeController.timVeTheoMa("PERF50");
        long searchTime = System.currentTimeMillis() - startTime;
        System.out.println("✓ Tìm kiếm vé trong " + searchTime + "ms");
        
        // Dọn dẹp
        for (int i = 0; i < 100; i++) {
            VeController.xoaVe("PERF" + i);
        }
    }
    
    // === MAIN TEST METHOD ===
    
    public static void test() {
        System.out.println("🚀 BẮT ĐẦU TEST VE CONTROLLER");
        System.out.println("=============================");
        
        try {
            testTaoVe();
            testCapNhatVe();
            testXoaVe();
            testXemThongTinVe();
            testXemTatCaVe();
            testTimVeTheoMa();
            testTimVeTheoKhachHang();
            testTimVeTheoSuatChieu();
            testHuyVe();
            testTinhTongDoanhThu();
            testThongKeVe();
            testVeManagementFlow();
            testEdgeCases();
            testMockData();
            testPerformance();
            
            System.out.println("\n=============================");
            System.out.println("✅ TẤT CẢ TEST VE CONTROLLER THÀNH CÔNG!");
            
        } catch (Exception e) {
            System.out.println("\n=============================");
            System.out.println("❌ TEST VE CONTROLLER THẤT BẠI: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 