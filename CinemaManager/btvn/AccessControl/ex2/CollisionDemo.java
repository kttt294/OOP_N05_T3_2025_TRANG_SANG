package btvn.AccessControl.ex2;

import java.util.*;

import btvn.AccessControl.ex2.net.mindview.simple.Vector;

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