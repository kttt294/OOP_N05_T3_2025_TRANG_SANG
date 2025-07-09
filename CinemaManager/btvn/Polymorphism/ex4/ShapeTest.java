package btvn.Polymorphism.ex4;
import btvn.Polymorphism.ex3.th1.Shape;

public class ShapeTest {
    private static RandomShapeGenerator gen = new RandomShapeGenerator();

    public static void test() {
        Shape[] shapes = new Shape[10];

        for (int i = 0; i < shapes.length; i++) {
            shapes[i] = gen.next(); // ngẫu nhiên một trong 4 loại
        }

        for (Shape s : shapes) {
            s.draw(); // kiểm tra tính đa hình
        }
    }
}
