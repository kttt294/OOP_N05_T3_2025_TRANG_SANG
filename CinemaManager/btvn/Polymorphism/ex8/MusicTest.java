package btvn.Polymorphism.ex8;

import btvn.Polymorphism.ex6.*;

import btvn.Polymorphism.ex7.Electronic;

public class MusicTest {
    public static void tune(Instrument i) {
        i.play(Note.MIDDLE_C);    // Gọi play() thông qua đa hình
    }

    public static void tuneAll(Instrument[] e) {
        for (Instrument i : e) {
            tune(i);    // Gọi tune() trên mảng nhạc cụ
        }
    }

    public static void test() {
        RandomInstrumentGenerator gen = new RandomInstrumentGenerator();
        Instrument[] orchestra = new Instrument[10];

        // Sinh 10 nhạc cụ ngẫu nhiên
        for (int i = 0; i < orchestra.length; i++) {
            orchestra[i] = gen.next();
        }

        tuneAll(orchestra); // Gọi play() cho từng nhạc cụ
    }
}
