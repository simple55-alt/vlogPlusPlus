package com.vlogplusplus;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import com.vlogplusplus.player.subtitle.APPApplication;
import com.vlogplusplus.player.subtitle.ResolutionAdapter;
import com.vlogplusplus.player.subtitle.SrtParser;
import com.vlogplusplus.player.subtitle.VideoPathObject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class VideoDetails_page extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {
    private View inflate;
    private Dialog dialog;
    private RecyclerView recyclerView_remark;
    private VDRemark_Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private int video_id = 0;
    private int u_id = 0; //当前登录用户id
    private String nickname = ""; //当前登录用户昵称
    //播放器所需要的成员
    private VideoView videoView ;
    TextView tvSrt, mCurrentTime,mTotalTime,resolution_switch,mediacontroller_file_name;
    ImageView mediacontroller_play_pause,switch_screen;
    private SeekBar progress_seekbar;
    private AudioManager mAM;
    private long totalDuration;
    private boolean mShowing = true, mDragging,isResolution;
    private static final int PARSE_SRT = 0;
    private static final int FADE_OUT = 1;
    private static final int SHOW_PROGRESS = 2;
    private static final int CHANGE_VIDEOVIEW_BG = 3;
    private static final int sDefaultTimeout = 3000;
    private RelativeLayout videoview_layout, mMediaController;
    private ListView resolution_listview;
    private boolean isPortraint = true;
    private int screenWidth,videoViewHeight;
    List<VideoPathObject> videopathList= new ArrayList<>();
    Handler mHandler=new Handler(){
        public void handleMessage(Message msg){
            long pos;
            switch (msg.what) {
                case PARSE_SRT:
                    SrtParser.showSRT(videoView,tvSrt) ;
                    //每隔500ms执行一次showSRT()
                    mHandler.sendEmptyMessageDelayed(0, 500);
                    break;
                case FADE_OUT:
                    showOrHideController();
                    break;
                case SHOW_PROGRESS:
                    pos = setControllerProgress();
                    if (!mDragging && mShowing) {
                        msg = obtainMessage(SHOW_PROGRESS);
                        sendMessageDelayed(msg, 1000 - (pos % 1000));
                    }
                    break;
                case CHANGE_VIDEOVIEW_BG:
                    videoView.setBackgroundColor(0x00000000);
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videodetails_page);

        video_id = getIntent().getIntExtra("video_id",0);
        u_id = getIntent().getIntExtra("u_id",0);
        nickname = getIntent().getStringExtra("nickname");
        TextView videotitle = findViewById(R.id.videotitle); //设置视频标题
        videotitle.setText(getIntent().getStringExtra("video_title"));
        TextView uploadtime = findViewById(R.id.uploadtime); //设置视频上传时间
        uploadtime.setText(getIntent().getStringExtra("video_uploadTime"));
        TextView authorName = findViewById(R.id.name);  //设置作者名字
        authorName.setText(getIntent().getStringExtra("nickname"));
        CircleImageView head_img = findViewById(R.id.headp);  //设置作者头像
        head_img.setImageURL(getIntent().getStringExtra("author_headimg"));
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
        recyclerView_remark.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        //创建适配器，并且设置
        mAdapter = new VDRemark_Adapter(this, video_id);
        recyclerView_remark.setAdapter(mAdapter);

        //查看更多评论登录
        TextView tv = findViewById(R.id.more_remark);
        final SpannableStringBuilder style = new SpannableStringBuilder();
        style.append("查看更多评论");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(VideoDetails_page.this, "没有更多了！",Toast.LENGTH_SHORT).show();
            }
        };
        style.setSpan(clickableSpan, 0, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#000000"));
        style.setSpan(foregroundColorSpan, 0, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv.setText(style);


        //加载播放器组件
        videoView = (VideoView)this.findViewById(R.id.videoView );
        mAM = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        screenWidth = APPApplication.screenWidth;
        videoViewHeight = screenWidth * 9 / 16;
        tvSrt = (TextView)findViewById(R.id.srt);//项目中显示字幕的控件
        mediacontroller_file_name= (TextView)findViewById(R.id.mediacontroller_file_name);
        mTotalTime = (TextView) findViewById(R.id.mediacontroller_time_total);
        mCurrentTime = (TextView) findViewById(R.id.mediacontroller_time_current);
        resolution_switch = (TextView) findViewById(R.id.resolution_switch);
        mediacontroller_play_pause = (ImageView) findViewById(R.id.mediacontroller_play_pause);
        switch_screen = (ImageView) findViewById(R.id.switch_screen);
        videoview_layout = (RelativeLayout) findViewById(R.id.videoview_layout);
        mediacontroller_play_pause.setOnClickListener(this);
        progress_seekbar = (SeekBar) findViewById(R.id.mediacontroller_seekbar);
        videoview_layout = (RelativeLayout) findViewById(R.id.videoview_layout);
        mMediaController = (RelativeLayout) findViewById(R.id.media_controller);
        resolution_listview = (ListView) findViewById(R.id.resolution_listview);
        resolution_switch.setOnClickListener(this);
        videoView.setOnTouchListener(this);
        progress_seekbar.setOnSeekBarChangeListener(mSeekListener);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, videoViewHeight);
        videoview_layout.setLayoutParams(params);
        String rawUri = getIntent().getStringExtra("video");
        Uri uri = Uri.parse(rawUri); //设置视频文件路径
        //设置视频控制器
//        videoView.setMediaController(new MediaController(this));
        //播放完成回调
        videoView.setOnCompletionListener(new MyPlayerOnCompletionListener());
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            //@Override
            public void onPrepared(MediaPlayer mp) {
                totalDuration=videoView.getDuration();
                if (mTotalTime != null)
                    mTotalTime.setText("/"+generateTime(totalDuration));
            }
        });
        //设置视频路径
        videoView.setVideoURI(uri);
        //开始播放视频
        videoView.start();
        //设置字幕
        final String subtitlePath = getIntent().getStringExtra("subtitle");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(subtitlePath); //字幕文件路径
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    SrtParser.parseSrt(VideoDetails_page.this, conn.getInputStream());
                    SrtParser.showSRT(videoView, tvSrt);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
        mHandler.sendEmptyMessageDelayed(0, 500);
        initVideoResolution();
    }

    public void myItemClick(View view){
        // 获取itemView的位置
        int position = recyclerView_remark.getChildAdapterPosition(view);
//        Toast.makeText(VideoDetails_page.this, "点击了 " + position,
//                Toast.LENGTH_SHORT).show();
        replyDialog(position);
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

    public void remarkDialog() {  //发布新评论
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
        final EditText editText = inflate.findViewById(R.id.reply_content);
        Button fb = inflate.findViewById(R.id.fb);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            String var = editText.getText().toString();
                            String json = "{\"u_id\":" + u_id + ", \"target_id\":" + video_id
                                    + ",\"var\":\"" + var + "\",\"image\":\"\",\"count\":0}";
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                    .url(Api.url + "/comment/add")
                                    .post(RequestBody.create(MediaType.parse("application/json"), json))
                                    .build();
                            Response response = client.newCall(request).execute(); //执行发送指令
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(VideoDetails_page.this,"发布成功！",Toast.LENGTH_SHORT).show();
                                    mAdapter.addData(nickname,var);
                                    editText.setText("");
                                }
                            });
                        }catch (Exception e){
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(VideoDetails_page.this,"发布失败！",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
            }
        });
        dialog.show();//显示对话框
    }

    public void replyDialog(int position) {  //回复评论
        dialog = new Dialog(this,R.style.ActionSheetDialogStyle);//填充对话框的布局
        inflate = LayoutInflater.from(this).inflate(R.layout.remark_dialog, null);        //初始化控件
        //设置 输入提醒——回复对象
        final EditText reply_to=(EditText) inflate.findViewById(R.id.reply_content);
        //SpannableString s = new SpannableString(mAdapter.getName(position));
        reply_to.setHint("@"+mAdapter.getName(position));
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
        //int height= dm.heightPixels; // 屏幕高度（像素）
        float density = dm.density;//屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = dm.densityDpi;//屏幕密度dpi（120 / 160 / 240）
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        //这个地方可以用ViewGroup.LayoutParams.MATCH_PARENT属性，各位试试看看有没有效果
        layoutParams.width = width;
        //layoutParams.height = height;
        //将属性设置给窗体
        dialogWindow.setAttributes(lp);
        Button fb = inflate.findViewById(R.id.fb);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String var = "@"+mAdapter.getName(position)+" "+reply_to.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String json = "{\"u_id\":" + u_id + ", \"target_id\":" + video_id
                                    + ",\"var\":\"" + var + "\",\"image\":\"\",\"count\":0}";
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                    .url(Api.url + "/comment/add")
                                    .post(RequestBody.create(MediaType.parse("application/json"), json))
                                    .build();
                            Response response = client.newCall(request).execute(); //执行发送指令
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(VideoDetails_page.this, "回复评论成功！", Toast.LENGTH_SHORT).show();
                                    mAdapter.addData(nickname,var);
                                    reply_to.setText("");
                                }
                            });
                        }catch (Exception e){
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(VideoDetails_page.this, "回复评论失败！", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
            }
        });
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
            case R.id.mediacontroller_play_pause:  //播放器
                Log.d("gaolei", "mediacontroller_play_pause");
                updatePausePlay();
                break;
            case R.id.resolution_switch:   //播放器
                resolution_listview.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
//        dialog.dismiss();
    }

    //播放器需要的函数
    private void initVideoResolution(){
        VideoPathObject object1=new VideoPathObject();
        object1.videoStatus="超清";
        videopathList.add(object1);
        switchResolution(videopathList);
    }

    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {
        @Override
        public void onCompletion(MediaPlayer mp) {
            Toast.makeText( VideoDetails_page.this, "播放完成了", Toast.LENGTH_SHORT).show();
        }
    }

    private SeekBar.OnSeekBarChangeListener mSeekListener = new SeekBar.OnSeekBarChangeListener() {
        public void onStartTrackingTouch(SeekBar bar) {
            mDragging = true;
            mHandler.removeMessages(SHOW_PROGRESS);
            mAM.setStreamMute(AudioManager.STREAM_MUSIC, true);
        }

        public void onProgressChanged(SeekBar bar, int progress,
                                      boolean fromuser) {
            if (!fromuser)
                return;

            int newposition = (int)(totalDuration * progress) / 1000;

            String time = generateTime(newposition);
            videoView.seekTo(newposition);
            mCurrentTime.setText(time);
        }

        public void onStopTrackingTouch(SeekBar bar) {
            videoView.seekTo(((int)totalDuration * bar.getProgress()) / 1000);
            hideMediaController(sDefaultTimeout);
            mAM.setStreamMute(AudioManager.STREAM_MUSIC, false);
            mDragging = false;
            mHandler.sendEmptyMessageDelayed(SHOW_PROGRESS, 1000);
        }
    };

    private void switchResolution(final List<VideoPathObject> videopathList) {
        resolution_switch.setText(videopathList.get(videopathList.size() - 1).videoStatus);
        mediacontroller_play_pause.setImageResource(R.drawable.player_play);
        final ResolutionAdapter adapter = new ResolutionAdapter(videopathList,
                VideoDetails_page.this);
        resolution_listview.setAdapter(adapter);
        resolution_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                        VideoPathObject pathObject = videopathList.get(position);
                        adapter.changePosition(position);
                        resolution_switch.setText(pathObject.videoStatus);
                        resolution_listview.setVisibility(View.GONE);
                    }
                });
    }

    public void showOrHideController() {
        if (mShowing) {
            mHandler.removeMessages(SHOW_PROGRESS);
            mHandler.removeMessages(FADE_OUT);
            mMediaController.setVisibility(View.GONE);
            resolution_listview.setVisibility(View.GONE);
            mShowing = false;
        } else {
            mHandler.sendEmptyMessage(SHOW_PROGRESS);
            mMediaController.setVisibility(View.VISIBLE);
            hideMediaController(sDefaultTimeout);
            mShowing = true;
        }
    }

    public void hideMediaController(int sDefaultTimeout) {
        mHandler.sendEmptyMessageDelayed(FADE_OUT, sDefaultTimeout);
    }

    private long setControllerProgress() {
        if (videoView == null || mDragging)
            return 0;

        int position = videoView.getCurrentPosition();
        if (progress_seekbar != null) {
            if (totalDuration > 0) {
                long pos = 1000L * position / totalDuration;
                // Log.d("gaolei", "progress--------------" + pos);
                progress_seekbar.setProgress((int) pos);
            }
            int percent = videoView.getBufferPercentage();
            progress_seekbar.setSecondaryProgress(percent * 10);
        }

        if (mCurrentTime != null)
            mCurrentTime.setText(generateTime(position));

        return position;
    }

    private static String generateTime(long position) {
        int totalSeconds = (int) (position / 1000);

        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;
        if (hours > 0) {
            return String.format(Locale.US, "%02d:%02d:%02d", hours, minutes, seconds);
        } else {
            return String.format(Locale.US, "%02d:%02d", minutes, seconds);
        }
    }

    private void updatePausePlay() {
        if (videoView.isPlaying()) {
            videoView.pause();
            mediacontroller_play_pause.setImageResource(R.drawable.player_pause);
        } else {
            videoView.start();
            mediacontroller_play_pause.setImageResource(R.drawable.player_play);
        }
    }

    public void showResolution(View view) {
        if (!isResolution) {
            resolution_listview.setVisibility(View.VISIBLE);
            isResolution = true;
        } else {
            resolution_listview.setVisibility(View.GONE);
            isResolution = false;
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            changeToFullScreen();
            Log.d("gaolei", "ORIENTATION_LANDSCAPE-------------");
        }
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            changeToSmallScreen();
            Log.d("gaolei", "ORIENTATION_PORTRAIT-------------");
        }
    }

    public void switchScreen(View view) {
        if (isPortraint) {
            handToFullScreen();
        } else {
            handToSmallScreen();
        }
    }

    public void handToSmallScreen() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        changeToSmallScreen();
        //这里点击按钮转屏，用户5秒内不转到小屏，将自动识别当前屏幕方向
        autoSwitchScreenOrientation();
    }

    public void handToFullScreen() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        changeToFullScreen();
        //这里点击按钮转屏，用户5秒内不转到全屏，将自动识别当前屏幕方向
        autoSwitchScreenOrientation();
    }

    private void changeToFullScreen() {
        LinearLayout backbutton = findViewById(R.id.backbutton);
        LinearLayout videod = findViewById(R.id.videod);
        RelativeLayout commentssection = findViewById(R.id.commentssection);
        backbutton.setVisibility(View.GONE);
        videod.setVisibility(View.GONE);
        commentssection.setVisibility(View.GONE);
        isPortraint = false;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        videoview_layout.setLayoutParams(params2);
        videoView.setLayoutParams(params);
        WindowManager.LayoutParams windowparams = getWindow().getAttributes();
        windowparams.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(windowparams);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        switch_screen.setImageResource(R.drawable.player_switch_small);
    }

    private void changeToSmallScreen() {
        LinearLayout backbutton = findViewById(R.id.backbutton);
        LinearLayout videod = findViewById(R.id.videod);
        RelativeLayout commentssection = findViewById(R.id.commentssection);
        backbutton.setVisibility(View.VISIBLE);
        videod.setVisibility(View.VISIBLE);
        commentssection.setVisibility(View.VISIBLE);
        isPortraint = true;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, videoViewHeight);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, videoViewHeight);
        videoview_layout.setLayoutParams(params2);
        videoView.setLayoutParams(params);
        WindowManager.LayoutParams windowparams = getWindow().getAttributes();
        windowparams.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setAttributes(windowparams);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        switch_screen.setImageResource(R.drawable.player_switch_big);
    }

    public void autoSwitchScreenOrientation() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
                Log.d("gaolei", "SCREEN_ORIENTATION_FULL_SENSOR");				}
        }, 5000);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                showOrHideController();
                break;
            }
        }
        return false;
    }

    public void onRestart(){
        super.onRestart();
        videoView.start();
        mediacontroller_play_pause.setImageResource(R.drawable.player_play);
    }

    public void onStop(){
        super.onStop();
        videoView.pause();
        mediacontroller_play_pause.setImageResource(R.drawable.player_pause);
        mHandler.removeMessages(PARSE_SRT);
        mHandler.removeMessages(SHOW_PROGRESS);
    }
}
