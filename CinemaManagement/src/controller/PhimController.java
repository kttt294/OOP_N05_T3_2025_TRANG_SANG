import java.util.ArrayList;
import controller.GenericController;

public class PhimController implements GenericController {
    
    // Tạo phim mới
    public static boolean taoPhim(Phim phim) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (phim == null) {
                throw new IllegalArgumentException("Phim không được null!");
            }
            if (phim.getMaPhim() == null || phim.getMaPhim().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã phim không được để trống!");
            }
            if (phim.getTenPhim() == null || phim.getTenPhim().trim().isEmpty()) {
                throw new IllegalArgumentException("Tên phim không được để trống!");
            }
            if (phim.getThoiLuong() <= 0) {
                throw new IllegalArgumentException("Thời lượng phim phải lớn hơn 0!");
            }
            if (phim.getNamSanXuat() < 1900 || phim.getNamSanXuat() > 2030) {
                throw new IllegalArgumentException("Năm sản xuất không hợp lệ!");
            }

            Phim.Create(phim);
            System.out.println("Tạo phim thành công!");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Cập nhật phim
    public static boolean capNhatPhim(String maPhim, Phim phimMoi) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maPhim == null || maPhim.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã phim không được để trống!");
            }
            if (phimMoi == null) {
                throw new IllegalArgumentException("Thông tin phim mới không được null!");
            }

            // Kiểm tra phim có tồn tại không
            Phim phimCu = Phim.getPhimByMaPhim(maPhim);
            if (phimCu == null) {
                System.out.println("Không tìm thấy phim với mã: " + maPhim);
                return false;
            }

            Phim.Update(maPhim, phimMoi);
            System.out.println("Cập nhật phim thành công!");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Xóa phim
    public static boolean xoaPhim(String maPhim) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maPhim == null || maPhim.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã phim không được để trống!");
            }

            // Kiểm tra phim có tồn tại không
            Phim phim = Phim.getPhimByMaPhim(maPhim);
            if (phim == null) {
                System.out.println("Không tìm thấy phim với mã: " + maPhim);
                return false;
            }

            Phim.Delete(maPhim);
            System.out.println("Xóa phim thành công!");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Xem thông tin phim
    public static boolean xemThongTinPhim(String maPhim) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maPhim == null || maPhim.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã phim không được để trống!");
            }

            Phim.Read(maPhim);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Xem tất cả phim
    public static boolean xemTatCaPhim() {
        try {
            Phim.Read();
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Tìm kiếm phim theo mã
    public static Phim timPhimTheoMa(String maPhim) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maPhim == null || maPhim.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã phim không được để trống!");
            }

            return Phim.getPhimByMaPhim(maPhim);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return null;
        }
    }

    // Tìm kiếm phim theo tên
    public static ArrayList<Phim> timPhimTheoTen(String tenPhim) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (tenPhim == null || tenPhim.trim().isEmpty()) {
                throw new IllegalArgumentException("Tên phim không được để trống!");
            }

            return Phim.timKiemTheoTen(tenPhim);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Tìm kiếm phim theo thể loại
    public static ArrayList<Phim> timPhimTheoTheLoai(String theLoai) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (theLoai == null || theLoai.trim().isEmpty()) {
                throw new IllegalArgumentException("Thể loại không được để trống!");
            }

            return Phim.timKiemTheoTheLoai(theLoai);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Thống kê phim
    public static boolean thongKePhim() {
        try {
            Phim.thongKePhim();
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