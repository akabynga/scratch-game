package com.cyberspeed.config.symbols;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record Symbol(
        @JsonProperty("reward_multiplier")
        BigDecimal rewardMultiplier,
        SymbolType type,
        SymbolImpactType impact,
        BigDecimal extra
) {
    public static final Symbol EMPTY = new Symbol(
            BigDecimal.ONE,
            SymbolType.BONUS,
            SymbolImpactType.MISS,
            BigDecimal.ZERO);

}
