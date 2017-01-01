package utils;

/**
 * Created by jviladoms on 13/10/16.
 */
public class NumberValidator {
    public static boolean isNumber(String string) {
        try {
            Long.parseLong(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
