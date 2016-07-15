package com.android.accounts_keeper_android;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    public EditText user, password, nickname, email;
    public TextView error;
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
                    progressDialog.dismiss();
                    // Remember login or not
                    Utils.saveLoginField(getApplicationContext(), user.getText().toString(), password.getText().toString());
                    Utils.saveLoginStatus(getApplicationContext());

                    Intent it = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(it);
                    RegisterActivity.this.finish();
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
        setContentView(R.layout.activity_register);

        db = new MongoDB(handler);

        final Button register = (Button)findViewById(R.id.register_register);
        user = (EditText)findViewById(R.id.register_user);
        password = (EditText)findViewById(R.id.register_pwd);
        nickname = (EditText)findViewById(R.id.register_nickname);
        email = (EditText)findViewById(R.id.register_email);
        error = (TextView)findViewById(R.id.register_error);
        progressDialog = Utils.progressDialog(RegisterActivity.this, "注册中，请稍候...");

        register.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = user.getText().toString(),
                        pwd = password.getText().toString(),
                        nick = nickname.getText().toString(),
                        emailAddress = email.getText().toString();

                if (username.isEmpty() || pwd.isEmpty())
                    Toast.makeText(getApplicationContext(), "请至少填写用户名与密码", Toast.LENGTH_SHORT).show();
                else if (username.matches("[^0-9a-zA-Z_]") || username.length() <= 3 || username.length() >= 16) {
                    error.setVisibility(View.VISIBLE);
                    error.setText("请输入合法的用户名！（数字、字母，至少4个，至多16个字符。");
                } else if (pwd.matches("[^0-9a-zA-Z_]") || pwd.length() < 4 ||  pwd.length() > 16) {
                    error.setVisibility(View.VISIBLE);
                    error.setText("请输入合法的密码！（数字、字母，至少4个，至多16个字符。");
                } else {
                    progressDialog.show();
                    db.registerUser(username, nick, pwd, emailAddress);
                }
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
