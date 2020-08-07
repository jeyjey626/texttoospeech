package Utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TextProcessing {
    // --------------------------------------------------------------
    // ------------- Reading and processing txt Files ---------------
    // --------------------------------------------------------------
    public static String txtFileReader(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            StringBuilder stringBuilder = new StringBuilder();
            while (line != null) {
                stringBuilder.append(line).append("\n");
                line = reader.readLine();
            }
            txtString = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return txtString;
    }

    private static String txtString;

    public static ArrayList<String> txtFileProcessor(File file) {
        ArrayList<String> records;
        txtFileReader(file);
        records = sliceText(polishSymbolsProcessing(txtString));
        return records;
    }


    // --------------------------------------------------------------
    // ------------- Reading and processing pdf Files ---------------
    // --------------------------------------------------------------
    public static String processPDF(File file) {
        String ret = "";
        try {
            document = PDDocument.load(file);
            PDFTextStripper pdfStripper = new PDFTextStripper();
            ret = pdfStripper.getText(document);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }


    public static PDDocument document;
    public static String pdfText;
    public static ArrayList<String> pdfProcessor(File file){
        try {
            document = PDDocument.load(file);
            PDFTextStripper pdfStripper = new PDFTextStripper();
            pdfText = pdfStripper.getText(document);

        } catch (IOException e) {
            e.printStackTrace();
        }
        pdfText = Utils.TextProcessing.polishSymbolsProcessing(pdfText);
        String[] words = pdfText.split("\\W+");
        return (ArrayList<String>) Arrays.stream(words).collect(Collectors.toList());
    }

    // --------------------------------------------------------------
    // --------- Switching Polish sympol for cap letters ------------
    // --------------------------------------------------------------
    public static String polishSymbolsProcessing(String input){
        String text = input;
        text = text.toLowerCase();
        text = text.replaceAll("\u0105", "AA");
        text = text.replaceAll("\u0119", "EE");
        text = text.replaceAll("\u0107", "CC");
        text = text.replaceAll("\u0142", "LL");
        text = text.replaceAll("\u0144", "NN");
        text = text.replaceAll("\u00f3", "OO");
        text = text.replaceAll("\u015b", "SS");
        text = text.replaceAll("\u017a", "XX");
        text = text.replaceAll("\u017c", "ZZ");
        return text;
    }

    // --------------------------------------------------------------
    // ------------- Slicing String for words -----------------------
    // --------------------------------------------------------------
    public static ArrayList<String> sliceText(String input) {
        String[] words = polishSymbolsProcessing(input).split("\\W+");
        return (ArrayList<String>) Arrays.stream(words).collect(Collectors.toList());
    }
}
