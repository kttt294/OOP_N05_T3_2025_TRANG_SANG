package btvn.Polymorphism.ex1;

public class CycleTest {
    public static void test() {
        Unicycle u = new Unicycle();
        Bicycle b = new Bicycle();
        Tricycle t = new Tricycle();

        // Upcasting
        Cycle c1 = u;
        Cycle c2 = b;
        Cycle c3 = t;

        // Polymorphic method calls
        c1.ride(); // Output: Riding a unicycle
        c2.ride(); // Output: Riding a bicycle
        c3.ride(); // Output: Riding a tricycle
    }
}
