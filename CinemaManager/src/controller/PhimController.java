import java.util.ArrayList;
import model.Phim;

public class PhimController {
    public static void themPhim(Phim phim) {
        Phim.Create(phim);
    }

    public static void xemThongTinPhim(String maPhim) {
        Phim.Read(maPhim);
    }

    public static ArrayList<Phim> layTatCaPhim() {
        return Phim.Read();
    }

    public static void capNhatPhim(String maPhim, Phim phimMoi) {
        Phim.Update(maPhim, phimMoi);
    }

    public static void xoaPhim(String maPhim) {
        Phim.Delete(maPhim);
    }
} 