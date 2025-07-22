import java.util.Scanner;

public class testQLyTaiKhoan {
    public static void test(){
        Scanner sc = new Scanner(System.in);
        String tenDangNhap = sc.nextLine();
        String matKhau = sc.nextLine();
        String vaiTro = sc.nextLine();
        TaiKhoan tk = new TaiKhoan(tenDangNhap, matKhau, vaiTro);

        // tạo tài khoản mới
        QLyTaiKhoan.themTaiKhoan(tk);

        // xem tài khoản vừa tạo
        QLyTaiKhoan.xemThongTinTaiKhoan(tenDangNhap);

        // cập nhật mật khẩu
        

        sc.close();
    }
}
