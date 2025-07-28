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

    // Read toàn bộ danh sách voucher
    public static ArrayList<Voucher> Read() {
        if (danhSachVoucher.isEmpty()) {
            System.out.println("Danh sách voucher trống.");
            return new ArrayList<>();
        }
        System.out.println("Tổng số voucher: " + danhSachVoucher.size());
        return new ArrayList<>(danhSachVoucher);
    }
    
    // Read voucher theo mã
    public static void Read(String maVoucher) {
        if (danhSachVoucher.isEmpty()) {
            System.out.println("Danh sách voucher trống.");
            return;
        }
            Voucher v = getVoucherById(maVoucher);
            if (v != null) {
            v.hienThiThongTin();
            } else {
            System.out.println("Không tìm thấy voucher với mã: " + maVoucher);
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
    
    // Hiển thị thông tin chi tiết của một voucher
    public void hienThiThongTin() {
        System.out.println("=== THÔNG TIN VOUCHER ===");
        System.out.println("Mã voucher: " + this.maVoucher);
        System.out.println("Mô tả: " + this.moTa);
        System.out.println("Phần trăm giảm giá: " + this.phanTramGiamGia + "%");
        System.out.println("Ngày bắt đầu: " + this.ngayBatDau);
        System.out.println("Ngày kết thúc: " + this.ngayKetThuc);
        System.out.println("Số lượng còn lại: " + this.soLuongConLai);
        System.out.println("Trạng thái: " + this.trangThai);
        System.out.println("=========================");
    }
}
