package controller;

import java.util.ArrayList;
import java.util.List;
import model.Phim;
import model.PhongChieu;
import model.SuatChieu;
import model.KhachHang;


public class System {
    List<Phim> dsPhim;
    List<PhongChieu> dsPhongChieu;
    List<SuatChieu> dsSuatChieu;
    List<KhachHang> dsKhachHang;

    System(){
        dsPhim = new ArrayList<>();
        dsPhongChieu = new ArrayList<>();
        dsSuatChieu = new ArrayList<>();
        dsKhachHang = new ArrayList<>();
    }

    public static System getInstance(System instance){
        if (instance == null){
            instance = new System();
        }
        return instance;
    }

    public void themPhim(Phim phim){
        dsPhim.add(phim);
    }
    public List<Phim> layDsPhim(){
        return dsPhim;
    }

    public void themPhongChieu(PhongChieu phong){
        dsPhongChieu.add(phong);
    }
    public List<PhongChieu> layDsPhong(){
        return dsPhongChieu;
    }

    public void themSuatChieu(SuatChieu suat){
        dsSuatChieu.add(suat);
    }
    public List<SuatChieu> layDsSuat(){
        return dsSuatChieu;
    }

    public void dkyKhachHang(KhachHang khach){
        dsKhachHang.add(khach);
    }
    public List<KhachHang> layDsKhach(){
        return dsKhachHang;
    }
}
