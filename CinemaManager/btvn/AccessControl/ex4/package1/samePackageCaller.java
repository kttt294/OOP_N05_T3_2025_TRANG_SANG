package btvn.AccessControl.ex4.package1;

public class samePackageCaller {
    public static void test() {
        Parent p = new Parent();
        p.showMessage();
    }   
}