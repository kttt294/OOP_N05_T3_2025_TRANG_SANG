package com.example.servingwebcontent.model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SuatChieu {
    private String maSuatChieu;
    private String maPhim; // chỉ lưu mã phim
    private String maPhong; // chỉ lưu mã phòng chiếu
    private LocalDateTime thoiGianBatDau;
    private LocalDateTime thoiGianKetThuc;
    private List<Ghe> danhSachGheTrong;

    private static List<SuatChieu> danhSachSuatChieu = new ArrayList<>();

    public SuatChieu() {
    };

    public SuatChieu(String maSuatChieu, String maPhim, String maPhong,
            LocalDateTime thoiGianBatDau, List<Ghe> danhSachGheTrong) {
        this.maSuatChieu = maSuatChieu;
        this.maPhim = maPhim;
        this.maPhong = maPhong;
        this.thoiGianBatDau = thoiGianBatDau;
        this.danhSachGheTrong = danhSachGheTrong;
        this.thoiGianKetThuc = null; // hoặc tính toán nếu cần
    }

    // Getter & Setter
    public String getMaSuatChieu() {
        return maSuatChieu;
    }

    public void setMaSuatChieu(String maSuatChieu) {
        this.maSuatChieu = maSuatChieu;
    }

    public String getMaPhim() {
        return maPhim;
    }

    public void setMaPhim(String maPhim) {
        this.maPhim = maPhim;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public LocalDateTime getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(LocalDateTime thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public LocalDateTime getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(LocalDateTime thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    public List<Ghe> getDanhSachGheTrong() {
        return danhSachGheTrong;
    }

    public void setDanhSachGheTrong(List<Ghe> danhSachGheTrong) {
        this.danhSachGheTrong = danhSachGheTrong;
    }

    // Hiển thị thông tin suất chiếu
    public void hienThiThongTin() {
        System.out.println("=== THÔNG TIN SUẤT CHIẾU ===");
        System.out.println("Mã suất chiếu: " + this.maSuatChieu);
        System.out.println("Mã phim: " + this.maPhim);
        System.out.println("Mã phòng chiếu: " + this.maPhong);
        System.out.println("Thời gian bắt đầu: " + this.thoiGianBatDau);
        System.out.println("Thời gian kết thúc: " + this.thoiGianKetThuc);
        System.out.println("Số ghế trống: " + (this.danhSachGheTrong != null ? this.danhSachGheTrong.size() : 0));
        System.out.println("============================");
    }

    // Tính thời gian kết thúc dựa trên thời lượng phim
    public LocalDateTime tinhThoiGianKetThuc() {
        Phim phim = Phim.getPhimById(maPhim);
        if (phim != null && thoiGianBatDau != null) {
            return thoiGianBatDau.plusMinutes(phim.getThoiLuong());
        }
        return null;
    }

    // Cập nhật danh sách ghế sau khi đặt
    public void capNhatGheTrong(Ghe gheDaDat) {
        danhSachGheTrong.remove(gheDaDat);
    }

    // CRUD

    public static void Create(SuatChieu scObj) {
        if (scObj.getMaSuatChieu() == null || scObj.getMaSuatChieu().trim().isEmpty() ||
            scObj.getMaPhim() == null || scObj.getMaPhong() == null ||
            scObj.getThoiGianBatDau() == null) {
            System.out.println("Lỗi: Thông tin suất chiếu không được để trống.");
            return;
        }
        danhSachSuatChieu.add(scObj);
        System.out.println("Thêm suất chiếu thành công.");
    }

    // Read toàn bộ danh sách suất chiếu
    public static List<SuatChieu> Read() {
        if (danhSachSuatChieu.isEmpty()) {
            System.out.println("Danh sách suất chiếu trống.");
            return new ArrayList<>();
        }
        System.out.println("Tổng số suất chiếu: " + danhSachSuatChieu.size());
        return new ArrayList<>(danhSachSuatChieu);
    }
    
    // Read suất chiếu theo mã
    public static void Read(String maSuatChieu) {
        if (danhSachSuatChieu.isEmpty()) {
            System.out.println("Danh sách suất chiếu trống.");
            return;
        }
            SuatChieu p = getSuatChieuById(maSuatChieu);
            if (p != null) {
                p.hienThiThongTin();
            } else {
            System.out.println("Không tìm thấy suất chiếu với mã: " + maSuatChieu);
        }
    }

    public static void Update(String maSuatChieu, SuatChieu scObj) {
        if (scObj.getMaSuatChieu() == null || scObj.getMaSuatChieu().trim().isEmpty() ||
            scObj.getMaPhim() == null || scObj.getMaPhong() == null ||
            scObj.getThoiGianBatDau() == null) {
            System.out.println("Lỗi: Thông tin suất chiếu không được để trống.");
            return;
        }
        int index = getSuatChieuIndexById(maSuatChieu);
        if (index != -1) {
            scObj.setMaSuatChieu(maSuatChieu);
            danhSachSuatChieu.set(index, scObj);
            System.out.println("Cập nhật thông tin suất chiếu thành công.");
        } else {
            System.out.println("Không tìm thấy suất chiếu với mã đã nhập.");
        }
    }

    public static void Delete(String maSuatChieu) {
        int index = getSuatChieuIndexById(maSuatChieu);
        if (index != -1) {
            danhSachSuatChieu.remove(index);
            System.out.println("Đã xoá suất chiếu thành công.");
        } else {
            System.out.println("Không tìm thấy suất chiếu với mã đã nhập.");
        }
    }

    public static SuatChieu getSuatChieuById(String maSuatChieu) {
        for (SuatChieu sc : danhSachSuatChieu) {
            if (sc.getMaSuatChieu().equalsIgnoreCase(maSuatChieu)) {
                return sc;
            }
        }
        return null;
    }

    public static ArrayList<SuatChieu> getSuatChieuByPhim(String maPhim) {
        ArrayList<SuatChieu> ketQua = new ArrayList<>();
        for (SuatChieu sc : danhSachSuatChieu) {
            if (sc.getMaPhim().equalsIgnoreCase(maPhim)) {
                ketQua.add(sc);
            }
        }
        return ketQua;
    }

    public static ArrayList<SuatChieu> getSuatChieuByPhong(String maPhong) {
        ArrayList<SuatChieu> ketQua = new ArrayList<>();
        for (SuatChieu sc : danhSachSuatChieu) {
            if (sc.getMaPhong().equalsIgnoreCase(maPhong)) {
                ketQua.add(sc);
            }
        }
        return ketQua;
    }

    private static int getSuatChieuIndexById(String maSuatChieu) {
        for (int i = 0; i < danhSachSuatChieu.size(); i++) {
            if (danhSachSuatChieu.get(i).getMaSuatChieu().equalsIgnoreCase(maSuatChieu)) {
                return i;
            }
        }
        return -1;
    }
}
