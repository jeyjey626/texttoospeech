package com.jmakulec.texttoospeech;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;


public class GUI1 {
    private JFrame frame;
    private static final String libURL =  "E:\\Dokumenty\\PracaInz\\soundfiles\\";
    private static boolean done = false;
    public static void main(String[] args) {

        //SwingUtilities.invokeLater(GUI1::new);

        SoundLibraryContent.inputLibrary(libURL);


        /*try{
            ArrayList list = WordSlicer.sliceText("przyjaciel");
            AudioInputStream pleaseWork = AudioAppender.appendFiles(list);
            AudioFilePlayer.playWord(pleaseWork);
            System.out.println(WordSlicer.sliceText("przyjaciel"));
        } catch (Exception e) {
            e.printStackTrace();
        }*/


        ArrayList<String> sliced = SentenceSlicer.sliceText("przyjaciel przy");


        for (String s:sliced){
            try {
                AudioFilePlayer.playWord(AudioAppender.appendFiles(WordSlicer.sliceText(s)));
            } catch (IOException | UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
        }

        System.out.println();

        //System.out.println(SoundLibraryContent.getFileMap());

        /*String wavFile1 = libURL + "przy.wav";
        String wavFile2 = libURL + "ja.wav";
        String wavFile3 = libURL + "ciel.wav";

       /* try (Stream<Path> walk = Files.walk(Paths.get(libURL))) {

            List<String> result = walk.filter(Files::isRegularFile)
                    .map(Path::toString).collect(Collectors.toList());

            result.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }*/
        /*try{
            AudioInputStream clip1 = AudioSystem.getAudioInputStream(new File(wavFile1));
            AudioInputStream clip2 = AudioSystem.getAudioInputStream(new File(wavFile2));
            AudioInputStream clip3 = AudioSystem.getAudioInputStream(new File(wavFile3));





            AudioInputStream tempAppend = appender(clip1, clip2); //TODO appending all the sounds loop function
            AudioInputStream appendedFiles = appender(tempAppend, clip3);
            AudioFilePlayer.playWord(appendedFiles);

            AudioSystem.write(appendedFiles,
                    AudioFileFormat.Type.WAVE,
                    new File(libURL + "appended.wav"));


        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }*/

    }

   /* private static AudioInputStream appender(AudioInputStream clip1, AudioInputStream clip2){
        return new AudioInputStream(
                new SequenceInputStream(clip1, clip2),
                clip1.getFormat(),
                clip1.getFrameLength() + clip2.getFrameLength()
        );
    }*/


    private GUI1(){
        initUI();
    }

    private void initUI(){
        frame = new JFrame("Text to speech");
        frame.setVisible(true);
    }
}
