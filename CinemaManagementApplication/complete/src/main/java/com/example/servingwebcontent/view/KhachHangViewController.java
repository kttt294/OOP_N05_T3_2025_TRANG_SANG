package com.example.servingwebcontent.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Arrays;
import java.util.List;

@Controller
public class KhachHangViewController {
    @GetMapping("/khachhang")
    public String khachHangPage(Model model) {
        List<KhachHang> dsKH = Arrays.asList(
            new KhachHang(1, "Nguyễn Văn A", "0123456789"),
            new KhachHang(2, "Trần Thị B", "0987654321")
        );
        model.addAttribute("dsKH", dsKH);
        return "khachhang";
    }
    public static class KhachHang {
        public int id; public String ten; public String sdt;
        public KhachHang(int id, String ten, String sdt) { this.id = id; this.ten = ten; this.sdt = sdt; }
        public int getId() { return id; } public String getTen() { return ten; } public String getSdt() { return sdt; }
    }
} 