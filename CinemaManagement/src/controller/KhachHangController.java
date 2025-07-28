import java.util.ArrayList;

public class KhachHangController {
    // Tạo khách hàng mới (cho Admin)
    public static void taoKhachHang(KhachHang kh) {
        KhachHang.Create(kh);
    }

    // Cập nhật thông tin khách hàng (cho Admin)
    public static void capNhatThongTin(String CCCD, KhachHang khMoi) {
        KhachHang.Update(CCCD, khMoi);
    }

    // Xóa khách hàng (cho Admin)
    public static void xoaKhachHang(String CCCD) {
        KhachHang.Delete(CCCD);
    }

    // Xem thông tin khách hàng (cho Admin)
    public static void xemThongTin(String CCCD) {
        KhachHang.Read(CCCD);
    }

    // Xem lịch sử đặt vé của khách hàng (cho Admin)
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

    // Tính tổng số tiền mà khách hàng đã sử dụng (cho Admin)
    public static void tinhTongTienDaSuDung(String CCCD) {
        int tongTien = 0;
        ArrayList<Ve> ves = Ve.Read();
        for (Ve ve : ves) {
            if (ve.getCCCD() != null && ve.getCCCD().equals(CCCD) && ve.isDaThanhToan()) {
                tongTien += ve.getTongTien();
            }
        }
        System.out.println("Tổng số tiền khách hàng với CCCD " + CCCD + " đã sử dụng là: " + tongTien + " VNĐ");
    }

    // Gửi feedback (cho Admin)
    public static void guiFeedback(DanhGia fb) {
        DanhGia.Create(fb);
    }

    // === CÁC PHƯƠNG THỨC MỚI CHO ADMIN ===
    
    // Admin xem thống kê khách hàng
    public static void xemThongKeKhachHang() {
        ArrayList<KhachHang> danhSach = KhachHang.ReadKhachHang();
        System.out.println("=== THỐNG KÊ KHÁCH HÀNG ===");
        System.out.println("Tổng số khách hàng: " + danhSach.size());
    }
} 