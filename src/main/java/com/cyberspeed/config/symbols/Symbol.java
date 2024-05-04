package com.cyberspeed.config.symbols;

public record Symbol(
        double rewardMultiplier,
        SymbolType type,
        SymbolImpactType impactType,
        double extra
) {
}
