package com.github.filipebezerra.bible.verseoftheday;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import java.io.File;
import java.util.concurrent.TimeUnit;
import org.unbescape.html.HtmlEscape;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class HomeActivity extends AppCompatActivity implements Callback<VotdResponse> {
    public static final long HTTP_CACHE_SIZE = 10 * 1024 * 1024;
    public static final String HTTP_CACHE_FILE_NAME = "http";

    @Bind(R.id.reference)TextView mReferenceTextView;
    @Bind(R.id.verse)TextView mVerseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
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
                baseUrl("https://www.biblegateway.com").
                addConverterFactory(GsonConverterFactory.create()).
                client(okHttpClient).
                build();

        final BibleGatewayApi bibleGatewayApi = retrofit.create(BibleGatewayApi.class);

        bibleGatewayApi.getVerseOfTheDay("json", "NTLH").enqueue(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResponse(Response<VotdResponse> response, Retrofit retrofit) {
        mReferenceTextView.setText(response.body().votd.displayRef);
        mVerseTextView.setText(
                HtmlEscape.unescapeHtml(response.body().votd.content)
        );

    }

    @Override
    public void onFailure(Throwable t) {
        Log.e("VerseOfTheDay", "Getting votd", t);
    }
}