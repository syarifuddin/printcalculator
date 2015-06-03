package com.sen.papercut;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.function.Function;

import static org.junit.Assert.*;

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

        PrintJobItem result = job.calculateCost(costFinder);

        assertEquals("Expected cost doesn't match", expectedCost, result.getTotalCost().get());
    }

    @Test
    public void getTotalCost_ReturnsEmpty_IfNot_Calculated() {
        int numberOfPages = 10;
        PrintJobItem job = new PrintJobItem(aPrintingSpec, numberOfPages);
        assertFalse("total Must return empty before job is calculated", job.getTotalCost().isPresent());
    }

    @Test
    public void getTotalCost_ReturnsNonEmpty_If_Calculated() {
        int numberOfPages = 10;
        BigDecimal unitCost = new BigDecimal(20);

        Function<PrintingSpec, BigDecimal> costFinder = s -> unitCost;
        PrintJobItem job = new PrintJobItem(aPrintingSpec, numberOfPages);

        PrintJobItem result = job.calculateCost(costFinder);

        assertTrue("total Must return non empty after job is calculated", result.getTotalCost().isPresent());
    }

    @Test
    public void calculateJobItemCosts_Returns_Costs_For_AllItem() {
        int bwPages = 20;
        int colorPages = 10;
        BigDecimal unitCost = new BigDecimal(20);

        Function<PrintingSpec, BigDecimal> costFinder = s -> unitCost;
        PrintJobItem bwJob = new PrintJobItem(PrintingSpec.A4_SINGLESIDED_BW, bwPages);
        PrintJobItem colorJob = new PrintJobItem(PrintingSpec.A4_SINGLESIDED_COLOR, colorPages);

        PrintJob job = new PrintJob(new PrintJobItem[]{bwJob, colorJob});
        PrintJob result = job.calculateCosts(costFinder);
        assertTrue(Arrays.stream(result.getJobItems()).allMatch(ji -> ji.getTotalCost().isPresent()));
    }

    @Test
    public void getTotalCosts_SumAllJobItemCosts() {
        int bwPages = 20;
        int colorPages = 10;

        int bwCost = 100;
        int colorCosts = 75;

        PrintJobItem bwJob = new PrintJobItem(PrintingSpec.A4_SINGLESIDED_BW, bwPages);
        PrintJobItem colorJob = new PrintJobItem(PrintingSpec.A4_SINGLESIDED_COLOR, colorPages);

        PrintJob job = new PrintJob(
                new PrintJobItem[]{
                        new PrintJobItem(bwJob, new BigDecimal(bwCost)),
                        new PrintJobItem(colorJob, new BigDecimal(colorCosts))
                });
        assertEquals("total costs must add up all job item costs", new BigDecimal(bwCost + colorCosts), job.getTotalCosts());
    }
}
