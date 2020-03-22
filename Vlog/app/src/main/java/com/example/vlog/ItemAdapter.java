package com.example.vlog;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {

    private Context context;
    private Cursor cursor;
    private LinearLayout layout;
    public ItemAdapter(Context context,Cursor cursor){
        this.context=context;
        this.cursor=cursor;
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        return cursor.getPosition();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        layout = (LinearLayout)flater.inflate(R.layout.cell,null);
        ImageView picture = (ImageView)layout.findViewById(R.id.picture_yulan);
        ImageView photo = (ImageView)layout.findViewById(R.id.photo);
        TextView name = (TextView)layout.findViewById(R.id.sender_name);
        TextView time = (TextView)layout.findViewById(R.id.sending_time);
        TextView message = (TextView)layout.findViewById(R.id.message);
        TextView like = (TextView)layout.findViewById(R.id.like);
        TextView ping = (TextView)layout.findViewById(R.id.ping);
        TextView zhuan = (TextView)layout.findViewById(R.id.zhuan);
        cursor.moveToPosition(position);

        return layout;
    }
}
