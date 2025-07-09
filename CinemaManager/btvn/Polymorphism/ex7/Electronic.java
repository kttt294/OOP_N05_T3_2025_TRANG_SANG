package btvn.Polymorphism.ex7;

import btvn.Polymorphism.ex6.Note;
import btvn.Polymorphism.ex6.Instrument;

    public class Electronic extends Instrument {
    @Override
    public void play(Note n) {
        System.out.println("Electronic.play() " + n);
    }

    @Override
    public String toString() {
        return "Electronic";
    }

    @Override
    public void adjust() {
        System.out.println("Adjusting Electronic");
    }
}
