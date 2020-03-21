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

public class Sign_in2 extends AppCompatActivity {

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
    }
}
