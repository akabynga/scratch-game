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

//    public static final String[] DEFAULT_ARGUMENTS = {"--config", "config.json", "--betting-amount", "100"};

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