package btvn.Polymorphism.ex4;

import btvn.Polymorphism.ex3.th1.Shape;

public class Hexagon extends Shape {
    @Override
    public void draw() {
        System.out.println("Hexagon.draw()");
    }

    @Override
    public void erase() {
        System.out.println("Hexagon.erase()");
    }
}