package btvn.Polymorphism.ex9;

public class Gerbil extends Rodent {
    @Override
    public void eat() {
        System.out.println("Gerbil.eat()");
    }

    @Override
    public void run() {
        System.out.println("Gerbil.run()");
    }

    @Override
    public void sleep() {
        System.out.println("Gerbil.sleep()");
    }
}