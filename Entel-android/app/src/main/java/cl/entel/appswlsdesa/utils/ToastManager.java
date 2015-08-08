package cl.entel.appswlsdesa.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by alex on 7/31/14.
 */
public class ToastManager {
    public static void flash(Context context, int resId, int length) {
        flash(context, context.getString(resId),length);
    }
    public static void flash(Context context, String message, int length) {
        if (context != null && message != null && !message.isEmpty()) {
            Toast toast = Toast.makeText(context, message, length);
            toast.setGravity(Gravity.CENTER, 0, 0);
            LinearLayout toastLayout = (LinearLayout) toast.getView();
            TextView toastTV = (TextView) toastLayout.getChildAt(0);
            toastTV.setTextSize(16);
            toast.show();
        }
    }
}
