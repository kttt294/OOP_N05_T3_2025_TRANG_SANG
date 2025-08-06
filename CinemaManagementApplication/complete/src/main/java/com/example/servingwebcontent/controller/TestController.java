package com.example.servingwebcontent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.servingwebcontent.database.myDBConnection;
import com.example.servingwebcontent.database.phimAiven;
import com.example.servingwebcontent.model.Phim;
import java.util.List;

@Controller
public class TestController {
    
    @Autowired
    private myDBConnection dbConnection;
    
    @Autowired
    private phimAiven phimDB;
    
    @GetMapping("/test-db")
    public String testDatabase(Model model) {
        try {
            // Test connection
            boolean isConnected = dbConnection.testConnection();
            model.addAttribute("connectionStatus", isConnected ? "Thành công" : "Thất bại");
            model.addAttribute("connectionInfo", dbConnection.getConnectionInfo());
            
            if (isConnected) {
                // Test getting data
                List<Phim> dsPhim = phimDB.getAllPhim();
                model.addAttribute("phimCount", dsPhim.size());
                model.addAttribute("dsPhim", dsPhim);
                
                // Log for debugging
                System.out.println("Số lượng phim trong database: " + dsPhim.size());
                for (Phim phim : dsPhim) {
                    System.out.println("Phim: " + phim.getMaPhim() + " - " + phim.getTenPhim());
                }
            }
            
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
        
        return "test-db";
    }
} 