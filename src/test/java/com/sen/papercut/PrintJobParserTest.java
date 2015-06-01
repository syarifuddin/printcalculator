package com.sen.papercut;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by sen on 2/06/2015.
 */
public class PrintJobParserTest {
    @Test
    public void parseSingleSided() {
        String input = "25, 10, false";
        Iterable<PrintJobItem> jobItems = PrintJobParser.parse(input);
        for (PrintJobItem jobItem : jobItems) {
            assertEquals("all job items must be single sided", PrintType.SINGLESIDED, jobItem.getPrintType());
        }
    }
}
