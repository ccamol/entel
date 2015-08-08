package cl.entel.appswlsdesa.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import cl.entel.appswlsdesa.utils.Constants;


/**
 * TODO: document your custom view class.
 */
public class EntelEditText extends EditText {

    public EntelEditText(Context context) {
        super(context);
        init(null, 0);
    }

    public EntelEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public EntelEditText(Context context, AttributeSet attrs, int defStyle) {
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
