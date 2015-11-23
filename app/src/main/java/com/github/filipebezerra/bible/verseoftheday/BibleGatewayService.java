package com.github.filipebezerra.bible.verseoftheday;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * .
 *
 * @author Filipe Bezerra
 * @version #, 02/11/2015
 * @since #
 */
public interface BibleGatewayService {
    @GET("votd/get")
    Observable<VotdResponse> getVerseOfTheDay(
            @Query("format") String format, @Query("version") String version);
}
