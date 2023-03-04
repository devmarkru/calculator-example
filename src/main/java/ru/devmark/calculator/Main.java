package ru.devmark.calculator;

public class Main {

    public static void main(String[] args) {
        var calculator = new Calculator();
        calculator.calculate("   12*5   - 36 / 3"); // 48
        calculator.calculate("12 + 50 / 5 - 3  "); // 19
        calculator.calculate("   20 * ( 45 + 5 ) / 10"); // 100
        calculator.calculate("(0 - 1 + ( 45 + 5 ) * 2) / 33"); // 3
        calculator.calculate("0/1"); // 0
        calculator.calculate("2*2*2*2"); // 16
        calculator.calculate("015"); // 15
        calculator.calculate(String.valueOf(Integer.MAX_VALUE)); // 2147483647
    }
}
