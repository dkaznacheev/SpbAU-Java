package ru.spbau.injector.testclasses;

public class ClassXonX {
    ClassXonX dependency;

    public ClassXonX(ClassXonX dependency) {
        this.dependency = dependency;
    }
}
