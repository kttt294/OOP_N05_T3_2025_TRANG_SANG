import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class DanhGia {
    private String maDanhGia;
    private KhachHang nguoiDanhGia;
    private Phim phim;
    private int soSao; // từ 0 đến 5
    private String binhLuan;
    private LocalDateTime thoiGian;

    public DanhGia() {
    };

    public DanhGia(String maDanhGia, KhachHang nguoiDanhGia, Phim phim, int soSao, String binhLuan,
            LocalDateTime thoiGian) {
        this.maDanhGia = maDanhGia;
        this.nguoiDanhGia = nguoiDanhGia;
        this.phim = phim;
        this.soSao = soSao;
        this.binhLuan = binhLuan;
        this.thoiGian = thoiGian;
    }

    public String getMaDanhGia() {
        return maDanhGia;
    }

    public void setMaDanhGia(String maDanhGia) {
        this.maDanhGia = maDanhGia;
    }

    public KhachHang getNguoiDanhGia() {
        return nguoiDanhGia;
    }

    public void setNguoiDanhGia(KhachHang nguoiDanhGia) {
        this.nguoiDanhGia = nguoiDanhGia;
    }

    public Phim getPhim() {
        return phim;
    }

    public void setPhim(Phim phim) {
        this.phim = phim;
    }

    public int getSoSao() {
        return soSao;
    }

    public void setSoSao(int soSao) {
        this.soSao = soSao;
    }

    public String getBinhLuan() {
        return binhLuan;
    }

    public void setBinhLuan(String binhLuan) {
        this.binhLuan = binhLuan;
    }

    public LocalDateTime getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(LocalDateTime thoiGian) {
        this.thoiGian = thoiGian;
    }

    private static List<DanhGia> danhSachDanhGia = new ArrayList<>();

    // CRUD

    public static void createDanhGia(DanhGia danhGia){
        danhSachDanhGia.add(danhGia);
        System.out.println("Đã thêm đánh giá thành công.");
    }

}