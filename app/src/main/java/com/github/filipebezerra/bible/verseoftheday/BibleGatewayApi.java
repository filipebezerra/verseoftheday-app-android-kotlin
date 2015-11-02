package com.github.filipebezerra.bible.verseoftheday;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * .
 *
 * @author Filipe Bezerra
 * @version #, 02/11/2015
 * @since #
 */
public interface BibleGatewayApi {
    @GET("/votd/get")
    Call<VotdResponse> getVerseOfTheDay(
            @Query("format") String format, @Query("version") String version);
}
