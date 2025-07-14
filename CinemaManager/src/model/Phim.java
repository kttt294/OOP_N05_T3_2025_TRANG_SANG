public class Phim {
    private String maPhim;
    private String tenPhim;
    private String theLoai;
    private int thoiLuong;   // đơn vị: phút
    private String ngonNgu;
    private int gioiHanTuoi;
    private String moTa;

    public Phim() {};

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

    public void hienThiThongTin() {
        System.out.println("Mã phim: " + maPhim);
        System.out.println("Tên phim: " + tenPhim);
        System.out.println("Thể loại: " + theLoai);
        System.out.println("Thời lượng: " + thoiLuong + " phút");
        System.out.println("Ngôn ngữ: " + ngonNgu);
        System.out.println("Giới hạn tuổi: " + gioiHanTuoi + "+");
        System.out.println("Mô tả: " + moTa);
    }

    public boolean kiemTraDoTuoi(int tuoiNguoiXem) {
        return tuoiNguoiXem >= gioiHanTuoi;
    }
}
