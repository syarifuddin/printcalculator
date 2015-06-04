package com.sen.papercut;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Stream.concat;

/**
 * Created by sen on 4/06/2015.
 */
public class PrintJobFormatter {
    public static String PrintHeader() {
        return "===============================================================\n" +
                " Page Size |  Color  | Double Sided | Pages |   Cost    \n" +
                "===============================================================";

    }

    public static Stream<String> PrintJobs(List<PrintJob> jobs) {
        return concat(
                concat(Stream.of(PrintHeader()),
                        jobs.stream().flatMap(PrintJobFormatter::PrintJob)),
                PrintJobsTotalCosts(jobs));
    }

    private static Stream<String> PrintJobsTotalCosts(List<PrintJob> jobs) {

        BigDecimal allJobTotalCosts = jobs.stream().map(PrintJob::getTotalCosts).reduce(new BigDecimal(0), (a, b) -> a.add(b));
        return Stream.of(
                String.format(
                        "                                                ================\n" +
                                "                            Jobs Total Cost:      $ %(,.2f      \n" +
                                "                                                ================"
                        ,
                        allJobTotalCosts));
    }

    public static Stream<String> PrintJobTotalCost(PrintJob job) {
        return Stream.of(String.format(
                "---------------------------------------------------------------\n" +
                        "                         Job Sub Total Cost:    $ %(,.2f       \n" +
                        "                                                ===============\n"
                , job.getTotalCosts()
        ));
    }

    public static Stream<String> PrintJob(PrintJob job) {
        return concat(Arrays.stream(job.getJobItems()).map(PrintJobFormatter::PrintJobItem),
                PrintJobTotalCost(job));
    }

    public static String PrintJobItem(PrintJobItem item) {
        return String.format(
                "    %s     |  %-5s  |     %s        |  %3s  |   $ %(,.2f    ",
                item.getPrintingSpec().getPageSize(),
                formatColorType(item.getPrintingSpec().getColorType()),
                formatPrintType(item.getPrintingSpec().getPrintType()),
                item.getPages(),
                item.getTotalCost().get());
    }

    private static String formatColorType(ColorType colorType) {
        return ColorType.COLOR == colorType ? "Color" : "B/W";
    }

    private static String formatPrintType(PrintType printType) {
        return PrintType.DOUBLESIDED == printType ? "Y" : "N";
    }
}
