import org.junit.Test;
import ru.spbau.functional.Function1;
import ru.spbau.functional.Function2;


import static org.junit.Assert.*;

public class FPTest {
    @Test
    public void applyFunction1() {
        Function1<Integer, Integer> function = new Function1<Integer, Integer>() {
            @Override
            public Integer apply(Integer arg) {
                return arg * arg;
            }
        };
        int result = function.apply(3);
        assertEquals(9, result);
    }

    @Test
    public void applyCompose() {
        Function1<Integer, Integer> function1 = new Function1<Integer, Integer>() {
            @Override
            public Integer apply(Integer arg) {
                return arg * arg;
            }
        };
        Function1<Integer, Integer> function2 = new Function1<Integer, Integer>() {
            @Override
            public Integer apply(Integer arg) {
                return arg + 1;
            }
        };
        int result = function1.compose(function2).apply(1);
        assertNotEquals(4 ,result);
        assertEquals(2, result);
    }

    @Test
    public void applyFunction2() {
        Function2<String, Integer, String> function = new Function2<String, Integer, String>() {
            @Override
            public String apply(String arg1, Integer arg2) {
                StringBuilder stringBuilder = new StringBuilder (String.valueOf(arg1));
                stringBuilder.append (": ");
                stringBuilder.append (arg2);
                return stringBuilder.toString();
            }
        };
        String result = function.apply("Answer to the Ultimate Question of Life, the Universe, and Everything", 42);
        assertEquals("Answer to the Ultimate Question of Life, the Universe, and Everything: 42", result);
    }

    @Test
    public void composeFunction2() {
        Function2<Integer, Integer, Float> ratio = new Function2<Integer, Integer, Float>() {
            @Override
            public Float apply(Integer arg1, Integer arg2) {
                return ((float) arg1) / ((float) arg2);
            }
        };
        Function1<Float, Integer> ceil = new Function1<Float, Integer>() {
            @Override
            public Integer apply(Float arg) {
                return (int) java.lang.Math.ceil(arg);
            }
        };

        int result = ratio.compose(ceil).apply(5, 2);
        assertEquals(3, result);
    }

    @Test
    public void bind1() {
        Function2<Integer, Integer, Integer> sub = new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer arg1, Integer arg2) {
                return arg1 - arg2;
            }
        };
        Function1<Integer, Integer> neg = sub.bind1(0);
        int result = neg.apply(10);
        assertEquals(-10, result);
    }

    @Test
    public void bind2() {
        Function2<Integer, Integer, Integer> sub = new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer arg1, Integer arg2) {
                return arg1 - arg2;
            }
        };
        Function1<Integer, Integer> sub1 = sub.bind2(1);
        int result = sub1.apply(10);
        assertEquals(9, result);
    }

}