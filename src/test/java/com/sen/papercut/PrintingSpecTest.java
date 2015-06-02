package com.sen.papercut;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;

/**
 * Created by sen on 3/06/2015.
 */
public class PrintingSpecTest {

    @Test
    public void printingSpecEqualityTest() {
        PrintingSpec spec1 = new PrintingSpec(PageSize.A4, ColorType.BLACKWHITE, PrintType.DOUBLESIDED);
        PrintingSpec spec2 = new PrintingSpec(PageSize.A4, ColorType.BLACKWHITE, PrintType.DOUBLESIDED);
        assertTrue(spec1.equals(spec2));
    }

}
