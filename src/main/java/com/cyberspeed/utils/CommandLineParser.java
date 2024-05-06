package com.cyberspeed.utils;

import java.io.File;
import java.util.Arrays;

public class CommandLineParser {

    public static InputParameters parse(String[] args) {

        verifyRequiredArguments(args);

        File config = null;
        double bettingAmount = 0.0;
        try {
            for (int i = 0; i < args.length; i += 2) {
                String arg = args[i];
                switch (arg) {
                    case "--config":
                        config = new File(args[i + 1]);
                        if (!config.exists()) {
                            throw new CommandLineParserException(String.format("Unable to find the file using this path: %s", args[i + 1]));
                        }
                        break;
                    case "--betting-amount":
                        try {
                            bettingAmount = Double.parseDouble(args[i + 1]);
                        } catch (NumberFormatException e) {
                            throw new CommandLineParserException(String.format("Unable to parse betting amount: %s", args[i + 1]), e);
                        }
                        break;
                    default:
                        throw new CommandLineParserException(String.format("Unidentified parameter: %s", arg));
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CommandLineParserException(String.format("Unable to parse command line arguments, wrong number of arguments: %s", Arrays.toString(args)), e);
        }
        return new InputParameters(config, bettingAmount);
    }

    private static void verifyRequiredArguments(String[] args) {
        if (args == null) {
            throw new CommandLineParserException("Game settings not provided");
        }

        if (Arrays.stream(args).noneMatch(v -> v != null && v.equals("--betting-amount"))) {
            throw new CommandLineParserException("Required argument '--betting-amount' missed");
        }

        if (Arrays.stream(args).noneMatch(v -> v != null && v.equals("--config"))) {
            throw new CommandLineParserException("Required argument '--config' missed");
        }
    }
}