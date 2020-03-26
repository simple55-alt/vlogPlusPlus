package com.vlogplusplus;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class VideoDetails_page extends AppCompatActivity implements View.OnClickListener{
    private View inflate;
    private Dialog dialog;
    private RecyclerView recyclerView_remark;
    private VDRemark_Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videodetails_page);

        ImageButton backb = findViewById(R.id.back);
        backb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bindView();
        //开始设置RecyclerView
        recyclerView_remark=(RecyclerView)this.findViewById(R.id.remarker);
        //设置固定大小
        recyclerView_remark.setHasFixedSize(true);
        //创建线性布局
        mLayoutManager = new LinearLayoutManager(this);
        //垂直方向
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        //给RecyclerView设置布局管理器
        recyclerView_remark.setLayoutManager(mLayoutManager);
        //创建适配器，并且设置
        mAdapter = new VDRemark_Adapter(this);
        recyclerView_remark.setAdapter(mAdapter);

        //查看更多评论登录
        TextView tv = findViewById(R.id.more_remark);
        final SpannableStringBuilder style = new SpannableStringBuilder();
        style.append("查看更多评论");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                for (int i=0;i<10;i++) {
                    mAdapter.addData();
                }
            }
        };
        style.setSpan(clickableSpan, 0, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#000000"));
        style.setSpan(foregroundColorSpan, 0, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv.setText(style);
    }

    private void bindView() {
        ImageButton transmitbutton = (ImageButton) findViewById(R.id.transmitbutton);
        transmitbutton.setOnClickListener(this);
        EditText seachEditText = (EditText) findViewById(R.id.comment_my);
        seachEditText.setFocusable(false);
        seachEditText.setOnClickListener(this);
    }

    public void transmitDialog() {
        dialog = new Dialog(this,R.style.ActionSheetDialogStyle);        //填充对话框的布局
        inflate = LayoutInflater.from(this).inflate(R.layout.transmit_dialog, null);        //初始化控件
        Button cancelbutton =(Button) inflate.findViewById(R.id.tranmits_cancle);
        cancelbutton.setOnClickListener(this);
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

    public void remarkDialog() {
        dialog = new Dialog(this,R.style.ActionSheetDialogStyle);//填充对话框的布局
        inflate = LayoutInflater.from(this).inflate(R.layout.remark_dialog, null);        //初始化控件

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
            case R.id.tranmits_cancle:
                dialog.cancel();                //评论对话框
                break;
            case R.id.comment_my:
                remarkDialog();
                break;
            default:
                break;
        }
//        dialog.dismiss();
    }
}
