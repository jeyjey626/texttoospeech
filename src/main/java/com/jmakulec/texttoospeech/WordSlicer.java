package com.jmakulec.texttoospeech;

import java.util.ArrayList;

public class WordSlicer {

//todo jednosylabowe? 

    public static ArrayList<String> sliceText(String input) {

        String workable = input;
        int startPoint = 0;
        int stopPoint = input.length();
        ArrayList<String> workableArrayList = new ArrayList<>();


        while (!workable.equals("")){
            if(SoundLibraryContent.isInLibrary(workable)) {
                workableArrayList.add(workable);
                startPoint = workableArrayList.stream().mapToInt(String::length).sum();
                stopPoint = input.length();
                workable = input.substring(startPoint);

            }
            else {
                workable = input.substring(startPoint, stopPoint );
                stopPoint --;
            }
        }



        return workableArrayList;
    }
}
