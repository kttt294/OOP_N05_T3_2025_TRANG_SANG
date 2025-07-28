import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


public class testDatVe {
    public static void test() {
        // Tạo khách hàng
        KhachHang kh = new KhachHang("123456789", "Nguyen Van A", 25, "0123456789", "a@gmail.com", "Nam", new ArrayList<>());
        KhachHang.Create(kh);

        // Tạo ghế
        Ghe g1 = new Ghe("G01", 1, 1, Ghe.LoaiGhe.THUONG, "PH01");
        Ghe g2 = new Ghe("G02", 1, 2, Ghe.LoaiGhe.VIP, "PH01");
        List<Ghe> danhSachGhe = Arrays.asList(g1, g2);

        // Tạo phim
        Phim phim = new Phim("P01", "Avengers", "Hanh Dong", 120, "Tieng Viet", 13, "Phim sieu anh hung");
        Phim.Create(phim);

        // Tạo phòng chiếu
        PhongChieu phong = new PhongChieu("PH01", "Phong 1", 5, 5);
        PhongChieu.Create(phong);

        // Tạo suất chiếu
        SuatChieu suatChieu = new SuatChieu("SC01", "P01", "PH01", LocalDateTime.of(2025, 7, 22, 14, 0), danhSachGhe);
        SuatChieu.Create(suatChieu);

        // Tạo ghế suất chiếu (tương ứng với ghế G01 trong suất chiếu SC01)
        Ghe gsc = new Ghe("G01", 1, 1, Ghe.LoaiGhe.THUONG, "PH01", "SC01", 50000, Ghe.TrangThaiGhe.TRONG, "Thuong", "");
        Ghe.Create(gsc);

        // Tạo đối tượng DatVe
        DatVe datVe = new DatVe(kh, suatChieu, "VE01", "G01", 50000);
        boolean ketQua = datVe.datVe();
        System.out.println("Kết quả đặt vé: " + (ketQua ? "Thành công" : "Thất bại"));

        // Kiểm tra lại trạng thái ghế suất chiếu
        Ghe gscCheck = Ghe.getGheByMaGhe("G01");
        if (gscCheck != null) {
            System.out.println("Trạng thái ghế sau khi đặt: " + gscCheck.getTrangThai());
        }

        // Kiểm tra vé đã được tạo chưa
        Ve ve = Ve.getVeById("VE01");
        if (ve != null) {
            ve.hienThiThongTin();
        } else {
            System.out.println("Không tìm thấy vé với mã VE01");
        }
    }
}