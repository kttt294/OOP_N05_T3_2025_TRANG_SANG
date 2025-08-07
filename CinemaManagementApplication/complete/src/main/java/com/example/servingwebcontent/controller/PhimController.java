package com.example.servingwebcontent.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    private phimAiven phimDB;
    
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
    
    @GetMapping("/phim/form")
    public String phimFormPage(Model model) {
        model.addAttribute("phim", new Phim());
        return "form-phim";
    }
    
    @GetMapping("/phim/edit")
    public String editPhimPage(@RequestParam String maPhim, Model model) {
        try {
            Phim phim = phimDB.getPhimById(maPhim);
            if (phim != null) {
                model.addAttribute("phim", phim);
                return "form-phim";
            } else {
                model.addAttribute("error", "Không tìm thấy phim với mã: " + maPhim);
                return "redirect:/phim";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tải thông tin phim: " + e.getMessage());
            return "redirect:/phim";
        }
    }
    
    @PostMapping("/phim/create")
    public String createPhim(@ModelAttribute Phim phim, Model model) {
        System.out.println("=== CREATE PHIM REQUEST ===");
        System.out.println("MaPhim: " + phim.getMaPhim());
        System.out.println("TenPhim: " + phim.getTenPhim());
        System.out.println("TheLoai: " + phim.getTheLoai());
        System.out.println("ThoiLuong: " + phim.getThoiLuong());
        System.out.println("NgonNgu: " + phim.getNgonNgu());
        System.out.println("GioiHanTuoi: " + phim.getGioiHanTuoi());
        System.out.println("MoTa: " + phim.getMoTa());
        System.out.println("===========================");
        
        try {
            if (phimDB.createPhim(phim)) {
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
            if (phimDB.updatePhim(maPhim, phim)) {
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
            if (phimDB.deletePhim(maPhim)) {
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
                    results = phimDB.searchPhimByTen(keyword);
                    break;
                case "theloai":
                    results = phimDB.searchPhimByTheLoai(keyword);
                    break;
                default:
                    Phim phim = phimDB.getPhimById(keyword);
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
    
    // Business Logic Methods (delegated to database layer)
    public boolean taoPhim(Phim phim) {
        return phimDB.createPhim(phim);
    }
    
    public boolean capNhatPhim(String maPhim, Phim phimMoi) {
        return phimDB.updatePhim(maPhim, phimMoi);
    }
    
    public boolean xoaPhim(String maPhim) {
        return phimDB.deletePhim(maPhim);
    }
    
    public boolean xemThongTinPhim(String maPhim) {
        Phim phim = phimDB.getPhimById(maPhim);
        if (phim != null) {
            System.out.println("Thông tin phim: " + phim.getTenPhim());
            return true;
        }
        return false;
    }
    
    public boolean xemTatCaPhim() {
        List<Phim> dsPhim = phimDB.getAllPhim();
        System.out.println("Tổng số phim: " + dsPhim.size());
        for (Phim phim : dsPhim) {
            System.out.println("Mã phim: " + phim.getMaPhim() + " | Tên: " + phim.getTenPhim());
        }
        return true;
    }
    
    public Phim timPhimTheoMa(String maPhim) {
        return phimDB.getPhimById(maPhim);
    }
    
    public ArrayList<Phim> timPhimTheoTen(String tenPhim) {
        List<Phim> results = phimDB.searchPhimByTen(tenPhim);
        return new ArrayList<>(results);
    }
    
    public ArrayList<Phim> timPhimTheoTheLoai(String theLoai) {
        List<Phim> results = phimDB.searchPhimByTheLoai(theLoai);
        return new ArrayList<>(results);
    }
    
    public boolean thongKePhim() {
        List<Phim> dsPhim = phimDB.getAllPhim();
        System.out.println("=== THỐNG KÊ PHIM ===");
        System.out.println("Tổng số phim: " + dsPhim.size());
        
        // Thống kê theo thể loại
        java.util.Map<String, Integer> theLoaiCount = new java.util.HashMap<>();
        for (Phim phim : dsPhim) {
            String theLoai = phim.getTheLoai();
            theLoaiCount.put(theLoai, theLoaiCount.getOrDefault(theLoai, 0) + 1);
        }
        
        System.out.println("\nThống kê theo thể loại:");
        for (java.util.Map.Entry<String, Integer> entry : theLoaiCount.entrySet()) {
            System.out.println("- " + entry.getKey() + ": " + entry.getValue() + " phim");
        }
        
        return true;
    }
} 