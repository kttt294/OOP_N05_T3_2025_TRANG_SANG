import java.util.ArrayList;
import controller.GenericController;

public class VeController implements GenericController {
    
    // Tạo vé mới
    public static boolean taoVe(Ve ve) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (ve == null) {
                throw new IllegalArgumentException("Vé không được null!");
            }
            if (ve.getMaVe() == null || ve.getMaVe().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã vé không được để trống!");
            }
            if (ve.getCCCD() == null || ve.getCCCD().trim().isEmpty()) {
                throw new IllegalArgumentException("CCCD không được để trống!");
            }
            if (ve.getMaSuatChieu() == null || ve.getMaSuatChieu().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã suất chiếu không được để trống!");
            }
            if (ve.getMaGhe() == null || ve.getMaGhe().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã ghế không được để trống!");
            }
            if (ve.getGiaVe() <= 0) {
                throw new IllegalArgumentException("Giá vé phải lớn hơn 0!");
            }

            Ve.Create(ve);
            System.out.println("Tạo vé thành công!");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Cập nhật vé
    public static boolean capNhatVe(String maVe, Ve veMoi) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maVe == null || maVe.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã vé không được để trống!");
            }
            if (veMoi == null) {
                throw new IllegalArgumentException("Thông tin vé mới không được null!");
            }

            // Kiểm tra vé có tồn tại không
            Ve veCu = Ve.getVeByMaVe(maVe);
            if (veCu == null) {
                System.out.println("Không tìm thấy vé với mã: " + maVe);
                return false;
            }

            Ve.Update(maVe, veMoi);
            System.out.println("Cập nhật vé thành công!");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Xóa vé
    public static boolean xoaVe(String maVe) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maVe == null || maVe.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã vé không được để trống!");
            }

            // Kiểm tra vé có tồn tại không
            Ve ve = Ve.getVeByMaVe(maVe);
            if (ve == null) {
                System.out.println("Không tìm thấy vé với mã: " + maVe);
                return false;
            }

            Ve.Delete(maVe);
            System.out.println("Xóa vé thành công!");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Xem thông tin vé
    public static boolean xemThongTinVe(String maVe) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maVe == null || maVe.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã vé không được để trống!");
            }

            Ve.Read(maVe);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Xem tất cả vé
    public static boolean xemTatCaVe() {
        try {
            Ve.Read();
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Tìm kiếm vé theo mã
    public static Ve timVeTheoMa(String maVe) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maVe == null || maVe.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã vé không được để trống!");
            }

            return Ve.getVeByMaVe(maVe);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return null;
        }
    }

    // Tìm kiếm vé theo khách hàng
    public static ArrayList<Ve> timVeTheoKhachHang(String CCCD) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (CCCD == null || CCCD.trim().isEmpty()) {
                throw new IllegalArgumentException("CCCD không được để trống!");
            }

            return Ve.getVeByKhachHang(CCCD);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Tìm kiếm vé theo suất chiếu
    public static ArrayList<Ve> timVeTheoSuatChieu(String maSuatChieu) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maSuatChieu == null || maSuatChieu.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã suất chiếu không được để trống!");
            }

            return Ve.getVeBySuatChieu(maSuatChieu);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Hủy vé
    public static boolean huyVe(String maVe) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maVe == null || maVe.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã vé không được để trống!");
            }

            // Kiểm tra vé có tồn tại không
            Ve ve = Ve.getVeByMaVe(maVe);
            if (ve == null) {
                System.out.println("Không tìm thấy vé với mã: " + maVe);
                return false;
            }

            // Kiểm tra trạng thái vé
            if (ve.getTrangThai() != Ve.TrangThaiVe.DA_DAT) {
                System.out.println("Vé không thể hủy (đã hủy hoặc đã sử dụng)!");
                return false;
            }

            // Cập nhật trạng thái vé thành đã hủy
            ve.setTrangThai(Ve.TrangThaiVe.DA_HUY);
            Ve.Update(maVe, ve);
            
            System.out.println("Hủy vé thành công!");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Tính tổng doanh thu
    public static double tinhTongDoanhThu() {
        try {
            return Ve.tinhTongDoanhThu();
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return 0.0;
        }
    }

    // Thống kê vé
    public static boolean thongKeVe() {
        try {
            Ve.thongKeVe();
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Object them(Object obj) { return null; }
    @Override
    public Object sua(Object obj) { return null; }
    @Override
    public boolean xoa(Object obj) { return false; }
    @Override
    public Object hienThi(Object obj) { return null; }
} 