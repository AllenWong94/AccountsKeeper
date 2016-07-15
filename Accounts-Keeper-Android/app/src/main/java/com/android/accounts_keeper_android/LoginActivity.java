package com.android.accounts_keeper_android;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    public EditText user, password;
    public TextView error, new_user;
    public MongoDB db;
    public ProgressDialog progressDialog;

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Utils.LOGIN_REGISTER_ERROR:
                    progressDialog.dismiss();
                    error.setVisibility(View.VISIBLE);
                    error.setText(msg.obj.toString());
                    break;
                case Utils.LOGIN_REGISTER_SUCCESS:
                    // Remember accounts and password
                    Utils.saveLoginField(getApplicationContext(), user.getText().toString(), password.getText().toString());
                    // Remember login status
                    Utils.saveLoginStatus(getApplicationContext());

                    progressDialog.dismiss();
                    Intent it = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(it);
                    LoginActivity.this.finish();
                    break;
                case Utils.INTERNET_ERROR:
                    progressDialog.dismiss();
                    error.setVisibility(View.VISIBLE);
                    error.setText(msg.obj.toString());
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new MongoDB(handler);

        final Button login = (Button)findViewById(R.id.login_login);
        user = (EditText)findViewById(R.id.login_user);
        password = (EditText)findViewById(R.id.login_pwd);
        error = (TextView)findViewById(R.id.login_error);
        new_user = (TextView)findViewById(R.id.login_register);
        progressDialog = Utils.progressDialog(LoginActivity.this, "登陆中，请稍后");

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        user.setText(sharedPreferences.getString("username", ""));
        password.setText(sharedPreferences.getString("password", ""));

        login.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = user.getText().toString(),
                        pwd = password.getText().toString();

                if (username.isEmpty() || pwd.isEmpty())
                    Toast.makeText(LoginActivity.this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                else if (username.matches("[^0-9a-zA-Z_]") || username.length() < 4 || username.length() > 16) {
                    error.setVisibility(View.VISIBLE);
                    error.setText("请输入合法的用户名！（数字、字母，至少4个，至多16个字符。");
                } else {
                    progressDialog.show();
                    db.authUser(username, pwd);
                }
            }
        });

        new_user.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(it);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler = null;
        db = null;
    }
}
