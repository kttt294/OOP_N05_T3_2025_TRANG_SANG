package btvn.Polymorphism.ex4;

import btvn.Polymorphism.ex3.th1.Shape;
import btvn.Polymorphism.ex3.th2.*;
import btvn.Polymorphism.ex3.th3.*;
import btvn.Polymorphism.ex4.Hexagon;
import java.util.Random;


public class RandomShapeGenerator {
    private Random rand = new Random(47);

    public Shape next() {
        switch (rand.nextInt(4)) { // 0–3
            case 0: return new Circle();
            case 1: return new Square();
            case 2: return new Triangle();
            case 3: return new Hexagon(); // thêm Hexagon
            default: return new Shape(); // fallback
        }
    }
}
