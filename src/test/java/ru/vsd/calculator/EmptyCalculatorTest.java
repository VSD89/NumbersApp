package ru.vsd.calculator;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class EmptyCalculatorTest {
    private static final String WARNING = "You must enter at least one number!";

    private DataCalculator calculator = new DataCalculator();

    @Test
    public void printMinTest() {
        assertEquals(WARNING, calculator.printMin());
    }

    @Test
    public void printMaxTest() {
        assertEquals(WARNING, calculator.printMax());
    }

    @Test
    public void printAvgTest() {
        assertEquals(WARNING, calculator.printAvg());
    }

    @Test
    public void recalculateDataTest() {
        String input = "4232";
        calculator.recalculateData(input);
        assertEquals(new BigDecimal(input), calculator.getAvg());
    }
}
