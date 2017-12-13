package ru.spbau.injector.testclasses;

public class ClassBonA {
    public ClassBonA(ClassAonB dependency) {
        this.dependency = dependency;
    }

    ClassAonB dependency;
}
