import java.util.ArrayList;
import controller.GenericController;

public class DoAnController implements GenericController {
    
    // Tạo đồ ăn mới
    public static boolean taoDoAn(DoAn doAn) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (doAn == null) {
                throw new IllegalArgumentException("Đồ ăn không được null!");
            }
            if (doAn.getMaDoAn() == null || doAn.getMaDoAn().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã đồ ăn không được để trống!");
            }
            if (doAn.getTenDoAn() == null || doAn.getTenDoAn().trim().isEmpty()) {
                throw new IllegalArgumentException("Tên đồ ăn không được để trống!");
            }
            if (doAn.getGia() <= 0) {
                throw new IllegalArgumentException("Giá đồ ăn phải lớn hơn 0!");
            }
            if (doAn.getSoLuong() < 0) {
                throw new IllegalArgumentException("Số lượng không được âm!");
            }

            DoAn.Create(doAn);
            System.out.println("Tạo đồ ăn thành công!");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Cập nhật đồ ăn
    public static boolean capNhatDoAn(String maDoAn, DoAn doAnMoi) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maDoAn == null || maDoAn.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã đồ ăn không được để trống!");
            }
            if (doAnMoi == null) {
                throw new IllegalArgumentException("Thông tin đồ ăn mới không được null!");
            }

            // Kiểm tra đồ ăn có tồn tại không
            DoAn doAnCu = DoAn.getDoAnByMaDoAn(maDoAn);
            if (doAnCu == null) {
                System.out.println("Không tìm thấy đồ ăn với mã: " + maDoAn);
                return false;
            }

            DoAn.Update(maDoAn, doAnMoi);
            System.out.println("Cập nhật đồ ăn thành công!");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Xóa đồ ăn
    public static boolean xoaDoAn(String maDoAn) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maDoAn == null || maDoAn.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã đồ ăn không được để trống!");
            }

            // Kiểm tra đồ ăn có tồn tại không
            DoAn doAn = DoAn.getDoAnByMaDoAn(maDoAn);
            if (doAn == null) {
                System.out.println("Không tìm thấy đồ ăn với mã: " + maDoAn);
                return false;
            }

            DoAn.Delete(maDoAn);
            System.out.println("Xóa đồ ăn thành công!");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Xem thông tin đồ ăn
    public static boolean xemThongTinDoAn(String maDoAn) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maDoAn == null || maDoAn.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã đồ ăn không được để trống!");
            }

            DoAn.Read(maDoAn);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Xem tất cả đồ ăn
    public static boolean xemTatCaDoAn() {
        try {
            DoAn.Read();
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Tìm kiếm đồ ăn theo mã
    public static DoAn timDoAnTheoMa(String maDoAn) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maDoAn == null || maDoAn.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã đồ ăn không được để trống!");
            }

            return DoAn.getDoAnByMaDoAn(maDoAn);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return null;
        }
    }

    // Tìm kiếm đồ ăn theo tên
    public static ArrayList<DoAn> timDoAnTheoTen(String tenDoAn) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (tenDoAn == null || tenDoAn.trim().isEmpty()) {
                throw new IllegalArgumentException("Tên đồ ăn không được để trống!");
            }

            return DoAn.timKiemTheoTen(tenDoAn);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Tìm kiếm đồ ăn theo loại
    public static ArrayList<DoAn> timDoAnTheoLoai(String loaiDoAn) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (loaiDoAn == null || loaiDoAn.trim().isEmpty()) {
                throw new IllegalArgumentException("Loại đồ ăn không được để trống!");
            }

            return DoAn.timKiemTheoLoai(loaiDoAn);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Cập nhật số lượng đồ ăn
    public static boolean capNhatSoLuong(String maDoAn, int soLuongMoi) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maDoAn == null || maDoAn.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã đồ ăn không được để trống!");
            }
            if (soLuongMoi < 0) {
                throw new IllegalArgumentException("Số lượng không được âm!");
            }

            DoAn doAn = DoAn.getDoAnByMaDoAn(maDoAn);
            if (doAn == null) {
                System.out.println("Không tìm thấy đồ ăn với mã: " + maDoAn);
                return false;
            }

            doAn.setSoLuong(soLuongMoi);
            DoAn.Update(maDoAn, doAn);
            System.out.println("Cập nhật số lượng đồ ăn thành công!");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Thống kê đồ ăn
    public static boolean thongKeDoAn() {
        try {
            DoAn.thongKeDoAn();
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