package com.vlogplusplus;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Registered extends AppCompatActivity {
    private static int msgCode = -1; //短信验证码, 后门-1
    private static boolean usernameCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registered);
        //立即登录
        TextView tv = findViewById(R.id.signup);
        final SpannableStringBuilder style = new SpannableStringBuilder();
        style.append("立即登录");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent=new Intent(Registered.this,Sign_in1.class);
                //        Toast.makeText(SignIn1Activity.this, "账号/密码错误!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        };
        style.setSpan(clickableSpan, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#FC8C9E"));
        style.setSpan(foregroundColorSpan, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv.setText(style);

        //检查用户名可用性
        final Button button_check = findViewById(R.id.button_check);
        button_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            EditText username = findViewById(R.id.et1);
                            FormBody.Builder params = new FormBody.Builder();
                            params.add("username",username.getText().toString()); //添加url参数
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                    .url(Api.url+"/user/check")
                                    .post(params.build()).build();
                            Response response = client.newCall(request).execute(); //执行发送指令
                            String responseData = response.body().string();
                            Log.d("验证用户存在请求回复",responseData);
                            if(responseData.equals("[]")){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(Registered.this, "恭喜！用户名可用！", Toast.LENGTH_SHORT).show();
                                        usernameCheck = true;
                                        button_check.setText(R.string.usrnameChecked);
                                        button_check.setEnabled(false);
                                    }
                                });
                            }else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(Registered.this, "用户名不可用！换一个吧！", Toast.LENGTH_SHORT).show();
                                        usernameCheck = false;
                                    }
                                });
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Registered.this, "网络请求出错！", Toast.LENGTH_SHORT).show();
                                    usernameCheck = false;
                                }
                            });
                        }
                    }
                }).start();
            }
        });

        //发送验证码
        final Button button_sendCode = findViewById(R.id.button_sendSmsCode);
        final TimeCount timeCount = new TimeCount(50000,1000,button_sendCode);
        button_sendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //检查手机号是否已经被注册，节约短信
                            final EditText phone = findViewById(R.id.et2);
                            String phoneNumber = phone.getText().toString();
                            FormBody.Builder params = new FormBody.Builder();
                            params.add("username", phoneNumber); //添加url参数
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                    .url(Api.url + "/user/check")
                                    .post(params.build()).build();
                            Response response = client.newCall(request).execute(); //执行发送指令
                            String responseData = response.body().string();
                            Log.d("验证手机是否已注册请求回复", responseData);
                            if(responseData.equals("[]")) { //该手机号码尚未被注册
                                //发送短信
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
                                Log.d("短信请求回复",responseData2);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if(responseData2.equals("OK")) {
                                            Toast.makeText(Registered.this, "短信发送成功！", Toast.LENGTH_SHORT).show();
                                            timeCount.start();
                                            phone.setFocusable(false); //防止用户收到验证码后改手机号不验证直接注册
                                            phone.setFocusableInTouchMode(false);
                                        }else {
                                            Toast.makeText(Registered.this, "短信发送失败！请重试！", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                            else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(Registered.this, "该号码已被注册！", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Registered.this, "短信发送失败！", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
            }
        });

        //用户名输入框
        final EditText username = findViewById(R.id.et1);
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                button_check.setEnabled(true);
                button_check.setText(R.string.checkUsrname);
                usernameCheck = false;
            }
        });

        //注册按钮
        Button button_register = findViewById(R.id.button_register);
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String usrname = username.getText().toString();
                final EditText phone = findViewById(R.id.et2);
                final String phoneNumber = phone.getText().toString();
                final EditText password1 = findViewById(R.id.et3);
                final EditText password2 = findViewById(R.id.et5);
                final String passwd1 = password1.getText().toString();
                String passwd2 = password2.getText().toString();
                final EditText code = findViewById(R.id.et4);

                if(!usernameCheck)
                    Toast.makeText(Registered.this, "请先点击用户名检测按钮！", Toast.LENGTH_SHORT).show();
                else if(phoneNumber.length()!=11)
                    Toast.makeText(Registered.this, "手机号码输入有误！", Toast.LENGTH_SHORT).show();
                else if(!passwd1.equals(passwd2))
                    Toast.makeText(Registered.this, "两次密码输入不一致！", Toast.LENGTH_SHORT).show();
                else if(!code.getText().toString().equals(msgCode+""))
                    Toast.makeText(Registered.this, "短信验证码错误！", Toast.LENGTH_SHORT).show();
                else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                String pass = MD5Utils.encode(passwd1); //MD5加密
                                String json = "{\"username\": \"" + usrname + "\", \"password\": \"" + pass
                                        + "\",\"nickname:\":\"" + usrname + "\",\"email\":\"\",\"phone\":\""
                                        + phoneNumber + "\",\"image\":\"\",\"sex\":0,\"birthday\":\"1998-01-01 00:00:00\",\"fashion\":\"\"}";
                                OkHttpClient client = new OkHttpClient();
                                Request request = new Request.Builder()
                                        .url(Api.url + "/user/add")
                                        .post(RequestBody.create(MediaType.parse("application/json"), json))
                                        .build();
                                Response response = client.newCall(request).execute(); //执行发送指令
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(Registered.this, "注册成功！", Toast.LENGTH_SHORT).show();
                                        username.setText("");
                                        phone.setText("");
                                        password1.setText("");
                                        password2.setText("");
                                        code.setText("");
                                    }
                                });
                            }catch (Exception e){
                                e.printStackTrace();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(Registered.this, "注册失败！", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    }).start();
                }
            }
        });
    }
}

class TimeCount extends CountDownTimer {
    private Button button;

    TimeCount(long millisInFuture, long countDownInterval, Button button) {
        super(millisInFuture, countDownInterval);
        this.button = button;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        button.setEnabled(false);
        button.setText(millisUntilFinished / 1000 +"s");
    }

    @Override
    public void onFinish() {
        button.setText(R.string.sendCode);
        button.setEnabled(true);
    }
}
