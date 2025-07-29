package com.example.servingwebcontent.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Arrays;
import java.util.List;

@Controller
public class PhimViewController {
    @GetMapping("/phim")
    public String phimPage(Model model) {
        List<Phim> dsPhim = Arrays.asList(
            new Phim(1, "Lật Mặt", "Hành động"),
            new Phim(2, "Mắt Biếc", "Tình cảm")
        );
        model.addAttribute("dsPhim", dsPhim);
        return "phim";
    }
    public static class Phim {
        public int id; public String ten; public String theLoai;
        public Phim(int id, String ten, String theLoai) { this.id = id; this.ten = ten; this.theLoai = theLoai; }
        public int getId() { return id; } public String getTen() { return ten; } public String getTheLoai() { return theLoai; }
    }
} 