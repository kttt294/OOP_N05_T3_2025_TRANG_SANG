package model;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class KhachHang extends TaiKhoan{
    String maKH;
    String tenKH;
    int tuoi;
    String sdt;
    String email;
    GioiTinh gioiTinh;
    List<Ve> lichSuDatVe;

    public KhachHang(String maKH, String tenKh, int tuoi, String sdt, String email, GioiTinh gioiTinh, List<Ve> lichSuDatVe){
    this.maKH = maKH;
    this.tenKH = tenKH;
    this.tuoi = tuoi;
    this.sdt = sdt;
    this.email = email;
    this.gioiTinh = gioiTinh;
    this.lichSuDatVe = lichSuDatVe;
    }

    public String getMaKH() {
    return maKH;
}

public void setMaKH(String maKH) {
    this.maKH = maKH;
}

public String getTenKH() {
    return tenKH;
}

public void setTenKH(String tenKH) {
    this.tenKH = tenKH;
}

public int getTuoi() {
    return tuoi;
}

public void setTuoi(int tuoi) {
    this.tuoi = tuoi;
}

public String getSdt() {
    return sdt;
}

public void setSdt(String sdt) {
    this.sdt = sdt;
}

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public GioiTinh getGioiTinh() {
    return gioiTinh;
}

public void setGioiTinh(GioiTinh gioiTinh) {
    this.gioiTinh = gioiTinh;
}

public List<Ve> getLichSuDatVe() {
    return lichSuDatVe;
}

public void setLichSuDatVe(List<Ve> lichSuDatVe) {
    this.lichSuDatVe = lichSuDatVe;
}

public void themVe(Ve ve) {
    this.lichSuDatVe.add(ve);
}
}


