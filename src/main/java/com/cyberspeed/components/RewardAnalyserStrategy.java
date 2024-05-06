package com.cyberspeed.components;

import com.cyberspeed.config.combinations.CombinationWhenType;
import com.cyberspeed.config.combinations.WinCombination;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class RewardAnalyserStrategy {

    public Map<String, List<String>> analyse(String[][] board, Map<String, WinCombination> availableWinCombinations) {
        return analyseBoard(board, filter(availableWinCombinations));
    }

    protected abstract CombinationWhenType getStrategy();

    protected abstract Map<String, List<String>> analyseBoard(String[][] board, Map<String, WinCombination> availableWinCombinations);

    protected Map<String, WinCombination> filter(Map<String, WinCombination> combinations) {
        return combinations.entrySet().stream()
                .filter(e -> e.getValue().when() == getStrategy())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


}
