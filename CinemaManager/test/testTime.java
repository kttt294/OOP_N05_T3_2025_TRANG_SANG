package test;

import model.Time;

public class testTime {
    public static void test(){
        Time t1 = new Time();
        Time t2 = new Time(13,54,20);

        System.out.println("Thoi gian mac dinh" + t1);
        System.out.println("Thoi gian thuc:" + t2);
    }
}