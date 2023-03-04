package ru.devmark.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.devmark.calculator.token.BinaryOperationToken;
import ru.devmark.calculator.token.NumberToken;
import ru.devmark.calculator.token.OperationType;

public class LexerTest {

    private Lexer lexer;

    @BeforeEach
    void setUp() throws Exception {
        lexer = new Lexer();
    }

    @Test
    void lexerSkipSpacebars() {
        Assertions.assertEquals(0, lexer.getTokens("   ").size());
    }

    @Test
    void lexerTrimSpacebars() {
        var tokens = lexer.getTokens(" 2 ");
        Assertions.assertEquals(1, tokens.size());
        Assertions.assertEquals(2, ((NumberToken) tokens.get(0)).value());
    }

    @Test
    void lexerMultipleDigits() {
        var tokens = lexer.getTokens(" 333 ");
        Assertions.assertEquals(1, tokens.size());
        Assertions.assertEquals(333, ((NumberToken) tokens.get(0)).value());
    }

    @Test
    void lexerMultipleOperations() {
        var tokens = lexer.getTokens(" +-* ");
        Assertions.assertEquals(3, tokens.size());
        Assertions.assertEquals(OperationType.PLUS, ((BinaryOperationToken) tokens.get(0)).operationType());
    }
}
