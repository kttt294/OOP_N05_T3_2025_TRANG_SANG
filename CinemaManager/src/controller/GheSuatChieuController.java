import java.util.ArrayList;

public class GheSuatChieuController {
    public static void themGheSuatChieu(GheSuatChieu gsc) {
        GheSuatChieu.Create(gsc);
    }

    public static void xemThongTinGheSuatChieu(String maGhe, String maSuatChieu) {
        GheSuatChieu.Read(maGhe, maSuatChieu);
    }

    public static ArrayList<GheSuatChieu> layTatCaGheSuatChieu() {
        return GheSuatChieu.Read();
    }

    public static void capNhatGheSuatChieu(String maGhe, String maSuatChieu, GheSuatChieu gscMoi) {
        GheSuatChieu.Update(maGhe, maSuatChieu, gscMoi);
    }

    public static void xoaGheSuatChieu(String maGhe, String maSuatChieu) {
        GheSuatChieu.Delete(maGhe, maSuatChieu);
    }
} 