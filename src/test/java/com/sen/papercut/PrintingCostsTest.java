package com.sen.papercut;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.stream.Collectors;

import static junit.framework.Assert.assertEquals;

/**
 * Created by sen on 2/06/2015.
 */
@RunWith(value = Parameterized.class)
public class PrintingCostsTest {

    private final PrintingSpec printingSpec;
    private final BigDecimal expectedCosts;

    public PrintingCostsTest(PrintingSpec printingSpec, BigDecimal expectedCosts) {
        this.printingSpec = printingSpec;
        this.expectedCosts = expectedCosts;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return PrintingCosts.PriceList.stream()
                .map(e -> new Object[]{e.getPrintingSpec(), e.getUnitCost()})
                .collect(Collectors.toList());
    }

    @Test
    public void unitCostForPrintSpec() {
        BigDecimal unitCost = PrintingCosts.getUnitCostForSpec(printingSpec);
        assertEquals(expectedCosts, unitCost);
    }
}
