package btvn.Polymorphism.ex9;

public class RodentTest {
    public static void main(String[] args) {
        Rodent[] rodents = {
            new Mouse(),
            new Gerbil(),
            new Hamster()
        };

        for (Rodent r : rodents) {
            r.eat();
            r.run();
            r.sleep();
            System.out.println("-----");
        }
    }
}