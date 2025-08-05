package com.example.servingwebcontent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.servingwebcontent.database.khachHangAiven;
import com.example.servingwebcontent.database.phimAiven;
import com.example.servingwebcontent.model.KhachHang;
import com.example.servingwebcontent.model.Phim;
import java.util.List;

@Controller
public class HomeController {
    
    @Autowired
    private khachHangAiven khachHangDB;
    
    @Autowired
    private phimAiven phimDB;
    
    @GetMapping("/")
    public String home(Model model) {
        try {
            // Lấy thống kê cơ bản
            List<KhachHang> customers = khachHangDB.getAllKhachHang();
            List<Phim> movies = phimDB.getAllPhim();
            
            model.addAttribute("totalCustomers", customers.size());
            model.addAttribute("totalMovies", movies.size());
            
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi kết nối database: " + e.getMessage());
        }
        
        return "index";
    }
    
    @GetMapping("/health")
    @ResponseBody
    public String health() {
        return "OK";
    }
    
    @GetMapping("/api/stats")
    @ResponseBody
    public String getStats() {
        try {
            List<KhachHang> customers = khachHangDB.getAllKhachHang();
            List<Phim> movies = phimDB.getAllPhim();
            
            return String.format("{\"customers\": %d, \"movies\": %d}", 
                customers.size(), movies.size());
        } catch (Exception e) {
            return "{\"error\": \"" + e.getMessage() + "\"}";
        }
    }
} 