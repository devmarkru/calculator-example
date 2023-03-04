package ru.devmark.calculator;

import ru.devmark.calculator.token.Token;

import java.util.List;

public class Calculator {

    private final Lexer lexer = new Lexer();
    private final PostfixConverter converter = new PostfixConverter();
    private final StackMachine stackMachine = new StackMachine();

    public int calculate(String expression) {
        List<Token> tokens = lexer.getTokens(expression);
        var postfixExpression = converter.convertToPostfix(tokens);
        var result = stackMachine.evaluate(postfixExpression);
        System.out.println(result);
        return result;
    }
}
