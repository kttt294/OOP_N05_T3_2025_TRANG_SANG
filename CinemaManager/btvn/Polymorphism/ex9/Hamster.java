package btvn.Polymorphism.ex9;

public class Hamster extends Rodent {
    @Override
    public void eat() {
        System.out.println("Hamster.eat()");
    }

    @Override
    public void run() {
        System.out.println("Hamster.run()");
    }

    @Override
    public void sleep() {
        System.out.println("Hamster.sleep()");
    }
}