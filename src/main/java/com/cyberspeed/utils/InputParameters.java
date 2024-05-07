package com.cyberspeed.utils;

import java.io.File;
import java.math.BigDecimal;

public record InputParameters(
        File config,
        BigDecimal bettingAmount
) {
}
