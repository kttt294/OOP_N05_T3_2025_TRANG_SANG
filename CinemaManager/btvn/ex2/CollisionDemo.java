package btvn.ex2;

import btvn.ex2.net.mindview.simple.Vector;
import java.util.*;

public class CollisionDemo {
    public static void main(String[] args) {
        // Lỗi nếu dùng dòng này vì có 2 Vector từ 2 package
        Vector v = new Vector();

        /* Dùng rõ ràng lớp nào thì không lỗi:
        java.util.Vector<String> v1 = new java.util.Vector<>();
        btvn.ex2.net.mindview.simple.Vector v2 = new btvn.ex2.net.mindview.simple.Vector();
    */
    }
}
