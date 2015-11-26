package com.github.filipebezerra.bible.verseoftheday.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.content.SharedPreferencesCompat;
import android.text.TextUtils;
import com.github.filipebezerra.bible.verseoftheday.votd.Votd;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

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
    private static final String PREF_LANGUAGE = "language";

    private PreferencesUtil() {
        // no instances
    }

    public static void putVerse(@NonNull Context context, @NonNull Votd verse) {
        if (!TextUtils.isEmpty(verse.text) &&
                !TextUtils.isEmpty(verse.displayRef) &&
                !TextUtils.isEmpty(verse.year) &&
                !TextUtils.isEmpty(verse.month) &&
                !TextUtils.isEmpty(verse.day)) {

            // getting the current language configuration
            final String language = ResourcesUtil.getCurrentLanguage(context);

            final SharedPreferences preferences = getDefaultSharedPreferences(context);
            final SharedPreferences.Editor editor = preferences.edit();

            editor.putString(PREF_VERSE, verse.text);
            editor.putString(PREF_REFERENCE, verse.displayRef);
            editor.putString(PREF_YEAR_OF_VERSE, verse.year);
            editor.putString(PREF_MONTH_OF_VERSE, verse.month);
            editor.putString(PREF_DAY_OF_VERSE, verse.day);
            editor.putString(PREF_LANGUAGE, language);

            SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);
        }
    }

    public static Votd getVerse(@NonNull Context context) {
        final SharedPreferences preferences = getDefaultSharedPreferences(context);

        if (hasVerse(preferences)) {
            final Votd verse = new Votd();
            verse.text = preferences.getString(PREF_VERSE, "");
            verse.displayRef = preferences.getString(PREF_REFERENCE, "");
            verse.year = preferences.getString(PREF_YEAR_OF_VERSE, "");
            verse.month = preferences.getString(PREF_MONTH_OF_VERSE, "");
            verse.day = preferences.getString(PREF_DAY_OF_VERSE, "");

            try {
                if (!DateUtil.isToday(verse.year, verse.month, verse.day) ||
                        !isSameLanguage(preferences, context)) {
                    return null;
                }
            } catch (IllegalArgumentException ignored) {}

            return verse;
        }
        else
            return null;
    }

    private static boolean hasVerse(@NonNull SharedPreferences preferences) {
        return preferences.contains(PREF_VERSE) &&
                preferences.contains(PREF_REFERENCE) &&
                preferences.contains(PREF_DAY_OF_VERSE) &&
                preferences.contains(PREF_MONTH_OF_VERSE) &&
                preferences.contains(PREF_YEAR_OF_VERSE) &&
                preferences.contains(PREF_LANGUAGE);
    }

    private static boolean isSameLanguage(@NonNull SharedPreferences preferences,
            @NonNull Context context) {
        // getting the current language configuration
        final String language = ResourcesUtil.getCurrentLanguage(context);

        return preferences.contains(PREF_LANGUAGE) &&
                preferences.getString(PREF_LANGUAGE, "").equals(language);
    }
}
