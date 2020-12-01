package com.jmakulec.texttoospeech;

import javax.sound.sampled.*;

import java.io.IOException;
import static javax.sound.sampled.AudioSystem.getAudioInputStream;
import static javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED;

public class AudioFilePlayer extends Thread{

    public static volatile boolean runFlag = true;

    public SourceDataLine playingLine;

    private static final byte[] buffer = new byte[65536];
    private static AudioInputStream inputStream;

    @Override
    public void run() {
        playFile(inputStream);
    }

    public static void setPlayableInput(AudioInputStream inputStream) {
        AudioFilePlayer.inputStream = inputStream;
    }

    public void playFile(AudioInputStream inputStream){
        try{
         final AudioFormat outFormat =  getOutFormat(inputStream.getFormat());
         final DataLine.Info info = new DataLine.Info(SourceDataLine.class, outFormat);

         try(final  SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info)) {
             if (line != null){
                 this.playingLine = line;
                 playingLine.open(outFormat);
                 playingLine.start();
                 stream(getAudioInputStream(outFormat, inputStream), playingLine);
                 playingLine.drain();
                 playingLine.stop();
             }
         } catch (LineUnavailableException | IOException e) {
             e.printStackTrace();
         }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static AudioFormat getOutFormat(AudioFormat inFormat) {
        final int ch = inFormat.getChannels();
        final float rate = inFormat.getSampleRate();
        return new AudioFormat(PCM_SIGNED, rate, 16, ch, ch * 2, rate, false);
    }

    private static void stream(AudioInputStream in, SourceDataLine line) throws IOException {
        for (int n = 0; n != -1; n = in.read(buffer, 0, buffer.length)) {
            line.write(buffer, 0, n);
        }
    }

    public void cancelPlaying() {
        if (playingLine != null) playingLine.stop();
    }
}
