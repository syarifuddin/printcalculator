package com.sen.papercut;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.Function;

/**
 * Created by sen on 2/06/2015.
 */
public class PrintJobItem {
    private int pages;
    private PrintingSpec printingSpec;
    private Optional<BigDecimal> totalCost = Optional.empty();

    public PrintJobItem(String pageSize, ColorType colorType, PrintType printType, int pages) {
        this(new PrintingSpec(pageSize, colorType, printType), pages);
    }

    public PrintJobItem(PrintingSpec printingSpec, int pages) {
        this.printingSpec = printingSpec;
        this.pages = pages;
    }

    public PrintJobItem(PrintJobItem job, BigDecimal totalCost) {
        this(job.printingSpec, job.pages);
        this.totalCost = Optional.of(totalCost);
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

    public Optional<BigDecimal> getTotalCost() {
        return this.totalCost;
    }

    private String totalCostAsSString() {
        String cost = totalCost.isPresent() ? totalCost.get().toString() : "N/A";
        return cost;
    }

    public PrintJobItem calculateCost(Function<PrintingSpec, BigDecimal> unitCostFinder) {
        BigDecimal theTotalCost = unitCostFinder.apply(this.printingSpec).multiply(new BigDecimal(this.getPages()));
        return new PrintJobItem(this, theTotalCost);
    }

    @Override
    public String toString() {
        return "PrintJobItem{" +
                "pages=" + pages +
                ", printingSpec=" + printingSpec +
                ", totalCost=" + totalCostAsSString() +
                '}' + '\n';
    }
}
