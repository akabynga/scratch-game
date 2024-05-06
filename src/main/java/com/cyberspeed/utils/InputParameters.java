package com.cyberspeed.utils;

import java.io.File;

public record InputParameters(
        File config,
        Double bettingAmount
) {
}
