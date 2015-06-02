package com.sen.papercut;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by sen on 3/06/2015.
 */
public class PrintJob {
    private PrintJobItem[] jobItems;

    public PrintJob(Collection<PrintJobItem> jobs) {
        jobItems = new PrintJobItem[jobs.size()];
        this.jobItems = jobs.toArray(jobItems);
    }

    public PrintJobItem[] getJobItems() {
        return jobItems;
    }

    public int totalPages() {
        return Arrays.stream(jobItems).collect(Collectors.summingInt(PrintJobItem::getPages));
    }

    @Override
    public String toString() {
        return "PrintJob{\n" +
                "jobItems=\n" + Arrays.toString(jobItems) +
                "\n totalPages=" + totalPages() +
                '}';
    }
}
