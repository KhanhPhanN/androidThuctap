package com.example.enterc.practiceinter.Fragment;

import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.enterc.practiceinter.Activity.MainActivity;
import com.example.enterc.practiceinter.Activity.Register;
import com.example.enterc.practiceinter.Activity.Signin;
import com.example.enterc.practiceinter.Activity.UpdateProfileActivity;
import com.example.enterc.practiceinter.Model.Helper.EventSigninHelper;
import com.example.enterc.practiceinter.Model.Helper.GlobalHelper;
import com.example.enterc.practiceinter.Model.Helper.PreferenceHelper;
import com.example.enterc.practiceinter.Model.user.UserProfile;
import com.example.enterc.practiceinter.R;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentAccount extends Fragment {
    @BindView(R.id.btn_register)
    TextView btn_register;
    @BindView(R.id.btn_signin)
    TextView btn_signin;

    @BindView(R.id.signin_layout)
    RelativeLayout signin_layout;
    @BindView(R.id.account_layout)
    LinearLayout account_layout;

    @BindView(R.id.tv_profile_name)
    TextView tv_profile_name;
    @BindView(R.id.tv_gender)
    TextView tv_gender;
    @BindView(R.id.tv_birthday)
    TextView tv_birthday;
    @BindView(R.id.tv_song_favorite_info)
    TextView tv_song_favorite_info;
    @BindView(R.id.tv_singer_favorite_info)
    TextView tv_singer_favorite_info;
    @BindView(R.id.tv_more_profile)
    TextView tv_more_profile;

    @BindView(R.id.btn_update)
    TextView btn_update;
    PreferenceHelper preferenceHelper;
    @BindString(R.string.register_success)
    String register_success;
    int isSignin = 0;

    public FragmentAccount() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_signin, container, false);
        ButterKnife.bind(this, view);
        preferenceHelper = new PreferenceHelper(getContext(), GlobalHelper.PREFERENCE_NAME_PIKA);
        EventBus.getDefault().register(this);
        setupProfile();
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Signin.class);
                startActivity(intent);
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Register.class);
                startActivity(intent);
            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UpdateProfileActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void setupUi() {
        String json = preferenceHelper.getProfile();
        if (json.length() == 0 || preferenceHelper.getSignin() == 0) {
            isSignin = 0;
            if (preferenceHelper.isPremium())
                preferenceHelper.setPremium(false);
        } else {
            isSignin = preferenceHelper.getSignin();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSigninEvent(EventSigninHelper event) {
        switch (event.getStateChange()) {
            case SIGNOUT:
                setupProfile();
                break;
            case REGISTER: {
                EventBus.getDefault().post(new EventSigninHelper(EventSigninHelper.StateChange.SIGNIN));
                if (getActivity() != null)
                    Snackbar.make(getActivity().findViewById(R.id.coordinator), register_success, Snackbar.LENGTH_SHORT).show();
            }

            break;
            case SIGNIN: {
                Log.d("SINGININ", "Đã đăng nhập");
                setupProfile();
            }
        }
    }

    private void setupProfile() {
        if (preferenceHelper.getSignin() != 0) { // đã đăng nhâp
            UserProfile profile = new Gson().fromJson(preferenceHelper.getProfile(), UserProfile.class);
            if (profile != null && profile.getUser() != null) {
                tv_profile_name.setText(profile.getUser().getName());
                if (profile.getUser().getSex() == 0)
                    tv_gender.setText("Nam");
                else
                    tv_gender.setText("Nữ");
                tv_birthday.setText(profile.getUser().getDayOfBirth() + "/" + profile.getUser().getMonthOfBirth() + "/" + profile.getUser().getYearOfBirth());
                tv_singer_favorite_info.setText(profile.getUser().getSingerFavorite());
                tv_song_favorite_info.setText(profile.getUser().getSongFavorite());
                tv_more_profile.setText(profile.getUser().getMoreProfile());
                account_layout.setVisibility(View.VISIBLE);
                signin_layout.setVisibility(View.GONE);
            }
        } else {
            account_layout.setVisibility(View.GONE);
            signin_layout.setVisibility(View.VISIBLE);
        }
    }
}
