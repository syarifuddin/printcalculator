package com.sen.papercut;

/**
 * Created by sen on 2/06/2015.
 */
public class PrintJobItem {
    private ColorType colorType;
    private int pages;
    private PrintType printType;

    public PrintJobItem(ColorType colorType, int pages, PrintType printType) {
        this.colorType = colorType;
        this.pages = pages;
        this.printType = printType;
    }

    public ColorType getColorType() {
        return colorType;
    }

    public int getPages() {
        return pages;
    }

    public PrintType getPrintType() {
        return printType;
    }
}
