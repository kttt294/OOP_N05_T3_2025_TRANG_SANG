package btvn.AccessControl.ex4.package2;

import btvn.AccessControl.ex4.package1.Parent;

public class otherPackageCaller {
    public static void main(String[] args) {
        Parent p = new Parent();
    //  p.showMessage();  // ERROR: showMessage() has protected access
    }   
}