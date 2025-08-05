package com.example.servingwebcontent.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import com.example.servingwebcontent.model.Voucher;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class voucherAiven {
  
    public List<Voucher> getAllVoucher() {
        Connection conn = null;
        List<Voucher> danhSachVoucher = new ArrayList<>();
        try {

            myDBConnection mydb = new myDBConnection();

            conn = mydb.getOnlyConn();

             
            Statement sta = conn.createStatement();
            ResultSet reset = sta.executeQuery("select * from voucher");
            System.out.println("Lấy tất cả dữ liệu voucher từ database: ");
            while (reset.next()) {
                String maVoucher = reset.getString("maVoucher");
                String moTa = reset.getString("moTa");
                float phanTramGiamGia = reset.getFloat("phanTramGiamGia");
                String ngayBatDauStr = reset.getString("ngayBatDau");
                String ngayKetThucStr = reset.getString("ngayKetThuc");
                String soLuongConLai = reset.getString("soLuongConLai");
                String trangThai = reset.getString("trangThai");
                
                LocalDateTime ngayBatDau = null;
                LocalDateTime ngayKetThuc = null;
                
                if (ngayBatDauStr != null) {
                    ngayBatDau = LocalDateTime.parse(ngayBatDauStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                }
                if (ngayKetThucStr != null) {
                    ngayKetThuc = LocalDateTime.parse(ngayKetThucStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                }
                
                Voucher voucher = new Voucher(maVoucher, moTa, phanTramGiamGia, ngayBatDau, ngayKetThuc, soLuongConLai, trangThai);
                danhSachVoucher.add(voucher);
                System.out.println("Mã voucher: " + maVoucher + " | Mô tả: " + moTa + " | Giảm giá: " + phanTramGiamGia + "%");

            }

            reset.close();
            sta.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi lấy dữ liệu voucher: " + e);

            e.printStackTrace();
        }
        return danhSachVoucher;
    }
    
    public Voucher getVoucherById(String maVoucher) {
        Connection conn = null;
        Voucher voucher = null;
        try {

            myDBConnection mydb = new myDBConnection();

            conn = mydb.getOnlyConn();

             
            Statement sta = conn.createStatement();
            ResultSet reset = sta.executeQuery("select * from voucher where maVoucher = '" + maVoucher + "'");
            System.out.println("Tìm voucher theo mã: " + maVoucher);
            if (reset.next()) {
                String moTa = reset.getString("moTa");
                float phanTramGiamGia = reset.getFloat("phanTramGiamGia");
                String ngayBatDauStr = reset.getString("ngayBatDau");
                String ngayKetThucStr = reset.getString("ngayKetThuc");
                String soLuongConLai = reset.getString("soLuongConLai");
                String trangThai = reset.getString("trangThai");
                
                LocalDateTime ngayBatDau = null;
                LocalDateTime ngayKetThuc = null;
                
                if (ngayBatDauStr != null) {
                    ngayBatDau = LocalDateTime.parse(ngayBatDauStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                }
                if (ngayKetThucStr != null) {
                    ngayKetThuc = LocalDateTime.parse(ngayKetThucStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                }
                
                voucher = new Voucher(maVoucher, moTa, phanTramGiamGia, ngayBatDau, ngayKetThuc, soLuongConLai, trangThai);
                System.out.println("Tìm thấy voucher: " + maVoucher);

            }

            reset.close();
            sta.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi tìm voucher: " + e);

            e.printStackTrace();
        }
        return voucher;
    }
    
    public List<Voucher> getVoucherByTrangThai(String trangThai) {
        Connection conn = null;
        List<Voucher> danhSachVoucher = new ArrayList<>();
        try {

            myDBConnection mydb = new myDBConnection();

            conn = mydb.getOnlyConn();

             
            Statement sta = conn.createStatement();
            ResultSet reset = sta.executeQuery("select * from voucher where trangThai = '" + trangThai + "'");
            System.out.println("Tìm voucher theo trạng thái: " + trangThai);
            while (reset.next()) {
                String maVoucher = reset.getString("maVoucher");
                String moTa = reset.getString("moTa");
                float phanTramGiamGia = reset.getFloat("phanTramGiamGia");
                String ngayBatDauStr = reset.getString("ngayBatDau");
                String ngayKetThucStr = reset.getString("ngayKetThuc");
                String soLuongConLai = reset.getString("soLuongConLai");
                
                LocalDateTime ngayBatDau = null;
                LocalDateTime ngayKetThuc = null;
                
                if (ngayBatDauStr != null) {
                    ngayBatDau = LocalDateTime.parse(ngayBatDauStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                }
                if (ngayKetThucStr != null) {
                    ngayKetThuc = LocalDateTime.parse(ngayKetThucStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                }
                
                Voucher voucher = new Voucher(maVoucher, moTa, phanTramGiamGia, ngayBatDau, ngayKetThuc, soLuongConLai, trangThai);
                danhSachVoucher.add(voucher);
                System.out.println("Tìm thấy: " + maVoucher + " - " + moTa);

            }

            reset.close();
            sta.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi tìm voucher theo trạng thái: " + e);

            e.printStackTrace();
        }
        return danhSachVoucher;
    }
    
    public List<Voucher> getVoucherByPhanTramGiamGia(float phanTramMin, float phanTramMax) {
        Connection conn = null;
        List<Voucher> danhSachVoucher = new ArrayList<>();
        try {

            myDBConnection mydb = new myDBConnection();

            conn = mydb.getOnlyConn();

             
            Statement sta = conn.createStatement();
            ResultSet reset = sta.executeQuery("select * from voucher where phanTramGiamGia >= " + phanTramMin + " and phanTramGiamGia <= " + phanTramMax);
            System.out.println("Tìm voucher theo phần trăm giảm giá: " + phanTramMin + "% - " + phanTramMax + "%");
            while (reset.next()) {
                String maVoucher = reset.getString("maVoucher");
                String moTa = reset.getString("moTa");
                float phanTramGiamGia = reset.getFloat("phanTramGiamGia");
                String ngayBatDauStr = reset.getString("ngayBatDau");
                String ngayKetThucStr = reset.getString("ngayKetThuc");
                String soLuongConLai = reset.getString("soLuongConLai");
                String trangThai = reset.getString("trangThai");
                
                LocalDateTime ngayBatDau = null;
                LocalDateTime ngayKetThuc = null;
                
                if (ngayBatDauStr != null) {
                    ngayBatDau = LocalDateTime.parse(ngayBatDauStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                }
                if (ngayKetThucStr != null) {
                    ngayKetThuc = LocalDateTime.parse(ngayKetThucStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                }
                
                Voucher voucher = new Voucher(maVoucher, moTa, phanTramGiamGia, ngayBatDau, ngayKetThuc, soLuongConLai, trangThai);
                danhSachVoucher.add(voucher);
                System.out.println("Tìm thấy: " + maVoucher + " - Giảm giá: " + phanTramGiamGia + "%");

            }

            reset.close();
            sta.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi tìm voucher theo phần trăm giảm giá: " + e);

            e.printStackTrace();
        }
        return danhSachVoucher;
    }
} 