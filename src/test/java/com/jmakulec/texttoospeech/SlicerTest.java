
package com.jmakulec.texttoospeech;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;


import java.util.ArrayList;

public class SlicerTest {

    @Test
    public void shouldSliceText(){
        //given
        WordSlicer slicer = new WordSlicer();
        SoundLibraryContent.inputLibrary("E:\\Dokumenty\\PracaInz\\");

        //when
        ArrayList result = slicer.sliceText("przyjaciel");

        //then
        assertEquals(asList("przy","ja","ciel"), result);
    }

}
