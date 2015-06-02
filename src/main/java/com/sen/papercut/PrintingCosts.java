package com.sen.papercut;

import java.math.BigDecimal;
import java.util.Collection;

import static java.util.Arrays.asList;

/**
 * Created by sen on 2/06/2015.
 */
public class PrintingCosts {

    public static Collection<PrintingCosts> PriceList;

    static {
        PriceList = asList(
                new PrintingCosts(PrintingSpec.A4_SINGLESIDED_BW, new BigDecimal(0.15)),
                new PrintingCosts(PrintingSpec.A4_SINGLESIDED_COLOR, new BigDecimal(0.25)),
                new PrintingCosts(PrintingSpec.A4_DOUBLESIDED_BW, new BigDecimal(0.10)),
                new PrintingCosts(PrintingSpec.A4_DOUBLESIDED_COLOR, new BigDecimal(0.20))
        );
    }

    private PrintingSpec printingSpec;
    private BigDecimal unitCost;

    public PrintingCosts(PrintingSpec printingSpec, BigDecimal unitCost) {
        this.printingSpec = printingSpec;
        this.unitCost = unitCost;
    }

    public static BigDecimal getUnitCostForSpec(PrintingSpec spec) {
        return PriceList.stream().filter(p -> spec.equals(p.printingSpec)).map(PrintingCosts::getUnitCost).findFirst().get();
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public PrintingSpec getPrintingSpec() {
        return printingSpec;
    }

}
