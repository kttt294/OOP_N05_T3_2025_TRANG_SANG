package btvn.ReusingClasses.ex2;

// NewDetergent kế thừa từ Detergent (mà Detergent đã kế thừa Cleanser)
public class NewDetergent extends Detergent {

    // Ghi đè phương thức scrub()
    @Override
    public void scrub() {
        append(" NewDetergent.scrub()"); // Hành động riêng của NewDetergent
        super.scrub(); // Gọi lại scrub() từ lớp Detergent
    }

    // Thêm phương thức mới: sterilize()
    public void sterilize() {
        append(" sterilize()"); // Hành động riêng để khử trùng
    }
}
