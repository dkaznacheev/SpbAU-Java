/**
 * Abstract predicate is a function taking one argument and giving a boolean result.
 * To derive from Predicate, apply method must be overridden.
 * @param <T> type of argument
 */
public abstract class MyPredicate<T> extends Function1<T, Boolean> {

    /**
     * Constant predicate, giving true result on any input.
     */
    public  final MyPredicate<T> ALWAYS_TRUE = new MyPredicate<T>() {
        @Override
        public Boolean apply(T arg) {
            return true;
        }
    };

    /**
     * Constant predicate, giving false result on any input.
     */
    public final MyPredicate<T> ALWAYS_FALSE = new MyPredicate<T>() {
        @Override
        public Boolean apply(T arg) {
            return false;
        }
    };

    /**
     * Inverts the predicate.
     * @return new predicate with result inverted
     */
    public MyPredicate<T> not() {
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
    MyPredicate<T> or (MyPredicate <? super T> predicate) {
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
    MyPredicate<T> and (MyPredicate <? super T> predicate) {
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
