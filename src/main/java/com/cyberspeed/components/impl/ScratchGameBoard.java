package com.cyberspeed.components.impl;

import com.cyberspeed.components.GameBoard;
import com.cyberspeed.components.RewardCalculator;
import com.cyberspeed.config.ScratchConfiguration;
import com.cyberspeed.distribution.DistributionStrategy;
import com.cyberspeed.distribution.impl.SingleBonusDistribution;
import com.cyberspeed.entities.ScratchGameResult;

public class ScratchGameBoard implements GameBoard<ScratchGameResult> {

    private final RewardCalculator rewardCalculator;
    private DistributionStrategy distributionStrategy;

    public ScratchGameBoard(ScratchConfiguration config) {
        this.distributionStrategy = new SingleBonusDistribution(config.columns(), config.rows(), config.probabilities());
        this.rewardCalculator = new ScratchRewardCalculator(config);
    }

    @Override
    public ScratchGameResult bet(double bettingAmount) {
        return rewardCalculator.calculate(distributionStrategy.generateBoard(), bettingAmount);
    }

    public void setDistributionStrategy(DistributionStrategy distributionStrategy) {
        this.distributionStrategy = distributionStrategy;
    }
}
