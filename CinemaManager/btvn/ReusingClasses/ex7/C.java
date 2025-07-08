package btvn.ReusingClasses.ex7;

public class C extends A{
    public B b;

    public C(){
        super(10);
        b = new B("B");
        System.out.println("C constructor");
    }
}
