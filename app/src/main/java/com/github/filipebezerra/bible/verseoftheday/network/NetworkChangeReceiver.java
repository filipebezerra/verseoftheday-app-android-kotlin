package com.github.filipebezerra.bible.verseoftheday.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import com.github.filipebezerra.bible.verseoftheday.utils.NetworkUtil;

/**
 * .
 *
 * @author Filipe Bezerra
 * @version #, 26/11/2015
 * @since #
 */
public class NetworkChangeReceiver extends BroadcastReceiver {
    @NonNull private final NetworkConnectionRestoredListener mListener;

    public NetworkChangeReceiver(@NonNull NetworkConnectionRestoredListener listener) {
        mListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (NetworkUtil.isDeviceConnectedToInternet(context)) {
            mListener.onNetworkRestored();
        }
    }

    public interface NetworkConnectionRestoredListener {
        void onNetworkRestored();
    }
}
