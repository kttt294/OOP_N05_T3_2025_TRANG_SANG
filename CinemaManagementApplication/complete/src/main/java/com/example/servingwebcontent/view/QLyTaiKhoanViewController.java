package com.example.servingwebcontent.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Arrays;
import java.util.List;

@Controller
public class QLyTaiKhoanViewController {
    @GetMapping("/taikhoan")
    public String taiKhoanPage(Model model) {
        List<TaiKhoan> dsTK = Arrays.asList(
            new TaiKhoan(1, "admin", "Quản trị"),
            new TaiKhoan(2, "user1", "Khách hàng")
        );
        model.addAttribute("dsTK", dsTK);
        return "taikhoan";
    }
    public static class TaiKhoan {
        public int id; public String ten; public String vaiTro;
        public TaiKhoan(int id, String ten, String vaiTro) { this.id = id; this.ten = ten; this.vaiTro = vaiTro; }
        public int getId() { return id; } public String getTen() { return ten; } public String getVaiTro() { return vaiTro; }
    }
} 