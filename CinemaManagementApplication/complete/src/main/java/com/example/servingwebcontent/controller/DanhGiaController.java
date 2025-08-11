package com.example.servingwebcontent.controller;

import java.util.ArrayList;
import java.util.List;
import com.example.servingwebcontent.model.DanhGia;
import com.example.servingwebcontent.database.danhGiaAiven;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class DanhGiaController{
    
    private danhGiaAiven danhGiaDB = new danhGiaAiven();

    // Web Controller Methods
    @GetMapping("/danhgia")
    public String danhGiaPage(Model model) {
        try {
            List<DanhGia> dsDG = danhGiaDB.getAllDanhGia();
            model.addAttribute("dsDG", dsDG);
            model.addAttribute("message", "");
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi khi tải danh sách đánh giá: " + e.getMessage());
        }
        return "danhgia";
    }

    @GetMapping("/danhgia/form")
    public String hienThiFormTaoDanhGia(Model model) {
        model.addAttribute("danhGia", new DanhGia());
        return "form-danhgia";
    }

    @PostMapping("/danhgia/create")
    public String createDanhGia(@ModelAttribute DanhGia danhGia, Model model) {
        try {
            if (taoDanhGia(danhGia)) {
                model.addAttribute("message", "Tạo đánh giá thành công!");
            } else {
                model.addAttribute("message", "Lỗi khi tạo đánh giá!");
            }
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi: " + e.getMessage());
        }
        return "redirect:/danhgia";
    }

    @PostMapping("/danhgia/update")
    public String updateDanhGia(@RequestParam String maDanhGia, @ModelAttribute DanhGia danhGia, Model model) {
        try {
            if (capNhatDanhGia(maDanhGia, danhGia)) {
                model.addAttribute("message", "Cập nhật đánh giá thành công!");
            } else {
                model.addAttribute("message", "Lỗi khi cập nhật đánh giá!");
            }
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi: " + e.getMessage());
        }
        return "redirect:/danhgia";
    }

    @PostMapping("/danhgia/delete")
    public String deleteDanhGia(@RequestParam String maDanhGia, Model model) {
        try {
            if (xoaDanhGia(maDanhGia)) {
                model.addAttribute("message", "Xóa đánh giá thành công!");
            } else {
                model.addAttribute("message", "Lỗi khi xóa đánh giá!");
            }
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi: " + e.getMessage());
        }
        return "redirect:/danhgia";
    }

    @GetMapping("/danhgia/search")
    public String searchDanhGia(@RequestParam String keyword, Model model) {
        try {
            List<DanhGia> results = timDanhGiaTheoKhachHang(keyword);
            model.addAttribute("dsDG", results);
            model.addAttribute("message", "Kết quả tìm kiếm cho: " + keyword);
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi khi tìm kiếm: " + e.getMessage());
        }
        return "danhgia";
    }

    @GetMapping("/danhgia/statistics")
    public String danhGiaStatisticsPage(Model model) {
        try {
            thongKeDanhGia();
            model.addAttribute("message", "Thống kê đánh giá đã được hiển thị trong console!");
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi khi thống kê: " + e.getMessage());
        }
        return "danhgia";
    }

    // Business Logic Methods
    public boolean taoDanhGia(DanhGia danhGia) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (danhGia == null) {
                throw new IllegalArgumentException("Đánh giá không được null!");
            }
            if (danhGia.getMaDanhGia() == null || danhGia.getMaDanhGia().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã đánh giá không được để trống!");
            }
            if (danhGia.getCCCD() == null || danhGia.getCCCD().trim().isEmpty()) {
                throw new IllegalArgumentException("CCCD không được để trống!");
            }
            if (danhGia.getNoiDung() == null || danhGia.getNoiDung().trim().isEmpty()) {
                throw new IllegalArgumentException("Nội dung đánh giá không được để trống!");
            }
            if (danhGia.getSoSao() < 1 || danhGia.getSoSao() > 5) {
                throw new IllegalArgumentException("Điểm đánh giá phải từ 1-5!");
            }

            return danhGiaDB.createDanhGia(danhGia);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    public boolean capNhatDanhGia(String maDanhGia, DanhGia danhGiaMoi) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maDanhGia == null || maDanhGia.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã đánh giá không được để trống!");
            }
            if (danhGiaMoi == null) {
                throw new IllegalArgumentException("Thông tin đánh giá mới không được null!");
            }

            // Kiểm tra đánh giá có tồn tại không
            DanhGia danhGiaCu = danhGiaDB.getDanhGiaById(maDanhGia);
            if (danhGiaCu == null) {
                System.out.println("Không tìm thấy đánh giá với mã: " + maDanhGia);
                return false;
            }

            return danhGiaDB.updateDanhGia(maDanhGia, danhGiaMoi);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    public boolean xoaDanhGia(String maDanhGia) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maDanhGia == null || maDanhGia.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã đánh giá không được để trống!");
            }

            // Kiểm tra đánh giá có tồn tại không
            DanhGia danhGia = danhGiaDB.getDanhGiaById(maDanhGia);
            if (danhGia == null) {
                System.out.println("Không tìm thấy đánh giá với mã: " + maDanhGia);
                return false;
            }

            return danhGiaDB.deleteDanhGia(maDanhGia);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    public boolean xemThongTinDanhGia(String maDanhGia) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maDanhGia == null || maDanhGia.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã đánh giá không được để trống!");
            }

            DanhGia danhGia = danhGiaDB.getDanhGiaById(maDanhGia);
            if (danhGia != null) {
                System.out.println("=== THÔNG TIN ĐÁNH GIÁ ===");
                System.out.println("Mã đánh giá: " + danhGia.getMaDanhGia());
                System.out.println("CCCD: " + danhGia.getCCCD());
                System.out.println("Mã phim: " + danhGia.getMaPhim());
                System.out.println("Số sao: " + danhGia.getSoSao());
                System.out.println("Nội dung: " + danhGia.getNoiDung());
                System.out.println("Thời gian: " + danhGia.getThoiGian());
                return true;
            } else {
                System.out.println("Không tìm thấy đánh giá với mã: " + maDanhGia);
                return false;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    public boolean xemTatCaDanhGia() {
        try {
            List<DanhGia> danhSach = danhGiaDB.getAllDanhGia();
            if (danhSach.isEmpty()) {
                System.out.println("Không có đánh giá nào.");
                return false;
            }

            System.out.println("=== DANH SÁCH TẤT CẢ ĐÁNH GIÁ ===");
            for (DanhGia dg : danhSach) {
                System.out.println("Mã: " + dg.getMaDanhGia() + " | CCCD: " + dg.getCCCD() + 
                    " | Phim: " + dg.getMaPhim() + " | Số sao: " + dg.getSoSao());
            }
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    public DanhGia timDanhGiaTheoMa(String maDanhGia) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maDanhGia == null || maDanhGia.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã đánh giá không được để trống!");
            }

            return danhGiaDB.getDanhGiaById(maDanhGia);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return null;
        }
    }

    public ArrayList<DanhGia> timDanhGiaTheoKhachHang(String CCCD) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (CCCD == null || CCCD.trim().isEmpty()) {
                throw new IllegalArgumentException("CCCD không được để trống!");
            }

            List<DanhGia> results = danhGiaDB.getDanhGiaByCCCD(CCCD);
            return new ArrayList<>(results);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public double tinhDiemTrungBinh() {
        try {
            List<DanhGia> danhSach = danhGiaDB.getAllDanhGia();
            if (danhSach.isEmpty()) {
                return 0.0;
            }

            int tongDiem = 0;
            for (DanhGia dg : danhSach) {
                tongDiem += dg.getSoSao();
            }

            return (double) tongDiem / danhSach.size();
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return 0.0;
        }
    }

    public boolean thongKeDanhGia() {
        try {
            List<DanhGia> danhSach = danhGiaDB.getAllDanhGia();
            if (danhSach.isEmpty()) {
                System.out.println("Không có đánh giá để thống kê.");
                return false;
            }

            int tongDanhGia = danhSach.size();
            int tongDiem = 0;
            int[] demSao = new int[6]; // 0-5 sao

            for (DanhGia dg : danhSach) {
                tongDiem += dg.getSoSao();
                demSao[dg.getSoSao()]++;
            }

            System.out.println("=== THỐNG KÊ ĐÁNH GIÁ ===");
            System.out.println("Tổng số đánh giá: " + tongDanhGia);
            System.out.println("Điểm trung bình: " + String.format("%.2f", (double) tongDiem / tongDanhGia));
            System.out.println("Phân bố số sao:");
            for (int i = 1; i <= 5; i++) {
                System.out.println(i + " sao: " + demSao[i] + " đánh giá");
            }
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }
} 