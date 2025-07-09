package btvn.Polymorphism.ex9;

public class Mouse extends Rodent {
    @Override
    public void eat() {
        System.out.println("Mouse.eat()");
    }

    @Override
    public void run() {
        System.out.println("Mouse.run()");
    }

    @Override
    public void sleep() {
        System.out.println("Mouse.sleep()");
    }
}