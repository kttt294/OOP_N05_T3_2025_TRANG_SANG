package btvn.ReusingClasses.ex10;

public class Stem extends Root {
    Component1 c1;
    Component2 c2;
    Component3 c3;

    public Stem(String msg) {
        super(msg); // gọi constructor Root

        c1 = new Component1(msg + " (from Stem)");
        c2 = new Component2(msg + " (from Stem)");
        c3 = new Component3(msg + " (from Stem)");
        System.out.println("Stem constructor");
    }
}
