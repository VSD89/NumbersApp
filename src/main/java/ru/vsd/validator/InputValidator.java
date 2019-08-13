package ru.vsd.validator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class InputValidator {
    private static final String NUMBER_TEMPLATE = "-?\\d+";
    private static final String EMPTY_MESSAGE = "Type anything below";
    private static final String INVALID_MESSAGE = "%s is not a valid command";
    private static final Set<String> VALID_COMMANDS = new HashSet<>(Arrays.asList("min", "max", "avg", "all", "help", "exit"));

    /**
     * Method to not allow wrong requests to be processed
     *
     * @param input line from console
     * @return output message in case of wrong request or nothing in case of correct request
     */
    public Optional<String> validate(String input) {
        if (VALID_COMMANDS.contains(input) || input.matches(NUMBER_TEMPLATE)) {
            return Optional.empty();
        }
        if (input.isEmpty()) {
            return Optional.of(EMPTY_MESSAGE);
        }
        String message = input.length() > 20 ? input.substring(0, 20) + "..." : input;
        return Optional.of(String.format(INVALID_MESSAGE, message));
    }
}
