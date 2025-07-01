package model;

public class Ve {
    String maVe;
    KhachHang khachHang;
    SuatChieu suatChieu;
    Ghe ghe;
    double giaVe;
    boolean daThanhToan;

    public Ve(){};
    
    public Ve(String maVe, KhachHang khachHang, SuatChieu suatChieu, Ghe ghe, double giaVe, boolean daThanhToan) {
        this.maVe = maVe;
        this.khachHang = khachHang;
        this.suatChieu = suatChieu;
        this.ghe = ghe;
        this.giaVe = giaVe;
        this.daThanhToan = daThanhToan;
    }

    // Getter & Setter
    public String getMaVe() {
        return maVe;
    }

    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public SuatChieu getSuatChieu() {
        return suatChieu;
    }

    public void setSuatChieu(SuatChieu suatChieu) {
        this.suatChieu = suatChieu;
    }

    public Ghe getGhe() {
        return ghe;
    }

    public void setGhe(Ghe ghe) {
        this.ghe = ghe;
    }

    public double getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(double giaVe) {
        this.giaVe = giaVe;
    }

    public boolean isDaThanhToan() {
        return daThanhToan;
    }

    public void setDaThanhToan(boolean daThanhToan) {
        this.daThanhToan = daThanhToan;
    }

    // Phương thức đánh dấu đã thanh toán
    public void xacNhanThanhToan() {
        this.daThanhToan = true;
    }

    // Hiển thị thông tin vé
    public void hienThiThongTin() {
        System.out.println("Mã vé: " + maVe);
        System.out.println("Khách hàng: " + khachHang.getTenKH());
        System.out.println("Phim: " + suatChieu.getPhim().getTenPhim());
        System.out.println("Phòng: " + suatChieu.getPhongChieu().getTenPhong());
        System.out.println("Ghế: " + ghe.getMaGhe());
        System.out.println("Thời gian: " + suatChieu.getThoiGianBatDau());
        System.out.println("Giá vé: " + giaVe + " VND");
        System.out.println("Trạng thái: " + (daThanhToan ? "Đã thanh toán" : "Chưa thanh toán"));
    }
}
