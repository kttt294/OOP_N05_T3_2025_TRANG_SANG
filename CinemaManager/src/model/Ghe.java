package model;

import java.util.Objects;

public class Ghe {
    String maGhe;

    public Ghe(String maGhe) {
        this.maGhe = maGhe;
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
