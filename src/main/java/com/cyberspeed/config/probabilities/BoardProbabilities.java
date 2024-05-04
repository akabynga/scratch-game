package com.cyberspeed.config.probabilities;

import java.util.List;

public record BoardProbabilities(
        List<SymbolsProbability> standardSymbols,
        SymbolsProbability bonusSymbols
) {
}
