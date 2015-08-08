package cl.entel.appswlsdesa.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

public class BroadcastManager {

    public static String DATA = "DATA";

    public static void register(Context ctx, BroadcastReceiver receiver, IntentFilter filter) {
        LocalBroadcastManager
                .getInstance(ctx)
                .registerReceiver(receiver, filter);
    }

    public static void unregister(Context ctx, BroadcastReceiver receiver) {
        LocalBroadcastManager
                .getInstance(ctx)
                .unregisterReceiver(receiver);
    }

    public static void sendBroadcast(Context ctx, String action, Bundle data) {
        Intent intent = new Intent();
        intent.setAction(action);
        intent.putExtra(DATA, data);

        LocalBroadcastManager
                .getInstance(ctx)
                .sendBroadcast(intent);
    }

}
