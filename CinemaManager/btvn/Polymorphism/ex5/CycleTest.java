package btvn.Polymorphism.ex5;

public class CycleTest {
    public static void ride(Cycle c) {
        c.ride(); // gọi ride() → gọi wheels() bên trong
    }

    public static void test() {
        Cycle[] cycles = {
            new Unicycle(),
            new Bicycle(),
            new Tricycle()
        };

        for (Cycle c : cycles) {
            ride(c); // kiểm tra tính đa hình
        }
    }
}