package com.cyberspeed.distribution;

import com.cyberspeed.config.probabilities.BoardProbabilities;

import java.util.Map;
import java.util.Random;

public abstract class DistributionStrategy {

    protected final BoardProbabilities probabilities;
    protected final Random random;
    protected final int columns;
    protected final int rows;

    protected DistributionStrategy(int columns, int rows, BoardProbabilities probabilities) {
        this.probabilities = probabilities;
        this.random = new Random();
        this.columns = columns;
        this.rows = rows;
    }

    public abstract String[][] generateBoard();

    protected String chooseSymbol(Map<String, Integer> symbols) {

        int totalWeight = symbols.values().stream().mapToInt(Integer::intValue).sum();
        int randomWeight = random.nextInt(totalWeight);

        for (Map.Entry<String, Integer> entry : symbols.entrySet()) {
            randomWeight -= entry.getValue();
            if (randomWeight < 0) {
                return entry.getKey();
            }
        }

        return null;
    }

}
