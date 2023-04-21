package com.example.exampletz.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    public static String formatDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.UK);
        try {
            Date date = format.parse(dateString);
            format = new SimpleDateFormat("MMMM dd, yyyy", Locale.UK);
            return format.format(date);
        } catch (Exception e) {
            return "";
        }
    }
}
