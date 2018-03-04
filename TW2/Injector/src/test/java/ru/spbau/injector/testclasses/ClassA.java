package ru.spbau.injector.testclasses;

public class ClassA {
    public ClassB dependency;

    public ClassA(ClassB dependency) {
        this.dependency = dependency;
    }
}
