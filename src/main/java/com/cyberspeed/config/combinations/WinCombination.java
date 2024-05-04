package com.cyberspeed.config.combinations;

public record WinCombination(
        double rewardMultiplier,
        String when,
        int count,
        String group,
        String[][] coveredAreas
) {
}
