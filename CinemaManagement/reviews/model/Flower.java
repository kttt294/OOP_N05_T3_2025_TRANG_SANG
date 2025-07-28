public class Flower {
    int petalCount=0;
    String s = "";

    Flower(int petals){
        petalCount = petals;
    }

    Flower(String ss){
        s = ss;
    }

    Flower(String s, int petals){
        this(petals);
        // this(s);
        this.s = s;
    }

    Flower(){
        this("hi", 47);
    }
}
