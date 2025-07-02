package test;
import model.Time;

public class testTime {
    public static void test(){
        Time t1 = new Time(2, 7, 2025, 15, 40, 00);
        Time t2 = new Time(2, 7, 2025,13,54,20);

        System.out.println("Thời gian bắt đầu: " + t2);
        System.out.println("Thời gian kết thúc: " + t1);
    }
}