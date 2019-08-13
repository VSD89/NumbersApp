package ru.vsd.calculator;

import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.number.BigDecimalCloseTo.closeTo;
import static org.junit.Assert.assertEquals;

public class NotEmptyCalculatorTest {
    private static final String MIN_OUTPUT = "The minimum value is %.0f";
    private static final String MAX_OUTPUT = "The maximum value is %.0f";

    private static DataCalculator calculator = new DataCalculator();
    private static String maxNumberString = "234233";
    private static String minNumberString = "-3362";
    private static String additionalNumberString = "7576";
    private static BigDecimal maxNumber = new BigDecimal(maxNumberString);
    private static BigDecimal minNumber = new BigDecimal(minNumberString);
    private static BigDecimal additionalNumber = new BigDecimal(additionalNumberString);


    @BeforeClass
    public static void addData() {
        calculator.recalculateData(maxNumberString);
        calculator.recalculateData(minNumberString);
        calculator.recalculateData(additionalNumberString);
    }

    @Test
    public void printMinTest() {
        assertEquals(String.format(MIN_OUTPUT, minNumber), calculator.printMin());
    }

    @Test
    public void printMaxTest() {
        assertEquals(String.format(MAX_OUTPUT, maxNumber), calculator.printMax());
    }

    @Test
    public void recalculateDataTest() {
        String input = "3751";
        BigDecimal avg = maxNumber.add(minNumber).add(additionalNumber).add(new BigDecimal(input))
                .divide(new BigDecimal("4"), 10, RoundingMode.HALF_UP);
        calculator.recalculateData(input);
        BigDecimal error = new BigDecimal("0.00005");
        assertThat(avg, is(closeTo(calculator.getAvg(), error)));
    }
}