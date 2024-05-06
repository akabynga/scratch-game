package com.cyberspeed.components;

import com.cyberspeed.entities.ScratchGameResult;

public interface RewardCalculator {

    ScratchGameResult calculate(String[][] board, double bettingAmount);
}
