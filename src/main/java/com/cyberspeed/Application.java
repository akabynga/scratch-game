package com.cyberspeed;

import com.cyberspeed.components.GameBoard;
import com.cyberspeed.components.impl.ScratchGameBoard;
import com.cyberspeed.config.ScratchConfiguration;
import com.cyberspeed.entities.ScratchGameResult;
import com.cyberspeed.utils.CommandLineParser;
import com.cyberspeed.utils.InputParameters;
import com.cyberspeed.utils.JsonConvertor;
import com.cyberspeed.utils.impl.JsonConvertorImpl;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {

        JsonConvertor jsonConvertor = new JsonConvertorImpl();
        //TODO remove after testing
        String[] arguments = {"--config", "config.json", "--betting-amount", "100"};

        InputParameters params = CommandLineParser.parse(arguments);
        ScratchConfiguration configuration = jsonConvertor.readValue(params.config(), ScratchConfiguration.class);

        GameBoard<ScratchGameResult> board = new ScratchGameBoard(configuration);
        ScratchGameResult result = board.bet(params.bettingAmount());

        jsonConvertor.writeValue(System.out, result);
    }
}