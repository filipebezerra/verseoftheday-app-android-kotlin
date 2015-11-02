package com.github.filipebezerra.bible.verseoftheday;

import com.google.gson.annotations.SerializedName;

/**
 * .
 *
 * @author Fbs
 * @version #, 02/11/2015
 * @since #
 */
public class Votd {
    public String content;
    @SerializedName("display_ref")
    public String displayRef;
    public String audiolink;
    public String day;
    public String month;
    public String year;
}
