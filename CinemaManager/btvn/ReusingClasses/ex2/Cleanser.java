package btvn.ReusingClasses.ex2;

public class Cleanser {
    private String s = "Cleanser"; // Biến lưu trạng thái dưới dạng chuỗi

    public void append(String a) {
        s += a; // Nối chuỗi vào trạng thái
    }

    public void dilute() {
        append(" dilute()"); // Mô phỏng pha loãng
    }

    public void apply() {
        append(" apply()"); // Mô phỏng áp dụng
    }

    public void scrub() {
        append(" scrub()"); // Mô phỏng chà rửa
    }

    public String toString() {
        return s; // Trả về trạng thái hiện tại
    }
}
