import java.time.LocalDateTime;
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

    // Hiển thị thông tin suất chiếu
    public void hienThiThongTin() {
        System.out.println("Mã suất chiếu: " + maSuatChieu);
        System.out.println("Phim: " + phim.getTenPhim());
        System.out.println("Phòng chiếu: " + phongChieu.getTenPhong());
        System.out.println("Bắt đầu: " + thoiGianBatDau);
        System.out.println("Kết thúc: " + thoiGianKetThuc);
        System.out.println("Số ghế còn trống: " + danhSachGheTrong.size());
    }

    // Cập nhật danh sách ghế sau khi đặt
    public void capNhatGheTrong(Ghe gheDaDat) {
        danhSachGheTrong.remove(gheDaDat);
    }
}
