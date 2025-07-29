package com.example.servingwebcontent.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Arrays;
import java.util.List;

@Controller
public class VeViewController {
    @GetMapping("/ve")
    public String vePage(Model model) {
        List<Ve> dsVe = Arrays.asList(
            new Ve(1, "Lật Mặt", "A1"),
            new Ve(2, "Mắt Biếc", "B2")
        );
        model.addAttribute("dsVe", dsVe);
        return "ve";
    }
    public static class Ve {
        public int id; public String tenPhim; public String ghe;
        public Ve(int id, String tenPhim, String ghe) { this.id = id; this.tenPhim = tenPhim; this.ghe = ghe; }
        public int getId() { return id; } public String getTenPhim() { return tenPhim; } public String getGhe() { return ghe; }
    }
} 