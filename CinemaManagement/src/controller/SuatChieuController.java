import java.util.List;
import java.time.LocalDate;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SuatChieuController {
    
    // Hiển thị suất chiếu trong ngày
    public static boolean hienThiSuatChieuTrongNgay(List<SuatChieu> danhSachSuatChieu) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (danhSachSuatChieu == null) {
                throw new IllegalArgumentException("Danh sách suất chiếu không được null!");
            }

            Scanner scanner = new Scanner(System.in);
            System.out.print("Nhập ngày (dd/MM/yyyy): ");
            String input = scanner.nextLine();

            // Kiểm tra input không rỗng
            if (input == null || input.trim().isEmpty()) {
                System.out.println("Ngày không được để trống!");
                scanner.close();
                return false;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate ngay = null;
            
            try {
                ngay = LocalDate.parse(input, formatter);
            } catch (Exception e) {
                System.out.println("Định dạng ngày không hợp lệ! Vui lòng nhập theo định dạng dd/MM/yyyy");
                scanner.close();
                return false;
            }

            boolean tim = false;
            System.out.println("Danh sách suất chiếu trong ngày " + ngay + ":");
            
            for (SuatChieu sc : danhSachSuatChieu) {
                if (sc.getThoiGianBatDau().toLocalDate().equals(ngay)) {
                    sc.hienThiThongTin();
                    tim = true;
                }
            }
            
            if (!tim) {
                System.out.println("Không có suất chiếu nào trong ngày " + ngay);
            }
            
            scanner.close();
            return true;
            
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Tạo suất chiếu mới
    public static boolean taoSuatChieu(SuatChieu suatChieu) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (suatChieu == null) {
                throw new IllegalArgumentException("Suất chiếu không được null!");
            }
            if (suatChieu.getMaSuatChieu() == null || suatChieu.getMaSuatChieu().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã suất chiếu không được để trống!");
            }
            if (suatChieu.getMaPhim() == null || suatChieu.getMaPhim().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã phim không được để trống!");
            }
            if (suatChieu.getMaPhong() == null || suatChieu.getMaPhong().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã phòng không được để trống!");
            }
            if (suatChieu.getThoiGianBatDau() == null) {
                throw new IllegalArgumentException("Thời gian bắt đầu không được null!");
            }
            if (suatChieu.getThoiGianKetThuc() == null) {
                throw new IllegalArgumentException("Thời gian kết thúc không được null!");
            }
            if (suatChieu.getThoiGianBatDau().isAfter(suatChieu.getThoiGianKetThuc())) {
                throw new IllegalArgumentException("Thời gian bắt đầu phải trước thời gian kết thúc!");
            }

            SuatChieu.Create(suatChieu);
            System.out.println("Tạo suất chiếu thành công!");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Cập nhật suất chiếu
    public static boolean capNhatSuatChieu(String maSuatChieu, SuatChieu suatChieuMoi) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maSuatChieu == null || maSuatChieu.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã suất chiếu không được để trống!");
            }
            if (suatChieuMoi == null) {
                throw new IllegalArgumentException("Thông tin suất chiếu mới không được null!");
            }

            // Kiểm tra suất chiếu có tồn tại không
            SuatChieu suatChieuCu = SuatChieu.getSuatChieuByMaSuatChieu(maSuatChieu);
            if (suatChieuCu == null) {
                System.out.println("Không tìm thấy suất chiếu với mã: " + maSuatChieu);
                return false;
            }

            SuatChieu.Update(maSuatChieu, suatChieuMoi);
            System.out.println("Cập nhật suất chiếu thành công!");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Xóa suất chiếu
    public static boolean xoaSuatChieu(String maSuatChieu) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maSuatChieu == null || maSuatChieu.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã suất chiếu không được để trống!");
            }

            // Kiểm tra suất chiếu có tồn tại không
            SuatChieu suatChieu = SuatChieu.getSuatChieuByMaSuatChieu(maSuatChieu);
            if (suatChieu == null) {
                System.out.println("Không tìm thấy suất chiếu với mã: " + maSuatChieu);
                return false;
            }

            SuatChieu.Delete(maSuatChieu);
            System.out.println("Xóa suất chiếu thành công!");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Xem thông tin suất chiếu
    public static boolean xemThongTinSuatChieu(String maSuatChieu) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maSuatChieu == null || maSuatChieu.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã suất chiếu không được để trống!");
            }

            SuatChieu.Read(maSuatChieu);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Xem tất cả suất chiếu
    public static boolean xemTatCaSuatChieu() {
        try {
            SuatChieu.Read();
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    // Tìm kiếm suất chiếu theo mã
    public static SuatChieu timSuatChieuTheoMa(String maSuatChieu) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maSuatChieu == null || maSuatChieu.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã suất chiếu không được để trống!");
            }

            return SuatChieu.getSuatChieuByMaSuatChieu(maSuatChieu);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return null;
        }
    }

    // Tìm kiếm suất chiếu theo phim
    public static ArrayList<SuatChieu> timSuatChieuTheoPhim(String maPhim) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maPhim == null || maPhim.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã phim không được để trống!");
            }

            return SuatChieu.getSuatChieuByPhim(maPhim);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Tìm kiếm suất chiếu theo phòng
    public static ArrayList<SuatChieu> timSuatChieuTheoPhong(String maPhong) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maPhong == null || maPhong.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã phòng không được để trống!");
            }

            return SuatChieu.getSuatChieuByPhong(maPhong);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}