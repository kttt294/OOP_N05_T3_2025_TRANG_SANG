public class QLyTaiKhoan {
    // tạo tài khoản mới
    public static void themTaiKhoan(User user) {
        User.Create(user);
    }

    // xem thông tin tài khoản với tên đăng nhập cụ thể
    public static void xemThongTinTaiKhoan(String tenDangNhap) {
        User user = User.getNguoiByTenDangNhap(tenDangNhap);
        if (user != null) {
            user.hienThiThongTin();
        } else {
            System.out.println("Không tìm thấy tài khoản với tên đăng nhập: " + tenDangNhap);
        }
    }

    // cập nhật tài khoản
    public static void capNhatMatKhau(String tenDangNhap, User userMoi) {
        User user = User.getNguoiByTenDangNhap(tenDangNhap);
        if (user != null) {
            User.Update(user.getCCCD(), userMoi);
        }
    }

    // xóa tài khoản
    public static void xoaTaiKhoan(String tenDangNhap) {
        User user = User.getNguoiByTenDangNhap(tenDangNhap);
        if (user != null) {
            User.Delete(user.getCCCD());
        }
    }
}