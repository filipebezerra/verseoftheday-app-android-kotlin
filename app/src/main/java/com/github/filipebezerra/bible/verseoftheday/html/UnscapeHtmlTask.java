package com.github.filipebezerra.bible.verseoftheday.html;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import java.util.ArrayList;
import java.util.List;
import org.unbescape.html.HtmlEscape;

/**
 * .
 *
 * @author Filipe Bezerra
 * @version #, 26/11/2015
 * @since #
 */
public class UnscapeHtmlTask extends AsyncTask<Pair<Integer, String>, Void, List<Pair<Integer, String>>> {
    @NonNull private final HtmlUnescapedListener mListener;

    public UnscapeHtmlTask(@NonNull HtmlUnescapedListener listener) {
        mListener = listener;
    }

    @SafeVarargs
    @Override
    protected final List<Pair<Integer, String>> doInBackground(Pair<Integer, String>... params) {
        if (params == null || params.length == 0) {
            return null;
        }

        List<Pair<Integer, String>> htmls = new ArrayList<>();

        for(Pair<Integer, String> html : params) {
            htmls.add(Pair.create(html.first, HtmlEscape.unescapeHtml(html.second)));
        }

        return htmls;
    }

    @Override
    protected void onPostExecute(List<Pair<Integer, String>> htmls) {
        if (htmls != null && !htmls.isEmpty()) {
            mListener.onHtmlUnescaped(htmls);
        }
    }

    public interface HtmlUnescapedListener {
        void onHtmlUnescaped(List<Pair<Integer, String>> htmls);
    }
}
