package com.cyberspeed.config.probabilities;

import java.util.Map;

public record SymbolsProbability(
        int column,
        int row,
        Map<String, Integer> symbols
) {
}
