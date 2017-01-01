package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dimitri on 11/12/15.
 */
public class DateValidator {

    public static boolean isThisDateValid(String dateToValidate, String dateFromat) {

        if (dateToValidate == null) {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
        sdf.setLenient(false);

        try {
            Date date = sdf.parse(dateToValidate);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }
}
