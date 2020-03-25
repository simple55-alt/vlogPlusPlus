package com.vlogplusplus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModifyPhonenumber_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifyphonenumber_page);

        Button cpn = findViewById(R.id.button_cpn);
        cpn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText oldpw = findViewById(R.id.et1);
                EditText newpw = findViewById(R.id.et2);
                final String[] oldpasswd = {oldpw.getText().toString()};
                final String[] newpasswd = {newpw.getText().toString()};

                if(oldpasswd[0].equals(""))
                    Toast.makeText(ModifyPhonenumber_page.this, "请输入原手机号码！", Toast.LENGTH_SHORT).show();
                else if(newpasswd[0].equals(""))
                    Toast.makeText(ModifyPhonenumber_page.this, "请输入新手机号码!", Toast.LENGTH_SHORT).show();
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
