package com.example.servingwebcontent.model;
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
        System.out.println("Mã voucher: " + maVoucher);
        System.out.println("Mô tả: " + moTa);
        System.out.println("Phần trăm giảm giá: " + phanTramGiamGia + "%");
        System.out.println("Ngày bắt đầu: " + ngayBatDau);
        System.out.println("Ngày kết thúc: " + ngayKetThuc);
        System.out.println("Số lượng còn lại: " + soLuongConLai);
        System.out.println("Trạng thái: " + trangThai);
        System.out.println("=========================");
    }

    public static ArrayList<Voucher> timKiemTheoTen(String tenVoucher) {
        ArrayList<Voucher> ketQua = new ArrayList<>();
        for (Voucher voucher : danhSachVoucher) {
            if (voucher.getMoTa().toLowerCase().contains(tenVoucher.toLowerCase())) {
                ketQua.add(voucher);
            }
        }
        return ketQua;
    }

    public static boolean kiemTraVoucherHopLe(String maVoucher) {
        Voucher voucher = getVoucherById(maVoucher);
        if (voucher == null) {
            return false;
        }
        
        // Kiểm tra ngày hiệu lực
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(voucher.getNgayBatDau()) || now.isAfter(voucher.getNgayKetThuc())) {
            return false;
        }
        
        // Kiểm tra số lượng còn lại
        try {
            int soLuong = Integer.parseInt(voucher.getSoLuongConLai());
            if (soLuong <= 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        
        // Kiểm tra trạng thái
        if (!"HoatDong".equalsIgnoreCase(voucher.getTrangThai())) {
            return false;
        }
        
        return true;
    }

    public static boolean suDungVoucher(String maVoucher) {
        Voucher voucher = getVoucherById(maVoucher);
        if (voucher == null) {
            return false;
        }
        
        if (!kiemTraVoucherHopLe(maVoucher)) {
            return false;
        }
        
        // Giảm số lượng
        try {
            int soLuong = Integer.parseInt(voucher.getSoLuongConLai());
            soLuong--;
            voucher.setSoLuongConLai(String.valueOf(soLuong));
            
            // Nếu hết voucher thì cập nhật trạng thái
            if (soLuong <= 0) {
                voucher.setTrangThai("HetHang");
            }
            
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void thongKeVoucher() {
        System.out.println("=== THỐNG KÊ VOUCHER ===");
        System.out.println("Tổng số voucher: " + danhSachVoucher.size());
        
        int hoatDong = 0, hetHang = 0, hetHan = 0;
        int tongGiamGia = 0;
        
        LocalDateTime now = LocalDateTime.now();
        
        for (Voucher voucher : danhSachVoucher) {
            if ("HoatDong".equalsIgnoreCase(voucher.getTrangThai())) {
                hoatDong++;
            } else if ("HetHang".equalsIgnoreCase(voucher.getTrangThai())) {
                hetHang++;
            }
            
            if (now.isAfter(voucher.getNgayKetThuc())) {
                hetHan++;
            }
            
            tongGiamGia += voucher.getPhanTramGiamGia();
        }
        
        System.out.println("Voucher hoạt động: " + hoatDong);
        System.out.println("Voucher hết hàng: " + hetHang);
        System.out.println("Voucher hết hạn: " + hetHan);
        System.out.println("Tổng phần trăm giảm giá: " + tongGiamGia + "%");
        System.out.println("=========================");
    }
}
