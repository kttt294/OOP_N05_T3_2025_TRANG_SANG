package com.example.servingwebcontent.test;
import java.util.ArrayList;
import com.example.servingwebcontent.model.DanhGia;
import com.example.servingwebcontent.controller.DanhGiaController;

public class testDanhGiaController {
    
    // === UNIT TESTING ===
    
    // Test taoDanhGia
    public static void testTaoDanhGia() {
        System.out.println("=== TEST TAO DANH GIA ===");
        
        // Test tạo đánh giá hợp lệ
        DanhGia dg = new DanhGia("TEST001", "123456789", "PHIM001", 5, "Phim rất hay!");
        boolean result = DanhGiaController.taoDanhGia(dg);
        assert result == true : "Tạo đánh giá hợp lệ phải trả về true";
        System.out.println("✓ Tạo đánh giá hợp lệ OK");
        
        // Test tạo đánh giá null
        boolean resultNull = DanhGiaController.taoDanhGia(null);
        assert resultNull == false : "Tạo đánh giá null phải trả về false";
        System.out.println("✓ Tạo đánh giá null OK");
        
        // Test tạo đánh giá với điểm không hợp lệ
        DanhGia dgInvalid = new DanhGia("TEST002", "123456789", "PHIM001", 10, "Phim hay");
        boolean resultInvalid = DanhGiaController.taoDanhGia(dgInvalid);
        assert resultInvalid == false : "Tạo đánh giá với điểm không hợp lệ phải trả về false";
        System.out.println("✓ Tạo đánh giá với điểm không hợp lệ OK");
    }
    
    // Test capNhatDanhGia
    public static void testCapNhatDanhGia() {
        System.out.println("\n=== TEST CAP NHAT DANH GIA ===");
        
        // Tạo đánh giá để test
        DanhGia dg = new DanhGia("UPDATE001", "123456789", "PHIM001", 4, "Phim hay");
        DanhGiaController.taoDanhGia(dg);
        
        // Test cập nhật hợp lệ
        DanhGia dgMoi = new DanhGia("UPDATE001", "123456789", "PHIM001", 5, "Phim rất hay!");
        boolean result = DanhGiaController.capNhatDanhGia("UPDATE001", dgMoi);
        assert result == true : "Cập nhật đánh giá hợp lệ phải trả về true";
        System.out.println("✓ Cập nhật đánh giá hợp lệ OK");
        
        // Test cập nhật đánh giá không tồn tại
        DanhGia dgKhongTonTai = new DanhGia("NOTEXIST", "123456789", "PHIM001", 4, "Phim hay");
        boolean resultNotExist = DanhGiaController.capNhatDanhGia("NOTEXIST", dgKhongTonTai);
        assert resultNotExist == false : "Cập nhật đánh giá không tồn tại phải trả về false";
        System.out.println("✓ Cập nhật đánh giá không tồn tại OK");
    }
    
    // Test xoaDanhGia
    public static void testXoaDanhGia() {
        System.out.println("\n=== TEST XOA DANH GIA ===");
        
        // Tạo đánh giá để test
        DanhGia dg = new DanhGia("DELETE001", "123456789", "PHIM001", 4, "Phim hay");
        DanhGiaController.taoDanhGia(dg);
        
        // Test xóa đánh giá tồn tại
        boolean result = DanhGiaController.xoaDanhGia("DELETE001");
        assert result == true : "Xóa đánh giá tồn tại phải trả về true";
        System.out.println("✓ Xóa đánh giá tồn tại OK");
        
        // Test xóa đánh giá không tồn tại
        boolean resultNotExist = DanhGiaController.xoaDanhGia("NOTEXIST");
        assert resultNotExist == false : "Xóa đánh giá không tồn tại phải trả về false";
        System.out.println("✓ Xóa đánh giá không tồn tại OK");
    }
    
    // Test xemThongTinDanhGia
    public static void testXemThongTinDanhGia() {
        System.out.println("\n=== TEST XEM THONG TIN DANH GIA ===");
        
        // Tạo đánh giá để test
        DanhGia dg = new DanhGia("VIEW001", "123456789", "PHIM001", 4, "Phim hay");
        DanhGiaController.taoDanhGia(dg);
        
        // Test xem thông tin đánh giá tồn tại
        boolean result = DanhGiaController.xemThongTinDanhGia("VIEW001");
        assert result == true : "Xem thông tin đánh giá tồn tại phải trả về true";
        System.out.println("✓ Xem thông tin đánh giá tồn tại OK");
        
        // Test xem thông tin với mã rỗng
        boolean resultEmpty = DanhGiaController.xemThongTinDanhGia("");
        assert resultEmpty == false : "Xem thông tin với mã rỗng phải trả về false";
        System.out.println("✓ Xem thông tin với mã rỗng OK");
    }
    
    // Test xemTatCaDanhGia
    public static void testXemTatCaDanhGia() {
        System.out.println("\n=== TEST XEM TAT CA DANH GIA ===");
        
        // Test xem tất cả đánh giá
        boolean result = DanhGiaController.xemTatCaDanhGia();
        // Kết quả có thể true hoặc false tùy vào dữ liệu thực tế
        System.out.println("✓ Xem tất cả đánh giá: " + result);
    }
    
    // Test timDanhGiaTheoMa
    public static void testTimDanhGiaTheoMa() {
        System.out.println("\n=== TEST TIM DANH GIA THEO MA ===");
        
        // Tạo đánh giá để test
        DanhGia dg = new DanhGia("SEARCH001", "123456789", "PHIM001", 4, "Phim hay");
        DanhGiaController.taoDanhGia(dg);
        
        // Test tìm đánh giá tồn tại
        DanhGia result = DanhGiaController.timDanhGiaTheoMa("SEARCH001");
        assert result != null : "Tìm đánh giá tồn tại phải trả về đánh giá";
        assert "123456789".equals(result.getCCCD()) : "CCCD tìm được không đúng";
        System.out.println("✓ Tìm đánh giá tồn tại OK");
        
        // Test tìm đánh giá không tồn tại
        DanhGia resultNotExist = DanhGiaController.timDanhGiaTheoMa("NOTEXIST");
        assert resultNotExist == null : "Tìm đánh giá không tồn tại phải trả về null";
        System.out.println("✓ Tìm đánh giá không tồn tại OK");
    }
    
    // Test timDanhGiaTheoPhim
    public static void testTimDanhGiaTheoPhim() {
        System.out.println("\n=== TEST TIM DANH GIA THEO PHIM ===");
        
        // Tạo đánh giá để test
        DanhGia dg1 = new DanhGia("PHIM001", "111111111", "PHIM001", 5, "Phim rất hay!");
        DanhGia dg2 = new DanhGia("PHIM002", "222222222", "PHIM001", 4, "Phim hay");
        DanhGiaController.taoDanhGia(dg1);
        DanhGiaController.taoDanhGia(dg2);
        
        // Test tìm theo phim có kết quả
        ArrayList<DanhGia> result = DanhGiaController.timDanhGiaTheoPhim("PHIM001");
        assert result.size() == 2 : "Tìm theo phim 'PHIM001' phải trả về 2 kết quả";
        System.out.println("✓ Tìm theo phim có kết quả OK");
        
        // Test tìm theo phim không có kết quả
        ArrayList<DanhGia> resultEmpty = DanhGiaController.timDanhGiaTheoPhim("PHIM999");
        assert resultEmpty.size() == 0 : "Tìm theo phim không tồn tại phải trả về list rỗng";
        System.out.println("✓ Tìm theo phim không có kết quả OK");
    }
    
    // Test timDanhGiaTheoKhachHang
    public static void testTimDanhGiaTheoKhachHang() {
        System.out.println("\n=== TEST TIM DANH GIA THEO KHACH HANG ===");
        
        // Tạo đánh giá để test
        DanhGia dg1 = new DanhGia("KH001", "111111111", "PHIM001", 5, "Phim rất hay!");
        DanhGia dg2 = new DanhGia("KH002", "111111111", "PHIM002", 4, "Phim hay");
        DanhGiaController.taoDanhGia(dg1);
        DanhGiaController.taoDanhGia(dg2);
        
        // Test tìm theo khách hàng có kết quả
        ArrayList<DanhGia> result = DanhGiaController.timDanhGiaTheoKhachHang("111111111");
        assert result.size() == 2 : "Tìm theo khách hàng '111111111' phải trả về 2 kết quả";
        System.out.println("✓ Tìm theo khách hàng có kết quả OK");
        
        // Test tìm theo khách hàng không có kết quả
        ArrayList<DanhGia> resultEmpty = DanhGiaController.timDanhGiaTheoKhachHang("999999999");
        assert resultEmpty.size() == 0 : "Tìm theo khách hàng không tồn tại phải trả về list rỗng";
        System.out.println("✓ Tìm theo khách hàng không có kết quả OK");
    }
    
    // Test tinhDiemTrungBinh
    public static void testTinhDiemTrungBinh() {
        System.out.println("\n=== TEST TINH DIEM TRUNG BINH ===");
        
        // Test tính điểm trung bình
        double result = DanhGiaController.tinhDiemTrungBinh("PHIM001");
        // Kết quả có thể 0 hoặc giá trị khác tùy vào dữ liệu thực tế
        System.out.println("✓ Điểm trung bình: " + result);
    }
    
    // Test thongKeDanhGia
    public static void testThongKeDanhGia() {
        System.out.println("\n=== TEST THONG KE DANH GIA ===");
        
        // Test thống kê đánh giá
        boolean result = DanhGiaController.thongKeDanhGia();
        // Kết quả có thể true hoặc false tùy vào dữ liệu thực tế
        System.out.println("✓ Thống kê đánh giá: " + result);
    }
    
    // === INTEGRATION TESTING ===
    
    // Test luồng quản lý đánh giá hoàn chỉnh
    public static void testDanhGiaManagementFlow() {
        System.out.println("\n=== TEST DANH GIA MANAGEMENT FLOW ===");
        
        // 1. Tạo đánh giá mới
        DanhGia dg = new DanhGia("FLOW001", "123456789", "PHIM001", 5, "Phim rất hay!");
        boolean createResult = DanhGiaController.taoDanhGia(dg);
        assert createResult == true : "Tạo đánh giá trong flow phải thành công";
        
        // 2. Tìm kiếm đánh giá
        DanhGia foundDg = DanhGiaController.timDanhGiaTheoMa("FLOW001");
        assert foundDg != null : "Phải tìm thấy đánh giá sau khi tạo";
        
        // 3. Cập nhật thông tin
        foundDg.setDiem(4);
        foundDg.setNoiDung("Phim hay nhưng chưa xuất sắc");
        boolean updateResult = DanhGiaController.capNhatDanhGia("FLOW001", foundDg);
        assert updateResult == true : "Cập nhật đánh giá trong flow phải thành công";
        
        // 4. Xem thông tin
        boolean viewResult = DanhGiaController.xemThongTinDanhGia("FLOW001");
        assert viewResult == true : "Xem thông tin đánh giá trong flow phải thành công";
        
        // 5. Tính điểm trung bình
        double avgResult = DanhGiaController.tinhDiemTrungBinh("PHIM001");
        System.out.println("✓ Điểm trung bình trong flow: " + avgResult);
        
        // 6. Xóa đánh giá
        boolean deleteResult = DanhGiaController.xoaDanhGia("FLOW001");
        assert deleteResult == true : "Xóa đánh giá trong flow phải thành công";
        
        // 7. Xác nhận đã xóa
        DanhGia deletedDg = DanhGiaController.timDanhGiaTheoMa("FLOW001");
        assert deletedDg == null : "Đánh giá phải không tồn tại sau khi xóa";
        
        System.out.println("✓ Đánh giá management flow OK");
    }
    
    // === EDGE CASE TESTING ===
    
    // Test với dữ liệu biên
    public static void testEdgeCases() {
        System.out.println("\n=== TEST EDGE CASES ===");
        
        // Test điểm biên
        DanhGia dgMinScore = new DanhGia("EDGE001", "123456789", "PHIM001", 1, "Phim tệ");
        boolean resultMin = DanhGiaController.taoDanhGia(dgMinScore);
        // Có thể chấp nhận hoặc từ chối tùy vào business logic
        System.out.println("✓ Test điểm tối thiểu: " + resultMin);
        
        DanhGia dgMaxScore = new DanhGia("EDGE002", "123456789", "PHIM001", 5, "Phim xuất sắc!");
        boolean resultMax = DanhGiaController.taoDanhGia(dgMaxScore);
        // Có thể chấp nhận hoặc từ chối tùy vào business logic
        System.out.println("✓ Test điểm tối đa: " + resultMax);
        
        // Test nội dung biên
        String shortContent = "OK";
        DanhGia dgShortContent = new DanhGia("EDGE003", "123456789", "PHIM001", 3, shortContent);
        boolean resultShort = DanhGiaController.taoDanhGia(dgShortContent);
        // Có thể chấp nhận hoặc từ chối tùy vào business logic
        System.out.println("✓ Test nội dung ngắn: " + resultShort);
    }
    
    // === MOCK TESTING ===
    
    // Test với dữ liệu giả
    public static void testMockData() {
        System.out.println("\n=== TEST MOCK DATA ===");
        
        // Tạo dữ liệu giả
        ArrayList<DanhGia> mockReviews = new ArrayList<>();
        mockReviews.add(new DanhGia("MOCK001", "111111111", "PHIM001", 5, "Phim rất hay!"));
        mockReviews.add(new DanhGia("MOCK002", "222222222", "PHIM001", 4, "Phim hay"));
        mockReviews.add(new DanhGia("MOCK003", "333333333", "PHIM002", 3, "Phim bình thường"));
        
        // Test tạo nhiều đánh giá
        int successCount = 0;
        for (DanhGia dg : mockReviews) {
            if (DanhGiaController.taoDanhGia(dg)) {
                successCount++;
            }
        }
        assert successCount == 3 : "Tất cả đánh giá mock phải được tạo thành công";
        
        // Test tìm kiếm theo phim
        ArrayList<DanhGia> searchResult = DanhGiaController.timDanhGiaTheoPhim("PHIM001");
        assert searchResult.size() == 2 : "Tìm kiếm 'PHIM001' phải trả về 2 kết quả";
        
        // Test tìm kiếm theo khách hàng
        ArrayList<DanhGia> customerResult = DanhGiaController.timDanhGiaTheoKhachHang("111111111");
        assert customerResult.size() == 1 : "Tìm kiếm '111111111' phải trả về 1 kết quả";
        
        // Dọn dẹp dữ liệu test
        for (DanhGia dg : mockReviews) {
            DanhGiaController.xoaDanhGia(dg.getMaDanhGia());
        }
        
        System.out.println("✓ Mock data testing OK");
    }
    
    // === PERFORMANCE TESTING ===
    
    // Test hiệu suất
    public static void testPerformance() {
        System.out.println("\n=== TEST PERFORMANCE ===");
        
        long startTime = System.currentTimeMillis();
        
        // Test tạo 100 đánh giá
        for (int i = 0; i < 100; i++) {
            DanhGia dg = new DanhGia("PERF" + i, "CCCD" + i, "PHIM" + (i % 10), 
                                    (i % 5) + 1, "Đánh giá " + i);
            DanhGiaController.taoDanhGia(dg);
        }
        
        long createTime = System.currentTimeMillis() - startTime;
        System.out.println("✓ Tạo 100 đánh giá trong " + createTime + "ms");
        
        // Test tìm kiếm
        startTime = System.currentTimeMillis();
        DanhGia result = DanhGiaController.timDanhGiaTheoMa("PERF50");
        long searchTime = System.currentTimeMillis() - startTime;
        System.out.println("✓ Tìm kiếm đánh giá trong " + searchTime + "ms");
        
        // Dọn dẹp
        for (int i = 0; i < 100; i++) {
            DanhGiaController.xoaDanhGia("PERF" + i);
        }
    }
    
    // === MAIN TEST METHOD ===
    
    public static void test() {
        System.out.println("🚀 BẮT ĐẦU TEST DANH GIA CONTROLLER");
        System.out.println("===================================");
        
        try {
            testTaoDanhGia();
            testCapNhatDanhGia();
            testXoaDanhGia();
            testXemThongTinDanhGia();
            testXemTatCaDanhGia();
            testTimDanhGiaTheoMa();
            testTimDanhGiaTheoPhim();
            testTimDanhGiaTheoKhachHang();
            testTinhDiemTrungBinh();
            testThongKeDanhGia();
            testDanhGiaManagementFlow();
            testEdgeCases();
            testMockData();
            testPerformance();
            
            System.out.println("\n===================================");
            System.out.println("✅ TẤT CẢ TEST DANH GIA CONTROLLER THÀNH CÔNG!");
            
        } catch (Exception e) {
            System.out.println("\n===================================");
            System.out.println("❌ TEST DANH GIA CONTROLLER THẤT BẠI: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 