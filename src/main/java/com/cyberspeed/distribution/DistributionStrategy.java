package com.cyberspeed.distribution;

import com.cyberspeed.config.probabilities.BoardProbabilities;
import com.cyberspeed.distribution.impl.SingleBonusDistribution;

import java.util.*;

/**
 * This class is abstraction for strategy which is responsible for symbols distribution.
 * Based on requirements:
 * - Note (2): Bonus symbol can be generated randomly in any cell(s) in the matrix
 * was not really clear that bonus should be generated just in one matrix cell or bonus probability should be added to symbols probabilities
 * I decided to add few strategies, so you can replace it if needed.
 * By default, at most one bonus can be generated in the matrix ({@link SingleBonusDistribution})
 */
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
