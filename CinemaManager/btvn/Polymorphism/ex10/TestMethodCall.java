package btvn.Polymorphism.ex10;

public class TestMethodCall {
    public static void test() {
        Base obj = new Derived(); // upcasting
        obj.method1();
    }
}
