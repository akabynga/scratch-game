package com.cyberspeed.components.impl;

import com.cyberspeed.components.RewardAnalyserStrategy;
import com.cyberspeed.config.combinations.CombinationGroupType;
import com.cyberspeed.config.combinations.CombinationWhenType;
import com.cyberspeed.config.combinations.WinCombination;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SameSymbolsRewardAnalyser extends RewardAnalyserStrategy {

    @Override
    public Map<String, List<String>> analyseBoard(String[][] board, Map<String, WinCombination> availableWinCombinations) {

        Map<String, Long> symbolCounts = Arrays.stream(board)
                .flatMap(Arrays::stream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Map<String, List<String>> result = new HashMap<>();

        symbolCounts.forEach((symbol, count) -> availableWinCombinations.entrySet()
                .stream()
                .filter(e -> e.getValue().count() <= count && e.getValue().group() == CombinationGroupType.SAME_SYMBOLS)
                .max(Comparator.comparing(e -> e.getValue().rewardMultiplier()))
                .ifPresent(winCombination -> result.computeIfAbsent(symbol, k -> new ArrayList<>()).add(winCombination.getKey())));

        return result;
    }

    @Override
    protected CombinationWhenType getStrategy() {
        return CombinationWhenType.SAME_SYMBOLS;
    }
}
