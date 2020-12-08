package com.jmakulec.texttoospeech;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.List;

public class AudioAppender {
    public static AudioInputStream appendWordFragments(List<String> list) throws IOException, UnsupportedAudioFileException {
        AudioInputStream pause = AudioSystem.getAudioInputStream(SoundLibraryContent.getFromFileMap("0pause0"));
        AudioInputStream tempAppend = null;
        if (list.size() == 1) {
            tempAppend = appender(AudioSystem.getAudioInputStream(SoundLibraryContent.getFromFileMap(list.get(0))),
                    pause);
        }
        else {
            for (int i = 0; i < list.size() - 1; i++) {
                if (tempAppend == null) {
                    tempAppend = appender(AudioSystem.getAudioInputStream(SoundLibraryContent.getFromFileMap(list.get(0))),
                            AudioSystem.getAudioInputStream(SoundLibraryContent.getFromFileMap(list.get(1))));
                } else tempAppend = appender(tempAppend,
                        AudioSystem.getAudioInputStream(SoundLibraryContent.getFromFileMap(list.get(i + 1))));
            }
        }
        return tempAppend;
    }

    public static AudioInputStream appendWords(List<AudioInputStream> list) throws IOException, UnsupportedAudioFileException {
        AudioInputStream pause = AudioSystem.getAudioInputStream(SoundLibraryContent.getFromFileMap("0pause0"));
        AudioInputStream tempAppend = null;
        if (list.size() == 1) {
            tempAppend = appender(list.get(0), pause);
        }
        else {
            for (AudioInputStream word : list) {
                if (tempAppend == null) tempAppend = appender(word, pause);
                else if (word != null){
                    AudioInputStream tempPrevious = appender(tempAppend, word);
                    tempAppend = appender(tempPrevious, pause);
                }
            }
        }
        return tempAppend;
    }

    private static AudioInputStream appender(AudioInputStream clip1, AudioInputStream clip2) {
        return new AudioInputStream(
                new SequenceInputStream(clip1, clip2),
                clip1.getFormat(),
                clip1.getFrameLength() + clip2.getFrameLength()
        );
    }
}
