package com.vlogplusplus;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.Random;

public class ModifyPhonenumber_page extends AppCompatActivity {
    private static int msgCode = -1; //短信验证码, 后门-1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifyphonenumber_page);

        //修改按钮
        Button cpn = findViewById(R.id.button_cpn);
        cpn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText password = findViewById(R.id.et1);
                final EditText newPhone = findViewById(R.id.et2);
                final EditText Code = findViewById(R.id.et3);
                final String passwd = password.getText().toString();
                final String newphone = newPhone.getText().toString();
                final String code = Code.getText().toString();

                if(passwd.equals(""))
                    Toast.makeText(ModifyPhonenumber_page.this, "请输入密码！", Toast.LENGTH_SHORT).show();
                else if(newphone.equals(""))
                    Toast.makeText(ModifyPhonenumber_page.this, "请输入新手机号码!", Toast.LENGTH_SHORT).show();
                else if(code.equals(""))
                    Toast.makeText(ModifyPhonenumber_page.this, "请输入验证码!", Toast.LENGTH_SHORT).show();
                else { //短信验证码是否正确
                    if(code.equals(msgCode+"")){
                        new Thread(new Runnable() {//验证密码是否正确验证
                            @Override
                            public void run() {
                                try{
                                    String oldPass = MD5Utils.encode(passwd); //输入的密码
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
                                    if(oldPass.equals(oldpswd)) { //密码验证通过，进行新手机号的写入操作
                                        String json = "{\"u_id\": " + getIntent().getStringExtra("uid")
                                                + ", \"phone\": \"" + newphone + "\"}";
                                        client = new OkHttpClient();
                                        request = new Request.Builder()
                                                .url(Api.url + "/user/update_phone")
                                                .post(RequestBody.create(MediaType.parse("application/json"), json))
                                                .build();
                                        response = client.newCall(request).execute(); //执行发送指令
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(ModifyPhonenumber_page.this, "修改手机号成功!", Toast.LENGTH_SHORT).show();
                                                password.setText("");
                                                newPhone.setText("");
                                                Code.setText("");
                                            }
                                        });
                                    }else { //密码错误
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(ModifyPhonenumber_page.this, "密码错误!", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(ModifyPhonenumber_page.this, "修改手机号失败!", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        }).start();
                    }else
                        Toast.makeText(ModifyPhonenumber_page.this, "短信验证码错误!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //清除按钮
        ImageView clear_pw = findViewById(R.id.bt1);
        clear_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.et1);
                editText.setText("");
            }
        });
        ImageView clear_phone = findViewById(R.id.bt2);
        clear_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.et2);
                editText.setText("");
            }
        });

        //发送验证码按钮
        Button sendCode = findViewById(R.id.button_sendSmsCode);
        final TimeCount timeCount = new TimeCount(50000,1000,sendCode);
        sendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText phone = findViewById(R.id.et2);
                final String phoneNumber = phone.getText().toString();
                if(phoneNumber.length()!=11)
                    Toast.makeText(ModifyPhonenumber_page.this, "请输入正确的手机号码！", Toast.LENGTH_SHORT).show();
                else {   //发送短信
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
                                if(responseData.equals("[]")){ //生成4位数字的短信验证码
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
                                            if(responseData2.equals("OK")){
                                                Toast.makeText(ModifyPhonenumber_page.this, "短信发送成功！", Toast.LENGTH_SHORT).show();
                                                timeCount.start();
                                                phone.setFocusable(false); //防止用户收到验证码后改手机号不验证直接注册
                                                phone.setFocusableInTouchMode(false);
                                            }else {
                                                Toast.makeText(ModifyPhonenumber_page.this, "短信发送失败！请重试！", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }else { //手机号码已被注册
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(ModifyPhonenumber_page.this, "该手机号码已有绑定别的账号！", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(ModifyPhonenumber_page.this, "验证码发送失败!", Toast.LENGTH_SHORT).show();
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
    }
}
