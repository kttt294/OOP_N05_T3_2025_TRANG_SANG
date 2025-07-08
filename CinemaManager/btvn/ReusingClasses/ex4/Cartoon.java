package btvn.ReusingClasses.ex4;

// import static net.mindview.util.Print.*;
import static java.lang.System.out;


class Art {
    Art() {
        System.out.print("Art constructor");
    }
}

class Drawing extends Art {
    Drawing() {
        System.out.print("Drawing constructor");
    }
}

public class Cartoon extends Drawing {
    // tạo lại constructor cho Cartoon() để chứng minh base-class luôn được gọi trước derived class
    public Cartoon() { 
        System.out.print("Cartoon constructor"); 
    } 
}