package btvn.ReusingClasses.ex2;

public class NewDetergentTest {
        public static void test() {
        NewDetergent x = new NewDetergent();
        x.dilute();      // Từ Cleanser
        x.apply();       // Từ Cleanser
        x.scrub();       // Đã override trong NewDetergent
        x.foam();        // Từ Detergent
        x.sterilize();   // Mới trong NewDetergent
        System.out.println(x); // In ra chuỗi trạng thái cuối
    }
}
