package ru.spbau.injector.testclasses;

public class ClassDependsOnBase {
    public ClassDependsOnBase(ClassBase dependency) {
        this.dependency = dependency;
    }

    ClassBase dependency;
}
