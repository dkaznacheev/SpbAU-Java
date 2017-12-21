package ru.spbau.reflector.autoClasses;

public abstract class MyClass implements Base, SecondBase {
	public int a;
	public static String s;

	public class Inner {
		public void methodInner() {
		}

	}
	public static class Nested {
		public int x;
		public int y;
	}

	public static int method(int var1, int var2, String var3) {
		return 0;
	}

	protected abstract String method_irl();

	public static void method2() {
	}

}
