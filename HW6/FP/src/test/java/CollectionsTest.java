import ru.spbau.functional.Function2;
import ru.spbau.functional.MyCollections;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class CollectionsTest {
    @Test
    public void mapTest() {
        LinkedList<Integer> list = new LinkedList<>();
        list.push(1);
        list.push(2);
        list.push(3);
        LinkedList<Integer> result = new LinkedList<>();
        result.push(-1);
        result.push(-2);
        result.push(-3);
        assertEquals(result, MyCollections.map((e) -> -e, list));
    }

    @Test
    public void filterTest() {
        LinkedList<Integer> list = new LinkedList<>();
        list.push(1);
        list.push(2);
        list.push(3);
        LinkedList<Integer> result = new LinkedList<>();
        result.push(1);
        result.push(3);
        assertEquals(result, MyCollections.filter((e)->e % 2 != 0, list));
    }

    @Test
    public void takeWhileTest() {
        LinkedList<Integer> list = new LinkedList<>();
        list.push(4);
        list.push(3);
        list.push(2);
        list.push(1);
        LinkedList<Integer> result = new LinkedList<>();
        result.push(1);
        assertEquals(result, MyCollections.takeWhile((e) -> e < 2, list));
    }

    @Test
    public void takeUnlessTest() {
        LinkedList<Integer> list = new LinkedList<>();
        list.push(4);
        list.push(3);
        list.push(2);
        list.push(1);
        LinkedList<Integer> result = new LinkedList<>();
        result.push(2);
        result.push(1);
        assertEquals(result, MyCollections.takeUnless((e) -> e >= 3, list));
    }

    @Test
    public void foldlTest() {
        LinkedList<Integer> list = new LinkedList<>();
        list.push(4);
        list.push(3);
        list.push(2);
        list.push(1);
        Function2<Integer, Integer, Integer> sub = (a, b) -> a - b;
        assertEquals(2, (int)MyCollections.foldl(sub, 0, list));
    }

    @Test
    public void foldrTest() {
        LinkedList<Integer> list = new LinkedList<>();
        list.push(4);
        list.push(3);
        list.push(2);
        list.push(1);
        Function2<Integer, Integer, Integer> add = (a, b) -> a + b;
        assertEquals(10, (int)MyCollections.foldr(add, 0, list));
    }


}
