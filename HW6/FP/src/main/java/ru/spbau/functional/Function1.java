package ru.spbau.functional;

/**
 * Abstract function taking one argument.
 * To derive from Function1, apply method must be overridden.
 * @param <T> type of argument
 * @param <R> type of result
 */
public interface Function1<T, R> {

    /**
     * Abstract method of function application.
     * @param arg argument of function
     * @return result of function
     */
    R apply(T arg);

    /**
     * Composition of functions, produces the Function1 that takes the initial argument
     * and gives the result of composition of two functions
     * @param function function to compose with
     * @param <U> type of result in composed function
     * @return composed function
     */
    default <U> Function1<T, U> compose (Function1<? super R, ? extends U> function) {
        return new Function1<T, U>() {
            @Override
            public U apply(T arg) {
                return function.apply(Function1.this.apply(arg));
            }
        };
    }
}
