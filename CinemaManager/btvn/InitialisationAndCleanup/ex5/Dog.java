package btvn.InitialisationAndCleanup.ex5;

public class Dog {

    void bark() {
        System.out.println("Dog is barking normally");
    }

    void bark(int i) {
        System.out.println("Dog barks loudly: int " + i);
    }

    void bark(boolean b) {
        System.out.println("Dog is confused: boolean " + b);
    }

    void bark(char c) {
        System.out.println("Dog makes a sharp bark: char " + c);
    }

    void bark(float f) {
        System.out.println("Dog howls softly: float " + f);
    }

    void bark(double d) {
        System.out.println("Dog growls: double " + d);
    }

    void bark(byte b) {
        System.out.println("Dog yaps: byte " + b);
    }

    void bark(short s) {
        System.out.println("Dog whines: short " + s);
    }

    void bark(long l) {
        System.out.println("Dog roars deeply: long " + l);
    }
}
