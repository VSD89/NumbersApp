package ru.vsd.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DataCalculator {
    private static final String WARNING = "You must enter at least one number!";
    private static final String MIN_OUTPUT = "The minimum value is %.0f";
    private static final String MAX_OUTPUT = "The maximum value is %.0f";
    private static final String AVG_OUTPUT = "The average value is %.2f";

    private BigDecimal min;
    private BigDecimal max;
    private BigDecimal avg;
    private long count;

    public BigDecimal getAvg() {
        return avg;
    }

    /**
     * Method to receive formatted string with current minimum value
     *
     * @return message with current min for user
     */
    public String printMin() {
        String formattedMin = String.format(MIN_OUTPUT, min);
        return count > 0 ? formattedMin : WARNING;
    }

    /**
     * Method to receive formatted string with current maximum value
     *
     * @return message with current max for user
     */
    public String printMax() {
        String formattedMax = String.format(MAX_OUTPUT, max);
        return count > 0 ? formattedMax : WARNING;
    }

    /**
     * Method to receive formatted string with current average value
     *
     * @return message with current avg for user
     */
    public String printAvg() {
        String formattedAvg = String.format(AVG_OUTPUT, avg);
        return count > 0 ? formattedAvg : WARNING;
    }

    /**
     * Method to receive formatted string with current minimum, maximum and average values
     *
     * @return message with current min, max, avg for user
     */
    public String printAll() {
        String formattedMin = String.format(MIN_OUTPUT, min);
        String formattedMax = String.format(MAX_OUTPUT, max);
        String formattedAvg = String.format(AVG_OUTPUT, avg);
        return count > 0 ? formattedMin + "\n" + formattedMax + "\n" + formattedAvg : WARNING;
    }

    /**
     * Method to recalculate values of min, max, avg
     *
     * @param inputData line with number from user
     */
    public void recalculateData(String inputData) {
        BigDecimal currentNumber = new BigDecimal(inputData);
        if (count == 0) {
            min = currentNumber;
            max = min;
            avg = min;
        } else {
            min = min.min(currentNumber);
            max = max.max(currentNumber);
            avg = calculateAvg(currentNumber);
        }
        count++;
    }

    /**
     * Method to calculate current average value by formula
     * avg = currentAvg / (1 + 1 / count) + newNumber / (count + 1)
     *
     * @param number current number
     * @return calculated average value
     */
    private BigDecimal calculateAvg(BigDecimal number) {
        BigDecimal leftPart = avg.divide(BigDecimal.ONE
                .divide(BigDecimal.valueOf(count), 10, RoundingMode.HALF_UP)
                .add(BigDecimal.ONE), 10, RoundingMode.HALF_UP);
        BigDecimal rightPart = number.divide(BigDecimal.valueOf(count)
                .add(BigDecimal.ONE), 10, RoundingMode.HALF_UP);
        return leftPart.add(rightPart);
    }
}
