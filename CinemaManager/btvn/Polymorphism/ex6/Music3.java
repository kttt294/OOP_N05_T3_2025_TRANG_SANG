package btvn.Polymorphism.ex6;

public class Music3 {
    public static void tune(Instrument i) {
        i.play(Note.MIDDLE_C);
    }

    public static void tuneAll(Instrument[] e) {
        for (Instrument i : e) {
            tune(i);
        }
    }

    public static void test() {
        Instrument[] orchestra = {
                new Wind(),
                new Percussion(),
                new Stringed(),
                new Brass(),
                new Woodwind()
        };

        System.out.println("=== Playing Notes ===");
        tuneAll(orchestra);

        System.out.println("\n=== Printing Instruments ===");
        for (Instrument i : orchestra) {
            System.out.println(i); // tự động gọi toString()
        }
    }
}
