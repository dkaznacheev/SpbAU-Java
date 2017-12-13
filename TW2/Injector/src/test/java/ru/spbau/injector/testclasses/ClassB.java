package ru.spbau.injector.testclasses;

public class ClassB {
    public ClassC dependency;

    public ClassB(ClassC dependency) {
        this.dependency = dependency;
    }
}
