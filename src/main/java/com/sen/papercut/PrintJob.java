package com.sen.papercut;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by sen on 3/06/2015.
 */
public class PrintJob {
    private final PrintJobItem[] jobItems;

    public PrintJob(PrintJobItem[] jobs) {
        this.jobItems = jobs;
    }

    public PrintJobItem[] getJobItems() {
        return jobItems;
    }

    public int totalPages() {
        return Arrays.stream(jobItems).collect(Collectors.summingInt(PrintJobItem::getPages));
    }

    public PrintJob calculateCosts(Function<PrintingSpec, BigDecimal> unitCostFinder) {
        Stream<PrintJobItem> jobItemStream = Arrays.stream(jobItems).map(j -> j.calculateCost(unitCostFinder));
        PrintJobItem[] jobItems = jobItemStream.toArray(PrintJobItem[]::new);
        return new PrintJob(jobItems);
    }

    public BigDecimal getTotalCosts() {
        return Arrays.stream(this.jobItems).map(ji -> ji.getTotalCost().orElse(new BigDecimal(0)))
                .reduce(new BigDecimal(0), (a, b) -> a.add(b));
    }

    @Override
    public String toString() {
        return "PrintJob{\n" +
                "jobItems=\n" + Arrays.toString(jobItems) +
                "\n totalPages=" + totalPages() +
                '}';
    }
}
