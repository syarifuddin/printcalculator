package com.sen.papercut;

/**
 * Created by sen on 2/06/2015.
 */
public class PrintingSpec {

    public static final PrintingSpec A4_DOUBLESIDED_COLOR =
            new PrintingSpec(PageSize.A4, ColorType.COLOR, PrintType.DOUBLESIDED);

    public static final PrintingSpec A4_DOUBLESIDED_BW =
            new PrintingSpec(PageSize.A4, ColorType.BLACKWHITE, PrintType.DOUBLESIDED);

    public static final PrintingSpec A4_SINGLESIDED_COLOR =
            new PrintingSpec(PageSize.A4, ColorType.COLOR, PrintType.SINGLESIDED);

    public static final PrintingSpec A4_SINGLESIDED_BW =
            new PrintingSpec(PageSize.A4, ColorType.BLACKWHITE, PrintType.SINGLESIDED);

    private final String pageSize;
    private final ColorType colorType;
    private final PrintType printType;

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

    @Override
    public String toString() {
        return "PrintingSpec{" +
                "pageSize='" + pageSize + '\'' +
                ", colorType=" + colorType +
                ", printType=" + printType +
                '}';
    }
}
