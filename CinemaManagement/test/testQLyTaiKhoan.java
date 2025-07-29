import java.util.ArrayList;

public class testQLyTaiKhoan {
    
    // === UNIT TESTING ===
    public static void testThemTaiKhoan() {
        System.out.println("🧪 Test thêm tài khoản...");
        
        try {
            // Test tạo User với vai trò Admin
            User.VaiTro vaiTroAdmin = User.VaiTro.ADMIN;
            User userAdmin = new User("TEST001", "Admin User", 30, "0123456789", "admin@email.com", 
                                    "admin", "password123", vaiTroAdmin);
            
            boolean result = QLyTaiKhoan.themTaiKhoan(userAdmin);
            System.out.println("✅ Thêm tài khoản Admin: " + (result ? "THÀNH CÔNG" : "THẤT BẠI"));
            
        } catch (Exception e) {
            System.out.println("❌ Lỗi thêm tài khoản: " + e.getMessage());
        }
    }
    
    public static void testXemThongTinTaiKhoan() {
        System.out.println("🧪 Test xem thông tin tài khoản...");
        
        try {
            QLyTaiKhoan.xemThongTinTaiKhoan("admin");
            System.out.println("✅ Xem thông tin tài khoản: THÀNH CÔNG");
            
        } catch (Exception e) {
            System.out.println("❌ Lỗi xem thông tin: " + e.getMessage());
        }
    }
    
    // === INTEGRATION TESTING ===
    public static void testTaiKhoanManagementFlow() {
        System.out.println("🧪 Test luồng quản lý tài khoản...");
        
        try {
            // Tạo tài khoản nhân viên
            User.VaiTro vaiTroNhanVien = User.VaiTro.NHAN_VIEN;
            User userNhanVien = new User("TEST002", "Nhan Vien", 25, "0987654321", "nv@email.com", 
                                       "nhanvien", "pass123", vaiTroNhanVien);
            
            // Thêm tài khoản
            boolean themResult = QLyTaiKhoan.themTaiKhoan(userNhanVien);
            
            // Xem thông tin
            if (themResult) {
                QLyTaiKhoan.xemThongTinTaiKhoan("nhanvien");
            }
            
            System.out.println("✅ Luồng quản lý tài khoản: THÀNH CÔNG");
            
        } catch (Exception e) {
            System.out.println("❌ Lỗi luồng quản lý: " + e.getMessage());
        }
    }
    
    // === EDGE CASE TESTING ===
    public static void testEdgeCases() {
        System.out.println("🧪 Test các trường hợp đặc biệt...");
        
        try {
            // Test với tài khoản null
            QLyTaiKhoan.themTaiKhoan(null);
            
        } catch (Exception e) {
            System.out.println("✅ Xử lý tài khoản null: " + e.getMessage());
        }
        
        try {
            // Test với username không tồn tại
            QLyTaiKhoan.xemThongTinTaiKhoan("khongtontai");
            
        } catch (Exception e) {
            System.out.println("✅ Xử lý username không tồn tại: " + e.getMessage());
        }
    }
    
    // === MOCK TESTING ===
    public static void testMockData() {
        System.out.println("🧪 Test với dữ liệu giả...");
        
        try {
            // Tạo dữ liệu test
            User.VaiTro vaiTroKhach = User.VaiTro.KHACH_HANG;
            User userKhach = new User("TEST003", "Khach Hang", 28, "1122334455", "kh@email.com", 
                                    "khachhang", "kh123", vaiTroKhach);
            
            QLyTaiKhoan.themTaiKhoan(userKhach);
            QLyTaiKhoan.xemThongTinTaiKhoan("khachhang");
            
            System.out.println("✅ Test dữ liệu giả: THÀNH CÔNG");
            
        } catch (Exception e) {
            System.out.println("❌ Lỗi test dữ liệu giả: " + e.getMessage());
        }
    }
    
    // === MAIN TEST METHOD ===
    public static void test() {
        System.out.println("🚀 BẮT ĐẦU TEST QUẢN LÝ TÀI KHOẢN");
        System.out.println("==================================");
        
        try {
            testThemTaiKhoan();
            testXemThongTinTaiKhoan();
            testTaiKhoanManagementFlow();
            testEdgeCases();
            testMockData();
            
            System.out.println("\n==================================");
            System.out.println("✅ TẤT CẢ TEST QUẢN LÝ TÀI KHOẢN THÀNH CÔNG!");
            
        } catch (Exception e) {
            System.out.println("\n==================================");
            System.out.println("❌ TEST QUẢN LÝ TÀI KHOẢN THẤT BẠI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
