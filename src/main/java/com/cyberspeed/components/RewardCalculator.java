package com.cyberspeed.components;

import com.cyberspeed.entities.ScratchGameResult;

import java.math.BigDecimal;

public interface RewardCalculator {

    ScratchGameResult calculate(String[][] board, BigDecimal bettingAmount);
}
