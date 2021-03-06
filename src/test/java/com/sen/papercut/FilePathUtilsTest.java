package com.sen.papercut;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by sen on 5/06/2015.
 */
public class FilePathUtilsTest {

    public static boolean isWindows() {
        return (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0);

    }

    @Test
    public void absolutePathTest() {
        String path = isWindows() ? "C:/abc/def.txt" : "/abc/def/gh.txt";
        assertTrue("Absolute path must return true", FilePathUtils.isAbsolutePath(path));
    }

    @Test
    public void fileNameOnlyPathTest() {
        String path = "gh.txt";
        assertFalse("relative path must return false", FilePathUtils.isAbsolutePath(path));
    }

    @Test
    public void relativePathTest() {
        String path = "someSubPath/gh.txt";
        assertFalse("relative path must return false", FilePathUtils.isAbsolutePath(path));
    }
}
