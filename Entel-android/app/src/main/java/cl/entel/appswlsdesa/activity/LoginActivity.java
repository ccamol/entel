package cl.entel.appswlsdesa.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.animation.TranslateAnimation;

import cl.entel.appswlsdesa.R;
import cl.entel.appswlsdesa.fragment.LoginFragment;
import cl.entel.appswlsdesa.fragment.NavigationDrawerFragment;
import cl.entel.appswlsdesa.fragment.WebViewFragment;
import cl.entel.appswlsdesa.models.User;
import cl.entel.appswlsdesa.utils.Constants;
import cl.entel.appswlsdesa.utils.DataManager;
import cl.entel.appswlsdesa.utils.MenuManager;


public class LoginActivity extends BaseActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, LoginFragment.LoginFragmentCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout),
                MenuManager.MENU_LOGIN);

        mNavigationDrawerFragment.mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position, String url, String title) {
        Fragment f;

        if (position <= 1) {
            f = LoginFragment.newInstance();
            getSupportActionBar().hide();
        } else {
            if (url.contains(Constants.RECARGAS_URL)) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
                return;
            }

            String previousUrl = getIntent().getStringExtra(WebViewFragment.PREVIOUS_URL);
            f = WebViewFragment.newInstance(position, url, title, null);
            getSupportActionBar().show();
            getSupportActionBar().setTitle(title + "_");
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, f)
                .commit();
    }

    @Override
    public void onMenu() {
        mNavigationDrawerFragment.mDrawerLayout.openDrawer(mNavigationDrawerFragment.mFragmentContainerView);
    }
}
