package ru.devmark.calculator.token;

public record BinaryOperationToken(
        OperationType operationType
) implements Token {
    @Override
    public TokenType type() {
        return TokenType.BINARY_OPERATION;
    }
}
