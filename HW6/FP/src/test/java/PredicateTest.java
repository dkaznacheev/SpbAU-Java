import ru.spbau.functional.MyPredicate;
import org.junit.Test;

import static ru.spbau.functional.MyPredicate.ALWAYS_FALSE;
import static ru.spbau.functional.MyPredicate.ALWAYS_TRUE;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PredicateTest {

    @Test
    public void predicateAlwaysTrueFalse() {
        assertTrue((boolean)ALWAYS_TRUE.apply(true));
        assertTrue((boolean)ALWAYS_TRUE.apply(false));
        assertFalse((boolean)ALWAYS_FALSE.apply(false));
        assertFalse((boolean)ALWAYS_FALSE.apply(false));
    }

    @Test
    public void predicateOr() {
        assertTrue((boolean)ALWAYS_FALSE.or(ALWAYS_TRUE).apply(new Object()));
        assertTrue((boolean)ALWAYS_TRUE.or(ALWAYS_FALSE).apply(new Object()));
        assertFalse((boolean)ALWAYS_FALSE.or(ALWAYS_FALSE).apply(new Object()));
        assertTrue((boolean)ALWAYS_TRUE.or(ALWAYS_TRUE).apply(new Object()));
    }

    @Test
    public void predicateAnd() {
        assertFalse((boolean)ALWAYS_FALSE.and(ALWAYS_TRUE).apply(new Object()));
        assertFalse((boolean)ALWAYS_TRUE.and(ALWAYS_FALSE).apply(new Object()));
        assertFalse((boolean)ALWAYS_FALSE.and(ALWAYS_FALSE).apply(new Object()));
        assertTrue((boolean)ALWAYS_TRUE.and(ALWAYS_TRUE).apply(new Object()));
    }

    @Test
    public void predicateNot() {
        assertTrue((boolean)ALWAYS_FALSE.not().apply(true));
        assertFalse((boolean)ALWAYS_TRUE.not().apply(true));
    }

    @Test
    public void predicateCustom() {
        MyPredicate<Integer> isBig = (n) -> n >= 5;
        assertTrue(isBig.apply(100));
        assertFalse(isBig.apply(4));
    }
}
