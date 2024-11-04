package com.challenge.operations.util;

import java.util.Stack;
import java.util.StringTokenizer;

public class ExpressionEvaluator {

    // Defines the precedence of operators
    private int precedence(String operator) {
        return switch (operator) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            case "sqrt" -> 3;
            default -> -1;
        };
    }

    // Converts to postfix notation (RPN) using the Shunting-yard algorithm
    private String toPostfix(String expression) {
        Stack<String> stack = new Stack<>();
        StringBuilder postfix = new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer(expression, "+-*/() ", true);

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            if (token.isEmpty()) continue;

            // Checks if it is a number (now allows decimals)
            if (token.matches("\\d+(\\.\\d+)?")) {
                postfix.append(token).append(" ");
            }

            // If it is the sqrt function
            else if (token.equals("sqrt")) {
                stack.push("sqrt");
            }

            // If you are an operator
            else if (token.matches("[+\\-*/]")) {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(token)) {
                    postfix.append(stack.pop()).append(" ");
                }
                stack.push(token);
            }
            // Opening parenthesis
            else if (token.equals("(")) {
                stack.push("(");
            }
            // Closing parenthesis
            else if (token.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    postfix.append(stack.pop()).append(" ");
                }
                stack.pop(); // Remove the '(' from the stack
                if (!stack.isEmpty() && stack.peek().equals("sqrt")) {
                    postfix.append(stack.pop()).append(" ");
                }
            }
        }

        // Removes all remaining operators on the stack
        while (!stack.isEmpty()) {
            postfix.append(stack.pop()).append(" ");
        }

        return postfix.toString();
    }

    // Evaluates postfix expression
    public double evaluate(String expression) {
        String postfix = toPostfix(expression);
        Stack<Double> stack = new Stack<>();

        for (String token : postfix.split("\\s")) {
            if (token.matches("\\d+(\\.\\d+)?")) {  // allows decimal numbers
                stack.push(Double.parseDouble(token));
            } else if (token.matches("[+\\-*/]")) {
                double b = stack.pop();
                double a = stack.pop();
                switch (token) {
                    case "+" -> stack.push(a + b);
                    case "-" -> stack.push(a - b);
                    case "*" -> stack.push(a * b);
                    case "/" -> stack.push(a / b);
                }
            } else if (token.equals("sqrt")) {
                double a = stack.pop();
                stack.push(Math.sqrt(a));
            }
        }

        double result = stack.pop();
        return result;
    }
}
