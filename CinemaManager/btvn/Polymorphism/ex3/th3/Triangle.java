package btvn.Polymorphism.ex3.th3;

import btvn.Polymorphism.ex3.th1.Shape;

public class Triangle extends Shape {
    @Override
    public void draw() {
        System.out.println("Triangle.draw()");
    }

    @Override
    public void erase() {
        System.out.println("Triangle.erase()");
    }
}