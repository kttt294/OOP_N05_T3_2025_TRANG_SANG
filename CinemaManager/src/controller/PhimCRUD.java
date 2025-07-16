import java.util.ArrayList;
import java.util.Scanner;

public class PhimCRUD {
    // Biến static để dùng chung cho tất cả các phương thức static
    private static ArrayList<Phim> danhSachPhim = new ArrayList<>();

    public static void createPhim(Scanner sc) {
        System.out.println("\n=== Thêm phim mới ===");

        System.out.print("Mã phim: ");
        String maPhim = sc.nextLine().trim();
        if (maPhim.isEmpty()) {
            System.out.println("Mã phim không được để trống.");
            return;
        }
        for (Phim p : danhSachPhim) {
            if (p.getMaPhim().equalsIgnoreCase(maPhim)) {
                System.out.println("Mã phim đã tồn tại.");
                return;
            }
        }

        System.out.print("Tên phim: ");
        String tenPhim = sc.nextLine().trim();
        if (tenPhim.isEmpty()) {
            System.out.println("Tên phim không được để trống.");
            return;
        }

        System.out.print("Thể loại: ");
        String theLoai = sc.nextLine().trim();

        System.out.print("Thời lượng (phút): ");
        int thoiLuong = Integer.parseInt(sc.nextLine().trim());

        System.out.print("Ngôn ngữ: ");
        String ngonNgu = sc.nextLine().trim();

        System.out.print("Giới hạn tuổi: ");
        int gioiHanTuoi = Integer.parseInt(sc.nextLine().trim());

        System.out.print("Mô tả: ");
        String moTa = sc.nextLine().trim();

        Phim p = new Phim(maPhim, tenPhim, theLoai, thoiLuong, ngonNgu, gioiHanTuoi, moTa);
        danhSachPhim.add(p);
        System.out.println("Đã thêm phim thành công.");
    }

    public static void readPhim(Scanner sc) {
        if (danhSachPhim.isEmpty()) {
            System.out.println("Danh sách phim trống.");
            return;
        }
        System.out.println("\n=== Danh sách phim ===");
        for (Phim p : danhSachPhim) {
            p.hienThiThongTin();
        }
    }

    public static void updatePhim(Scanner sc) {
        System.out.print("Nhập mã phim cần sửa: ");
        String maPhim = sc.nextLine().trim();

        for (Phim p : danhSachPhim) {
            if (p.getMaPhim().equalsIgnoreCase(maPhim)) {
                System.out.print("Tên phim mới: ");
                p.setTenPhim(sc.nextLine().trim());

                System.out.print("Thể loại mới: ");
                p.setTheLoai(sc.nextLine().trim());

                System.out.print("Thời lượng mới: ");
                p.setThoiLuong(Integer.parseInt(sc.nextLine().trim()));

                System.out.print("Ngôn ngữ mới: ");
                p.setNgonNgu(sc.nextLine().trim());

                System.out.print("Giới hạn tuổi mới: ");
                p.setGioiHanTuoi(Integer.parseInt(sc.nextLine().trim()));

                System.out.print("Mô tả mới: ");
                p.setMoTa(sc.nextLine().trim());

                System.out.println("Cập nhật phim thành công.");
                return;
            }
        }

        System.out.println("Không tìm thấy phim với mã đã nhập.");
    }

    public static void deletePhim(Scanner sc) {
        System.out.print("Nhập mã phim cần xoá: ");
        String maPhim = sc.nextLine().trim();

        for (Phim p : danhSachPhim) {
            if (p.getMaPhim().equalsIgnoreCase(maPhim)) {
                danhSachPhim.remove(p);
                System.out.println("Xoá phim thành công.");
                return;
            }
        }

        System.out.println("Không tìm thấy phim với mã đã nhập.");
    }
}
