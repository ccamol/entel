package cl.entel.appswlsdesa.utils;

import android.content.Context;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;

import org.apache.http.Header;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.util.Date;

import cl.entel.appswlsdesa.R;
import cl.entel.appswlsdesa.models.User;

/**
 * Created by alex on 8/4/14.
 */
public class APIEngine {
    private static APIEngine ourInstance = new APIEngine();

    public static APIEngine getInstance() {
        if(ourInstance == null) {
            ourInstance = new APIEngine();
        }

        return ourInstance;
    }

    private AsyncHttpClient getClient(Context ctx) {
        AsyncHttpClient client = new AsyncHttpClient();
        PersistentCookieStore cookieStore = new PersistentCookieStore(ctx);
        client.setCookieStore(cookieStore);
        //client.setBasicAuth(Constants.CREDENTIAL_USER, Constants.CREDENTIAL_PSW);
        return client;
    }

    private APIEngine() {
    }

    public void getToken(final Context ctx, final String key, final TokenCallback callback) {
        AsyncHttpClient client = getClient(ctx);
        StringEntity entity = null;
        try {
            entity = new StringEntity("{\"key\":\""+key+"\"}");
            entity.setContentType("application/json; charset=utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        client.post(ctx, Constants.getUrl("signin.action"), entity, "application/json; charset=utf-8", new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                    int status = response.getInt("estado");

                    if (status == Constants.STATUS_SUCCESS) {
                        callback.onSuccess(response.getString("token"));
                    } else if (status == Constants.STATUS_REGISTER) {
                        createUserCookies(ctx);
                        callback.onRegisterView();
                    } else if (status == Constants.STATUS_UNKNOWN_ERROR) {
                        callback.onError(ctx.getString(R.string.unknown_error));
                    } else {
                        callback.onError(ctx.getString(R.string.login_error));
                    }
                } catch (JSONException e) {
                    callback.onError(ctx.getString(R.string.unknown_error));
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

                if (throwable instanceof SocketTimeoutException) {
                    callback.onError(ctx.getString(R.string.connection_error));
                } else {
                    callback.onError(ctx.getString(R.string.unknown_error));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);

                if (throwable instanceof SocketTimeoutException) {
                    callback.onError(ctx.getString(R.string.connection_error));
                } else {
                    callback.onError(ctx.getString(R.string.unknown_error));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);

                if (throwable instanceof SocketTimeoutException) {
                    callback.onError(ctx.getString(R.string.connection_error));
                } else {
                    callback.onError(ctx.getString(R.string.unknown_error));
                }
            }
        });
    }

    public void login(final Context ctx, final String token, final LoginCallback callback) {
        AsyncHttpClient client = getClient(ctx);
        StringEntity entity = null;
        try {
            entity = new StringEntity("{\"login\":\""+token+"\"}");
            entity.setContentType("application/json; charset=utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        client.post(ctx, Constants.getUrl("signin.action"), entity, "application/json; charset=utf-8", new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                DataManager.getInstance().incrementNumExecutions(ctx);
                Gson gson = new Gson();
                User user = gson.fromJson(response.toString(), User.class);
                int status = 1;

                try {
                    status = response.getInt("estado");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                
                if (status == Constants.STATUS_SUCCESS) {
                    createUserCookies(ctx);
                    callback.onSuccess(user);
                } else if (status == Constants.STATUS_UNKNOWN_ERROR) {
                    callback.onError(ctx.getString(R.string.unknown_error));
                } else {
                    callback.onError(ctx.getString(R.string.login_error));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                if (throwable instanceof SocketTimeoutException) {
                    callback.onError(ctx.getString(R.string.connection_error));
                } else {
                    callback.onError(ctx.getString(R.string.unknown_error));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                if (throwable instanceof SocketTimeoutException) {
                    callback.onError(ctx.getString(R.string.connection_error));
                } else {
                    callback.onError(ctx.getString(R.string.unknown_error));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                if (throwable instanceof SocketTimeoutException) {
                    callback.onError(ctx.getString(R.string.connection_error));
                } else {
                    callback.onError(ctx.getString(R.string.unknown_error));
                }
            }
        });
    }

    private void createUserCookies(Context ctx) {
        setCookies(ctx, Constants.SESSION_COOKIE);
        setCookies(ctx, Constants.USER_COOKIE);
    }

    private void setCookies(Context ctx, String cookieName) {
        PersistentCookieStore cookieStore = new PersistentCookieStore(ctx);
        DefaultHttpClient httpclient = new DefaultHttpClient();
        CookieSyncManager.createInstance(ctx);

        Cookie userCookie = getCookieByName(ctx, cookieName);

        if (userCookie == null) return;

        for (Cookie storedCookie : cookieStore.getCookies()) {
            BasicClientCookie bcc = new BasicClientCookie(storedCookie.getName(), storedCookie.getValue());
            bcc.setPath(storedCookie.getPath());
            Date d = storedCookie.getExpiryDate();
            bcc.setExpiryDate(d != null ? d : userCookie.getExpiryDate());
            bcc.setDomain(storedCookie.getDomain());
            httpclient.getCookieStore().addCookie(bcc);

            CookieManager cm = CookieManager.getInstance();
            cm.setAcceptCookie(true);

            String cookie = storedCookie.getName() + "=" + storedCookie.getValue() +
                    ";expires=" + (d != null ? d : userCookie.getExpiryDate());

            cm.setCookie(storedCookie.getDomain(), cookie);
            CookieSyncManager.getInstance().sync();
        }
    }

    private Cookie getCookieByName(Context ctx, String name) {
        PersistentCookieStore cookieStore = new PersistentCookieStore(ctx);
        for (Cookie storedCookie : cookieStore.getCookies()) {
            if (storedCookie.getName().equalsIgnoreCase(name)) {
                return storedCookie;
            }
        }

        return null;
    }

    public void logout(final Context ctx, final LogoutCallback callback) {
        AsyncHttpClient client = getClient(ctx);
        client.post(Constants.getUrl("signout.action"), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                callback.onSuccess();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                callback.onError(ctx.getString(R.string.logout_error));
            }
        });
    }

    public interface LoginCallback {
        public void onSuccess(User user);
        public void onError(String msg);
    }

    public interface LogoutCallback {
        public void onSuccess();
        public void onError(String msg);
    }

    public interface TokenCallback {
        public void onSuccess(String token);
        public void onError(String msg);
        public void onRegisterView();
    }
}
