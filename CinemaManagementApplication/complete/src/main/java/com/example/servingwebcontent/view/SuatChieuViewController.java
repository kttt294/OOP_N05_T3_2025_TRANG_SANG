package com.example.servingwebcontent.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Arrays;
import java.util.List;

@Controller
public class SuatChieuViewController {
    @GetMapping("/suatchieu")
    public String suatChieuPage(Model model) {
        List<SuatChieu> dsSC = Arrays.asList(
            new SuatChieu(1, "Lật Mặt", "10:00"),
            new SuatChieu(2, "Mắt Biếc", "14:00")
        );
        model.addAttribute("dsSC", dsSC);
        return "suatchieu";
    }
    public static class SuatChieu {
        public int id; public String tenPhim; public String gio;
        public SuatChieu(int id, String tenPhim, String gio) { this.id = id; this.tenPhim = tenPhim; this.gio = gio; }
        public int getId() { return id; } public String getTenPhim() { return tenPhim; } public String getGio() { return gio; }
    }
} 