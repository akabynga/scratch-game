package com.cyberspeed.config.symbols;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Symbol(
        @JsonProperty("reward_multiplier")
        double rewardMultiplier,
        SymbolType type,
        SymbolImpactType impact,
        double extra
) {
}
