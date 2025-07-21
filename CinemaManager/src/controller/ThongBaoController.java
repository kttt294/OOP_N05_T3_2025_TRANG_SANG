import java.util.ArrayList;

public class ThongBaoController {
    public static void themThongBao(ThongBao tb) {
        ThongBao.Create(tb);
    }

    public static void xemThongTinThongBao(String maThongBao) {
        ThongBao.Read(maThongBao);
    }

    public static ArrayList<ThongBao> layTatCaThongBao() {
        return ThongBao.Read();
    }

    public static void capNhatThongBao(String maThongBao, ThongBao tbMoi) {
        ThongBao.Update(maThongBao, tbMoi);
    }

    public static void xoaThongBao(String maThongBao) {
        ThongBao.Delete(maThongBao);
    }
}