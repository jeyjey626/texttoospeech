package com.jmakulec.texttoospeech;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class AppGUI {
    public AudioFilePlayer playingThread;
    private JPanel rootPanel;
    private JPanel entryPanel;
    private JPanel OptionsPanel;
    private JRadioButton fileSelect;
    private JRadioButton textEntry;
    private JCheckBox analysis;
    private JPanel entryValuePanel;
    private JPanel fileInputPanel;
    private JPanel textInputPanel;
    private JTextArea inputTextArea;
    private JTextField fileInputField;
    private JButton fileChooserButton;
    private JPanel navigationPanel;
    private JButton playButton;
    private JButton stopButton;
    private JButton cleanButton;
    private JMenu optionsMenu;
    private JMenuItem libraryButton;

    public AppGUI() {
        playingThread = new AudioFilePlayer();
        SoundLibraryContent.inputLibrary("E:\\Dokumenty\\PracaInz\\soundfiles\\");
        JFrame frame = new JFrame("Syntezer mowy");
        frame.setContentPane(rootPanel);
        frame.pack();
        frame.setMinimumSize(new Dimension(450, 600));
        frame.setVisible(true);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inputTextArea.getText().length() != 0) {
                    ArrayList<String> slicedSentence = SentenceSlicer.sliceText(inputTextArea.getText());
                    ArrayList<AudioInputStream> wordFileList = new ArrayList<>();
                    for (String s:slicedSentence){
                        try {
                            wordFileList.add(AudioAppender.appendFiles(WordSlicer.sliceText(s)));
                            //AudioFilePlayer.playFile(AudioAppender.appendFiles(WordSlicer.sliceText(s)));
                        } catch (IOException | UnsupportedAudioFileException exception) {
                            exception.printStackTrace();
                        }
                    }
                    try {
                        AudioFilePlayer.setPlayableInput( AudioAppender.appendWords(wordFileList) );
                        if (playingThread.isAlive()) playingThread.cancelPlaying();
                        playingThread = new AudioFilePlayer();
                        playingThread.start();
                    } catch (IOException | UnsupportedAudioFileException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });
        cleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputTextArea.setText("");
            }
        });
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if (playingThread.isAlive()) playingThread.cancelPlaying();
            }
        });
        libraryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        fileChooserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(); // creating a file chooser
                fileChooser.setDialogTitle("Wybierz plik do odtworzenia");
                FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Pliki tekstowe i PDF", "txt", "text", "pdf"); // creating filter allowing only pdf and txt
                fileChooser.setFileFilter(extensionFilter);


                int state = fileChooser.showOpenDialog(frame);

                if (state == JFileChooser.APPROVE_OPTION) {
                    fileInputField.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });
    }

    public static void main(String[] args) {
        new AppGUI();
    }
}
