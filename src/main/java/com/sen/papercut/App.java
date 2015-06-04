package com.sen.papercut;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Path path = Paths.get("/Users/sen/Dev/printcalculator", "printjobs.csv");

        try {
            PrintJobFormatter.PrintJobs(readPrintJobFrom(path)).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<PrintJob> readPrintJobFrom(Path filePath) throws IOException {

        return Files.lines(filePath).map(PrintJobParser::parseToPrintJob)
                .map(j -> j.calculateCosts(PrintingCosts::getUnitCostForSpec))
                .collect(Collectors.toList());
    }
}
