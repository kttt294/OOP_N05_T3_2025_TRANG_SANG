package btvn.Polymorphism.ex2;
import btvn.Polymorphism.ex2.shape.RandomShapeGenerator;
import btvn.Polymorphism.ex2.shape.Shape;

public class ShapeTest {
    private static RandomShapeGenerator gen = new RandomShapeGenerator();

    public static void test() {
        Shape[] shapes = new Shape[9];

        for (int i = 0; i < shapes.length; i++) {
            shapes[i] = gen.next(); // Random Shape
        }

        for (Shape s : shapes) {
            s.draw(); // kiểm tra tính đa hình
        }
    }
}