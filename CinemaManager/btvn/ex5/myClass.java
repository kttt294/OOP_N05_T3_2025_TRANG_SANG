package btvn.ex5;

public class myClass {
    public int pubField = 1;
    protected int proField = 2;
    int defaultField = 3;        // package-access (default)
    private int privField = 4;

    public void pubMethod() {
        System.out.println("Public method");
    }

    protected void proMethod() {
        System.out.println("Protected method");
    }

    void defaultMethod() {
        System.out.println("Default/package-access method");
    }

    private void privMethod() {
        System.out.println("Private method");
    }
}
