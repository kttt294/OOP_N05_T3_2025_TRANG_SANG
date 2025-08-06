package com.example.servingwebcontent.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.servingwebcontent.database.khachHangAiven;
import com.example.servingwebcontent.database.phimAiven;
import com.example.servingwebcontent.database.veAiven;
import com.example.servingwebcontent.database.suatChieuAiven;
import com.example.servingwebcontent.model.KhachHang;
import com.example.servingwebcontent.model.Phim;
import com.example.servingwebcontent.model.Ve;
import com.example.servingwebcontent.model.SuatChieu;
import java.util.List;

@Controller
public class HomeViewController {
    
    @Autowired
    private khachHangAiven khachHangDB;
    
    @Autowired
    private phimAiven phimDB;
    
    @Autowired
    private veAiven veDB;
    
    @Autowired
    private suatChieuAiven suatChieuDB;
    
    @GetMapping("/")
    public String home(Model model) {
        try {
            // Lấy thống kê từ tất cả các bảng database
            List<KhachHang> customers = khachHangDB.getAllKhachHang();
            List<Phim> movies = phimDB.getAllPhim();
            List<Ve> tickets = veDB.getAllVe();
            List<SuatChieu> showtimes = suatChieuDB.getAllSuatChieu();
            
            model.addAttribute("totalCustomers", customers.size());
            model.addAttribute("totalMovies", movies.size());
            model.addAttribute("totalTickets", tickets.size());
            model.addAttribute("totalShowtimes", showtimes.size());
            
        } catch (Exception e) {
            // Nếu có lỗi database, sử dụng dữ liệu mẫu
            model.addAttribute("totalCustomers", 0);
            model.addAttribute("totalMovies", 0);
            model.addAttribute("totalTickets", 0);
            model.addAttribute("totalShowtimes", 0);
            model.addAttribute("error", "Lỗi kết nối database: " + e.getMessage());
        }
        
        return "index";
    }
    
    @GetMapping("/db-status")
    public String dbStatus(Model model) {
        try {
            // Kiểm tra kết nối database và lấy thống kê từ tất cả các bảng
            List<KhachHang> customers = khachHangDB.getAllKhachHang();
            List<Phim> movies = phimDB.getAllPhim();
            List<Ve> tickets = veDB.getAllVe();
            List<SuatChieu> showtimes = suatChieuDB.getAllSuatChieu();
            
            model.addAttribute("isConnected", true);
            model.addAttribute("connectionInfo", "MySQL Database - Connected Successfully");
            model.addAttribute("totalCustomers", customers.size());
            model.addAttribute("totalMovies", movies.size());
            model.addAttribute("totalTickets", tickets.size());
            model.addAttribute("totalShowtimes", showtimes.size());
            model.addAttribute("customers", customers);
            
        } catch (Exception e) {
            model.addAttribute("isConnected", false);
            model.addAttribute("connectionInfo", "MySQL Database - Connection Failed");
            model.addAttribute("error", "Lỗi kết nối database: " + e.getMessage());
            model.addAttribute("totalCustomers", 0);
            model.addAttribute("totalMovies", 0);
            model.addAttribute("totalTickets", 0);
            model.addAttribute("totalShowtimes", 0);
            model.addAttribute("customers", null);
        }
        
        return "db-status";
    }
}
