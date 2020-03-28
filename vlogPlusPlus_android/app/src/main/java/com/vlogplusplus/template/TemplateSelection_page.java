package com.vlogplusplus.template;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vlogplusplus.R;
import com.vlogplusplus.VDRemark_Adapter;

public class TemplateSelection_page extends AppCompatActivity {

    private View inflate;
    private Dialog dialog;
    private RecyclerView recyclerView_template;
    private TemplateSelection_Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.templateselection_page);


        //开始设置RecyclerView
        recyclerView_template=(RecyclerView)this.findViewById(R.id.remarker);
        //设置固定大小
        recyclerView_template.setHasFixedSize(true);
        //创建线性布局
        mLayoutManager = new LinearLayoutManager(this);
        //垂直方向
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        //给RecyclerView设置布局管理器
        recyclerView_template.setLayoutManager(mLayoutManager);
        recyclerView_template.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        //创建适配器，并且设置
        mAdapter = new TemplateSelection_Adapter(this);
        recyclerView_template.setAdapter(mAdapter);
    }

    public void myItemClick(View view){
        // 获取itemView的位置
        int position = recyclerView_template.getChildAdapterPosition(view);
        Intent intent=new Intent(TemplateSelection_page.this,TemplateFilled_page.class);
        intent.putExtra("data","nihao");//用putExtra把内容传送到另一个Activity,名字是data，值是nihao
        startActivity(intent);
    }


}
