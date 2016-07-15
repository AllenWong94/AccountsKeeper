package com.android.accounts_keeper_android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LauncherSplash extends Activity {
    private static final int LOGO_ANIMATION = 0;
    private static final int WELCOME_ANIMATION = 2000;
    private static final int SPLASH_DELAY_LENGTH = 3000;
    private static final int TO_LOGIN = 273;
    private static final int TO_MAIN = 357;

    public LinearLayout logo;
    public TextView welcome;
    public Animation transition, alpha;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case LOGO_ANIMATION:
                    logo.startAnimation(transition);
                    break;
                case WELCOME_ANIMATION:
                    welcome.setVisibility(View.VISIBLE);
                    welcome.startAnimation(alpha);
                    break;
                case TO_LOGIN:
                    Intent it = new Intent(LauncherSplash.this, LoginActivity.class);
                    startActivity(it);
                    LauncherSplash.this.finish();
                    break;
                case TO_MAIN:
                    it = new Intent(LauncherSplash.this, MainActivity.class);
                    startActivity(it);
                    LauncherSplash.this.finish();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher_splash);

        logo = (LinearLayout)findViewById(R.id.splash_logo);
        welcome = (TextView)findViewById(R.id.splash_welcome);
        transition = AnimationUtils.loadAnimation(this, R.anim.splash);
        alpha = AnimationUtils.loadAnimation(this, R.anim.splash2);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean login = sharedPreferences.getBoolean("login", false);

        Message msg = new Message();
        msg.what = LOGO_ANIMATION;
        handler.sendMessageDelayed(msg, LOGO_ANIMATION);

        msg = new Message();
        msg.what = WELCOME_ANIMATION;
        handler.sendMessageDelayed(msg, WELCOME_ANIMATION);

        msg = new Message();
        if (login)
            msg.what = TO_MAIN;
        else
            msg.what = TO_LOGIN;
        handler.sendMessageDelayed(msg, SPLASH_DELAY_LENGTH);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler = null;
    }
}
