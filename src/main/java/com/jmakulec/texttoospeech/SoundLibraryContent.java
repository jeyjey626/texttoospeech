package com.jmakulec.texttoospeech;

import java.io.File;
import java.util.*;
import java.util.stream.*;
import org.apache.commons.io.FilenameUtils;

public class SoundLibraryContent {
    private static final String libURL =  "E:\\Dokumenty\\PracaInz\\";
    private static File folder;

    private static Map<String, File> fileMap; // = Arrays.asList(Objects.requireNonNull(folder.listFiles()));

    public static void inputLibrary(String libURL){
        folder =  new File(libURL);

        List<File> files = Arrays.asList(Objects.requireNonNull(folder.listFiles()));
        fileMap = files.stream().collect(Collectors.toMap(
                file -> FilenameUtils.getBaseName(String.valueOf(file)),
                file -> file
        ));
    }

    public static Map<String, File> getFileMap() {
        return fileMap;
    }
}
