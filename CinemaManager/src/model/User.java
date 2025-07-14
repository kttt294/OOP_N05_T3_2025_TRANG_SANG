public class User {
    private String ten;
    private int tuoi;
    private String gioiTinh;  // Nam, Nu, Khac
    private String sdt;
    private String email;

    public void setTen(String ten){
        this.ten = ten;
    }
    public String getTen(){
        return ten;
    }

    public void setTuoi(int tuoi){
        this.tuoi = tuoi;
    }
    public int getTuoi(){
        return tuoi;
    }

    public void setGioiTinh(String gioiTinh){
        this.gioiTinh = gioiTinh;
    }
    public String getGioiTinh(){
        return gioiTinh;
    }

    public void setSDT(String sdt){
        this.sdt = sdt;
    }
    public String getSDT(){
        return sdt;
    }

    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }
}

