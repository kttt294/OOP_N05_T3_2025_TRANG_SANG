package controller;

import java.util.ArrayList;
import model.KhachHang;
import model.Ve;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

<<<<<<< HEAD
public class DatVe {
=======
public class DatVe{
>>>>>>> 8cd6e89d87aa971c828bf8a964449e4996e0481a
    private String maDatVe;
    private KhachHang khachHang;
    private List<Ve> danhSachVe;
    private double tongTien;
    private LocalDateTime thoiGianDat;

    public DatVe(){};

    public DatVe(String maDatVe, KhachHang khachHang, List<Ve> danhSachVe, double tongTien, LocalDateTime thoiGianDat) {
        this.maDatVe = maDatVe;
        this.khachHang = khachHang;
        this.danhSachVe = (danhSachVe != null) ? danhSachVe : new ArrayList<>();
        this.tongTien = tongTien;
        this.thoiGianDat = thoiGianDat;
    }

    public String getMaDatVe() {
        return maDatVe;
    }

    public void setMaDatVe(String maDatVe) {
        this.maDatVe = maDatVe;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public List<Ve> getDanhSachVe() {
        return danhSachVe;
    }

    public void setDanhSachVe(List<Ve> danhSachVe) {
        this.danhSachVe = (danhSachVe != null) ? danhSachVe : new ArrayList<>();
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public LocalDateTime getThoiGianDat() {
        return thoiGianDat;
    }

    public void setThoiGianDat(LocalDateTime thoiGianDat) {
        this.thoiGianDat = thoiGianDat;
    }
}
