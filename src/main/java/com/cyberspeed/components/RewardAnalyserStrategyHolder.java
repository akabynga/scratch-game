package com.cyberspeed.components;

import com.cyberspeed.components.impl.EmptyRewardAnalyser;
import com.cyberspeed.components.impl.LinearRewardAnalyser;
import com.cyberspeed.components.impl.SameSymbolsRewardAnalyser;
import com.cyberspeed.config.combinations.CombinationWhenType;

import java.util.HashMap;
import java.util.Map;

public class RewardAnalyserStrategyHolder {

    private static volatile RewardAnalyserStrategyHolder instance = null;
    private static final RewardAnalyserStrategy emptyRewardCalculator = new EmptyRewardAnalyser();
    private final Map<CombinationWhenType, RewardAnalyserStrategy> strategies;

    private RewardAnalyserStrategyHolder() {
        this.strategies = new HashMap<>();
        init();
    }

    public static RewardAnalyserStrategyHolder getInstance() {
        if (instance == null) {
            synchronized (RewardAnalyserStrategyHolder.class) {
                if (instance == null) {
                    instance = new RewardAnalyserStrategyHolder();
                }
            }
        }
        return instance;
    }

    private void init() {
        strategies.put(CombinationWhenType.SAME_SYMBOLS, new SameSymbolsRewardAnalyser());
        strategies.put(CombinationWhenType.LINEAR_SYMBOLS, new LinearRewardAnalyser());
    }

    public RewardAnalyserStrategy get(CombinationWhenType type) {
        return strategies.getOrDefault(type, emptyRewardCalculator);
    }

}
