package ru.spbau.reflector.testClasses;

import ru.spbau.reflector.Base;
import ru.spbau.reflector.SecondBase;

public abstract class MyClass implements Base, SecondBase{
    public int a;
    public static String s;

    public static int method(int a, int b, String c) {
        return 0;
    }

    public static void method2() {
    }

    abstract protected String method_irl();

    public static class Nested {
        public int x;
        public int y;
    }

    public class Inner {
        public void methodInner(){}
    }
}
