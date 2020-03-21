package com.vlogplusplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

public class Registered extends AppCompatActivity {

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
            }
        };
        style.setSpan(clickableSpan, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#FC8C9E"));
        style.setSpan(foregroundColorSpan, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv.setText(style);
    }
}
