package cl.entel.appswlsdesa.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import cl.entel.appswlsdesa.utils.BroadcastManager;
import cl.entel.appswlsdesa.utils.NetworkUtil;

public class NetworkChangeReceiver extends BroadcastReceiver {

    public static String IS_CONNECTED = "IS_CONNECTED";
    public static String CONNECTION_CHANGED = "CONNECTION_CHANGED";

	@Override
	public void onReceive(final Context context, final Intent intent) {
		boolean isConnected = NetworkUtil.getConnectivityStatusString(context);
        Bundle data = new Bundle();
        data.putBoolean(IS_CONNECTED, isConnected);
        BroadcastManager.sendBroadcast(context, CONNECTION_CHANGED, data);
	}
}
