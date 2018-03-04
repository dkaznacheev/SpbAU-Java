package ru.spbau.dkaznacheev.calculator;


/**
 * Class for parsing the expression.
 * Is constructed with a string to parse, can return a postfix-style stack with calculation tokens to evaluate later.
 */
public class ExpressionParser {

    /**
     * The expression to parse.
     */
    private String expr;

    public ExpressionParser(String expr) {
        this.expr = expr;
    }

    /**
     * Inner class that generates tokens from the expressions.
     */
    private class TokenGenerator {

        /**
         * Current position in the string.
         */
        private int pos;

        public TokenGenerator() {
            pos = 0;
        }

        /**
         * Checks if the next character in the line is a digit.
         * @return true if the next character in the line is a digit
         */
        private boolean nextIsDigit() {
            return (pos < expr.length() - 1) &&
                    Character.isDigit(expr.charAt(pos + 1));
        }

        /**
         * Checks if the character is an operation.
         * @param ch character
         * @return true if the character is an operation
         */
        private boolean isOperation(char ch) {
            return  (ch == '+')
                 || (ch == '-')
                 || (ch == '*')
                 || (ch == '/')
                 || (ch == '(')
                 || (ch == ')');
        }

        /**
         * Returns the next token from the string, null if the end was reached.
         * @return the next token from the string
         */
        public CalcToken getToken() throws ParseException {
            if (pos >= expr.length()) {
                return null;
            }
            int currentNumber = 0;
            for (; pos < expr.length(); pos++) {
                char c = expr.charAt(pos);

                if (Character.isDigit(c)) {
                    currentNumber = currentNumber * 10 + Character.getNumericValue(c);
                    if (!nextIsDigit()) {
                        int number = currentNumber;
                        pos++;
                        return new IntToken(number);
                    }
                    continue;
                }
                if (isOperation(c)) {
                    pos++;
                    return new OpToken(c);
                }
                else {
                    throw new ParseException();
                }

            }

            return null;

        }
    }

    /**
     * Parses an expression to postfix-style stack of tokens.
     * Uses a Shunting-yard algorithm.
     * @return stack of tokens in postfix style
     */
    public MyStack<CalcToken> parse() throws ParseException {
        MyStack<CalcToken> outStack = new MyStack<>();
        MyStack<OpToken> opStack = new MyStack<>();

        expr = '(' + expr + ')';

        TokenGenerator generator = new TokenGenerator();

        try {

            for (CalcToken token = generator.getToken(); token != null; token = generator.getToken()) {

                if (!token.isOperation()) {
                    outStack.push(token);
                    continue;
                }

                OpToken opToken = (OpToken) token;
                if (opToken.getOperation() == '(') {
                    opStack.push(opToken);
                    continue;
                }
                if (opToken.getOperation() == ')') {
                    while (opStack.peek().getOperation() != '(') {
                        outStack.push(opStack.peek());
                        opStack.pop();
                    }
                    opStack.pop();
                    continue;
                }
                while ( !opStack.isEmpty() &&
                        !isBracket(opStack.peek()) &&
                        priority(opStack.peek()) <= priority(opToken)) {
                    outStack.push(opStack.peek());
                    opStack.pop();
                }
                opStack.push(opToken);

            }

            while (!opStack.isEmpty()) {
                OpToken opToken = opStack.peek();
                if (isBracket(opToken)) {
                    throw new ParseException();
                }
                outStack.push(opToken);
                opStack.pop();
            }


            return outStack;

        } catch (Exception e) {
            throw new ParseException();
        }
    }

    /**
     * Checks if the operation token is a bracket.
     * @param operation operation token to check
     * @return true if the operation token is a bracket
     */
    private boolean isBracket(OpToken operation) {
        char op = operation.getOperation();
        return  (op == '(' || op == ')');
    }

    /**
     * Returns the priority of the operation, the lesser the priority, the more important it is.
     * @param operation operation token
     * @return its priority
     */
    private int priority(OpToken operation) {
        char op = operation.getOperation();
        if (op == '+' || op == '-') {
            return 2;
        }
        if (op == '*' || op == '/') {
            return 1;
        }
        return 0;
    }
}
