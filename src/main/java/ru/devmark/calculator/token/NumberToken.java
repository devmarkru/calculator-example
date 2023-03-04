package ru.devmark.calculator.token;

public record NumberToken(
        Integer value
) implements Token {
    @Override
    public TokenType type() {
        return TokenType.NUMBER;
    }
}
