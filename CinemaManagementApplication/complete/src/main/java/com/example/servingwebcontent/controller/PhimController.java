package com.example.servingwebcontent.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.example.servingwebcontent.model.Phim;
import com.example.servingwebcontent.database.phimAiven;

@Controller
public class PhimController {
    
    private phimAiven phimDB = new phimAiven();
    
    // Web Controller Methods
    @GetMapping("/phim")
    public String phimPage(Model model) {
        try {
            List<Phim> dsPhim = phimDB.getAllPhim();
            model.addAttribute("dsPhim", dsPhim);
            model.addAttribute("phim", new Phim());
            return "phim";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tải danh sách phim: " + e.getMessage());
            return "phim";
        }
    }
    
    @PostMapping("/phim/create")
    public String createPhim(@ModelAttribute Phim phim, Model model) {
        try {
            if (taoPhim(phim)) {
                model.addAttribute("success", "Tạo phim thành công!");
            } else {
                model.addAttribute("error", "Lỗi khi tạo phim!");
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi hệ thống: " + e.getMessage());
        }
        return "redirect:/phim";
    }
    
    @PostMapping("/phim/update")
    public String updatePhim(@RequestParam String maPhim, @ModelAttribute Phim phim, Model model) {
        try {
            if (capNhatPhim(maPhim, phim)) {
                model.addAttribute("success", "Cập nhật phim thành công!");
            } else {
                model.addAttribute("error", "Lỗi khi cập nhật phim!");
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi hệ thống: " + e.getMessage());
        }
        return "redirect:/phim";
    }
    
    @PostMapping("/phim/delete")
    public String deletePhim(@RequestParam String maPhim, Model model) {
        try {
            if (xoaPhim(maPhim)) {
                model.addAttribute("success", "Xóa phim thành công!");
            } else {
                model.addAttribute("error", "Lỗi khi xóa phim!");
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi hệ thống: " + e.getMessage());
        }
        return "redirect:/phim";
    }
    
    @GetMapping("/phim/search")
    public String searchPhim(@RequestParam String keyword, @RequestParam String type, Model model) {
        try {
            List<Phim> results = new ArrayList<>();
            switch (type) {
                case "ten":
                    results = timPhimTheoTen(keyword);
                    break;
                case "theloai":
                    results = timPhimTheoTheLoai(keyword);
                    break;
                default:
                    Phim phim = timPhimTheoMa(keyword);
                    if (phim != null) {
                        results.add(phim);
                    }
                    break;
            }
            model.addAttribute("dsPhim", results);
            model.addAttribute("searchKeyword", keyword);
            model.addAttribute("searchType", type);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tìm kiếm: " + e.getMessage());
        }
        return "phim";
    }
    
    // Business Logic Methods
    public boolean taoPhim(Phim phim) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (phim == null) {
                throw new IllegalArgumentException("Phim không được null!");
            }
            if (phim.getMaPhim() == null || phim.getMaPhim().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã phim không được để trống!");
            }
            if (phim.getTenPhim() == null || phim.getTenPhim().trim().isEmpty()) {
                throw new IllegalArgumentException("Tên phim không được để trống!");
            }
            if (phim.getThoiLuong() <= 0) {
                throw new IllegalArgumentException("Thời lượng phim phải lớn hơn 0!");
            }
            if (phim.getGioiHanTuoi() < 0 || phim.getGioiHanTuoi() > 25) {
                throw new IllegalArgumentException("Giới hạn tuổi không hợp lệ!");
            }

            return phimDB.createPhim(phim);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    public boolean capNhatPhim(String maPhim, Phim phimMoi) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maPhim == null || maPhim.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã phim không được để trống!");
            }
            if (phimMoi == null) {
                throw new IllegalArgumentException("Thông tin phim mới không được null!");
            }

            // Kiểm tra phim có tồn tại không
            Phim phimCu = phimDB.getPhimById(maPhim);
            if (phimCu == null) {
                System.out.println("Không tìm thấy phim với mã: " + maPhim);
                return false;
            }

            return phimDB.updatePhim(maPhim, phimMoi);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    public boolean xoaPhim(String maPhim) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maPhim == null || maPhim.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã phim không được để trống!");
            }

            // Kiểm tra phim có tồn tại không
            Phim phim = phimDB.getPhimById(maPhim);
            if (phim == null) {
                System.out.println("Không tìm thấy phim với mã: " + maPhim);
                return false;
            }

            return phimDB.deletePhim(maPhim);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    public boolean xemThongTinPhim(String maPhim) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maPhim == null || maPhim.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã phim không được để trống!");
            }

            Phim phim = phimDB.getPhimById(maPhim);
            if (phim != null) {
                phim.hienThiThongTin();
                return true;
            } else {
                System.out.println("Không tìm thấy phim với mã: " + maPhim);
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

    public boolean xemTatCaPhim() {
        try {
            List<Phim> danhSachPhim = phimDB.getAllPhim();
            if (danhSachPhim.isEmpty()) {
                System.out.println("Danh sách phim trống.");
            } else {
                System.out.println("Tổng số phim: " + danhSachPhim.size());
                for (Phim phim : danhSachPhim) {
                    phim.hienThiThongTin();
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    public Phim timPhimTheoMa(String maPhim) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maPhim == null || maPhim.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã phim không được để trống!");
            }

            return phimDB.getPhimById(maPhim);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return null;
        }
    }

    public ArrayList<Phim> timPhimTheoTen(String tenPhim) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (tenPhim == null || tenPhim.trim().isEmpty()) {
                throw new IllegalArgumentException("Tên phim không được để trống!");
            }

            return phimDB.searchPhimByTen(tenPhim);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public ArrayList<Phim> timPhimTheoTheLoai(String theLoai) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (theLoai == null || theLoai.trim().isEmpty()) {
                throw new IllegalArgumentException("Thể loại không được để trống!");
            }

            return phimDB.searchPhimByTheLoai(theLoai);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean thongKePhim() {
        try {
            List<Phim> danhSachPhim = phimDB.getAllPhim();
            System.out.println("=== THỐNG KÊ PHIM ===");
            System.out.println("Tổng số phim: " + danhSachPhim.size());
            
            // Thống kê theo thể loại
            java.util.Map<String, Integer> theLoaiCount = new java.util.HashMap<>();
            for (Phim phim : danhSachPhim) {
                String theLoai = phim.getTheLoai();
                theLoaiCount.put(theLoai, theLoaiCount.getOrDefault(theLoai, 0) + 1);
            }
            
            System.out.println("Thống kê theo thể loại:");
            for (java.util.Map.Entry<String, Integer> entry : theLoaiCount.entrySet()) {
                System.out.println("  " + entry.getKey() + ": " + entry.getValue() + " phim");
            }
            
            // Thống kê theo ngôn ngữ
            java.util.Map<String, Integer> ngonNguCount = new java.util.HashMap<>();
            for (Phim phim : danhSachPhim) {
                String ngonNgu = phim.getNgonNgu();
                ngonNguCount.put(ngonNgu, ngonNguCount.getOrDefault(ngonNgu, 0) + 1);
            }
            
            System.out.println("Thống kê theo ngôn ngữ:");
            for (java.util.Map.Entry<String, Integer> entry : ngonNguCount.entrySet()) {
                System.out.println("  " + entry.getKey() + ": " + entry.getValue() + " phim");
            }
            
            System.out.println("=====================");
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }
} 