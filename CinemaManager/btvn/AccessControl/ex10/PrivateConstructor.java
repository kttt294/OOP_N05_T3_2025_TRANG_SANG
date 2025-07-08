package btvn.AccessControl.ex10;

public class PrivateConstructor {
    private PrivateConstructor() {
        System.out.println("Secret constructor called!");
    }

    public static PrivateConstructor createInstance() {
        return new PrivateConstructor();
    }
    
    public void sayHello() {
        System.out.println("Hello from secret instance!");
    }
}