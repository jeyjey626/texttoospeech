package com.jmakulec.texttoospeech;

import java.io.File;
import java.util.*;
import java.util.stream.*;
import org.apache.commons.io.FilenameUtils;

public class SoundLibraryContent {
    private static final String libURL =  "E:\\Dokumenty\\PracaInz\\";
    private static File folder; //dir of our library

    private static Map<String, File> fileMap; // todo: should it be a hash set?

    public static void inputLibrary(String libURL){ //allows user to input their own library
        folder =  new File(libURL);

        List<File> files = Arrays.asList(Objects.requireNonNull(folder.listFiles()));
        fileMap = files.stream().collect(Collectors.toMap(
                file -> FilenameUtils.getBaseName(String.valueOf(file)),
                file -> file
        )); //creating a map of base filenames with their full paths

    }

    public static List<String> getFileNames(){
        return Arrays.stream(Objects.requireNonNull(folder.listFiles())) //getting a stream of filenames
                .map(file -> FilenameUtils.getBaseName(String.valueOf(file))) //getting just names for comparison
                .collect(Collectors.toList()); //closing as list
    }

    public static Map<String, File> getFileMap() {
        return fileMap;
    }

    public static boolean isInLibrary(String candidate){
        return getFileNames().contains(candidate);
    }
}
