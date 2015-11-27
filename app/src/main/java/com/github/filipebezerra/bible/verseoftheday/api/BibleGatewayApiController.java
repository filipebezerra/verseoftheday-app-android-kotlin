package com.github.filipebezerra.bible.verseoftheday.api;

import android.content.Context;
import android.support.annotation.NonNull;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import java.io.File;
import java.util.concurrent.TimeUnit;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * .
 *
 * @author Filipe Bezerra
 * @version #, 26/11/2015
 * @since #
 */
public class BibleGatewayApiController {
    private static final long HTTP_CACHE_SIZE = 10 * 1024 * 1024;
    private static final String HTTP_CACHE_FILE_NAME = "http";
    private static BibleGatewayService sService;

    private BibleGatewayApiController() {
        // no instances
    }

    public static BibleGatewayService instance(@NonNull Context context) {
        if (sService == null) {
            final Cache cache = new Cache(new File(context.getCacheDir(), HTTP_CACHE_FILE_NAME),
                    HTTP_CACHE_SIZE);

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            final OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
            okHttpClient.setReadTimeout(30, TimeUnit.SECONDS);
            okHttpClient.setCache(cache);
            okHttpClient.interceptors().add(interceptor);

            Retrofit retrofit = new Retrofit.Builder().
                    baseUrl(BibleGatewayService.API_BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).
                    addCallAdapterFactory(RxJavaCallAdapterFactory.create()).
                    client(okHttpClient).
                    build();

            sService = retrofit.create(BibleGatewayService.class);
        }
        return sService;
    }
}
