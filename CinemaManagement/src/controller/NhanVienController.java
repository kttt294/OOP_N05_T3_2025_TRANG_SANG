import java.util.ArrayList;

public class NhanVienController {
    public static void themNhanVien(NhanVien nv) {
        User.Create(nv);
    }

    public static void xemThongTinNhanVien(String CCCD) {
        User.Read(CCCD);
    }

    public static ArrayList<User> layTatCaNhanVien() {
        ArrayList<User> allUsers = User.Read();
        ArrayList<User> nhanViens = new ArrayList<>();
        for (User user : allUsers) {
            if (user.isNhanVien()) {
                nhanViens.add(user);
            }
        }
        return nhanViens;
    }

    public static void capNhatNhanVien(String CCCD, NhanVien nvMoi) {
        User.Update(CCCD, nvMoi);
    }

    public static void xoaNhanVien(String CCCD) {
        User.Delete(CCCD);
    }
} 