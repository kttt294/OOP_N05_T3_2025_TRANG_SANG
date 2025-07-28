import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class testDatVe {
    
    public static void inputDatVe(Scanner sc) {
        System.out.println("\n=== ĐẶT VÉ ===");
        
        // Nhập thông tin khách hàng
        System.out.print("CCCD khách hàng: ");
        String CCCD = sc.nextLine().trim();
        if (CCCD.isEmpty()) {
            System.out.println("CCCD không được để trống.");
            return;
        }
        
        // Kiểm tra khách hàng có tồn tại không
        KhachHang kh = KhachHangController.timKhachHangTheoCCCD(CCCD);
        if (kh == null) {
            System.out.println("Không tìm thấy khách hàng với CCCD: " + CCCD);
            return;
        }
        
        // Nhập mã suất chiếu
        System.out.print("Mã suất chiếu: ");
        String maSuatChieu = sc.nextLine().trim();
        if (maSuatChieu.isEmpty()) {
            System.out.println("Mã suất chiếu không được để trống.");
            return;
        }
        
        // Tìm suất chiếu
        SuatChieu suatChieu = SuatChieu.getSuatChieuById(maSuatChieu);
        if (suatChieu == null) {
            System.out.println("Không tìm thấy suất chiếu với mã: " + maSuatChieu);
            return;
        }
        
        // Nhập mã vé
        System.out.print("Mã vé: ");
        String maVe = sc.nextLine().trim();
        if (maVe.isEmpty()) {
            System.out.println("Mã vé không được để trống.");
            return;
        }
        
        // Nhập mã ghế
        System.out.print("Mã ghế: ");
        String maGhe = sc.nextLine().trim();
        if (maGhe.isEmpty()) {
            System.out.println("Mã ghế không được để trống.");
            return;
        }
        
        // Nhập giá vé
        System.out.print("Giá vé: ");
        String giaVeInput = sc.nextLine().trim();
        if (giaVeInput.isEmpty()) {
            System.out.println("Giá vé không được để trống.");
            return;
        }
        
        try {
            double giaVe = Double.parseDouble(giaVeInput);
            
            // Tạo đối tượng DatVe
            DatVe datVe = new DatVe(kh, suatChieu, maVe, maGhe, (int)giaVe);
            boolean ketQua = datVe.datVe();
            
            if (ketQua) {
                System.out.println("✓ Đặt vé thành công!");
                
                // Thêm vé vào lịch sử khách hàng
                Ve ve = Ve.getVeById(maVe);
                if (ve != null) {
                    KhachHangController.themVeChoKhachHang(CCCD, ve);
                    System.out.println("✓ Đã thêm vé vào lịch sử khách hàng");
                }
            } else {
                System.out.println("✗ Đặt vé thất bại!");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Giá vé phải là số.");
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
    
    public static void inputXemLichSuDatVe(Scanner sc) {
        System.out.println("\n=== XEM LỊCH SỬ ĐẶT VÉ ===");
        
        System.out.print("Nhập CCCD khách hàng: ");
        String CCCD = sc.nextLine().trim();
        if (CCCD.isEmpty()) {
            System.out.println("CCCD không được để trống.");
            return;
        }
        
        KhachHangController.xemLichSuDatVe(CCCD);
    }
    
    public static void inputTinhTongTien(Scanner sc) {
        System.out.println("\n=== TÍNH TỔNG TIỀN KHÁCH HÀNG ===");
        
        System.out.print("Nhập CCCD khách hàng: ");
        String CCCD = sc.nextLine().trim();
        if (CCCD.isEmpty()) {
            System.out.println("CCCD không được để trống.");
            return;
        }
        
        double tongTien = KhachHangController.tinhTongTienKhachHang(CCCD);
        System.out.println("✓ Tổng tiền khách hàng đã sử dụng: " + tongTien + " VNĐ");
    }
    
    public static void inputTimKiemKhachHang(Scanner sc) {
        System.out.println("\n=== TÌM KIẾM KHÁCH HÀNG ===");
        System.out.println("1. Tìm theo tên");
        System.out.println("2. Tìm theo giới tính");
        System.out.println("3. Tìm theo CCCD");
        System.out.print("Chọn loại tìm kiếm (1-3): ");
        
        String luaChon = sc.nextLine().trim();
        
        switch (luaChon) {
            case "1":
                System.out.print("Nhập tên cần tìm: ");
                String ten = sc.nextLine().trim();
                if (!ten.isEmpty()) {
                    ArrayList<KhachHang> ketQua = KhachHangController.timKhachHangTheoTen(ten);
                    System.out.println("✓ Tìm thấy " + ketQua.size() + " khách hàng có tên chứa '" + ten + "'");
                    for (KhachHang kh : ketQua) {
                        kh.hienThiThongTin();
                        System.out.println("---");
                    }
                } else {
                    System.out.println("Tên không được để trống.");
                }
                break;
                
            case "2":
                System.out.print("Nhập giới tính (Nam/Nu): ");
                String gioiTinh = sc.nextLine().trim();
                if (!gioiTinh.isEmpty()) {
                    ArrayList<KhachHang> ketQua = KhachHangController.timKhachHangTheoGioiTinh(gioiTinh);
                    System.out.println("✓ Tìm thấy " + ketQua.size() + " khách hàng " + gioiTinh);
                    for (KhachHang kh : ketQua) {
                        kh.hienThiThongTin();
                        System.out.println("---");
                    }
                } else {
                    System.out.println("Giới tính không được để trống.");
                }
                break;
                
            case "3":
                System.out.print("Nhập CCCD: ");
                String CCCD = sc.nextLine().trim();
                if (!CCCD.isEmpty()) {
                    KhachHang kh = KhachHangController.timKhachHangTheoCCCD(CCCD);
                    if (kh != null) {
                        System.out.println("✓ Tìm thấy khách hàng:");
                        kh.hienThiThongTin();
                    } else {
                        System.out.println("✗ Không tìm thấy khách hàng với CCCD: " + CCCD);
                    }
                } else {
                    System.out.println("CCCD không được để trống.");
                }
                break;
                
            default:
                System.out.println("Lựa chọn không hợp lệ.");
                break;
        }
    }
    
    public static void inputXemThongKe(Scanner sc) {
        System.out.println("\n=== XEM THỐNG KÊ KHÁCH HÀNG ===");
        KhachHangController.xemThongKeKhachHang();
    }
    
    public static void inputBaoCaoVIP(Scanner sc) {
        System.out.println("\n=== BÁO CÁO KHÁCH HÀNG VIP ===");
        KhachHangController.baoCaoKhachHangVIP();
    }
    
    public static void inputBaoCaoMoi(Scanner sc) {
        System.out.println("\n=== BÁO CÁO KHÁCH HÀNG MỚI ===");
        KhachHangController.baoCaoKhachHangMoi();
    }
    
    public static void hienThiMenu() {
        System.out.println("\n=== MENU QUẢN LÝ ĐẶT VÉ ===");
        System.out.println("1. Đặt vé");
        System.out.println("2. Xem lịch sử đặt vé");
        System.out.println("3. Tính tổng tiền khách hàng");
        System.out.println("4. Tìm kiếm khách hàng");
        System.out.println("5. Xem thống kê khách hàng");
        System.out.println("6. Báo cáo khách hàng VIP");
        System.out.println("7. Báo cáo khách hàng mới");
        System.out.println("0. Thoát");
        System.out.print("Chọn chức năng (0-7): ");
    }
    
    public static void xuLyLuaChon(Scanner sc) {
        String luaChon = sc.nextLine().trim();
        
        switch (luaChon) {
            case "1":
                inputDatVe(sc);
                break;
            case "2":
                inputXemLichSuDatVe(sc);
                break;
            case "3":
                inputTinhTongTien(sc);
                break;
            case "4":
                inputTimKiemKhachHang(sc);
                break;
            case "5":
                inputXemThongKe(sc);
                break;
            case "6":
                inputBaoCaoVIP(sc);
                break;
            case "7":
                inputBaoCaoMoi(sc);
                break;
            case "0":
                System.out.println("Cảm ơn bạn đã sử dụng chương trình!");
                System.exit(0);
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                break;
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== CHƯƠNG TRÌNH QUẢN LÝ ĐẶT VÉ ===");
        
        while (true) {
            hienThiMenu();
            xuLyLuaChon(sc);
        }
    }
}