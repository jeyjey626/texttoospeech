
package com.jmakulec.texttoospeech;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;


import java.util.ArrayList;

public class SlicerTest {

    @Test
    public void shouldSliceText(){
        //given
        SoundLibraryContent.inputLibrary("E:\\Dokumenty\\PracaInz\\");

        //when
        ArrayList result = WordSlicer.sliceText("przyjaciel");

        //then
        assertEquals(asList("przy","ja","ciel"), result);
    }

    @Test
    public void shouldSliceSentences(){
        //given
        SentenceSlicer slicer = new SentenceSlicer();

        //when
        ArrayList result = slicer.sliceText("przyjaciel ';';'; .,przyjaciel elo siema . /*-");

        //then
        assertEquals(asList("przyjaciel", "przyjaciel", "elo", "siema"), result);
    }

    @Test
    public void shouldSliceSentenceToFileNames(){
        //given
        SoundLibraryContent.inputLibrary("E:\\Dokumenty\\PracaInz\\");
        SentenceSlicer slicer = new SentenceSlicer();

        //when
        ArrayList<String> sliced = slicer.sliceText("przyjaciel, przyja");


        //then
        assertEquals(asList("przy","ja","ciel","przy","ja"), sliced);

    }

}
