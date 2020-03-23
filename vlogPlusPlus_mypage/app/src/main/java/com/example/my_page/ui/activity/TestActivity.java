package com.example.my_page.ui.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.my_page.R;

import androidx.annotation.Nullable;
import butterknife.ButterKnife;

public class TestActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

    }
}
