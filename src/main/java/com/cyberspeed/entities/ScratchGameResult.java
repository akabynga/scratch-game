package com.cyberspeed.entities;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public record ScratchGameResult(
        String[][] matrix,
        BigDecimal reward,
        Map<String, List<String>> appliedWinningCombinations,
        String appliedBonusSymbol
) {
}
