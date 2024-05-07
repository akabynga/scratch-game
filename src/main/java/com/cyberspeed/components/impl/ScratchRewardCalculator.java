package com.cyberspeed.components.impl;

import com.cyberspeed.components.RewardAnalyserStrategyHolder;
import com.cyberspeed.components.RewardCalculator;
import com.cyberspeed.config.ScratchConfiguration;
import com.cyberspeed.config.combinations.CombinationWhenType;
import com.cyberspeed.config.combinations.WinCombination;
import com.cyberspeed.config.symbols.Symbol;
import com.cyberspeed.config.symbols.SymbolType;
import com.cyberspeed.entities.ScratchGameResult;

import java.math.BigDecimal;
import java.util.*;

public class ScratchRewardCalculator implements RewardCalculator {

    private final ScratchConfiguration config;

    public ScratchRewardCalculator(ScratchConfiguration config) {
        this.config = config;
    }

    @Override
    public ScratchGameResult calculate(String[][] board, BigDecimal bettingAmount) {

        Map<String, List<String>> appliedWinningCombinations = new HashMap<>();
        for (CombinationWhenType strategy : CombinationWhenType.values()) {
            Map<String, List<String>> strategyMatchedCombination =
                    RewardAnalyserStrategyHolder.getInstance().get(strategy)
                            .analyse(board, config.winCombination());

            for (String symbol : strategyMatchedCombination.keySet()) {
                List<String> applied = strategyMatchedCombination.get(symbol);
                appliedWinningCombinations.computeIfAbsent(symbol, k -> new ArrayList<>())
                        .addAll(applied);
            }
        }

        String appliedBonusSymbol = findBonusSymbol(board);
        BigDecimal reward = calculateReward(bettingAmount, appliedWinningCombinations, appliedBonusSymbol);

        return new ScratchGameResult(board, reward, appliedWinningCombinations, appliedBonusSymbol);
    }

    private String findBonusSymbol(String[][] board) {
        return Arrays.stream(board).flatMap(Arrays::stream).filter(
                        config.symbols()
                                .entrySet()
                                .stream()
                                .filter(e -> e.getValue().type() == SymbolType.BONUS)
                                .map(Map.Entry::getKey).toList()::contains)
                .findFirst()
                .orElse(null);
    }

    private BigDecimal calculateReward(BigDecimal bettingAmount, Map<String, List<String>> appliedWinningCombinations, String appliedBonusSymbol) {
        BigDecimal reward = BigDecimal.ZERO;
        for (String symbol : appliedWinningCombinations.keySet()) {
            BigDecimal symbolReward = bettingAmount.multiply(symbolMultiplier(symbol));

            for (String combination : appliedWinningCombinations.get(symbol)) {
                symbolReward = symbolReward.multiply(winCombinationMultiplier(combination));
            }
            reward = reward.add(symbolReward);
        }
        if (reward.signum() > 0) {
            reward = applyBonusSymbol(reward, config.symbols().get(appliedBonusSymbol));
        }
        return reward;
    }

    private BigDecimal applyBonusSymbol(BigDecimal reward, Symbol symbol) {
        if (symbol == null) {
            return reward;
        }

        switch (symbol.impact()) {
            case MULTIPLY_REWARD:
                return reward.multiply(symbol.rewardMultiplier());
            case EXTRA_BONUS:
                return reward.add(symbol.extra());
            case MISS: // do nothing
        }
        return reward;
    }

    private BigDecimal winCombinationMultiplier(String combination) {
        return Optional.of(config.winCombination().get(combination)).orElse(WinCombination.EMPTY).rewardMultiplier();
    }

    private BigDecimal symbolMultiplier(String symbol) {
        return Optional.of(config.symbols().get(symbol)).orElse(Symbol.EMPTY).rewardMultiplier();
    }
}
