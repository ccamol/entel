package cl.entel.appswlsdesa.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import cl.entel.appswlsdesa.R;
import cl.entel.appswlsdesa.fragment.WebViewFragment;

public class DetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        int position = getIntent().getIntExtra(WebViewFragment.ARG_SECTION_NUMBER,1);
        String url = getIntent().getStringExtra(WebViewFragment.NEXT_URL);
        String previousUrl = getIntent().getStringExtra(WebViewFragment.PREVIOUS_URL);
        String title = getIntent().getStringExtra(WebViewFragment.TITLE);
        boolean isRegister = getIntent().getBooleanExtra(WebViewFragment.IS_REGISTER, false);
        boolean isNotRegisteredUser = getIntent().getBooleanExtra(WebViewFragment.IS_NOT_REGISTERED_USER, false);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title + "_");

        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, WebViewFragment.newInstance(position, url, title, isRegister, isNotRegisteredUser, previousUrl))
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
