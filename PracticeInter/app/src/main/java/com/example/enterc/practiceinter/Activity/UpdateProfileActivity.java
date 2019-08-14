package com.example.enterc.practiceinter.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.enterc.practiceinter.Listener.AccountCallBack;
import com.example.enterc.practiceinter.Listener.CallBack;
import com.example.enterc.practiceinter.Model.Helper.CheckConnect;
import com.example.enterc.practiceinter.Model.Helper.EventSigninHelper;
import com.example.enterc.practiceinter.Model.Helper.GlobalHelper;
import com.example.enterc.practiceinter.Model.Helper.PreferenceHelper;
import com.example.enterc.practiceinter.Model.Helper.UpdateProfileHelper;
import com.example.enterc.practiceinter.Model.user.UserProfile;
import com.example.enterc.practiceinter.R;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateProfileActivity extends AppCompatActivity {

    @BindView(R.id.tool_bar)
    Toolbar toolbar;
    @BindView(R.id.main_layout_id)
    CoordinatorLayout main_layout_id;
    @BindView(R.id.content)
    RelativeLayout content;
    @BindView(R.id.update_profile_layout)
    LinearLayout update_profile_layout;
    @BindView(R.id.layout_change)
    RelativeLayout layout_change;

    @BindView(R.id.tv_day)
    TextView tv_day;
    @BindView(R.id.tv_month)
    TextView tv_month;
    @BindView(R.id.tv_year)
    TextView tv_year;

    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_song_favorite)
    EditText et_song_favorite;
    @BindView(R.id.et_singer_favorite)
    EditText et_singer_favorite;
    @BindView(R.id.et_more_profile)
    EditText et_more_profile;
    @BindView(R.id.btn_save)
    TextView btn_save;
    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;

    @BindView(R.id.layout_n1)
    RelativeLayout layout_n1;
    @BindView(R.id.layout_n2)
    RelativeLayout layout_n2;
    @BindView(R.id.layout_n3)
    RelativeLayout layout_n3;
    @BindView(R.id.layout_n4)
    RelativeLayout layout_n4;
    @BindView(R.id.layout_n5)
    RelativeLayout layout_n5;

    @BindView(R.id.iv_check_n1)
    ImageView iv_check_n1;
    @BindView(R.id.iv_check_n2)
    ImageView iv_check_n2;
    @BindView(R.id.iv_check_n3)
    ImageView iv_check_n3;
    @BindView(R.id.iv_check_n4)
    ImageView iv_check_n4;
    @BindView(R.id.iv_check_n5)
    ImageView iv_check_n5;

    @BindView(R.id.layout_male)
    RelativeLayout layout_male;
    @BindView(R.id.layout_female)
    RelativeLayout layout_female;

    @BindView(R.id.iv_check_male)
    ImageView iv_check_male;
    @BindView(R.id.iv_check_female)
    ImageView iv_check_female;

    @BindString(R.string.no_internet)
    String no_internet;
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

    @BindString(R.string.n1)
    String N1;
    @BindString(R.string.n2)
    String N2;
    @BindString(R.string.n3)
    String N3;
    @BindString(R.string.n4)
    String N4;
    @BindString(R.string.n5)
    String N5;

    private PreferenceHelper preferenceHelper;
    private String name, song, singer, more;
    private String level_jap = "";
    private String day_of_birth = "";
    private String month_of_birth = "";
    private String year_of_birth = "";
    private int posSex = 0;
    private String[] list_day, list_month, list_year;
    private int posDay = 0, posMonth = 0, posYear = 0;
    private int year;
    private String name_first;
    private int CHANGE_PASSWORD = 96;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        preferenceHelper = new PreferenceHelper(getApplicationContext(), GlobalHelper.PREFERENCE_NAME_PIKA);
        ButterKnife.bind(this);
        initUI();
    }
    private void initUI() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (preferenceHelper.getSignin() == 1)
            layout_change.setVisibility(View.VISIBLE);
        else layout_change.setVisibility(View.GONE);

        String json = preferenceHelper.getProfile();
        UserProfile userProfile = new Gson().fromJson(json, UserProfile.class);

        if (userProfile.getUser().getLevelJap() == null)
            userProfile.getUser().setLevelJap("");

        String lvJLPT = userProfile.getUser().getLevelJap();
        switch (lvJLPT) {
            case "N1":
                showCheckJLPT(1);
                break;
            case "N2":
                showCheckJLPT(2);
                break;
            case "N3":
                showCheckJLPT(3);
                break;
            case "N4":
                showCheckJLPT(4);
                break;
            case "N5":
                showCheckJLPT(5);
                break;
        }

        if (userProfile.getUser().getSex() == null)
            userProfile.getUser().setSex(0);

        switch (userProfile.getUser().getSex()) {
            case 1:
                showCheckSex(1);
                break;
            case 2:
                showCheckSex(2);
                break;
        }

        year = Calendar.getInstance().get(Calendar.YEAR);
        if (userProfile.getUser().getDayOfBirth() != null && userProfile.getUser().getMonthOfBirth() != null &&
                userProfile.getUser().getYearOfBirth() != null) {
            posDay = userProfile.getUser().getDayOfBirth() - 1;
            tv_day.setText(String.valueOf(userProfile.getUser().getDayOfBirth()));
            posMonth = userProfile.getUser().getMonthOfBirth() - 1;
            tv_month.setText(String.valueOf(userProfile.getUser().getMonthOfBirth()));
            posYear = userProfile.getUser().getYearOfBirth() - year + 100;
            tv_year.setText(String.valueOf(userProfile.getUser().getYearOfBirth()));
        }

        et_name.setText(userProfile.getUser().getName());
        name_first = userProfile.getUser().getName();
        et_song_favorite.setText(userProfile.getUser().getSongFavorite());
        et_singer_favorite.setText(userProfile.getUser().getSingerFavorite());
        et_more_profile.setText(userProfile.getUser().getMoreProfile());
    }

    @OnClick({R.id.layout_n1, R.id.layout_n2, R.id.layout_n3, R.id.layout_n4, R.id.layout_n5,
            R.id.layout_male, R.id.layout_female,
            R.id.sp_day, R.id.sp_month, R.id.sp_year, R.id.btn_save, R.id.btn_cancel, R.id.layout_change})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_n1:
                showCheckJLPT(1);
                break;
            case R.id.layout_n2:
                showCheckJLPT(2);
                break;
            case R.id.layout_n3:
                showCheckJLPT(3);
                break;
            case R.id.layout_n4:
                showCheckJLPT(4);
                break;
            case R.id.layout_n5:
                showCheckJLPT(5);
                break;
            case R.id.layout_male:
                showCheckSex(1);
                break;
            case R.id.layout_female:
                showCheckSex(2);
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
            case R.id.btn_save:
                update();
                break;
            case R.id.btn_cancel:
                onBackPressed();
                break;
            case R.id.layout_change:
                //Intent intent = new Intent(UpdateProfileActivity.this, ChangePasswordActivity.class);
                //startActivityForResult(intent, CHANGE_PASSWORD);
                break;
        }
    }

    private void chooseDay() {
        int month = 1;
        try {
            month = Integer.parseInt(tv_month.getText().toString());
        } catch (NumberFormatException e) {
            Log.e("error", "NumberFormatException");
        }
        int number_day = 31;
        int year = Calendar.getInstance().get(Calendar.YEAR);
        try {
            year = Integer.parseInt(tv_year.getText().toString());
        } catch (NumberFormatException e) {
            Log.e("error", "NumberFormatException");
        }

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

        list_day = new String[number_day];
        for (int i = 1; i <= number_day; i++)
            list_day[i - 1] = String.valueOf(i);

        AlertDialog dialog;
//        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateProfileActivity.this).setTitle(tx_day);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(tx_day);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, list_day) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                int textColor = GlobalHelper.resolveColorByStyle(getContext(), getResources().getColor(R.color.colorblack), getResources().getColor(R.color.colorBlack_4));
                textView.setTextColor(textColor);
                return textView;
            }
        };
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
//        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateProfileActivity.this)
//                .setTitle(tx_month);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, list_month);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(tx_month);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, list_month) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                int textColor = GlobalHelper.resolveColorByStyle(getContext(), getResources().getColor(R.color.colorBlack_4), getResources().getColor(R.color.colorBlack_4));
                textView.setTextColor(textColor);
                return textView;
            }
        };
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
                        int year = Integer.parseInt(tv_year.getText().toString());
                        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
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
//        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateProfileActivity.this)
//                .setTitle(tx_year);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, list_year);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(tx_year);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, list_year) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                int textColor = GlobalHelper.resolveColorByStyle(getContext(), getResources().getColor(R.color.colorBlack_4), getResources().getColor(R.color.colorBlack_4));
                textView.setTextColor(textColor);
                return textView;
            }
        };
        builder.setSingleChoiceItems(adapter, posYear, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tv_year.setText(list_year[which]);
                posYear = which;

                int y;
                try {
                    y = Integer.parseInt(list_year[which]);
                } catch (NumberFormatException e) {
                    y = 2000;
                    Log.e("error", "NumberFormatException");
                }


                int m;
                try {
                    m = Integer.parseInt(tv_month.getText().toString());
                } catch (NumberFormatException e) {
                    m = 1;
                    Log.e("error", "NumberFormatException");
                }

                if (!(y % 400 == 0 || (y % 4 == 0 && y % 100 != 0)) && m == 2)
                    if (Integer.parseInt(tv_day.getText().toString()) == 29) {
                        tv_day.setText("1");
                        posDay = 0;
                    }

                dialog.dismiss();
            }
        });
        dialog = builder.create();
        dialog.show();
    }

    public void update() {
        name = et_name.getText().toString();
        song = et_song_favorite.getText().toString();
        singer = et_singer_favorite.getText().toString();
        more = et_more_profile.getText().toString();
        year_of_birth = tv_year.getText().equals(tx_year) ? "" : tv_year.getText().toString();
        month_of_birth = tv_month.getText().equals(tx_month) ? "" : tv_month.getText().toString();
        day_of_birth = tv_day.getText().equals(tx_day) ? "" : tv_day.getText().toString();

        String query = "data={\"name\" : \"" + name + "\", \"level_jap\" : \"" + level_jap + "\", \"sex\": \"" + posSex +
                "\", \"year_of_birth\" : \"" + year_of_birth + "\", \"month_of_birth\" : \"" +
                month_of_birth + "\", \"day_of_birth\" : \"" + day_of_birth +
                "\", \"song_favorite\" : \"" + song +
                "\", \"singer_favorite\" : \"" + singer +
                "\", \"more_profile\" : \"" + more + "\"}";
        UpdateProfileHelper updateProfileHelper = new UpdateProfileHelper(getApplicationContext(), onPreUpdate, onPostUpdate);
        updateProfileHelper.updateAccount(query);
    }

    public CallBack onPreUpdate = new CallBack() {
        @Override
        public void execute() {
            btn_save.setVisibility(View.INVISIBLE);
        }
    };
    public AccountCallBack onPostUpdate = new AccountCallBack() {
        @Override
        public void execute(String update) {
            if (update != null) {
                UserProfile userUpdate = new Gson().fromJson(update, UserProfile.class);
                if (userUpdate.getErr() == null) {
                    String json = preferenceHelper.getProfile();
                    UserProfile userProfile = new Gson().fromJson(json, UserProfile.class);
                    if (name.length() > 0) userProfile.getUser().setName(name);
                    if (!name.equals(name_first))
                        FirebaseDatabase.getInstance().getReference().child("user").child(String.valueOf(userProfile.getUser().getId()))
                                .child("name").setValue(name);
                    if (song.length() > 0) userProfile.getUser().setSongFavorite(song);
                    if (singer.length() > 0) userProfile.getUser().setSingerFavorite(singer);
                    if (more.length() > 0) userProfile.getUser().setMoreProfile(more);
                    userProfile.getUser().setSex(posSex);
                    if (day_of_birth.length() > 0)
                        userProfile.getUser().setDayOfBirth(Integer.valueOf(day_of_birth));
                    if (month_of_birth.length() > 0)
                        userProfile.getUser().setMonthOfBirth(Integer.valueOf(month_of_birth));
                    if (year_of_birth.length() > 0)
                        userProfile.getUser().setYearOfBirth(Integer.valueOf(year_of_birth));
                    userProfile.getUser().setLevelJap(level_jap);
                    preferenceHelper.setProfile(new Gson().toJson(userProfile));
                    setResult(RESULT_OK);
                    onBackPressed();
                    EventBus.getDefault().post(new EventSigninHelper(EventSigninHelper.StateChange.SIGNIN));
                } else {
                    setResult(RESULT_CANCELED);
                    onBackPressed();
                }
            } else {
                if (!new CheckConnect(getApplicationContext()).checkInternetConnection())
                    Snackbar.make(main_layout_id, no_internet, Snackbar.LENGTH_SHORT).show();
                else {
                    setResult(RESULT_CANCELED);
                    onBackPressed();
                }
            }
            btn_save.setVisibility(View.VISIBLE);
        }
    };

    private void showCheckJLPT(int i) {
        switch (i) {
            case 1:
                level_jap = N1;
                checkJLPT(View.VISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                break;
            case 2:
                level_jap = N2;
                checkJLPT(View.INVISIBLE, View.VISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                break;
            case 3:
                level_jap = N3;
                checkJLPT(View.INVISIBLE, View.INVISIBLE, View.VISIBLE, View.INVISIBLE, View.INVISIBLE);
                break;
            case 4:
                level_jap = N4;
                checkJLPT(View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.VISIBLE, View.INVISIBLE);
                break;
            case 5:
                level_jap = N5;
                checkJLPT(View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.VISIBLE);
                break;
        }
    }

    private void checkJLPT(int n1, int n2, int n3, int n4, int n5) {
        iv_check_n1.setVisibility(n1);
        iv_check_n2.setVisibility(n2);
        iv_check_n3.setVisibility(n3);
        iv_check_n4.setVisibility(n4);
        iv_check_n5.setVisibility(n5);
    }

    private void showCheckSex(int i) {
        switch (i) {
            case 1:
                posSex = 1;
                checkSex(View.VISIBLE, View.INVISIBLE);
                break;
            case 2:
                posSex = 2;
                checkSex(View.INVISIBLE, View.VISIBLE);
                break;
        }
    }

    private void checkSex(int male, int female) {
        iv_check_male.setVisibility(male);
        iv_check_female.setVisibility(female);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHANGE_PASSWORD) {
            if (resultCode == RESULT_OK) {
                onBackPressed();
            }
        }
    }


}
