import java.util.ArrayList;

public class KhachHangController {
    // === PHƯƠNG THỨC CƠ BẢN CHO ADMIN ===
    
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

    // Xem tất cả khách hàng (cho Admin)
    public static void xemTatCaKhachHang() {
        KhachHang.Read();
    }

    // === PHƯƠNG THỨC TÌM KIẾM ===
    
    // Tìm kiếm khách hàng theo CCCD
    public static KhachHang timKhachHangTheoCCCD(String CCCD) {
        return KhachHang.getKhachHangByCCCD(CCCD);
    }

    // Tìm kiếm khách hàng theo tên
    public static ArrayList<KhachHang> timKhachHangTheoTen(String ten) {
        return KhachHang.timKiemTheoTen(ten);
    }

    // Tìm kiếm khách hàng theo giới tính
    public static ArrayList<KhachHang> timKhachHangTheoGioiTinh(String gioiTinh) {
        return KhachHang.timKiemTheoGioiTinh(gioiTinh);
    }

    // === PHƯƠNG THỨC THỐNG KÊ ===
    
    // Xem thống kê khách hàng (cho Admin)
    public static void xemThongKeKhachHang() {
        KhachHang.xemThongKe();
    }

    // Tính tổng tiền khách hàng đã sử dụng
    public static double tinhTongTienKhachHang(String CCCD) {
        return KhachHang.tinhTongTienKhachHang(CCCD);
    }

    // === PHƯƠNG THỨC QUẢN LÝ VÉ ===
    
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
                    System.out.println("---");
                }
            }
        } else {
            System.out.println("Không tìm thấy khách hàng với CCCD: " + CCCD);
        }
    }

    // Thêm vé vào lịch sử khách hàng
    public static void themVeChoKhachHang(String CCCD, Ve ve) {
        KhachHang kh = KhachHang.getKhachHangByCCCD(CCCD);
        if (kh != null) {
            kh.themVe(ve);
            System.out.println("Đã thêm vé vào lịch sử khách hàng.");
        } else {
            System.out.println("Không tìm thấy khách hàng với CCCD: " + CCCD);
        }
    }

    // === PHƯƠNG THỨC BÁO CÁO ===
    
    // Báo cáo khách hàng VIP (có nhiều vé)
    public static void baoCaoKhachHangVIP() {
        ArrayList<KhachHang> danhSach = KhachHang.Read();
        System.out.println("=== BÁO CÁO KHÁCH HÀNG VIP ===");
        
        int count = 0;
        for (KhachHang kh : danhSach) {
            if (kh.getLichSuDatVe() != null && kh.getLichSuDatVe().size() >= 5) {
                count++;
                System.out.println("Khách hàng VIP #" + count + ":");
                kh.hienThiThongTin();
                System.out.println("Tổng tiền đã sử dụng: " + tinhTongTienKhachHang(kh.getCCCD()) + " VNĐ");
                System.out.println("---");
            }
        }
        
        if (count == 0) {
            System.out.println("Chưa có khách hàng VIP nào.");
        } else {
            System.out.println("Tổng số khách hàng VIP: " + count);
        }
    }

    // Báo cáo khách hàng mới (chưa đặt vé)
    public static void baoCaoKhachHangMoi() {
        ArrayList<KhachHang> danhSach = KhachHang.Read();
        System.out.println("=== BÁO CÁO KHÁCH HÀNG MỚI ===");
        
        int count = 0;
        for (KhachHang kh : danhSach) {
            if (kh.getLichSuDatVe() == null || kh.getLichSuDatVe().isEmpty()) {
                count++;
                System.out.println("Khách hàng mới #" + count + ":");
                kh.hienThiThongTin();
                System.out.println("---");
            }
        }
        
        if (count == 0) {
            System.out.println("Tất cả khách hàng đều đã đặt vé.");
        } else {
            System.out.println("Tổng số khách hàng mới: " + count);
        }
    }

    // === PHƯƠNG THỨC GỬI FEEDBACK ===
    
    // Gửi feedback (cho Admin)
    public static void guiFeedback(DanhGia fb) {
        DanhGia.Create(fb);
    }
} 