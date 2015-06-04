package com.sen.papercut;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collection;

import static java.util.Arrays.asList;

/**
 * Created by sen on 3/06/2015.
 */
public class PrintCalculatorTest {
    @Test
    public void readStream() {
        Collection<String> lines = asList(
                "25, 10, false",
                "55, 13, true",
                "502, 22, true",
                "1, 0, false"
        );
        lines.stream().map(PrintJobParser::parseToPrintJob)
                .map(j -> j.calculateCosts(s -> new BigDecimal("0.20")));
    }
}
