public class QLyTaiKhoan {
    // tạo tài khoản mới
    public static void themTaiKhoan(TaiKhoan tk) {
        TaiKhoan.Create(tk);
    }

    // xem thông tin tài khoản với tên đăng nhập cụ thể
    public static void xemThongTinTaiKhoan(String tenDangNhap) {
        TaiKhoan.Read(tenDangNhap);
    }

    // cập nhật tài khoản
    public static void capNhatMatKhau(String tenDangNhap, TaiKhoan tkMoi) {
        TaiKhoan.Update(tenDangNhap, tkMoi);
    }

    // xóa tài khoản
    public static void xoaTaiKhoan(String tenDangNhap) {
        TaiKhoan.Delete(tenDangNhap);
    }
}