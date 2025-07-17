import java.time.LocalDateTime;
import java.util.ArrayList;

public class Voucher {
    private String maVoucher;
    private String moTa;
    private float phanTramGiamGia;
    private LocalDateTime ngayBatDau;
    private LocalDateTime ngayKetThuc;
    private String soLuongConLai;
    private String trangThai;

    private static ArrayList<Voucher> danhSachVoucher = new ArrayList<>();

    public Voucher() {}

    public Voucher(String maVoucher, String moTa, float phanTramGiamGia,
                   LocalDateTime ngayBatDau, LocalDateTime ngayKetThuc,
                   String soLuongConLai, String trangThai) {
        this.maVoucher = maVoucher;
        this.moTa = moTa;
        setPhanTramGiamGia(phanTramGiamGia);
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.soLuongConLai = soLuongConLai;
        this.trangThai = trangThai;
    }

    public String getMaVoucher() {
        return maVoucher;
    }

    public void setMaVoucher(String maVoucher) {
        this.maVoucher = maVoucher;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public float getPhanTramGiamGia() {
        return phanTramGiamGia;
    }

    public void setPhanTramGiamGia(float phanTramGiamGia) {
        if (phanTramGiamGia > 0) this.phanTramGiamGia = phanTramGiamGia;
        else throw new IllegalArgumentException("Phần trăm giảm giá phải lớn hơn 0");
    }

    public LocalDateTime getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDateTime ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public LocalDateTime getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDateTime ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getSoLuongConLai() {
        return soLuongConLai;
    }

    public void setSoLuongConLai(String soLuongConLai) {
        this.soLuongConLai = soLuongConLai;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    // CRUD
    public static void Create(Voucher voucher) {
        if (voucher.getMaVoucher() == null || voucher.getMaVoucher().trim().isEmpty() ||
            voucher.getMoTa() == null || voucher.getMoTa().trim().isEmpty() ||
            voucher.getNgayBatDau() == null || voucher.getNgayKetThuc() == null ||
            voucher.getSoLuongConLai() == null || voucher.getSoLuongConLai().trim().isEmpty() ||
            voucher.getTrangThai() == null || voucher.getTrangThai().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin voucher không được để trống.");
            return;
        }
        if (voucher.getPhanTramGiamGia() <= 0) {
            System.out.println("Lỗi: Phần trăm giảm giá phải lớn hơn 0.");
            return;
        }
        danhSachVoucher.add(voucher);
        System.out.println("Đã thêm voucher thành công.");
    }

    public static void Read(String maVoucher) {
        if (danhSachVoucher.isEmpty()) {
            System.out.println("Danh sách voucher trống.");
        } else {
            Voucher v = getVoucherById(maVoucher);
            if (v != null) {
                System.out.println("Mã voucher: " + v.getMaVoucher());
                System.out.println("Mô tả: " + v.getMoTa());
                System.out.println("Phần trăm giảm giá: " + v.getPhanTramGiamGia());
                System.out.println("Ngày bắt đầu: " + v.getNgayBatDau());
                System.out.println("Ngày kết thúc: " + v.getNgayKetThuc());
                System.out.println("Số lượng còn lại: " + v.getSoLuongConLai());
                System.out.println("Trạng thái: " + v.getTrangThai());
            } else {
                System.out.println("Không tìm thấy mã voucher cần tìm!");
            }
        }
    }

    public static void Update(String maVoucher, Voucher voucher) {
        if (voucher.getMaVoucher() == null || voucher.getMaVoucher().trim().isEmpty() ||
            voucher.getMoTa() == null || voucher.getMoTa().trim().isEmpty() ||
            voucher.getNgayBatDau() == null || voucher.getNgayKetThuc() == null ||
            voucher.getSoLuongConLai() == null || voucher.getSoLuongConLai().trim().isEmpty() ||
            voucher.getTrangThai() == null || voucher.getTrangThai().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin voucher không được để trống.");
            return;
        }
        if (voucher.getPhanTramGiamGia() <= 0) {
            System.out.println("Lỗi: Phần trăm giảm giá phải lớn hơn 0.");
            return;
        }
        int index = getVoucherIndexById(maVoucher);
        if (index != -1) {
            voucher.setMaVoucher(maVoucher);
            danhSachVoucher.set(index, voucher);
            System.out.println("Cập nhật thông tin voucher thành công.");
        } else {
            System.out.println("Không tìm thấy voucher với mã đã nhập.");
        }
    }

    public static void Delete(String maVoucher) {
        int index = getVoucherIndexById(maVoucher);
        if (index != -1) {
            danhSachVoucher.remove(index);
            System.out.println("Đã xoá voucher thành công.");
        } else {
            System.out.println("Không tìm thấy voucher với mã đã nhập.");
        }
    }

    public static Voucher getVoucherById(String maVoucher) {
        return danhSachVoucher.stream()
                .filter(v -> v.getMaVoucher().equalsIgnoreCase(maVoucher))
                .findFirst()
                .orElse(null);
    }

    private static int getVoucherIndexById(String maVoucher) {
        for (int i = 0; i < danhSachVoucher.size(); i++) {
            if (danhSachVoucher.get(i).getMaVoucher().equalsIgnoreCase(maVoucher)) {
                return i;
            }
        }
        return -1;
    }
}
