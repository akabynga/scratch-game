package com.cyberspeed.components.impl;

import com.cyberspeed.components.RewardAnalyserStrategy;
import com.cyberspeed.config.combinations.CombinationWhenType;
import com.cyberspeed.config.combinations.WinCombination;

import java.util.*;
import java.util.stream.Collectors;

public class LinearRewardAnalyser extends RewardAnalyserStrategy {

    @Override
    public Map<String, List<String>> analyseBoard(String[][] board, Map<String, WinCombination> availableWinCombinations) {
        Map<String, List<String>> result = new HashMap<>();

        Map<String, List<List<Coordinates>>> coveredAreasMap =
                availableWinCombinations.entrySet()
                        .stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                e -> Arrays.stream(e.getValue().coveredAreas())
                                        .map(this::parseCoordinates)
                                        .toList()
                        ));

        Set<String> checkedSymbols = new HashSet<>();

        for (String[] symbols : board) {
            for (String symbol : symbols) {
                if (checkedSymbols.contains(symbol)) {
                    continue;
                }
                checkedSymbols.add(symbol);
                List<String> winningCombinations = getWinningCombinationsForSymbol(symbol, board, coveredAreasMap);
                if (!winningCombinations.isEmpty()) {
                    result.computeIfAbsent(symbol, k -> new ArrayList<>()).addAll(winningCombinations);
                }
            }
        }

        return result;
    }

    @Override
    protected CombinationWhenType getStrategy() {
        return CombinationWhenType.LINEAR_SYMBOLS;
    }

    private List<String> getWinningCombinationsForSymbol(String symbol, String[][] board, Map<String, List<List<Coordinates>>> coveredAreasMap) {
        return coveredAreasMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue().stream()
                        .anyMatch(list -> list.stream()
                                .allMatch(coords -> board[coords.x()][coords.y()].equals(symbol))))
                .map(Map.Entry::getKey)
                .toList();
    }

    private List<Coordinates> parseCoordinates(String[] area) {
        return Arrays.stream(area)
                .map(this::parse)
                .toList();
    }


    private Coordinates parse(String s) {
        String[] parts = s.split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid format. String must be in 'x:y' format.");
        }
        try {
            return new Coordinates(Integer.parseInt(parts[0].trim()), Integer.parseInt(parts[1].trim()));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format.", e);
        }
    }

    private record Coordinates(int x, int y) {
    }
}
