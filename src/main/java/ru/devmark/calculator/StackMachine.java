package ru.devmark.calculator;

import ru.devmark.calculator.token.BinaryOperationToken;
import ru.devmark.calculator.token.NumberToken;
import ru.devmark.calculator.token.Token;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class StackMachine {

    public int evaluate(List<Token> postfixExpression) {
        Deque<Integer> valueStack = new LinkedList<>();
        for (Token token : postfixExpression) {
            if (token instanceof NumberToken number) {
                valueStack.push(number.value());
            } else if (token instanceof BinaryOperationToken operation) {
                int right = valueStack.pop();
                int left = valueStack.pop();
                int result = switch (operation.operationType()) {
                    case PLUS -> left + right;
                    case MINUS -> left - right;
                    case MULTIPLY -> left * right;
                    case DIVIDE -> {
                        if (right == 0) {
                            throw new RuntimeException("Divide by zero!");
                        }
                        yield left / right;
                    }
                };
                valueStack.push(result);
            }
        }
        return valueStack.pop();
    }
}
