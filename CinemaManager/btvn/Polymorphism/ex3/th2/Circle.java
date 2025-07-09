package btvn.Polymorphism.ex3.th2;

import btvn.Polymorphism.ex3.th1.Shape;


public class Circle extends Shape {
    @Override
    public void draw() {
        System.out.println("Circle.draw()");
    }

    @Override
    public void erase() {
        System.out.println("Circle.erase()");
    }

    @Override
    public void message() {
        System.out.println("This is Circle.message()");
    }
}