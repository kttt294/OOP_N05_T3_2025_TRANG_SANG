package reviews.test;

import reviews.model.Leaf;

public class testLeaf {
    public static void test(){
        Leaf x = new Leaf();
        x.increment().increment().increment().print();
    }
}
