package ru.vsd.validator;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class InputValidatorTest {
    private static final String EMPTY_MESSAGE = "Type anything below";
    private static final String INVALID_MESSAGE = "%s is not a valid command";

    private InputValidator validator = new InputValidator();

    @Test
    public void commandValidateTest() {
        String input = "  aVG".trim().toLowerCase();
        assertEquals(Optional.empty(), validator.validate(input));
    }

    @Test
    public void numberValidateTest() {
        String input = "736319  ".trim().toLowerCase();
        assertEquals(Optional.empty(), validator.validate(input));
    }

    @Test
    public void emptyValidateTest() {
        String input = "    ".trim().toLowerCase();
        assertEquals(Optional.of(EMPTY_MESSAGE), validator.validate(input));
    }

    @Test
    public void wrongValidateTest() {
        String input = "other request".trim().toLowerCase();
        assertEquals(Optional.of(String.format(INVALID_MESSAGE, input)), validator.validate(input));
    }
}
