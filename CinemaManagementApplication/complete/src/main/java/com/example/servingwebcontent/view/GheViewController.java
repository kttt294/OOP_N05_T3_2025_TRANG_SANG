package com.example.servingwebcontent.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Arrays;
import java.util.List;

@Controller
public class GheViewController {
    @GetMapping("/ghe")
    public String ghePage(Model model) {
        List<Ghe> dsGhe = Arrays.asList(
            new Ghe(1, "A1", "Thường"),
            new Ghe(2, "B2", "VIP")
        );
        model.addAttribute("dsGhe", dsGhe);
        return "ghe";
    }
    public static class Ghe {
        public int id; public String soGhe; public String loai;
        public Ghe(int id, String soGhe, String loai) { this.id = id; this.soGhe = soGhe; this.loai = loai; }
        public int getId() { return id; } public String getSoGhe() { return soGhe; } public String getLoai() { return loai; }
    }
} 