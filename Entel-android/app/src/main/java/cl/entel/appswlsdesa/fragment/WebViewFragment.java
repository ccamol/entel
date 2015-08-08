package cl.entel.appswlsdesa.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieSyncManager;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshWebView;
import com.loopj.android.http.PersistentCookieStore;

import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import cl.entel.appswlsdesa.R;
import cl.entel.appswlsdesa.activity.MainActivity;
import cl.entel.appswlsdesa.utils.BroadcastManager;
import cl.entel.appswlsdesa.utils.Constants;
import cl.entel.appswlsdesa.utils.DataManager;
import cl.entel.appswlsdesa.utils.MenuManager;
import cl.entel.appswlsdesa.utils.NetworkUtil;
import cl.entel.appswlsdesa.utils.ToastManager;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link WebViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class WebViewFragment extends BaseFragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "section_number";
    public static final String IS_REGISTER = "is_register";
    public static final String IS_NOT_REGISTERED_USER = "is_user_not_registered";
    private static final long TIMEOUT = 25000;

    private View mRootView;
    private ImageButton mBtnReload;
    private LinearLayout mErrorView;
    private boolean mErrorWV = false;
    private boolean mIsPtr = false;
    private boolean mIsLink = false;
    private boolean mIsNoSession = false;
    private boolean mIsRegister = false;
    private boolean mIsSessionError = false;
    private int mSessionErrorAttempts = 0;
    protected boolean mIsNotRegisteredUser = false;
    private WebView mEntelWV;
    private ProgressBar mLoadingView;

    private Timer mTimer;
    private TimerTask mTimerTask;

    private IntentFilter mFilter;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Constants.SURVEY_ALERTBOX_NOTIF)) {
                showSurveyAlert();
            }
        }
    };

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static WebViewFragment newInstance(int sectionNumber, String url, String title, String previousUrl) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString(NEXT_URL, url);
        args.putString(TITLE, title);
        args.putString(PREVIOUS_URL, previousUrl);
        fragment.setArguments(args);
        return fragment;
    }

    public static WebViewFragment newInstance(int sectionNumber, String url, String title, boolean isRegister, boolean isNotRegisteredUser, String previousUrl) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString(NEXT_URL, url);
        args.putString(TITLE, title);
        args.putBoolean(IS_REGISTER, isRegister);
        args.putBoolean(IS_NOT_REGISTERED_USER, isNotRegisteredUser);
        args.putString(PREVIOUS_URL, previousUrl);
        fragment.setArguments(args);
        return fragment;
    }

    public WebViewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_main, container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIsRegister = getArguments().getBoolean(IS_REGISTER);
        mIsNotRegisteredUser = getArguments().getBoolean(IS_NOT_REGISTERED_USER);
        final String nextUrl = getArguments().getString(NEXT_URL);
        mBtnReload = (ImageButton) mRootView.findViewById(R.id.btn_reload);
        mErrorView = (LinearLayout) mRootView.findViewById(R.id.connection_error_view);
        mErrorView.setVisibility(View.GONE);

        final AnimationDrawable preloaderDrawable = (AnimationDrawable) getResources().getDrawable(R.drawable.preloader);
        mLoadingView = (ProgressBar) mRootView.findViewById(R.id.progressBar);
        mLoadingView.setIndeterminateDrawable(preloaderDrawable);

        final PullToRefreshWebView pullRefreshWebView = (PullToRefreshWebView) getActivity().findViewById(R.id.entelWebView);
        int position = getArguments().getInt(ARG_SECTION_NUMBER);
        pullRefreshWebView.setMode(position > 0 && MenuManager.getInstance().getCurrentMenuOption(position -1).isPullToRefresh() ?
                PullToRefreshBase.Mode.PULL_FROM_START :
                PullToRefreshBase.Mode.DISABLED);

        mEntelWV = pullRefreshWebView.getRefreshableView();

        if(!mEntelWV.getSettings().getUserAgentString().contains(Constants.USER_AGENT_SOURCE_APP_MOBILE)){
            mEntelWV.getSettings().setUserAgentString(mEntelWV.getSettings().getUserAgentString() + Constants.USER_AGENT_SOURCE_APP_MOBILE);
        }

        pullRefreshWebView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<WebView>() {
            @Override
            public void onRefresh(PullToRefreshBase<WebView> refreshView) {
                mIsPtr = true;
                if (NetworkUtil.getConnectivityStatusString(getActivity())) {
                    mErrorWV = false;
                    mIsLink = false;
                    refreshView.getRefreshableView().reload();
                } else {
                    refreshView.onRefreshComplete();
                    ToastManager.flash(getActivity(), R.string.no_internet_conection, Toast.LENGTH_SHORT);
                    mErrorView.setVisibility(View.VISIBLE);
                    mEntelWV.setVisibility(View.GONE);
                }
            }
        });

        mLoadingView.post(new Runnable() {
            @Override
            public void run() {
                preloaderDrawable.start();
            }
        });

        mBtnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (NetworkUtil.getConnectivityStatusString(getActivity())) {
                    mErrorWV = false;
                    mIsLink = false;
                    //mEntelWV.reload();
                    loadUrl(nextUrl);
                } else {
                    ToastManager.flash(getActivity(), R.string.no_internet_conection, Toast.LENGTH_SHORT);
                }
            }
        });

        mEntelWV.setWebViewClient(new WebViewClient() {
//            @Override
//            public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
//                handler.proceed(Constants.CREDENTIAL_USER, Constants.CREDENTIAL_PSW);
//            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.d("DATAWV", "onPageStarted: " + url);
                if (mIsNoSession) {
                    return;
                }

                if (!mIsLink) {
                    startTimer();

                    if (mIsRegister) {
                        getRegisterData();
                    }

                    view.setVisibility(mIsPtr ? View.VISIBLE : View.GONE);
                    mLoadingView.setVisibility(mIsPtr ? View.GONE : View.VISIBLE);
                    mErrorView.setVisibility(View.GONE);
                    mIsPtr = false;
                    if (!mIsNoSession) {
                        validateURL(url, view);
                    }
                }

                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("DATAWV", "shouldOverrideUrlLoading: " + url);

                if (mIsRegister) {
                    onLogin(true, mIsNotRegisteredUser);
                    view.stopLoading();
                    return super.shouldOverrideUrlLoading(view, url);
                }

                if (url.equals(Constants.CALL_HISTORY_BACK)) {
                    getActivity().finish();
                    view.stopLoading();
                    return true;
                }

                if (url.contains(Constants.BOLSAS_CONFIRMAR_URL)) {
                    openURLWithMenu(url);
                    view.stopLoading();
                    return true;
                }

                if (url.startsWith("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_DIAL,
                            Uri.parse(url));
                    startActivity(intent);
                    view.stopLoading();
                    return true;
                }

                if (url.startsWith(Constants.DOMAIN_SURVEY)) {
                    return super.shouldOverrideUrlLoading(view, url);
                }

                if (!mIsNoSession) {
                    mIsLink = true;

                    if (validateURL(url, view)) {
                        startNewActivity(url);
                    }

                    view.stopLoading();
                    return true;
                }

                view.stopLoading();
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                Log.d("DATAWV", "onPageFinished: " + url);

                String historyBackRewriteString = String.format("var elems=document.getElementsByClassName('btn btn-default');for(var i=0;i<elems.length;i++){var elem = elems[i];if(elem && elem.getAttribute('href').indexOf('history.back')>=0){elem.setAttribute('href','%1$s')};};", Constants.CALL_HISTORY_BACK);
                view.loadUrl("javascript:".concat(historyBackRewriteString));
                view.loadUrl("javascript:window.Android.processHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");

                stopTimer();
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                if (!NetworkUtil.getConnectivityStatusString(getActivity())) {
                    ToastManager.flash(getActivity(), R.string.no_internet_conection, Toast.LENGTH_SHORT);
                } else {
                    if (Constants.PROXY_ERROR_CODE == errorCode) {
                        ToastManager.flash(getActivity(), R.string.no_internet_conection, Toast.LENGTH_SHORT);
                    } else {
                        ToastManager.flash(getActivity(), description, Toast.LENGTH_SHORT);
                    }
                }

                mErrorWV = true;
                view.stopLoading();
                view.setVisibility(View.GONE);
                mErrorView.setVisibility(View.VISIBLE);
                mLoadingView.setVisibility(View.GONE);
                stopTimer();
            }

        });

        WebSettings settings = mEntelWV.getSettings();
        settings.setJavaScriptEnabled(true);
        mEntelWV.addJavascriptInterface(new JavaScriptInterface(getActivity()), "Android");
        settings.setGeolocationEnabled(true);

        mEntelWV.setWebChromeClient(new WebChromeClient() {
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    pullRefreshWebView.onRefreshComplete();
                }
            }
        });

        mEntelWV.requestFocus(View.FOCUS_DOWN);
        mEntelWV.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_UP:
                        if (!v.hasFocus()) {
                            v.requestFocus();
                        }
                        break;
                }
                return false;
            }
        });

        loadUrl(nextUrl);
    }

    private void loadUrl(String url) {
        Map<String, String> header = new HashMap<String, String>();
        header.put("If-None-Match","");

        if (url.equals(Constants.URL_SURVEY)) {
            header.put("Referer", getArguments().getString(PREVIOUS_URL));
        }

        mEntelWV.loadUrl(url, header);
    }

    private void onPageLoaded() {
        mLoadingView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.GONE);
        mEntelWV.setVisibility(View.VISIBLE);
    }

    private void getRegisterData() {
        if (mRegisterPhone == null || mRegisterPhone.isEmpty()) {
            mEntelWV.loadUrl("javascript:window.Android.registerPhone(" +
                    "document.getElementById('msisdn').value)");
        } else {
            mEntelWV.loadUrl("javascript:window.Android.registerData(" +
                    "document.getElementById('rut').value, " +
                    "document.getElementById('clave').value)");
        }
    }

    private void startTimer() {
        mTimer = new Timer();
        initializeTimerTask();
        mTimer.schedule(mTimerTask, TIMEOUT, TIMEOUT);
    }

    public void initializeTimerTask() {
        mTimerTask = new TimerTask() {
            public void run() {
                if (getActivity()!=null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (mEntelWV.getProgress() < 100) {
                                mErrorWV = true;
                                mEntelWV.stopLoading();
                                mErrorView.setVisibility(View.VISIBLE);
                                mLoadingView.setVisibility(View.GONE);
                                mEntelWV.setVisibility(View.GONE);
                                stopTimer();
                            }
                        }
                    });
                }
            }
        };
    }

    private void stopTimer() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    private boolean validateURL(String url, WebView view) {
        if (getActivity() == null) {
            return false;
        }

        if (url.contains("/"+ Constants.API_PATH +"/login")) {
            logCookies();
            mIsNoSession = true;
            onLogout();
            view.stopLoading();
            return false;
        } else {
            return true;
        }
    }

    private void openURLWithMenu(String url) {
        Intent i = new Intent(getActivity(), MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra(MainActivity.IS_LOAD_URL, true);
        i.putExtra(MainActivity.LOAD_URL, url);
        i.putExtra(MainActivity.LOAD_TITLE, getArguments().getString(TITLE));
        i.putExtra(MainActivity.LOAD_POSITION, getArguments().getInt(ARG_SECTION_NUMBER, 1));
        getActivity().startActivity(i);
        getActivity().finish();
    }

    private void logCookies() {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        CookieSyncManager.createInstance(getActivity());
        Log.d("COOKIES NUM", "" + httpclient.getCookieStore().getCookies());
        for (Cookie c : httpclient.getCookieStore().getCookies()) {
            Log.d("ENTEL COOKIE", "" + c);
        }

        PersistentCookieStore cookieStore = new PersistentCookieStore(getActivity());
        for (Cookie storedCookie : cookieStore.getCookies()) {
            Log.d("ENTEL COOKIE", "Name: " + storedCookie.getName() + " Value:" + storedCookie.getValue());
        }

        CookieSyncManager.getInstance().sync();
    }

    private void checkSurvey() {
        if (DataManager.getInstance().isSurveyAlertShowed()) {
            return;
        }

        if (DataManager.getInstance().surveyShouldLoad(getActivity())) {
            showSurveyAlert();
        } else if (DataManager.getInstance().getNumExecutions(getActivity()) >= Constants.RULE_1 &&
                !getActivity().getSharedPreferences(Constants.ENTEL_PREFS, getActivity().MODE_PRIVATE).getBoolean(Constants.RULE_1_PREF, false) &&
                DataManager.getInstance().getSurveyTimer() == null) {
            DataManager.getInstance().surveyTimer(getActivity());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkSurvey();

        mFilter = new IntentFilter();
        mFilter.addAction(Constants.SURVEY_ALERTBOX_NOTIF);
    }

    @Override
    public void onResume() {
        super.onResume();
        BroadcastManager.register(getActivity(), mReceiver, mFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        BroadcastManager.unregister(getActivity(), mReceiver);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BroadcastManager.unregister(getActivity(), mReceiver);
    }

    class JavaScriptInterface {

        private Context ctx;

        JavaScriptInterface(Context ctx) {
            this.ctx = ctx;
        }

        @JavascriptInterface
        public void registerPhone(String phone) {
            mRegisterPhone = phone;
        }

        @JavascriptInterface
        public void registerData(String rut, String key) {
            mRegisterRut = rut;
            mRegisterKey = key;
        }

        @JavascriptInterface
        public void processHTML(String html) {
            Document doc = Jsoup.parse(html);
            Elements elements = doc.select("MZZO");

            if (elements != null && elements.size() > 0 &&
                    elements.get(0).attr("id").equalsIgnoreCase("error")) {

                if (!mIsSessionError) {
                    mSessionErrorAttempts = Integer.parseInt(elements.get(0).attr("attempt"));
                }

                mIsSessionError = true;

                if (mIsSessionError && mSessionErrorAttempts > 0) {
                    mSessionErrorAttempts--;
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mEntelWV.reload();
                            }
                        });
                    }
                } else if (!mErrorWV && getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            onPageLoaded();
                        }
                    });
                }
            } else if (!mErrorWV && getActivity() != null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        onPageLoaded();
                    }
                });
            }
        }

        @JavascriptInterface
        public void errorPopup (String error) {
            showAlert(error);
        }
    }
}
