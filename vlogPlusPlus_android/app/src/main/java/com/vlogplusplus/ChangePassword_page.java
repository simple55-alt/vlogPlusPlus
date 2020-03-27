package com.vlogplusplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ChangePassword_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepassword_page);

        Button cpwd = findViewById(R.id.button_cpwd);
        cpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText oldpw = findViewById(R.id.et1);
                final EditText newpw = findViewById(R.id.et2);
                final EditText newpw2 = findViewById(R.id.et3);
                final String oldpasswd = oldpw.getText().toString();
                final String newpasswd = newpw.getText().toString();
                final String newpasswd2 = newpw2.getText().toString();

                if(oldpasswd.equals(""))
                    Toast.makeText(ChangePassword_page.this, "请输入原密码！", Toast.LENGTH_SHORT).show();
                else if(newpasswd.equals(""))
                    Toast.makeText(ChangePassword_page.this, "请输入新密码!", Toast.LENGTH_SHORT).show();
                else if(newpasswd2.equals(""))
                    Toast.makeText(ChangePassword_page.this, "请再一次输入新密码!", Toast.LENGTH_SHORT).show();
                else if(!newpasswd.equals(newpasswd2))
                    Toast.makeText(ChangePassword_page.this, "两次新密码不相同!", Toast.LENGTH_SHORT).show();
                else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {//先验证旧密码是否正确
                                String oldPass = MD5Utils.encode(oldpasswd); //输入的旧密码
                                FormBody.Builder params = new FormBody.Builder();
                                params.add("u_id",getIntent().getStringExtra("uid")); //添加url参数
                                OkHttpClient client = new OkHttpClient();
                                Request request = new Request.Builder()
                                        .url(Api.url + "/user/get_user")
                                        .post(params.build()).build();
                                Response response = client.newCall(request).execute(); //执行发送指令
                                String responseData = response.body().string();
                                JSONObject jsonObject = new JSONObject(responseData);
                                String oldpswd = jsonObject.getString("password");
                                if(oldPass.equals(oldpswd)){ //旧密码验证通过，进行新密码的写入操作
                                    String newPass = MD5Utils.encode(newpasswd);
                                    String json = "{\"u_id\": " + getIntent().getStringExtra("uid")
                                            + ", \"password\": \"" + newPass + "\"}";
                                    client = new OkHttpClient();
                                    request = new Request.Builder()
                                            .url(Api.url + "/user/update_pass")
                                            .post(RequestBody.create(MediaType.parse("application/json"), json))
                                            .build();
                                    response = client.newCall(request).execute(); //执行发送指令
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(ChangePassword_page.this, "修改密码成功!", Toast.LENGTH_SHORT).show();
                                            oldpw.setText("");
                                            newpw.setText("");
                                            newpw2.setText("");
                                        }
                                    });
                                }else{ //旧密码输入错误
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(ChangePassword_page.this, "原密码错误!", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(ChangePassword_page.this, "修改密码失败!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    }).start();
                }

            }
        });
        Button backbutton = findViewById(R.id.go_back);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //清除按钮
        ImageView clear_opw = findViewById(R.id.bt1);
        clear_opw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.et1);
                editText.setText("");
            }
        });
        ImageView clear_npw = findViewById(R.id.bt2);
        clear_npw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.et2);
                editText.setText("");
            }
        });
        ImageView clear_npw2 = findViewById(R.id.bt3);
        clear_npw2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.et3);
                editText.setText("");
            }
        });
    }
}
