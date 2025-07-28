import java.util.List;
import java.time.LocalDate;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class SuatChieuController {
    public void hienThiSuatChieuTrongNgay(List<SuatChieu> danhSachSuatChieu) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập ngày (dd/MM/yyyy): ");
        String input = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate ngay = LocalDate.parse(input, formatter);
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
    }
}