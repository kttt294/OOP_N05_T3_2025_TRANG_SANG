import java.util.List;

public class PhongChieu {
    private String maPhong;
    private String tenPhong;
    private int soHangGhe;
    private int soCotGhe;
    private List<Ghe> danhSachGheTrong;

    public PhongChieu(){};

    public PhongChieu(String maPhong, String tenPhong) {
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
    }

    public PhongChieu(String maPhong, String tenPhong, int soHangGhe, int soCotGhe) {
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
        this.soHangGhe = soHangGhe;
        this.soCotGhe = soCotGhe;
    }
    public void setTenPhong(String tenPhong){
        this.tenPhong = tenPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setMaPhong(String maPhong){
        this.maPhong = maPhong;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setSoHangGhe(int soHang){
        this.soHangGhe = soHang;
    }    

    public int getSoHangGhe() {
        return soHangGhe;
    }

    public void setSoCotGhe(int soCot){
        this.soCotGhe = soCot;
    }

    public int getSoCotGhe() {
        return soCotGhe;
    }

    public void setDanhSachGheTrong(List<Ghe> ds){
        this.danhSachGheTrong = ds;
    }

    public List<Ghe> getDanhSachGheTrong(){
        return danhSachGheTrong;
    }
}
