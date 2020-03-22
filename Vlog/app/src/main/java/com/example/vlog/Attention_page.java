package com.example.vlog;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class Attention_page extends Fragment {
    private Context context;
    //private ListView listView;
    private View view;
    private Cursor cursor;
    //private ItemAdapter itemAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.attention_page, container,false);
        context = getActivity();
        //listView = view.findViewById(R.id.listview);

        //itemAdapter = new ItemAdapter(context,cursor);
        //listView.setAdapter(itemAdapter);
        return view;
    }
}
