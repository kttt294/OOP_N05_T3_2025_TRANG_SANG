package com.cinema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CinemaManagementApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(CinemaManagementApplication.class, args);
        System.out.println("🎬 Cinema Management System đã khởi động thành công!");
        System.out.println("🌐 Truy cập: http://localhost:8080");
    }
} 