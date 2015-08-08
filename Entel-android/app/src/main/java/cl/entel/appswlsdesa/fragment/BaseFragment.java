package cl.entel.appswlsdesa.fragment;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Date;

import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;

import cl.entel.appswlsdesa.R;
import cl.entel.appswlsdesa.activity.DetailActivity;
import cl.entel.appswlsdesa.activity.LoginActivity;
import cl.entel.appswlsdesa.activity.MainActivity;
import cl.entel.appswlsdesa.models.User;
import cl.entel.appswlsdesa.utils.APIEngine;
import cl.entel.appswlsdesa.utils.ARC4;
import cl.entel.appswlsdesa.utils.Constants;
import cl.entel.appswlsdesa.utils.DataManager;
import cl.entel.appswlsdesa.utils.NetworkUtil;
import cl.entel.appswlsdesa.utils.ToastManager;
import cl.entel.appswlsdesa.view.EntelEditText;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class BaseFragment extends Fragment {

    public static final String NEXT_URL = "next_url";
    public static final String PREVIOUS_URL = "previous_url";
    public static final String TITLE = "title";

    protected ProgressDialog progressDialog;
    protected EntelEditText keyET;
    protected EntelEditText rutET;
    protected EntelEditText phoneET;
    protected String mRegisterPhone, mRegisterRut, mRegisterKey;

    public BaseFragment() {
        // Required empty public constructor
    }

    protected void onLogout() {
        if (!NetworkUtil.getConnectivityStatusString(getActivity())) {
            ToastManager.flash(getActivity(), R.string.no_internet_conection, Toast.LENGTH_SHORT);
            return;
        }

        showProgressDialog();
        APIEngine.getInstance().logout(getActivity(), new APIEngine.LogoutCallback() {
            @Override
            public void onSuccess() {
                DataManager.getInstance().logout(getActivity());
                Intent i = new Intent(getActivity(), LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(i);
                getActivity().finish();
                progressDialog.dismiss();
            }

            @Override
            public void onError(String msg) {
                progressDialog.dismiss();
                ToastManager.flash(getActivity(), msg, Toast.LENGTH_SHORT);
            }
        });
    }

    public void onLogin(boolean isRegister) {
        onLogin(isRegister, false);
    }

    public void onLogin(boolean isRegister, boolean isNotRegisteredUser) {
        if (isNotRegisteredUser) {
            showProgressDialog();
            loginWithCriptedKey(DataManager.getInstance().getCipherKey());
            return;
        }

        String phone = isRegister ? mRegisterPhone : phoneET.getText().toString();
        String rut = isRegister ? mRegisterRut : rutET.getText().toString();
        String key = isRegister ? mRegisterKey : keyET.getText().toString();

        rut = rut.replace(".", "")
                .replace("-", "");

        if (!isRegister) {
            if (phone.isEmpty()) {
                phoneET.requestFocus();
                showAlert(getActivity().getString(R.string.login_phone_error));
                return;
            } else if (phone.length() < 8 || phoneET.length() > 8) {
                showAlert(getActivity().getString(R.string.login_error_phone_size));
                phoneET.requestFocus();
                return;
            } else if (rut.isEmpty()) {
                showAlert(getActivity().getString(R.string.login_error_rut));
                rutET.requestFocus();
                return;
            } else if (!validateRut(rut)) {
                showAlert(getActivity().getString(R.string.login_incorrect_error_rut));
                rutET.requestFocus();
                return;
            } else if (key.isEmpty()) {
                showAlert(getActivity().getString(R.string.login_error_pin));
                keyET.requestFocus();
                return;
            }
        }

//        StringBuilder catBag = new StringBuilder();
//        catBag.append(Constants.VERSION_CODE)
//                .append("|")
//                .append(getDeviceName())
//                .append("|Android ")
//                .append(Build.VERSION.RELEASE);

        showProgressDialog();

        StringBuilder cipherText = new StringBuilder();
        cipherText.append(phone)
                .append(",")
                .append(rut)
                .append(",")
                .append(key);
//                .append(",")
//                .append(catBag.toString());

        try {
            String cryptedKey = ARC4.encrypt(cipherText.toString());
            DataManager.getInstance().setCipherKey(cryptedKey);
            loginWithCriptedKey(cryptedKey);
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (ShortBufferException e) {
            e.printStackTrace();
        }
    }

    private void loginWithCriptedKey(String cryptedKey) {
        APIEngine.getInstance().getToken(getActivity(), cryptedKey, new APIEngine.TokenCallback() {

            @Override
            public void onSuccess(String token) {
                loginWithToken(token);
            }

            @Override
            public void onError(String error) {
                progressDialog.dismiss();
                showAlert(error);
            }

            @Override
            public void onRegisterView() {
                progressDialog.dismiss();
                onRegister(true);
            }

        });
    }

    private void loginWithToken (String token) {
        APIEngine.getInstance().login(getActivity(), token, new APIEngine.LoginCallback() {
            @Override
            public void onSuccess(User user) {
                progressDialog.dismiss();
                DataManager.getInstance().archieveUser(getActivity(), user);
                Intent i = new Intent(getActivity(), MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(i);
                getActivity().finish();
            }

            @Override
            public void onError(String error) {
                progressDialog.dismiss();
                showAlert(error);
            }
        });
    }

    public static boolean validateRut(String rutdv) {
        int rut;

        try {
            rut = Integer.valueOf(rutdv.substring(0, rutdv.length() - 1));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }

        char dv = rutdv.charAt(rutdv.length()-1);

        //to accept 'k' lowercase to avoid issues with "K" clients
        dv = (dv == 'k')?'K':dv;

        int m = 0, s = 1;
        for (; rut != 0; rut /= 10)
        {
            s = (s + rut % 10 * (9 - m++ % 6)) % 11;
        }
        return dv == (char) (s != 0 ? s + 47 : 75);
    }

    protected void onRegister() {
        onRegister(false);
    }

    protected void onRegister(boolean isNotRegisteredUser) {
        loadBtnUrl(Constants.getUrl(Constants.REGISTER_URL), getActivity().getString(R.string.register_title), true, isNotRegisteredUser);
    }

    protected void loadBtnUrl(String url, String title, boolean isRegister) {
        loadBtnUrl(url, title, isRegister, false);
    }

    protected void loadBtnUrl(String url, String title, boolean isRegister, boolean isNotRegisteredUser) {
        Intent i = new Intent(getActivity(), DetailActivity.class);
        i.putExtra(WebViewFragment.ARG_SECTION_NUMBER, getArguments().getInt(WebViewFragment.ARG_SECTION_NUMBER));
        i.putExtra(WebViewFragment.NEXT_URL, url);
        i.putExtra(WebViewFragment.TITLE, title);
        i.putExtra(WebViewFragment.IS_REGISTER, isRegister);
        i.putExtra(WebViewFragment.IS_NOT_REGISTERED_USER, isNotRegisteredUser);
        getActivity().startActivity(i);
    }

    protected void showProgressDialog() {
        progressDialog = ProgressDialog.show(getActivity(), getString(R.string.loading_title), getString(R.string.loading_text), false);
        progressDialog.setCancelable(false);
    }

    protected void showSurveyAlert() {
        if (getActivity() == null) {
            return;
        }

        DataManager.getInstance().setSurveyAlertShowed(true);
        DataManager.getInstance().setSurveyLastupdate(getActivity());

        new AlertDialog.Builder(getActivity())
                .setMessage(getActivity().getString(R.string.alertbox_text))
                .setPositiveButton(R.string.alertbox_cancel_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //cancel survey
                    }
                })
                .setNegativeButton(R.string.alertbox_ok_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (getActivity() != null && getActivity() instanceof MainActivity) {
                            ((MainActivity) getActivity()).closeMenu();
                        }
                        startNewActivity(Constants.URL_SURVEY, getActivity().getString(R.string.survey_header));
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    protected void showAlert(String msg) {
        if (getActivity() == null) {
            return;
        }

        new AlertDialog.Builder(getActivity())
                .setMessage(msg)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    protected void startNewActivity(String url) {
        startNewActivity(url, null);
    }

    protected void startNewActivity(String url, String title) {
        Intent i = new Intent(getActivity(), DetailActivity.class);
        i.putExtra(WebViewFragment.ARG_SECTION_NUMBER, getArguments().getInt(WebViewFragment.ARG_SECTION_NUMBER));
        i.putExtra(PREVIOUS_URL, getArguments().getString(NEXT_URL));
        i.putExtra(NEXT_URL, url);
        i.putExtra(TITLE, title == null ? getArguments().getString(TITLE) : title);
        getActivity().startActivity(i);
    }

    public String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }

    private String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }
}
