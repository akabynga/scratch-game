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
}
