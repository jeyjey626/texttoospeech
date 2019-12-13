package com.jmakulec.texttoospeech;

import java.util.ArrayList;
import java.util.ArrayList;

public class Slicer {

    private static ArrayList<String> workableArrayList;

   public static ArrayList<String> sliceText(String input){
        workableArrayList = new ArrayList<String>();
        int length = input.length();
        String startString = input;
        StringBuilder workable = null;
        String rest;
        for (int i = 0; i < length; i++){

            if (workable != null) {
                startString = startString.substring(1);
                workable.append(String.valueOf(startString.charAt(0))); //loading next letters into comparable string
            }else workable = new StringBuilder(String.valueOf(startString.charAt(0)));
            //rest = startString.substring(1, i+1); //leaving the rest for further slicing

            if (SoundLibraryContent.isInLibrary(String.valueOf(workable))){
                //startString = rest;
                workableArrayList.add(String.valueOf(workable));
                workable = null;
            }
        }
       //ArrayList<String> ArrayList = workableArrayList;
        return workableArrayList;
    }

    private static boolean compareToLib(String candidate){
        if(SoundLibraryContent.isInLibrary(candidate)){
            workableArrayList.add(candidate);
            System.out.println(workableArrayList);
            return true;
        }else return false;
    }
}
