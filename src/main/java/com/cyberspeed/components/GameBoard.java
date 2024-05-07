package com.cyberspeed.components;

import java.math.BigDecimal;

public interface GameBoard<R> {

    R bet(BigDecimal bettingAmount);
}
