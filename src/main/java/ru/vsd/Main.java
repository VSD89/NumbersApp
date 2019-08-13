package ru.vsd;

import ru.vsd.calculator.DataCalculator;
import ru.vsd.handler.CommandHandler;
import ru.vsd.validator.InputValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class Main {
    private static final String START_MESSAGE = "Enter number (not fractional) here or enter HELP to see other options";
    private static final String END_MESSAGE = "End of program";
    private static final String EXCEPTION_MESSAGE = "Input/output error";

    public static void main(String[] args) {
        InputValidator validator = new InputValidator();
        DataCalculator calculator = new DataCalculator();
        CommandHandler handler = new CommandHandler(calculator);
        System.out.println(START_MESSAGE);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                String input = reader.readLine().trim().toLowerCase();
                Optional<String> validationError = validator.validate(input);
                if (!validationError.isPresent()) {
                    if (input.equals("exit")) {
                        break;
                    }
                    handler.handle(input);
                } else {
                    System.out.println(validationError.get());
                }
            }
            System.out.println(END_MESSAGE);
        } catch (IOException e) {
            System.out.println(EXCEPTION_MESSAGE);
        }
    }
}
