package ru.devmark.calculator;

import ru.devmark.calculator.token.BinaryOperationToken;
import ru.devmark.calculator.token.Token;
import ru.devmark.calculator.token.TokenType;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class PostfixConverter {

    public List<Token> convertToPostfix(List<Token> source) {
        List<Token> postfixExpression = new ArrayList<>();
        Deque<Token> operationStack = new LinkedList<>();
        for (Token token : source) {
            switch (token.type()) {
                case NUMBER -> postfixExpression.add(token);
                case OPEN_BRACKET -> operationStack.push(token);
                case CLOSE_BRACKET -> {
                    while (!operationStack.isEmpty() && operationStack.peek().type() != TokenType.OPEN_BRACKET) {
                        postfixExpression.add(operationStack.pop());
                    }
                    operationStack.pop(); // открывающая скобка
                }
                case BINARY_OPERATION -> {
                    while (!operationStack.isEmpty() && getPriority(operationStack.peek()) >= getPriority(token)) {
                        postfixExpression.add(operationStack.pop());
                    }
                    operationStack.push(token);
                }
            }
        }
        while (!operationStack.isEmpty()) {
            postfixExpression.add(operationStack.pop());
        }
        return postfixExpression;
    }

    private int getPriority(Token token) {
        if (token instanceof BinaryOperationToken operation) {
            return switch (operation.operationType()) {
                case PLUS, MINUS -> 1;
                case MULTIPLY, DIVIDE -> 2;
            };
        }
        return 0; // для открывающей скобки
    }
}
