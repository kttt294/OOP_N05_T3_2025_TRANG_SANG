import java.util.ArrayList;

public class KhachHangController {
    // Đăng ký khách hàng mới
    public static void dangKy(KhachHang kh, TaiKhoan tk) {
        KhachHang.Create(kh);
        TaiKhoan.Create(tk);
    }

    // Đăng nhập (trả về true nếu thành công)
    public static boolean dangNhap(String tenDangNhap, String matKhau) {
        TaiKhoan tk = TaiKhoan.dangNhap(tenDangNhap, matKhau);
        return tk != null && tk.isKhachHang();
    }

    // Cập nhật thông tin khách hàng
    public static void capNhatThongTin(String CCCD, KhachHang khMoi) {
        KhachHang.Update(CCCD, khMoi);
    }

    // Xóa khách hàng
    public static void xoaKhachHang(String CCCD) {
        KhachHang.Delete(CCCD);
    }

    // Xem thông tin khách hàng
    public static void xemThongTin(String CCCD) {
        KhachHang.Read(CCCD);
    }

    // Xem lịch sử đặt vé của khách hàng
    public static void xemLichSuDatVe(String CCCD) {
        KhachHang kh = KhachHang.getKhachHangByCCCD(CCCD);
        if (kh != null) {
            ArrayList<Ve> lichSu = kh.getLichSuDatVe();
            if (lichSu == null || lichSu.isEmpty()) {
                System.out.println("Khách hàng chưa đặt vé nào.");
            } else {
                System.out.println("Lịch sử đặt vé của khách hàng " + kh.getTen() + ":");
                for (Ve ve : lichSu) {
                    ve.hienThiThongTin();
                }
            }
        } else {
            System.out.println("Không tìm thấy khách hàng với CCCD: " + CCCD);
        }
    }

    // Tính tổng số tiền mà khách hàng đã sử dụng
    public static void tinhTongTienDaSuDung(String CCCD) {
        int tongTien = 0;
        ArrayList<HoaDon> hoaDons = HoaDon.Read();
        for (HoaDon hd : hoaDons) {
            KhachHang kh = hd.getKhachHang();
            if (kh != null && kh.getCCCD().equals(CCCD)) {
                tongTien += hd.getTongTien();
            }
        }
        System.out.println("Tổng số tiền khách hàng với CCCD " + CCCD + " đã sử dụng là: " + tongTien + " VNĐ");
    }

    // Gửi feedback
    public static void guiFeedback(Feedback fb) {
        Feedback.Create(fb);
    }
} 