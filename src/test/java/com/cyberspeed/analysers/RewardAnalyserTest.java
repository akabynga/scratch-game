package com.cyberspeed.analysers;

import com.cyberspeed.components.RewardAnalyserStrategy;
import com.cyberspeed.components.impl.LinearRewardAnalyser;
import com.cyberspeed.components.impl.SameSymbolsRewardAnalyser;
import com.cyberspeed.config.ScratchConfiguration;
import com.cyberspeed.utils.JsonConvertor;
import com.cyberspeed.utils.impl.JsonConvertorImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class RewardAnalyserTest {

    private ScratchConfiguration configuration;

    @BeforeEach
    public void beforeEach() throws IOException {
        JsonConvertor jsonConvertor = new JsonConvertorImpl();
        configuration = jsonConvertor.readValue(new File("config.json"), ScratchConfiguration.class);
    }

    @Test
    public void initWithSameSymbolsHorizontallyAnalyseAndReturnSizeOne() {
        RewardAnalyserStrategy analyser = new LinearRewardAnalyser();
        String[][] matrix = {
                {"A", "A", "A"},
                {"B", "C", "D"},
                {"E", "T", "Y"},
        };

        Map<String, List<String>> combinations = analyser.analyse(matrix, configuration.winCombination());

        Assertions.assertEquals(1, combinations.get("A").size());
    }

    @Test
    public void initWithSameSymbolsAnalyseAndReturnExpectedType() {
        String expected = "same_symbol_3_times";
        RewardAnalyserStrategy analyser = new SameSymbolsRewardAnalyser();
        String[][] matrix = {
                {"A", "C", "A"},
                {"B", "A", "D"},
                {"E", "T", "Y"},
        };

        Map<String, List<String>> combinations = analyser.analyse(matrix, configuration.winCombination());

        Assertions.assertEquals(expected, combinations.get("A").get(0));
    }

    @Test
    public void initWithSameSymbolsHorizontallyAnalyseAndReturnExpectedType() {
        String expected = "same_symbols_horizontally";
        RewardAnalyserStrategy analyser = new LinearRewardAnalyser();
        String[][] matrix = {
                {"B", "C", "D"},
                {"A", "A", "A"},
                {"E", "V", "T"},
        };

        Map<String, List<String>> combinations = analyser.analyse(matrix, configuration.winCombination());

        Assertions.assertEquals(expected, combinations.get("A").get(0));
    }

    @Test
    public void initWithSameSymbolsVerticallyAnalyseAndReturnExpectedType() {
        String expected = "same_symbols_vertically";
        RewardAnalyserStrategy analyser = new LinearRewardAnalyser();
        String[][] matrix = {
                {"A", "C", "D"},
                {"A", "B", "A"},
                {"A", "V", "T"},
        };

        Map<String, List<String>> combinations = analyser.analyse(matrix, configuration.winCombination());

        Assertions.assertEquals(expected, combinations.get("A").get(0));
    }

    @Test
    public void initWithSameSymbolsDiagonallyLeftToRightAnalyseAndReturnExpectedType() {
        String expected = "same_symbols_diagonally_left_to_right";
        RewardAnalyserStrategy analyser = new LinearRewardAnalyser();
        String[][] matrix = {
                {"A", "C", "D"},
                {"B", "A", "A"},
                {"A", "V", "A"},
        };

        Map<String, List<String>> combinations = analyser.analyse(matrix, configuration.winCombination());

        Assertions.assertEquals(expected, combinations.get("A").get(0));
    }

    @Test
    public void initWithSameSymbolsDiagonallyRightToLeftAnalyseAndReturnExpectedType() {
        String expected = "same_symbols_diagonally_right_to_left";
        RewardAnalyserStrategy analyser = new LinearRewardAnalyser();
        String[][] matrix = {
                {"D", "C", "A"},
                {"B", "A", "D"},
                {"A", "V", "A"},
        };

        Map<String, List<String>> combinations = analyser.analyse(matrix, configuration.winCombination());

        Assertions.assertEquals(expected, combinations.get("A").get(0));
    }

    @Test
    public void initWithSameSymbolsHorizontallyAnalyseAndReturnFourTypes() {
        List<String> expected = List.of("same_symbols_vertically", "same_symbols_diagonally_right_to_left", "same_symbols_diagonally_left_to_right", "same_symbols_horizontally");
        RewardAnalyserStrategy analyser = new LinearRewardAnalyser();
        String[][] matrix = {
                {"A", "A", "A"},
                {"A", "A", "A"},
                {"A", "A", "A"},
        };

        Map<String, List<String>> combinations = analyser.analyse(matrix, configuration.winCombination());

        Assertions.assertEquals(expected, combinations.get("A"));
    }

}
