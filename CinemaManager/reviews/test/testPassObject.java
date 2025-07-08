package reviews.test;

import reviews.model.Number;
import reviews.model.PassObject;

public class testPassObject {
    public static void test1(){
        Number n = new Number();
        n.i = 14;
        PassObject.f(n);
        System.out.println(n.i);
    }
}
