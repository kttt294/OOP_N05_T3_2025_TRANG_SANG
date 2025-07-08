package btvn.AccessControl.ex6;

public class testEx6 {
    public static void test(){
        protectedData a = new protectedData();
        a.value = 100;
        a.display();
        System.out.println("Modified value: " + a.value);
    }
}
