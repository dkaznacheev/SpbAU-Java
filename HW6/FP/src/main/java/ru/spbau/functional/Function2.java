package ru.spbau.functional;

/**
 * Abstract function taking two arguments.
 * To derive from Function2, apply method must be overridden.
 * @param <T1> type of first argument
 * @param <T2> type of second argument
 * @param <R> type of result
 */
public interface Function2<T1, T2, R> {

    /**
     * Abstract method of function application.
     * @param arg1 first argument of function
     * @param arg2 first argument of function
     * @return result of function
     */
    R apply(T1 arg1, T2 arg2);

    /**Composition of functions, produces the Function2 that takes the initial arguments
     * and gives the result of composition of this function and given Function1
     * @param function one-argument function to compose result with
     * @param <U> type of composed result
     * @return composed function of two arguments
     */
    default  <U> Function2<T1, T2, U> compose (Function1<? super R, ? extends U> function) {
        return new Function2<T1, T2, U>() {
            @Override
            public U apply(T1 arg1, T2 arg2) {
                return function.apply(Function2.this.apply(arg1, arg2));
            }
        };
    }

    /**
     * Binds (sets) the function's first argument, produces new Function1 taking second argument.
     * @param arg1 argument to bind
     * @return new Function1 with bound first argument
     */
    default Function1<T2, R> bind1 (T1 arg1) {
        return new Function1<T2, R>() {
            @Override
            public R apply(T2 arg2) {
                return Function2.this.apply(arg1, arg2);
            }
        };
    }

    /**
     * Binds (sets) the function's second argument, produces new Function1 taking first argument.
     * @param arg2 argument to bind
     * @return new Function1 with bound second argument
     */
    default Function1<T1, R> bind2 (T2 arg2) {
        return new Function1<T1, R>() {
            @Override
            public R apply(T1 arg1) {
                return Function2.this.apply(arg1, arg2);
            }
        };
    }

    /**
     * Converts the function to function of one argument
     * @param arg1 argument to convert with
     * @return the curried function
     */
    default Function1<T2, R> curry (T1 arg1) {
        return bind1(arg1);
    }
}
