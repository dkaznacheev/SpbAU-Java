package ru.spbau.dkaznacheev.calculator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StackCalculatorTest {
    @Mock
    private MyStack<CalcToken> stack;

    @Test
    public void simpleEvaluate() throws Exception {
        when(stack.isEmpty()).thenReturn(false, false, false, true);
        when(stack.peek()).thenReturn(new OpToken('+'), new IntToken(2), new IntToken(3));
        StackCalculator calculator = new StackCalculator(stack);
        assertEquals(5, calculator.evaluate());
    }

    @Test
    public void bigEvaluate() throws Exception {
        when(stack.isEmpty()).thenReturn( false, false, false,
                                                false, false, false,
                                                false, false, false,
                                                false, false, true);
        when(stack.peek()).thenReturn(
                new OpToken('*'),
                new IntToken(3),
                new OpToken('-'),
                new IntToken(2),
                new OpToken('/'),
                new IntToken(5),
                new OpToken('*'),
                new OpToken('+'),
                new IntToken(3),
                new IntToken(2),
                new IntToken(1)
        );

        StackCalculator calculator = new StackCalculator(stack);

        assertEquals(-3, calculator.evaluate());
    }

    @Test (expected = CalculationException.class)
    public void failEvaluateNotEnoughArgs() throws Exception {
        when(stack.isEmpty()).thenReturn(false, false, true);
        when(stack.peek()).thenReturn(new OpToken('+'), new IntToken(2));
        StackCalculator calculator = new StackCalculator(stack);
        calculator.evaluate();
    }

    @Test (expected = CalculationException.class)
    public void failEvaluateTooMuchArgs() throws Exception {
        when(stack.isEmpty()).thenReturn(false, false, false, false, true);
        when(stack.peek()).thenReturn(new OpToken('+'), new IntToken(2), new IntToken(2), new IntToken(2));
        StackCalculator calculator = new StackCalculator(stack);
        calculator.evaluate();
    }
}