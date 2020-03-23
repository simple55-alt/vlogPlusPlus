package com.vlogplusplus;

import androidx.appcompat.app.AppCompatActivity;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vlogplusplus.player.subtitle.SubtitleActivity;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sign_in1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in1);
        //验证码登录
        TextView tv = findViewById(R.id.verifysignin);
        final SpannableStringBuilder style = new SpannableStringBuilder();
        style.append("使用短信验证码登录");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent=new Intent(Sign_in1.this,Sign_in2.class);
                //Intent intent=new Intent(Sign_in1.this, SubtitleActivity.class);
                //        Toast.makeText(SignIn1Activity.this, "账号/密码错误!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        };
        style.setSpan(clickableSpan, 0, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#FC8C9E"));
        style.setSpan(foregroundColorSpan, 0, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv.setText(style);

        //立即注册
        TextView tv2 = findViewById(R.id.signup);
        final SpannableStringBuilder style2 = new SpannableStringBuilder();
        style2.append("立即注册");
        ClickableSpan clickableSpan2 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent=new Intent(Sign_in1.this,Registered.class);
                startActivity(intent);
                finish();
            }
        };
        style2.setSpan(clickableSpan2, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(Color.parseColor("#FC8C9E"));
        style2.setSpan(foregroundColorSpan2, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv2.setMovementMethod(LinkMovementMethod.getInstance());
        tv2.setText(style2);

        //登录按钮
        Button signin = findViewById(R.id.button_signin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = findViewById(R.id.et1);
                EditText password = findViewById(R.id.et2);
                final String usrname = username.getText().toString();
                final String[] passwd = {password.getText().toString()};

                if(usrname.equals(""))
                    Toast.makeText(Sign_in1.this, "请输入账号！", Toast.LENGTH_SHORT).show();
                else if(passwd[0].equals(""))
                    Toast.makeText(Sign_in1.this, "请输入密码!", Toast.LENGTH_SHORT).show();
                else{
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //MD5加密
                            passwd[0] = MD5Utils.encode(passwd[0]); //MD5加密
                            try {
                                String json = "{\"username\": \""+usrname+"\", \"password\": \""+ passwd[0] +"\"}";
                                OkHttpClient client = new OkHttpClient();
                                Request request = new Request.Builder()
                                        .url(Api.url+"/user/login")
                                        .post(RequestBody.create(MediaType.parse("application/json"),json))
                                        .build();
                                Response response = client.newCall(request).execute(); //执行发送指令
                                String responseData = response.body().string();
                                Log.d("登录请求回复",responseData);
                                if(responseData.equals("")){ //登录失败
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(Sign_in1.this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }else{ //登录成功
                                    JSONObject jsonObject = new JSONObject(responseData);
                                    String id = jsonObject.getString("u_id");
                                    String nickname = jsonObject.getString("nickname");
                                    //写入用户信息到永久储存
                                    SharedPreferences.Editor editor = getSharedPreferences("db_login",MODE_PRIVATE).edit();
                                    editor.putString("id",id);
                                    editor.putString("username",usrname);
                                    editor.putString("nickname",nickname);
                                    editor.apply();
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(Sign_in1.this, "登录成功！", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(Sign_in1.this,MainActivity.class);
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
                                        Toast.makeText(Sign_in1.this, "网络请求出错！", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    }).start();
                }
            }
        });

        //清空用户名
        ImageView clear_username = findViewById(R.id.bt1);
        clear_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.et1);
                editText.setText("");
            }
        });

        //清空密码
        ImageView clear_password = findViewById(R.id.bt2);
        clear_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.et2);
                editText.setText("");
            }
        });
    }
}

class MD5Utils {
    public static String encode(String str) {
        StringBuffer buffer = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(str.getBytes());
            for (byte b : digest) {
                int x = b & 0xff;  // 将byte转换2位的16进制int类型数
                String s = Integer.toHexString(x); // 将一个int类型的数转为2位的十六进制数
                if (s.length() == 1) {
                    s = "0" + s;
                }
                buffer.append(s);
                //Log.d("vivi", "encode: " + s);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }
}