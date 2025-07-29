import java.util.ArrayList;
import controller.GenericController;

public class VoucherController implements GenericController {
    
    // Tạo voucher mới
    public static boolean taoVoucher(Voucher voucher) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (voucher == null) {
                throw new IllegalArgumentException("Voucher không được null!");
            }
            if (voucher.getMaVoucher() == null || voucher.getMaVoucher().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã voucher không được để trống!");
            }
            if (voucher.getTenVoucher() == null || voucher.getTenVoucher().trim().isEmpty()) {
                throw new IllegalArgumentException("Tên voucher không được để trống!");
            }
            if (voucher.getGiaTri() <= 0) {
                throw new IllegalArgumentException("Giá trị voucher phải lớn hơn 0!");
            }
            if (voucher.getSoLuong() < 0) {
                throw new IllegalArgumentException("Số lượng không được âm!");
            }

            Voucher.Create(voucher);
            System.out.println("Tạo voucher thành công!");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Cập nhật voucher
    public static boolean capNhatVoucher(String maVoucher, Voucher voucherMoi) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maVoucher == null || maVoucher.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã voucher không được để trống!");
            }
            if (voucherMoi == null) {
                throw new IllegalArgumentException("Thông tin voucher mới không được null!");
            }

            // Kiểm tra voucher có tồn tại không
            Voucher voucherCu = Voucher.getVoucherByMaVoucher(maVoucher);
            if (voucherCu == null) {
                System.out.println("Không tìm thấy voucher với mã: " + maVoucher);
                return false;
            }

            Voucher.Update(maVoucher, voucherMoi);
            System.out.println("Cập nhật voucher thành công!");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Xóa voucher
    public static boolean xoaVoucher(String maVoucher) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maVoucher == null || maVoucher.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã voucher không được để trống!");
            }

            // Kiểm tra voucher có tồn tại không
            Voucher voucher = Voucher.getVoucherByMaVoucher(maVoucher);
            if (voucher == null) {
                System.out.println("Không tìm thấy voucher với mã: " + maVoucher);
                return false;
            }

            Voucher.Delete(maVoucher);
            System.out.println("Xóa voucher thành công!");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Xem thông tin voucher
    public static boolean xemThongTinVoucher(String maVoucher) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maVoucher == null || maVoucher.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã voucher không được để trống!");
            }

            Voucher.Read(maVoucher);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Xem tất cả voucher
    public static boolean xemTatCaVoucher() {
        try {
            Voucher.Read();
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Tìm kiếm voucher theo mã
    public static Voucher timVoucherTheoMa(String maVoucher) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maVoucher == null || maVoucher.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã voucher không được để trống!");
            }

            return Voucher.getVoucherByMaVoucher(maVoucher);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return null;
        }
    }

    // Tìm kiếm voucher theo tên
    public static ArrayList<Voucher> timVoucherTheoTen(String tenVoucher) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (tenVoucher == null || tenVoucher.trim().isEmpty()) {
                throw new IllegalArgumentException("Tên voucher không được để trống!");
            }

            return Voucher.timKiemTheoTen(tenVoucher);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Kiểm tra voucher có hợp lệ không
    public static boolean kiemTraVoucherHopLe(String maVoucher) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maVoucher == null || maVoucher.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã voucher không được để trống!");
            }

            return Voucher.kiemTraVoucherHopLe(maVoucher);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Sử dụng voucher
    public static boolean suDungVoucher(String maVoucher) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maVoucher == null || maVoucher.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã voucher không được để trống!");
            }

            return Voucher.suDungVoucher(maVoucher);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Thống kê voucher
    public static boolean thongKeVoucher() {
        try {
            Voucher.thongKeVoucher();
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