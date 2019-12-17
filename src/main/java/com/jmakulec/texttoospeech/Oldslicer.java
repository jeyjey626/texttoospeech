package com.jmakulec.texttoospeech;

import java.util.ArrayList;
import java.util.ArrayList;

public class Oldslicer {

    private static ArrayList<String> workableArrayList;


   public static ArrayList<String> sliceText(String input){
        workableArrayList = new ArrayList<String>();
        int length = input.length();
        String startString = input; //what are we working currently
        StringBuilder workable = null; //part we're comparing
        String rest = input;
        for (int i = 0; i < length; i++){ //iterating  on the whole word

            if (workable != null) {
                startString = startString.substring(1);
                rest = startString.substring(1);
                workable.append(String.valueOf(startString.charAt(0))); //loading next letters into comparable string
            }else workable = new StringBuilder(String.valueOf(startString.charAt(0)));
            //rest = startString.substring(1, i+1); //leaving the rest for further slicing

            if (SoundLibraryContent.isInLibrary(String.valueOf(workable))){
                //startString = rest;
                workableArrayList.add(String.valueOf(workable));
                startString = rest;
                workable = null;
                //todo: what if there's a typo - how long should be the workable
                //todo: slice workable from the top trying to find something already in it
                //todo: play the typo separately
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
