package com.sen.papercut;

import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

import static junit.framework.Assert.*;

/**
 * Created by sen on 2/06/2015.
 */
public class PrintJobParserTest {
    @Test
    public void parseSingleSided() {
        String input = "25, 10, false";
        PrintJob job = PrintJobParser.parse(input);
        boolean allSingleSided = Arrays.stream(job.getJobItems()).allMatch(PrintJobItem::isSingleSided);
        assertTrue("all job items must be single sided", allSingleSided);
    }

    @Test
    public void parseDoubleSidedJobs() {
        String input = "502, 22, true";
        PrintJob job = PrintJobParser.parse(input);
        boolean allDoubleSided = Arrays.stream(job.getJobItems()).allMatch(PrintJobItem::isDoubleSided);
        assertTrue("all job items must be double sided", allDoubleSided);
    }

    @Test
    public void parseReturnColorAndBlackWhiteJobs() {
        String input = "25, 10, false";
        PrintJob job = PrintJobParser.parse(input);
        boolean hasBWJob = Arrays.stream(job.getJobItems()).anyMatch(PrintJobItem::isBlackWhite);
        assertTrue("must contain black and white job", hasBWJob);
        boolean hasColorJob = Arrays.stream(job.getJobItems()).anyMatch(PrintJobItem::isColor);
        assertTrue("must contain color job", hasColorJob);
    }

    @Test
    public void parse_ShouldNot_ReturnColorJob_IfColorPages_IsZero() {
        String input = "25, 0, false";
        PrintJob job = PrintJobParser.parse(input);
        boolean hasColorJob = Arrays.stream(job.getJobItems()).anyMatch(PrintJobItem::isColor);
        assertFalse("must not contain color job", hasColorJob);
    }

    @Test
    public void parse_ShouldNot_ReturnBlackWhiteJob_IfBlackWhitePages_IsZero() {
        String input = "10, 10, true";
        PrintJob job = PrintJobParser.parse(input);
        boolean hasBWJob = Arrays.stream(job.getJobItems()).anyMatch(PrintJobItem::isBlackWhite);
        assertFalse("must not contain black and white job", hasBWJob);
    }

    @Test
    public void parseReturnCorrectNumberOfColorPages() {
        String input = "502, 22, true";
        PrintJob job = PrintJobParser.parse(input);
        Optional<PrintJobItem> colorJob = Arrays.stream(job.getJobItems()).filter(PrintJobItem::isColor).findAny();
        assertEquals("must return the correct number of color pages", 22, colorJob.get().getPages());
    }

    @Test
    public void parseReturnCorrectNumberOfBlackWhitePages() {
        String input = "502, 22, true";
        PrintJob job = PrintJobParser.parse(input);
        Optional<PrintJobItem> bwJob = Arrays.stream(job.getJobItems()).filter(PrintJobItem::isBlackWhite).findAny();
        assertEquals("must return the correct number of black and white pages", 480, bwJob.get().getPages());
    }
}
