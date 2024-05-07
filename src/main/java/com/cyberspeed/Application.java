package com.cyberspeed;

import com.cyberspeed.components.GameBoard;
import com.cyberspeed.components.impl.ScratchGameBoard;
import com.cyberspeed.config.ScratchConfiguration;
import com.cyberspeed.entities.ScratchGameResult;
import com.cyberspeed.utils.CommandLineParser;
import com.cyberspeed.utils.InputParameters;
import com.cyberspeed.utils.JsonConvertor;
import com.cyberspeed.utils.impl.JsonConvertorImpl;

public class Application {
    /**
     * MyGame - A Simple Betting Game
     * <p>
     * This program simulates a simple betting game where players can place bets and win rewards.
     * The game accepts command-line arguments for configuration and betting amount.
     * <p>
     * Usage:
     * java MyGame --config config.json --betting-amount 100
     * <p>
     * Arguments:
     * --config <file>       Path to the configuration file (in JSON format).
     * --betting-amount <n>  Betting amount (positive integer).
     * <p>
     * Configuration File (config.json):
     * The configuration file should contain game-specific settings, such as reward multipliers,
     * winning conditions, and other relevant parameters.
     */
    public static void main(String[] args) {

        try {
            JsonConvertor jsonConvertor = new JsonConvertorImpl();

            InputParameters params = CommandLineParser.parse(args);
            ScratchConfiguration configuration = jsonConvertor.readValue(params.config(), ScratchConfiguration.class);

            GameBoard<ScratchGameResult> board = new ScratchGameBoard(configuration);
            ScratchGameResult result = board.bet(params.bettingAmount());

            jsonConvertor.writeValue(System.out, result);
        } catch (Exception e) {
            System.out.printf("Sorry, but something goes wrong: %s \n", e.getMessage());
        }
    }
}