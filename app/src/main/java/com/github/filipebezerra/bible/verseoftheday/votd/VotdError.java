package com.github.filipebezerra.bible.verseoftheday.votd;

import android.support.annotation.NonNull;

/**
 * .
 *
 * @author Filipe Bezerra
 * @version #, 26/11/2015
 * @since #
 */
public class VotdError extends Exception {
    private final String mErrorCode;

    public VotdError(@NonNull VotdResponse.Error error) {
        super(error.message);
        mErrorCode = error.code;
    }

    @Override
    public String toString() {
        return String.format("Code: %s\nMessage: %s", mErrorCode, getMessage());
    }
}
