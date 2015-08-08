package cl.entel.appswlsdesa.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cl.entel.appswlsdesa.R;
import cl.entel.appswlsdesa.utils.Constants;

/**
 * Created by alex on 6/22/15.
 */
public class OnBoardingAdapter extends PagerAdapter {

    private Context mContext;
    private List<Drawable> mImages;
    private String[] mTitles;
    private String[] mSubtitles;
    private String[] mBodies;

    public OnBoardingAdapter(Context mContext, List<Drawable> mImages, String[] mTitles, String[] mSubtitles, String[] mBodies) {
        this.mContext = mContext;
        this.mImages = mImages;
        this.mTitles = mTitles;
        this.mSubtitles = mSubtitles;
        this.mBodies = mBodies;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = ((Activity) mContext).getLayoutInflater().inflate(R.layout.adapter_onboarding, null);
        TextView marker = (TextView) view.findViewById(R.id.marker);

        if (position < mImages.size()) {
            ImageView page = (ImageView) view.findViewById(R.id.img_page);
            page.setImageDrawable(mImages.get(position));
            marker.setVisibility(View.VISIBLE);

            TextView title = (TextView) view.findViewById(R.id.title);
            TextView subtitle = (TextView) view.findViewById(R.id.subtitle);
            TextView body = (TextView) view.findViewById(R.id.body);

            Typeface tf = Typeface.createFromAsset(mContext.getAssets(), Constants.DIN_FONT);
            title.setTypeface(tf);
            subtitle.setTypeface(tf);
            body.setTypeface(tf);
            marker.setTypeface(tf);

            title.setText(getItemString(mTitles[position]));

            if (mSubtitles != null && position < mSubtitles.length) {
                subtitle.setText(getItemString(mSubtitles[position]));
                subtitle.setVisibility(View.VISIBLE);
            } else {
                subtitle.setVisibility(View.GONE);
            }

            body.setText(getItemString(mBodies[position]));

            container.addView(view);
        } else {
            marker.setVisibility(View.GONE);
        }

        return view;
    }

    private String getItemString(String id) {
        int stringId = mContext.getResources()
                .getIdentifier(id, "string", mContext.getPackageName());

        return mContext.getString(stringId);
    }

    @Override
    public int getCount() {
        return mImages.size() + 1;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
