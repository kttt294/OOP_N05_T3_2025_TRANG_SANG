import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class testSuatChieuController {
    public static void main(String[] args) {
        SuatChieu sc1 = new SuatChieu("SC01", "P01", "PH01", LocalDateTime.of(2025, 7, 22, 14, 0),LocalDateTime.of(2025, 7, 22, 16, 0));
        SuatChieu sc2 = new SuatChieu(
        SuatChieu sc3 = new SuatChieu(

        List<SuatChieu> danhSach = Arrays.asList(sc1, sc2, sc3);

        SuatChieuController controller = new SuatChieuController();
        controller.hienThiSuatChieuTrongNgay(danhSach);
    }
}
