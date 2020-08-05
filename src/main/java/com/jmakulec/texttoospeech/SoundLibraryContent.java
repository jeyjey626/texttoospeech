package com.jmakulec.texttoospeech;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import org.apache.commons.io.FilenameUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SoundLibraryContent {
    public static String getLibraryPath() {
        return libraryPath;
    }

    public static boolean isFileMapEmpty() {
        return fileMap.isEmpty();
    }

    private static String libraryPath;
    private static File folder; //dir of our library

    private static Map<String, File> fileMap; // todo: should it be a hash set?

    public static void inputLibrary(String libURL){ //allows user to input their own library
        libraryPath = libURL;
        folder =  new File(libURL);

        List<File> files = Arrays.asList(Objects.requireNonNull(folder.listFiles()));
        fileMap = files.stream()
                .filter( file -> FilenameUtils.getExtension(file.getName()).equals("wav"))
                .collect(Collectors.toMap(
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

    // saving library path to JSON
    public static void saveLibraryPath(String inputPath) {
        JSONObject libraryDetails = new JSONObject();
        libraryDetails.put("path", inputPath);

        try (FileWriter file = new FileWriter("libraryPath.json")) {
            file.write(libraryDetails.toJSONString());
            file.flush();
            System.out.println("yup");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // recovering data from saved json or informing there is no such data saved / no json
    public static boolean isInitLibrary(){
        boolean retValue = false;
        JSONParser jsonParser = new org.json.simple.parser.JSONParser();
        try (FileReader reader = new FileReader("libraryPath.json")){
            Object obj = jsonParser.parse(reader);
            JSONObject pathJSON = (JSONObject) obj;
            inputLibrary( (String) pathJSON.get("path"));
            retValue = !fileMap.isEmpty();
            System.out.println(retValue);
            System.out.println(fileMap);
        } catch (FileNotFoundException e) {
            retValue = false;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return retValue;
    }
}
