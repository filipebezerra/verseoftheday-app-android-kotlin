package com.github.filipebezerra.bible.verseoftheday.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import com.github.filipebezerra.bible.verseoftheday.R;

/**
 * .
 *
 * @author Filipe Bezerra
 * @version #, 02/11/2015
 * @since #
 */
public class IntentUtil {
    @SuppressWarnings("deprecation")
    public static Intent createShareIntent(@NonNull Context context, @NonNull String text) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
        } else {
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        }
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);

        if (!resolveActivity(shareIntent, context)) {
            return null;
        }

        return Intent.createChooser(shareIntent, context.getString(R.string.share_dialog_title));
    }

    public static boolean resolveActivity(@NonNull Intent intent, @NonNull Context context) {
        return intent.resolveActivity(context.getPackageManager()) != null;
    }
}
