package ru.spbau.injector.testclasses;

public class ClassAonB {
    public ClassAonB(ClassBonA dependency) {
        this.dependency = dependency;
    }

    public ClassBonA dependency;
}
