import java.util.Objects;

public class Ghe {
    private String maGhe;
    private int hang;
    private int cot;
    private boolean daDat;

    public Ghe(){};

    public Ghe(String maGhe){
        this.maGhe = maGhe;
    }
    public Ghe(String maGhe, int hang, int cot) {
        this.maGhe = maGhe;
        this.hang = hang;
        this.cot = cot;
    }

    public String getMaGhe() {
        return maGhe;
    }
    public void setMaGhe(String maGhe) {
        this.maGhe = maGhe;
    }
    public int getHang() {
        return hang;
    }
    public void setHang(int hang) {
        this.hang = hang;
    }
    public int getCot() {
        return cot;
    }
    public void setCot(int cot) {
        this.cot = cot;
    }
    public boolean isDaDat() {
        return daDat;
    }
    public void setDaDat(boolean daDat) {
        this.daDat = daDat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ghe)) return false;
        Ghe ghe = (Ghe) o;
        return Objects.equals(maGhe, ghe.maGhe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maGhe);
    }
}
