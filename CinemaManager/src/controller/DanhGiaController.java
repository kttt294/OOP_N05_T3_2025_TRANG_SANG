import java.util.List;
import model.DanhGia;

public class DanhGiaController {
    public static void themDanhGia(DanhGia dg) {
        DanhGia.Create(dg);
    }

    public static void xemThongTinDanhGia(String maDanhGia) {
        DanhGia.Read(maDanhGia);
    }

    public static List<DanhGia> layTatCaDanhGia() {
        return DanhGia.Read();
    }

    public static void capNhatDanhGia(String maDanhGia, DanhGia dgMoi) {
        DanhGia.Update(maDanhGia, dgMoi);
    }

    public static void xoaDanhGia(String maDanhGia) {
        DanhGia.Delete(maDanhGia);
    }
} 