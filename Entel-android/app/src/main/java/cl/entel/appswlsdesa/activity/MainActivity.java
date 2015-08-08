package cl.entel.appswlsdesa.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;

import cl.entel.appswlsdesa.R;
import cl.entel.appswlsdesa.fragment.NavigationDrawerFragment;
import cl.entel.appswlsdesa.fragment.WebViewFragment;
import cl.entel.appswlsdesa.utils.Constants;
import cl.entel.appswlsdesa.utils.DataManager;
import cl.entel.appswlsdesa.utils.MenuManager;

public class MainActivity extends BaseActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    public static String IS_LOAD_URL = "IS_LOAD_URL";
    public static String LOAD_URL = "LOAD_URL";
    public static String LOAD_TITLE = "LOAD_TITLE";
    public static String LOAD_POSITION = "LOAD_POSITION";
    private boolean isLoadUrl = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout),
                MenuManager.getInstance().getMenuMode(DataManager.getInstance().unarchieveUser(this).getMarket()));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position, String url, String title) {
        // update the main content by replacing fragments
        if (url.contains(Constants.RECARGAS_URL)) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
            return;
        }

        String previousUrl = null;

        //To load a specific section
        if (getIntent() != null && getIntent().getExtras() != null) {
            if (getIntent().getBooleanExtra(IS_LOAD_URL, false)) {
                getIntent().removeExtra(IS_LOAD_URL);
                url = getIntent().getExtras().getString(LOAD_URL);
                title = getIntent().getExtras().getString(LOAD_TITLE);
                position = getIntent().getExtras().getInt(LOAD_POSITION);
                previousUrl = getIntent().getStringExtra(WebViewFragment.PREVIOUS_URL);
            }
        }

        getSupportActionBar().setTitle(title + "_");

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, WebViewFragment.newInstance(position, url, title, previousUrl))
                .commit();
    }

    public void closeMenu() {
        mNavigationDrawerFragment.closeMenu();
    }
}
