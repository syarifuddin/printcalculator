package com.sen.papercut;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by sen on 2/06/2015.
 */
public class PrintJobParser {

    public static PrintJob parseToPrintJob(String line) throws InvalidFormat {
        try {
            String[] s = line.split(", |,");
            validateSplitLength(line, s);

            int totalPages = Integer.parseInt(s[0]);
            int colorPages = Integer.parseInt(s[1]);

            validatePagesNumber(line, totalPages, colorPages);

            validateBooleanString(line, s[2]);
            boolean doubleSided = Boolean.parseBoolean(s[2]);

            PrintType printType = doubleSided ? PrintType.DOUBLESIDED : PrintType.SINGLESIDED;
            int bwPages = totalPages - colorPages;

            return new PrintJob(createJobItems(colorPages, printType, bwPages));
        } catch (NumberFormatException fEx) {
            throw new InvalidFormat(String.format("incorrect format in this line \"%s\"", line));
        }

    }

    private static void validateSplitLength(String line, String[] s) {
        if (s.length < 3) {
            throw new InvalidFormat(String.format("incorrect format in this line \"%s\", " +
                    "line must have 3 fields separated by comma e.g \"90, 20, true\"", line));
        }
    }

    private static void validateBooleanString(String line, String booleanString) {
        if (!isValidBooleanString(booleanString)) {
            throw new InvalidFormat(String.format("incorrect boolean format in this line \"%s\"", line));
        }
    }

    private static void validatePagesNumber(String line, int totalPages, int colorPages) {
        if (totalPages < colorPages) {
            throw new InvalidFormat(String.format(
                    "the total pages %d cannot be less than color pages %d in line: \"%s\"",
                    totalPages, colorPages, line
            ));
        }
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

    private static boolean isValidBooleanString(String theString) {
        return Boolean.TRUE.toString().equalsIgnoreCase(theString)
                || Boolean.FALSE.toString().equalsIgnoreCase(theString);
    }
}
