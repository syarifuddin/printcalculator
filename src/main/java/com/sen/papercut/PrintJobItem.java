package com.sen.papercut;

/**
 * Created by sen on 2/06/2015.
 */
public class PrintJobItem {
    private int pages;
    private PrintingSpec printingSpec;

    public PrintJobItem(String pageSize, ColorType colorType, PrintType printType, int pages) {
        this.printingSpec = new PrintingSpec(pageSize, colorType, printType);
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
}
