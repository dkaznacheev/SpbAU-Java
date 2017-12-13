package ru.spbau.injector;

import org.junit.Test;
import static org.junit.Assert.*;
import ru.spbau.injector.testclasses.*;

import java.util.Arrays;
import java.util.Collections;


public class InjectorTest {
    private static final String PACKAGE = "ru.spbau.injector.testclasses.";
    @Test
    public void injectorShouldInitializeClassWithoutDependencies()
            throws Exception {
        Object object = Injector.initialize(PACKAGE + "ClassWithoutDependencies", Collections.emptyList());
        assertTrue(object instanceof ClassWithoutDependencies);
    }

    @Test
    public void injectorShouldInitializeClassWithOneClassDependency()
            throws Exception {
        Object object = Injector.initialize(
                PACKAGE + "ClassWithOneClassDependency",
                Collections.singletonList(PACKAGE + "ClassWithoutDependencies")
        );
        assertTrue(object instanceof ClassWithOneClassDependency);
        ClassWithOneClassDependency instance = (ClassWithOneClassDependency) object;
        assertTrue(instance.dependency != null);
    }

    @Test
    public void injectorShouldInitializeClassWithOneInterfaceDependency()
            throws Exception {
        Object object = Injector.initialize(
                PACKAGE + "ClassWithOneInterfaceDependency",
                Collections.singletonList(PACKAGE + "InterfaceImpl")
        );
        assertTrue(object instanceof ClassWithOneInterfaceDependency);
        ClassWithOneInterfaceDependency instance = (ClassWithOneInterfaceDependency) object;
        assertTrue(instance.dependency instanceof InterfaceImpl);
    }
    @Test (expected = InjectionCycleException.class)
    public void injectionCycleThrows()
            throws Exception {
        Object object = Injector.initialize(
                PACKAGE + "ClassAonB",
                Collections.singletonList(PACKAGE + "ClassBonA")
        );
    }

    @Test (expected = InjectionCycleException.class)
    public void indirectInjectionCycleThrows()
            throws Exception {
        Object object = Injector.initialize(
                PACKAGE + "ClassXonY",
                Arrays.asList(PACKAGE + "ClassYonZ", PACKAGE + "ClassZonX")
        );
    }

    @Test (expected = AmbiguousImplementationException.class)
    public void ambigiousImplsThrows() throws Exception{
        Object object = Injector.initialize(
                PACKAGE + "ClassDependsOnBase",
                Arrays.asList(PACKAGE + "ClassDerA", PACKAGE + "ClassDerB")
        );
    }

    @Test (expected = ImplementationNotFoundException.class)
    public void implNotFoundThrows() throws Exception{
        Object object = Injector.initialize(
                PACKAGE + "ClassDependsOnBase",
                Arrays.asList(PACKAGE + "InterfaceImpl",
                              PACKAGE + "InterfaceImplAnother")
        );

    }

    @Test
    public void multipleInterfaceImplInitializes () throws Exception {
        Object object = Injector.initialize(
                PACKAGE + "ClassWithTwoInterfaceDependencies",
                Arrays.asList(PACKAGE + "InterfaceImpl",
                        PACKAGE + "InterfaceAnotherImpl")
        );
        assertTrue(object instanceof ClassWithTwoInterfaceDependencies);
        ClassWithTwoInterfaceDependencies instance = (ClassWithTwoInterfaceDependencies) object;
        assertTrue(instance.dependencyA instanceof InterfaceImpl);
        assertTrue(instance.dependencyB instanceof InterfaceAnotherImpl);
    }

    @Test
    public void chainDependencyInitializes () throws Exception {
        Object object = Injector.initialize(
                PACKAGE + "ClassA",
                Arrays.asList(PACKAGE + "ClassB",
                        PACKAGE + "ClassC")
        );
        assertTrue(object instanceof ClassA);
        ClassA instance = (ClassA) object;
        assertTrue(instance.dependency instanceof ClassB);
    }
}