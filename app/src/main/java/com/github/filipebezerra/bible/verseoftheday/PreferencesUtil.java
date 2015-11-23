package com.github.filipebezerra.bible.verseoftheday;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.content.SharedPreferencesCompat;
import android.text.TextUtils;

/**
 * .
 *
 * @author Filipe Bezerra
 * @version #, 23/11/2015
 * @since #
 */
public class PreferencesUtil {
    private static final String PREF_VERSE = "verse";
    private static final String PREF_REFERENCE = "reference";
    private static final String PREF_YEAR_OF_VERSE = "year";
    private static final String PREF_MONTH_OF_VERSE = "month";
    private static final String PREF_DAY_OF_VERSE = "day";
    private static final String PREF_LOCALE = "locale";

    public static void putVerse(@NonNull Context context, @NonNull Votd verse) {
        if (!TextUtils.isEmpty(verse.text) &&
                !TextUtils.isEmpty(verse.displayRef) &&
                !TextUtils.isEmpty(verse.year) &&
                !TextUtils.isEmpty(verse.month) &&
                !TextUtils.isEmpty(verse.day) &&
                !TextUtils.isEmpty(verse.locale)) {

            final SharedPreferences preferences
                    = PreferenceManager.getDefaultSharedPreferences(context);
            final SharedPreferences.Editor editor = preferences.edit();
            editor.putString(PREF_VERSE, verse.text);
            editor.putString(PREF_REFERENCE, verse.displayRef);
            editor.putString(PREF_YEAR_OF_VERSE, verse.year);
            editor.putString(PREF_MONTH_OF_VERSE, verse.month);
            editor.putString(PREF_DAY_OF_VERSE, verse.day);
            editor.putString(PREF_LOCALE, verse.locale);

            SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);
        }
    }

    public static boolean isVerseFromSameDay(@NonNull Context context, @NonNull Votd verse) {
        return hasVerse(context) && verse.equals(getVerse(context));
    }

    public static Votd getVerse(@NonNull Context context) {
        final SharedPreferences preferences
                = PreferenceManager.getDefaultSharedPreferences(context);

        if (hasVerse(context)) {
            final Votd verse = new Votd();
            verse.text = preferences.getString(PREF_VERSE, "");
            verse.displayRef = preferences.getString(PREF_REFERENCE, "");
            verse.year = preferences.getString(PREF_YEAR_OF_VERSE, "");
            verse.month = preferences.getString(PREF_MONTH_OF_VERSE, "");
            verse.day = preferences.getString(PREF_DAY_OF_VERSE, "");
            verse.locale = preferences.getString(PREF_LOCALE, "");

            return verse;
        }
        else
            return null;
    }

    private static boolean hasVerse(@NonNull Context context) {
        final SharedPreferences preferences
                = PreferenceManager.getDefaultSharedPreferences(context);

        return preferences.contains(PREF_VERSE) &&
                preferences.contains(PREF_REFERENCE) &&
                preferences.contains(PREF_DAY_OF_VERSE) &&
                preferences.contains(PREF_MONTH_OF_VERSE) &&
                preferences.contains(PREF_YEAR_OF_VERSE) &&
                preferences.contains(PREF_LOCALE);
    }
}
