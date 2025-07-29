package com.example.servingwebcontent.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Arrays;
import java.util.List;

@Controller
public class PhongChieuViewController {
    @GetMapping("/phongchieu")
    public String phongChieuPage(Model model) {
        List<PhongChieu> dsPC = Arrays.asList(
            new PhongChieu(1, "Phòng 1", 100),
            new PhongChieu(2, "Phòng 2", 80)
        );
        model.addAttribute("dsPC", dsPC);
        return "phongchieu";
    }
    public static class PhongChieu {
        public int id; public String ten; public int sucChua;
        public PhongChieu(int id, String ten, int sucChua) { this.id = id; this.ten = ten; this.sucChua = sucChua; }
        public int getId() { return id; } public String getTen() { return ten; } public int getSucChua() { return sucChua; }
    }
} 