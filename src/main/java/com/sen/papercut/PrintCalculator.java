package com.sen.papercut;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class PrintCalculator
{
    public static void main( String[] args )
    {
        String filePath = null;
        Path thePath = null;
        if (args.length > 0) {
            filePath = args[0];
            thePath = makeAbsolutePath(filePath);
        }

        if (args.length == 0) {
            thePath = promptFilePath();
        }

        while (!FilePathUtils.isFileExists(thePath)) {
            thePath = promptFilePath();
        }

        try {
            PrintJobFormatter.PrintJobs(readPrintJobFrom(thePath)).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Path promptFilePath() {
        String filePath;// create a scanner so we can read the command-line input
        Scanner scanner = new Scanner(System.in);

        System.out.println("Cannot read input file.");

        System.out.print("Please enter the input file path:");
        filePath = scanner.next();
        return makeAbsolutePath(filePath);

    }

    private static Path makeAbsolutePath(String filePath) {
        if (!FilePathUtils.isAbsolutePath(filePath)) {
            String baseDir = System.getProperty("user.dir");
            return Paths.get(baseDir, filePath);
        } else {
            return Paths.get(filePath);
        }
    }

    private static List<PrintJob> readPrintJobFrom(Path filePath) throws IOException {

        return Files.lines(filePath).filter(s -> s.trim().length() > 0).map(PrintJobParser::parseToPrintJob)
                .map(j -> j.calculateCosts(PrintingCosts::getUnitCostForSpec))
                .collect(Collectors.toList());
    }
}
