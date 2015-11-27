package com.github.filipebezerra.bible.verseoftheday.snackbar;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;
import com.github.filipebezerra.bible.verseoftheday.R;

import static android.support.v4.content.ContextCompat.getColor;

/**
 * .
 *
 * @author Filipe Bezerra
 * @version #, 26/11/2015
 * @since #
 */
public class SnackbarHelper {
    private SnackbarHelper() {
        //
    }

    public static void showShort(@NonNull View view, @NonNull CharSequence text) {
        final Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_SHORT);
        final View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(getColor(view.getContext(), R.color.colorPrimary));

        final TextView textView = (TextView) snackbarView.findViewById(
                android.support.design.R.id.snackbar_text);

        textView.setTextColor(getColor(view.getContext(),R.color.white));

        snackbar.show();
    }
}
