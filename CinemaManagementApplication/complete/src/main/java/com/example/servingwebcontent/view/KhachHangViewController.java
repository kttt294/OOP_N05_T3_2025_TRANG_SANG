package com.example.servingwebcontent.view;

import com.example.servingwebcontent.model.KhachHang;
import com.example.servingwebcontent.database.khachHangAiven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class KhachHangViewController {
    @Autowired
    private khachHangAiven khachHangDB;

    @GetMapping("/khachhang")
    public String khachHangPage(Model model) {
        List<KhachHang> dsKH = khachHangDB.getAllKhachHang();
        model.addAttribute("dsKH", dsKH);
        return "khachhang";
    }
} 