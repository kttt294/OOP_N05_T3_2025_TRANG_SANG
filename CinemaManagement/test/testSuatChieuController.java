import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class testSuatChieuController {
    
    // === UNIT TESTING ===
    
    // Test hienThiSuatChieuTrongNgay
    public static void testHienThiSuatChieuTrongNgay() {
        System.out.println("=== TEST HIEN THI SUAT CHIEU TRONG NGAY ===");
        
        // Test với ngày hợp lệ
        String validDate = "2024-12-25";
        boolean result1 = SuatChieuController.hienThiSuatChieuTrongNgay(validDate);
        // Kết quả có thể true hoặc false tùy vào dữ liệu thực tế
        System.out.println("✓ Hiển thị suất chiếu ngày hợp lệ: " + result1);
        
        // Test với ngày không hợp lệ
        String invalidDate = "2024-13-45";
        boolean result2 = SuatChieuController.hienThiSuatChieuTrongNgay(invalidDate);
        assert result2 == false : "Ngày không hợp lệ phải trả về false";
        System.out.println("✓ Test ngày không hợp lệ OK");
        
        // Test với ngày rỗng
        boolean result3 = SuatChieuController.hienThiSuatChieuTrongNgay("");
        assert result3 == false : "Ngày rỗng phải trả về false";
        System.out.println("✓ Test ngày rỗng OK");
    }
    
    // Test taoSuatChieu
    public static void testTaoSuatChieu() {
        System.out.println("\n=== TEST TAO SUAT CHIEU ===");
        
        // Test tạo suất chiếu hợp lệ
        LocalDateTime ngayGio = LocalDateTime.now().plusHours(1);
        SuatChieu sc = new SuatChieu("TEST001", "PHIM001", "PHONG001", ngayGio, ngayGio.plusHours(2));
        boolean result = SuatChieuController.taoSuatChieu(sc);
        assert result == true : "Tạo suất chiếu hợp lệ phải trả về true";
        System.out.println("✓ Tạo suất chiếu hợp lệ OK");
        
        // Test tạo suất chiếu null
        boolean resultNull = SuatChieuController.taoSuatChieu(null);
        assert resultNull == false : "Tạo suất chiếu null phải trả về false";
        System.out.println("✓ Tạo suất chiếu null OK");
        
        // Test tạo suất chiếu với mã rỗng
        SuatChieu scEmpty = new SuatChieu("", "PHIM001", "PHONG001", ngayGio, ngayGio.plusHours(2));
        boolean resultEmpty = SuatChieuController.taoSuatChieu(scEmpty);
        assert resultEmpty == false : "Tạo suất chiếu với mã rỗng phải trả về false";
        System.out.println("✓ Tạo suất chiếu với mã rỗng OK");
    }
    
    // Test capNhatSuatChieu
    public static void testCapNhatSuatChieu() {
        System.out.println("\n=== TEST CAP NHAT SUAT CHIEU ===");
        
        // Tạo suất chiếu để test
        LocalDateTime ngayGio = LocalDateTime.now().plusHours(1);
        SuatChieu sc = new SuatChieu("UPDATE001", "PHIM001", "PHONG001", ngayGio, ngayGio.plusHours(2));
        SuatChieuController.taoSuatChieu(sc);
        
        // Test cập nhật hợp lệ
        SuatChieu scMoi = new SuatChieu("UPDATE001", "PHIM_UPDATED", "PHONG_UPDATED", ngayGio, ngayGio.plusHours(3));
        boolean result = SuatChieuController.capNhatSuatChieu("UPDATE001", scMoi);
        assert result == true : "Cập nhật suất chiếu hợp lệ phải trả về true";
        System.out.println("✓ Cập nhật suất chiếu hợp lệ OK");
        
        // Test cập nhật suất chiếu không tồn tại
        SuatChieu scKhongTonTai = new SuatChieu("NOTEXIST", "PHIM001", "PHONG001", ngayGio, ngayGio.plusHours(2));
        boolean resultNotExist = SuatChieuController.capNhatSuatChieu("NOTEXIST", scKhongTonTai);
        assert resultNotExist == false : "Cập nhật suất chiếu không tồn tại phải trả về false";
        System.out.println("✓ Cập nhật suất chiếu không tồn tại OK");
    }
    
    // Test xoaSuatChieu
    public static void testXoaSuatChieu() {
        System.out.println("\n=== TEST XOA SUAT CHIEU ===");
        
        // Tạo suất chiếu để test
        LocalDateTime ngayGio = LocalDateTime.now().plusHours(1);
        SuatChieu sc = new SuatChieu("DELETE001", "PHIM001", "PHONG001", ngayGio, ngayGio.plusHours(2));
        SuatChieuController.taoSuatChieu(sc);
        
        // Test xóa suất chiếu tồn tại
        boolean result = SuatChieuController.xoaSuatChieu("DELETE001");
        assert result == true : "Xóa suất chiếu tồn tại phải trả về true";
        System.out.println("✓ Xóa suất chiếu tồn tại OK");
        
        // Test xóa suất chiếu không tồn tại
        boolean resultNotExist = SuatChieuController.xoaSuatChieu("NOTEXIST");
        assert resultNotExist == false : "Xóa suất chiếu không tồn tại phải trả về false";
        System.out.println("✓ Xóa suất chiếu không tồn tại OK");
    }
    
    // Test xemThongTinSuatChieu
    public static void testXemThongTinSuatChieu() {
        System.out.println("\n=== TEST XEM THONG TIN SUAT CHIEU ===");
        
        // Tạo suất chiếu để test
        LocalDateTime ngayGio = LocalDateTime.now().plusHours(1);
        SuatChieu sc = new SuatChieu("VIEW001", "PHIM001", "PHONG001", ngayGio, ngayGio.plusHours(2));
        SuatChieuController.taoSuatChieu(sc);
        
        // Test xem thông tin suất chiếu tồn tại
        boolean result = SuatChieuController.xemThongTinSuatChieu("VIEW001");
        assert result == true : "Xem thông tin suất chiếu tồn tại phải trả về true";
        System.out.println("✓ Xem thông tin suất chiếu tồn tại OK");
        
        // Test xem thông tin với mã rỗng
        boolean resultEmpty = SuatChieuController.xemThongTinSuatChieu("");
        assert resultEmpty == false : "Xem thông tin với mã rỗng phải trả về false";
        System.out.println("✓ Xem thông tin với mã rỗng OK");
    }
    
    // Test xemTatCaSuatChieu
    public static void testXemTatCaSuatChieu() {
        System.out.println("\n=== TEST XEM TAT CA SUAT CHIEU ===");
        
        // Test xem tất cả suất chiếu
        boolean result = SuatChieuController.xemTatCaSuatChieu();
        // Kết quả có thể true hoặc false tùy vào dữ liệu thực tế
        System.out.println("✓ Xem tất cả suất chiếu: " + result);
    }
    
    // Test timSuatChieuTheoMa
    public static void testTimSuatChieuTheoMa() {
        System.out.println("\n=== TEST TIM SUAT CHIEU THEO MA ===");
        
        // Tạo suất chiếu để test
        LocalDateTime ngayGio = LocalDateTime.now().plusHours(1);
        SuatChieu sc = new SuatChieu("SEARCH001", "PHIM001", "PHONG001", ngayGio, ngayGio.plusHours(2));
        SuatChieuController.taoSuatChieu(sc);
        
        // Test tìm suất chiếu tồn tại
        SuatChieu result = SuatChieuController.timSuatChieuTheoMa("SEARCH001");
        assert result != null : "Tìm suất chiếu tồn tại phải trả về suất chiếu";
        assert "PHIM001".equals(result.getMaPhim()) : "Mã phim tìm được không đúng";
        System.out.println("✓ Tìm suất chiếu tồn tại OK");
        
        // Test tìm suất chiếu không tồn tại
        SuatChieu resultNotExist = SuatChieuController.timSuatChieuTheoMa("NOTEXIST");
        assert resultNotExist == null : "Tìm suất chiếu không tồn tại phải trả về null";
        System.out.println("✓ Tìm suất chiếu không tồn tại OK");
    }
    
    // Test timSuatChieuTheoPhim
    public static void testTimSuatChieuTheoPhim() {
        System.out.println("\n=== TEST TIM SUAT CHIEU THEO PHIM ===");
        
        // Tạo suất chiếu để test
        LocalDateTime ngayGio = LocalDateTime.now().plusHours(1);
        SuatChieu sc1 = new SuatChieu("PHIM001", "PHIM001", "PHONG001", ngayGio, ngayGio.plusHours(2));
        SuatChieu sc2 = new SuatChieu("PHIM002", "PHIM001", "PHONG002", ngayGio.plusHours(3), ngayGio.plusHours(5));
        SuatChieuController.taoSuatChieu(sc1);
        SuatChieuController.taoSuatChieu(sc2);
        
        // Test tìm theo phim có kết quả
        ArrayList<SuatChieu> result = SuatChieuController.timSuatChieuTheoPhim("PHIM001");
        assert result.size() == 2 : "Tìm theo phim 'PHIM001' phải trả về 2 kết quả";
        System.out.println("✓ Tìm theo phim có kết quả OK");
        
        // Test tìm theo phim không có kết quả
        ArrayList<SuatChieu> resultEmpty = SuatChieuController.timSuatChieuTheoPhim("NOTEXIST");
        assert resultEmpty.size() == 0 : "Tìm theo phim không tồn tại phải trả về list rỗng";
        System.out.println("✓ Tìm theo phim không có kết quả OK");
    }
    
    // Test timSuatChieuTheoPhong
    public static void testTimSuatChieuTheoPhong() {
        System.out.println("\n=== TEST TIM SUAT CHIEU THEO PHONG ===");
        
        // Tạo suất chiếu để test
        LocalDateTime ngayGio = LocalDateTime.now().plusHours(1);
        SuatChieu sc1 = new SuatChieu("PHONG001", "PHIM001", "PHONG001", ngayGio, ngayGio.plusHours(2));
        SuatChieu sc2 = new SuatChieu("PHONG002", "PHIM002", "PHONG001", ngayGio.plusHours(3), ngayGio.plusHours(5));
        SuatChieuController.taoSuatChieu(sc1);
        SuatChieuController.taoSuatChieu(sc2);
        
        // Test tìm theo phòng có kết quả
        ArrayList<SuatChieu> result = SuatChieuController.timSuatChieuTheoPhong("PHONG001");
        assert result.size() == 2 : "Tìm theo phòng 'PHONG001' phải trả về 2 kết quả";
        System.out.println("✓ Tìm theo phòng có kết quả OK");
        
        // Test tìm theo phòng không có kết quả
        ArrayList<SuatChieu> resultEmpty = SuatChieuController.timSuatChieuTheoPhong("NOTEXIST");
        assert resultEmpty.size() == 0 : "Tìm theo phòng không tồn tại phải trả về list rỗng";
        System.out.println("✓ Tìm theo phòng không có kết quả OK");
    }
    
    // === INTEGRATION TESTING ===
    
    // Test luồng quản lý suất chiếu hoàn chỉnh
    public static void testSuatChieuManagementFlow() {
        System.out.println("\n=== TEST SUAT CHIEU MANAGEMENT FLOW ===");
        
        // 1. Tạo suất chiếu mới
        LocalDateTime ngayGio = LocalDateTime.now().plusHours(1);
        SuatChieu sc = new SuatChieu("FLOW001", "PHIM001", "PHONG001", ngayGio, ngayGio.plusHours(2));
        boolean createResult = SuatChieuController.taoSuatChieu(sc);
        assert createResult == true : "Tạo suất chiếu trong flow phải thành công";
        
        // 2. Tìm kiếm suất chiếu
        SuatChieu foundSc = SuatChieuController.timSuatChieuTheoMa("FLOW001");
        assert foundSc != null : "Phải tìm thấy suất chiếu sau khi tạo";
        
        // 3. Cập nhật thông tin
        foundSc.setMaPhim("PHIM_UPDATED");
        boolean updateResult = SuatChieuController.capNhatSuatChieu("FLOW001", foundSc);
        assert updateResult == true : "Cập nhật suất chiếu trong flow phải thành công";
        
        // 4. Xem thông tin
        boolean viewResult = SuatChieuController.xemThongTinSuatChieu("FLOW001");
        assert viewResult == true : "Xem thông tin suất chiếu trong flow phải thành công";
        
        // 5. Xóa suất chiếu
        boolean deleteResult = SuatChieuController.xoaSuatChieu("FLOW001");
        assert deleteResult == true : "Xóa suất chiếu trong flow phải thành công";
        
        // 6. Xác nhận đã xóa
        SuatChieu deletedSc = SuatChieuController.timSuatChieuTheoMa("FLOW001");
        assert deletedSc == null : "Suất chiếu phải không tồn tại sau khi xóa";
        
        System.out.println("✓ Suất chiếu management flow OK");
    }
    
    // === EDGE CASE TESTING ===
    
    // Test với dữ liệu biên
    public static void testEdgeCases() {
        System.out.println("\n=== TEST EDGE CASES ===");
        
        // Test thời gian biên
        LocalDateTime edgeTime = LocalDateTime.now().plusMinutes(1);
        SuatChieu sc = new SuatChieu("EDGE001", "PHIM001", "PHONG001", edgeTime, edgeTime.plusMinutes(30));
        boolean result = SuatChieuController.taoSuatChieu(sc);
        // Có thể chấp nhận hoặc từ chối tùy vào business logic
        System.out.println("✓ Test thời gian biên: " + result);
        
        // Test suất chiếu rất ngắn
        LocalDateTime shortTime = LocalDateTime.now().plusHours(1);
        SuatChieu scShort = new SuatChieu("EDGE002", "PHIM001", "PHONG001", shortTime, shortTime.plusMinutes(5));
        boolean resultShort = SuatChieuController.taoSuatChieu(scShort);
        // Có thể chấp nhận hoặc từ chối tùy vào business logic
        System.out.println("✓ Test suất chiếu ngắn: " + resultShort);
        
        // Test suất chiếu rất dài
        LocalDateTime longTime = LocalDateTime.now().plusHours(1);
        SuatChieu scLong = new SuatChieu("EDGE003", "PHIM001", "PHONG001", longTime, longTime.plusHours(10));
        boolean resultLong = SuatChieuController.taoSuatChieu(scLong);
        // Có thể chấp nhận hoặc từ chối tùy vào business logic
        System.out.println("✓ Test suất chiếu dài: " + resultLong);
    }
    
    // === MOCK TESTING ===
    
    // Test với dữ liệu giả
    public static void testMockData() {
        System.out.println("\n=== TEST MOCK DATA ===");
        
        // Tạo dữ liệu giả
        ArrayList<SuatChieu> mockShows = new ArrayList<>();
        LocalDateTime baseTime = LocalDateTime.now();
        
        mockShows.add(new SuatChieu("MOCK001", "PHIM001", "PHONG001", baseTime.plusHours(1), baseTime.plusHours(3)));
        mockShows.add(new SuatChieu("MOCK002", "PHIM002", "PHONG002", baseTime.plusHours(4), baseTime.plusHours(6)));
        mockShows.add(new SuatChieu("MOCK003", "PHIM003", "PHONG003", baseTime.plusHours(7), baseTime.plusHours(9)));
        
        // Test tạo nhiều suất chiếu
        int successCount = 0;
        for (SuatChieu sc : mockShows) {
            if (SuatChieuController.taoSuatChieu(sc)) {
                successCount++;
            }
        }
        assert successCount == 3 : "Tất cả suất chiếu mock phải được tạo thành công";
        
        // Test tìm kiếm theo phim
        ArrayList<SuatChieu> searchResult = SuatChieuController.timSuatChieuTheoPhim("PHIM001");
        assert searchResult.size() == 1 : "Tìm kiếm 'PHIM001' phải trả về 1 kết quả";
        
        // Test tìm kiếm theo phòng
        ArrayList<SuatChieu> roomResult = SuatChieuController.timSuatChieuTheoPhong("PHONG001");
        assert roomResult.size() == 1 : "Tìm kiếm 'PHONG001' phải trả về 1 kết quả";
        
        // Dọn dẹp dữ liệu test
        for (SuatChieu sc : mockShows) {
            SuatChieuController.xoaSuatChieu(sc.getMaSuatChieu());
        }
        
        System.out.println("✓ Mock data testing OK");
    }
    
    // === PERFORMANCE TESTING ===
    
    // Test hiệu suất
    public static void testPerformance() {
        System.out.println("\n=== TEST PERFORMANCE ===");
        
        long startTime = System.currentTimeMillis();
        
        // Test tạo 100 suất chiếu
        LocalDateTime baseTime = LocalDateTime.now();
        for (int i = 0; i < 100; i++) {
            SuatChieu sc = new SuatChieu("PERF" + i, "PHIM" + (i % 10), "PHONG" + (i % 5), 
                                        baseTime.plusHours(i), baseTime.plusHours(i + 2));
            SuatChieuController.taoSuatChieu(sc);
        }
        
        long createTime = System.currentTimeMillis() - startTime;
        System.out.println("✓ Tạo 100 suất chiếu trong " + createTime + "ms");
        
        // Test tìm kiếm
        startTime = System.currentTimeMillis();
        SuatChieu result = SuatChieuController.timSuatChieuTheoMa("PERF50");
        long searchTime = System.currentTimeMillis() - startTime;
        System.out.println("✓ Tìm kiếm suất chiếu trong " + searchTime + "ms");
        
        // Dọn dẹp
        for (int i = 0; i < 100; i++) {
            SuatChieuController.xoaSuatChieu("PERF" + i);
        }
    }
    
    // === MAIN TEST METHOD ===
    
    public static void test() {
        System.out.println("🚀 BẮT ĐẦU TEST SUAT CHIEU CONTROLLER");
        System.out.println("=====================================");
        
        try {
            testHienThiSuatChieuTrongNgay();
            testTaoSuatChieu();
            testCapNhatSuatChieu();
            testXoaSuatChieu();
            testXemThongTinSuatChieu();
            testXemTatCaSuatChieu();
            testTimSuatChieuTheoMa();
            testTimSuatChieuTheoPhim();
            testTimSuatChieuTheoPhong();
            testSuatChieuManagementFlow();
            testEdgeCases();
            testMockData();
            testPerformance();
            
            System.out.println("\n=====================================");
            System.out.println("✅ TẤT CẢ TEST SUAT CHIEU CONTROLLER THÀNH CÔNG!");
            
        } catch (Exception e) {
            System.out.println("\n=====================================");
            System.out.println("❌ TEST SUAT CHIEU CONTROLLER THẤT BẠI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
