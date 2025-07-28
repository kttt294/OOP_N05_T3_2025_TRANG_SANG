import java.util.ArrayList;

public class GheController {
    public static void themGhe(Ghe ghe) {
        Ghe.Create(ghe);
    }

    public static void xemThongTinGhe(String maGhe) {
        Ghe.Read(maGhe);
    }

    public static ArrayList<Ghe> layTatCaGhe() {
        return Ghe.Read();
    }

    public static void capNhatGhe(String maGhe, Ghe gheMoi) {
        Ghe.Update(maGhe, gheMoi);
    }

    public static void xoaGhe(String maGhe) {
        Ghe.Delete(maGhe);
    }
} 