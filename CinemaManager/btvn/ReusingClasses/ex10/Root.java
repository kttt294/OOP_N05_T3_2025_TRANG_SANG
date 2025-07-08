package btvn.ReusingClasses.ex10;

public class Root {
    Component1 c1;
    Component2 c2;
    Component3 c3;

    public Root(String msg) {
        c1 = new Component1(msg + " (from Root)");
        c2 = new Component2(msg + " (from Root)");
        c3 = new Component3(msg + " (from Root)");
        System.out.println("Root constructor");
    }
}
