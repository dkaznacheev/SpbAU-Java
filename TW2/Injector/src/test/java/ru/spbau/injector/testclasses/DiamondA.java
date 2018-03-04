package ru.spbau.injector.testclasses;

public class DiamondA {
    public DiamondB dependencyB;
    public DiamondC dependencyC;

    public DiamondA(DiamondB dependencyB, DiamondC dependencyC) {
        this.dependencyB = dependencyB;
        this.dependencyC = dependencyC;
    }
}
