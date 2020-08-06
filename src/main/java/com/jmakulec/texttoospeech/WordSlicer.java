package com.jmakulec.texttoospeech;

import java.util.ArrayList;

public class WordSlicer {
    public static int polishSymbolsCount = 0;
    public static int corruptedCount = 0; // for counting how many letters excepting

    public static ArrayList<String> sliceText(String input, boolean isAnalysis) {
        //todo polish letter count
        corruptedCount = 0;
        polishSymbolsCount = 0;
        String workable = input;
        int startPoint = 0;
        int stopPoint = input.length();
        //int totalLetterCount; // for counting % of letters covered by the library
        ArrayList<String> workableArrayList = new ArrayList<>();


        while (!workable.equals("")){
            if(SoundLibraryContent.isInLibrary(workable)) {
                workableArrayList.add(workable);
                if (isAnalysis && workable.length() == 2) {
                    if (Character.isUpperCase(workable.charAt(0)) && !Utils.OtherUtils.isVowel(workable.substring(1))) corruptedCount++;
                }
                else if (isAnalysis && workable.length() == 1){
                    if (!Utils.OtherUtils.isVowel(workable)) corruptedCount++;
                }
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
