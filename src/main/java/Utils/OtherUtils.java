package Utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashSet;

public class OtherUtils {

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
    public static boolean isVowel(String s) {
        return vowelSet.contains(s);
    }
}
