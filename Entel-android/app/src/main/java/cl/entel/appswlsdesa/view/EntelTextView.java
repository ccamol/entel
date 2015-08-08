package cl.entel.appswlsdesa.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import cl.entel.appswlsdesa.utils.Constants;


/**
 * TODO: document your custom view class.
 */
public class EntelTextView extends TextView {

    public EntelTextView(Context context) {
        super(context);
        init(null, 0);
    }

    public EntelTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public EntelTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                getTypeface() != null && getTypeface().getStyle() == Typeface.BOLD ?
                        Constants.BOLD_FONT : Constants.FONT);

        setTypeface(tf);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

}
