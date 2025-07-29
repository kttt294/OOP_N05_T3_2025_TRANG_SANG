package com.example.servingwebcontent.model;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DanhGia {
    public enum LoaiDanhGia { DANH_GIA_PHIM, FEEDBACK }
    public enum TrangThaiXuLy { MOI, DANG_XU_LY, DA_XU_LY }

    private String maDanhGia;
    private String CCCD;
    private String maPhim; // null nếu là feedback
    private int soSao; // từ 0 đến 5, chỉ dùng cho đánh giá phim
    private String noiDung; // bình luận cho đánh giá phim hoặc nội dung feedback
    private LocalDateTime thoiGian;
    private LoaiDanhGia loaiDanhGia;
    private TrangThaiXuLy trangThaiXuLy; // chỉ dùng cho feedback
    private String loaiFeedback; // góp ý, khiếu nại, khác - chỉ dùng cho feedback

    private static ArrayList<DanhGia> danhSachDanhGia = new ArrayList<>();

    public DanhGia() {}

    // Constructor cho đánh giá phim
    public DanhGia(String maDanhGia, String CCCD, String maPhim, int soSao, String noiDung, LocalDateTime thoiGian) {
        setMaDanhGia(maDanhGia);
        setCCCD(CCCD);
        setMaPhim(maPhim);
        setSoSao(soSao);
        setNoiDung(noiDung);
        setThoiGian(thoiGian);
        this.loaiDanhGia = LoaiDanhGia.DANH_GIA_PHIM;
        this.trangThaiXuLy = TrangThaiXuLy.DA_XU_LY;
    }

    // Constructor cho feedback
    public DanhGia(String maDanhGia, String CCCD, String noiDung, LocalDateTime thoiGian,
                   LoaiDanhGia loaiDanhGia, TrangThaiXuLy trangThaiXuLy) {
        setMaDanhGia(maDanhGia);
        setCCCD(CCCD);
        setNoiDung(noiDung);
        setThoiGian(thoiGian);
        this.loaiDanhGia = loaiDanhGia;
        this.trangThaiXuLy = trangThaiXuLy;
    }

    // Constructor đầy đủ
    public DanhGia(String maDanhGia, String CCCD, String maPhim, int soSao, String noiDung,
                   LocalDateTime thoiGian, LoaiDanhGia loaiDanhGia, TrangThaiXuLy trangThaiXuLy) {
        setMaDanhGia(maDanhGia);
        setCCCD(CCCD);
        setMaPhim(maPhim);
        setSoSao(soSao);
        setNoiDung(noiDung);
        setThoiGian(thoiGian);
        this.loaiDanhGia = loaiDanhGia;
        this.trangThaiXuLy = trangThaiXuLy;
    }

    public String getMaDanhGia() { return maDanhGia; }
    public void setMaDanhGia(String maDanhGia) {
        if (maDanhGia == null || maDanhGia.trim().isEmpty())
            throw new IllegalArgumentException("Mã đánh giá không được để trống!");
        this.maDanhGia = maDanhGia.trim();
    }
    public String getCCCD() { return CCCD; }
    public void setCCCD(String CCCD) {
        if (CCCD == null || CCCD.trim().isEmpty())
            throw new IllegalArgumentException("CCCD không được để trống!");
        this.CCCD = CCCD.trim();
    }
    public String getMaPhim() { return maPhim; }
    public void setMaPhim(String maPhim) {
        this.maPhim = (maPhim != null) ? maPhim.trim() : null;
    }
    public int getSoSao() { return soSao; }
    public void setSoSao(int soSao) {
        if (soSao < 0 || soSao > 5) throw new IllegalArgumentException("Số sao phải từ 0 đến 5!");
        this.soSao = soSao;
    }
    public String getNoiDung() { return noiDung; }
    public void setNoiDung(String noiDung) {
        this.noiDung = (noiDung != null) ? noiDung.trim() : "";
    }
    public LocalDateTime getThoiGian() { return thoiGian; }
    public void setThoiGian(LocalDateTime thoiGian) {
        this.thoiGian = thoiGian;
    }
    public LoaiDanhGia getLoaiDanhGia() { return loaiDanhGia; }
    public void setLoaiDanhGia(LoaiDanhGia loaiDanhGia) {
        this.loaiDanhGia = (loaiDanhGia != null) ? loaiDanhGia : LoaiDanhGia.DANH_GIA_PHIM;
    }
    public TrangThaiXuLy getTrangThaiXuLy() { return trangThaiXuLy; }
    public void setTrangThaiXuLy(TrangThaiXuLy trangThaiXuLy) {
        this.trangThaiXuLy = (trangThaiXuLy != null) ? trangThaiXuLy : TrangThaiXuLy.MOI;
    }
    public String getLoaiFeedback() { return loaiFeedback; }
    public void setLoaiFeedback(String loaiFeedback) {
        this.loaiFeedback = (loaiFeedback != null) ? loaiFeedback.trim() : "";
    }

    // Phương thức kiểm tra loại
    public boolean isDanhGiaPhim() { return loaiDanhGia == LoaiDanhGia.DANH_GIA_PHIM; }
    public boolean isFeedback() { return loaiDanhGia == LoaiDanhGia.FEEDBACK; }

    // Phương thức thay đổi trạng thái xử lý (chỉ cho feedback)
    public void capNhatTrangThaiXuLy(TrangThaiXuLy trangThaiMoi) {
        if (isFeedback()) {
            this.trangThaiXuLy = trangThaiMoi;
        }
    }

    // CRUD
    public static void Create(DanhGia dg) {
        if (dg == null || dg.getMaDanhGia() == null || dg.getMaDanhGia().trim().isEmpty() ||
            dg.getCCCD() == null || dg.getCCCD().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin đánh giá không được để trống.");
            return;
        }
        if (dg.isDanhGiaPhim() && (dg.getMaPhim() == null || dg.getMaPhim().trim().isEmpty())) {
            System.out.println("Lỗi: Mã phim không được để trống cho đánh giá phim.");
            return;
        }
        if (getDanhGiaByMa(dg.getMaDanhGia()) != null) {
            System.out.println("Lỗi: Đánh giá đã tồn tại.");
            return;
        }
        danhSachDanhGia.add(dg);
        System.out.println("Đã thêm " + (dg.isDanhGiaPhim() ? "đánh giá" : "feedback") + " thành công.");
    }
    
    // Read toàn bộ danh sách đánh giá
    public static ArrayList<DanhGia> Read() {
        if (danhSachDanhGia.isEmpty()) {
            System.out.println("Danh sách đánh giá trống.");
            return new ArrayList<>();
        }
        System.out.println("Tổng số đánh giá: " + danhSachDanhGia.size());
        return new ArrayList<>(danhSachDanhGia);
    }
    
    // Read đánh giá theo mã
    public static void Read(String maDanhGia) {
        if (danhSachDanhGia.isEmpty()) {
            System.out.println("Danh sách đánh giá trống.");
            return;
        }
        DanhGia dg = getDanhGiaByMa(maDanhGia);
        if (dg != null) {
            dg.hienThiThongTin();
        } else {
            System.out.println("Không tìm thấy đánh giá với mã: " + maDanhGia);
        }
    }
    
    public static void Update(String maDanhGia, DanhGia dg) {
        if (dg == null || dg.getMaDanhGia() == null || dg.getMaDanhGia().trim().isEmpty()) {
            System.out.println("Lỗi: Thông tin đánh giá không được để trống.");
            return;
        }
        int idx = getDanhGiaIndexByMa(maDanhGia);
        if (idx == -1) {
            System.out.println("Không tìm thấy đánh giá với mã: " + maDanhGia);
            return;
        }
        dg.setMaDanhGia(maDanhGia);
        danhSachDanhGia.set(idx, dg);
        System.out.println("Cập nhật " + (dg.isDanhGiaPhim() ? "đánh giá" : "feedback") + " thành công.");
    }
    
    public static void Delete(String maDanhGia) {
        int idx = getDanhGiaIndexByMa(maDanhGia);
        if (idx == -1) {
            System.out.println("Không tìm thấy đánh giá với mã: " + maDanhGia);
            return;
        }
        DanhGia dg = danhSachDanhGia.get(idx);
        danhSachDanhGia.remove(idx);
        System.out.println("Đã xóa " + (dg.isDanhGiaPhim() ? "đánh giá" : "feedback") + " thành công.");
    }
    
    public static DanhGia getDanhGiaByMa(String maDanhGia) {
        for (DanhGia dg : danhSachDanhGia) {
            if (dg.getMaDanhGia().equalsIgnoreCase(maDanhGia)) return dg;
        }
        return null;
    }
    
    public static int getDanhGiaIndexByMa(String maDanhGia) {
        for (int i = 0; i < danhSachDanhGia.size(); i++) {
            if (danhSachDanhGia.get(i).getMaDanhGia().equalsIgnoreCase(maDanhGia)) return i;
        }
        return -1;
    }

    // Phương thức tìm kiếm theo loại
    public static ArrayList<DanhGia> getDanhGiaByLoai(LoaiDanhGia loai) {
        ArrayList<DanhGia> result = new ArrayList<>();
        for (DanhGia dg : danhSachDanhGia) {
            if (dg.getLoaiDanhGia() == loai) {
                result.add(dg);
            }
        }
        return result;
    }

    // Phương thức tìm kiếm đánh giá phim theo mã phim
    public static ArrayList<DanhGia> getDanhGiaByMaPhim(String maPhim) {
        ArrayList<DanhGia> result = new ArrayList<>();
        for (DanhGia dg : danhSachDanhGia) {
            if (dg.isDanhGiaPhim() && dg.getMaPhim() != null && dg.getMaPhim().equals(maPhim)) {
                result.add(dg);
            }
        }
        return result;
    }

    // Phương thức tìm kiếm feedback theo trạng thái xử lý
    public static ArrayList<DanhGia> getFeedbackByTrangThai(TrangThaiXuLy trangThai) {
        ArrayList<DanhGia> result = new ArrayList<>();
        for (DanhGia dg : danhSachDanhGia) {
            if (dg.isFeedback() && dg.getTrangThaiXuLy() == trangThai) {
                result.add(dg);
            }
        }
        return result;
    }

    // Phương thức tính điểm trung bình của phim
    public static double tinhDiemTrungBinhPhim(String maPhim) {
        ArrayList<DanhGia> danhGiaPhim = getDanhGiaByMaPhim(maPhim);
        if (danhGiaPhim.isEmpty()) {
            return 0.0;
        }
        
        double tongDiem = 0.0;
        int soDanhGia = 0;
        
        for (DanhGia dg : danhGiaPhim) {
            if (dg.isDanhGiaPhim()) {
                tongDiem += dg.getSoSao();
                soDanhGia++;
            }
        }
        
        return soDanhGia > 0 ? tongDiem / soDanhGia : 0.0;
    }

    public static ArrayList<DanhGia> getDanhGiaByCCCD(String CCCD) {
        ArrayList<DanhGia> ketQua = new ArrayList<>();
        for (DanhGia dg : danhSachDanhGia) {
            if (dg.getCCCD().equalsIgnoreCase(CCCD)) {
                ketQua.add(dg);
            }
        }
        return ketQua;
    }

    public static void thongKeDanhGia() {
        System.out.println("=== THỐNG KÊ ĐÁNH GIÁ ===");
        System.out.println("Tổng số đánh giá: " + danhSachDanhGia.size());
        
        int soDanhGiaPhim = 0;
        int soFeedback = 0;
        
        for (DanhGia dg : danhSachDanhGia) {
            if (dg.isDanhGiaPhim()) {
                soDanhGiaPhim++;
            } else if (dg.isFeedback()) {
                soFeedback++;
            }
        }
        
        System.out.println("Số đánh giá phim: " + soDanhGiaPhim);
        System.out.println("Số feedback: " + soFeedback);
        
        // Thống kê theo trạng thái xử lý
        int moi = 0, dangXuLy = 0, daXuLy = 0;
        for (DanhGia dg : danhSachDanhGia) {
            if (dg.isFeedback()) {
                switch (dg.getTrangThaiXuLy()) {
                    case MOI: moi++; break;
                    case DANG_XU_LY: dangXuLy++; break;
                    case DA_XU_LY: daXuLy++; break;
                }
            }
        }
        
        System.out.println("Feedback mới: " + moi);
        System.out.println("Feedback đang xử lý: " + dangXuLy);
        System.out.println("Feedback đã xử lý: " + daXuLy);
        System.out.println("=========================");
    }

    public void hienThiThongTin() {
        if (isDanhGiaPhim()) {
            System.out.println("=== THÔNG TIN ĐÁNH GIÁ PHIM ===");
            System.out.println("Mã đánh giá: " + maDanhGia);
            System.out.println("Mã khách hàng: " + CCCD);
            System.out.println("Mã phim: " + maPhim);
            System.out.println("Số sao: " + soSao + "/5");
            System.out.println("Bình luận: " + noiDung);
            System.out.println("Thời gian: " + thoiGian);
        } else {
            System.out.println("=== THÔNG TIN FEEDBACK ===");
            System.out.println("Mã feedback: " + maDanhGia);
            System.out.println("Mã khách hàng: " + CCCD);
            System.out.println("Nội dung: " + noiDung);
            System.out.println("Thời gian: " + thoiGian);
            System.out.println("Trạng thái xử lý: " + trangThaiXuLy);
            System.out.println("Loại feedback: " + loaiFeedback);
        }
        System.out.println("=============================");
    }
}