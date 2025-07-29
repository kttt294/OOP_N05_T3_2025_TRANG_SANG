package com.cinema.controller;

import com.cinema.model.KhachHang;
import com.cinema.controller.KhachHangController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/khachhang")
public class KhachHangWebController {
    
    @GetMapping
    public String listKhachHang(Model model) {
        try {
            List<KhachHang> khachHangList = KhachHangController.xemTatCaKhachHang();
            model.addAttribute("khachHangList", khachHangList);
            model.addAttribute("title", "Quản lý Khách hàng");
            return "khachhang/list";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tải danh sách khách hàng: " + e.getMessage());
            return "error";
        }
    }
    
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("khachHang", new KhachHang());
        model.addAttribute("title", "Thêm Khách hàng mới");
        return "khachhang/create";
    }
    
    @PostMapping("/create")
    public String create(@ModelAttribute KhachHang khachHang, 
                        RedirectAttributes redirectAttributes) {
        try {
            boolean success = KhachHangController.taoKhachHang(khachHang);
            if (success) {
                redirectAttributes.addFlashAttribute("success", "Thêm khách hàng thành công!");
            } else {
                redirectAttributes.addFlashAttribute("error", "Thêm khách hàng thất bại!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi: " + e.getMessage());
        }
        return "redirect:/khachhang";
    }
    
    @GetMapping("/edit/{cccd}")
    public String editForm(@PathVariable String cccd, Model model) {
        try {
            KhachHang khachHang = KhachHangController.timKhachHangTheoCCCD(cccd);
            if (khachHang != null) {
                model.addAttribute("khachHang", khachHang);
                model.addAttribute("title", "Sửa thông tin Khách hàng");
                return "khachhang/edit";
            } else {
                model.addAttribute("error", "Không tìm thấy khách hàng với CCCD: " + cccd);
                return "error";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi: " + e.getMessage());
            return "error";
        }
    }
    
    @PostMapping("/edit")
    public String edit(@ModelAttribute KhachHang khachHang, 
                      RedirectAttributes redirectAttributes) {
        try {
            boolean success = KhachHangController.capNhatThongTin(khachHang);
            if (success) {
                redirectAttributes.addFlashAttribute("success", "Cập nhật khách hàng thành công!");
            } else {
                redirectAttributes.addFlashAttribute("error", "Cập nhật khách hàng thất bại!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi: " + e.getMessage());
        }
        return "redirect:/khachhang";
    }
    
    @GetMapping("/delete/{cccd}")
    public String delete(@PathVariable String cccd, 
                        RedirectAttributes redirectAttributes) {
        try {
            boolean success = KhachHangController.xoaKhachHang(cccd);
            if (success) {
                redirectAttributes.addFlashAttribute("success", "Xóa khách hàng thành công!");
            } else {
                redirectAttributes.addFlashAttribute("error", "Xóa khách hàng thất bại!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi: " + e.getMessage());
        }
        return "redirect:/khachhang";
    }
    
    @GetMapping("/view/{cccd}")
    public String view(@PathVariable String cccd, Model model) {
        try {
            KhachHang khachHang = KhachHangController.timKhachHangTheoCCCD(cccd);
            if (khachHang != null) {
                model.addAttribute("khachHang", khachHang);
                model.addAttribute("title", "Thông tin Khách hàng");
                return "khachhang/view";
            } else {
                model.addAttribute("error", "Không tìm thấy khách hàng với CCCD: " + cccd);
                return "error";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi: " + e.getMessage());
            return "error";
        }
    }
} 