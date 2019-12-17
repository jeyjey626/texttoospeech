package com.jmakulec.texttoospeech;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SentenceSlicer {
    public ArrayList sliceText(String input) {
        String[] words = input.split("\\W+");
        return (ArrayList) Arrays.stream(words).collect(Collectors.toList());
    }
}
