package com.example.servingwebcontent.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class aivenConnection {
  
    public void aivenConn() {
                Connection conn = null;
        try {
            myDBConnection mydb = new myDBConnection();
            conn = mydb.getOnlyConn();
            Statement sta = conn.createStatement();
            ResultSet reset = sta.executeQuery("select * from phim");
            System.out.println("Hiển thị dữ liệu phim từ database: ");
            while (reset.next()) {
                String maPhim = reset.getString("maPhim");
                String tenPhim = reset.getString("tenPhim");
                String theLoai = reset.getString("theLoai");
                System.out.println("Mã phim: " + maPhim + " | Tên phim: " + tenPhim + " | Thể loại: " + theLoai);
            }

            reset.close();
            sta.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi kết nối database: " + e);
            e.printStackTrace();
        }
    }
}
