package com.cyberspeed.utils;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CommandLineParserTest {


    @Test
    void testCommandLineArgumentsParsing() {
        String[] args = {"--config", "config.json", "--betting-amount", "100.0"};
        InputParameters params = CommandLineParser.parse(args);
        assertNotNull(params);
        assertNotNull(params.config());
        assertTrue(params.config().exists());
        assertEquals(new BigDecimal("100.0"), params.bettingAmount());
    }

    @Test
    void initWithUnidentifiedArgumentShouldThrowCommandLineParserException() {

        String[] args = {"--config", "config.json", "--betting-amount", "100.0", "not valid param"};

        CommandLineParserException thrown = assertThrows(
                CommandLineParserException.class,
                () -> CommandLineParser.parse(args),
                "Expected parse() to throw, but it didn't"
        );

        assertEquals("Unidentified parameter: not valid param", thrown.getMessage());
    }

    @Test
    void initWithWrongDoubleValueShouldThrowCommandLineParserException() {

        String[] args = {"--config", "config.json", "--betting-amount", "not double"};

        CommandLineParserException thrown = assertThrows(
                CommandLineParserException.class,
                () -> CommandLineParser.parse(args),
                "Expected parse() to throw, but it didn't"
        );

        assertEquals("Unable to parse betting amount: not double", thrown.getMessage());
    }

    @Test
    void initWithWrongConfigPathShouldThrowCommandLineParserException() {

        String[] args = {"--config", "wrong/dir/config.json", "--betting-amount", "not big decimal"};

        CommandLineParserException thrown = assertThrows(
                CommandLineParserException.class,
                () -> CommandLineParser.parse(args),
                "Expected parse() to throw, but it didn't"
        );

        assertEquals("Unable to find the file using this path: wrong/dir/config.json", thrown.getMessage());
    }

    @Test
    void initWithNullArgumentsThrowCommandLineParserException() {

        CommandLineParserException thrown = assertThrows(
                CommandLineParserException.class,
                () -> CommandLineParser.parse(null),
                "Expected parse() to throw, but it didn't"
        );

        assertEquals("Game settings not provided", thrown.getMessage());
    }

    @Test
    void initWithMissedBettingAmountArgumentThrowCommandLineParserException() {

        String[] args = {"--config", "config.json"};

        CommandLineParserException thrown = assertThrows(
                CommandLineParserException.class,
                () -> CommandLineParser.parse(args),
                "Expected parse() to throw, but it didn't"
        );

        assertEquals("Required argument '--betting-amount' missed", thrown.getMessage());
    }

    @Test
    void initWithMissedConfigArgumentThrowCommandLineParserException() {

        String[] args = {"--betting-amount", "100"};

        CommandLineParserException thrown = assertThrows(
                CommandLineParserException.class,
                () -> CommandLineParser.parse(args),
                "Expected parse() to throw, but it didn't"
        );

        assertEquals("Required argument '--config' missed", thrown.getMessage());
    }

    @Test
    void initWithWrongNumberOfArgumentsThrowCommandLineParserException() {

        String[] args = {"--config", "config.json", "--betting-amount"};

        CommandLineParserException thrown = assertThrows(
                CommandLineParserException.class,
                () -> CommandLineParser.parse(args),
                "Expected parse() to throw, but it didn't"
        );

        assertEquals("Unable to parse command line arguments, wrong number of arguments: [--config, config.json, --betting-amount]", thrown.getMessage());
    }
}
