package ru.devmark.calculator;

import ru.devmark.calculator.token.*;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Lexer {
    private static final String DELIMITERS = " +-*/()";

    public List<Token> getTokens(String source) {
        StringTokenizer tokenizer = new StringTokenizer(source, DELIMITERS, true);
        List<Token> tokens = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (token.isBlank()) {
                continue;
            } else if (isNumber(token)) {
                tokens.add(new NumberToken(Integer.parseInt(token)));
                continue;
            }
            tokens.add(
                    switch (token) {
                        case "+" -> new BinaryOperationToken(OperationType.PLUS);
                        case "-" -> new BinaryOperationToken(OperationType.MINUS);
                        case "*" -> new BinaryOperationToken(OperationType.MULTIPLY);
                        case "/" -> new BinaryOperationToken(OperationType.DIVIDE);
                        case "(" -> new OtherToken(TokenType.OPEN_BRACKET);
                        case ")" -> new OtherToken(TokenType.CLOSE_BRACKET);
                        default -> throw new RuntimeException("Unexpected token: " + token);
                    }
            );
        }
        return tokens;
    }

    private boolean isNumber(String token) {
        for (int i = 0; i < token.length(); i++) {
            if (!Character.isDigit(token.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
