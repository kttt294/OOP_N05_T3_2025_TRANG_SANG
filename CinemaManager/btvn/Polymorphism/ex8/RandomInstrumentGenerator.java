package btvn.Polymorphism.ex8;

import btvn.Polymorphism.ex6.*;
import btvn.Polymorphism.ex7.Electronic;
import java.util.Random;

public class RandomInstrumentGenerator {
    private Random rand = new Random();

    public Instrument next() {
        switch (rand.nextInt(6)) {
            case 0: return new Wind();
            case 1: return new Percussion();
            case 2: return new Stringed();
            case 3: return new Brass();
            case 4: return new Woodwind();
            case 5: return new Electronic(); // mới thêm ở bài 7
            default: return new Wind(); // fallback
        }
    }
}