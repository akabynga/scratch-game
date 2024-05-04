package com.cyberspeed.config.probabilities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record BoardProbabilities(
        @JsonProperty("standard_symbols")
        List<SymbolsProbability> standardSymbols,
        @JsonProperty("bonus_symbols")
        SymbolsProbability bonusSymbols
) {
}
