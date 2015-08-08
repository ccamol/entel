package cl.entel.appswlsdesa.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cl.entel.appswlsdesa.R;
import cl.entel.appswlsdesa.receiver.NetworkChangeReceiver;
import cl.entel.appswlsdesa.utils.BroadcastManager;
import cl.entel.appswlsdesa.utils.Constants;
import cl.entel.appswlsdesa.utils.NetworkUtil;

public class BaseActivity extends ActionBarActivity {

    private IntentFilter mFilter;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(NetworkChangeReceiver.CONNECTION_CHANGED)) {
                Bundle data = getBundle(intent, BroadcastManager.DATA);
                onConnectivityChanged(data.getBoolean(NetworkChangeReceiver.IS_CONNECTED));
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int actionBarTitle = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
        TextView actionBarTitleView = (TextView) getWindow().findViewById(actionBarTitle);
        Typeface font = Typeface.createFromAsset(getAssets(), Constants.BOLD_FONT);

        if(actionBarTitleView != null){
            actionBarTitleView.setTypeface(font);
        }

        mFilter = new IntentFilter();
        mFilter.addAction(NetworkChangeReceiver.CONNECTION_CHANGED);
    }

    @Override
    protected void onResume() {
        super.onResume();
        onConnectivityChanged(NetworkUtil.getConnectivityStatusString(this));
        BroadcastManager.register(this, mReceiver, mFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BroadcastManager.unregister(this, mReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BroadcastManager.unregister(this, mReceiver);
    }

    private void onConnectivityChanged(boolean isConnected) {
        TextView txtNoConnection = (TextView) findViewById(R.id.txt_no_connection);

        if (txtNoConnection != null) {
            txtNoConnection.setVisibility(isConnected ? View.GONE : View.VISIBLE);
        }
    }

    public Bundle getBundle(Intent intent, String key) {
        Bundle bundle = null;

        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                bundle = extras.getBundle(key);
            }
        }

        return bundle;
    }
}
