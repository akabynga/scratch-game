package com.cyberspeed.utils;

public class CommandLineParserException extends RuntimeException {
    public CommandLineParserException() {
        super();
    }

    public CommandLineParserException(String message) {
        super(message);
    }

    public CommandLineParserException(String message, Exception e) {
        super(message, e);
    }

}
