package com.sen.papercut;

import java.io.File;

/**
 * Created by sen on 5/06/2015.
 */
public class FilePathUtils {
    public static boolean isAbsolutePath(String path) {
        return new File(path).isAbsolute();
    }

    public static boolean isFileExists(String path) {
        return new File(path).exists();
    }
}
