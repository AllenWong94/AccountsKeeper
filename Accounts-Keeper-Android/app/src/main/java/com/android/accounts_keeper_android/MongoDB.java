package com.android.accounts_keeper_android;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * From remote server get remote database's data.
 */
public class MongoDB {
    private static final String url = "http://120.76.122.114:3000/";

    public Handler handler;

    public MongoDB(Handler handler) {
        this.handler = handler;
    }

    public void getUserInfo(final String username) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    connection = (HttpURLConnection)((new URL(url + "user/" + username))).openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);

                    String result = handleInput(connection);
                    JSONObject json = new JSONObject(result);

                    Log.d("shawn", "get:" + result);

                    Message msg = new Message();
                    if (json.getInt("success") == 0) {
                        msg.what = Utils.GET_USERINFO_FAIL;
                        msg.obj = json.getString("err");
                        handler.sendMessage(msg);
                        return;
                    } else if (json.getInt("success") == 1) {
                        msg.what = Utils.GET_USERINFO_SUCCESS;
                        msg.obj = result;
                        handler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Message msg = new Message();
                    msg.what = Utils.INTERNET_ERROR;
                    msg.obj = "您的网络好像出了点问题，请检查一下再试！";
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }

    public void authUser(final String username, final String password) {
      new Thread(new Runnable() {
          @Override
          public void run() {
              HttpURLConnection connection = null;
              try {
                  connection = (HttpURLConnection)((new URL(url + "user/" + username))).openConnection();
                  connection.setRequestMethod("POST");
                  connection.setConnectTimeout(5000);
                  connection.setReadTimeout(5000);

                  DataOutputStream data = new DataOutputStream(connection.getOutputStream());
                  data.writeBytes("password=" + password);

                  String result = handleInput(connection);
                  JSONObject json = new JSONObject(result);

                  Log.d("shawn", "auth:" + result);

                  Message msg = new Message();
                  if (json.getInt("success") == 0) {
                      msg.what = Utils.LOGIN_REGISTER_ERROR;
                      msg.obj = json.getString("err");
                      handler.sendMessage(msg);
                      return;
                  } else if (json.getInt("success") == 1) {
                      msg.what = Utils.LOGIN_REGISTER_SUCCESS;
                      msg.obj = result;
                      handler.sendMessage(msg);
                  }
              } catch (Exception e) {
                  e.printStackTrace();
                  Message msg = new Message();
                  msg.what = Utils.INTERNET_ERROR;
                  msg.obj = "您的网络好像出了点问题，请检查一下再试！";
                  handler.sendMessage(msg);
              }
          }
      }).start();
    }

    public void registerUser(final String username, final String nickname, final String password, final String email) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    connection = (HttpURLConnection)((new URL(url + "user"))).openConnection();
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);

                    DataOutputStream data = new DataOutputStream(connection.getOutputStream());
                    data.writeBytes("username=" + username + "&nickname=" + nickname + "&password=" + password + "&email=" + email);

                    String result = handleInput(connection);
                    JSONObject json = new JSONObject(result);

                    Log.d("shawn", "register:" + result);

                    Message msg = new Message();
                    if (json.getInt("success") == 0) {
                        msg.what = Utils.LOGIN_REGISTER_ERROR;
                        msg.obj = json.getString("err");
                        handler.sendMessage(msg);
                        return;
                    } else if (json.getInt("success") == 1) {
                        msg.what = Utils.LOGIN_REGISTER_SUCCESS;
                        msg.obj = result;
                        handler.sendMessage(msg);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Message msg = new Message();
                    msg.what = Utils.INTERNET_ERROR;
                    msg.obj = "您的网络好像出了点问题，请检查一下再试！";
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }

    public void changeNickname(final Context ctx, final String nickname) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    connection = (HttpURLConnection)((new URL(url + "user/" + Utils.getUsername(ctx) + "/nickname"))).openConnection();
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);

                    DataOutputStream data = new DataOutputStream(connection.getOutputStream());
                    data.writeBytes("nickname=" + nickname);

                    String result = handleInput(connection);
                    JSONObject json = new JSONObject(result);

                    Log.d("shawn", "changeNickname:" + result);

                    if (json.getInt("success") == 0)
                        Toast.makeText(ctx, json.getString("err"), Toast.LENGTH_SHORT).show();
                    else if (json.getInt("success") == 1) {
                        Message msg = new Message();
                        msg.what = Utils.UPDATE_NICKNAME_SUCCESS;
                        msg.obj = nickname;
                        handler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Message msg = new Message();
                    msg.what = Utils.INTERNET_ERROR;
                    msg.obj = "您的网络好像出了点问题，请检查一下再试！";
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }

    public void changePassword(final String username, final String oripwd, final String newpwd) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    connection = (HttpURLConnection)((new URL(url + "user/" + username + "/password"))).openConnection();
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);

                    DataOutputStream data = new DataOutputStream(connection.getOutputStream());
                    data.writeBytes("password=" + oripwd + "&newpwd=" + newpwd);

                    String result = handleInput(connection);
                    JSONObject json = new JSONObject(result);

                    Log.d("shawn", "changePWD:" + result);

                    Message msg = new Message();
                    if (json.getInt("success") == 0) {
                        msg.what = Utils.UPDATE_PASSWORD_FAIL;
                        msg.obj = json.getString("err");
                        handler.sendMessage(msg);
                    } else if (json.getInt("success") == 1) {
                        msg.what = Utils.UPDATE_PASSWORD_SUCCESS;
                        handler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Message msg = new Message();
                    msg.what = Utils.INTERNET_ERROR;
                    msg.obj = "您的网络好像出了点问题，请检查一下再试！";
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }

    private String handleInput(HttpURLConnection connection) {
        try {
            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder result = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null)
                result.append(line);

            // Release resource
            in.close();
            connection.disconnect();

            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
