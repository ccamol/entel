package cl.entel.appswlsdesa.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;

import cl.entel.appswlsdesa.R;
import cl.entel.appswlsdesa.models.User;
import cl.entel.appswlsdesa.utils.Constants;
import cl.entel.appswlsdesa.utils.DataManager;

public class SplashscreenActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                SharedPreferences prefs = getSharedPreferences(Constants.ENTEL_PREFS, MODE_PRIVATE);
                User user = DataManager.getInstance().unarchieveUser(SplashscreenActivity.this);
                Intent i;

                if (prefs.getBoolean(Constants.IS_FIRST_LAUNCH, true)) {
                    DataManager.getInstance().setLastVersionCode(SplashscreenActivity.this);
                    loadOnboarding(true);
                } else if (DataManager.getInstance().getVersionCode(SplashscreenActivity.this) > prefs.getInt(Constants.LAST_VERSION_CODE, 0)) {
                    DataManager.getInstance().setLastVersionCode(SplashscreenActivity.this);
                    loadOnboarding(false);
                } else {
                    if (user != null) {
                        if (user.getAaa() != -1) {
                            DataManager.getInstance().incrementNumExecutions(SplashscreenActivity.this);
                            i = new Intent(SplashscreenActivity.this, MainActivity.class);
                        } else {
                            i = new Intent(SplashscreenActivity.this, LoginActivity.class);
                        }
                    } else {
                        i = new Intent(SplashscreenActivity.this, LoginActivity.class);
                    }

                    SplashscreenActivity.this.startActivity(i);
                    SplashscreenActivity.this.finish();

                }

                // transition from splash to main menu
                overridePendingTransition(android.R.anim.fade_in,
                        android.R.anim.fade_out);
            }
        }, 2000);
    }

    private void loadOnboarding(boolean isFirstOnboarding) {
        Intent i = new Intent(this, OnBoardingActivity.class);
        i.putExtra(OnBoardingActivity.IS_FRIST_ON_BOARDING, isFirstOnboarding);
        startActivity(i);
        finish();
    }
}
