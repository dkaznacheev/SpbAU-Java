package ru.spbau.injector.testclasses;

public class DiamondC {
    public DiamondD dependencyD;

    public DiamondC(DiamondD dependencyD) {
        this.dependencyD = dependencyD;
    }
}
