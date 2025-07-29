package com.example.servingwebcontent.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Arrays;
import java.util.List;

@Controller
public class DoAnViewController {
    @GetMapping("/doan")
    public String doAnPage(Model model) {
        List<DoAn> dsDoAn = Arrays.asList(
            new DoAn(1, "Bắp rang", 30000),
            new DoAn(2, "Nước ngọt", 20000)
        );
        model.addAttribute("dsDoAn", dsDoAn);
        return "doan";
    }
    public static class DoAn {
        public int id; public String ten; public int gia;
        public DoAn(int id, String ten, int gia) { this.id = id; this.ten = ten; this.gia = gia; }
        public int getId() { return id; } public String getTen() { return ten; } public int getGia() { return gia; }
    }
} 