package com.sen.papercut;

import org.junit.Test;

import java.util.Collection;
import java.util.Optional;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by sen on 2/06/2015.
 */
public class PrintJobParserTest {
    @Test
    public void parseSingleSided() {
        String input = "25, 10, false";
        Collection<PrintJobItem> jobItems = PrintJobParser.parse(input);
        boolean allSingleSided = jobItems.stream().allMatch(PrintJobItem::isSingleSided);
        assertTrue("all job items must be single sided", allSingleSided);
    }

    @Test
    public void parseDoubleSidedJobs() {
        String input = "502, 22, true";
        Collection<PrintJobItem> jobItems = PrintJobParser.parse(input);
        boolean allDoubleSided = jobItems.stream().allMatch(PrintJobItem::isDoubleSided);
        assertTrue("all job items must be double sided", allDoubleSided);
    }

    @Test
    public void parseReturnColorAndBlackWhiteJobs() {
        String input = "25, 10, false";
        Collection<PrintJobItem> jobItems = PrintJobParser.parse(input);
        boolean hasBWJob = jobItems.stream().anyMatch(PrintJobItem::isBlackWhite);
        assertTrue("must contain black and white job", hasBWJob);
        boolean hasColorJob = jobItems.stream().anyMatch(PrintJobItem::isColor);
        assertTrue("must contain color job", hasColorJob);
    }

    @Test
    public void parseReturnCorrectNumberOfColorPages() {
        String input = "502, 22, true";
        Collection<PrintJobItem> jobItems = PrintJobParser.parse(input);
        Optional<PrintJobItem> colorJob = jobItems.stream().filter(PrintJobItem::isColor).findAny();
        assertEquals("must return the correct number of color pages", 22, colorJob.get().getPages());
    }

    @Test
    public void parseReturnCorrectNumberOfBlackWhitePages() {
        String input = "502, 22, true";
        Collection<PrintJobItem> jobItems = PrintJobParser.parse(input);
        Optional<PrintJobItem> bwJob = jobItems.stream().filter(PrintJobItem::isBlackWhite).findAny();
        assertEquals("must return the correct number of black and white pages", 480, bwJob.get().getPages());
    }
}
