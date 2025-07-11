package controller;

import model.Phim;

import java.util.ArrayList;
import java.util.Scanner;

public class PhimCRUD {
    private ArrayList<Phim> danhSachPhim = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public void createPhim() {
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
        if (theLoai.isEmpty()) {
            System.out.println("Thể loại không được để trống.");
            return;
        }

        System.out.print("Thời lượng (phút): ");
        int thoiLuong;
        try {
            thoiLuong = Integer.parseInt(sc.nextLine());
            if (thoiLuong <= 0) {
                System.out.println("Thời lượng phải lớn hơn 0.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Thời lượng phải là số nguyên.");
            return;
        }

        System.out.print("Ngôn ngữ: ");
        String ngonNgu = sc.nextLine().trim();
        if (ngonNgu.isEmpty()) {
            System.out.println("Ngôn ngữ không được để trống.");
            return;
        }

        System.out.print("Giới hạn tuổi: ");
        int gioiHanTuoi;
        try {
            gioiHanTuoi = Integer.parseInt(sc.nextLine());
            if (gioiHanTuoi < 0) {
                System.out.println("Giới hạn tuổi không hợp lệ.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Giới hạn tuổi phải là số nguyên.");
            return;
        }

        System.out.print("Mô tả: ");
        String moTa = sc.nextLine().trim();

        Phim p = new Phim(maPhim, tenPhim, theLoai, thoiLuong, ngonNgu, gioiHanTuoi, moTa);
        danhSachPhim.add(p);
        System.out.println("Đã thêm phim thành công.");
    }

    public void readPhim() {
        System.out.println("\n=== Danh sách phim ===");
        if (danhSachPhim.isEmpty()) {
            System.out.println("Không có phim nào.");
            return;
        }
        for (Phim p : danhSachPhim) {
            System.out.println("---------------------------");
            p.hienThiThongTin();
        }
    }

    public void editPhim() {
        System.out.print("\nNhập mã phim cần sửa: ");
        String ma = sc.nextLine().trim();
        for (Phim p : danhSachPhim) {
            if (p.getMaPhim().equalsIgnoreCase(ma)) {
                System.out.println("=== Cập nhật thông tin phim ===");

                System.out.print("Tên phim mới: ");
                String tenPhim = sc.nextLine().trim();
                if (!tenPhim.isEmpty()) p.setTenPhim(tenPhim);

                System.out.print("Thể loại mới: ");
                String theLoai = sc.nextLine().trim();
                if (!theLoai.isEmpty()) p.setTheLoai(theLoai);

                System.out.print("Thời lượng mới: ");
                try {
                    int thoiLuong = Integer.parseInt(sc.nextLine());
                    if (thoiLuong > 0) p.setThoiLuong(thoiLuong);
                    else System.out.println("Bỏ qua cập nhật thời lượng (giá trị không hợp lệ).");
                } catch (NumberFormatException e) {
                    System.out.println("Bỏ qua cập nhật thời lượng (không phải số).");
                }

                System.out.print("Ngôn ngữ mới: ");
                String ngonNgu = sc.nextLine().trim();
                if (!ngonNgu.isEmpty()) p.setNgonNgu(ngonNgu);

                System.out.print("Giới hạn tuổi mới: ");
                try {
                    int gioiHanTuoi = Integer.parseInt(sc.nextLine());
                    if (gioiHanTuoi >= 0) p.setGioiHanTuoi(gioiHanTuoi);
                    else System.out.println("Bỏ qua cập nhật giới hạn tuổi (giá trị không hợp lệ).");
                } catch (NumberFormatException e) {
                    System.out.println("Bỏ qua cập nhật giới hạn tuổi (không phải số).");
                }

                System.out.print("Mô tả mới: ");
                String moTa = sc.nextLine().trim();
                if (!moTa.isEmpty()) p.setMoTa(moTa);

                System.out.println("Đã cập nhật thông tin phim.");
                return;
            }
        }
        System.out.println("Không tìm thấy mã phim.");
    }

    public void deletePhim() {
        System.out.print("\nNhập mã phim cần xoá: ");
        String ma = sc.nextLine().trim();
        for (Phim p : danhSachPhim) {
            if (p.getMaPhim().equalsIgnoreCase(ma)) {
                danhSachPhim.remove(p);
                System.out.println("Đã xoá phim.");
                return;
            }
        }
        System.out.println("Không tìm thấy mã phim.");
    }
}
