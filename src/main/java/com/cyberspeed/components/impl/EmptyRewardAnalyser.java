package com.cyberspeed.components.impl;

import com.cyberspeed.components.RewardAnalyserStrategy;
import com.cyberspeed.config.combinations.CombinationWhenType;
import com.cyberspeed.config.combinations.WinCombination;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class EmptyRewardAnalyser extends RewardAnalyserStrategy {


    @Override
    public Map<String, List<String>> analyseBoard(String[][] board, Map<String, WinCombination> availableWinCombinations) {
        return Collections.emptyMap();
    }

    @Override
    protected CombinationWhenType getStrategy() {
        // do nothing
        return null;
    }
}
