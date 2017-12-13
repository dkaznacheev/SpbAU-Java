package ru.spbau.injector.testclasses;

public class ClassYonZ {
    public ClassYonZ(ClassZonX dependency) {
        this.dependency = dependency;
    }

    public ClassZonX dependency;
}
