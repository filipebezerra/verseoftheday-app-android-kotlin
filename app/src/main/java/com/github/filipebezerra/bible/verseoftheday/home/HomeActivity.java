package com.github.filipebezerra.bible.verseoftheday.home;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.github.filipebezerra.bible.verseoftheday.R;
import com.github.filipebezerra.bible.verseoftheday.api.BibleGatewayApiController;
import com.github.filipebezerra.bible.verseoftheday.html.UnscapeHtmlTask;
import com.github.filipebezerra.bible.verseoftheday.html.UnscapeHtmlTask.HtmlUnescapedListener;
import com.github.filipebezerra.bible.verseoftheday.utils.IntentUtil;
import com.github.filipebezerra.bible.verseoftheday.utils.PreferencesUtil;
import com.github.filipebezerra.bible.verseoftheday.votd.Votd;
import com.github.filipebezerra.bible.verseoftheday.votd.VotdError;
import com.github.filipebezerra.bible.verseoftheday.votd.VotdResponse;
import java.util.List;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * .
 *
 * @author Filipe Bezerra
 * @version #, 02/11/2015
 * @since #
 */
public class HomeActivity extends AppCompatActivity implements HtmlUnescapedListener {
    @Bind(R.id.reference) TextView mReferenceTextView;
    @Bind(R.id.verse) TextView mVerseTextView;
    @Bind(R.id.version) TextView mVersionView;

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
    }

    @Override
    protected void onResume() {
        super.onResume();

        final Votd verse = PreferencesUtil.getVerse(this);

        if (verse != null) {
            bindVerseToUi(verse);
        } else {
            BibleGatewayApiController.instance(this)
                    .getVerseOfTheDay("json", getString(R.string.bible_version))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new VerseOfTheDaySubscriber());
        }
    }

    private class VerseOfTheDaySubscriber extends Subscriber<VotdResponse> {
        private VotdResponse mVotdResponse;

        @Override
        public void onCompleted() {
            if (mVotdResponse.error == null) {
                bindVerseToUi(mVotdResponse.votd);
                PreferencesUtil.putVerse(HomeActivity.this, mVotdResponse.votd);
            } else {
                onError(new VotdError(mVotdResponse.error));
            }
        }

        @Override
        public void onError(Throwable e) {
            Log.e("VerseOfTheDay", "Getting votd", e);
            bindErrorToUi(getString(R.string.error_state));
        }

        @Override
        public void onNext(VotdResponse votdResponse) {
            mVotdResponse = votdResponse;
        }
    }

    public void bindVerseToUi(Votd verse) {
        mReferenceTextView.setText(verse.displayRef);
        //noinspection unchecked
        new UnscapeHtmlTask(this).execute(
                Pair.create(R.id.verse, verse.text), Pair.create(R.id.version, verse.version));
    }

    @Override
    public void onHtmlUnescaped(List<Pair<Integer, String>> htmls) {
        for (Pair<Integer, String> html : htmls) {
            switch (html.first) {
                case R.id.verse:
                    mVerseTextView.setText(html.second);
                    break;
                case R.id.version:
                    mVersionView.setText(html.second);
                    break;
            }
        }
    }

    public void bindErrorToUi(String error) {
        mVerseTextView.setText(error);
    }
}