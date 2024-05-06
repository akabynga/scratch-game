package com.cyberspeed.components.impl;

import com.cyberspeed.components.RewardAnalyserStrategyHolder;
import com.cyberspeed.components.RewardCalculator;
import com.cyberspeed.config.combinations.CombinationWhenType;
import com.cyberspeed.config.combinations.WinCombination;
import com.cyberspeed.entities.ScratchGameResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScratchRewardCalculator implements RewardCalculator {

    private final Map<String, WinCombination> winCombinations;

    public ScratchRewardCalculator(Map<String, WinCombination> winCombinations) {
        this.winCombinations = winCombinations;
    }

    @Override
    public ScratchGameResult calculate(String[][] board, double bettingAmount) {

        Map<String, List<String>> appliedWinningCombinations = new HashMap<>();
        for (CombinationWhenType strategy : CombinationWhenType.values()) {
            Map<String, List<String>> strategyMatchedCombination =
                    RewardAnalyserStrategyHolder.getInstance().get(strategy)
                            .analyse(board, winCombinations);

            for (String symbol : strategyMatchedCombination.keySet()) {
                List<String> applied = strategyMatchedCombination.get(symbol);
                appliedWinningCombinations.computeIfAbsent(symbol, k -> new ArrayList<>())
                        .addAll(applied);
            }
        }

        double reward = 0;
        String appliedBonusSymbol = null;


        return new ScratchGameResult(board, reward, appliedWinningCombinations, appliedBonusSymbol);
    }
}
