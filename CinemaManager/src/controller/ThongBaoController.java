import java.util.ArrayList;

public class ThongBaoController {
    public static void themThongBao(Notification tb) {
        Notification.Create(tb);
    }

    public static void xemThongTinThongBao(String maThongBao) {
        Notification.Read(maThongBao);
    }

    public static ArrayList<Notification> layTatCaThongBao() {
        return Notification.Read();
    }

    public static void capNhatThongBao(String maThongBao, Notification tbMoi) {
        Notification.Update(maThongBao, tbMoi);
    }

    public static void xoaThongBao(String maThongBao) {
        Notification.Delete(maThongBao);
    }
}