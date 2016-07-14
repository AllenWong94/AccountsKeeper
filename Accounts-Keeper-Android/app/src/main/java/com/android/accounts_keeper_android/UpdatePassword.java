package com.android.accounts_keeper_android;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class UpdatePassword extends AppCompatActivity {

    public TextView error;
    public EditText oldPwd, newPwd;
    public ProgressDialog progressDialog;

    public MongoDB mongoDB;
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == Utils.UPDATE_PASSWORD_SUCCESS) {
                progressDialog.dismiss();
                error.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "修改成功！", Toast.LENGTH_SHORT).show();
                UpdatePassword.this.finish();
            } else if (msg.what == Utils.UPDATE_PASSWORD_FAIL || msg.what == Utils.INTERNET_ERROR) {
                progressDialog.dismiss();
                error.setVisibility(View.VISIBLE);
                error.setText(msg.obj.toString());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        mongoDB = new MongoDB(handler);
        progressDialog = Utils.progressDialog(UpdatePassword.this, "修改密码中，请稍候...");

        final Button button = (Button)findViewById(R.id.update_password_confirm);
        error = (TextView)findViewById(R.id.update_password_error);
        oldPwd = (EditText)findViewById(R.id.update_password_origin);
        newPwd = (EditText)findViewById(R.id.update_password_new);

        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPassword = oldPwd.getText().toString(),
                        newPassword = newPwd.getText().toString();

                if (oldPassword.isEmpty()) {
                    error.setVisibility(View.VISIBLE);
                    error.setText("请输入原始密码！");
                } else if (newPassword.isEmpty() || newPassword.matches("[^0-9a-zA-Z_]+") || newPassword.length() < 4 || newPassword.length() > 16) {
                    error.setVisibility(View.VISIBLE);
                    error.setText("请输入合法的新密码！（数字、字母，至少4个，至多16个字符。");
                } else {
                    progressDialog.show();
                    mongoDB.changePassword(Utils.getUsername(getApplicationContext()), oldPassword, newPassword);
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mongoDB = null;
        handler = null;
    }
}
