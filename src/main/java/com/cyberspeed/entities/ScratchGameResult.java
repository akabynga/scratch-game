package com.cyberspeed.entities;

import java.util.List;
import java.util.Map;

public record ScratchGameResult(
        String[][] matrix,
        double reward,
        Map<String, List<String>> appliedWinningCombinations,
        String appliedBonusSymbol
) {
}
