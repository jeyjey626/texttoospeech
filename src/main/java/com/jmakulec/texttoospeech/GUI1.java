package com.jmakulec.texttoospeech;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class GUI1 {
    private JFrame frame;
    private static final String libURL =  "E:\\Dokumenty\\PracaInz\\";
    private static boolean done = false;
    public static void main(String[] args) {

        //SwingUtilities.invokeLater(GUI1::new);

        SoundLibraryContent.inputLibrary(libURL);
        System.out.println(SoundLibraryContent.getFileMap());

        String wavFile1 = libURL + "przy.wav";
        String wavFile2 = libURL + "ja.wav";
        String wavFile3 = libURL + "ciel.wav";

        try (Stream<Path> walk = Files.walk(Paths.get(libURL))) {

            List<String> result = walk.filter(Files::isRegularFile)
                    .map(Path::toString).collect(Collectors.toList());

            result.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            AudioInputStream clip1 = AudioSystem.getAudioInputStream(new File(wavFile1));
            AudioInputStream clip2 = AudioSystem.getAudioInputStream(new File(wavFile2));
            AudioInputStream clip3 = AudioSystem.getAudioInputStream(new File(wavFile3));

            //TODO sound library - extracting names from catalogue and putting them into a data file
            //todo cutting the



            AudioInputStream tempAppend = appender(clip1, clip2); //TODO appending all the sounds loop function
            AudioInputStream appendedFiles = appender(tempAppend, clip3);
            AudioFilePlayer.playWord(appendedFiles);

            AudioSystem.write(appendedFiles,
                    AudioFileFormat.Type.WAVE,
                    new File(libURL + "appended.wav"));


        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }

    }

    private static AudioInputStream appender(AudioInputStream clip1, AudioInputStream clip2){
        return new AudioInputStream(
                new SequenceInputStream(clip1, clip2),
                clip1.getFormat(),
                clip1.getFrameLength() + clip2.getFrameLength()
        );
    }


    private GUI1(){
        initUI();
    }

    private void initUI(){
        frame = new JFrame("Text to speech");
        frame.setVisible(true);
    }
}
