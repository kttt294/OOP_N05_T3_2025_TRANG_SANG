package btvn.ReusingClasses.ex2;

public class CleanserTest {
        public static void test() {
        Cleanser x = new Cleanser();
        x.dilute();
        x.apply();
        x.scrub();
        System.out.println(x); // In ra chuỗi trạng thái
    }
}