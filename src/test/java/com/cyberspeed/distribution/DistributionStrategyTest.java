package com.cyberspeed.distribution;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DistributionStrategyTest {

    @Test
    void initFakeDistributionStrategyExpectAllSymbolsDistributedAccordingToTheirProbability() {
        Map<String, Integer> symbolsProbability = Map.of(
                "A", 1,
                "B", 2,
                "C", 3,
                "D", 4,
                "E", 5,
                "F", 6
        );
        int symbolsProbabilitySum = symbolsProbability.values().stream().reduce(Integer::sum).orElse(1);

        DistributionStrategy distribution = new DistributionTestStrategy();
        Map<String, Integer> symbolCounter = new HashMap<>();
        int numberOfRuns = 1_000_000;

        for (int i = 0; i < symbolsProbabilitySum * numberOfRuns; i++) {
            String symbol = distribution.chooseSymbol(symbolsProbability);
            symbolCounter.compute(symbol, (k, v) -> v == null ? 1 : v + 1);
        }

        assertEquals(1, round(symbolCounter.get("A"), numberOfRuns));
        assertEquals(2, round(symbolCounter.get("B"), numberOfRuns));
        assertEquals(3, round(symbolCounter.get("C"), numberOfRuns));
        assertEquals(4, round(symbolCounter.get("D"), numberOfRuns));
        assertEquals(5, round(symbolCounter.get("E"), numberOfRuns));
        assertEquals(6, round(symbolCounter.get("F"), numberOfRuns));
    }

    private long round(int value, int numberOfRuns) {
        return Math.round(value / (double) numberOfRuns);
    }

    private static class DistributionTestStrategy extends DistributionStrategy {

        protected DistributionTestStrategy() {
            super(0, 0, null);
        }

        @Override
        public String[][] generateBoard() {
            throw new UnsupportedOperationException();
        }
    }
}
