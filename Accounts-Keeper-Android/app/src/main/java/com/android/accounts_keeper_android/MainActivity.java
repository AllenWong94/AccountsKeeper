package com.android.accounts_keeper_android;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    public String username, nickname, email;

    // Tab: Account
    public TextView date;
    public ImageView calendar, statistics;
    public MaterialCalendarView calendarView;
    public ListView listView;
    public AccountsItemAdapter adapter;
    public static SQLiteDBHelper dbHelper;

    // Tab: Add
    public LinearLayout addPlane;

    // Tab: User
    public TextView user_nickname;

    // Global
    public ImageView account, add, user;
    public RelativeLayout accountLayout, addLayout, userLayout;
    public boolean calendarViewActive, addPlaneActive;
    public boolean backPressedTwice;

    public ProgressDialog progressDialog;
    public AlertDialog alertDialog;
    public MongoDB mongoDB;
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Utils.UPDATE_NICKNAME_SUCCESS:
                    progressDialog.dismiss();
                    alertDialog.dismiss();
                    nickname = msg.obj.toString();
                    user_nickname.setText(nickname);
                    break;
                case Utils.GET_USERINFO_SUCCESS:
                    try {
                        JSONObject json = new JSONObject(msg.obj.toString());
                        nickname = json.getString("nickname");
                        email = json.getString("email");
                        user_nickname.setText(nickname);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case Utils.GET_USERINFO_FAIL:
                    mongoDB.getUserInfo(username);
                    break;
                case Utils.INTERNET_ERROR:
                    if (progressDialog != null && progressDialog.isShowing())
                        progressDialog.dismiss();
                    if (alertDialog != null && alertDialog.isShowing())
                        alertDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "您的网络好像出了点问题，请检查！", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //************************ Global
        account = (ImageView)findViewById(R.id.main_img_account);
        accountLayout = (RelativeLayout)findViewById(R.id.main_account); // layout
        add = (ImageView)findViewById(R.id.main_img_add);
        addLayout = (RelativeLayout)findViewById(R.id.main_add);
        user = (ImageView)findViewById(R.id.main_img_user);
        userLayout = (RelativeLayout)findViewById(R.id.main_user);

        mongoDB = new MongoDB(handler);
        backPressedTwice = false;
        username = Utils.getUsername(getApplicationContext());
        mongoDB.getUserInfo(username);

        //************************* Tab: Accounts Part
        date = (TextView)findViewById(R.id.main_date);
        date.setText(Utils.formatDate(Utils.getToday()));
        // Calendar
        calendar = (ImageView)findViewById(R.id.main_img_calendar);
        calendarView = (MaterialCalendarView)findViewById(R.id.main_account_calendar);
        calendarView.setSelectedDate(Utils.getToday());
        calendarView.setMaximumDate(Utils.getToday());
        // Statistics
        statistics = (ImageView)findViewById(R.id.main_img_statistics);
        // Main Content - Accounts
        listView = (ListView)findViewById(R.id.main_account_listview);
        dbHelper = new SQLiteDBHelper(getApplicationContext());
        adapter = new AccountsItemAdapter(getApplicationContext(), R.layout.item_accounts_listview,
                dbHelper.getAdapterSource(dbHelper.queryAccordingDate(username, Utils.formatDate(Utils.getToday()))));
        listView.setAdapter(adapter);

        // Status
        calendarViewActive = false;

        //********* Events
        account.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                account.setImageDrawable(getResources().getDrawable(R.drawable.ic_account));
                add.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_gray));
                user.setImageDrawable(getResources().getDrawable(R.drawable.ic_user_gray));
                accountLayout.setVisibility(View.VISIBLE);
                addLayout.setVisibility(View.GONE);
                userLayout.setVisibility(View.GONE);
            }
        });

        calendar.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!calendarViewActive) {
                    calendarView.setVisibility(View.VISIBLE);
                    Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.calendar);
                    calendarView.startAnimation(animation);
                    calendarViewActive = true;
                } else {
                    Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.calendar2);
                    calendarView.startAnimation(animation);
                    calendarView.setVisibility(View.GONE);
                    calendarViewActive = false;
                }
            }
        });

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView materialCalendarView, @NonNull CalendarDay calendarDay, boolean b) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.calendar2);
                calendarView.startAnimation(animation);
                calendarView.setVisibility(View.GONE);
                calendarViewActive = false;
                String timeStamp = Utils.formatDate(calendarDay.getDate());
                date.setText(timeStamp);
                adapter = new AccountsItemAdapter(getApplicationContext(), R.layout.item_accounts_listview,
                        dbHelper.getAdapterSource(dbHelper.queryAccordingDate(username, timeStamp)));
                listView.setAdapter(adapter);
            }
        });

        //************************ Tab: Add Items
        addPlane = (LinearLayout)findViewById(R.id.main_add_anim);
        addPlaneActive = false;

        final ImageView add_food = (ImageView) findViewById(R.id.main_add_food);
        final ImageView add_fruits = (ImageView) findViewById(R.id.main_add_fruits);
        final ImageView add_necessary = (ImageView) findViewById(R.id.main_add_necessary);
        final ImageView add_school_thing = (ImageView) findViewById(R.id.main_add_schoolthings);
        final ImageView add_note = (ImageView) findViewById(R.id.main_add_note);
        final ImageView add_cancel = (ImageView) findViewById(R.id.main_add_cancel);

        add.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                // UI
                account.setImageDrawable(getResources().getDrawable(R.drawable.ic_account_gray));
                add.setImageDrawable(getResources().getDrawable(R.drawable.ic_add));
                user.setImageDrawable(getResources().getDrawable(R.drawable.ic_user_gray));
                accountLayout.setVisibility(View.GONE);
                addLayout.setVisibility(View.VISIBLE);
                userLayout.setVisibility(View.GONE);
                // Show Choices Buttons
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.add_up);
                addPlane.startAnimation(animation);
                addPlaneActive = true;
            }
        });

        ImageView.OnClickListener listener = new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.main_add_food:
                        Utils.updateAccount(MainActivity.this, username, date.getText().toString(), "食物");
                        break;
                    case R.id.main_add_fruits:
                        Utils.updateAccount(MainActivity.this, username, date.getText().toString(), "水果");
                        break;
                    case R.id.main_add_necessary:
                        Utils.updateAccount(MainActivity.this, username, date.getText().toString(), "生活用品");
                        break;
                    case R.id.main_add_schoolthings:
                        Utils.updateAccount(MainActivity.this, username, date.getText().toString(), "学习用品");
                        break;
                    case R.id.main_add_note:
                        Utils.updateAccount(MainActivity.this, username, date.getText().toString(), "备忘");
                        break;
                    case R.id.main_add_cancel:
                        // Update changed data
                        adapter = new AccountsItemAdapter(getApplicationContext(), R.layout.item_accounts_listview,
                                dbHelper.getAdapterSource(dbHelper.queryAccordingDate(username, date.getText().toString())));
                        listView.setAdapter(adapter);

                        // Return Accounts
                        account.setImageDrawable(getResources().getDrawable(R.drawable.ic_account));
                        add.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_gray));
                        addLayout.setVisibility(View.GONE);
                        accountLayout.setVisibility(View.VISIBLE);
                        addPlaneActive = false;
                        break;
                }
            }
        };

        add_food.setOnClickListener(listener);
        add_fruits.setOnClickListener(listener);
        add_necessary.setOnClickListener(listener);
        add_school_thing.setOnClickListener(listener);
        add_note.setOnClickListener(listener);
        add_cancel.setOnClickListener(listener);

        //*********************** Tab: User Info
        final Button logout = (Button)findViewById(R.id.main_user_logout);
        user_nickname = (TextView)findViewById(R.id.main_user_name);
        TextView update_nickname = (TextView)findViewById(R.id.main_user_update_nickname);
        TextView update_password = (TextView)findViewById(R.id.main_user_update_password);

        user.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                account.setImageDrawable(getResources().getDrawable(R.drawable.ic_account_gray));
                add.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_gray));
                user.setImageDrawable(getResources().getDrawable(R.drawable.ic_user));
                accountLayout.setVisibility(View.GONE);
                addLayout.setVisibility(View.GONE);
                userLayout.setVisibility(View.VISIBLE);
            }
        });

        update_nickname.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View l = LayoutInflater.from(MainActivity.this).inflate(R.layout.update_dialog, null);
                TextView label = (TextView)l.findViewById(R.id.update_dialog_label);
                final EditText contents = (EditText)l.findViewById(R.id.update_dialog_content);
                label.setText("新的昵称：");

                alertDialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("更新昵称")
                        .setView(l)
                        .setPositiveButton("确认", null)
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();

                alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {

                        Button confirm = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                        confirm.setOnClickListener(new Button.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String nick = contents.getText().toString();
                                if (nick.isEmpty())
                                    Toast.makeText(getApplicationContext(), "请输入您的新昵称", Toast.LENGTH_SHORT).show();
                                else {
                                    progressDialog = Utils.progressDialog(MainActivity.this, "更改中，请稍候！");
                                    mongoDB.changeNickname(getApplicationContext(), nick);
                                }
                            }
                        });
                    }
                });

                alertDialog.show();
            }
        });

        update_password.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, UpdatePassword.class);
                startActivity(it);
            }
        });

        logout.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.removeLoginStatus(getApplicationContext());
                Intent it = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(it);
                MainActivity.this.finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (backPressedTwice) {
            super.onBackPressed();
        } else if (calendarViewActive) {
            Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.calendar2);
            calendarView.startAnimation(animation);
            calendarView.setVisibility(View.GONE);
            calendarViewActive = false;
        } else if (addPlaneActive) {
            account.setImageDrawable(getResources().getDrawable(R.drawable.ic_account));
            add.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_gray));
            addLayout.setVisibility(View.GONE);
            accountLayout.setVisibility(View.VISIBLE);
            addPlaneActive = false;
        } else {
            Toast.makeText(getApplicationContext(), "再按一次退出应用", Toast.LENGTH_SHORT).show();
            backPressedTwice = true;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    backPressedTwice = false;
                }
            }, 2000);
        }
    }

    @Override
         protected void onDestroy() {
        super.onDestroy();
        mongoDB = null;
        handler = null;
    }
}
