import java.util.ArrayList;

public class GheSuatChieu {
    private String maGhe;
    private String maSuatChieu;
    private double giaGhe;
    private String trangThai; // "BinhThuong", "Khoa", ...
    private String loaiGhe;   // Có thể khác với mặc định
    private String moTa;

    private static ArrayList<GheSuatChieu> danhSachGheSuatChieu = new ArrayList<>();

    public GheSuatChieu(String maGhe, String maSuatChieu, double giaGhe, String trangThai, String loaiGhe, String moTa) {
        setMaGhe(maGhe);
        setMaSuatChieu(maSuatChieu);
        setGiaGhe(giaGhe);
        setTrangThai(trangThai);
        setLoaiGhe(loaiGhe);
        setMoTa(moTa);
    }

    public String getMaGhe() { return maGhe; }
    public void setMaGhe(String maGhe) {
        if (maGhe == null || maGhe.trim().isEmpty())
            throw new IllegalArgumentException("Mã ghế không được để trống!");
        this.maGhe = maGhe.trim();
    }
    public String getMaSuatChieu() { return maSuatChieu; }
    public void setMaSuatChieu(String maSuatChieu) {
        if (maSuatChieu == null || maSuatChieu.trim().isEmpty())
            throw new IllegalArgumentException("Mã suất chiếu không được để trống!");
        this.maSuatChieu = maSuatChieu.trim();
    }
    public double getGiaGhe() { return giaGhe; }
    public void setGiaGhe(double giaGhe) {
        if (giaGhe < 0) throw new IllegalArgumentException("Giá ghế không hợp lệ!");
        this.giaGhe = giaGhe;
    }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) {
        this.trangThai = (trangThai != null) ? trangThai.trim() : "BinhThuong";
    }
    public String getLoaiGhe() { return loaiGhe; }
    public void setLoaiGhe(String loaiGhe) {
        this.loaiGhe = (loaiGhe != null) ? loaiGhe.trim() : "Thuong";
    }
    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) {
        this.moTa = (moTa != null) ? moTa.trim() : "";
    }

    // CRUD static
    public static boolean Create(GheSuatChieu gsc) {
        if (gsc == null) return false;
        if (getByMaGheAndMaSuatChieu(gsc.getMaGhe(), gsc.getMaSuatChieu()) != null) return false;
        danhSachGheSuatChieu.add(gsc);
        return true;
    }
    public static ArrayList<GheSuatChieu> Read() {
        return new ArrayList<>(danhSachGheSuatChieu);
    }
    public static boolean Update(GheSuatChieu gsc) {
        if (gsc == null) return false;
        int idx = getIndexByMaGheAndMaSuatChieu(gsc.getMaGhe(), gsc.getMaSuatChieu());
        if (idx == -1) return false;
        danhSachGheSuatChieu.set(idx, gsc);
        return true;
    }
    public static boolean Delete(String maGhe, String maSuatChieu) {
        int idx = getIndexByMaGheAndMaSuatChieu(maGhe, maSuatChieu);
        if (idx == -1) return false;
        danhSachGheSuatChieu.remove(idx);
        return true;
    }
    public static GheSuatChieu getByMaGheAndMaSuatChieu(String maGhe, String maSuatChieu) {
        for (GheSuatChieu gsc : danhSachGheSuatChieu) {
            if (gsc.getMaGhe().equalsIgnoreCase(maGhe) && gsc.getMaSuatChieu().equalsIgnoreCase(maSuatChieu))
                return gsc;
        }
        return null;
    }
    public static int getIndexByMaGheAndMaSuatChieu(String maGhe, String maSuatChieu) {
        for (int i = 0; i < danhSachGheSuatChieu.size(); i++) {
            GheSuatChieu gsc = danhSachGheSuatChieu.get(i);
            if (gsc.getMaGhe().equalsIgnoreCase(maGhe) && gsc.getMaSuatChieu().equalsIgnoreCase(maSuatChieu))
                return i;
        }
        return -1;
    }
} 