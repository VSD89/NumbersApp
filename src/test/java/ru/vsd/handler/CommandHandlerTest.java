package ru.vsd.handler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.vsd.calculator.DataCalculator;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class CommandHandlerTest {
    private static final String HELP_OUTPUT = "You can enter here number or one of these keywords: MIN/MAX/AVG - " +
            "to display the minimum/maximum/average value, ALL - to display minimum & maximum & average at once, " +
            "EXIT - to exit the program";

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    private CommandHandler handler = new CommandHandler(new DataCalculator());

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void handleTest() {
        handler.handle("help");
        assertEquals(HELP_OUTPUT, outContent.toString().replaceAll("\r", "")
                .replaceAll("\n", ""));
    }
}
