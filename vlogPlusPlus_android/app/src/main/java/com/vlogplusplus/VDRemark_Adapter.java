package com.vlogplusplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class VDRemark_Adapter extends RecyclerView.Adapter<VDRemark_Adapter.ViewHolder>{
    private LayoutInflater mInflater;
    private String[] mTitles;

    VDRemark_Adapter(Context context){
        this.mInflater=LayoutInflater.from(context);
        this.mTitles=new String[20];
        for (int i=0;i<20;i++){
            int index=i+1;
            mTitles[i]="周杰伦"+index;
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
        holder.name.setText(mTitles[position]);
    }

    @Override
    public int getItemCount() {
        return mTitles.length;
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
