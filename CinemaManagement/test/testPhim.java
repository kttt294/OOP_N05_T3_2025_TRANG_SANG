import java.util.Scanner;

public class testPhim {
    public static void inputCreatePhim(Scanner sc) {
        System.out.println("\n=== Thêm phim mới ===");

        System.out.print("Mã phim: ");
        String maPhim = sc.nextLine().trim();
        if (maPhim.isEmpty()) {
            System.out.println("Mã phim không được để trống.");
            return;
        }

        if (Phim.getPhimById(maPhim) != null) {
            System.out.println("Mã phim đã tồn tại.");
            return;
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
        Phim.Create(p);
    }

    public static void inputReadPhim(Scanner sc){
        System.out.println("Nhập mã phim cần xem thông tin: ");
        String maPHim = sc.nextLine().trim();
        Phim.Read(maPHim);
    }
    
    public static void inputUpdatePhim(Scanner sc) {
        System.out.print("Nhập mã phim cần sửa: ");
        String maPhim = sc.nextLine().trim();

        Phim p = Phim.getPhimById(maPhim);
        if (p == null) {
            System.out.println("Không tìm thấy phim.");
        }

        System.out.println("\n=== Cập nhật thông tin phim ===");

        System.out.print("Tên phim mới: ");
        String tenPhim = sc.nextLine().trim();
        if (!tenPhim.isEmpty()) {
            p.setTenPhim(tenPhim);
        }

        System.out.print("Thể loại mới: ");
        String theLoai = sc.nextLine().trim();
        if (!theLoai.isEmpty()) {
            p.setTheLoai(theLoai);
        }

        System.out.print("Thời lượng mới (phút): ");
        String thoiLuongStr = sc.nextLine().trim();
        if (!thoiLuongStr.isEmpty()) {
            p.setThoiLuong(Integer.parseInt(thoiLuongStr));
        }

        System.out.print("Ngôn ngữ mới: ");
        String ngonNgu = sc.nextLine().trim();
        if (!ngonNgu.isEmpty()) {
            p.setNgonNgu(ngonNgu);
        }

        System.out.print("Giới hạn tuổi mới: ");
        String gioiHanTuoiStr = sc.nextLine().trim();
        if (!gioiHanTuoiStr.isEmpty()) {
            p.setGioiHanTuoi(Integer.parseInt(gioiHanTuoiStr));
        }

        System.out.print("Mô tả mới: ");
        String moTa = sc.nextLine().trim();
        if (!moTa.isEmpty()) {
            p.setMoTa(moTa);
        }

        Phim.Update(maPhim, p);
    }

    public static void inputDeletePhim(Scanner sc) {
        System.out.print("Nhập mã phim cần xoá: ");
        String maPhim = sc.nextLine().trim();
        Phim.Delete(maPhim);
    }

    public static void test() {
        Scanner sc = new Scanner(System.in);
        int luaChon;
        do {
            System.out.println("\nMENU PHIM");
            System.out.println("1. Thêm phim");
            System.out.println("2. Xem danh sách phim");
            System.out.println("3. Sửa phim");
            System.out.println("4. Xoá phim");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            try {
                luaChon = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                luaChon = -1;
            }

            switch (luaChon) {
                case 1:
                    inputCreatePhim(sc);
                    break;
                case 2:
                    inputReadPhim(sc);
                    break;
                case 3:
                    inputUpdatePhim(sc);
                    break;
                case 4:
                    inputDeletePhim(sc);
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (luaChon != 0);
        sc.close();
    }
}
