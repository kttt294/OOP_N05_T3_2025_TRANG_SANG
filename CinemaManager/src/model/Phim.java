import java.util.ArrayList;

public class Phim {
    private String maPhim;
    private String tenPhim;
    private String theLoai;
    private int thoiLuong; // đơn vị: phút
    private String ngonNgu;
    private int gioiHanTuoi;
    private String moTa = "";

    public Phim() {
    }

    public Phim(String maPhim, String tenPhim, String theLoai, int thoiLuong,
            String ngonNgu, int gioiHanTuoi, String moTa) {
        this.maPhim = maPhim;
        this.tenPhim = tenPhim;
        this.theLoai = theLoai;
        setThoiLuong(thoiLuong);
        this.ngonNgu = ngonNgu;
        setGioiHanTuoi(gioiHanTuoi);
        this.moTa = moTa;
    }

    public String getMaPhim() {
        return maPhim;
    }

    public void setMaPhim(String maPhim) {
        this.maPhim = maPhim;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public int getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(int thoiLuong) {
        if (thoiLuong > 0) this.thoiLuong = thoiLuong;
        else throw new IllegalArgumentException("Thời lượng phải lớn hơn 0");
    }

    public String getNgonNgu() {
        return ngonNgu;
    }

    public void setNgonNgu(String ngonNgu) {
        this.ngonNgu = ngonNgu;
    }

    public int getGioiHanTuoi() {
        return gioiHanTuoi;
    }

    public void setGioiHanTuoi(int gioiHanTuoi) {
        if (gioiHanTuoi >= 0) this.gioiHanTuoi = gioiHanTuoi;
        else throw new IllegalArgumentException("Giới hạn tuổi phải lớn hơn hoặc bằng 0");
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    private static ArrayList<Phim> danhSachPhim = new ArrayList<>();

    // CRUD
    public static void Create(Phim phim) {
        if (phim.getMaPhim() == null || phim.getMaPhim().trim().isEmpty() ||
            phim.getTenPhim() == null || phim.getTenPhim().trim().isEmpty() ||
            phim.getTheLoai() == null || phim.getTheLoai().trim().isEmpty() ||
            phim.getNgonNgu() == null || phim.getNgonNgu().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin phim không được để trống.");
            return;
        }
        danhSachPhim.add(phim);
        System.out.println("Đã thêm phim thành công.");
    }

    public static void Read(String maPhim) {
        if (danhSachPhim.isEmpty()) {
            System.out.println("Danh sách phim trống.");
        } else {
            Phim p = getPhimById(maPhim);
            if (p != null) {
                System.out.println("Mã phim: " + p.getMaPhim());
                System.out.println("Tên phim: " + p.getTenPhim());
                System.out.println("Thể loại: " + p.getTheLoai());
                System.out.println("Thời lượng: " + p.getThoiLuong());
                System.out.println("Ngôn ngữ: " + p.getNgonNgu());
                System.out.println("Giới hạn tuổi: " + p.getGioiHanTuoi());
                System.out.println("Mô tả: " + p.getMoTa());
            } else {
                System.out.println("Không tìm thấy mã phim cần tìm!");
            }
        }
    }

    public static void Update(String maPhim, Phim phim) {
        if (phim.getMaPhim() == null || phim.getMaPhim().trim().isEmpty() ||
            phim.getTenPhim() == null || phim.getTenPhim().trim().isEmpty() ||
            phim.getTheLoai() == null || phim.getTheLoai().trim().isEmpty() ||
            phim.getNgonNgu() == null || phim.getNgonNgu().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin phim không được để trống.");
            return;
        }
        int index = getPhimIndexById(maPhim);
        if (index != -1) {
            phim.setMaPhim(maPhim);
            danhSachPhim.set(index, phim);
            System.out.println("Cập nhật thông tin phim thành công.");
        } else {
            System.out.println("Không tìm thấy phim với mã đã nhập.");
        }
    }

    public static void Delete(String maPhim) {
        Phim p = getPhimById(maPhim);
        if (p != null) {
            danhSachPhim.remove(p);
            System.out.println("Xoá phim thành công.");
        } else {
            System.out.println("Không tìm thấy phim với mã đã nhập.");
        }
    }

    public static Phim getPhimById(String maPhim) {
        return danhSachPhim.stream()
                .filter(p -> p.getMaPhim().equalsIgnoreCase(maPhim))
                .findFirst()
                .orElse(null);
    }

    private static int getPhimIndexById(String maPhim) {
        for (int i = 0; i < danhSachPhim.size(); i++) {
            if (danhSachPhim.get(i).getMaPhim().equalsIgnoreCase(maPhim)) {
                return i;
            }
        }
        return -1;
    }
}
