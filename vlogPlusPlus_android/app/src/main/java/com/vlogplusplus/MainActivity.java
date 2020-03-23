package com.vlogplusplus;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.vlogplusplus.player.subtitle.SubtitleActivity;
import com.vlogplusplus.videoEditor.OptiMainActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button_temp_test);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubtitleActivity.class);
                intent.putExtra("video","http://192.168.100.12:8778/test.mp4");
                intent.putExtra("subtitle","http://192.168.100.12:8778/test.txt");
                startActivity(intent);
            }
        });

        Button button2 = findViewById(R.id.button_temp_test2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Sign_in1.class);
                startActivity(intent);
            }
        });

        Button button3 = findViewById(R.id.button_temp_test3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OptiMainActivity.class);
                startActivity(intent);
            }
        });
    }
}
