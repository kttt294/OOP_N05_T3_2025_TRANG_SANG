package btvn.InitialisationAndCleanup.ex5;

public class testEx5 {
    public static void test() {
        Dog d = new Dog();
        d.bark();
        d.bark(5);
        d.bark(true);
        d.bark('a');
        d.bark(3.14f);
        d.bark(2.71828);
        d.bark((byte) 1);
        d.bark((short) 2);
        d.bark(10000000000L);
    }
}
