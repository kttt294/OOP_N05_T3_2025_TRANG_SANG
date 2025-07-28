import java.util.ArrayList;

public class NhanVienController {
    public static void themNhanVien(NhanVien nv) {
        NhanVien.Create(nv);
    }

    public static void xemThongTinNhanVien(String CCCD) {
        NhanVien.Read(CCCD);
    }

    public static ArrayList<NhanVien> layTatCaNhanVien() {
        return NhanVien.ReadNhanVien();
    }

    public static void capNhatNhanVien(String CCCD, NhanVien nvMoi) {
        NhanVien.Update(CCCD, nvMoi);
    }

    public static void xoaNhanVien(String CCCD) {
        NhanVien.Delete(CCCD);
    }

    // === CÁC PHƯƠNG THỨC MỚI CHO ADMIN ===
    
    // Admin xem thống kê nhân viên
    public static void xemThongKeNhanVien() {
        ArrayList<NhanVien> danhSach = NhanVien.ReadNhanVien();
        System.out.println("=== THỐNG KÊ NHÂN VIÊN ===");
        System.out.println("Tổng số nhân viên: " + danhSach.size());
        
        // Thống kê theo chức vụ
        int soNhanVienBanHang = 0;
        int soQuanLy = 0;
        int soKhac = 0;
        
        for (NhanVien nv : danhSach) {
            if (nv.getChucVu().equalsIgnoreCase("Nhân viên bán hàng")) {
                soNhanVienBanHang++;
            } else if (nv.getChucVu().equalsIgnoreCase("Quản lý")) {
                soQuanLy++;
            } else {
                soKhac++;
            }
        }
        
        System.out.println("Nhân viên bán hàng: " + soNhanVienBanHang);
        System.out.println("Quản lý: " + soQuanLy);
        System.out.println("Khác: " + soKhac);
    }
} 