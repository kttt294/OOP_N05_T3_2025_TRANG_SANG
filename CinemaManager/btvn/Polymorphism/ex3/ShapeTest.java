package btvn.Polymorphism.ex3;

import btvn.Polymorphism.ex3.th1.Shape;
import btvn.Polymorphism.ex3.th1.RandomShapeGenerator;
// CASE 1
import btvn.Polymorphism.ex3.th1.Circle;
import btvn.Polymorphism.ex3.th1.Square;
import btvn.Polymorphism.ex3.th1.Triangle;

// CASE 2
// import btvn.Polymorphism.ex3.th2.Circle;
// import btnv.Polymorphism.ex3.th1.Square;
// import btnv.Polymorphism.ex3.th1.Triangle;

// CASE 3
// import btvn.Polymorphism.ex3.th2.Circle;
// import btvn.Polymorphism.ex3.th3.Square;
// import btvn.Polymorphism.ex3.th3.Triangle;



public class ShapeTest {
    private static RandomShapeGenerator gen = new RandomShapeGenerator();

    public static void test() {
        Shape[] shapes = new Shape[6];

        for (int i = 0; i < shapes.length; i++) {
            shapes[i] = gen.next();
        }

        for (Shape s : shapes) {
            s.message(); // Gọi phương thức message()
        }
    }
}