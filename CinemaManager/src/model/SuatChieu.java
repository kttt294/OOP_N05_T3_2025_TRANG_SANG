import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SuatChieu {
    private String maSuatChieu;
    private Phim phim;
    private PhongChieu phongChieu;
    private LocalDateTime thoiGianBatDau;
    private LocalDateTime thoiGianKetThuc;
    private List<Ghe> danhSachGheTrong;

    public SuatChieu(){};

    public SuatChieu(String maSuatChieu, Phim phim, PhongChieu phongChieu,
                     LocalDateTime thoiGianBatDau, List<Ghe> danhSachGheTrong) {
        this.maSuatChieu = maSuatChieu;
        this.phim = phim;
        this.phongChieu = phongChieu;
        this.thoiGianBatDau = thoiGianBatDau;
        this.danhSachGheTrong = danhSachGheTrong;
        this.thoiGianKetThuc = tinhThoiGianKetThuc();
    }

    // Getter & Setter
    public String getMaSuatChieu() {
        return maSuatChieu;
    }

    public void setMaSuatChieu(String maSuatChieu) {
        this.maSuatChieu = maSuatChieu;
    }

    public Phim getPhim() {
        return phim;
    }

    public void setPhim(Phim phim) {
        this.phim = phim;
    }

    public PhongChieu getPhongChieu() {
        return phongChieu;
    }

    public void setPhongChieu(PhongChieu phongChieu) {
        this.phongChieu = phongChieu;
    }

    public LocalDateTime getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(LocalDateTime thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = tinhThoiGianKetThuc();
    }

    public LocalDateTime getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public List<Ghe> getDanhSachGheTrong() {
        return danhSachGheTrong;
    }

    public void setDanhSachGheTrong(List<Ghe> danhSachGheTrong) {
        this.danhSachGheTrong = danhSachGheTrong;
    }

    // Tính thời gian kết thúc dựa trên thời lượng phim
    public LocalDateTime tinhThoiGianKetThuc() {
        if (phim != null && thoiGianBatDau != null) {
            return thoiGianBatDau.plusMinutes(phim.getThoiLuong());
        }
        return null;
    }

    // Cập nhật danh sách ghế sau khi đặt
    public void capNhatGheTrong(Ghe gheDaDat) {
        danhSachGheTrong.remove(gheDaDat);
    }

    private static List<SuatChieu> danhSachSuatChieu = new ArrayList<>();


    // CRUD

    public static void createSuatChieu(SuatChieu scObj) {
        danhSachSuatChieu.add(scObj);
        System.out.println("Thêm suất chiếu thành công.");
    }

    public static void readSuatChieu() {
        if (danhSachSuatChieu.isEmpty()) {
            System.out.println("Danh sách suất chiếu trống.");
            return;
        }
        System.out.println("\n=== Danh sách suất chiếu ===");
        for (SuatChieu sc : danhSachSuatChieu) {
            System.out.println("Mã suất chiếu: " + sc.getMaSuatChieu());
            System.out.println("Phim: " + sc.getPhim().getTenPhim());
            System.out.println("Phòng chiếu: " + sc.getPhongChieu().getTenPhong());
            System.out.println("Thời gian bắt đầu: " + sc.getThoiGianBatDau());
            System.out.println("Thời gian kết thúc: " + sc.getThoiGianKetThuc());
            System.out.println("-----------------------------");
        }
    }

    public static void updateSuatChieu(String maSuatChieu, SuatChieu scObj) {
        for (int i = 0; i < danhSachSuatChieu.size(); i++) {
            if (danhSachSuatChieu.get(i).getMaSuatChieu().equalsIgnoreCase(maSuatChieu)) {
                danhSachSuatChieu.set(i, scObj);
                System.out.println("Cập nhật thông tin suất chiếu thành công.");
                return;
            }
        }
        System.out.println("Không tìm thấy suất chiếu với mã đã nhập.");

    }

    public static void deleteSuatChieu(String maSuatChieu) {
        for( SuatChieu sc : danhSachSuatChieu) {
            if (sc.getMaSuatChieu().equalsIgnoreCase(maSuatChieu)) {
                danhSachSuatChieu.remove(sc);
                System.out.println("Đã xoá suất chiếu thành công.");
            }
        }
    }

    public static SuatChieu getSuatChieuById(String maSuatChieu) {
        return danhSachSuatChieu.stream()
                .filter(sc -> sc.getMaSuatChieu().equalsIgnoreCase(maSuatChieu))
                .findFirst()
                .orElse(null);
    }
}
