package ru.spbau.dkaznacheev.calculator;

import java.util.NoSuchElementException;

/**
 * Stack calculator that takes a postfix-notation stack of tokens and evaluates it.
 */
public class StackCalculator {

    /**
     * Postfix-notation stack of calculation tokens.
     */
    private MyStack<CalcToken> calcStack;

    public StackCalculator(MyStack<CalcToken> calcStack) {
        this.calcStack = calcStack;
    }

    /**
     * Calculates the result of an expression stored in a stack.
     * @ the result of calculations
     * @throws CalculationException
     */
    public int evaluate() throws CalculationException {

        MyStack<IntToken> processingStack = new MyStack<>();

        MyStack<CalcToken> polish = new MyStack<>();

        while (!calcStack.isEmpty()) {
            polish.push(calcStack.peek());
            calcStack.pop();
        }

        try {
            while (!polish.isEmpty()) {
                CalcToken token = polish.peek();
                polish.pop();

                if (token.isOperation()) {
                    IntToken arg2 = processingStack.peek();
                    processingStack.pop();
                    IntToken arg1 = processingStack.peek();
                    processingStack.pop();

                    processingStack.push(processOperation(arg1, arg2, (OpToken)token));

                } else {
                    processingStack.push((IntToken)token);
                }
            }

            IntToken result = processingStack.peek();
            processingStack.pop();
            if (!processingStack.isEmpty() || result == null) {
                throw new CalculationException();
            }

            return result.getValue();

        } catch (ClassCastException | NoSuchElementException e) {
            throw new CalculationException();
        }

    }

    /**
     * Performs a single binary operation.
     * @param arg1 first argument of operation
     * @param arg2 second argument of operation
     * @param operation the operation symbol
     * @return result of the operation
     * @throws CalculationException
     */
    private IntToken processOperation(IntToken arg1, IntToken arg2, OpToken operation) throws CalculationException {
        int result;

        if (    arg1 == null ||
                arg2 == null ||
                operation == null) {
            throw new CalculationException();
        }

        switch (operation.getOperation()) {
            case '+': {
                result = arg1.getValue() + arg2.getValue();
                break;
            }
            case '-': {
                result = arg1.getValue() - arg2.getValue();
                break;
            }
            case '*': {
                result = arg1.getValue() * arg2.getValue();
                break;
            }
            case '/': {
                result = arg1.getValue() / arg2.getValue();
                break;
            }
            default: {
                throw new CalculationException();
            }
        }
        return new IntToken(result);
    }

}
