package com.sen.papercut;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

/**
 * Created by sen on 3/06/2015.
 */
public class PrintJobCostCalculatorTest {
    private static final PrintingSpec aPrintingSpec = new PrintingSpec("XX", ColorType.COLOR, PrintType.DOUBLESIDED);

    @Test
    public void calculateJobCost() {
        int numberOfPages = 10;
        BigDecimal unitCost = new BigDecimal(20);
        BigDecimal expectedCost = new BigDecimal(200);

        Function<PrintingSpec, BigDecimal> costFinder = s -> unitCost;
        PrintJobItem job = new PrintJobItem(aPrintingSpec, numberOfPages);

        BigDecimal cost = job.calculateCost(costFinder);

        assertEquals("Expected cost doesn't match", expectedCost, cost);
    }
}
