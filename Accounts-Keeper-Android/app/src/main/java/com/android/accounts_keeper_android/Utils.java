package com.android.accounts_keeper_android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Utilities.
 */
public class Utils {
    public static final int LOGIN_REGISTER_ERROR = 344;
    public static final int LOGIN_REGISTER_SUCCESS = 336;
    public static final int UPDATE_NICKNAME_SUCCESS = 990;
    public static final int UPDATE_PASSWORD_FAIL = 248;
    public static final int UPDATE_PASSWORD_SUCCESS = 391;
    public static final int GET_USERINFO_FAIL = 728;
    public static final int GET_USERINFO_SUCCESS = 914;

    public static final int INTERNET_ERROR = 958;

    public static void saveLoginField(Context ctx, String username, String password) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.apply();
    }

    // Used to remember user's status
    public static void saveLoginStatus(Context ctx) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("login", true);
        editor.apply();
    }

    public static String getUsername(Context ctx) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        return sharedPreferences.getString("username", "");
    }

    // Used when user logout
    public static void removeLoginStatus(Context ctx) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("login", false);
        editor.apply();
    }

    public static ProgressDialog progressDialog(Activity activity, String msg) {
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setTitle("记账大师");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(msg);
        progressDialog.setCancelable(false);
        return progressDialog;
    }

    public static Date getToday() {
        return CalendarDay.today().getDate();
    }
    
    public static String formatDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        return simpleDateFormat.format(date);
    }

    public static void updateAccount(final Context ctx, final String username, final String date, final String which) {
        final View l = LayoutInflater.from(ctx).inflate(R.layout.update_dialog, null);
        String title;

        if (which == "备忘")
            title = "修改备忘：" + date;
        else
            title = "增加" + which + "金额：" + date;

        final AlertDialog ad = new AlertDialog.Builder(ctx)
                .setTitle(title)
                .setView(l)
                .setPositiveButton("确认", null)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();

        ad.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                TextView label = (TextView)l.findViewById(R.id.update_dialog_label);
                final EditText contents = (EditText)l.findViewById(R.id.update_dialog_content);
                label.setText(which);

                Button confirm = ad.getButton(DialogInterface.BUTTON_POSITIVE);
                confirm.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String cost = contents.getText().toString();

                        if (which.equals("备忘")) {
                            MainActivity.dbHelper.updateAccordingZh(which, username, date, cost);
                            ad.dismiss();
                        } else {
                            if (cost.matches("[^\\d\\.-]+"))
                                Toast.makeText(ctx, "请输入正确的金额", Toast.LENGTH_SHORT).show();
                            else {
                                MainActivity.dbHelper.updateAccordingZh(which, username, date, cost);
                                ad.dismiss();
                            }
                        }
                    }
                });
            }
        });

        ad.show();

    }
}
