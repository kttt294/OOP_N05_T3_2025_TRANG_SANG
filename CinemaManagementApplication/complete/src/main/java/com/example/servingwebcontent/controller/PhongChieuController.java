import java.util.ArrayList;
import controller.GenericController;

public class PhongChieuController implements GenericController {
    
    // Tạo phòng chiếu mới
    public static boolean taoPhongChieu(PhongChieu phongChieu) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (phongChieu == null) {
                throw new IllegalArgumentException("Phòng chiếu không được null!");
            }
            if (phongChieu.getMaPhong() == null || phongChieu.getMaPhong().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã phòng không được để trống!");
            }
            if (phongChieu.getTenPhong() == null || phongChieu.getTenPhong().trim().isEmpty()) {
                throw new IllegalArgumentException("Tên phòng không được để trống!");
            }
            if (phongChieu.getSoHang() <= 0) {
                throw new IllegalArgumentException("Số hàng phải lớn hơn 0!");
            }
            if (phongChieu.getSoCot() <= 0) {
                throw new IllegalArgumentException("Số cột phải lớn hơn 0!");
            }

            PhongChieu.Create(phongChieu);
            System.out.println("Tạo phòng chiếu thành công!");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Cập nhật phòng chiếu
    public static boolean capNhatPhongChieu(String maPhong, PhongChieu phongChieuMoi) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maPhong == null || maPhong.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã phòng không được để trống!");
            }
            if (phongChieuMoi == null) {
                throw new IllegalArgumentException("Thông tin phòng chiếu mới không được null!");
            }

            // Kiểm tra phòng chiếu có tồn tại không
            PhongChieu phongChieuCu = PhongChieu.getPhongChieuByMaPhong(maPhong);
            if (phongChieuCu == null) {
                System.out.println("Không tìm thấy phòng chiếu với mã: " + maPhong);
                return false;
            }

            PhongChieu.Update(maPhong, phongChieuMoi);
            System.out.println("Cập nhật phòng chiếu thành công!");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Xóa phòng chiếu
    public static boolean xoaPhongChieu(String maPhong) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maPhong == null || maPhong.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã phòng không được để trống!");
            }

            // Kiểm tra phòng chiếu có tồn tại không
            PhongChieu phongChieu = PhongChieu.getPhongChieuByMaPhong(maPhong);
            if (phongChieu == null) {
                System.out.println("Không tìm thấy phòng chiếu với mã: " + maPhong);
                return false;
            }

            PhongChieu.Delete(maPhong);
            System.out.println("Xóa phòng chiếu thành công!");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Xem thông tin phòng chiếu
    public static boolean xemThongTinPhongChieu(String maPhong) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maPhong == null || maPhong.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã phòng không được để trống!");
            }

            PhongChieu.Read(maPhong);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Xem tất cả phòng chiếu
    public static boolean xemTatCaPhongChieu() {
        try {
            PhongChieu.Read();
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Tìm kiếm phòng chiếu theo mã
    public static PhongChieu timPhongChieuTheoMa(String maPhong) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maPhong == null || maPhong.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã phòng không được để trống!");
            }

            return PhongChieu.getPhongChieuByMaPhong(maPhong);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return null;
        }
    }

    // Tìm kiếm phòng chiếu theo tên
    public static ArrayList<PhongChieu> timPhongChieuTheoTen(String tenPhong) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (tenPhong == null || tenPhong.trim().isEmpty()) {
                throw new IllegalArgumentException("Tên phòng không được để trống!");
            }

            return PhongChieu.timKiemTheoTen(tenPhong);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Thống kê phòng chiếu
    public static boolean thongKePhongChieu() {
        try {
            PhongChieu.thongKePhongChieu();
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