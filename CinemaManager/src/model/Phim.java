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
    };

    public Phim(String maPhim, String tenPhim, String theLoai, int thoiLuong,
            String ngonNgu, int gioiHanTuoi, String moTa) {
        this.maPhim = maPhim;
        this.tenPhim = tenPhim;
        this.theLoai = theLoai;
        this.thoiLuong = thoiLuong;
        this.ngonNgu = ngonNgu;
        this.gioiHanTuoi = gioiHanTuoi;
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
        this.thoiLuong = thoiLuong;
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
        this.gioiHanTuoi = gioiHanTuoi;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    // Biến static để dùng chung cho tất cả các phương thức static
    private static ArrayList<Phim> danhSachPhim = new ArrayList<>();

    // CRUD

    public static void createPhim(Phim phim) {
        danhSachPhim.add(phim);
        System.out.println("Đã thêm phim thành công.");
    }

    public static void readPhim(String maPhim) {
        if (danhSachPhim.isEmpty()) {
            System.out.println("Danh sách phim trống.");
        } else {
            boolean check = true;
            for (Phim p : danhSachPhim) {
                if (p.getMaPhim().equalsIgnoreCase(maPhim)) {
                    System.out.println("Mã phim: " + maPhim);
                    System.out.println("Tên phim: " + p.getTenPhim());
                    System.out.println("Thể loại: " + p.getTheLoai());
                    System.out.println("Thời lượng: " + p.getThoiLuong());
                    System.out.println("Ngôn ngữ: " + p.getNgonNgu());
                    System.out.println("Giới hạn tuổi: " + p.getGioiHanTuoi());
                    System.out.println("Mô tả: " + p.getMoTa());
                    break;
                }
            }
            if (check)
                System.out.println("Không tìm thấy mã phim cần tìm!");
        }
    }

    public static void updatePhim(String maPhim, Phim phim) {
        boolean check = true;
        for (int i = 0; i < danhSachPhim.size(); i++) {
            if (danhSachPhim.get(i).getMaPhim() == maPhim) {
                phim.setMaPhim(maPhim);
                danhSachPhim.set(i, phim);
                System.out.println("Cập nhật thông tin phim thành công.");
                check = false;
                break;
            }
        }
        if (check)
            System.out.println("Không tìm thấy phim với mã đã nhập.");
    }

    public static void deletePhim(String maPhim) {
        boolean check = true;
        for (Phim p : danhSachPhim) {
            if (p.getMaPhim().equalsIgnoreCase(maPhim)) {
                danhSachPhim.remove(p);
                System.out.println("Xoá phim thành công.");
                check = false;
            }
        }
        if (check)
            System.out.println("Không tìm thấy phim với mã đã nhập.");
    }

    public static Phim getPhimById(String maPhim) {
        return danhSachPhim.stream()
                .filter(p -> p.getMaPhim().equalsIgnoreCase(maPhim))
                .findFirst()
                .orElse(null);
    }
}
