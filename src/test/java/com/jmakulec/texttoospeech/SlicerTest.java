
package com.jmakulec.texttoospeech;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;


import java.util.ArrayList;

public class SlicerTest {

    @Test
    public void shouldSliceOneSyllables(){
        SoundLibraryContent.inputLibrary("E:\\Dokumenty\\PracaInz\\soundfiles\\");
        ArrayList result = WordSlicer.sliceText("przy", false);
        assertEquals(asList("przy"), result);
    }

    @Test
    public void shouldSliceText(){
        //given
        SoundLibraryContent.inputLibrary("E:\\Dokumenty\\PracaInz\\soundfiles\\");

        //when
        ArrayList result = WordSlicer.sliceText("przy", false);

        //then
        assertEquals(asList("przy"), result);
    }

    @Test
    public void shouldSliceSentences(){
        //given
        //SentenceSlicer slicer = new SentenceSlicer();

        //when
        ArrayList result = Utils.TextProcessing.sliceText("przyjaciel ';';'; .,przyjaciel elo siema . /*-");

        //then
        assertEquals(asList("przyjaciel", "przyjaciel", "elo", "siema"), result);
    }

}
