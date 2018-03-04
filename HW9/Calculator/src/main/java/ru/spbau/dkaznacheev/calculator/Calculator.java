package ru.spbau.dkaznacheev.calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The class for working with console input and output.
 * Reads a line with expression, parses it to postfix notation, evaluates it, then prints the result.
 */
public class Calculator {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        while (true) {
            try {
                line = br.readLine();
                if (line.equals("q")) {
                    break;
                }

                System.out.println(evaluate(line));
            } catch (IOException e) {
                System.out.println("I/O problems");
                break;
            } catch (ParseException e) {
                System.out.println("Incorrect line");
            } catch (CalculationException e) {
                System.out.println("Incorrect formula");
            }
        }

    }

    /**
     * Evaluates a single expression
     * @param line expression
     * @return result of calculations
     */
    private static int evaluate(String line) throws ParseException, CalculationException {
        MyStack<CalcToken> stack = new ExpressionParser(line).parse();

        StackCalculator calculator = new StackCalculator(stack);
        return calculator.evaluate();
    }
}

