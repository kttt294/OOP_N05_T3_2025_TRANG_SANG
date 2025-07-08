package btvn.AccessControl.ex9;

import btvn.AccessControl.ex9.interfaceprocessor.Apply;
import btvn.AccessControl.ex9.filters.MyProcessor;

public class testEx9 {
    public static void test() {
        Apply.process(new MyProcessor(), "hello from ex9");
    }
}
