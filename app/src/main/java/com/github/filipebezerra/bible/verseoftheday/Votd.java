package com.github.filipebezerra.bible.verseoftheday;

import com.google.gson.annotations.SerializedName;

/**
 * .
 *
 * @author Filipe Bezerra
 * @version #, 02/11/2015
 * @since #
 */
public class Votd {
    public String text;
    public String content;
    @SerializedName("display_ref")
    public String displayRef;
    public String audiolink;
    public String day;
    public String month;
    public String year;

    @Override
    public boolean equals(Object o) {
        if (o instanceof  Votd) {
            Votd another = (Votd) o;

            return this.year.equals(another.year) &&
                    this.month.equals(another.month) &&
                    this.day.equals(another.day);
        }
        return false;
    }
}
