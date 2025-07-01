package controller;

import model.KhachHang;
import model.Ve;
import java.util.List;
import java.time.LocalDateTime;

public class DatVe{
    String maDatVe;
    KhachHang khachHang;
    List<Ve> danhSachVe;
    double tongTien;
    LocalDateTime thoiGianDat;

    DatVe(){};

    public DatVe(String maDatVe, KhachHang khachHang, List<Ve> danhSachVe, double tongTien, LocalDateTime thoiGianDat){
        this.maDatVe = maDatVe;
        this.khachHang = khachHang;
        this.tongTien = tongTien;
        this.thoiGianDat = thoiGianDat;
        this.danhSachVe = (danhSachVe != null) ? danhSachVe : new ArrayList<>();
    }
}