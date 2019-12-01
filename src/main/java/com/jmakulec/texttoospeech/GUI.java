package com.jmakulec.texttoospeech;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.SequenceInputStream;



public class GUI {
    private static final String libURL =  "E:\\Dokumenty\\PracaInz\\";
    public static void main(String[] args) {
        String wavFile1 = libURL + "przy.wav";
        String wavFile2 = libURL + "ja.wav";
        String wavFile3 = libURL + "ciel.wav";
        try{
            AudioInputStream clip1 = AudioSystem.getAudioInputStream(new File(wavFile1));
            AudioInputStream clip2 = AudioSystem.getAudioInputStream(new File(wavFile2));
            AudioInputStream clip3 = AudioSystem.getAudioInputStream(new File(wavFile3));

            //TODO sound library - extracting names from catalogue and putting them into a data file
            //todo cutting the
            //TODO appending all the sounds loop function

            AudioInputStream tempAppend = appender(clip1, clip2);
            AudioInputStream appendedFiles = appender(tempAppend, clip3);

            AudioSystem.write(appendedFiles,
                    AudioFileFormat.Type.WAVE,
                    new File(libURL + "appended.wav"));

            Clip clip = AudioSystem.getClip();
            clip.open(appendedFiles);
            clip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

    }

    private static AudioInputStream appender(AudioInputStream clip1, AudioInputStream clip2){
        AudioInputStream returnFile = new AudioInputStream(
                new SequenceInputStream(clip1, clip2),
                clip1.getFormat(),
                clip1.getFrameLength() + clip2.getFrameLength()
        );
        return returnFile;
    }
}
