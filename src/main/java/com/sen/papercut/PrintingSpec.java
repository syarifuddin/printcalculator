package com.sen.papercut;

/**
 * Created by sen on 2/06/2015.
 */
public class PrintingSpec {
    private String pageSize;
    private ColorType colorType;
    private PrintType printType;

    public PrintingSpec(String pageSize, ColorType colorType, PrintType printType) {
        this.pageSize = pageSize;
        this.colorType = colorType;
        this.printType = printType;
    }

    public String getPageSize() {
        return pageSize;
    }

    public ColorType getColorType() {
        return colorType;
    }

    public PrintType getPrintType() {
        return printType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrintingSpec that = (PrintingSpec) o;

        if (!pageSize.equals(that.pageSize)) return false;
        if (colorType != that.colorType) return false;
        return printType == that.printType;

    }

    @Override
    public int hashCode() {
        int result = pageSize.hashCode();
        result = 31 * result + colorType.hashCode();
        result = 31 * result + printType.hashCode();
        return result;
    }
}
