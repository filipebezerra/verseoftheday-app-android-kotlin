package com.github.filipebezerra.bible.verseoftheday.api;

import com.github.filipebezerra.bible.verseoftheday.votd.VotdResponse;
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
    String API_BASE_URL = "https://www.biblegateway.com/";

    @GET("votd/get")
    Observable<VotdResponse> getVerseOfTheDay(
            @Query("format") String format, @Query("version") String version);
}
