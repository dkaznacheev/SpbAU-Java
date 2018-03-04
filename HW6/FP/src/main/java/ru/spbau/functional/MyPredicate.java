package ru.spbau.functional;

/**
 * Abstract predicate is a function taking one argument and giving a boolean result.
 * To derive from Predicate, apply method must be overridden.
 * @param <T> type of argument
 */
public interface MyPredicate<T> extends Function1<T, Boolean> {

    /**
     * Constant predicate, giving true result on any input.
     */
    MyPredicate ALWAYS_TRUE = (e) -> true;

    /**
     * Constant predicate, giving false result on any input.
     */
    MyPredicate ALWAYS_FALSE = (e) -> false;

    /**
     * Inverts the predicate.
     * @return new predicate with result inverted
     */
    default MyPredicate<T> not() {
        return new MyPredicate<T>() {
            @Override
            public Boolean apply(T arg) {
                return !(MyPredicate.this.apply(arg));
            }
        };
    }

    /**Does the logic OR operation on the predicate and the given predicate.
     * @param predicate predicate to OR with
     * @return new predicate
     */
    default MyPredicate<T> or(MyPredicate<? super T> predicate) {
        return new MyPredicate<T>() {
            @Override
            public Boolean apply(T arg) {
                if (MyPredicate.this.apply(arg))
                    return true;
                return predicate.apply(arg);
            }
        };
    }

    /**Does the logic AND operation on the predicate and the given predicate.
     * @param predicate predicate to AND with
     * @return new predicate
     */
    default MyPredicate<T> and (MyPredicate <? super T> predicate) {
        return new MyPredicate<T>() {
            @Override
            public Boolean apply(T arg) {
                if (!MyPredicate.this.apply(arg))
                    return false;
                return predicate.apply(arg);
            }
        };
    }
}
