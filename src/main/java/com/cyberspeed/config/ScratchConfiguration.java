package com.cyberspeed.config;

import com.cyberspeed.config.combinations.WinCombination;
import com.cyberspeed.config.probabilities.BoardProbabilities;
import com.cyberspeed.config.symbols.Symbol;

import java.util.Map;

public record ScratchConfiguration(
        int columns,
        int rows,
        Map<String, Symbol> symbols,
        BoardProbabilities probabilities,
        WinCombination winCombination
) {
}
