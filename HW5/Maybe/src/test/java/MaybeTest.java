import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.*;

public class MaybeTest {
    @Test
    public void get() throws Exception {
        Maybe<String> mbString = new Maybe<>(null);
        boolean exceptionThrown;
        try {
            System.out.println(mbString.get());
            exceptionThrown = false;
        } catch (NothingException ex) {
            exceptionThrown = true;
        }
        assert (exceptionThrown);


        Maybe<String> mbString2 = new Maybe<>("test");

        try {
            assert (mbString2.get().equals("test"));
        } catch (NothingException ex) {
            assert (false);
        }
    }

    @Test
    public void map() throws Exception {
        Maybe<Integer> mbInt = new Maybe<>(12);
        Maybe<Integer> result = mbInt.map(value -> value*value);
        assert (result.get() == 144);
        try {
            Maybe<Integer> mbNo = Maybe.nothing();
            System.out.println(mbNo.map(x -> 2 * x).get());
            assert (false);
        } catch (NothingException e) {
            assert (true);
        }

    }

}