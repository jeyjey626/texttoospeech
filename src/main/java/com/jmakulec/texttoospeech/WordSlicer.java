package com.jmakulec.texttoospeech;

import java.util.ArrayList;

public class WordSlicer {
    public static int polishSymbolsCount = 0;
    public static int corruptedCount = 0; // for counting how many characters are

    public static ArrayList<String> sliceText(String input, boolean isAnalysis) {
        corruptedCount = 0;
        polishSymbolsCount = 0;
        String workable = input;
        int startPoint = 0;
        int stopPoint = input.length();
        ArrayList<String> workableArrayList = new ArrayList<>();

        while (!workable.equals("")){
            if(SoundLibraryContent.isInLibrary(workable)) {
                workableArrayList.add(workable);
                if(isAnalysis) {
                    if (workable.length() == 2) { if (Character.isUpperCase(workable.charAt(0)) && Utils.OtherUtils.isConsonant(workable.substring(1))) corruptedCount++; }
                    else if (workable.length() == 1){ if (Utils.OtherUtils.isConsonant(workable)) corruptedCount++; }
                    for (int i = 0; i < workable.length(); i++) {
                        if (Utils.OtherUtils.isPolishSymbol(String.valueOf(workable.charAt(i)))) polishSymbolsCount++;
                    }
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
        polishSymbolsCount = polishSymbolsCount != 0 ? polishSymbolsCount/2 : 0;
        return workableArrayList;
    }
}
