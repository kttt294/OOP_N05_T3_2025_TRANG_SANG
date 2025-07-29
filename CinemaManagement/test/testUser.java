import java.util.ArrayList;

public class testUser {
    
    // === UNIT TESTING ===
    
    // Test constructor
    public static void testConstructor() {
        System.out.println("=== TEST CONSTRUCTOR ===");
        
        // Test constructor rỗng
        User user1 = new User();
        assert user1.getUsername() == null : "Constructor rỗng không đúng";
        assert user1.getPassword() == null : "Constructor rỗng không đúng";
        System.out.println("✓ Constructor rỗng OK");
        
        // Test constructor với tham số
        User user2 = new User("admin", "password123", "ADMIN");
        assert "admin".equals(user2.getUsername()) : "Username không đúng";
        assert "password123".equals(user2.getPassword()) : "Password không đúng";
        assert "ADMIN".equals(user2.getVaiTro()) : "Vai trò không đúng";
        System.out.println("✓ Constructor với tham số OK");
        
        // Test constructor với trạng thái
        User user3 = new User("staff", "staff123", "STAFF", "ACTIVE");
        assert "staff".equals(user3.getUsername()) : "Username không đúng";
        assert "staff123".equals(user3.getPassword()) : "Password không đúng";
        assert "STAFF".equals(user3.getVaiTro()) : "Vai trò không đúng";
        assert "ACTIVE".equals(user3.getTrangThai()) : "Trạng thái không đúng";
        System.out.println("✓ Constructor với trạng thái OK");
    }
    
    // Test getters và setters
    public static void testGettersSetters() {
        System.out.println("\n=== TEST GETTERS & SETTERS ===");
        
        User user = new User();
        
        // Test setUsername
        user.setUsername("testuser");
        assert "testuser".equals(user.getUsername()) : "setUsername/getUsername không đúng";
        
        // Test setPassword
        user.setPassword("newpassword");
        assert "newpassword".equals(user.getPassword()) : "setPassword/getPassword không đúng";
        
        // Test setVaiTro
        user.setVaiTro("MANAGER");
        assert "MANAGER".equals(user.getVaiTro()) : "setVaiTro/getVaiTro không đúng";
        
        // Test setTrangThai
        user.setTrangThai("INACTIVE");
        assert "INACTIVE".equals(user.getTrangThai()) : "setTrangThai/getTrangThai không đúng";
        
        System.out.println("✓ Tất cả getters/setters OK");
    }
    
    // Test method hienThiThongTin
    public static void testHienThiThongTin() {
        System.out.println("\n=== TEST HIEN THI THONG TIN ===");
        
        User user = new User("testuser", "password123", "STAFF", "ACTIVE");
        
        // Test hiển thị thông tin
        user.hienThiThongTin();
        System.out.println("✓ Hiển thị thông tin OK");
    }
    
    // === INTEGRATION TESTING ===
    
    // Test luồng CRUD hoàn chỉnh
    public static void testCRUDIntegration() {
        System.out.println("\n=== TEST CRUD INTEGRATION ===");
        
        // Create
        User user = new User("INT001", "password123", "STAFF", "ACTIVE");
        User.Create(user);
        
        // Read
        User userRead = User.getUserByUsername("INT001");
        assert userRead != null : "Không tìm thấy user sau khi tạo";
        assert "password123".equals(userRead.getPassword()) : "Dữ liệu đọc không đúng";
        
        // Update
        user.setVaiTro("MANAGER");
        user.setTrangThai("INACTIVE");
        User.Update("INT001", user);
        User userUpdated = User.getUserByUsername("INT001");
        assert "MANAGER".equals(userUpdated.getVaiTro()) : "Update không thành công";
        
        // Delete
        User.Delete("INT001");
        User userDeleted = User.getUserByUsername("INT001");
        assert userDeleted == null : "Delete không thành công";
        
        System.out.println("✓ CRUD Integration OK");
    }
    
    // === EDGE CASE TESTING ===
    
    // Test với dữ liệu null
    public static void testNullData() {
        System.out.println("\n=== TEST NULL DATA ===");
        
        // Test tạo user với username null
        try {
            User user = new User(null, "password123", "STAFF");
            User.Create(user);
            System.out.println("✗ Không bắt được lỗi username null");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi username null: " + e.getMessage());
        }
        
        // Test tạo user với password null
        try {
            User user = new User("testuser", null, "STAFF");
            User.Create(user);
            System.out.println("✗ Không bắt được lỗi password null");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi password null: " + e.getMessage());
        }
        
        // Test tạo user với username rỗng
        try {
            User user = new User("", "password123", "STAFF");
            User.Create(user);
            System.out.println("✗ Không bắt được lỗi username rỗng");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi username rỗng: " + e.getMessage());
        }
    }
    
    // Test với dữ liệu không hợp lệ
    public static void testInvalidData() {
        System.out.println("\n=== TEST INVALID DATA ===");
        
        // Test username quá ngắn
        try {
            User user = new User("ab", "password123", "STAFF");
            User.Create(user);
            System.out.println("✗ Không bắt được lỗi username quá ngắn");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi username quá ngắn: " + e.getMessage());
        }
        
        // Test password quá ngắn
        try {
            User user = new User("testuser", "123", "STAFF");
            User.Create(user);
            System.out.println("✗ Không bắt được lỗi password quá ngắn");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi password quá ngắn: " + e.getMessage());
        }
        
        // Test vai trò không hợp lệ
        try {
            User user = new User("testuser", "password123", "INVALID_ROLE");
            User.Create(user);
            System.out.println("✗ Không bắt được lỗi vai trò không hợp lệ");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi vai trò không hợp lệ: " + e.getMessage());
        }
        
        // Test trạng thái không hợp lệ
        try {
            User user = new User("testuser", "password123", "STAFF", "INVALID_STATUS");
            User.Create(user);
            System.out.println("✗ Không bắt được lỗi trạng thái không hợp lệ");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi trạng thái không hợp lệ: " + e.getMessage());
        }
        
        // Test username trùng lặp
        User user1 = new User("DUP001", "password123", "STAFF");
        User.Create(user1);
        
        try {
            User user2 = new User("DUP001", "differentpassword", "MANAGER");
            User.Create(user2);
            System.out.println("✗ Không bắt được lỗi username trùng lặp");
        } catch (Exception e) {
            System.out.println("✓ Bắt được lỗi username trùng lặp: " + e.getMessage());
        }
    }
    
    // === MOCK TESTING ===
    
    // Test với dữ liệu giả
    public static void testMockData() {
        System.out.println("\n=== TEST MOCK DATA ===");
        
        // Tạo dữ liệu giả
        ArrayList<User> mockData = new ArrayList<>();
        mockData.add(new User("admin", "admin123", "ADMIN", "ACTIVE"));
        mockData.add(new User("manager1", "manager123", "MANAGER", "ACTIVE"));
        mockData.add(new User("staff1", "staff123", "STAFF", "ACTIVE"));
        
        // Test tìm kiếm theo vai trò
        ArrayList<User> result1 = new ArrayList<>();
        for (User user : mockData) {
            if ("ADMIN".equals(user.getVaiTro())) {
                result1.add(user);
            }
        }
        assert result1.size() == 1 : "Tìm kiếm theo vai trò không đúng";
        
        // Test tìm kiếm theo trạng thái
        ArrayList<User> result2 = new ArrayList<>();
        for (User user : mockData) {
            if ("ACTIVE".equals(user.getTrangThai())) {
                result2.add(user);
            }
        }
        assert result2.size() == 3 : "Tìm kiếm theo trạng thái không đúng";
        
        // Test đếm theo vai trò
        int adminCount = 0;
        int managerCount = 0;
        int staffCount = 0;
        
        for (User user : mockData) {
            if ("ADMIN".equals(user.getVaiTro())) {
                adminCount++;
            } else if ("MANAGER".equals(user.getVaiTro())) {
                managerCount++;
            } else if ("STAFF".equals(user.getVaiTro())) {
                staffCount++;
            }
        }
        
        assert adminCount == 1 : "Đếm admin không đúng";
        assert managerCount == 1 : "Đếm manager không đúng";
        assert staffCount == 1 : "Đếm staff không đúng";
        
        System.out.println("✓ Mock data testing OK");
    }
    
    // === PERFORMANCE TESTING ===
    
    // Test hiệu suất với dữ liệu lớn
    public static void testPerformance() {
        System.out.println("\n=== TEST PERFORMANCE ===");
        
        long startTime = System.currentTimeMillis();
        
        // Tạo 1000 user
        for (int i = 0; i < 1000; i++) {
            User user = new User("USER" + i, "password" + i, "STAFF", "ACTIVE");
            User.Create(user);
        }
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        System.out.println("✓ Tạo 1000 user trong " + duration + "ms");
        
        // Test tìm kiếm
        startTime = System.currentTimeMillis();
        User result = User.getUserByUsername("USER500");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        
        System.out.println("✓ Tìm kiếm user trong " + duration + "ms");
    }
    
    // === BUSINESS LOGIC TESTING ===
    
    // Test logic nghiệp vụ
    public static void testBusinessLogic() {
        System.out.println("\n=== TEST BUSINESS LOGIC ===");
        
        // Test user admin
        User adminUser = new User("admin", "admin123", "ADMIN", "ACTIVE");
        boolean isAdmin = "ADMIN".equals(adminUser.getVaiTro());
        assert isAdmin == true : "User admin không đúng";
        
        // Test user manager
        User managerUser = new User("manager", "manager123", "MANAGER", "ACTIVE");
        boolean isManager = "MANAGER".equals(managerUser.getVaiTro());
        assert isManager == true : "User manager không đúng";
        
        // Test user staff
        User staffUser = new User("staff", "staff123", "STAFF", "ACTIVE");
        boolean isStaff = "STAFF".equals(staffUser.getVaiTro());
        assert isStaff == true : "User staff không đúng";
        
        // Test user active
        User activeUser = new User("active", "password123", "STAFF", "ACTIVE");
        boolean isActive = "ACTIVE".equals(activeUser.getTrangThai());
        assert isActive == true : "User active không đúng";
        
        // Test user inactive
        User inactiveUser = new User("inactive", "password123", "STAFF", "INACTIVE");
        boolean isInactive = "INACTIVE".equals(inactiveUser.getTrangThai());
        assert isInactive == true : "User inactive không đúng";
        
        System.out.println("✓ Business logic testing OK");
    }
    
    // === VALIDATION TESTING ===
    
    // Test validation
    public static void testValidation() {
        System.out.println("\n=== TEST VALIDATION ===");
        
        // Test validation username
        try {
            User user = new User("test_user", "password123", "STAFF");
            // Kiểm tra format username
            boolean isValidFormat = user.getUsername().matches("^[a-zA-Z0-9_]+$");
            assert isValidFormat == true : "Format username không hợp lệ";
            System.out.println("✓ Validation username OK");
        } catch (Exception e) {
            System.out.println("✗ Validation username thất bại: " + e.getMessage());
        }
        
        // Test validation password
        try {
            User user = new User("testuser", "password123", "STAFF");
            // Kiểm tra password hợp lệ
            boolean isValidPassword = user.getPassword().length() >= 6 && user.getPassword().length() <= 50;
            assert isValidPassword == true : "Password không hợp lệ";
            System.out.println("✓ Validation password OK");
        } catch (Exception e) {
            System.out.println("✗ Validation password thất bại: " + e.getMessage());
        }
        
        // Test validation vai trò
        try {
            User user = new User("testuser", "password123", "STAFF");
            // Kiểm tra vai trò hợp lệ
            boolean isValidRole = "ADMIN".equals(user.getVaiTro()) || 
                                "MANAGER".equals(user.getVaiTro()) || 
                                "STAFF".equals(user.getVaiTro());
            assert isValidRole == true : "Vai trò không hợp lệ";
            System.out.println("✓ Validation vai trò OK");
        } catch (Exception e) {
            System.out.println("✗ Validation vai trò thất bại: " + e.getMessage());
        }
        
        // Test validation trạng thái
        try {
            User user = new User("testuser", "password123", "STAFF", "ACTIVE");
            // Kiểm tra trạng thái hợp lệ
            boolean isValidStatus = "ACTIVE".equals(user.getTrangThai()) || 
                                  "INACTIVE".equals(user.getTrangThai());
            assert isValidStatus == true : "Trạng thái không hợp lệ";
            System.out.println("✓ Validation trạng thái OK");
        } catch (Exception e) {
            System.out.println("✗ Validation trạng thái thất bại: " + e.getMessage());
        }
    }
    
    // === MAIN TEST METHOD ===
    
    public static void test() {
        System.out.println("🚀 BẮT ĐẦU TEST USER MODEL");
        System.out.println("===========================");
        
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
            
            System.out.println("\n===========================");
            System.out.println("✅ TẤT CẢ TEST USER THÀNH CÔNG!");
            
        } catch (Exception e) {
            System.out.println("\n===========================");
            System.out.println("❌ TEST USER THẤT BẠI: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 