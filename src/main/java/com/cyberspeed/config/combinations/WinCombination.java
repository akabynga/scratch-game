package com.cyberspeed.config.combinations;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record WinCombination(
        @JsonProperty("reward_multiplier")
        BigDecimal rewardMultiplier,
        CombinationWhenType when,
        int count,
        CombinationGroupType group,
        @JsonProperty("covered_areas")
        String[][] coveredAreas
) {
    public static final WinCombination EMPTY = new WinCombination(
            BigDecimal.ONE,
            CombinationWhenType.SAME_SYMBOLS,
            0,
            CombinationGroupType.SAME_SYMBOLS,
            null);
}
