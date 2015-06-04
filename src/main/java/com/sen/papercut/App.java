package com.sen.papercut;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final NumberFormat money;

    static {
        money = NumberFormat.getCurrencyInstance(Locale.CANADA);
        money.setRoundingMode(RoundingMode.HALF_EVEN);
    }

    public static void main( String[] args )
    {
        Path path = Paths.get("/Users/sen/Dev/printcalculator", "printjobs.csv");

        try {
            List<PrintJob> jobList = readPrintJobFrom(path).collect(Collectors.toList());
            PrintJobFormatter.PrintJobs(readPrintJobFrom(path)).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String moneyFormat(BigDecimal price) {
        return money.format(price.setScale(2, RoundingMode.HALF_EVEN));
    }

    private static Stream<PrintJob> readPrintJobFrom(Path filePath) throws IOException {

        return Files.lines(filePath).map(PrintJobParser::parseToPrintJob).map(j -> j.calculateCosts(PrintingCosts::getUnitCostForSpec));
    }
}
