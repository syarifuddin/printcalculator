package com.sen.papercut;

import java.util.Collection;

import static java.util.Arrays.asList;

/**
 * Created by sen on 2/06/2015.
 */
public class PrintJobParser {

    public static Collection<PrintJobItem> parse(String line) {
        String[] s = line.split(", ");

        int totalPages = Integer.parseInt(s[0]);
        int colorPages = Integer.parseInt(s[1]);
        boolean doubleSided = Boolean.parseBoolean(s[2]);

        PrintType printType = doubleSided ? PrintType.DOUBLESIDED : PrintType.SINGLESIDED;
        int bwPages = totalPages - colorPages;

        return createJobItems(colorPages, printType, bwPages);
    }

    private static Collection<PrintJobItem> createJobItems(int colorPages, PrintType printType, int bwPages) {

        PrintJobItem colorJob = new PrintJobItem(PageSize.A4, ColorType.COLOR, printType, colorPages);
        PrintJobItem bwJob = new PrintJobItem(PageSize.A4, ColorType.BLACKWHITE, printType, bwPages);
        return asList(colorJob, bwJob);
    }
}
