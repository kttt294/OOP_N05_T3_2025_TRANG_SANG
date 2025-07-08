package btvn.AccessControl.ex5;

public class testEx5 {
    public static void test() {
        myClass obj = new myClass();

        System.out.println(obj.pubField);
        System.out.println(obj.proField);
        System.out.println(obj.defaultField);
        // System.out.println(obj.privField);

        obj.pubMethod(); 
        obj.proMethod(); 
        obj.defaultMethod();
        // obj.privMethod();   // Error: private
    } 
}
