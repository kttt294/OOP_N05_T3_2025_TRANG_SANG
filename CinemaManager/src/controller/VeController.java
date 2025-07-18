import java.util.ArrayList;

public class VeController {
    // Đặt vé mới
    public static void datVe(Ve ve) {
        Ve.Create(ve);
    }

    // Xem thông tin vé
    public static void xemThongTinVe(String maVe) {
        Ve.Read(maVe);
    }

    // Xem toàn bộ vé
    public static ArrayList<Ve> layTatCaVe() {
        return Ve.Read();
    }

    // Cập nhật vé
    public static void capNhatVe(String maVe, Ve veMoi) {
        Ve.Update(maVe, veMoi);
    }

    // Xóa vé
    public static void xoaVe(String maVe) {
        Ve.Delete(maVe);
    }
}