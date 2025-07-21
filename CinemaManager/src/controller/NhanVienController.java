import java.util.ArrayList;

public class NhanVienController {
    public static void themNhanVien(NhanVien nv) {
        NhanVien.Create(nv);
    }

    public static void xemThongTinNhanVien(String CCCD) {
        NhanVien.Read(CCCD);
    }

    public static ArrayList<NhanVien> layTatCaNhanVien() {
        return NhanVien.Read();
    }

    public static void capNhatNhanVien(String CCCD, NhanVien nvMoi) {
        NhanVien.Update(CCCD, nvMoi);
    }

    public static void xoaNhanVien(String CCCD) {
        NhanVien.Delete(CCCD);
    }
} 