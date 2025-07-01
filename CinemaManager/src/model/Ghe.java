package model;

import java.util.Objects;

public class Ghe {
    String maGhe;
    int hang;
    int cot;
    boolean daDat;

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
