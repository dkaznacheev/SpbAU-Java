package ru.spbau.injector.testclasses;

public class DiamondB {
    public DiamondD dependencyD;

    public DiamondB(DiamondD dependencyD) {
        this.dependencyD = dependencyD;
    }
}
