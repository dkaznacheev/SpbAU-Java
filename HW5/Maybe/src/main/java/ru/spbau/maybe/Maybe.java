package ru.spbau.maybe;

import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

/**
 * Wrapper generic class Maybe made for handling nullable objects.
 * @param <T>
 */
public class Maybe<T> {
    @SuppressWarnings("unchecked")
    private static final Maybe nothing = new Maybe(null);

    /**
     * Maybe's value, can't be null
     */
    private T value;

    private Maybe(T t) {
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
    @SuppressWarnings("unchecked")
    public static <T> Maybe<T> nothing() {
        return nothing;
    }

    /**
     * Returns Maybe's value, throws NothingException if it was Nothing.
     * @return value of Maybe
     * @throws NothingException if it is Nothing
     */
    public T get() throws NothingException {
        if (this.equals(nothing)) {
            throw new NothingException();
        }
        return value;
    }

    /**
     * Checks if Maybe isn't Nothing
     * @return if Maybe isn't Nothing
     */
    public boolean isPresent() {
        return !this.equals(nothing);
    }

    /**
     * Applies function to ru.spbau.maybe.Maybe, throws NothingException if it was Nothing
     * @param mapper function to apply
     * @param <U> type of function's result
     * @return Maybe with value of function's result
     * @throws NothingException if it is Nothing
     */
    public <U> Maybe<U> map(Function<? super T, ? extends U> mapper) throws NothingException {
        if (this.equals(nothing)) {
            throw new NothingException();
        }
        return new Maybe<>(mapper.apply(value));
    }
}
