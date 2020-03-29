package com.vlogplusplus;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import androidx.recyclerview.widget.RecyclerView;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VDRemark_Adapter extends RecyclerView.Adapter<VDRemark_Adapter.ViewHolder>{
    private LayoutInflater mInflater;
    private List<String> mName;
    private List<String> mContent;
    private List<String> mTime;
    private List<String> mHeadImg;

    VDRemark_Adapter(Context context, int video_id){
        this.mInflater=LayoutInflater.from(context);
        this.mName=new ArrayList<String>();
        this.mContent=new ArrayList<String>();
        this.mTime=new ArrayList<String>();
        this.mHeadImg=new ArrayList<String>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FormBody.Builder params = new FormBody.Builder();
                    params.add("video",video_id+""); //添加url参数
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(Api.url+"/comment/listByVideo")
                            .post(params.build()).build();
                    Response response = client.newCall(request).execute(); //执行发送指令
                    String responseData = response.body().string();
                    Log.d("获取视频评论信息",responseData);
                    if(!responseData.equals("")){
                        JSONArray jsonArray = new JSONArray(responseData);
                        for(int i=0;i<jsonArray.length();i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            int u_id = jsonObject.getInt("u_id");
                            String nickname = jsonObject.getString("nickname");
                            String c_time = jsonObject.getString("c_time");
                            String var = jsonObject.getString("var");
                            String uimage = jsonObject.getString("uimage");
                            mName.add(nickname);
                            mContent.add(var);
                            mTime.add(c_time);
                            mHeadImg.add(uimage);
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
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
        holder.name.setText(mName.get(position));
        holder.comment1.setText(mContent.get(position));
        holder.commenttime.setText(mTime.get(position));
        holder.headp.setImageURL(Api.web+mHeadImg.get(position));
    }

    @Override
    public int getItemCount() {
        return mName.size();
    }

    public String getName(int position) {
        return mName.get(position);
    }

    public void addData(String nickname, String var) {//在list中添加数据，并通知条目加入一条
        mName.add(0,nickname);
        mContent.add(0,var);
        mTime.add(0,"刚刚发布");
        //添加动画
        notifyItemInserted(0);
        notifyItemChanged(0);
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView headp;
        public TextView name;
        public TextView comment1;
        public TextView commenttime;

        ViewHolder(View view){
            super(view);
            headp = view.findViewById(R.id.observerheadp);
            name = (TextView)view.findViewById(R.id.observername);
            comment1 = (TextView)view.findViewById(R.id.comment);
            commenttime = (TextView)view.findViewById(R.id.commenttime);
        }
    }
}
