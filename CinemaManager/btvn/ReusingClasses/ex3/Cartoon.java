package btvn.ReusingClasses.ex3;

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
    // KHÔNG có constructor nào ở đây
    // public Cartoon() { print("Cartoon constructor"); } 
}
