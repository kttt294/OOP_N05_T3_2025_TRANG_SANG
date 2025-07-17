
public abstract class Nguoi {
    private String CCCD;
    private String ten;
    private int tuoi;
    private String sdt;
    private String email;

    public Nguoi() {}
    public Nguoi(String CCCD, String ten, int tuoi, String sdt, String email) {
        this.CCCD = CCCD;
        this.ten = ten;
        this.tuoi = tuoi;
        this.sdt = sdt;
        this.email = email;
    }
    public String getCCCD() { return CCCD; }
    public void setCCCD(String CCCD) { this.CCCD = CCCD; }
    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }
    public int getTuoi() { return tuoi; }
    public void setTuoi(int tuoi) { this.tuoi = tuoi; }
    public String getSdt() { return sdt; }
    public void setSdt(String sdt) { this.sdt = sdt; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public abstract String getLoaiNguoi();
    public abstract void hienThiThongTin();
}