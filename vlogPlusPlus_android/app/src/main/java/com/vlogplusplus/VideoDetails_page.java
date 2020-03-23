package com.vlogplusplus;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class VideoDetails_page extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videodetails_page);

        bindView();
    }


    private void bindView() {
        ImageButton transmitbutton = (ImageButton) findViewById(R.id.transmitbutton);
        transmitbutton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.transmitbutton:
                    transmitDialog();                //评论对话框
                    break;
                default:
                    break;
            }
        }
    public void transmitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(VideoDetails_page.this);
        final AlertDialog dialog = builder.create();
        View dialogView = View.inflate(VideoDetails_page.this, R.layout.transmit_dialog, null);
        dialog.setView(dialogView);
        builder.setCancelable(true);
        dialog.show();

    }
}

