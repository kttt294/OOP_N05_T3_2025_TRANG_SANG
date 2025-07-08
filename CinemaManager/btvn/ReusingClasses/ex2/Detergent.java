package btvn.ReusingClasses.ex2;

// Kế thừa đúng từ Cleanser
public class Detergent extends Cleanser {

    // Ghi đè phương thức scrub()
    @Override
    public void scrub() {
        append(" Detergent.scrub()"); // Hành động riêng của Detergent
        super.scrub(); // Gọi scrub() của lớp Cleanser
    }

    // Thêm phương thức foam() mới
    public void foam() {
        append(" foam()"); // Hành động riêng của Detergent
    }
}
