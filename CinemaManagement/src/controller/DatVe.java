import java.util.ArrayList;

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

    public boolean datVe() {
        String CCCD = kh.getCCCD();
        String maSuatChieu = suatChieu.getMaSuatChieu();

        // Lấy ghế tương ứng
        Ghe ghe = Ghe.getGheByMaGhe(maGhe);
        if (ghe == null) {
            System.out.println("Không tìm thấy ghế!");
            return false;
        }
        
        // Kiểm tra ghế có thuộc suất chiếu này không
        if (!maSuatChieu.equals(ghe.getMaSuatChieu())) {
            System.out.println("Ghế không thuộc suất chiếu này!");
            return false;
        }
        
        // Kiểm tra trạng thái ghế
        if (ghe.getTrangThai() != Ghe.TrangThaiGhe.TRONG) {
            System.out.println("Ghế đã được đặt hoặc không khả dụng!");
            return false;
        }
        
        // Tạo vé mới
        Ve veDat = new Ve(maVe, CCCD, maSuatChieu, maGhe, giaVe);
        Ve.Create(veDat);
        
        // Cập nhật trạng thái ghế thành đã đặt
        ghe.setTrangThai(Ghe.TrangThaiGhe.DA_DAT);
        Ghe.Update(maGhe, ghe);
        
        System.out.println("Đặt vé thành công!");
        return true;
    }
}