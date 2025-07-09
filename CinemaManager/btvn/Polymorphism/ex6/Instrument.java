package btvn.Polymorphism.ex6;

public class Instrument {
    public void play(Note n) {
        System.out.println("Instrument.play() " + n);
    }

    @Override
    public String toString() {
        return "Instrument";
    }

    public void adjust() {
        System.out.println("Adjusting Instrument");
    }
}
