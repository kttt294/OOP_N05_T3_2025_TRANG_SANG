package btvn.InitialisationAndCleanup.ex2;

public class ex2 {
    String s1 = "Giá trị khởi tạo tại chỗ";
    String s2;

    // Constructor
    public ex2(String s) {
        s2 = s;
    }

    public void printStrings() {
        System.out.println("s1: " + s1);
        System.out.println("s2: " + s2);
    }
}
