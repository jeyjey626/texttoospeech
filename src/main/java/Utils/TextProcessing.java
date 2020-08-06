package Utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

public class TextProcessing {
    // --------------------------------------------------------------
    // ------------- Reading and processing txt Files ---------------
    // --------------------------------------------------------------
    private static String txtString;

    public static ArrayList<String> txtFileProcessor(File file) {
        ArrayList<String> records;
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
        records = sliceText(polishSymbolsProcessing(txtString));
        return records;
    }


    // --------------------------------------------------------------
    // ------------- Reading and processing pdf Files ---------------
    // --------------------------------------------------------------
    public static PDDocument document;
    private static String pdfText;
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
