package btvn.AccessControl.ex9.filters;

import btvn.AccessControl.ex9.interfaceprocessor.Processor;

public class MyProcessor implements Processor {
    public String name() {
        return "UppercaseProcessor";
    }

    public Object process(Object input) {
        return ((String) input).toUpperCase();
    }
}
