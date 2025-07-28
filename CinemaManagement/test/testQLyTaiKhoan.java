import java.util.Scanner;

public class testQLyTaiKhoan {
    public static void test(){
        Scanner sc = new Scanner(System.in);
        String tenDangNhap = sc.nextLine();
        String matKhau = sc.nextLine();
        String vaiTro = sc.nextLine();
        
        // Tạo User với vai trò tương ứng
        User.VaiTro vaiTroEnum = User.VaiTro.KHACH_HANG;
        if (vaiTro.equalsIgnoreCase("Admin")) {
            vaiTroEnum = User.VaiTro.ADMIN;
        } else if (vaiTro.equalsIgnoreCase("NhanVien")) {
            vaiTroEnum = User.VaiTro.NHAN_VIEN;
        }
        
        // Tạo User tạm thời để test (cần CCCD và thông tin cá nhân)
        User user = new User("TEST001", "Test User", 25, "0123456789", "test@email.com", 
                           tenDangNhap, matKhau, vaiTroEnum) {
            @Override
            public String getLoaiNguoi() {
                return "Test User";
            }
            
            @Override
            public void hienThiThongTin() {
                System.out.println("Test User - " + getTen());
            }
        };

        // tạo tài khoản mới
        QLyTaiKhoan.themTaiKhoan(user);

        // xem tài khoản vừa tạo
        QLyTaiKhoan.xemThongTinTaiKhoan(tenDangNhap);

        sc.close();
    }
}
