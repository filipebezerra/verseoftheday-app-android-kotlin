package com.github.filipebezerra.bible.verseoftheday;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import java.io.File;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.unbescape.html.HtmlEscape;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.github.filipebezerra.bible.verseoftheday.BibleGatewayAvailableVersions.getVersionByLanguage;

/**
 * .
 *
 * @author Filipe Bezerra
 * @version #, 02/11/2015
 * @since #
 */
public class HomeActivity extends AppCompatActivity {
    public static final long HTTP_CACHE_SIZE = 10 * 1024 * 1024;
    public static final String HTTP_CACHE_FILE_NAME = "http";

    @Bind(R.id.reference)TextView mReferenceTextView;
    @Bind(R.id.verse)TextView mVerseTextView;

    private BibleGatewayService mBibleGatewayService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            String text = mVerseTextView.getText().toString() + "\n" +
                    mReferenceTextView.getText().toString();
            startActivity(IntentUtil.createShareIntent(HomeActivity.this, text));
        });

        setUpRetrofit();
    }

    private void setUpRetrofit() {
        final Cache cache = new Cache(new File(getCacheDir(), HTTP_CACHE_FILE_NAME),
                HTTP_CACHE_SIZE);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(30, TimeUnit.SECONDS);
        okHttpClient.setCache(cache);
        okHttpClient.interceptors().add(interceptor);

        final Retrofit retrofit = new Retrofit.Builder().
                baseUrl("https://www.biblegateway.com/").
                addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJavaCallAdapterFactory.create()).
                client(okHttpClient).
                build();

        mBibleGatewayService = retrofit.create(BibleGatewayService.class);
    }

    @Override
    protected void onResume() {
        super.onResume();

        String language;
        if (Locale.getDefault().getLanguage().equals("pt")) {
            language = "pt";
        } else {
            language = "en";
        }

        final Votd verse = PreferencesUtil.getVerse(this);

        if (verse != null) {
            if (PreferencesUtil.isVerseFromSameDay(this, verse)) {
                bindVerseToUi(verse);
                return;
            }
        }

        mBibleGatewayService.getVerseOfTheDay("json", getVersionByLanguage(language))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new VerseOfTheDaySubscriber());
    }

    private class VerseOfTheDaySubscriber extends Subscriber<VotdResponse> {
        private Votd mVerse;

        @Override
        public void onCompleted() {
            bindVerseToUi(mVerse);
            PreferencesUtil.putVerse(HomeActivity.this, mVerse);
        }

        @Override
        public void onError(Throwable e) {
            Log.e("VerseOfTheDay", "Getting votd", e);
            bindErrorToUi(getString(R.string.error_state));
        }

        @Override
        public void onNext(VotdResponse votdResponse) {
            mVerse = votdResponse.votd;
        }
    }

    public void bindVerseToUi(Votd verse) {
        mReferenceTextView.setText(verse.displayRef);
        mVerseTextView.setText(HtmlEscape.unescapeHtml(verse.text));
    }

    public void bindErrorToUi(String error) {
        mVerseTextView.setText(error);
    }
}