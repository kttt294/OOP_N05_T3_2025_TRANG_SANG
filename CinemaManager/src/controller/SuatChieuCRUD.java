import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SuatChieuCRUD {
    private List<SuatChieu> danhSachSuatChieu = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void themSuatChieu(SuatChieu suatChieu) {
        danhSachSuatChieu.add(suatChieu);
        System.out.println("Đã thêm suất chiếu thành công.");
    }

    public void hienThiTatCaSuatChieu() {
        if (danhSachSuatChieu.isEmpty()) {
            System.out.println("Không có suất chiếu nào.");
            return;
        }
        for (SuatChieu sc : danhSachSuatChieu) {
            sc.hienThiThongTin();
            System.out.println("--------------------");
        }
    }

    public SuatChieu timSuatChieuTheoMa(String ma) {
        for (SuatChieu sc : danhSachSuatChieu) {
            if (sc.getMaSuatChieu().equalsIgnoreCase(ma)) {
                return sc;
            }
        }
        return null;
    }

    public void xoaSuatChieu(String ma) {
        SuatChieu sc = timSuatChieuTheoMa(ma);
        if (sc != null) {
            danhSachSuatChieu.remove(sc);
            System.out.println("Đã xóa suất chiếu thành công.");
        } else {
            System.out.println("Không tìm thấy suất chiếu có mã: " + ma);
        }
    }

    public void capNhatThoiGianBatDau(String ma) {
        SuatChieu sc = timSuatChieuTheoMa(ma);
        if (sc == null) {
            System.out.println("Không tìm thấy suất chiếu.");
            return;
        }

        System.out.print("Nhập thời gian bắt đầu mới (yyyy-MM-ddTHH:mm): ");
        String thoiGianMoi = scanner.nextLine();
        LocalDateTime thoiGian = LocalDateTime.parse(thoiGianMoi);
        sc.setThoiGianBatDau(thoiGian);
        System.out.println("Đã cập nhật thời gian bắt đầu.");
    }
}

