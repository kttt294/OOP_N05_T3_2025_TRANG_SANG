import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class testSuatChieuController {
    public static void test() {
        Ghe g1 = new Ghe("G01", 1, 1, Ghe.LoaiGhe.THUONG, "PH01");
        Ghe g2 = new Ghe("G02", 1, 2, Ghe.LoaiGhe.VIP, "PH01");
        Ghe g3 = new Ghe("G03", 2, 1, Ghe.LoaiGhe.COUPLE, "PH02");

        List<Ghe> danhSachGhe = Arrays.asList(g1, g2, g3);

        SuatChieu sc1 = new SuatChieu("SC01", "P01", "PH01",
                LocalDateTime.of(2025, 7, 22, 14, 0),
                danhSachGhe);
        SuatChieu sc2 = new SuatChieu("SC02", "P02", "PH02",
                LocalDateTime.of(2025, 7, 22, 18, 0),
                danhSachGhe);
        SuatChieu sc3 = new SuatChieu("SC03", "P01", "PH01",
                LocalDateTime.of(2025, 7, 23, 14, 0),
                danhSachGhe);

        List<SuatChieu> danhSach = Arrays.asList(sc1, sc2, sc3);

        SuatChieuController controller = new SuatChieuController();
        controller.hienThiSuatChieuTrongNgay(danhSach);
    }
}
