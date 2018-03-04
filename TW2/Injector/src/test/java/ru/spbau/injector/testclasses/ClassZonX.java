package ru.spbau.injector.testclasses;

public class ClassZonX {
    public ClassZonX(ClassXonY dependency) {
        this.dependency = dependency;
    }

    public ClassXonY dependency;
}
