package com.example.enterc.practiceinter.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.enterc.practiceinter.Activity.MainActivity;
import com.example.enterc.practiceinter.Listener.CallBack;
import com.example.enterc.practiceinter.Model.Helper.EventSigninHelper;
import com.example.enterc.practiceinter.Model.Helper.GlobalHelper;
import com.example.enterc.practiceinter.Model.Helper.PreferenceHelper;
import com.example.enterc.practiceinter.Model.Helper.SignoutHelper;
import com.example.enterc.practiceinter.Model.user.UserProfile;
import com.example.enterc.practiceinter.R;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentMore  extends Fragment {

    @BindView(R.id.profile_image)
    ImageView profileCIV;
    @BindView(R.id.version_tv)
    TextView version_tv;
  @BindView(R.id.user_name_tv)
  TextView user_name_tv;
    @BindView(R.id.rela_update_premium)
    RelativeLayout rela_update_premium;
    @BindView(R.id.rela_notification)
    RelativeLayout rela_notification;
    @BindView(R.id.rela_select_language)
    RelativeLayout rela_select_language;
    @BindView(R.id.tv_current_language)
    TextView tvCurrentLanguage;
    @BindView(R.id.rela_select_theme)
    RelativeLayout rela_select_theme;
    @BindView(R.id.tv_current_theme)
    TextView tvCurrentTheme;
   @BindView(R.id.rela_profile)
   RelativeLayout rela_profile;
   @BindView(R.id.btn_signin)
   AppCompatButton btn_signin;
    @BindDrawable(R.drawable.avatar_default)
    Drawable avatar_default;
@BindView(R.id.btn_signout)
AppCompatButton btn_signout;
    @BindString(R.string.select_language)
    String selectLanguage;
    @BindString(R.string.device_language)
    String deviceLanguage;
    @BindString(R.string.selecet_theme)
    String selectTheme;
    @BindString(R.string.theme_light)
    String themeLight;
    @BindString(R.string.theme_dark)
    String themeDark;
    @BindString(R.string.language_code_2)
    String language_code_2;
    @BindString(R.string.register_success)
    String register_success;
    @BindColor(R.color.colorBlack_4)
    int colorBlack4;

    private String currentLanguageCode;
    private int currentLangPosition;
    private ArrayList<String> languageCodeList = new ArrayList<>();
    private ArrayList<String> languageList = new ArrayList<>();

    private ArrayList<String> themeList = new ArrayList<>();
    private int currentTheme;
    private PreferenceHelper preferenceHelper;


    public FragmentMore() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more,container,false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        initItemSelectLanguage();
        preferenceHelper = new PreferenceHelper(getContext(), GlobalHelper.PREFERENCE_NAME_PIKA);
        rela_select_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogSelectLanguage();
            }
        });
        setupProfile();
        return view;
    }
    private void initItemSelectLanguage() {
        languageCodeList.add("");
        languageCodeList.add("vi");
        languageCodeList.add("en");
        languageCodeList.add("id");
        languageCodeList.add("ja");

        languageList.add(deviceLanguage);
        languageList.add("Việt Nam (Tiếng Việt)");
        languageList.add("United States (English)");
        languageList.add("Indonesia (Bahasa Indonesia)");
        languageList.add("日本 (日本語)");

        getCurrentLanguage();

        tvCurrentLanguage.setText(languageList.get(currentLangPosition));
    }
    private void getCurrentLanguage() {
        currentLanguageCode = preferenceHelper.getApplicationLanguageCode();
        currentLangPosition = 0;
        if (currentLanguageCode != null) {
            currentLangPosition = languageCodeList.indexOf(currentLanguageCode);
        } else {
            currentLanguageCode = language_code_2;
        }
    }



    private void showDialogSelectLanguage() {
        if (getActivity() == null)
            return;
        //Woz
        AlertDialog dialog;
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext()).setTitle(selectLanguage);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(selectLanguage);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_single_choice, languageList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                int textColor = GlobalHelper.resolveColorByStyle(getContext(), colorBlack4, colorBlack4);
                textView.setTextColor(textColor);
                return textView;
            }
        };

        final String currentDeviceLanguageCode = getContext().getResources().getSystem().getConfiguration().locale.getLanguage();

        builder.setSingleChoiceItems(arrayAdapter, currentLangPosition, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                if (i == currentLangPosition || currentLangPosition == -1) {
                    return;
                }
                if (i == 0) {//Select on "Device language"
                    //Do something to set to device language
                    if (!currentLanguageCode.equals(currentDeviceLanguageCode)) {
                        setLocale(currentDeviceLanguageCode);
                    }
                    preferenceHelper.setapplicationLanguageCode(null);
                    currentLangPosition = 0;
                } else {//Select on a different language
                    if (!languageCodeList.get(i).equals(currentLanguageCode)) {
                        setLocale(languageCodeList.get(i));
                    }
                    preferenceHelper.setapplicationLanguageCode(languageCodeList.get(i));
                    currentLangPosition = i;
                }
                tvCurrentLanguage.setText(languageList.get(currentLangPosition));
            }
        });
        dialog = builder.create();
        dialog.show();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferenceHelper = new PreferenceHelper(getContext(), GlobalHelper.PREFERENCE_NAME_PIKA);
    }
    public void setLocale(String lang) {
//        Log.d("Language", lang);
        Locale myLocale = new Locale(lang);
        Resources res = getContext().getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        if (getActivity() != null && getActivity() instanceof MainActivity) {
//            getActivity().recreate();
            //startActivity(getActivity().getIntent());
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            //getActivity().finish();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSigninEvent(EventSigninHelper event){
        switch (event.getStateChange()) {
            case SIGNOUT:
                setupProfile();
                break;
            case REGISTER:
                EventBus.getDefault().post(new EventSigninHelper(EventSigninHelper.StateChange.SIGNIN));
                if (getActivity() != null)
                    Snackbar.make(getActivity().findViewById(R.id.coordinator), register_success, Snackbar.LENGTH_SHORT).show();
                break;
            case SIGNIN:
            {
                setupProfile();
            }
        }
    }
    private void setupProfile() {
        if (preferenceHelper.getSignin() != 0) { // đã đăng nhâp
            UserProfile profile = new Gson().fromJson(preferenceHelper.getProfile(), UserProfile.class);
            if (profile != null && profile.getUser() != null) {
                user_name_tv.setText(profile.getUser().getName());
                rela_profile.setVisibility(View.VISIBLE);
                btn_signin.setVisibility(View.GONE);
                btn_signout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //EventBus.getDefault().unregister(EventSigninHelper.StateChange.SIGNOUT);
                        SignoutHelper signoutHelper = new SignoutHelper(getContext(), onPostExecute);
                        signoutHelper.signoutAccount();
                    }
                });
            }
        } else {
            rela_profile.setVisibility(View.GONE);
            btn_signin.setVisibility(View.VISIBLE);
        }
    }
    public CallBack onPostExecute = new CallBack() {
        @Override
        public void execute() {
            setupProfile();
            EventBus.getDefault().post(new EventSigninHelper(EventSigninHelper.StateChange.SIGNOUT));
        }
    };
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
