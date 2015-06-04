package com.sen.papercut;

import java.util.Arrays;
import java.util.stream.Stream;

import static java.util.stream.Stream.concat;

/**
 * Created by sen on 4/06/2015.
 */
public class PrintJobFormatter {
    public static String PrintHeader() {
        return "===============================================================\n" +
                " Page Size | Color | Double Sided | Pages |   Cost    \n" +
                "===============================================================";

    }

    public static Stream<String> PrintJobs(Stream<PrintJob> jobs) {
        return concat(Stream.of(PrintHeader()),
                jobs.flatMap(PrintJobFormatter::PrintJob)
        );
    }

    public static Stream<String> PrintJobTotalCost(PrintJob job) {
        return Stream.of(String.format(
                "----------------------------------------------------------------\n" +
                        " Job Total Cost                               $ %(,.2f          \n" +
                        "----------------------------------------------------------------\n",
                job.getTotalCosts()
        ));
    }

    public static Stream<String> PrintJob(PrintJob job) {
        return concat(Arrays.stream(job.getJobItems()).map(PrintJobFormatter::PrintJobItem),
                PrintJobTotalCost(job));
    }

    public static String PrintJobItem(PrintJobItem item) {
        return String.format(
                "    %s     | %b  |     %b     |  %3s  |   $ %(,.2f    ",
                item.getPrintingSpec().getPageSize(),
                item.getPrintingSpec().getColorType(),
                item.getPrintingSpec().getPrintType(),
                item.getPages(),
                item.getTotalCost().get());
    }
}
