package com.vlogplusplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

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
                EditText oldpw = findViewById(R.id.et1);
                EditText newpw = findViewById(R.id.et2);
                EditText newpw2 = findViewById(R.id.et3);
                final String[] oldpasswd = {oldpw.getText().toString()};
                final String[] newpasswd = {newpw.getText().toString()};
                final String[] newpasswd2 = {newpw2.getText().toString()};

                if(oldpasswd[0].equals(""))
                    Toast.makeText(ChangePassword_page.this, "请输入原密码！", Toast.LENGTH_SHORT).show();
                else if(newpasswd[0].equals(""))
                    Toast.makeText(ChangePassword_page.this, "请输入新密码!", Toast.LENGTH_SHORT).show();
                else if(newpasswd2[0].equals(""))
                    Toast.makeText(ChangePassword_page.this, "请再一次输入新密码!", Toast.LENGTH_SHORT).show();
                else if(!newpasswd[0].equals(newpasswd2))
                    Toast.makeText(ChangePassword_page.this, "两次新密码不相同!", Toast.LENGTH_SHORT).show();
                else {

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
