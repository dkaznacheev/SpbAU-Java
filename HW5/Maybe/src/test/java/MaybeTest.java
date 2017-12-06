import org.junit.Test;
import ru.spbau.maybe.Maybe;
import ru.spbau.maybe.NothingException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static ru.spbau.maybe.Maybe.just;
import static ru.spbau.maybe.Maybe.nothing;

public class MaybeTest {
    @Test (expected = NothingException.class)
    public void getThrowExceptionTest() throws Exception {
        Maybe<String> mbString = nothing();
        mbString.get();

        Maybe<String> mbString2 = just("test");

        try {
            assert (mbString2.get().equals("test"));
        } catch (NothingException ex) {
            assert (false);
        }
    }

    @Test
    public void getNotThrowingTest() throws Exception {
        Maybe<String> mbString2 = just("test");

        assertEquals("test", mbString2.get());
    }

    @Test
    public void isPresentTest() throws Exception {
        assertTrue(just(1).isPresent());
        assertFalse(nothing().isPresent());
    }

    @Test
    public void mapSimpleTest() throws Exception {
        Maybe<Integer> mbInt = just(12);
        Maybe<Integer> result = mbInt.map(value -> value*value);
        assertEquals (144, (int)result.get());
    }

    @Test (expected = NothingException.class)
    public void mapThrowExceptionTest() throws Exception {
        Maybe<Integer> mbNo = nothing();
        mbNo.map(x -> 2 * x).get();
    }

}