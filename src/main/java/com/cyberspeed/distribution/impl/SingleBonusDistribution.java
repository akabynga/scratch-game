package com.cyberspeed.distribution.impl;

import com.cyberspeed.distribution.DistributionStrategy;
import com.cyberspeed.config.probabilities.BoardProbabilities;
import com.cyberspeed.config.probabilities.SymbolsProbability;

public class SingleBonusDistribution extends DistributionStrategy {

    public SingleBonusDistribution(int columns, int rows, BoardProbabilities probabilities) {
        super(columns, rows, probabilities);
    }

    @Override
    public String[][] generateBoard() {

        String[][] board = new String[columns][rows];
        int bonusCell = random.nextInt(probabilities.standardSymbols().size());

        for (int i = 0; i < probabilities.standardSymbols().size(); i++) {
            SymbolsProbability symbolsProbability = probabilities.standardSymbols().get(i);
            board[symbolsProbability.row()][symbolsProbability.column()] = chooseSymbol(bonusCell == i ?
                    probabilities.bonusSymbols().symbols() :
                    symbolsProbability.symbols()
            );
        }
        return board;
    }
}
