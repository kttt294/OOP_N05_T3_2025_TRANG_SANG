package com.example.servingwebcontent.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.servingwebcontent.database.khachHangAiven;
import com.example.servingwebcontent.database.phimAiven;
import com.example.servingwebcontent.model.KhachHang;
import com.example.servingwebcontent.model.Phim;
import java.util.List;

@Controller
public class HomeViewController {
    
    @Autowired
    private khachHangAiven khachHangDB;
    
    @Autowired
    private phimAiven phimDB;
    
    @GetMapping("/")
    public String home(Model model) {
        try {
            // Lấy thống kê cơ bản từ database
            List<KhachHang> customers = khachHangDB.getAllKhachHang();
            List<Phim> movies = phimDB.getAllPhim();
            
            model.addAttribute("totalCustomers", customers.size());
            model.addAttribute("totalMovies", movies.size());
            
        } catch (Exception e) {
            // Nếu có lỗi database, sử dụng dữ liệu mẫu
            model.addAttribute("totalCustomers", 150);
            model.addAttribute("totalMovies", 25);
            model.addAttribute("error", "Lỗi kết nối database: " + e.getMessage());
        }
        
        return "index";
    }
}
