import java.util.ArrayList;
import model.Voucher;

public class VoucherController {
    public static void themVoucher(Voucher voucher) {
        Voucher.Create(voucher);
    }

    public static void xemThongTinVoucher(String maVoucher) {
        Voucher.Read(maVoucher);
    }

    public static ArrayList<Voucher> layTatCaVoucher() {
        return Voucher.Read();
    }

    public static void capNhatVoucher(String maVoucher, Voucher voucherMoi) {
        Voucher.Update(maVoucher, voucherMoi);
    }

    public static void xoaVoucher(String maVoucher) {
        Voucher.Delete(maVoucher);
    }
} 