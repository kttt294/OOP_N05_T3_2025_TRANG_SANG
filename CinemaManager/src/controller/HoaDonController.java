import java.util.ArrayList;
import model.HoaDon;

public class HoaDonController {
    public static void themHoaDon(HoaDon hd) {
        HoaDon.Create(hd);
    }

    public static void xemThongTinHoaDon(String maHoaDon) {
        HoaDon.Read(maHoaDon);
    }

    public static ArrayList<HoaDon> layTatCaHoaDon() {
        return HoaDon.Read();
    }

    public static void capNhatHoaDon(String maHoaDon, HoaDon hdMoi) {
        HoaDon.Update(maHoaDon, hdMoi);
    }

    public static void xoaHoaDon(String maHoaDon) {
        HoaDon.Delete(maHoaDon);
    }
} 