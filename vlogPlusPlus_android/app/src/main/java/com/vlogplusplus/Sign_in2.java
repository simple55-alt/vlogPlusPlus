package com.vlogplusplus;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.Random;

public class Sign_in2 extends AppCompatActivity {
    private static int msgCode = -1; //短信验证码, 后门-1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in2);
        //密码登录
        TextView tv = findViewById(R.id.passsignin);
        final SpannableStringBuilder style = new SpannableStringBuilder();
        style.append("使用密码登录");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent=new Intent(Sign_in2.this,Sign_in1.class);
                startActivity(intent);
                finish();
            }
        };
        style.setSpan(clickableSpan, 0, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#FC8C9E"));
        style.setSpan(foregroundColorSpan, 0, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv.setText(style);

        //立即注册
        TextView tv2 = findViewById(R.id.signup);
        final SpannableStringBuilder style2 = new SpannableStringBuilder();
        style2.append("立即注册");
        ClickableSpan clickableSpan2 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent=new Intent(Sign_in2.this,Registered.class);
                startActivity(intent);
                finish();
            }
        };
        style2.setSpan(clickableSpan2, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(Color.parseColor("#FC8C9E"));
        style2.setSpan(foregroundColorSpan2, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv2.setMovementMethod(LinkMovementMethod.getInstance());
        tv2.setText(style2);

        //发送短信验证码
        Button button_sendSmsCode = findViewById(R.id.button_sendSmsCode);
        button_sendSmsCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText phone = findViewById(R.id.et1);
                EditText code = findViewById(R.id.et2);
                final String phoneNumber = phone.getText().toString();
                if(phoneNumber.length()!=11)
                    Toast.makeText(Sign_in2.this, "请输入正确的手机号码！", Toast.LENGTH_SHORT).show();
                else{   //发送短信
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                //检测用户是否存在，避免浪费短信条数
                                FormBody.Builder params = new FormBody.Builder();
                                params.add("username",phoneNumber); //添加url参数
                                OkHttpClient client = new OkHttpClient();
                                Request request = new Request.Builder()
                                        .url(Api.url+"/user/check")
                                        .post(params.build()).build();
                                Response response = client.newCall(request).execute(); //执行发送指令
                                String responseData = response.body().string();
                                Log.d("验证用户存在请求回复",responseData);
                                if(responseData.equals("")){
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(Sign_in2.this, "该手机号码尚未注册！", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }else {
                                    //生成4位数字的短信验证码
                                    Random random = new Random();
                                    while (msgCode<1000)
                                        msgCode = random.nextInt(10000);
                                    //Log.d("短信验证码",msgCode+"");
                                    String json = "{\"phoneNumber\": \""+phoneNumber+"\", \"code\": \""+ msgCode +"\", \"min\": 3}";
                                    client = new OkHttpClient();
                                    request = new Request.Builder()
                                            .url(Api.url+"/sms/sendCode")
                                            .post(RequestBody.create(MediaType.parse("application/json"),json))
                                            .build();
                                    response = client.newCall(request).execute(); //执行发送指令
                                    final String responseData2 = response.body().string();
                                    Log.d("短信请求回复",responseData);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if(responseData2.equals("OK")){
                                                Toast.makeText(Sign_in2.this, "短信发送成功！", Toast.LENGTH_SHORT).show();
                                            }else {
                                                Toast.makeText(Sign_in2.this, "短信发送失败！请重试！", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(Sign_in2.this, "短信发送失败！", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    }).start();
                }
            }
        });

        //登录按钮
        Button button_signin = findViewById(R.id.button_signin);
        button_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText smsCode = findViewById(R.id.et2);
                String code = smsCode.getText().toString();
                if(code.equals(msgCode+"")){ //验证码校验
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try{ //登录
                                EditText phone = findViewById(R.id.et1);
                                String json = "{\"username\": \""+phone.getText().toString()+"\", \"password\": \""+ msgCode +"\"}";
                                OkHttpClient client = new OkHttpClient();
                                Request request = new Request.Builder()
                                        .url(Api.url+"/user/login")
                                        .post(RequestBody.create(MediaType.parse("application/json"),json))
                                        .build();
                                Response response = client.newCall(request).execute(); //执行发送指令
                                final String responseData = response.body().string();
                                Log.d("登录请求回复",responseData);
                                if(responseData.equals("")){ //登录失败
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(Sign_in2.this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }else{ //登录成功
                                    JSONObject jsonObject = new JSONObject(responseData);
                                    String id = jsonObject.getString("u_id");
                                    String username = jsonObject.getString("username");
                                    String nickname = jsonObject.getString("nickname");
                                    //写入用户信息到永久储存
                                    SharedPreferences.Editor editor = getSharedPreferences("db_login",MODE_PRIVATE).edit();
                                    editor.putString("id",id);
                                    editor.putString("username",username);
                                    editor.putString("nickname",nickname);
                                    editor.apply();
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(Sign_in2.this, "登录成功！", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(Sign_in2.this,MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    });
                                    Log.d("用户id",id);
                                    Log.d("昵称",nickname);
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(Sign_in2.this, "网络请求出错！", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    }).start();
                }else
                    Toast.makeText(Sign_in2.this, "验证码错误！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
