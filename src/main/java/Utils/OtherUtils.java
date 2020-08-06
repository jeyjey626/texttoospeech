package Utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashSet;

public class OtherUtils {

    // --------------------------------------------------------------
    // ------------- About App String ------- -----------------------
    // --------------------------------------------------------------
    public static final String aboutAppTxt = "Aplikacja wykonana przez Julię Makulec w ramach pracy inżynierskiej, 2020.\n\n" +
            "Program syntezuje mowę z tekstu wprowadzonego w aplikacji bądź z plików txt i pdf\n metodą konkatenatywną" +
            "z użyciem próbek głosu autorki. \n\nDodatkową opcją jest uzyskanie informacji w jakim stopniu stworzona przez autorkę\n" +
            "bądź użytkownika biblioteka dźwięków pokrywa wprowadzony tekst.";

    // --------------------------------------------------------------
    // ------------- Double approx to 2 decimal points --------------
    // --------------------------------------------------------------
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    // --------------------------------------------------------------
    // ------------- Vowel recognizer ------- -----------------------
    // --------------------------------------------------------------
    private static final HashSet<String> vowelSet = new HashSet<>(Arrays.asList("a", "A", "e", "E", "i", "o", "O", "u", "y"));
    public static boolean isConsonant(String s) {
        return !vowelSet.contains(s);
    }

    // --------------------------------------------------------------
    // ------------- polish symbols recognizer-----------------------
    // --------------------------------------------------------------
    private static final HashSet<String> polishSet = new HashSet<>(Arrays.asList("A", "C", "E","L","N","O","S","X","Z"));
    public static boolean isPolishSymbol(String s) { return polishSet.contains(s); }
}
