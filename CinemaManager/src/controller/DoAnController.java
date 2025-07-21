import java.util.ArrayList;

public class DoAnController {
    public static void themDoAn(DoAn doAn) {
        DoAn.Create(doAn);
    }

    public static void xemThongTinDoAn(String maDoAn) {
        DoAn.Read(maDoAn);
    }

    public static ArrayList<DoAn> layTatCaDoAn() {
        return DoAn.Read();
    }

    public static void capNhatDoAn(String maDoAn, DoAn doAnMoi) {
        DoAn.Update(doAnMoi);
    }

    public static void xoaDoAn(String maDoAn) {
        DoAn.Delete(maDoAn);
    }
} 