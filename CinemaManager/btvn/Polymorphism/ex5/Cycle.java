package btvn.Polymorphism.ex5;

public class Cycle {
    public void ride() {
        System.out.println("Riding a cycle with " + wheels() + " wheel(s)");
    }

    public int wheels() {
        return 0; // chưa xác định ở lớp cha
    }
}
