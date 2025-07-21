import java.util.ArrayList;

public class TaiKhoanController {
    public static void themTaiKhoan(TaiKhoan tk) {
        TaiKhoan.Create(tk);
    }

    public static void xemThongTinTaiKhoan(String tenDangNhap) {
        TaiKhoan.Read(tenDangNhap);
    }

    public static ArrayList<TaiKhoan> layTatCaTaiKhoan() {
        return TaiKhoan.Read();
    }

    public static void capNhatTaiKhoan(String tenDangNhap, TaiKhoan tkMoi) {
        TaiKhoan.Update(tenDangNhap, tkMoi);
    }

    public static void xoaTaiKhoan(String tenDangNhap) {
        TaiKhoan.Delete(tenDangNhap);
    }
} 