package ru.spbau.maybe;

/**
 * Custom exception for handling situations when Nothing is used as Just.
 * For example, it is thrown when somebody tries to map over Nothing.
 */
public class NothingException extends Exception {
}
