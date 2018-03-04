package ru.spbau.injector;

/**
 * An Exception that is thrown when there is an injection cycle,
 * i.e. two classes depend on each other, directly or indirectly.
 */
public class InjectionCycleException extends Exception {
}
