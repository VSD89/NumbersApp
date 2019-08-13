package ru.vsd.handler;

import ru.vsd.calculator.DataCalculator;

public class CommandHandler {
    private DataCalculator calculator;
    private static final String HELP_OUTPUT = "You can enter here number or one of these keywords: MIN/MAX/AVG - " +
            "to display the minimum/maximum/average value, ALL - to display minimum & maximum & average at once, " +
            "EXIT - to exit the program";

    public CommandHandler(DataCalculator calculator) {
        this.calculator = calculator;
    }

    /**
     * Method to recognize and handle command depending on the content
     *
     * @param command from console
     */
    public void handle(String command) {
        switch (command) {
            case "min":
                System.out.println(calculator.printMin());
                break;
            case "max":
                System.out.println(calculator.printMax());
                break;
            case "avg":
                System.out.println(calculator.printAvg());
                break;
            case "all":
                System.out.println(calculator.printAll());
                break;
            case "help":
                System.out.println(HELP_OUTPUT);
                break;
            default:
                calculator.recalculateData(command);
                break;
        }
    }
}
