package com.cyberspeed.distribution.impl;

import com.cyberspeed.distribution.DistributionStrategy;
import com.cyberspeed.config.probabilities.BoardProbabilities;
import com.cyberspeed.config.probabilities.SymbolsProbability;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MultipleBonusDistribution extends DistributionStrategy {

    public MultipleBonusDistribution(int columns, int rows, BoardProbabilities probabilities) {
        super(columns, rows, probabilities);
    }

    @Override
    public String[][] generateBoard() {

        String[][] board = new String[columns][rows];
        for (SymbolsProbability symbolsProbability : probabilities.standardSymbols()) {
            board[symbolsProbability.row()][symbolsProbability.column()] =
                    chooseSymbol(Stream.concat(symbolsProbability.symbols().entrySet().stream(), probabilities.bonusSymbols().symbols().entrySet().stream())
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        }
        return board;
    }
}
