package com.example.enterc.practiceinter.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.enterc.practiceinter.Listener.AccountCallBack;
import com.example.enterc.practiceinter.Listener.CallBack;
import com.example.enterc.practiceinter.Model.Helper.AnimationHelper;
import com.example.enterc.practiceinter.Model.Helper.CheckConnect;
import com.example.enterc.practiceinter.Model.Helper.EventSigninHelper;
import com.example.enterc.practiceinter.Model.Helper.GlobalHelper;
import com.example.enterc.practiceinter.Model.Helper.PreferenceHelper;
import com.example.enterc.practiceinter.Model.Helper.RegisterHelper;
import com.example.enterc.practiceinter.Model.Helper.SigninHelper;
import com.example.enterc.practiceinter.Model.Helper.UpdateProfileHelper;
import com.example.enterc.practiceinter.Model.user.UserProfile;
import com.example.enterc.practiceinter.R;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindArray;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Register extends AppCompatActivity {
    @BindView(R.id.main_layout_id)
    RelativeLayout main_layout_id;
    @BindView(R.id.tool_bar)
    Toolbar tool_bar;
    @BindView(R.id.layout_register)
    NestedScrollView layout_register;
    @BindView(R.id.btn_register_facebook)
    LinearLayout btn_register_facebook;
    @BindView(R.id.pb_register_fb)
    ProgressBar pb_register_fb;
    @BindView(R.id.btn_register_google)
    LinearLayout btn_register_google;
    @BindView(R.id.pb_register_gg)
    ProgressBar pb_register_gg;
    @BindView(R.id.tv_register_agree)
    TextView tv_register_agree;

    @BindView(R.id.add_profile_layout)
    LinearLayout add_profile_layout;
    @BindView(R.id.et_name_email)
    EditText et_name_email;
    @BindView(R.id.et_account_email)
    EditText et_account_email;
    @BindView(R.id.et_password_email)
    EditText et_password_email;
    @BindView(R.id.et_submit_password_email)
    EditText et_submit_password_email;
    @BindView(R.id.btn_continue)
    TextView btn_continue;
    @BindView(R.id.pb_continue)
    ProgressBar pb_continue;
    @BindView(R.id.tv_enter_name)
    TextView tv_enter_name;
    @BindView(R.id.tv_enter_email)
    TextView tv_enter_email;
    @BindView(R.id.tv_enter_password)
    TextView tv_enter_password;
    @BindView(R.id.tv_enter_submit_password)
    TextView tv_enter_submit_password;
    @BindView(R.id.view_1)
    View view_1;
    @BindView(R.id.view_2)
    View view_2;
    @BindView(R.id.view_3)
    View view_3;
    @BindView(R.id.view_4)
    View view_4;

    @BindView(R.id.tv_level)
    TextView tv_level;
    @BindView(R.id.tv_sex)
    TextView tv_sex;
    @BindView(R.id.tv_day)
    TextView tv_day;
    @BindView(R.id.tv_month)
    TextView tv_month;
    @BindView(R.id.tv_year)
    TextView tv_year;

    @BindView(R.id.update_profile_layout)
    LinearLayout update_profile_layout;
    @BindView(R.id.et_song_favorite)
    EditText et_song_favorite;
    @BindView(R.id.et_singer_favorite)
    EditText et_singer_favorite;
    @BindView(R.id.et_more_profile)
    EditText et_more_profile;
    @BindView(R.id.btn_finish)
    TextView btn_finish;
    @BindView(R.id.pb_finish)
    ProgressBar pb_finish;

    @BindString(R.string.register_error)
    String register_error;
    @BindString(R.string.email_already_exists)
    String email_already_exists;
    @BindString(R.string.no_internet)
    String no_internet;
    @BindString(R.string.enter_full_info)
    String enter_full_info;
    @BindString(R.string.choose)
    String choose;
    @BindString(R.string.day)
    String tx_day;
    @BindString(R.string.month)
    String tx_month;
    @BindString(R.string.year)
    String tx_year;
    @BindString(R.string.sex)
    String tx_sex;
    @BindString(R.string.japanese_level)
    String tx_level;
    @BindString(R.string.account_not_correct)
    String account_not_correct;
    @BindString(R.string.register_agree_text)
    String register_agree_text;
    @BindString(R.string.can_not_null)
    String can_not_null;
    @BindString(R.string.something_wrong)
    String something_wrong;

    private String level_jap = "";
    private String day_of_birth = "";
    private String month_of_birth = "";
    private String year_of_birth = "";
    private Boolean checkInfo = false;
    private int posLV = 0;
    private int posSex = 1;
    private int sex = 0;
    private String[] list_day, list_month, list_year;
    private int posDay = 0, posMonth = 0, posYear = 0;
    int year = Calendar.getInstance().get(Calendar.YEAR);
    private PreferenceHelper preferenceHelper;
    private String song, singer, more;
    String[] list_sex = new String[]{"Nam","Nữ"}, list_level = new String[]{"N1","N2","N3","N4","N5"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        preferenceHelper = new PreferenceHelper(getApplicationContext(), GlobalHelper.PREFERENCE_NAME_PIKA);
        ButterKnife.bind(this);
        Toolbar toolbarSigin = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbarSigin);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        et_name_email.addTextChangedListener(textWatcher);
        et_account_email.addTextChangedListener(textWatcher);
        et_password_email.addTextChangedListener(textWatcher);
        et_submit_password_email.addTextChangedListener(textWatcher);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @OnClick({R.id.btn_register_facebook, R.id.btn_register_google,
            R.id.sp_level, R.id.sp_sex, R.id.sp_day, R.id.sp_month, R.id.sp_year, R.id.btn_continue, R.id.btn_finish})
    public void onRegister(View v) {
        final Intent[] intent = new Intent[1];
        switch (v.getId()) {
            case R.id.btn_register_facebook:
                AnimationHelper.ScaleAnimation(btn_register_facebook, new CallBack() {
                    @Override
                    public void execute() {
//                        LoginManager.getInstance().logInWithReadPermissions(RegisterActivity.this, Arrays.asList("public_profile", "email"));
//                        sign_in = 2;
                    }
                }, 0.96f);
                break;
            case R.id.btn_register_google:
                AnimationHelper.ScaleAnimation(btn_register_google, new CallBack() {
                    @Override
                    public void execute() {
//                        intent[0] = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
//                        startActivityForResult(intent[0], REGISTER_GOOGLE);
//                        sign_in = 3;
                    }
                }, 0.96f);
                break;
            case R.id.sp_level:
                chooseLevel();
                break;
            case R.id.sp_sex:
                chooseSex();
                break;
            case R.id.sp_day:
                chooseDay();
                break;
            case R.id.sp_month:
                chooseMonth();
                break;
            case R.id.sp_year:
                chooseYear();
                break;
            case R.id.btn_continue:

                AnimationHelper.ScaleAnimation(btn_continue, new CallBack() {
                    @Override
                    public void execute() {
                        continue_add_profile();
                    }
                }, 0.96f);
                break;
            case R.id.btn_finish:
                AnimationHelper.ScaleAnimation(btn_finish, new CallBack() {
                    @Override
                    public void execute() {
                        update_profile();
                    }
                }, 0.96f);
                break;
        }
    }
        private void chooseLevel() {
            AlertDialog dialog;
            AlertDialog.Builder builder = new AlertDialog.Builder(Register.this)
                    .setTitle(tx_level);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, list_level);
            builder.setSingleChoiceItems(adapter, posLV, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0:
                            tv_level.setText(list_level[0]);
                            posLV = 0;
                            break;
                        case 1:
                            tv_level.setText(list_level[1]);
                            posLV = 1;
                            break;
                        case 2:
                            tv_level.setText(list_level[2]);
                            posLV = 2;
                            break;
                        case 3:
                            tv_level.setText(list_level[3]);
                            posLV = 3;
                            break;
                        case 4:
                            tv_level.setText(list_level[4]);
                            posLV = 4;
                            break;
                    }
                    dialog.dismiss();
                }
            });
            dialog = builder.create();
            dialog.show();
        }

        private void chooseSex() {
            AlertDialog dialog;
            AlertDialog.Builder builder = new AlertDialog.Builder(Register.this)
                    .setTitle(tx_sex);
            ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, list_sex);
            builder.setSingleChoiceItems(adapter, posSex - 1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0:
                            tv_sex.setText(list_sex[0]);
                            posSex = 1;
                            break;
                        case 1:
                            tv_sex.setText(list_sex[1]);
                            posSex = 2;
                            break;
                    }
                    dialog.dismiss();
                }
            });
            dialog = builder.create();
            dialog.show();
        }

        private void chooseDay() {
            int number_day = 31;
            if (!tv_month.getText().equals(tx_month)) {
                int month = Integer.parseInt(tv_month.getText().toString());
                int year = 0;
                if (!tv_year.getText().equals(tx_year))
                    year = Integer.parseInt(tv_year.getText().toString());
                switch (month) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        number_day = 31;
                        break;
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                        number_day = 30;
                        break;
                    case 2:
                        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))
                            number_day = 29;
                        else number_day = 28;
                        break;
                }
            }
            list_day = new String[number_day];
            for (int i = 1; i <= number_day; i++)
                list_day[i - 1] = String.valueOf(i);

            AlertDialog dialog;
            AlertDialog.Builder builder = new AlertDialog.Builder(Register.this)
                    .setTitle(tx_day);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, list_day);
            builder.setSingleChoiceItems(adapter, posDay, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    tv_day.setText(list_day[which]);
                    posDay = which;
                    dialog.dismiss();
                }
            });
            dialog = builder.create();
            dialog.show();
        }

        private void chooseMonth() {
            list_month = new String[12];
            for (int i = 1; i < 13; i++)
                list_month[i - 1] = String.valueOf(i);

            AlertDialog dialog;
            AlertDialog.Builder builder = new AlertDialog.Builder(Register.this)
                    .setTitle(tx_month);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, list_month);
            builder.setSingleChoiceItems(adapter, posMonth, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    tv_month.setText(list_month[which]);
                    posMonth = which;

                    switch (which + 1) {
                        case 4:
                        case 6:
                        case 9:
                        case 11:
                            if (posDay == 30) {
                                tv_day.setText("1");
                                posDay = 0;
                            }
                            break;
                        case 2:
                            int year_s = year - 100;
                            if (!tv_year.getText().equals(tx_year))
                                year_s = Integer.parseInt(tv_year.getText().toString());
                            if (year_s % 400 == 0 || (year_s % 4 == 0 && year_s % 100 != 0)) {
                                if (posDay > 28) {
                                    tv_day.setText("1");
                                    posDay = 0;
                                }
                            } else {
                                if (posDay > 27) {
                                    tv_day.setText("1");
                                    posDay = 0;
                                }
                            }
                            break;
                    }

                    dialog.dismiss();
                }
            });
            dialog = builder.create();
            dialog.show();
        }

        private void chooseYear() {
            list_year = new String[101];

            for (int i = year - 100; i < year + 1; i++)
                list_year[i - year + 100] = String.valueOf(i);

            AlertDialog dialog;
            AlertDialog.Builder builder = new AlertDialog.Builder(Register.this)
                    .setTitle(tx_year);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, list_year);
            builder.setSingleChoiceItems(adapter, posYear, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    tv_year.setText(list_year[which]);
                    posYear = which;
                    int y = Integer.parseInt(list_year[which]);
                    int m = 1;
                    if (!tv_month.getText().equals(tx_month))
                        m = Integer.parseInt(tv_month.getText().toString());

                    int d = 1;
                    if (!tv_day.getText().equals(tx_day))
                        d = Integer.parseInt(tv_day.getText().toString());

                    if (!(y % 400 == 0 || (y % 4 == 0 && y % 100 != 0)) && m == 2)
                        if (d == 29) {
                            tv_day.setText("1");
                            posDay = 0;
                        }
                    dialog.dismiss();
                }
            });
            dialog = builder.create();
            dialog.show();
        }

        public void continue_add_profile() {
            if (et_name_email.getText().toString().trim().length() == 0 || et_account_email.getText().toString().trim().length() == 0 ||
                    et_password_email.getText().toString().trim().length() == 0 || et_submit_password_email.getText().toString().trim().length() == 0 || !checkInfo) {
                Snackbar.make(main_layout_id, enter_full_info, Snackbar.LENGTH_SHORT).show();
                if (et_name_email.getText().toString().trim().length() == 0) {
                    tv_enter_name.setVisibility(View.VISIBLE);
                    tv_enter_name.setText(can_not_null);
                    view_1.setBackgroundColor(getResources().getColor(R.color.colorTextRed));
                }
                if (et_account_email.getText().length() == 0) {
                    tv_enter_email.setVisibility(View.VISIBLE);
                    tv_enter_email.setText(can_not_null);
                    view_2.setBackgroundColor(getResources().getColor(R.color.colorTextRed));
                }
                if (et_password_email.getText().length() == 0) {
                    tv_enter_password.setVisibility(View.VISIBLE);
                    tv_enter_password.setText(can_not_null);
                    view_3.setBackgroundColor(getResources().getColor(R.color.colorTextRed));
                }
                if (et_submit_password_email.getText().length() == 0) {
                    tv_enter_submit_password.setVisibility(View.VISIBLE);
                    tv_enter_submit_password.setText(can_not_null);
                    view_4.setBackgroundColor(getResources().getColor(R.color.colorTextRed));
                }
                return;
            }
            String query = "email=" + et_account_email.getText().toString() + "&password=" + et_password_email.getText().toString();
            SigninHelper signinHelper = new SigninHelper(GlobalHelper.REQUEST_SIGNIN_EMAIL_URL, onPreCheck, onPostCheck);
            signinHelper.signinAccount(query);
        }

        public void update_profile() {
            //sign_in = 1;
            String query = "name=" + et_name_email.getText().toString().trim() + "&email=" + et_account_email.getText().toString().trim() +
                    "&password=" + et_password_email.getText().toString();
            RegisterHelper registerHelper = new RegisterHelper(onPreRegister, onPostRegister);
            registerHelper.registerAccount(query);

        }

    public CallBack onPreCheck = new CallBack() {
        @Override
        public void execute() {
            btn_continue.setVisibility(View.GONE);
            pb_continue.setVisibility(View.VISIBLE);
        }
    };

    public AccountCallBack onPostCheck = new AccountCallBack() {
        @Override
        public void execute(String json) {
            pb_continue.setVisibility(View.GONE);
            btn_continue.setVisibility(View.VISIBLE);
            GlobalHelper.hideKeyboard(Register.this);
            if (json != null) {
                UserProfile userProfile;
                try {
                    userProfile = new Gson().fromJson(json, UserProfile.class);
                } catch (JsonSyntaxException e) {
                    userProfile = null;
                }
                if (userProfile != null && userProfile.getErr() == null) {
                    preferenceHelper.setProfile(json);
//                    SignoutHelper signoutHelper = new SignoutHelper(getApplicationContext(), onBackExecute);
//                    signoutHelper.signoutAccount();
                    Snackbar.make(main_layout_id, email_already_exists, Snackbar.LENGTH_SHORT).show();
                } else if (!json.contains("data")) {
                    Snackbar.make(main_layout_id, email_already_exists, Snackbar.LENGTH_SHORT).show();
                } else {
                    add_profile_layout.setVisibility(View.GONE);
                    update_profile_layout.setVisibility(View.VISIBLE);
                }
            } else {
                if (new CheckConnect(getApplicationContext()).checkInternetConnection())
                    Snackbar.make(main_layout_id, something_wrong, Snackbar.LENGTH_SHORT).show();
                else
                    Snackbar.make(main_layout_id, no_internet, Snackbar.LENGTH_SHORT).show();
            }
        }
    };

    public CallBack onBackExecute = new CallBack() {
        @Override
        public void execute() {
        }
    };

    public CallBack onPreRegister = new CallBack() {
        @Override
        public void execute() {
            btn_finish.setVisibility(View.GONE);
            pb_finish.setVisibility(View.VISIBLE);
        }
    };

    public AccountCallBack onPostRegister = new AccountCallBack() {
        @Override
        public void execute(String json) {
            UserProfile userProfile = new Gson().fromJson(json, UserProfile.class);
            if (json != null) {
                if (userProfile.getErr() == null) {
                    preferenceHelper.setProfile(json);
                    if (!tv_level.getText().equals(choose))
                        level_jap = tv_level.getText().toString();
                    if (!tv_sex.getText().equals(choose))
                        sex = posSex;
                    song   = et_song_favorite.getText().toString();
                    singer = et_singer_favorite.getText().toString();
                    more   = et_more_profile.getText().toString();
                    year_of_birth = tv_year.getText().equals(tx_year) ? String.valueOf(year - 100) : tv_year.getText().toString();
                    month_of_birth = tv_month.getText().equals(tx_month) ? "1" : tv_month.getText().toString();
                    day_of_birth = tv_day.getText().equals(tx_day) ? "1" : tv_day.getText().toString();
                    String query = "data={\"level_jap\" : \"" + level_jap + "\", \"sex\": \"" + sex +
                            "\", \"year_of_birth\" : \"" + year_of_birth + "\", \"month_of_birth\" : \"" +
                            month_of_birth + "\", \"day_of_birth\" : \"" + day_of_birth +
                            "\", \"song_favorite\" : \"" + et_song_favorite.getText().toString() +
                            "\", \"singer_favorite\" : \"" + et_singer_favorite.getText().toString() +
                            "\", \"more_profile\" : \"" + et_more_profile.getText().toString() + "\"}";
                    UpdateProfileHelper updateProfileHelper = new UpdateProfileHelper(getApplicationContext(), onPreUpdate, onPostUpdate);
                    updateProfileHelper.updateAccount(query);
                } else if (userProfile.getErr().getMessage().equals("Not correct!")) {
                    String query = "email=" + et_account_email.getText().toString() + "&password=" + et_password_email.getText().toString();
                    SigninHelper signinHelper = new SigninHelper(GlobalHelper.REQUEST_SIGNIN_EMAIL_URL, onPreCheck, onRegistter);
                    signinHelper.signinAccount(query);
                } else {
                    btn_finish.setVisibility(View.VISIBLE);
                    pb_finish.setVisibility(View.GONE);
                    Snackbar.make(main_layout_id, register_error, Snackbar.LENGTH_SHORT).show();
                }
            } else {
                btn_finish.setVisibility(View.VISIBLE);
                pb_finish.setVisibility(View.GONE);
                Snackbar.make(main_layout_id, register_error, Snackbar.LENGTH_SHORT).show();
            }
        }

    };

    public AccountCallBack onRegistter = new AccountCallBack() {
        @Override
        public void execute(String json) {
            if (json != null) {
                UserProfile userProfile = new Gson().fromJson(json, UserProfile.class);
                if (userProfile.getErr() == null) {
                    preferenceHelper.setProfile(json);
                    if (!tv_level.getText().equals(choose))
                        level_jap = tv_level.getText().toString();
                    if (!tv_sex.getText().equals(choose))
                        sex = posSex;
                    song = et_song_favorite.getText().toString();
                    singer = et_singer_favorite.getText().toString();
                    more = et_more_profile.getText().toString();
                    year_of_birth = tv_year.getText().equals(tx_year) ? String.valueOf(year - 100) : tv_year.getText().toString();
                    month_of_birth = tv_month.getText().equals(tx_month) ? "1" : tv_month.getText().toString();
                    day_of_birth = tv_day.getText().equals(tx_day) ? "1" : tv_day.getText().toString();
                    String query = "data={\"level_jap\" : \"" + level_jap + "\", \"sex\": \"" + sex +
                            "\", \"year_of_birth\" : \"" + year_of_birth + "\", \"month_of_birth\" : \"" +
                            month_of_birth + "\", \"day_of_birth\" : \"" + day_of_birth +
                            "\", \"song_favorite\" : \"" + et_song_favorite.getText().toString() +
                            "\", \"singer_favorite\" : \"" + et_singer_favorite.getText().toString() +
                            "\", \"more_profile\" : \"" + et_more_profile.getText().toString() + "\"}";
                    UpdateProfileHelper updateProfileHelper = new UpdateProfileHelper(getApplicationContext(), onPreUpdate, onPostUpdate);
                    updateProfileHelper.updateAccount(query);
                }
            }
        }
    };

    public CallBack onPreUpdate = new CallBack() {
        @Override
        public void execute() {
        }
    };

    public AccountCallBack onPostUpdate = new AccountCallBack() {
        @Override
        public void execute(String json) {
            btn_finish.setVisibility(View.VISIBLE);
            pb_finish.setVisibility(View.GONE);
            UserProfile userProfile = new Gson().fromJson(json, UserProfile.class);
            if (userProfile.getErr() == null) {
                String data = preferenceHelper.getProfile();
                UserProfile userUpdate = new Gson().fromJson(data, UserProfile.class);
                if (song.length() > 0) userUpdate.getUser().setSongFavorite(song);
                if (singer.length() > 0) userUpdate.getUser().setSingerFavorite(singer);
                if (more.length() > 0) userUpdate.getUser().setMoreProfile(more);
                userUpdate.getUser().setSex(sex);
                userUpdate.getUser().setDayOfBirth(Integer.valueOf(day_of_birth));
                userUpdate.getUser().setMonthOfBirth(Integer.valueOf(month_of_birth));
                userUpdate.getUser().setYearOfBirth(Integer.valueOf(year_of_birth));
                if (posLV > -1) userUpdate.getUser().setLevelJap(level_jap);
                preferenceHelper.setProfile(new Gson().toJson(userUpdate));
            }
            preferenceHelper.setSignin(1);
            EventBus.getDefault().post(new EventSigninHelper(EventSigninHelper.StateChange.REGISTER));
            finish();
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
            if (et_name_email.isFocused()) {
                if (et_name_email.getText().toString().trim().length() == 0) {
                    tv_enter_name.setVisibility(View.VISIBLE);
                    tv_enter_name.setText(can_not_null);
                    view_1.setBackgroundColor(getResources().getColor(R.color.colorTextRed));
                    checkInfo = false;
                } else {
                    checkInfo = true;
                    view_1.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    tv_enter_name.setVisibility(View.INVISIBLE);
                }
            } else if (et_account_email.isFocused()) {
                if (et_account_email.getText().toString().length() == 0) {
                    checkInfo = false;
                    tv_enter_email.setVisibility(View.VISIBLE);
                    tv_enter_email.setText(can_not_null);
                    view_2.setBackgroundColor(getResources().getColor(R.color.colorTextRed));
                } else if (!validate(et_account_email.getText().toString().trim())) {
                    checkInfo = false;
                    tv_enter_email.setVisibility(View.VISIBLE);
                    tv_enter_email.setText(getResources().getString(R.string.invalid_email));
                    view_2.setBackgroundColor(getResources().getColor(R.color.colorTextRed));
                } else {
                    checkInfo = true;
                    tv_enter_email.setVisibility(View.INVISIBLE);
                    view_2.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                }
            } else if (et_password_email.isFocused()) {
                if (et_submit_password_email.getText().toString().equals(et_password_email.getText().toString())) {
                    tv_enter_submit_password.setVisibility(View.INVISIBLE);
                    view_4.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                }
                if (et_password_email.getText().toString().length() == 0) {
                    checkInfo = false;
                    tv_enter_password.setVisibility(View.VISIBLE);
                    tv_enter_password.setText(can_not_null);
                    view_3.setBackgroundColor(getResources().getColor(R.color.colorTextRed));
                } else if (et_password_email.getText().toString().length() < 6) {
                    checkInfo = false;
                    tv_enter_password.setVisibility(View.VISIBLE);
                    tv_enter_password.setText(getResources().getString(R.string.invalid_password));
                    view_3.setBackgroundColor(getResources().getColor(R.color.colorTextRed));
                } else {
                    checkInfo = true;
                    tv_enter_password.setVisibility(View.INVISIBLE);
                    view_3.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                }
            } else if (et_submit_password_email.isFocused()) {
                if (et_submit_password_email.getText().toString().length() == 0) {
                    if (et_password_email.getText().toString().length() == 0) {
                        tv_enter_submit_password.setVisibility(View.INVISIBLE);
                        view_4.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    } else {
                        checkInfo = false;
                        tv_enter_submit_password.setVisibility(View.VISIBLE);
                        tv_enter_submit_password.setText(can_not_null);
                        view_4.setBackgroundColor(getResources().getColor(R.color.colorTextRed));
                    }
                } else if (!et_submit_password_email.getText().toString().equals(et_password_email.getText().toString())) {
                    checkInfo = false;
                    tv_enter_submit_password.setVisibility(View.VISIBLE);
                    tv_enter_submit_password.setText(getResources().getString(R.string.match_submit_password));
                    view_4.setBackgroundColor(getResources().getColor(R.color.colorTextRed));
                } else {
                    checkInfo = true;
                    tv_enter_submit_password.setVisibility(View.INVISIBLE);
                    view_4.setBackgroundColor(getResources().getColor(R.color.colorWhite));
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
