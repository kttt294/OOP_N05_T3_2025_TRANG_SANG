import java.util.ArrayList;
import java.util.Objects;

public class Ghe {
    public enum LoaiGhe { THUONG, VIP, COUPLE }

    private String maGhe;
    private int hang;
    private int cot;
    private LoaiGhe loaiGheMacDinh;
    private String maPhong;

    private static ArrayList<Ghe> danhSachGhe = new ArrayList<>();

    public Ghe() {}

    public Ghe(String maGhe, int hang, int cot, LoaiGhe loaiGheMacDinh, String maPhong) {
        setMaGhe(maGhe);
        setHang(hang);
        setCot(cot);
        setLoaiGheMacDinh(loaiGheMacDinh);
        setMaPhong(maPhong);
    }

    public String getMaGhe() { return maGhe; }
    public void setMaGhe(String maGhe) {
        if (maGhe == null || maGhe.trim().isEmpty())
            throw new IllegalArgumentException("Mã ghế không được để trống!");
        this.maGhe = maGhe.trim();
    }
    public int getHang() { return hang; }
    public void setHang(int hang) {
        if (hang < 0) throw new IllegalArgumentException("Hàng ghế không hợp lệ!");
        this.hang = hang;
    }
    public int getCot() { return cot; }
    public void setCot(int cot) {
        if (cot < 0) throw new IllegalArgumentException("Cột ghế không hợp lệ!");
        this.cot = cot;
    }
    public LoaiGhe getLoaiGheMacDinh() { return loaiGheMacDinh; }
    public void setLoaiGheMacDinh(LoaiGhe loaiGheMacDinh) {
        this.loaiGheMacDinh = (loaiGheMacDinh != null) ? loaiGheMacDinh : LoaiGhe.THUONG;
    }
    public String getMaPhong() { return maPhong; }
    public void setMaPhong(String maPhong) {
        this.maPhong = (maPhong != null) ? maPhong.trim() : "";
    }

    // CRUD static giữ nguyên
    public static boolean Create(Ghe ghe) {
        if (ghe == null) return false;
        if (getGheByMaGhe(ghe.getMaGhe()) != null) return false;
        danhSachGhe.add(ghe);
        return true;
    }
    public static ArrayList<Ghe> Read() {
        return new ArrayList<>(danhSachGhe);
    }
    public static boolean Update(Ghe ghe) {
        if (ghe == null) return false;
        int idx = getGheIndexByMaGhe(ghe.getMaGhe());
        if (idx == -1) return false;
        danhSachGhe.set(idx, ghe);
        return true;
    }
    public static boolean Delete(String maGhe) {
        int idx = getGheIndexByMaGhe(maGhe);
        if (idx == -1) return false;
        danhSachGhe.remove(idx);
        return true;
    }
    public static Ghe getGheByMaGhe(String maGhe) {
        for (Ghe ghe : danhSachGhe) {
            if (ghe.getMaGhe().equalsIgnoreCase(maGhe)) return ghe;
        }
        return null;
    }
    public static int getGheIndexByMaGhe(String maGhe) {
        for (int i = 0; i < danhSachGhe.size(); i++) {
            if (danhSachGhe.get(i).getMaGhe().equalsIgnoreCase(maGhe)) return i;
        }
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ghe)) return false;
        Ghe ghe = (Ghe) o;
        return Objects.equals(maGhe, ghe.maGhe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maGhe);
    }
}
