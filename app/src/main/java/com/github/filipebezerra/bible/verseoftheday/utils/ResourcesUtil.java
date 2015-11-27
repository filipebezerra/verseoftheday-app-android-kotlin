package com.github.filipebezerra.bible.verseoftheday.utils;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * .
 *
 * @author Filipe Bezerra
 * @version #, 26/11/2015
 * @since #
 */
public class ResourcesUtil {
    private ResourcesUtil() {
        // no instances
    }

    public static String getCurrentLanguage(@NonNull Context context) {
        return context.getResources().getConfiguration().locale.getLanguage();
    }
}
