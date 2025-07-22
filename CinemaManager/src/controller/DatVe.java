public class DatVe {

    KhachHang kh;
    SuatChieu suatChieu;
    String maVe;
    String maGhe;
    int giaVe;

    public DatVe(KhachHang kh, SuatChieu suatChieu, String maVe, String maGhe, int giaVe){
        this.kh = kh;
        this.suatChieu = suatChieu;
        this.maVe = maVe;
        this.maGhe = maGhe;
        this.giaVe = giaVe;
    }

    // Đặt vé
    String CCCD = kh.getCCCD();
    String maSuatChieu = suatChieu.getMaSuatChieu();
    Ve veDat = new Ve(maVe, CCCD, maSuatChieu, maGhe, giaVe, true);
    public static void KhachHangDatVe(Ve veDat) {
        Ve.Create(veDat);
    }
}