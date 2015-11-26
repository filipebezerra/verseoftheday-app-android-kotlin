package com.github.filipebezerra.bible.verseoftheday.utils;

import java.util.Calendar;

import static android.text.TextUtils.isDigitsOnly;

/**
 * .
 *
 * @author Filipe Bezerra
 * @version #, 26/11/2015
 * @since #
 */
public class DateUtil {
    private DateUtil() {
        // no instances
    }

    public static boolean isToday(String year, String month, String day) {
        if (!isDigitsOnly(year)) {
            throw new IllegalArgumentException("Invalid argument. Year must be a digit");
        } else if (!isDigitsOnly(month)) {
            throw new IllegalArgumentException("Invalid argument. Month must be a digit");
        } else if (!isDigitsOnly(day)) {
            throw new IllegalArgumentException("Invalid argument. Day must be a digit");
        }

        final int yearGiven = Integer.valueOf(year);
        final int monthGiven = Integer.valueOf(month);
        final int dayGiven = Integer.valueOf(day);

        final Calendar calendar = Calendar.getInstance();
        final int yearToday = calendar.get(Calendar.YEAR);
        final int monthToday = calendar.get(Calendar.MONTH) + 1;//JANUARY == 0
        final int dayToday = calendar.get(Calendar.DAY_OF_MONTH);

        return (yearGiven == yearToday) && (monthGiven == monthToday) && (dayGiven == dayToday);
    }
}
