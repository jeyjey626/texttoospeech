package com.jmakulec.texttoospeech;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.List;

public class AudioAppender {
    private static final String libURL =  "E:\\Dokumenty\\PracaInz\\";
    //todo make liburl an input


//todo: wrong name files
    public static AudioInputStream appendFiles(List<String> list) throws IOException, UnsupportedAudioFileException {
        AudioInputStream tempAppend = null;
        int numberOfFiles = list.size();
        for (int i = 0; i < list.size() - 1; i++){
            if (tempAppend == null){
                tempAppend = appender(AudioSystem.getAudioInputStream(new File(libURL + list.get(0) + ".wav")),
                        AudioSystem.getAudioInputStream(new File(libURL + list.get(1) + ".wav")));
            }
            else tempAppend = appender(tempAppend,
                    AudioSystem.getAudioInputStream(new File(libURL + list.get(i+1) + ".wav")));
        }
        return tempAppend;
    }

    private static AudioInputStream appender(AudioInputStream clip1, AudioInputStream clip2) throws IOException, UnsupportedAudioFileException {
        return new AudioInputStream(
                new SequenceInputStream(clip1, clip2),
                clip1.getFormat(),
                clip1.getFrameLength() + clip2.getFrameLength()
        );
    }
}
