package com.cyberspeed.config.combinations;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WinCombination(
        @JsonProperty("reward_multiplier")
        double rewardMultiplier,
        CombinationWhenType when,
        int count,
        CombinationGroupType group,
        @JsonProperty("covered_areas")
        String[][] coveredAreas
) {
    public static final WinCombination EMPTY = new WinCombination(
            1,
            CombinationWhenType.SAME_SYMBOLS,
            0,
            CombinationGroupType.SAME_SYMBOLS,
            null);
}
