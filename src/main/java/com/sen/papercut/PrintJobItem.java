package com.sen.papercut;

import java.math.BigDecimal;
import java.util.function.Function;

/**
 * Created by sen on 2/06/2015.
 */
public class PrintJobItem {
    private int pages;
    private PrintingSpec printingSpec;

    public PrintJobItem(String pageSize, ColorType colorType, PrintType printType, int pages) {
        this(new PrintingSpec(pageSize, colorType, printType), pages);
    }

    public PrintJobItem(PrintingSpec printingSpec, int pages) {
        this.printingSpec = printingSpec;
        this.pages = pages;
    }

    public PrintingSpec getPrintingSpec() {
        return printingSpec;
    }

    public int getPages() {
        return pages;
    }

    public boolean isDoubleSided() {
        return this.printingSpec.getPrintType() == PrintType.DOUBLESIDED;
    }

    public boolean isSingleSided() {
        return this.printingSpec.getPrintType() == PrintType.SINGLESIDED;
    }

    public boolean isBlackWhite() {
        return this.printingSpec.getColorType() == ColorType.BLACKWHITE;
    }

    public boolean isColor() {
        return this.printingSpec.getColorType() == ColorType.COLOR;
    }

    public BigDecimal calculateCost(Function<PrintingSpec, BigDecimal> unitCostFinder) {
        return unitCostFinder.apply(this.printingSpec).multiply(new BigDecimal(this.getPages()));
    }

    @Override
    public String toString() {
        return "PrintJobItem{" +
                "pages=" + pages +
                ", printingSpec=" + printingSpec +
                '}';
    }
}
