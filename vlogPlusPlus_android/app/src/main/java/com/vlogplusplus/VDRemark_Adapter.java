package com.vlogplusplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class VDRemark_Adapter extends RecyclerView.Adapter<VDRemark_Adapter.ViewHolder>{
    private LayoutInflater mInflater;
    private List<String> mTitles;
    private List<String> mContent;
    VDRemark_Adapter(Context context){
        this.mInflater=LayoutInflater.from(context);
        this.mTitles=new ArrayList<String>();
        this.mContent=new ArrayList<String>();
        for (int i=0;i<10;i++){
            int index=i+1;
            mTitles.add("周杰伦"+index);
            mContent.add("哎哟，不错哦！");
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.vdremark__adapter,parent,false);
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
        holder.name.setText(mTitles.get(position));
        holder.comment1.setText(mContent.get(position));
    }

    @Override
    public int getItemCount() {
        return mTitles.size();
    }
    public void addData() {
//      在list中添加数据，并通知条目加入一条
        int position=getItemCount() + 1;
        mTitles.add("陈奕迅" + position);
        mContent.add("真的不错哦");
        //添加动画
        notifyItemInserted(position - 1);
        notifyItemChanged(position - 1);
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView headp;
        public TextView name;
        public TextView comment1;
        public TextView commenttime;

        ViewHolder(View view){
            super(view);
            headp = (ImageView)view.findViewById(R.id.observerheadp);
            name = (TextView)view.findViewById(R.id.observername);
            comment1 = (TextView)view.findViewById(R.id.comment);
            commenttime = (TextView)view.findViewById(R.id.commenttime);
        }
    }
}
