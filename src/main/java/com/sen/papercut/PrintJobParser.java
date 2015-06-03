package com.sen.papercut;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by sen on 2/06/2015.
 */
public class PrintJobParser {

    public static PrintJob parse(String line) {
        String[] s = line.split(", ");

        int totalPages = Integer.parseInt(s[0]);
        int colorPages = Integer.parseInt(s[1]);
        boolean doubleSided = Boolean.parseBoolean(s[2]);

        PrintType printType = doubleSided ? PrintType.DOUBLESIDED : PrintType.SINGLESIDED;
        int bwPages = totalPages - colorPages;

        return new PrintJob(createJobItems(colorPages, printType, bwPages));
    }

    private static PrintJobItem[] createJobItems(int colorPages, PrintType printType, int bwPages) {
        Collection<PrintJobItem> jobs = new ArrayList<>();
        if (colorPages > 0) {
            PrintJobItem colorJob = new PrintJobItem(PageSize.A4, ColorType.COLOR, printType, colorPages);
            jobs.add(colorJob);
        }
        if (bwPages > 0) {
            PrintJobItem bwJob = new PrintJobItem(PageSize.A4, ColorType.BLACKWHITE, printType, bwPages);
            jobs.add(bwJob);
        }
        return jobs.toArray(new PrintJobItem[jobs.size()]);
    }
}
