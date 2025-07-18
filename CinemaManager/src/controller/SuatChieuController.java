import java.util.List;
import model.SuatChieu;

public class SuatChieuController {
    // Thêm suất chiếu mới
    public static void themSuatChieu(SuatChieu sc) {
        SuatChieu.Create(sc);
    }

    // Xem thông tin suất chiếu
    public static void xemThongTinSuatChieu(String maSuatChieu) {
        SuatChieu.Read(maSuatChieu);
    }

    // Lấy toàn bộ suất chiếu
    public static List<SuatChieu> layTatCaSuatChieu() {
        return SuatChieu.Read();
    }

    // Cập nhật suất chiếu
    public static void capNhatSuatChieu(String maSuatChieu, SuatChieu scMoi) {
        SuatChieu.Update(maSuatChieu, scMoi);
    }

    // Xóa suất chiếu
    public static void xoaSuatChieu(String maSuatChieu) {
        SuatChieu.Delete(maSuatChieu);
    }
} 