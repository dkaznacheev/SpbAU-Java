package calculator;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExpressionParserTest {
    @Test
    public void parseSimple() throws Exception {
        ExpressionParser parser = new ExpressionParser("2+300");
        MyStack<CalcToken> stack = parser.parse();
        assertEquals(true, stack.peek().isOperation());
        assertEquals('+', ((OpToken)(stack.peek())).getOperation());
        stack.pop();
        assertEquals(false, stack.peek().isOperation());
        assertEquals(300, ((IntToken)(stack.peek())).getValue());
        stack.pop();
        assertEquals(false, stack.peek().isOperation());
        assertEquals(2, ((IntToken)(stack.peek())).getValue());
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test(expected = ParseException.class)
    public void throwParseException() throws Exception {
        ExpressionParser parser = new ExpressionParser("2(+3))");
        MyStack<CalcToken> stack = parser.parse();
    }

    @Test(expected = ParseException.class)
    public void throwParseExceptionOddSymbols() throws Exception {
        ExpressionParser parser = new ExpressionParser("a2+2b3");
        MyStack<CalcToken> stack = parser.parse();
    }
}