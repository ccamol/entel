package cl.entel.appswlsdesa.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

import cl.entel.appswlsdesa.R;
import cl.entel.appswlsdesa.models.User;
import cl.entel.appswlsdesa.utils.DataManager;
import cl.entel.appswlsdesa.view.OnBoardingAdapter;

/**
 * Created by alex on 6/22/15.
 */
public class OnBoardingActivity extends BaseActivity {

    public static final String IS_FRIST_ON_BOARDING = "IS_FRIST_ON_BOARDING";
    public static final String IS_FROM_LOGIN = "IS_FROM_LOGIN";

    private ViewPager mOnboardingPager;
    private OnBoardingAdapter mOnboardingAdapter;
    private CirclePageIndicator mIndicator;
    private boolean mIsFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        getViews();
        setViews();
    }

    private void setViews() {
        mIsFirst = getIntent().getBooleanExtra(IS_FRIST_ON_BOARDING , true);

        final List<Drawable> images = getDrawables(getResources().getStringArray(mIsFirst ?
                R.array.onboarding : R.array.onboarding_update));

        String[] titles = getResources().getStringArray(mIsFirst ?
                R.array.onboarding_titles : R.array.onboarding_update_titles);

        String[] subtitles = getResources().getStringArray(mIsFirst ?
                R.array.onboarding_subtitles : R.array.onboarding_update_subtitles);

        String[] bodies = getResources().getStringArray(mIsFirst ?
                R.array.onboarding_bodies : R.array.onboarding_update_bodies);

        mOnboardingAdapter = new OnBoardingAdapter(this, images, titles, subtitles, bodies);
        mOnboardingPager.setAdapter(mOnboardingAdapter);

        mIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == images.size() - 1 && positionOffset > 0) {
                    onClose();
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mIndicator.setViewPager(mOnboardingPager);
        mIndicator.setSnap(true);
        mIndicator.setExtraSpacing(10);

        findViewById(R.id.close_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClose();
            }
        });
    }

    private void getViews() {
        mOnboardingPager = (ViewPager) findViewById(R.id.tutorial_pager);
        mIndicator = (CirclePageIndicator) findViewById(R.id.page_indicator);
    }

    private void onClose() {
        DataManager.getInstance().setIsFirstLaunch(this, false);

        if (getIntent().getBooleanExtra(IS_FROM_LOGIN, false)) {
            finish();
            overridePendingTransition(R.anim.rotate_out, R.anim.rotate_in);
            return;
        }

        if (mIsFirst) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            overridePendingTransition(R.anim.rotate_out, R.anim.rotate_in);

        } else {
            User user = DataManager.getInstance().unarchieveUser(this);
            Intent i;

            if (user != null) {
                if (user.getAaa() != -1) {
                    DataManager.getInstance().incrementNumExecutions(this);
                    i = new Intent(this, MainActivity.class);
                } else {
                    i = new Intent(this, LoginActivity.class);
                }
            } else {
                i = new Intent(this, LoginActivity.class);
            }

            startActivity(i);
            finish();
            overridePendingTransition(R.anim.rotate_out, R.anim.rotate_in);
        }
    }

    private List<Drawable> getDrawables(String[] imagesNames) {
        List<Drawable> images = new ArrayList<Drawable>();

        for (int i = 0; i < imagesNames.length; i++) {
            int resID = getResources().getIdentifier(imagesNames[i], "drawable", getPackageName());
            images.add(getResources().getDrawable(resID));
        }

        return images;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch(keyCode){
            case KeyEvent.KEYCODE_BACK:
                if (getIntent().getBooleanExtra(IS_FROM_LOGIN, false)) {
                    finish();
                    overridePendingTransition(R.anim.rotate_out, R.anim.rotate_in);
                    return true;
                } else {
                    return super.onKeyDown(keyCode, event);
                }
        }
        return super.onKeyDown(keyCode, event);
    }
}
