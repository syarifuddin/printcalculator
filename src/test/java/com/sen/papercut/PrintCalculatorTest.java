package com.sen.papercut;

import org.junit.Test;

import java.util.Collection;

import static java.util.Arrays.asList;

/**
 * Created by sen on 3/06/2015.
 */
public class PrintCalculatorTest {
    @Test
    public void readStream() {
        Collection<String> lines = asList(
                "25, 10, false",
                "55, 13, true",
                "502, 22, true",
                "1, 0, false"
        );
        lines.stream().flatMap(j -> PrintJobParser.parse(j).stream()).forEach(System.out::println);
//                .collect(Collectors.toMap(Function.identity(), j->j.calculateCost(PrintingCosts::getUnitCostForSpec)))
//                .forEach((j,c)-> System.out.println("Job :" + j.toString() + " costs:" + c.toString()));
    }
}
