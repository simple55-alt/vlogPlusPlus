package com.vlogplusplus.template;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.vlogplusplus.R;

import java.util.ArrayList;
import java.util.List;

public class TemplateSelection_Adapter extends RecyclerView.Adapter<TemplateSelection_Adapter.ViewHolder>{
    private LayoutInflater mInflater;
    private List<String> mTitle;
    private List<String> mPhoto;
    private List<String> mdescription;
    TemplateSelection_Adapter(Context context){
        this.mInflater=LayoutInflater.from(context);
        this.mTitle=new ArrayList<String>();
        this.mPhoto=new ArrayList<String>();
        this.mdescription=new ArrayList<String>();
        for (int i=0;i<5;i++){
            int index=i+1;
            mTitle.add("美食模板"+index);
            mdescription.add("vlog++真好使！");
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.templateselection__adapter,parent,false);
        //view.setBackgroundColor(Color.RED);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }
    /**
     * 数据的绑定显示
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.T_title.setText(mTitle.get(position));
        holder.T_photo.setImageResource(R.mipmap.head_example);
        holder.T_description.setText(mdescription.get(position));
    }



    @Override
    public int getItemCount() {
        return mTitle.size();
    }

    public String getTitle(int position) {
        return mTitle.get(position);
    }
    public void addData() {
//      在list中添加数据，并通知条目加入一条
        int position=getItemCount() + 1;

        //添加动画
        notifyItemInserted(position - 1);
        notifyItemChanged(position - 1);
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView T_photo;
        public TextView T_title;
        public TextView T_description;

        ViewHolder(View view){
            super(view);
            T_photo = (ImageView)view.findViewById(R.id.template_photo);
            T_title = (TextView)view.findViewById(R.id.template_title);
            T_description = (TextView)view.findViewById(R.id.template_description);
        }
    }
}
