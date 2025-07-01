public class Flower1 {
    int petalCount=0;
    String s = "";

    Flower1(int petals){
        petalCount = petals;
    }

    Flower1(String ss){
        s = ss;
    }

    Flower1(String s, int petals){
        this(petals);
        // this(s);
        this.s = s;
    }

    Flower1(){
        this("hi", 47);
    }
}
