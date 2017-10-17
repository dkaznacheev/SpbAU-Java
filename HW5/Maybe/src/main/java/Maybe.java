import com.sun.istack.internal.NotNull;

import java.util.function.Function;

/**
 * Wrapper generic class Maybe is made for handling objects nullable objects.
 * @param <T>
 */
public class Maybe<T> {

    /**
     * Maybe's value, can be null
     */
    private T value;

    public Maybe (T t) {
        this.value = t;
    }

    /**
     * Wraps not null object in Maybe
     * @param t not null object
     * @param <T> object type
     * @return Maybe containing this object
     */
    public static <T> Maybe<T> just(@NotNull T t) {
        return new Maybe<>(t);
    }

    /**
     * Wraps null in Maybe
     * @param <T> object type
     * @return Maybe containing null
     */
    public static <T> Maybe<T> nothing() {
        return new Maybe<>(null);
    }

    /**
     * Returns Maybe's value, throws NothingException if it was Nothing.
     * @return value of Maybe
     * @throws NothingException
     */
    public T get() throws NothingException {
        if (!isPresent())
            throw new NothingException();
        return value;
    }

    /**
     * Checks if Maybe isn't Nothing
     * @return if Maybe isn't Nothing
     */
    public boolean isPresent() {
        return value != null;
    }

    /**
     * Applies function to Maybe, throws NothingException if it was Nothing
     * @param mapper function to apply
     * @param <U> type of function's result
     * @return Maybe with value of function's result
     * @throws NothingException
     */
    public <U> Maybe<U> map(Function<T, U> mapper) throws NothingException {
        if (!isPresent())
            throw new NothingException();
        return new Maybe<>(mapper.apply(value));
    }
}
