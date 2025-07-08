package btvn.ReusingClasses.ex8;

public class Derived extends Base{
    public Derived(){
        super(0);
        System.out.println("Derived default constructor");
    }

    public Derived(int y){
        super(y);
        System.out.println("Derived non-default constructor");
    }
}
