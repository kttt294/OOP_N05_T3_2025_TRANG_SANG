package com.example.servingwebcontent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.servingwebcontent.database.myDBConnection;
import com.example.servingwebcontent.database.khachHangAiven;
import com.example.servingwebcontent.model.KhachHang;
import java.util.List;

@Controller
public class DatabaseTestController {
    
    @Autowired
    private myDBConnection dbConnection;
    
    @Autowired
    private khachHangAiven khachHangDB;
    
    @GetMapping("/test-db")
    @ResponseBody
    public String testDatabase() {
        StringBuilder result = new StringBuilder();
        result.append("<h2>Database Connection Test</h2>");
        
        // Test basic connection
        result.append("<h3>1. Testing Basic Connection</h3>");
        boolean connectionTest = dbConnection.testConnection();
        result.append("Connection Test: ").append(connectionTest ? "SUCCESS" : "FAILED").append("<br>");
        result.append("Connection Info: ").append(dbConnection.getConnectionInfo()).append("<br><br>");
        
        // Test customer data retrieval
        result.append("<h3>2. Testing Customer Data Retrieval</h3>");
        try {
            List<KhachHang> customers = khachHangDB.getAllKhachHang();
            result.append("Total customers found: ").append(customers.size()).append("<br>");
            
            if (!customers.isEmpty()) {
                result.append("<h4>Customer List:</h4>");
                result.append("<table border='1' style='border-collapse: collapse;'>");
                result.append("<tr><th>CCCD</th><th>Name</th><th>Age</th><th>Phone</th><th>Email</th><th>Gender</th></tr>");
                
                for (KhachHang customer : customers) {
                    result.append("<tr>");
                    result.append("<td>").append(customer.getCCCD()).append("</td>");
                    result.append("<td>").append(customer.getTen()).append("</td>");
                    result.append("<td>").append(customer.getTuoi()).append("</td>");
                    result.append("<td>").append(customer.getSdt()).append("</td>");
                    result.append("<td>").append(customer.getEmail()).append("</td>");
                    result.append("<td>").append(customer.getGioiTinh()).append("</td>");
                    result.append("</tr>");
                }
                result.append("</table>");
            } else {
                result.append("No customers found in database.<br>");
            }
            
        } catch (Exception e) {
            result.append("Error retrieving customer data: ").append(e.getMessage()).append("<br>");
            e.printStackTrace();
        }
        
        // Test statistics
        result.append("<h3>3. Testing Statistics</h3>");
        try {
            int totalCustomers = khachHangDB.getTotalKhachHang();
            result.append("Total customers (from statistics): ").append(totalCustomers).append("<br>");
        } catch (Exception e) {
            result.append("Error getting statistics: ").append(e.getMessage()).append("<br>");
        }
        
        result.append("<br><a href='/'>Back to Home</a>");
        return result.toString();
    }
    
    @GetMapping("/db-status")
    public String databaseStatus(Model model) {
        boolean isConnected = dbConnection.testConnection();
        String connectionInfo = dbConnection.getConnectionInfo();
        
        model.addAttribute("isConnected", isConnected);
        model.addAttribute("connectionInfo", connectionInfo);
        
        if (isConnected) {
            try {
                List<KhachHang> customers = khachHangDB.getAllKhachHang();
                model.addAttribute("totalCustomers", customers.size());
                model.addAttribute("customers", customers);
            } catch (Exception e) {
                model.addAttribute("error", "Error retrieving data: " + e.getMessage());
            }
        }
        
        return "db-status";
    }
} 