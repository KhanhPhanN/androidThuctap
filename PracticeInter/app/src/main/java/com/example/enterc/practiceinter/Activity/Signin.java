package com.example.enterc.practiceinter.Activity;

import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.enterc.practiceinter.Listener.AccountCallBack;
import com.example.enterc.practiceinter.Listener.CallBack;
import com.example.enterc.practiceinter.Model.Helper.EventSigninHelper;
import com.example.enterc.practiceinter.Model.Helper.GlobalHelper;
import com.example.enterc.practiceinter.Model.Helper.PreferenceHelper;
import com.example.enterc.practiceinter.Model.Helper.SigninHelper;
import com.example.enterc.practiceinter.Model.user.InfoSigninJSONObject;
import com.example.enterc.practiceinter.Model.user.UserProfile;
import com.example.enterc.practiceinter.R;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Signin extends AppCompatActivity {
@BindView(R.id.btn_signin)
    TextView btn_signin;
@BindView(R.id.et_account_email)
    EditText et_account_email;
@BindView(R.id.et_password_email)
    EditText et_password_email;
@BindView(R.id.tv_enter_email)
TextView tv_enter_email;
@BindView(R.id.tv_enter_password)
TextView tv_enter_password;
@BindView(R.id.view_1)
View view_1;

@BindView(R.id.tv_show_hide)
TextView tv_show_hide;
    @BindView(R.id.view_2)
    View view_2;
    @BindView(R.id.pb_sigin_email)
    ProgressBar pb_sigin_email;
    @BindString(R.string.signin_error)
    String signin_error;
    @BindString(R.string.account_not_correct)
    String account_not_correct;
    @BindString(R.string.sign_in_email_account)
    String sign_in_email_account;
    @BindString(R.string.invalid_email)
    String invalid_email;
    @BindString(R.string.can_not_null)
    String can_not_null;
    @BindString(R.string.invalid_password)
    String invalid_password;
    @BindString(R.string.show)
    String show;
    @BindString(R.string.hide)
    String hide;

    Boolean checkInfo = false;
    Boolean checkShow = false;
  PreferenceHelper preferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        preferenceHelper = new PreferenceHelper(getApplicationContext(), GlobalHelper.PREFERENCE_NAME_PIKA);
        ButterKnife.bind(this);
        Toolbar toolbarSigin = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbarSigin);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        try {
            et_account_email.addTextChangedListener(textWatcher);
            et_password_email.addTextChangedListener(textWatcher);
        } catch (NullPointerException e) {
            finish();
        }
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singInRequest();
            }
        });
    }

    private  void singInRequest(){
        if (et_account_email.getText().length() == 0 || et_password_email.getText().length() == 0 || !checkInfo) {
            if (et_account_email.getText().length() == 0) {
                tv_enter_email.setVisibility(View.VISIBLE);
                tv_enter_email.setText(can_not_null);
                view_1.setBackgroundColor(getResources().getColor(R.color.colorTextRed));
            }
            if (et_password_email.getText().length() == 0) {
                tv_enter_password.setVisibility(View.VISIBLE);
                tv_enter_password.setText(can_not_null);
                view_2.setBackgroundColor(getResources().getColor(R.color.colorTextRed));
            }
            return;
        }
        String query = "email=" + et_account_email.getText().toString() + "&password=" + et_password_email.getText().toString();
        SigninHelper signinHelper = new SigninHelper(GlobalHelper.REQUEST_SIGNIN_EMAIL_URL, onPreExecute, onPostExecute);
        signinHelper.signinAccount(query);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    CallBack onPreExecute = new CallBack() {
        @Override
        public void execute() {
            btn_signin.setVisibility(View.GONE);
            pb_sigin_email.setVisibility(View.VISIBLE);
        }
    };
    AccountCallBack onPostExecute = new AccountCallBack() {
        @Override
        public void execute(String json) {
            pb_sigin_email.setVisibility(View.GONE);
            btn_signin.setVisibility(View.VISIBLE);
            GlobalHelper.hideKeyboard(Signin.this);
            Log.d("LOGIN", json);
            if (json != null) {
                UserProfile userProfile = new Gson().fromJson(json, UserProfile.class);
                if (userProfile.getErr() == null) {
                    preferenceHelper.setSignin(1);
                    preferenceHelper.setProfile(json);

                    String json_signin = preferenceHelper.getInfoSigninUser();
                    InfoSigninJSONObject infoSignin;

                    if (json_signin.length() == 0)
                        infoSignin = new InfoSigninJSONObject();
                    else
                        infoSignin = new Gson().fromJson(json_signin, InfoSigninJSONObject.class);
                    infoSignin.setEmail_account(et_account_email.getText().toString());
                    infoSignin.setEmail_password(et_password_email.getText().toString());
                    preferenceHelper.setInfoSigninUser(new Gson().toJson(infoSignin));
                    EventBus.getDefault().post(new EventSigninHelper(EventSigninHelper.StateChange.SIGNIN));
                    finish();
                }
            }

        }
    };


    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (et_account_email.isFocused()) {
                if (et_account_email.getText().toString().length() == 0) {
                    checkInfo = false;
                    tv_enter_email.setVisibility(View.VISIBLE);
                    tv_enter_email.setText(can_not_null);
                    view_1.setBackgroundColor(getResources().getColor(R.color.colorTextRed));
                } else if (!validate(et_account_email.getText().toString().trim())) {
                    checkInfo = false;
                    tv_enter_email.setVisibility(View.VISIBLE);
                    tv_enter_email.setText(invalid_email);
                    view_1.setBackgroundColor(getResources().getColor(R.color.colorTextRed));
                } else {
                    checkInfo = true;
                    tv_enter_email.setVisibility(View.INVISIBLE);
                    view_1.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                }
            } else if (et_password_email.isFocused()) {
                if (et_password_email.getText().toString().length() == 0) {
                    checkInfo = false;
                    tv_enter_password.setVisibility(View.VISIBLE);
                    tv_enter_password.setText(can_not_null);
                    view_2.setBackgroundColor(getResources().getColor(R.color.colorTextRed));
                    tv_show_hide.setVisibility(View.GONE);
                } else if (et_password_email.getText().toString().length() < 6) {
                    checkInfo = false;
                    tv_enter_password.setVisibility(View.VISIBLE);
                    tv_enter_password.setText(invalid_password);
                    view_2.setBackgroundColor(getResources().getColor(R.color.colorTextRed));
                    tv_show_hide.setVisibility(View.VISIBLE);
                } else {
                    checkInfo = true;
                    tv_enter_password.setVisibility(View.INVISIBLE);
                    view_2.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    tv_show_hide.setVisibility(View.VISIBLE);
                }
            }
        }
    };
    public static Boolean validate(String email) {
        String expressions = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern regex = Pattern.compile(expressions);
        Matcher matcher = regex.matcher(email);
        return matcher.find();
    }



}
