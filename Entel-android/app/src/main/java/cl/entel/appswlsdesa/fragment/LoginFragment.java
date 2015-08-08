package cl.entel.appswlsdesa.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;

import cl.entel.appswlsdesa.activity.DetailActivity;
import cl.entel.appswlsdesa.activity.MainActivity;
import cl.entel.appswlsdesa.activity.OnBoardingActivity;
import cl.entel.appswlsdesa.models.User;
import cl.entel.appswlsdesa.utils.Constants;
import cl.entel.appswlsdesa.utils.DataManager;
import cl.entel.appswlsdesa.utils.NetworkUtil;
import cl.entel.appswlsdesa.utils.ToastManager;
import cl.entel.appswlsdesa.view.EntelEditText;
import cl.entel.appswlsdesa.R;
import cl.entel.appswlsdesa.utils.APIEngine;
import cl.entel.appswlsdesa.utils.ARC4;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class LoginFragment extends BaseFragment {
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LoginFragment.
     */

    private String TAG = LoginFragment.class.getSimpleName();
    private LoginFragmentCallbacks mCallbacks;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_view, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (LoginFragmentCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        phoneET = (EntelEditText) getActivity().findViewById(R.id.et_phone);
        rutET = (EntelEditText) getActivity().findViewById(R.id.et_rut);
        keyET = (EntelEditText) getActivity().findViewById(R.id.et_key);

        Button loginBtn = (Button) getActivity().findViewById(R.id.btn_login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (NetworkUtil.getConnectivityStatusString(getActivity())) {
                    onLogin(false);
                } else {
                    ToastManager.flash(getActivity(), R.string.no_internet_conection, Toast.LENGTH_SHORT);
                }
            }
        });

        ((FrameLayout) getActivity().findViewById(R.id.open_onboarding_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), OnBoardingActivity.class);
                i.putExtra(OnBoardingActivity.IS_FRIST_ON_BOARDING, false);
                i.putExtra(OnBoardingActivity.IS_FROM_LOGIN, true);
                startActivity(i);
                getActivity().overridePendingTransition(R.anim.rotate_out, R.anim.rotate_in);
            }
        });

        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),
                loginBtn.getTypeface() != null && loginBtn.getTypeface().getStyle() == Typeface.BOLD ?
                        Constants.BOLD_FONT : Constants.FONT);
        loginBtn.setTypeface(tf);

//        getActivity().findViewById(R.id.btn_menu).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mCallbacks.onMenu();
//            }
//        });

        getActivity().findViewById(R.id.btn_recover).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRecover();
            }
        });

        getActivity().findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRegister();
            }
        });
    }

    private void onRecover() {
        loadBtnUrl(Constants.getUrl(Constants.RECOVER_URL), getActivity().getString(R.string.recover_title), false);
    }

    /**
     * Callbacks interface that all activities using this fragment must implement.
     */
    public static interface LoginFragmentCallbacks {
        void onMenu();
    }

}
