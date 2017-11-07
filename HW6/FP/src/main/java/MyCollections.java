import java.util.LinkedList;

/**
 * Class containing methods to work with functions and predicates on Collections.
 */
public class MyCollections {

    /**
     * Applies the function to every element of the list.
     * @param function function to apply
     * @param list list to map over
     * @param <T> type of elements in list
     * @param <R> type of elements in resulting list
     * @return new list with mapped values
     */
    public static  <T, R> LinkedList<R> map(Function1<T, R> function, LinkedList<T> list) {
        LinkedList<R> result = new LinkedList<>();

        for (T element : list) {
            result.add(function.apply(element));
        }

        return result;
    }

    /** Takes all elements from the list that satisfy the predicate
     * @param predicate predicate
     * @param list list to take from
     * @param <T> type of elements in the list
     * @return new list with elements that satisfy the predicate
     */
    public static  <T> LinkedList<T> filter(MyPredicate<T> predicate, LinkedList<T> list) {
        LinkedList<T> result = new LinkedList<>();

        for (T element : list) {
            if (predicate.apply(element)) {
                result.add(element);
            }
        }

        return result;
    }

    /**
     * Takes first elements from the list that satisfy the predicate, stops after the element does not satisfy.
     * @param predicate predicate
     * @param list list to take from
     * @param <T> type of elements in the list
     * @return new list
     */
    public static  <T> LinkedList<T> takeWhile(MyPredicate<T> predicate, LinkedList<T> list) {
        LinkedList<T> result = new LinkedList<>();

        for (T element : list) {
            if (predicate.not().apply(element)) {
                return result;
            }
            result.add(element);
        }

        return result;
    }

    /**
     * Takes first elements from the list that do not satisfy the predicate, stops after the element does satisfy.
     * @param predicate predicate
     * @param list list to take from
     * @param <T> type of elements in the list
     * @return new list
     */
    public static <T> LinkedList<T> takeUnless(MyPredicate<T> predicate, LinkedList<T> list) {
        LinkedList<T> result = new LinkedList<>();

        for (T element : list) {
            if (predicate.apply(element)) {
                return result;
            }
            result.add(element);
        }

        return result;
    }

    /**
     * Applies the function to the initial element with every element from the list, left to right.
     * @param function function to apply
     * @param initial initial value
     * @param list list to take elements from
     * @param <T> type of elements in the list
     * @param <U> type of the result
     * @return result of applications
     */
    public static  <T, U> U foldl(Function2<T, U, U> function, U initial, LinkedList<T> list) {
        U result = initial;

        for (T element : list) {
            result = function.apply(element, result);
        }

        return result;
    }

    /**
     * Applies the function to the initial element with every element from the list, right to left.
     * @param function function to apply
     * @param initial initial value
     * @param list list to take elements from
     * @param <T> type of elements in the list
     * @param <U> type of the result
     * @return result of applications
     */
    public static <T, U> U foldr(Function2<T, U, U> function, U initial, LinkedList<T> list) {
        U result = initial;

        java.util.Collections.reverse(list);
        for (T element : list) {
            result = function.apply(element, result);
        }

        return result;
    }
}
