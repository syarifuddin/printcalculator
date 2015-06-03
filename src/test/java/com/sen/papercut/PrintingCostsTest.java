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

//
//    @Test
//    public void unitCostsTest() {
//        PrintingSpec a4DoubleSidedBw = new PrintingSpec(PageSize.A4, ColorType.BLACKWHITE, PrintType.DOUBLESIDED);
//        assertEquals(new BigDecimal(0.10), PrintingCosts.getUnitCostForSpec(a4DoubleSidedBw));
//        PrintingSpec a4DoubleSidedColor = new PrintingSpec(PageSize.A4, ColorType.COLOR, PrintType.DOUBLESIDED);
//        assertEquals(new BigDecimal(0.20), PrintingCosts.getUnitCostForSpec(a4DoubleSidedColor));
//        PrintingSpec a4SingleSidedBw = new PrintingSpec(PageSize.A4, ColorType.BLACKWHITE, PrintType.SINGLESIDED);
//        assertEquals(new BigDecimal(0.15), PrintingCosts.getUnitCostForSpec(a4SingleSidedBw));
//
//        PrintingSpec a4SingleSidedColor = new PrintingSpec(PageSize.A4, ColorType.COLOR, PrintType.SINGLESIDED);
//        assertEquals(new BigDecimal(0.25), PrintingCosts.getUnitCostForSpec(a4SingleSidedColor));
//    }


    @Test
    public void unitCostForPrintSpec() {
        BigDecimal unitCost = PrintingCosts.getUnitCostForSpec(printingSpec);
        assertEquals(expectedCosts, unitCost);
    }
}
