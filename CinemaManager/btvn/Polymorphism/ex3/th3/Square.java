package btvn.Polymorphism.ex3.th3;

import btvn.Polymorphism.ex3.th1.Shape;

public class Square extends Shape {
    @Override
    public void draw() {
        System.out.println("Square.draw()");
    }

    @Override
    public void erase() {
        System.out.println("Square.erase()");
    }
}