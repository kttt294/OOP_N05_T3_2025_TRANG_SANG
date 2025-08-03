public class Leaf {
    int i=0;
    public Leaf increment(){
        i++;
        return this;
    }
    
    public void print(){
        System.out.println("i = " + i);
    }
}
