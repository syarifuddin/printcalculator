package com.sen.papercut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Created by sen on 2/06/2015.
 */
public class PrintJobParser {

    public static Iterable<PrintJobItem> parse(String line) {
        String[] s = line.split(", ");

        int totalPages = Integer.parseInt(s[0]);
        int colorPages = Integer.parseInt(s[1]);
        boolean doubleSided = Boolean.parseBoolean(s[2]);

        PrintType printType = doubleSided ? PrintType.DOUBLESIDED : PrintType.SINGLESIDED;
        int bwPages = totalPages - colorPages;

        return createJobItems(colorPages, printType, bwPages);
    }

    private static Iterable<PrintJobItem> createJobItems(int colorPages, PrintType printType, int bwPages) {
        List<PrintJobItem> jobs = new ArrayList<>(2);
        PrintJobItem colorJob = new PrintJobItem(ColorType.COLOR, colorPages, printType);
        PrintJobItem bwJob = new PrintJobItem(ColorType.BLACKWHITE, bwPages, printType);

        return Arrays.asList(colorJob, bwJob);
    }
}
