package com.cyberspeed.config;

import com.cyberspeed.config.symbols.Symbol;
import com.cyberspeed.utils.JsonConvertor;
import com.cyberspeed.utils.impl.JsonConvertorImpl;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ScratchConfigurationTest {

    private ScratchConfiguration configuration;

    @BeforeEach
    public void beforeEach() throws IOException {
        JsonConvertor jsonConvertor = new JsonConvertorImpl();
        configuration = jsonConvertor.readValue(new File("config.json"), ScratchConfiguration.class);
    }

    @Test
    public void parseConfig_testNonNull() {
        assertNotNull(configuration);
    }

    @Test
    public void parseConfig_testWinCombinationNotNull() {
        Assertions.assertNotNull(configuration.winCombination());
        Assertions.assertFalse(configuration.winCombination().isEmpty());
    }

    @Test
    public void parseConfig_testBoardSize() {
        Assertions.assertEquals(3, configuration.columns());
        Assertions.assertEquals(3, configuration.rows());
    }

    @Test
    public void parseConfig_testProbabilities() {
        Assertions.assertNotNull(configuration.probabilities());
        Assertions.assertNotNull(configuration.probabilities().bonusSymbols());
        Assertions.assertNotNull(configuration.probabilities().standardSymbols());
        Assertions.assertFalse(configuration.probabilities().standardSymbols().isEmpty());
    }

    @Test
    public void parseConfig_testSymbols() {
        Assertions.assertNotNull(configuration.symbols());
        Assertions.assertFalse(configuration.symbols().isEmpty());

        for (Map.Entry<String, Symbol> s : configuration.symbols().entrySet()) {
            Assertions.assertNotNull(s.getKey());
            Assertions.assertNotNull(s.getValue());
            Assertions.assertNotNull(s.getValue().type());
        }
    }
}
