package controller;

import java.util.ArrayList;
import java.util.List;
import model.Phim;
import model.PhongChieu;
import model.SuatChieu;
import model.KhachHang;

public class System {
    private static System instance;

    private List<Phim> dsPhim;
    private List<PhongChieu> dsPhongChieu;
    private List<SuatChieu> dsSuatChieu;
    private List<KhachHang> dsKhachHang;

    public System() {
        dsPhim = new ArrayList<>();
        dsPhongChieu = new ArrayList<>();
        dsSuatChieu = new ArrayList<>();
        dsKhachHang = new ArrayList<>();
    }

    public static System getInstance() {
        if (instance == null) {
            instance = new System();
        }
        return instance;
    }

    public void themPhim(Phim phim) {
        dsPhim.add(phim);
    }

    public List<Phim> getDsPhim() {
        return dsPhim;
    }

    public void setDsPhim(List<Phim> dsPhim) {
        this.dsPhim = dsPhim;
    }

    public void themPhongChieu(PhongChieu phong) {
        dsPhongChieu.add(phong);
    }

    public List<PhongChieu> getDsPhongChieu() {
        return dsPhongChieu;
    }

    public void setDsPhongChieu(List<PhongChieu> dsPhongChieu) {
        this.dsPhongChieu = dsPhongChieu;
    }

    public void themSuatChieu(SuatChieu suat) {
        dsSuatChieu.add(suat);
    }

    public List<SuatChieu> getDsSuatChieu() {
        return dsSuatChieu;
    }

    public void setDsSuatChieu(List<SuatChieu> dsSuatChieu) {
        this.dsSuatChieu = dsSuatChieu;
    }

    public void dangKyKhachHang(KhachHang khach) {
        dsKhachHang.add(khach);
    }

    public List<KhachHang> getDsKhachHang() {
        return dsKhachHang;
    }

    public void setDsKhachHang(List<KhachHang> dsKhachHang) {
        this.dsKhachHang = dsKhachHang;
    }
}
