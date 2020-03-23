package com.vlogplusplus;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class VideoDetails_page extends AppCompatActivity implements View.OnClickListener{
    private View inflate;
    private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videodetails_page);


        bindView();
    }

    private void bindView() {
        ImageButton transmitbutton = (ImageButton) findViewById(R.id.transmitbutton);
        transmitbutton.setOnClickListener(this);

//        Button cancelbutton = (Button) findViewById(R.id.cancel);
//        cancelbutton.setOnClickListener(this);
        }
    public void transmitDialog() {
        dialog = new Dialog(this,R.style.ActionSheetDialogStyle);        //填充对话框的布局
        inflate = LayoutInflater.from(this).inflate(R.layout.transmit_dialog, null);        //初始化控件
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity( Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 0;//设置Dialog距离底部的距离
        //设置dialog的宽高
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;// 屏幕宽度（像素）
//        int height= dm.heightPixels; // 屏幕高度（像素）
        float density = dm.density;//屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = dm.densityDpi;//屏幕密度dpi（120 / 160 / 240）
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        //这个地方可以用ViewGroup.LayoutParams.MATCH_PARENT属性，各位试试看看有没有效果
        layoutParams.width = width;
//        layoutParams.height = height;
//       将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.transmitbutton:
                transmitDialog();                //评论对话框
                break;
//            case R.id.cancel:
//                dialog.dismiss();                //评论对话框
//                break;
            default:
                break;
        }
    }
}
