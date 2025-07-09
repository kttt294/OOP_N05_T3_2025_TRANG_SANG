package btvn.Polymorphism.ex10;

public class Base {
    public void method1() {
        System.out.println("Base.method1() calling method2():");
        method2(); // Gọi qua this → sẽ gọi phiên bản override nếu có
    }

    public void method2() {
        System.out.println("Base.method2()");
    }
}
