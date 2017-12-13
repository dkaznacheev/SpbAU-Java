package ru.spbau.injector.testclasses;

public class ClassXonY {
    public ClassXonY(ClassYonZ dependency) {
        this.dependency = dependency;
    }

    public ClassYonZ dependency;
}
