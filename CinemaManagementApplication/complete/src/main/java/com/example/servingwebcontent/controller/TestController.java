package com.example.servingwebcontent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.servingwebcontent.database.myDBConnection;
import com.example.servingwebcontent.database.khachHangAiven;
import com.example.servingwebcontent.database.phimAiven;
import com.example.servingwebcontent.model.KhachHang;
import com.example.servingwebcontent.model.Phim;
import java.util.List;

@Controller
public class TestController {
    
    @Autowired
    private myDBConnection dbConnection;
    
    @Autowired
    private khachHangAiven khachHangDB;
    
    @Autowired
    private phimAiven phimDB;
    
    @GetMapping("/test")
    @ResponseBody
    public String testSystem() {
        StringBuilder result = new StringBuilder();
        result.append("<h2>System Test Results</h2>");
        
        // Test database connection
        result.append("<h3>1. Database Connection Test</h3>");
        boolean connectionTest = dbConnection.testConnection();
        result.append("Connection Test: ").append(connectionTest ? "SUCCESS" : "FAILED").append("<br>");
        result.append("Connection Info: ").append(dbConnection.getConnectionInfo()).append("<br><br>");
        
        // Test customer data
        result.append("<h3>2. Customer Data Test</h3>");
        try {
            List<KhachHang> customers = khachHangDB.getAllKhachHang();
            result.append("Total customers: ").append(customers.size()).append("<br>");
            
            if (!customers.isEmpty()) {
                result.append("<h4>Customer List:</h4>");
                result.append("<table border='1' style='border-collapse: collapse;'>");
                result.append("<tr><th>CCCD</th><th>Name</th><th>Age</th><th>Phone</th><th>Email</th></tr>");
                
                for (KhachHang customer : customers) {
                    result.append("<tr>");
                    result.append("<td>").append(customer.getCCCD()).append("</td>");
                    result.append("<td>").append(customer.getTen()).append("</td>");
                    result.append("<td>").append(customer.getTuoi()).append("</td>");
                    result.append("<td>").append(customer.getSdt()).append("</td>");
                    result.append("<td>").append(customer.getEmail()).append("</td>");
                    result.append("</tr>");
                }
                result.append("</table>");
            }
        } catch (Exception e) {
            result.append("Error with customer data: ").append(e.getMessage()).append("<br>");
        }
        
        // Test movie data
        result.append("<h3>3. Movie Data Test</h3>");
        try {
            List<Phim> movies = phimDB.getAllPhim();
            result.append("Total movies: ").append(movies.size()).append("<br>");
            
            if (!movies.isEmpty()) {
                result.append("<h4>Movie List:</h4>");
                result.append("<table border='1' style='border-collapse: collapse;'>");
                result.append("<tr><th>Code</th><th>Name</th><th>Genre</th><th>Duration</th><th>Language</th></tr>");
                
                for (Phim movie : movies) {
                    result.append("<tr>");
                    result.append("<td>").append(movie.getMaPhim()).append("</td>");
                    result.append("<td>").append(movie.getTenPhim()).append("</td>");
                    result.append("<td>").append(movie.getTheLoai()).append("</td>");
                    result.append("<td>").append(movie.getThoiLuong()).append(" min</td>");
                    result.append("<td>").append(movie.getNgonNgu()).append("</td>");
                    result.append("</tr>");
                }
                result.append("</table>");
            }
        } catch (Exception e) {
            result.append("Error with movie data: ").append(e.getMessage()).append("<br>");
        }
        
        // Test adding a sample movie
        result.append("<h3>4. Add Movie Test</h3>");
        try {
            Phim testMovie = new Phim("TEST001", "Phim Test", "Hành động", 120, "Tiếng Việt", 16, "Phim test để kiểm tra hệ thống");
            boolean addResult = phimDB.createPhim(testMovie);
            result.append("Add movie test: ").append(addResult ? "SUCCESS" : "FAILED").append("<br>");
        } catch (Exception e) {
            result.append("Error adding movie: ").append(e.getMessage()).append("<br>");
        }
        
        result.append("<br><a href='/'>Back to Home</a>");
        return result.toString();
    }
    
    @GetMapping("/test-add-phim")
    @ResponseBody
    public String testAddPhim() {
        try {
            Phim testMovie = new Phim("TEST001", "Phim Test", "Hành động", 120, "Tiếng Việt", 16, "Phim test để kiểm tra hệ thống");
            boolean result = phimDB.createPhim(testMovie);
            return "Add movie result: " + (result ? "SUCCESS" : "FAILED");
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
    
    @GetMapping("/test-add-khachhang")
    @ResponseBody
    public String testAddKhachHang() {
        try {
            KhachHang testCustomer = new KhachHang("TEST001", "Nguyễn Văn Test", 25, "0123456789", "test@example.com", "Nam");
            testCustomer.setDiaChi("123 Đường Test, TP.HCM");
            testCustomer.setNgheNghiep("Sinh viên");
            testCustomer.setNgaySinh("1999-01-01");
            testCustomer.setSoVisa("VN123456");
            
            boolean result = khachHangDB.createKhachHang(testCustomer);
            return "Add customer result: " + (result ? "SUCCESS" : "FAILED");
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
} 