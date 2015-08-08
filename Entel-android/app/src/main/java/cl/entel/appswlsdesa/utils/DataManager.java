package cl.entel.appswlsdesa.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import com.google.gson.Gson;
import com.loopj.android.http.PersistentCookieStore;

import org.apache.http.impl.client.DefaultHttpClient;

import java.util.Date;

import cl.entel.appswlsdesa.models.User;

/**
 * Created by alex on 7/31/14.
 */
public class DataManager {
    private static DataManager ourInstance = new DataManager();
    private String mCipherKey;
    private boolean surveyAlertShowed = false;
    public static User user;
    private Handler mHandler;

    public static DataManager getInstance() {
        return ourInstance;
    }

    private DataManager() {
    }

    public void logout(Context ctx) {
        removeSharedPrefence(ctx, Constants.USER_KEY);
        removeSharedPrefence(ctx, Constants.NUM_EXECUTIONS_PREF);
        removeSharedPrefence(ctx, Constants.RULE_1_PREF);
        removeSharedPrefence(ctx, Constants.SURVEY_LAST_UPDATE_PREF);
        removeCookies(ctx);
    }

    private void removeCookies(Context ctx) {
        CookieSyncManager.createInstance(ctx);
        DefaultHttpClient httpclient = new DefaultHttpClient();
        httpclient.getCookieStore().clear();

        PersistentCookieStore cookieStore = new PersistentCookieStore(ctx);
        cookieStore.clear();

        CookieManager cm = CookieManager.getInstance();
        cm.removeAllCookie();
        CookieSyncManager.getInstance().sync();
    }

    public User getUser() {
        return this.user;
    }

    public User unarchieveUser(Context ctx) {
        SharedPreferences  prefs = ctx.getSharedPreferences(Constants.ENTEL_PREFS, ctx.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(Constants.USER_KEY, null);
        this.user = gson.fromJson(json, User.class);
        return this.user;
    }

    public void archieveUser(Context ctx, User user) {
        this.user = user;
        SharedPreferences  prefs = ctx.getSharedPreferences(Constants.ENTEL_PREFS, ctx.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        prefsEditor.putString(Constants.USER_KEY, json);
        prefsEditor.apply();
    }

    public void removeSharedPrefence(Context ctx, String key) {
        SharedPreferences  prefs = ctx.getSharedPreferences(Constants.ENTEL_PREFS, ctx.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.remove(key);
        prefsEditor.apply();
    }

    public String getCipherKey() {
        return mCipherKey;
    }

    public void setCipherKey(String mCipherKey) {
        this.mCipherKey = mCipherKey;
    }

    public boolean isSurveyAlertShowed() {
        return surveyAlertShowed;
    }

    public void setSurveyAlertShowed(boolean surveyAlertShowed) {
        this.surveyAlertShowed = surveyAlertShowed;
    }

    public int getNumExecutions(Context ctx) {
        SharedPreferences  prefs = ctx.getSharedPreferences(Constants.ENTEL_PREFS, ctx.MODE_PRIVATE);
        return prefs.getInt(Constants.NUM_EXECUTIONS_PREF, 0);
    }

    public void incrementNumExecutions(Context ctx) {
        SharedPreferences  prefs = ctx.getSharedPreferences(Constants.ENTEL_PREFS, ctx.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        int numExecutions = getNumExecutions(ctx);

        if (numExecutions <= Constants.RULE_1) {
            prefsEditor.putInt(Constants.NUM_EXECUTIONS_PREF, numExecutions + 1);
            prefsEditor.apply();
        }
    }

    public void surveyTimer(final Context ctx) {
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            public void run() {
                finishSurveyTimer(ctx);
            }
        }, Constants.SURVEY_TIMER_SECONDS);
    }

    private void finishSurveyTimer(Context ctx) {
        mHandler = null;
        SharedPreferences  prefs = ctx.getSharedPreferences(Constants.ENTEL_PREFS, ctx.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putBoolean(Constants.RULE_1_PREF, true);
        prefsEditor.apply();
        BroadcastManager.sendBroadcast(ctx, Constants.SURVEY_ALERTBOX_NOTIF, null);
    }

    public Handler getSurveyTimer() {
        return mHandler;
    }

    public void setSurveyLastupdate(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences(Constants.ENTEL_PREFS, ctx.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        long currentDateMilis = (new Date()).getTime();
        prefsEditor.putLong(Constants.SURVEY_LAST_UPDATE_PREF, currentDateMilis);
        prefsEditor.apply();
    }

    public boolean surveyShouldLoad(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences(Constants.ENTEL_PREFS, ctx.MODE_PRIVATE);
        long lastUpdateMilis = prefs.getLong(Constants.SURVEY_LAST_UPDATE_PREF, -1);
        int days = 0;

        if (lastUpdateMilis != -1) {
            long diff = (new Date()).getTime() - lastUpdateMilis;
            days = (int) (diff / (24 * 60 * 60 * 1000));
        }

        return days >= Constants.SURVEY_DAYS;
    }

    public void setIsFirstLaunch(Context ctx, boolean isFirstLaunch) {
        SharedPreferences prefs = ctx.getSharedPreferences(Constants.ENTEL_PREFS, ctx.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putBoolean(Constants.IS_FIRST_LAUNCH, isFirstLaunch);
        prefsEditor.apply();
    }

    public void setLastVersionCode(Context ctx) {
        int versionCode = getVersionCode(ctx);
        SharedPreferences prefs = ctx.getSharedPreferences(Constants.ENTEL_PREFS, ctx.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putInt(Constants.LAST_VERSION_CODE, versionCode);
        prefsEditor.apply();
    }

    public int getVersionCode(Context ctx) {
        try {
            PackageInfo pInfo = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
            return pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
