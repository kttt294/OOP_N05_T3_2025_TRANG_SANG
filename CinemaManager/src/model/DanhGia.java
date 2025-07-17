import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DanhGia {
    private String maDanhGia;
    private String maKhachHang;
    private String maPhim;
    private int soSao; // từ 0 đến 5
    private String binhLuan;
    private LocalDateTime thoiGian;

    private static List<DanhGia> danhSachDanhGia = new ArrayList<>();

    public DanhGia() {}

    public DanhGia(String maDanhGia, String maKhachHang, String maPhim, int soSao, String binhLuan, LocalDateTime thoiGian) {
        setMaDanhGia(maDanhGia);
        setMaKhachHang(maKhachHang);
        setMaPhim(maPhim);
        setSoSao(soSao);
        setBinhLuan(binhLuan);
        setThoiGian(thoiGian);
    }

    public String getMaDanhGia() { return maDanhGia; }
    public void setMaDanhGia(String maDanhGia) {
        if (maDanhGia == null || maDanhGia.trim().isEmpty())
            throw new IllegalArgumentException("Mã đánh giá không được để trống!");
        this.maDanhGia = maDanhGia.trim();
    }
    public String getMaKhachHang() { return maKhachHang; }
    public void setMaKhachHang(String maKhachHang) {
        if (maKhachHang == null || maKhachHang.trim().isEmpty())
            throw new IllegalArgumentException("Mã khách hàng không được để trống!");
        this.maKhachHang = maKhachHang.trim();
    }
    public String getMaPhim() { return maPhim; }
    public void setMaPhim(String maPhim) {
        if (maPhim == null || maPhim.trim().isEmpty())
            throw new IllegalArgumentException("Mã phim không được để trống!");
        this.maPhim = maPhim.trim();
    }
    public int getSoSao() { return soSao; }
    public void setSoSao(int soSao) {
        if (soSao < 0 || soSao > 5) throw new IllegalArgumentException("Số sao phải từ 0 đến 5!");
        this.soSao = soSao;
    }
    public String getBinhLuan() { return binhLuan; }
    public void setBinhLuan(String binhLuan) {
        this.binhLuan = (binhLuan != null) ? binhLuan.trim() : "";
    }
    public LocalDateTime getThoiGian() { return thoiGian; }
    public void setThoiGian(LocalDateTime thoiGian) {
        this.thoiGian = thoiGian;
    }

    // CRUD static
    public static boolean Create(DanhGia dg) {
        if (dg == null || dg.getMaDanhGia() == null || dg.getMaDanhGia().trim().isEmpty() ||
            dg.getMaKhachHang() == null || dg.getMaKhachHang().trim().isEmpty() ||
            dg.getMaPhim() == null || dg.getMaPhim().trim().isEmpty()) {
            return false;
        }
        if (getDanhGiaByMa(dg.getMaDanhGia()) != null) return false;
        danhSachDanhGia.add(dg);
        return true;
    }
    public static List<DanhGia> Read() {
        return new ArrayList<>(danhSachDanhGia);
    }
    public static boolean Update(DanhGia dg) {
        if (dg == null || dg.getMaDanhGia() == null || dg.getMaDanhGia().trim().isEmpty()) return false;
        int idx = getDanhGiaIndexByMa(dg.getMaDanhGia());
        if (idx == -1) return false;
        danhSachDanhGia.set(idx, dg);
        return true;
    }
    public static boolean Delete(String maDanhGia) {
        int idx = getDanhGiaIndexByMa(maDanhGia);
        if (idx == -1) return false;
        danhSachDanhGia.remove(idx);
        return true;
    }
    public static DanhGia getDanhGiaByMa(String maDanhGia) {
        for (DanhGia dg : danhSachDanhGia) {
            if (dg.getMaDanhGia().equalsIgnoreCase(maDanhGia)) return dg;
        }
        return null;
    }
    public static int getDanhGiaIndexByMa(String maDanhGia) {
        for (int i = 0; i < danhSachDanhGia.size(); i++) {
            if (danhSachDanhGia.get(i).getMaDanhGia().equalsIgnoreCase(maDanhGia)) return i;
        }
        return -1;
    }
}