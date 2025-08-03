public class Person {
    private String name;

    public Person(){};
    public Person(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }

    public void setName(String s){
        name = s;
    }
}