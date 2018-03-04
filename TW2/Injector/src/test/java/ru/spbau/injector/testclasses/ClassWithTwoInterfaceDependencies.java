package ru.spbau.injector.testclasses;

public class ClassWithTwoInterfaceDependencies {
    public InterfaceImpl dependencyA;
    public InterfaceAnotherImpl dependencyB;

    public ClassWithTwoInterfaceDependencies(InterfaceImpl dependencyA, InterfaceAnotherImpl dependencyB) {
        this.dependencyA = dependencyA;
        this.dependencyB = dependencyB;
    }
}
