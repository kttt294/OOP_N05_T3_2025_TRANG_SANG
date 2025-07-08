package btvn.AccessControl.ex3;

import static btvn.AccessControl.ex3.debugoff.debug.*;

public class testEx3_debugoff {
    public static void test(){
        debug("This debug message should NOT appear.");
        System.out.println("This should appear WITHOUT any debug info.");
    }
}