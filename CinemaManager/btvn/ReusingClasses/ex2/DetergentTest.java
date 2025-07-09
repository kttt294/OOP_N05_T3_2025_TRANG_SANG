package btvn.ReusingClasses.ex2;

public class DetergentTest {
        public static void test() {
        Detergent x = new Detergent();
        x.dilute();  // Từ Cleanser
        x.apply();   // Từ Cleanser
        x.scrub();   // Đã được override trong Detergent
        x.foam();    // Phương thức riêng của Detergent
        System.out.println(x);
    }
}
