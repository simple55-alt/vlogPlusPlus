package com.example.vlog;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private Fragment nowFragment;
    private Home_page home_page = new Home_page();
    private My_page my_page = new My_page();
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_community: //社区
                    if(nowFragment != home_page){
                        fragmentTransaction = fragmentManager.beginTransaction(); //启动碎片切换事物
                        fragmentTransaction.replace(R.id.frameLayout, home_page).commit(); //载入消息列表碎片
                        nowFragment = home_page; //记录当前显示的碎片
                    }
                    return true;
                case R.id.navigation_photograph: //拍摄
                    Intent intent = new Intent(MainActivity.this, Making_page.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_mine: //我的
                    if(nowFragment != my_page){
                        fragmentTransaction = fragmentManager.beginTransaction(); //启动碎片切换事物
                        fragmentTransaction.replace(R.id.frameLayout, my_page).commit(); //载入消息列表碎片
                        nowFragment = my_page; //记录当前显示的碎片
                    }
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigationView = (BottomNavigationView)findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        fragmentManager = getSupportFragmentManager(); //碎片管理器
        fragmentTransaction = fragmentManager.beginTransaction(); //启动碎片切换事物
        fragmentTransaction.replace(R.id.frameLayout, home_page).commit(); //载入消息列表碎片
        nowFragment = home_page; //记录当前显示的碎片
    }

    @Override
    public void onClick(View v) {

    }
}
